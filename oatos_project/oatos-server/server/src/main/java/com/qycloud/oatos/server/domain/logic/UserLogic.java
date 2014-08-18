package com.qycloud.oatos.server.domain.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conlect.oatos.dto.autobean.IEnterpriseUserDTO;
import com.conlect.oatos.dto.client.DepartmentAndUserDTO;
import com.conlect.oatos.dto.client.EnterpriseUserDTO;
import com.conlect.oatos.dto.client.SimpleUserInfoDTO;
import com.conlect.oatos.dto.client.UserIconDTO;
import com.conlect.oatos.dto.client.UserInfoCategoryDTO;
import com.conlect.oatos.dto.client.UserInfoDTO;
import com.conlect.oatos.dto.client.UserInfosDTO;
import com.conlect.oatos.dto.client.UserStatusDTO;
import com.conlect.oatos.dto.client.UserStatusesDTO;
import com.conlect.oatos.dto.client.mail.MailDTO;
import com.conlect.oatos.dto.client.user.UserImportSaveDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.EmployeeStatus;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.OrderStatus;
import com.conlect.oatos.dto.status.UserStatus;
import com.conlect.oatos.dto.status.UserType;
import com.conlect.oatos.utils.StringUtils;
import com.qycloud.oatos.server.dao.BlockListMapper;
import com.qycloud.oatos.server.dao.DepartmentMapper;
import com.qycloud.oatos.server.dao.EnterpriseMapper;
import com.qycloud.oatos.server.dao.LoginMapper;
import com.qycloud.oatos.server.dao.PersonalFileMapper;
import com.qycloud.oatos.server.dao.PersonalFolderMapper;
import com.qycloud.oatos.server.dao.RoleMapper;
import com.qycloud.oatos.server.dao.UserMapper;
import com.qycloud.oatos.server.dao.UsualContactMapper;
import com.qycloud.oatos.server.domain.entity.Block;
import com.qycloud.oatos.server.domain.entity.Department;
import com.qycloud.oatos.server.domain.entity.Enterprise;
import com.qycloud.oatos.server.domain.entity.Login;
import com.qycloud.oatos.server.domain.entity.PersonalFolder;
import com.qycloud.oatos.server.domain.entity.Role;
import com.qycloud.oatos.server.domain.entity.User;
import com.qycloud.oatos.server.domain.entity.UserRole;
import com.qycloud.oatos.server.domain.model.CachedUser;
import com.qycloud.oatos.server.logic.mail.MailSender;
import com.qycloud.oatos.server.security.SecurityUtil;
import com.qycloud.oatos.server.util.BllLogger;
import com.qycloud.oatos.server.util.CacheClient;
import com.qycloud.oatos.server.util.CacheKey;
import com.qycloud.oatos.server.util.LogicException;
import com.qycloud.oatos.server.util.MyConstants;
import com.qycloud.oatos.server.util.ReadTxtUtil;

/**
 * 企业用户服务
 * @author yang
 *
 */
@Service("UserLogic")
public class UserLogic {

	@Autowired
	private SequenceLogic sequence;
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UsualContactMapper usualContactMapper;
	@Autowired
	private LoginMapper loginMapper;
	@Autowired
	private PersonalFolderMapper personalFolderMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private BlockListMapper blockListMapper;
	@Autowired
	private PersonalFileMapper personalFileMapper;
	@Autowired
	private EnterpriseLogic enterpriseLogic;
	@Autowired
	private MailSender mailSender;

	/**
	 * 添加企业用户
	 * @param userDTO
	 * @return
	 */
	@Transactional
	public String addUser(EnterpriseUserDTO userDTO) {
		long userId = doAddUser(userDTO);
		return String.valueOf(userId);
	}

