package com.qycloud.oatos.server.domain.logic;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conlect.oatos.dto.client.ClientToken;
import com.conlect.oatos.dto.client.EnterpriseLoginDTO;
import com.conlect.oatos.dto.client.PasswordChangeDTO;
import com.conlect.oatos.dto.client.UserStatusDTO;
import com.conlect.oatos.dto.status.CallStatus;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.EmployeeStatus;
import com.conlect.oatos.dto.status.EnterpriseStatus;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.OrderStatus;
import com.conlect.oatos.dto.status.UserAgent;
import com.conlect.oatos.dto.status.UserStatus;
import com.conlect.oatos.dto.status.UserType;
import com.qycloud.oatos.server.dao.EnterpriseMapper;
import com.qycloud.oatos.server.dao.LoginLogMapper;
import com.qycloud.oatos.server.dao.LoginMapper;
import com.qycloud.oatos.server.dao.UserMapper;
import com.qycloud.oatos.server.domain.entity.Enterprise;
import com.qycloud.oatos.server.domain.entity.Login;
import com.qycloud.oatos.server.domain.entity.LoginLog;
import com.qycloud.oatos.server.domain.entity.User;
import com.qycloud.oatos.server.domain.model.CachedUser;
import com.qycloud.oatos.server.security.Security;
import com.qycloud.oatos.server.security.SecurityUtil;
import com.qycloud.oatos.server.util.BllLogger;
import com.qycloud.oatos.server.util.CacheClient;
import com.qycloud.oatos.server.util.CacheKey;
import com.qycloud.oatos.server.util.LogicException;

/**
 * 登录服务
 * @author yang
 *
 */
@Service("LoginLogic")
public class LoginLogic {

	@Autowired
	private SequenceLogic sequence;
	@Autowired
	private LoginMapper loginMapper;
	@Autowired
	private LoginLogMapper loginLogMapper;
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private EnterpriseMapper enterpriseMapper;

	/**
	 * 企业管理员登录
	 * 
	 * @param loginDTO
	 * @return
	 */
	public String adminLogin(EnterpriseLoginDTO loginDTO) {
		// 数据传输安全完整性检查
		String password = SecurityUtil.checkHashKey(loginDTO.getAccount(), loginDTO.getPassword(), loginDTO);

		String entName = loginDTO.getEnterpriseName();
		String userName = loginDTO.getAccount();

		Enterprise ent = enterpriseMapper.getEnterpriseByName(entName);
		if (ent == null) {
			throw new LogicException(ErrorType.errorWrongEnterpriseName);
		}
		// 检查企业服务状态
		checkEntServiceStatus(ent);

		if (CommConstants.Administrator.equalsIgnoreCase(userName)) {
			userName = CommConstants.Administrator;
		}

		Login login = loginMapper.getLoginByAccount(entName, userName);
		String pwd = null;
		User user = null;
		if (login != null) {
			// 存在管理员用户
			user = userMapper.getUser(login.getUserId());
			// 检查用户状态
			checkUserStatus(user);
			if (user.getUserType().equals(UserType.Administrator)) {
				// 超级管理员
				pwd = ent.getAdminPassword();
			} else if (user.getUserType().equals(UserType.SecondAdministrator)) {
				// 二级管理员
				pwd = login.getPassword();
			} else {
				// 帐号错误
				throw new LogicException(ErrorType.errorWrongAccount);
			}
		} else if (CommConstants.Administrator.equals(userName)) {
			// 早期没有超级管理员用户，使用企业管理员密码
			pwd = ent.getAdminPassword();
		} else {
			// 帐号错误
			throw new LogicException(ErrorType.errorWrongAccount);
		}

		//验证密码
		checkLoginPassword(password, pwd);

		// 生成客户端token
		String token = Security.CreateToken(String.valueOf(ent.getEnterpriseId()));
		if (user != null) {
			token = String.valueOf(user.getUserId()) + ClientToken.SEPARATOR
					+ token;
		}
		return token;
	}
	
	/**
	 * 检查企业服务状态
	 * @param ent
	 */
	private void checkEntServiceStatus(Enterprise ent) {
		if (EnterpriseStatus.Suspended.compareTo(ent.getStatus()) > 0) {
			// 企业服务暂停
			throw new LogicException(ErrorType.errorAuditFail);
		}
		if (ent.getExpirationTime() != null
				&& ent.getExpirationTime().before(new Date())) {
			// 服务到期
			throw new LogicException(ErrorType.errorAccountExpired);
		}
	}

	/**
	 * 检查用户状态
	 * @param user
	 */
	private void checkUserStatus(User user) {
		if (!user.getUserType().equals(UserType.Administrator)) {
			if (user.isLocked()) {
				// 用户被锁定
				throw new LogicException(ErrorType.errorUserLocked);
			}
			if (OrderStatus.isPayExpired(user.getPayStatus())) {
				// 购买的服务过期
				throw new LogicException(ErrorType.errorPayExpired);
			}
		}
	}

