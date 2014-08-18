package com.qycloud.oatos.server.domain.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conlect.oatos.dto.client.DepartmentDTO;
import com.conlect.oatos.dto.client.DepartmentsDTO;
import com.conlect.oatos.dto.client.EnterpriseDTO;
import com.conlect.oatos.dto.client.LimitDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;
import com.conlect.oatos.dto.client.UserInfoDTO;
import com.conlect.oatos.dto.client.admin.AdminDataDTO;
import com.conlect.oatos.dto.client.mail.MailDTO;
import com.conlect.oatos.dto.status.AdminToken;
import com.conlect.oatos.dto.status.CallStatus;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.EmployeeStatus;
import com.conlect.oatos.dto.status.EnterpriseStatus;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.ModuleStatus;
import com.conlect.oatos.dto.status.OrderStatus;
import com.conlect.oatos.dto.status.SystemType;
import com.conlect.oatos.dto.status.UserAgent;
import com.conlect.oatos.dto.status.UserStatus;
import com.conlect.oatos.dto.status.UserType;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.dao.AdminDepartmentMapper;
import com.qycloud.oatos.server.dao.AdminFolderMapper;
import com.qycloud.oatos.server.dao.AdminMapper;
import com.qycloud.oatos.server.dao.CustomerMapper;
import com.qycloud.oatos.server.dao.DepartmentMapper;
import com.qycloud.oatos.server.dao.EntModuleMapper;
import com.qycloud.oatos.server.dao.EnterpriseMapper;
import com.qycloud.oatos.server.dao.LoginMapper;
import com.qycloud.oatos.server.dao.ProductKeyMapper;
import com.qycloud.oatos.server.dao.RoleMapper;
import com.qycloud.oatos.server.dao.RolePermissionMapper;
import com.qycloud.oatos.server.dao.ShareFolderHistoryMapper;
import com.qycloud.oatos.server.dao.ShareFolderMapper;
import com.qycloud.oatos.server.dao.UserMapper;
import com.qycloud.oatos.server.domain.entity.Admin;
import com.qycloud.oatos.server.domain.entity.AdminDepartment;
import com.qycloud.oatos.server.domain.entity.AdminFolder;
import com.qycloud.oatos.server.domain.entity.Customer;
import com.qycloud.oatos.server.domain.entity.Department;
import com.qycloud.oatos.server.domain.entity.EntModule;
import com.qycloud.oatos.server.domain.entity.Enterprise;
import com.qycloud.oatos.server.domain.entity.Login;
import com.qycloud.oatos.server.domain.entity.ProductKey;
import com.qycloud.oatos.server.domain.entity.Role;
import com.qycloud.oatos.server.domain.entity.RolePermission;
import com.qycloud.oatos.server.domain.entity.ShareFolder;
import com.qycloud.oatos.server.domain.entity.ShareFolderHistory;
import com.qycloud.oatos.server.domain.entity.User;
import com.qycloud.oatos.server.domain.model.CachedUser;
import com.qycloud.oatos.server.logic.mail.MailSender;
import com.qycloud.oatos.server.security.Security;
import com.qycloud.oatos.server.security.SecurityUtil;
import com.qycloud.oatos.server.util.CacheClient;
import com.qycloud.oatos.server.util.CacheKey;
import com.qycloud.oatos.server.util.ConfigUtil;
import com.qycloud.oatos.server.util.LogicException;
import com.qycloud.oatos.server.util.MyConstants;
import com.qycloud.oatos.server.util.ReadTxtUtil;

/**
 * 企业服务
 * @author yang
 *
 */
@Service("EnterpriseLogic")
public class EnterpriseLogic {

	@Autowired
	private SequenceLogic sequence;
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private LoginMapper loginMapper;
	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private ProductKeyMapper productKeyMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private ShareFolderMapper shareFolderMapper;
	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Autowired
	private ShareFolderHistoryMapper shareFolderHistoryMapper;

	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private AdminDepartmentMapper adminDepartmentMapper;
	@Autowired
	private AdminFolderMapper adminFolderMapper;

	@Autowired
	private EntModuleMapper entModuleMapper;
	
	@Autowired
	private ShareDiskLogic shareDiskLogic;

	@Autowired
	private UserLogic userLogic;

	@Autowired
	private MailSender mailSender;

	private static Random random = new Random();