	/**
	 * 添加企业用户
	 * @param userDTO
	 * @return
	 */
	private long doAddUser(EnterpriseUserDTO userDTO) {
		String pwd = SecurityUtil.checkHashKey(userDTO.getAccount(), userDTO.getPassword(), userDTO);
		
		// 检查企业是否存在
		Enterprise ent = enterpriseMapper.getEnterpriseById(userDTO.getEnterpriseId());
		if (ent == null) {
			throw new LogicException(ErrorType.errorEnterpriseNotExist);
		}
		// 检查企业员工数
		long userCount = userMapper.getUserCountByEntId(ent.getEnterpriseId());
		if (userCount >= ent.getMaxEmployees()) {
			throw new LogicException(ErrorType.errorUserNoOver);
		}
		if (userCount >= CommConstants.DEFAULT_MAX_EMPLOYEES && OrderStatus.isPayExpired(ent.getPayStatus())) {
			throw new LogicException(ErrorType.errorPayExpired);
		}
		
		userMapper.getUserByEntId(ent.getEnterpriseId());
		userDTO.setEnterpriseName(ent.getEnterpriseName());

		// 检查用户名是否存在，用户名可能被注册
		Login loginModel = loginMapper.getLoginByAccount(userDTO.getEnterpriseName(),
				userDTO.getAccount());

		if (loginModel != null) {
			throw new LogicException(ErrorType.errorEmployeeAlreadyExist);
		}

		// 添加用户信息
		User user = new User();
		user.setUserId(sequence.getNextId());
		user.setEnterpriseId(userDTO.getEnterpriseId());
		user.setUserName(userDTO.getAccount());
		if (userDTO.getDepartmentId() > 0) {
			user.setDepartmentId(userDTO.getDepartmentId());
		}
		user.setRegisterMail(userDTO.getEmail());
		user.setUserLable("");
		user.setRegistTime(new Date());
		user.setUserSex(userDTO.getGender());
		user.setEmployeeNumber(userDTO.getEmployeeNumber());
		user.setJobTitle(userDTO.getJobTitle());

		user.setDiskSize(CommConstants.PERSONAL_DISK_SIZE);
		user.setUserType(UserType.BusinessUser);
		user.setStatus(EmployeeStatus.Register);
		user.setCustomerService(userDTO.isCustomerService());
		user.setRealName(userDTO.getRealName());
		user.setPayStatus(OrderStatus.UNPAY.name());

		if(userCount >= CommConstants.DEFAULT_MAX_EMPLOYEES) {
			user.setPayStatus(OrderStatus.PAY_OK.name());
		}
		userMapper.addUser(user);

		// 插入用户登录数据
		Login login = new Login();
		login.setLoginId(sequence.getNextId());
		login.setUserId(user.getUserId());
		login.setEntName(userDTO.getEnterpriseName());
		login.setAccount(userDTO.getAccount());
		login.setPassword(pwd);
		loginMapper.addLogin(login);

		// 插入用户默认文件夹
		addDefaultUserFolder(user.getUserId());

		// 设置默认角色
		Long roleId = roleMapper.getFirstRoleByEntId(userDTO.getEnterpriseId());
		if (roleId != null) {
			UserRole userRole = new UserRole(user.getUserId(), roleId);
			roleMapper.addUserRole(userRole);
		}
		// send mail
		sendEnterpriseUserMail(ent, user, userDTO.getLocale(), userDTO.isNet());
		return user.getUserId();
	}

	/**
	 * 增加用户系统文件夹
	 * 
	 * @param userId
	 */
	public void addDefaultUserFolder(long userId) {
		// 增加文档文件夹
		addPersonlFolder(CommConstants.DocumentFolderName, userId);
		// 增加图片文件夹
		addPersonlFolder(CommConstants.PictureFolderName, userId);
		// 收到的文件
		addPersonlFolder(CommConstants.ReceivedFileFolderName, userId);
		// 发送的文件
		addPersonlFolder(CommConstants.SendFileFolderName, userId);
		// 邮件附件
		addPersonlFolder(CommConstants.EmailAttachFolderName, userId);
	}