	/**
	 * 验证登录密码
	 * @param inputPwd
	 * @param dbPwd
	 */
	private void checkLoginPassword(String inputPwd, String dbPwd) {
		if (!inputPwd.equals(dbPwd)) {
			throw new LogicException(ErrorType.errorWrongPWD);
		}
	}

	/**
	 * 企业用户登录
	 * 
	 * @param loginDTO
	 * @return
	 */
	@Transactional
	public String userLogin(EnterpriseLoginDTO loginDTO) {
		// 数据传输安全完整性检查
		String password = SecurityUtil.checkHashKey(loginDTO.getAccount(), loginDTO.getPassword(), loginDTO);

		String userName = loginDTO.getAccount();
		String entName = loginDTO.getEnterpriseName();
		
		//把Administrator无论大小写都改成Administrator 防止用户大小写不注意
		if (CommConstants.Administrator.equalsIgnoreCase(userName)) {
			userName = CommConstants.Administrator;
		}

		Login loginModel = loginMapper.getLoginByAccount(entName, userName);
		
		//如果没有这个账号
		if (loginModel == null) {
			throw new LogicException(ErrorType.errorWrongAccount);
		}
		
		long userId = loginModel.getUserId();

		// 获取用户信息
		User user = userMapper.getUser(userId);
		// 检查用户状态
		checkUserStatus(user);

		// 获取企业信息
		Enterprise ent = enterpriseMapper.getEnterpriseById(user.getEnterpriseId());
		// 检查企业服务状态
		checkEntServiceStatus(ent);

		String pwd = loginModel.getPassword();
		//如果是管理员则密码在企业表中查找密码
		if (user.getUserType().equals(UserType.Administrator)) {
			pwd = ent.getAdminPassword();
		}
		//验证密码
		checkLoginPassword(password, pwd);

		// 修改ios设备标识
		updateDeviceToken(userId, loginDTO);

		// 生成客户端token
		String token = Security.CreateToken(String.valueOf(userId));

		//在缓存中设置用户状态为在线
		changeUserOnline(userId, token, loginDTO.getAgent());

		//记录登录日志
		insertLoginLog(ent.getEnterpriseId(), userId, loginDTO);

		return token;
	}

	/**
	 * 设置用户状态为在线
	 * @param userId
	 * @param token
	 * @param agent
	 */
	private void changeUserOnline(long userId, String token, String agent) {
		String key = CacheKey.Online + userId;
		CachedUser cachedUser = new CachedUser(userId, UserStatus.Online, CallStatus.Free, agent);
		cachedUser.setToken(token);
		CacheClient.getInstance().set(key, cachedUser);
	}

	/**
	 * 记录登录日志
	 * @param entId
	 * @param userId
	 * @param loginDTO
	 */
	private void insertLoginLog(long entId, long userId, EnterpriseLoginDTO loginDTO) {
		try {
			LoginLog loginLog = new LoginLog(sequence.getNextId(),
					entId, userId, loginDTO.getAccount(), loginDTO.getEnterpriseName(),
					loginDTO.getIp(), loginDTO.getAgent());
			loginLogMapper.insertLoginLog(loginLog);
		} catch (Exception ex) {
			BllLogger.getLogger().error(userId, ex);
		}
	}

	/**
	 * 
	 * @param userId
	 * @param deviceToken
	 */
	private void updateDeviceToken(long userId, EnterpriseLoginDTO loginDTO) {
		if(UserAgent.IOS.equals(loginDTO.getAgent())){
			// 修改ios设备标识
			userMapper.addDeviceTokenByUserId(userId, loginDTO.getDeviceToken());
		}
	}

	/**
	 * 修改企业管理员密码
	 * 
	 * @param pwdDTO
	 * @return
	 */
	@Transactional
	public String changeAdminPassword(PasswordChangeDTO pwdDTO) {
		String pwd = SecurityUtil.checkHashKey(String.valueOf(pwdDTO.getId()),
				pwdDTO.getNewPassword(), pwdDTO);
		String oldPwd = Security.CreatePassword(pwdDTO.getOldPasswrod());

		Enterprise entModel = enterpriseMapper
				.getEnterpriseById(pwdDTO.getId());

		if (!entModel.getAdminPassword().equals(oldPwd)) {
			return ErrorType.errorWrongPWD.name();
		}

		enterpriseMapper.changeAdminPassword(pwdDTO.getId(), pwd);
		return CommConstants.OK_MARK;
	}