	/**
	 * 企业注册
	 * @param entDTO
	 * @return
	 */
	@Transactional
	public String registerEnterprise(EnterpriseDTO entDTO) {
		// 数据传输安全完整性检查
		String pwd = SecurityUtil.checkHashKey(entDTO.getEnterpriseName(), entDTO.getAdminPassword(), entDTO);
		entDTO.setAdminPassword(pwd);

		// 检查企业名是否重复
		checkEntNameExist(entDTO.getEnterpriseName());

		// 插入企业信息
		Enterprise ent = addEnterprise(entDTO);

		// 插入企业服务状态信息
		addEnterpriseModule(ent);

		// 新建企业超级管理员
		addAdministrator(ent, entDTO.getAdminName());

		// 新建一个企业网盘文件夹
		addDefaultShareFolder(ent.getEnterpriseId());

		// 发送企业注册邮件
		sendEnterpriseRegisterationMail(entDTO);

		return CommConstants.OK_MARK;
	}

	/**
	 * 检查企业名是否重复
	 * @param entName
	 */
	private void checkEntNameExist(String entName) {
		Enterprise ent = enterpriseMapper.getEnterpriseByName(entName);
		if (ent != null) {
			throw new LogicException(ErrorType.errorEnterpriseAlreadyExist);
		}
	}

	/**
	 * 新增一个企业模块
	 * @param entModel
	 */
	private void addEnterpriseModule(Enterprise entModel) {
		EntModule entModule = createEntModule(entModel.getEnterpriseId());
		entModuleMapper.addEntModule(entModule);
	}

	/**
	 * 插入企业信息
	 * @param entDTO
	 * @return 
	 */
	private Enterprise addEnterprise(EnterpriseDTO entDTO) {
		Enterprise ent = new Enterprise(entDTO);
		// 新企业，分配新ID
		ent.setEnterpriseId(sequence.getNextId());
		ent.setDiskSize(CommConstants.ENTERPRISE_DISK_SIZE);
		ent.setMaxEmployees(CommConstants.DEFAULT_MAX_EMPLOYEES);
		ent.setPersonalDiskSize(CommConstants.PERSONAL_DISK_SIZE * CommConstants.DEFAULT_MAX_EMPLOYEES);
		ent.setMaxShareLinkDownload(CommConstants.SHARE_LINK_DOWN_SIZE);
		ent.setStatus(EnterpriseStatus.Free);
		ent.setPayStatus(OrderStatus.UNPAY.name());
		// 永久免费
		Calendar cal = Calendar.getInstance();
		cal.set(3000, 1, 1, 0, 0, 0);
		ent.setExpirationTime(cal.getTime());

		// 插入企业信息
		enterpriseMapper.addEnterprise(ent);
		return ent;
		
	}

	/**
	 * 插入企业服务状态
	 * 
	 * @param entId
	 * @return
	 */
	private EntModule createEntModule(long entId) {
		EntModule entModule = new EntModule();
		entModule.setId(sequence.getNextId());
		entModule.setEntId(entId);
		entModule.setColleagueStatus(ModuleStatus.FREE);
		entModule.setPersonalDiskStatus(ModuleStatus.FREE);
		entModule.setEnterpriseDiskStatus(ModuleStatus.FREE);
		entModule.setCreateDate(new Date());

		entModule.setConferenceStatus(ModuleStatus.TRIAL);
		entModule.setOfficeStatus(ModuleStatus.TRIAL);
		entModule.setMailStatus(ModuleStatus.TRIAL);
		entModule.setEnhanceStatus(ModuleStatus.TRIAL);
		entModule.setCsStatus(ModuleStatus.TRIAL);
		return entModule;
	}

	/**
	 * 添加企业超级管理员
	 * @param ent
	 */
	private void addAdministrator(Enterprise entDTO, String adminName) {
		long userId = sequence.getNextId();

		// 添加用户信息
		User user = new User();
		user.setUserId(userId);
		user.setEnterpriseId(entDTO.getEnterpriseId());
		user.setUserType(UserType.Administrator);
		user.setUserName(adminName);
		user.setDepartmentId(null);
		user.setDiskSize(CommConstants.PERSONAL_DISK_SIZE);
		user.setDiskSpace(CommConstants.PERSONAL_DISK_SIZE);
		user.setRegisterMail(entDTO.getMail());
		user.setUserMobile(entDTO.getMobile());
		user.setUserPhone(entDTO.getPhone());
		user.setUserLable("");
		user.setRegistTime(new Date());
		user.setDownload(true);
		user.setAccessExternal(true);
		user.setCustomerService(true);
		user.setJobTitle(CommConstants.Administrator);
		user.setStatus(EmployeeStatus.Active);
		userMapper.addUser(user);

		// 插入用户登录数据
		Login login = new Login();
		login.setLoginId(sequence.getNextId());
		login.setUserId(userId);
		login.setEntName(entDTO.getEnterpriseName());
		login.setAccount(adminName);
		login.setPassword(entDTO.getAdminPassword());
		loginMapper.addLogin(login);

		// 插入用户默认文件夹
		userLogic.addDefaultUserFolder(userId);
	}