	/**
	 * 添加个人网盘文件夹
	 * @param folderName
	 * @param userId
	 */
	private void addPersonlFolder(String folderName, long userId) {
		PersonalFolder personFolder = new PersonalFolder();
		personFolder.setFolderName(folderName);
		personFolder.setUserId(userId);
		if (personalFolderMapper.getDefaultFolder(personFolder) == null) {
			personFolder.setFolderId(sequence.getNextId());
			personalFolderMapper.addPersonalFolder(personFolder);
		}
	}

	/**
	 * 发送添加用户邮件
	 * @param ent
	 * @param user
	 * @param locale
	 * @param isNet
	 */
	private void sendEnterpriseUserMail(Enterprise ent, User user, String locale, boolean isNet) {
		if (user.getRegisterMail() != null && !"".equals(user.getRegisterMail().trim())) {
			String mailMsg = ReadTxtUtil.getEnterpriseUserMailText(locale, isNet);

			mailMsg = mailMsg.replace(MyConstants.enterpriseName, ent.getEnterpriseName());
			mailMsg = mailMsg.replace(MyConstants.userName, user.getUserName());

			// 发邮件
			MailDTO mail = new MailDTO();
			if (locale != null && locale.indexOf("en") != -1) {
				mail.setSubject(MyConstants.EnterpriseSubjectEn);
			}
			else {
				mail.setSubject(MyConstants.EnterpriseSubject);
			}
			mail.setRecieve(user.getRegisterMail());
			mail.setContent(mailMsg);
			mailSender.sendMail(mail);
		}
	}

	/**
	 * 用户导入
	 * @param userImportDTO
	 * @return
	 */
	@Transactional
	public String importUser(UserImportSaveDTO userImportDTO) {
		Enterprise ent = enterpriseMapper.getEnterpriseById(userImportDTO.getEntId());
		long userCount = userMapper.getUserCountByEntId(ent.getEnterpriseId());
		if ((userCount + userImportDTO.getUserList().size()) > ent.getMaxEmployees()) {
			throw new LogicException(ErrorType.errorUserNoOver);
		}
		// 保存部门信息
		saveDepartments(userImportDTO);
		// 保存角色信息
		saveRoles(userImportDTO);

		for (EnterpriseUserDTO user : userImportDTO.getUserList()) {
			long userId = doAddUser(user);
			// 关联角色
			if (user.getRoleId() > 0) {
				UserRole userRole = new UserRole();
				userRole.setRoleId(user.getRoleId());
				userRole.setUserId(userId);
				roleMapper.addUserRole(userRole);
			}
		}
		return CommConstants.OK_MARK;
	}

	/**
	 * 导入部门
	 * @param userImportDTO
	 */
	private void saveDepartments(UserImportSaveDTO userImportDTO) {
		// 企业已经存在的部门
		List<Department> existDeparts = departmentMapper.getDepartmentByEntId(userImportDTO.getEntId());

		for (IEnterpriseUserDTO user : userImportDTO.getUserList()) {
			// 分割部门名，父部门在后，子部门在前
			String[] deptNames = splitDeptName(user.getDepartmentName());
			if (deptNames != null && deptNames.length > 0) {
				Long parentId = null;
				for (int i = deptNames.length - 1; i >= 0; i--) {
					String each = deptNames[i];
					// 部门是否存在
					Department dept = getAddedDepartment(each, existDeparts, parentId);
					if (dept == null) {
						// 部门不存在，新建部门
						dept = new Department();
						dept.setDepartmentId(sequence.getNextId());
						dept.setEnterpriseId(userImportDTO.getEntId());
						dept.setName(each);
						dept.setParentId(parentId);

						departmentMapper.addDepartment(dept);
						existDeparts.add(dept);
					}
					parentId = dept.getDepartmentId();
					if (i == 0) {
						// 设置用户的部门信息
						user.setDepartmentId(dept.getDepartmentId());
						user.setDepartmentName(dept.getName());
					}
				}
			}
		}
	}