	/**
	 * 修改密码
	 * 
	 * @param login
	 */
	public void changePassword(PasswordChangeDTO pwdDTO) {
		String pwd = SecurityUtil.checkHashKey(String.valueOf(pwdDTO.getId()),
				pwdDTO.getNewPassword(), pwdDTO);
		String oldPwd = Security.CreatePassword(pwdDTO.getOldPasswrod());

		Login loginModel = loginMapper.getLoginByUserId(pwdDTO.getId());
		if (loginModel == null) {
			throw new LogicException(ErrorType.error500);
		}

		if (loginModel.getPassword().equals(oldPwd)) {
			loginModel.setPassword(pwd);
			loginMapper.updatePassword(loginModel);

			userMapper.updateUserStatus(pwdDTO.getId(), EmployeeStatus.Active);
		} else {
			// 旧密码错误时，抛出异常
			throw new LogicException(ErrorType.errorWrongOldPWD);
		}
	}

	/**
	 * 改变用户在线状态
	 * 
	 * @param online
	 * @return
	 */
	public void changeUserStatus(UserStatusDTO statusDTO) {
		String key = CacheKey.Online + statusDTO.getUserId();

		CachedUser cachedUser = null;
		Object obj = CacheClient.getInstance().get(key); // 用户在线状态

		if (obj != null) {
			cachedUser = (CachedUser) obj;
			cachedUser.setOnlineStatus(statusDTO.getUserStatus());
		} else {
			cachedUser = new CachedUser(statusDTO.getUserId(),
					statusDTO.getUserStatus(), CallStatus.Free,
					statusDTO.getAgent());
		}
		// 对象写入缓存
		CacheClient.getInstance().set(key, cachedUser);
	}

	/**
	 * 登出
	 * 
	 * @param userId
	 */
	public String logOut(long userId, String token) {
		String result = CommConstants.OK_MARK;
		BllLogger.getLogger().info("log out, header token:" + token);
		String key = CacheKey.Online + String.valueOf(userId);
		Object cacheData = CacheClient.getInstance().get(key);
		if (cacheData != null) {
			CacheClient.getInstance().delete(key);
			result = CommConstants.OK_MARK;
		}
		return result;
	}

	/**
	 * 检查token
	 * 
	 * @param token
	 */
	public String checkToken(String token) {
		boolean logout = true;
		BllLogger.getLogger().info("check param token:" + token);
		ClientToken clientToken = new ClientToken(token);

		// 判断用户是否在线
		String key = CacheKey.Online + String.valueOf(clientToken.getUserId());
		Object cacheData = CacheClient.getInstance().get(key);

		if (cacheData != null) {
			CachedUser cachedUser = (CachedUser) cacheData;
			BllLogger.getLogger().info(
					"check cached token:" + cachedUser.getToken());
			if (!token.equals(cachedUser.getToken())) {
				// 用户重新登录了，则不用logout
				logout = false;
			}
		}
		return String.valueOf(logout);
	}

	/**
	 * 客服的服务客户数减1
	 * @param userId
	 */
	public void reductionCustomerCounter(long userId) {
		String key = CacheKey.Online + userId;

		Object obj = CacheClient.getInstance().get(key);

		if (obj != null) {
			CachedUser cachedUser = (CachedUser) obj;
			int counter = cachedUser.getCustomerCounter();
			if (counter > 0) {
				cachedUser.setCustomerCounter(counter - 1);
				CacheClient.getInstance().set(key, cachedUser);
			}
		} else {
			throw new LogicException(ErrorType.errorUserIsOffline);
		}

	}

	/**
	 * 用户重新登陆，返回新的token
	 * @param token
	 * @param agent
	 * @return
	 */
	public String reLogin(String token, String agent) {
		ClientToken clientToken = new ClientToken(token);
		// 生成新的token
		String newToken = Security.CreateToken(String.valueOf(clientToken.getUserId()));

		// 设置用户在线
		changeUserOnline(clientToken.getUserId(), newToken, agent);
		
		return newToken;
	}

	/**
	 * 检查用户密码是否正确
	 * @param pwdDTO
	 * @return
	 */
	public String checkUserPassword(PasswordChangeDTO pwdDTO) {
		String pwd = SecurityUtil.checkHashKey(String.valueOf(pwdDTO.getId()),
				pwdDTO.getOldPasswrod(), pwdDTO);
		String result = ErrorType.errorWrongPWD.name();
		User user = userMapper.getUser(pwdDTO.getId());
		if (UserType.Administrator == user.getUserType()) {
			// 超级管理员，验证管理员密码
			Enterprise ent = enterpriseMapper.getEnterpriseById(user
					.getEnterpriseId());
			if (pwd.equals(ent.getAdminPassword())) {
				result = CommConstants.OK_MARK;
			}
		} else {
			// 普通用户，验证用户登录密码
			Login loginModel = loginMapper.getLoginByUserId(pwdDTO.getId());
			if (pwd.equals(loginModel.getPassword())) {
				result = CommConstants.OK_MARK;
			}
		}

		return result;
	}

}