	/**
	 * 增加第一个共享文件夹
	 * 
	 * @param enterpriseId
	 */
	private void addDefaultShareFolder(long enterpriseId) {
		// add first folder
		ShareFolder folder = new ShareFolder();
		folder.setFolderId(sequence.getNextId());
		folder.setEnterpriseId(enterpriseId);
		folder.setName("Share");
		folder.setVersion(shareDiskLogic.getNextVersion(enterpriseId));
		// add folder
		shareFolderMapper.addShareFolder(folder);
		// insert history
		ShareFolderHistory historyModel = new ShareFolderHistory(folder);
		historyModel.setOperation(Operation.NewFolder.name());
		historyModel.setUserId(enterpriseId);
		historyModel.setUserName(CommConstants.Administrator);
		historyModel.setUpdateTime(new Date());
		shareFolderHistoryMapper.addShareFolderHistory(historyModel);

		// add first role
		Role role = new Role();
		role.setRoleId(sequence.getNextId());
		role.setEnterpriseId(enterpriseId);
		role.setName(CommConstants.DefaultRoleName);
		roleMapper.addRole(role);

		// set permission
		RolePermission rp = new RolePermission();
		rp.setRoleId(role.getRoleId());
		rp.setFolderId(folder.getFolderId());
		rp.setList(true);
		rp.setRead(true);
		rp.setWrite(true);
		rp.setDownload(true);
		rp.setUpload(true);
		rp.setDelete(true);
		rp.setShare(true);
		rolePermissionMapper.addRolePermisssion(rp);
	}

	/**
	 * 发送企业注册邮件
	 * @param ent
	 */
	private void sendEnterpriseRegisterationMail(EnterpriseDTO entDTO) {
		if (entDTO.getMail() != null && !"".equals(entDTO.getMail().trim())) {
			String locale = entDTO.getLocale();
			boolean isNet = entDTO.isNet();

			String mailMsg = ReadTxtUtil.getEnterpriseRegistrationMailText(locale, isNet);
			mailMsg = mailMsg.replace(MyConstants.enterpriseName, entDTO.getEnterpriseName());
			mailMsg = mailMsg.replace(MyConstants.Administrator, entDTO.getAdminName());
			// 发邮件
			MailDTO mail = new MailDTO();
			if (locale != null && locale.indexOf("en") != -1) {
				mail.setSubject(MyConstants.EnterpriseSubjectEn);
			}
			else {
				mail.setSubject(MyConstants.EnterpriseSubject);
			}
			mail.setRecieve(entDTO.getMail());
			mail.setContent(mailMsg);
			mailSender.sendMail(mail);
		}
	}

	/**
	 * 判断企业名是否已经注册
	 * @param entName
	 * @return
	 */
	public String existEnterprise(String entName) {
		Enterprise ent = enterpriseMapper.getEnterpriseByName(entName);

		if (ent == null) {
			return SystemType.False;
		}
		else {
			return SystemType.True;
		}
	}

	/**
	 * 注册客户，返回客户token
	 * @param customerIP
	 * @return
	 */
	@Transactional
	public String createCustomer(String customerIP) {
		Customer customer = addCustomerAndReturnIt(customerIP);
		String token = Security.CreateToken(Long.toString(customer.getCustomerId()));
		setUserOnlineAndCallFree(customer,token);
		return token;
	}

	/**
	 * @param customer
	 * @param token 
	 */
	private  void setUserOnlineAndCallFree(Customer customer, String token) {
		// add by yang start
		// set user online
		String key = CacheKey.Online + customer.getCustomerId();
		CachedUser cachedUser = new CachedUser(customer.getCustomerId(), UserStatus.Online, CallStatus.Free,
		        UserAgent.Web);
		cachedUser.setToken(token);
		CacheClient.getInstance().set(key, cachedUser);
		// add by yang end
	}