	/**
	 * 分割部门名
	 */
	private String[] splitDeptName(String str) {
		String split = "/";
		String[] names = null;
		if (str != null && !str.trim().equals("")) {
			str = str.replaceAll("(\\s*/+\\s*)+", split);
	        if (str.startsWith(split)) {
	            str = str.substring(str.indexOf(split) + 1);
            }
	        if (str.endsWith(split)) {
	            str = str.substring(0, str.lastIndexOf(split));
            }
			names = str.split(split);
        }
		return names;
	}

	/**
	 * 判断部门是否已经存在
	 * 
	 * @param each
	 * @param existDeparts
	 * @param parentName
	 * @return
	 */
	private Department getAddedDepartment(String each, List<Department> existDeparts, Long parentId) {
		for (Department d : existDeparts) {
			// 部门名相同
			if (each.equalsIgnoreCase(d.getName())) {
				if (parentId == null) {
					// 父部门ID为空，为顶级部门
					return d;
				}

				if (d.getParentId() == null) {
					// 修改父部门id
					d.setParentId(parentId);
					departmentMapper.updateDepartment(d);
					return d;
				}
				if (parentId.equals(d.getParentId())) {
					// 父部门相同
					return d;
				}
			}
		}
		return null;
	}

	/**
	 * 导入角色
	 * @param userImportDTO
	 */
	private void saveRoles(UserImportSaveDTO userImportDTO) {
		List<Role> existRoles = roleMapper.getRolesByEntId(userImportDTO.getEntId());
		for (IEnterpriseUserDTO user : userImportDTO.getUserList()) {
			boolean roleExist = false;
			String roleName = user.getRoleName();
			if (StringUtils.isValid(roleName)) {
				for (Role r: existRoles) {
					if (roleName.equals(r.getName())) {
						roleExist = true;
						user.setRoleId(r.getRoleId());
						break;
					}
				}
				if (!roleExist) {
					Role role = new Role();
					role.setRoleId(sequence.getNextId());
					role.setEnterpriseId(userImportDTO.getEntId());
					role.setName(roleName);
					roleMapper.addRole(role);
					existRoles.add(role);
					user.setRoleId(role.getRoleId());
				}
			}

		}
	}

