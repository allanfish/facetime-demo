package com.qycloud.oatos.server.service;

import java.net.URLDecoder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.EnterpriseLoginDTO;
import com.conlect.oatos.dto.client.PasswordChangeDTO;
import com.conlect.oatos.dto.client.ReLoginDTO;
import com.conlect.oatos.dto.client.UserStatusDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.logic.LoginLogic;
import com.qycloud.oatos.server.util.BllLogger;
import com.qycloud.oatos.server.util.LogicException;

/** 
 * 实现登录服务
 * 
 * @author huhao
 * 
 */
@Controller("LoginService")
public class LoginService {

	private final static Logger logger = BllLogger.getLogger();

	@Autowired
	private LoginLogic loginLogic;
	
	/**
	 * 企业管理员登录
	 * @see RESTurl#adminLogin
	 */
	@RequestMapping(value = RESTurl.adminLogin, method = RequestMethod.POST)
	public @ResponseBody
	String adminLogin(@RequestBody String postData) {
		String reBody = "";
		try {
			EnterpriseLoginDTO loginDTO = PojoMapper.fromJsonAsObject(postData,EnterpriseLoginDTO.class);
			reBody = loginLogic.adminLogin(loginDTO);
		} catch (LogicException ex) {
			reBody = ex.getError().name();
		} catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 企业用户登录
	 * @see RESTurl#enterpriseUserLogin
	 */
	@RequestMapping(value = RESTurl.enterpriseUserLogin, method = RequestMethod.POST)
	public @ResponseBody
	String userLogin(@RequestBody String postData) {
		String reBody = "";
		try {
			EnterpriseLoginDTO loginDTO = PojoMapper.fromJsonAsObject(postData,
					EnterpriseLoginDTO.class);
			reBody = loginLogic.userLogin(loginDTO);
		} catch (LogicException ex) {
			reBody = ex.getError().name();
		} catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 修改企业超级管理员登录密码
	 * @see RESTurl#changeAdminPassword
	 */
	@RequestMapping(value = RESTurl.changeAdminPassword, method = RequestMethod.POST)
	public @ResponseBody
	String changeAdminPassword(@RequestBody String postData) {
		String reBody = CommConstants.ERROR_MARK;
		try {
			PasswordChangeDTO pwdDTO = PojoMapper.fromJsonAsObject(postData,
					PasswordChangeDTO.class);

			reBody = loginLogic.changeAdminPassword(pwdDTO);
		} catch (LogicException ex) {
			reBody = ex.getError().name();
		} catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 用户退出登录
	 * @see RESTurl#logout
	 */
	@RequestMapping(value = RESTurl.logout, method = RequestMethod.POST)
	public @ResponseBody
	String logout(@RequestBody String userId,
			@RequestHeader(RESTurl.UserTokenkey) String token) {
		String reBody = "";
		try {
			String t = URLDecoder.decode(token, CommConstants.CHARSET_UTF_8);
			reBody = loginLogic.logOut(Long.valueOf(userId), t);
		} catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(userId, ex);
		}
		return reBody;
	}

	/**
	 * 修改用户在线状态
	 * @see RESTurl#changeUserStatus
	 */
	@RequestMapping(value = RESTurl.changeUserStatus, method = RequestMethod.POST)
	public @ResponseBody
	String changeUserStatus(@RequestBody String postData) {
		String reBody = "";
		try {
			UserStatusDTO statusDTO = PojoMapper.fromJsonAsObject(postData,
					UserStatusDTO.class);
			loginLogic.changeUserStatus(statusDTO);
			reBody = CommConstants.OK_MARK;
		} catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 修改用户登录密码
	 * @see RESTurl#changePassword
	 */
	@RequestMapping(value = RESTurl.changePassword, method = RequestMethod.POST)
	public @ResponseBody
	String changePassword(@RequestBody String postData) {
		String reBody = "";
		try {
			PasswordChangeDTO pwdDTO = PojoMapper.fromJsonAsObject(postData,
					PasswordChangeDTO.class);
			loginLogic.changePassword(pwdDTO);
			reBody = CommConstants.OK_MARK;
		} catch (LogicException ex) {
			reBody = ex.getError().name();
		} catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 检查token是否有效
	 * @see RESTurl#checkToken
	 */
	@RequestMapping(value = RESTurl.checkToken, method = RequestMethod.POST)
	public @ResponseBody
	String checkToken(@RequestBody String postData) {
		String reBody = "";
		try {
			reBody = loginLogic.checkToken(postData);
		} catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 客服的客户服务计数减1
	 * @see RESTurl#reductionCustomerCounter
	 */
	@RequestMapping(value = RESTurl.reductionCustomerCounter, method = RequestMethod.POST)
	public @ResponseBody
	String reductionCustomerCounter(@RequestBody String userId) {
		String reBody = "";
		try {
			loginLogic.reductionCustomerCounter(Long.parseLong(userId));
			reBody = CommConstants.OK_MARK;
		} catch (LogicException ex) {
			reBody = ex.getError().name();
		} catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(userId, ex);
		}
		return reBody;
	}

	/**
	 * 重新登录
	 * @see RESTurl#reLogin
	 */
	@RequestMapping(value = RESTurl.reLogin, method = RequestMethod.POST)
	public @ResponseBody
	String reLogin(@RequestBody String postData) {
		String reBody = "";
		try {
			ReLoginDTO reLoginDTO = PojoMapper.fromJsonAsObject(postData,
					ReLoginDTO.class);
			reBody = loginLogic.reLogin(reLoginDTO.getToken(),
					reLoginDTO.getAgent());
		} catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(ex);
		}
		return reBody;
	}

	/**
	 * 验证用户登录密码
	 * @see RESTurl#checkUserPassword
	 */
	@RequestMapping(value = RESTurl.checkUserPassword, method = RequestMethod.POST)
	public @ResponseBody
	String checkUserPassword(@RequestBody String postData) {
		String reBody = "";
		try {
			PasswordChangeDTO pwdDTO = PojoMapper.fromJsonAsObject(postData,
					PasswordChangeDTO.class);
			reBody = loginLogic.checkUserPassword(pwdDTO);
		} catch (LogicException ex) {
			reBody = ex.getError().name();
		} catch (Exception ex) {
			logger.error(postData, ex);
			reBody = ErrorType.error500.name();
		}
		return reBody;
	}

}