	/**
	 * @param customerIP
	 */
	private Customer addCustomerAndReturnIt(String customerIP) {
		Customer customer = new Customer();
		customer.setCustomerId(sequence.getNextId());
		customer.setCustomerIP(customerIP);
		customerMapper.addCustomer(customer);
		return customer;
		
	}

	/**
	 * 批量新建部门
	 * @param deptDtoList
	 * @return
	 */
	@Transactional
	public String addDepartments(DepartmentsDTO deptDtoList) {

		if (deptDtoList != null && deptDtoList.getDepartmentList().size() > 0) {
			long entpriseId = deptDtoList.getDepartmentList().get(0).getEnterpriseId();

			// 检查企业是否存在
			Enterprise ent = enterpriseMapper.getEnterpriseById(entpriseId);
			if (ent == null) {
				throw new LogicException(ErrorType.errorEnterpriseNotExist);
			}

			for (DepartmentDTO deptDTO : deptDtoList.getDepartmentList()) {
				Department deptModel = new Department(deptDTO);
				deptModel.setDepartmentId(sequence.getNextId());
				deptModel.setOrderValue(departmentMapper.getDepartMaxOrderValue(entpriseId) + 1);
				deptDTO.setDepartmentId(deptModel.getDepartmentId());
				departmentMapper.addDepartment(deptModel);
			}
		}

		return CommConstants.OK_MARK;
	}

	/**
	 * 获取企业的部门列表
	 * @param entpriseId
	 * @return
	 */
	public List<DepartmentDTO> getDepartmentsByEntId(long entpriseId) {
		List<Department> deptModelList = departmentMapper.getDepartmentByEntId(entpriseId);
		Enterprise enterprise = enterpriseMapper.getEnterpriseById(entpriseId);
		if (!enterprise.isOrderEnabled()) {
			for (int i = 0; i < deptModelList.size(); i++) {
				Department department = deptModelList.get(i);
				department.setOrderValue(i);
				departmentMapper.updateDepartment(department);
			}
			enterprise.setOrderEnabled(true);
			enterpriseMapper.updateEnterprise(enterprise);
		}
		List<DepartmentDTO> deptDTOList = new ArrayList<DepartmentDTO>();
		if (deptModelList != null) {
			for (Department dept : deptModelList) {
				deptDTOList.add(dept.toDepartmentDTO());
			}
		}
		return deptDTOList;
	}

	/**
	 * 取企业信息
	 * @param enterpriseId
	 * @return
	 */
	public EnterpriseDTO getEnterpriseById(long enterpriseId) {

		Enterprise ent = enterpriseMapper.getEnterpriseById(enterpriseId);
		// 清除管理员密码，防止密码传递到前端，导致密码外泄。
		ent.setAdminPassword("");
		EnterpriseDTO enterpriseDTO = ent.toEnterpriseDTO();
		enterpriseDTO.setPersonDiskUsed(userMapper.getUserCountByEntId(enterpriseId) * CommConstants.PERSONAL_DISK_SIZE);

		EntModule entModule = entModuleMapper.getEntModuleByEntId(enterpriseId);
		if (entModule == null) {
			entModule = createEntModule(ent.getEnterpriseId());
			entModuleMapper.addEntModule(entModule);
		}
		enterpriseDTO.setEntModule(entModule.asDTO());
		return enterpriseDTO;
	}

	/**
	 * 修改部门信息
	 * @param departmentDTO
	 * @return
	 */
	@Transactional
	public String updateDepartment(DepartmentDTO departmentDTO) {
		departmentMapper.updateDepartment(new Department(departmentDTO));
		return CommConstants.OK_MARK;
	}

	/**
	 * 删除部门
	 * @param departmentDTO
	 * @return
	 */
	@Transactional
	public String deleteDepartment(DepartmentDTO departmentDTO) {
		List<Department> subDepts = departmentMapper.getSubDepartmentByDeptId(departmentDTO.getDepartmentId());
		if (subDepts != null && subDepts.size() > 0) {
			// 存在子部门
			throw new LogicException(ErrorType.errorDeleteDepartUserExist);
		}
		List<User> users = userMapper.getUserByDeptId(departmentDTO.getDepartmentId());
		if (users != null && users.size() > 0) {
			// 存在用户
			throw new LogicException(ErrorType.errorDeleteDepartUserExist);
		}
		departmentMapper.deleteDepartment(new Department(departmentDTO));

		return CommConstants.OK_MARK;
	}