	/**
	 * 获取用户信息
	 * 
	 * @param UserId
	 * @return
	 */
	public UserInfoDTO getUserInfo(long userId) {
		User user = userMapper.getUser(userId);
		UserInfoDTO userInfoDTO = user.toUserInfoDTO();
		userInfoDTO.setOnlineStatus(getUserStatus(userId));
		return userInfoDTO;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param mailUser
	 */
	@Transactional
	public void updateUserInfo(UserInfoDTO userDTO) {
		doUpdateUser(userDTO);
	}
	
	


	/**
	 * 修改用户信息
	 * @param userDTO
	 */
	private void doUpdateUser(UserInfoDTO userDTO) {
		User user = new User(userDTO);
		userMapper.updateUser(user);
		if (UserType.Administrator != user.getUserType()) {
			Login loginModel = new Login();
			loginModel.setUserId(user.getUserId());
			loginModel.setAccount(user.getUserName());
			loginMapper.updateAccount(loginModel);
		}
	}

	/**
	 * 批量修改用户信息
	 * @param userDTOs
	 */
	@Transactional
	public void updateUsers(List<UserInfoDTO> userDTOs) {
		for (UserInfoDTO u : userDTOs) {
			doUpdateUser(u);
		}
	}

	/**
	 * 修改员工信息和登录密码
	 * @param userDTO
	 * @return
	 */
	@Transactional
	public String updateUserAndPassword(UserInfoDTO userDTO) {
		String pwd = SecurityUtil.checkHashKey(userDTO.getUserName(), userDTO.getPassword(), userDTO);

		User user = new User(userDTO);
		userMapper.updateUser(user);

		if (UserType.Administrator == userDTO.getUserType()) {
			enterpriseMapper.changeAdminPassword(userDTO.getEnterpriseId(), userDTO.getPassword());
		}
		else {
			Login loginModel = new Login();
			loginModel.setUserId(userDTO.getUserId());
			loginModel.setAccount(user.getUserName());
			loginModel.setPassword(pwd);
			loginMapper.updateAccountAndPassword(loginModel);
		}
		return CommConstants.OK_MARK;
	}

	/**
	 * 获取用户在线状态
	 */
	public String getUserStatus(long userId) {
		try {
			String key = CacheKey.Online + userId;

			// 用户在线状
			Object cacheData = CacheClient.getInstance().get(key);
			// 如果存在缓存
			if (cacheData != null) {
				CachedUser cachedUser = (CachedUser) cacheData;
				return cachedUser.getOnlineStatus();
			}
		} catch (Exception ex) {
			BllLogger.getLogger().error(userId, ex);
		}
		return UserStatus.Logout;
	}

	/**
	 * 取在线好友和同事(基本信息)
	 * @param userId
	 * @return
	 */
	public List<SimpleUserInfoDTO> getOnlineFriends(long userId) {
		List<SimpleUserInfoDTO> userList = new ArrayList<SimpleUserInfoDTO>();
		User user = userMapper.getUser(userId);
		if (user.getEnterpriseId() != null) {
			// get colleague
			List<User> users = userMapper.getUserByEntId(user.getEnterpriseId());
			if (users != null) {
				for (User u : users) {
					if (u.getUserId() == userId) {
						continue;
					}
					String status = getUserStatus(u.getUserId());
					if (!UserStatus.Logout.equals(status)) {
						SimpleUserInfoDTO uDTO = new SimpleUserInfoDTO(u.getUserId(), u.getUserName());
						userList.add(uDTO);
					}
				}

			}
		}
		return userList;
	}

	/**
	 * 统计企业在线用户数
	 * @return
	 */
	public String statisticalUser() {
		StringBuilder sb = new StringBuilder();
		List<Enterprise> ents = enterpriseMapper.getAllEnterprise();
		if (ents != null) {
			for (Enterprise ent : ents) {
				List<User> users = userMapper.getUserByEntId(ent.getEnterpriseId());
				// 企业在线用户数
				int k = 0;
				if (users != null) {
					for (User u : users) {
						String status = getUserStatus(u.getUserId());
						if (!UserStatus.Logout.equals(status)
						        && !UserStatus.Offline.equals(status)) {
							k++;
						}
					}
				}
				sb.append(ent.getEnterpriseId());
				sb.append(",");
				sb.append(k);
				sb.append(";");
			}
		}
		return sb.toString();
	}
	

	/**
	 * 取企业用户包含角色
	 * @param entId
	 * @return
	 */
	public List<UserInfoDTO> getUsersWithRolesByEntId(long entId) {
		List<User> users = userMapper.getUserByEntId(entId);

		List<Role> roles = roleMapper.getRolesByEntId(entId);
		List<UserRole> userRoles = roleMapper.getUserRoleByEntId(entId);

		List<UserInfoDTO> userDTOs = new ArrayList<UserInfoDTO>();
		if (users != null) {
			for (User u : users) {
				UserInfoDTO user = u.toUserInfoDTO();
				if (userRoles != null && roles != null) {
					for (UserRole ur : userRoles) {
						if (user.getUserId() == ur.getUserId()) {
							for (Role r : roles) {
								if (ur.getRoleId() == r.getRoleId()) {
									user.getRoles().add(r.toRoleDTO());
									break;
								}
							}
						}
					}
				}
				userDTOs.add(user);
			}
		}
		return userDTOs;
	}

	/**
	 * 删除企业用户
	 * @param emps
	 * @return
	 */
	@Transactional
	public String deleteEnterpriseUsers(UserInfosDTO emps) {
		for (UserInfoDTO user : emps.getUserInfoDTOList()) {
			if (user.getUserType() == UserType.Administrator) {
				throw new LogicException(ErrorType.error500);
			}
			// delete block
			blockListMapper.deleteBlockList(user.getUserId());
			// delete roles
			roleMapper.deleteUserRoleByUserId(user.getUserId());
			// delete file
			personalFileMapper.deleteFileByUserId(user.getUserId());
			personalFolderMapper.deleteFolderByUserId(user.getUserId());

			// 删除用户信息
			userMapper.deleteUser(user.getUserId());
		}
		return CommConstants.OK_MARK;

	}

	/**
	 * 取所有同事的在线状态
	 * @param entId
	 * @return
	 */
	public UserStatusesDTO getEmployeesStatus(long entId) {
		List<UserInfoDTO> userList = getUsersByEntId(entId);
		List<UserStatusDTO> statusDTOs = new ArrayList<UserStatusDTO>();
		for (UserInfoDTO user : userList) {
			UserStatusDTO statusDTO = new UserStatusDTO(user.getUserId(), UserStatus.Logout);
			// 获取每个员工的在线状态
			String key = CacheKey.Online + user.getUserId();
			Object onlineCache = CacheClient.getInstance().get(key);
			if (onlineCache != null) {
				CachedUser online = (CachedUser) onlineCache;
				statusDTO.setUserStatus(online.getOnlineStatus());
			}
			statusDTOs.add(statusDTO);
		}
		UserStatusesDTO statusesDTO = new UserStatusesDTO();
		statusesDTO.setUserStatus(statusDTOs);
		return statusesDTO;
	}

	/**
	 * 取企业的所有员工信息，不包含状态和角色
	 * @param entId
	 * @return
	 */
	public List<UserInfoDTO> getUsersByEntId(long entId) {
		List<UserInfoDTO> userDTOs = new ArrayList<UserInfoDTO>();
		List<User> users = userMapper.getUserByEntId(entId);
		for (User u : users) {
			userDTOs.add(u.toUserInfoDTO());
		}
		return userDTOs;
	}

	/**
	 * 取企业部门和员工，不包含状态和角色
	 * @param entId
	 * @return
	 */
	public DepartmentAndUserDTO getDepartmentAndUserByEntId(long entId) {
		DepartmentAndUserDTO depAndUserDTO = new DepartmentAndUserDTO();
		depAndUserDTO.setDepartmentList(enterpriseLogic.getDepartmentsByEntId(entId));
		depAndUserDTO.setUserList(getUsersByEntId(entId));
		return depAndUserDTO;
	}

	/**
	 * 取企业部门和员工，包含角色，不包含状态
	 * @param entId
	 * @return
	 */
	public DepartmentAndUserDTO getDepartmentAndUserWithRolesByEntId(long entId) {
		DepartmentAndUserDTO depAndUserDTO = new DepartmentAndUserDTO();
		depAndUserDTO.setDepartmentList(enterpriseLogic.getDepartmentsByEntId(entId));
		depAndUserDTO.setUserList(getUsersWithRolesByEntId(entId));
		return depAndUserDTO;
	}

	/**
	 * 按用户ID取企业部门和员工，包含状态，不包含角色
	 * @param userId
	 * @return
	 */
	public DepartmentAndUserDTO getDepartmentAndUserWithStatusByUserId(long userId) {
		DepartmentAndUserDTO depAndUserDTO = new DepartmentAndUserDTO();
		User userModel = userMapper.getUser(userId);
		depAndUserDTO.setDepartmentList(enterpriseLogic.getDepartmentsByEntId(userModel.getEnterpriseId()));

		List<UserInfoDTO> userInfoDTOs = getUsersByEntId(userModel.getEnterpriseId());
		List<Block> entBlockList = blockListMapper.getEnterpriseBlocksByUserId(userId);
		List<Long> entBlocks = new ArrayList<Long>();
	    for (Block b : entBlockList) {
        	long blockUserId = b.getBlockUserId();
            if (b.getUserId() == userId) {
            	blockUserId = b.getBlockUserId();
            } else if (b.getBlockUserId() == userId) {
            	blockUserId = b.getUserId();
			}
            if (userId != blockUserId) {
            	entBlocks.add(blockUserId);
            }
        }
	    List<Long> usualContacts = usualContactMapper.getUsualContacts(userId);
		List<Block> blockList = blockListMapper.getBlockListByUserId(userId);
		List<UserInfoDTO> visibleUsers = new ArrayList<UserInfoDTO>();
		for (UserInfoDTO user : userInfoDTOs) {
			if (!entBlocks.contains(user.getUserId())) {
				// 获取每个员工的在线状态
				String key = CacheKey.Online + user.getUserId();
				Object onlineCache = CacheClient.getInstance().get(key);
				if (onlineCache != null) {
					CachedUser online = (CachedUser) onlineCache;
					user.setOnlineStatus(online.getOnlineStatus());
					user.setAgent(online.getAgent());
				}
				else {
					user.setOnlineStatus(UserStatus.Logout);
				}
				// 阻止联系人
				for (Block blockModel : blockList) {
					if (user.getUserId() == blockModel.getBlockUserId()) {
						user.setBlocked(true);
						break;
					}
				}
				// 常用联系人
				if (usualContacts.contains(user.getUserId())) {
					user.setUsualContact(true);
				}
				visibleUsers.add(user);
			}
		}
		depAndUserDTO.setUserList(visibleUsers);
		return depAndUserDTO;
	}

	/**
	 * 按企业ID取企业部门和员工，包含状态，不包含角色
	 * @param entId
	 * @return
	 */
	public DepartmentAndUserDTO getDepartmentAndUserWithStatusByEntId(long entId) {
		DepartmentAndUserDTO depAndUserDTO = new DepartmentAndUserDTO();
		depAndUserDTO.setDepartmentList(enterpriseLogic.getDepartmentsByEntId(entId));

		List<UserInfoDTO> userInfoDTOs = getUsersByEntId(entId);
		for (UserInfoDTO user : userInfoDTOs) {
			// 获取每个员工的在线状态
			String key = CacheKey.Online + user.getUserId();
			Object onlineCache = CacheClient.getInstance().get(key);
			if (onlineCache != null) {
				CachedUser online = (CachedUser) onlineCache;
				user.setOnlineStatus(online.getOnlineStatus());
				user.setAgent(online.getAgent());
			}
			else {
				user.setOnlineStatus(UserStatus.Logout);
			}
		}
		depAndUserDTO.setUserList(userInfoDTOs);
		return depAndUserDTO;
	}

	/**
	 * 取企业在线和不在线的用户ID
	 * @return
	 */
	public UserInfoCategoryDTO getAllUserIdsByCategory() {
		List<Long> userIds = userMapper.getAllUserId();
		List<Long> offlineUserIds = new ArrayList<Long>();
		List<Long> onlineUserIds = new ArrayList<Long>();
		for (Long userId : userIds) {
			if (UserStatus.Offline.equals(getUserStatus(userId)))
				offlineUserIds.add(userId);
			else
				onlineUserIds.add(userId);
		}
		return new UserInfoCategoryDTO(onlineUserIds, offlineUserIds);
	}

	/**
	 * 修改用户头像
	 * @param userIconDTO
	 */
	@Transactional
	public void changeUserIcon(UserIconDTO userIconDTO) {
		User user = userMapper.getUser(userIconDTO.getUserId());
		user.setUserHeaderPhoto(userIconDTO.getPath());
		userMapper.updateUser(user);
	}


}