	/**
	 * 取部门信息
	 * @param departmentId
	 * @return
	 */
	public DepartmentDTO getDepartmentById(String departmentId) {
		return departmentMapper.getDepartmentById(Long.parseLong(departmentId)).toDepartmentDTO();
	}

	/**
	 * 修改企业信息
	 * @param entDTO
	 * @return
	 */
	@Transactional
	public String updateEnterprise(EnterpriseDTO entDTO) {
		enterpriseMapper.updateEnterprise(new Enterprise(entDTO));
		return CommConstants.OK_MARK;
	}

	/**
	 * 取企业一个当前可以提供服务的客服
	 * @param enterpriseId
	 * @return
	 */
	public UserInfoDTO getEnterpriseCustomerService(long enterpriseId) {
		
		CachedUser minCachedUser = null;
		User minUser = null;
		List<User> customerServiceList = userMapper.getCustomerServiceByEntId(enterpriseId);
		//取当前服务数最小的客服
		for (int i = 0, length = customerServiceList.size(); i < length; i++) {
			User CS = customerServiceList.get(i);
			String key = CacheKey.Online + CS.getUserId();
			CachedUser currentCacheUser = (CachedUser) CacheClient.getInstance().get(key);
			// 如果用户在线
			if (currentCacheUser != null && currentCacheUser.getOnlineStatus().equals(UserStatus.Online)) {
				// 
				if (minCachedUser == null || minCachedUser.getCustomerCounter() > currentCacheUser.getCustomerCounter() ) {
					minCachedUser = currentCacheUser;
					minUser = CS;
				}
			}
		}

		// 每个客服允许的最大客户数
		int maxCustomerCounter = Integer.parseInt(ConfigUtil.getValue(CommConstants.MaxCustomer));
		
		if (minCachedUser != null && minCachedUser.getCustomerCounter() < maxCustomerCounter) {
			int counter = minCachedUser.getCustomerCounter();
			minCachedUser.setCustomerCounter(counter + 1);
			// update memory cached
			upDateMemoryCached(minCachedUser);
			return minUser.toUserInfoDTO();
		}
		return null;
	}


	/**
	 * @param minCachedUser
	 */
	private void upDateMemoryCached(CachedUser minCachedUser) {
		String k = CacheKey.Online + minCachedUser.getUserId();
		CacheClient.getInstance().set(k, minCachedUser);
	}

	/**
	 * 生成产品key
	 * @param sales
	 * @param num
	 * @return
	 */
	@Transactional
	public String generatorProductKey(String sales, int num) {

		// 生成产品码
		for (int i = 0; i < num; i++) {

			StringBuffer key = new StringBuffer(30);
			key.append(Security.randomCharString(5));
			key.append("-");

			key.append(Security.randomCharString(5));
			key.append("-");

			key.append(Security.randomCharString(5));
			key.append("-");

			key.append(Security.randomCharString(5));
			key.append("-");

			key.append(Security.randomCharString(5));

			String productKey = key.toString().toUpperCase();

			ProductKey prodKey = new ProductKey(productKey, sales);

			productKeyMapper.addKey(prodKey);
		}

		return CommConstants.OK_MARK;
	}

	/**
	 * 取一个免费的产品key
	 * @return
	 */
	public String getFreeProductKey() {
		String key = null;
		List<ProductKey> keys = productKeyMapper.getFreeKeys();
		if (keys != null && keys.size() > 0) {
			int i = random.nextInt(keys.size());
			key = keys.get(i).getKey();
		}
		return key;
	}

	/**
	 * 修改注册企业的产品key
	 * @param enterpriseDTO
	 * @return
	 */
	@Transactional
	public String registerProductKey(EnterpriseDTO enterpriseDTO) {
		String result = CommConstants.OK_MARK;
		ProductKey key = productKeyMapper.getKey(enterpriseDTO.getProductKey());
		if (key == null || key.isUsed()) {
			result = ErrorType.errorInvalidProductKey.name();
		}
		else if (key.isFree()) {
			result = ErrorType.errorProductKeyFree.name();
		}
		else {
			enterpriseMapper.updateProductKey(enterpriseDTO.getEnterpriseId(), enterpriseDTO.getProductKey());
			key.setUsed(true);
			productKeyMapper.updateKey(key);
		}
		return result;
	}

	/**
	 * 判断企业产品序列号激活状态
	 * @param entId
	 * @return
	 */
	public String checkProductKey(long entId) {
		String result = ErrorType.errorInvalidProductKey.name();
		Enterprise ent = enterpriseMapper.getEnterpriseById(entId);
		if (ent.getProductKey() != null) {
			ProductKey key = productKeyMapper.getKey(ent.getProductKey());
			if (key != null) {
				if (key.isFree()) {
					result = ErrorType.errorProductKeyFree.name();
				}
				else {
					result = CommConstants.OK_MARK;
				}
			}
		}
		return result;
	}

	/**
	 * 取上传下载速度限制
	 * @param userId
	 * @return
	 */
	public LimitDTO getUploadLimit(long userId) {
		LimitDTO limitDTO = new LimitDTO();
		try {
			User user = userMapper.getUser(userId);
			Enterprise enterprise = enterpriseMapper.getEnterpriseById(user.getEnterpriseId());
			if (EnterpriseStatus.Pay.equals(enterprise.getStatus())) {
				// 200 kb
				limitDTO.setUploadSpeed(200 * 1024L);
				// 200 kb
				limitDTO.setDownloadSpeed(200 * 1024L);
				// 10 g
				limitDTO.setUploadSize(10 * 1024 * 1024 * 1024L);
			}
		}
		catch (Exception ex) {
		}
		return limitDTO;
	}

	/**
	 * 新建部门
	 * @param departmentDTO
	 * @return
	 */
	@Transactional
	public String addDepartment(DepartmentDTO departmentDTO) {
		String result = "";
		Department dep = new Department(departmentDTO);
		if (departmentMapper.getSameDepartment(dep) == null) {
			long depId = sequence.getNextId();
			dep.setDepartmentId(depId);
			dep.setOrderValue(departmentMapper.getDepartMaxOrderValue(departmentDTO.getEnterpriseId()) + 1);
			departmentMapper.addDepartment(dep);
			departmentDTO.setDepartmentId(depId);
			result = PojoMapper.toJson(departmentDTO);
			if (departmentDTO.getCreateUserId() != null) {
	            Admin a = adminMapper.getAdminByUserId(departmentDTO.getCreateUserId());
	            if (a != null) {
	            	AdminDepartment d = new AdminDepartment();
	            	d.setUserId(departmentDTO.getCreateUserId());
	            	d.setDepartmentId(depId);
	                adminDepartmentMapper.addAdminDepartment(d);
                }
            }
		}
		else {
			result = ErrorType.errorSameName.name();
		}
		return result;
	}

	/**
	 * 获取企业管理员登录后初始信息（企业信息，管理员信息）
	 * @param token
	 * @return
	 */
    public String getAdminData(AdminToken token) { 
		AdminDataDTO dataDTO = new AdminDataDTO();
		EnterpriseDTO e = getEnterpriseById(token.getEnterpriseId());
		dataDTO.setEnterpriseDTO(e);
		if (token.getUserId() != null) {
			User u = userMapper.getUser(token.getUserId());
			dataDTO.setUserInfoDTO(u.toUserInfoDTO());
			Admin admin = adminMapper.getAdminByUserId(token.getUserId());
			if (admin != null) {
	        	if (admin.isCreateDept() || admin.isCreateMember()) {
	        		List<AdminDepartment> ds = adminDepartmentMapper.getAdminDepartmentsByUserId(token.getUserId());
	        		admin.setDepartments(ds);
	        	}
	        	if (admin.isFolderPermission()) {
		        	List<AdminFolder> fs = adminFolderMapper.getAdminFoldersByUserId(token.getUserId());
		        	admin.setFolders(fs);
                }
	        	dataDTO.setAdminDTO(admin.toAdminDTO());
	        }
        }
	    return PojoMapper.toJson(dataDTO);
    }
    
    /**
     * 更新部门的排序
     * @param departments
     * @return
     */
	@Transactional
	public String updateDepartmentLevels(DepartmentsDTO departments) {
		for (DepartmentDTO depart : departments.getDepartmentList()) {
			departmentMapper.updateDepartment(new Department(depart));
		}
		return CommConstants.OK_MARK;
	}
}
