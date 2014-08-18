package com.qycloud.oatos.server.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.DepartmentAndUserDTO;
import com.conlect.oatos.dto.client.EnterpriseUserDTO;
import com.conlect.oatos.dto.client.SimpleUserInfoDTO;
import com.conlect.oatos.dto.client.SimpleUserInfosDTO;
import com.conlect.oatos.dto.client.UserIconDTO;
import com.conlect.oatos.dto.client.UserInfoDTO;
import com.conlect.oatos.dto.client.UserInfosDTO;
import com.conlect.oatos.dto.client.UserStatusesDTO;
import com.conlect.oatos.dto.client.user.UserImportSaveDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.logic.UserLogic;
import com.qycloud.oatos.server.util.BllLogger;
import com.qycloud.oatos.server.util.LogicException;

/**
 * 用户服务
 * @author yang
 *
 */
@Controller("UserService")
public class UserService {

	private final static Logger logger = BllLogger.getLogger();
	
	@Autowired
	private UserLogic userLogic;

	/**
	 * 添加企业用户
	 * 
	 * @param entUserDTOJson
	 *            企业用信息数据传输对象
	 * @return user id
	 */
	@RequestMapping(value = RESTurl.addEnterpriseUser, method = RequestMethod.POST)
	public @ResponseBody
	String addEnterpriseUser(@RequestBody String entUserDTOJson) {
		String reBody = "";
		try {
			EnterpriseUserDTO userDTO = PojoMapper.fromJsonAsObject(entUserDTOJson, EnterpriseUserDTO.class);
			reBody = userLogic.addUser(userDTO);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(entUserDTOJson, ex);
		}
		return reBody;
	}

	/**
	 * 用户导入
	 * 
	 * @param importDTOJson
	 *            企业用信息数据传输对象
	 * @return OK
	 */
	@RequestMapping(value = RESTurl.addEnterpriseUserList, method = RequestMethod.POST)
	public @ResponseBody
	String importUser(@RequestBody String importDTOJson) {
		String reBody = "";
		try {
			UserImportSaveDTO userImportDTO = PojoMapper.fromJsonAsObject(importDTOJson, UserImportSaveDTO.class);
			reBody = userLogic.importUser(userImportDTO);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(importDTOJson, ex);
		}
		return reBody;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param userId
	 */
	@RequestMapping(value = RESTurl.getUserProfile, method = RequestMethod.POST)
	public @ResponseBody
	String getUserInfo(@RequestBody String userId) {
		String reBody = "";
		try {
			UserInfoDTO userDto = userLogic.getUserInfo(Long.parseLong(userId));
			reBody = PojoMapper.toJson(userDto);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(userId, ex);
		}
		return reBody;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param userDTOJson
	 */
	@RequestMapping(value = RESTurl.updateUserProfile, method = RequestMethod.POST)
	public @ResponseBody
	String updateUserInfo(@RequestBody String userDTOJson) {
		String reBody = "";
		try {
			UserInfoDTO user = PojoMapper.fromJsonAsObject(userDTOJson, UserInfoDTO.class);
			userLogic.updateUserInfo(user);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex)
		{
			reBody = ErrorType.error500.name();
			logger.error(userDTOJson, ex);
		}
		return reBody;
	}

	/**
	 * 批量修改用户信息
	 * 
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateUsers, method = RequestMethod.POST)
	public @ResponseBody
	String updateUsers(@RequestBody String usersDTOJson) {
		String reBody = "";
		try {
			UserInfosDTO userDTOs = PojoMapper.fromJsonAsObject(usersDTOJson, UserInfosDTO.class);
			userLogic.updateUsers(userDTOs.getUserInfoDTOList());
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(usersDTOJson, ex);
		}
		return reBody;
	}

	/**
	 * 修改员工信息和密码
	 * 
	 * @param userDTOJson
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateEnterpriseUserAndPassword, method = RequestMethod.POST)
	public @ResponseBody
	String updateUserAndPassword(@RequestBody String userDTOJson) {
		String reBody = CommConstants.ERROR_MARK;
		try {
			UserInfoDTO userDTO = PojoMapper.fromJsonAsObject(userDTOJson, UserInfoDTO.class);
			reBody = userLogic.updateUserAndPassword(userDTO);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(userDTOJson, ex);
		}
		return reBody;

	}


	/**
	 * 取某用户的在线状态
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getUserStatus, method = RequestMethod.POST)
	public @ResponseBody
	String getUserStatus(@RequestBody String userId) {
		String reBody = "";
		try {
			reBody = userLogic.getUserStatus(Long.parseLong(userId));
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(reBody, ex);
		}
		return reBody;
	}
	
	/**
	 * 取在线同事(基本信息)
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getOnlineBuddyAndColleague, method = RequestMethod.POST)
	public @ResponseBody
	String getOnlineFriends(@RequestBody String userId) {
		String reBody = "";
		try {
			List<SimpleUserInfoDTO> userList = userLogic.getOnlineFriends(Long.parseLong(userId));
			SimpleUserInfosDTO userInfosDTO = new SimpleUserInfosDTO();
			userInfosDTO.setUserList(userList);
			reBody = PojoMapper.toJson(userInfosDTO);
		}
		catch (Exception ex)
		{
			reBody = ErrorType.error500.name();
			logger.error(userId, ex);
		}
		return reBody;
	}

	/**
	 * 统计企业在线用户数
	 * @return
	 */
	@RequestMapping(value = RESTurl.statisticalUser, method = RequestMethod.GET)
	public @ResponseBody String statisticalUser() {
		String reBody = "";
		try {
			reBody = userLogic.statisticalUser();
		}
		catch (Exception ex) {
			reBody = ex.getLocalizedMessage();
			logger.error(ex);
		}
		return reBody;
	}

	/**
	 * 取企业用户包含角色
	 * 
	 * @param entId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getEnterpriseUserListWithRoles, method = RequestMethod.POST)
	public @ResponseBody
	String getUsersWithRolesByEntId(@RequestBody String entId) {
		String reBody = "";
		try {
			List<UserInfoDTO> userDTOs = userLogic.getUsersWithRolesByEntId(Long.parseLong(entId));
			UserInfosDTO userInfoList = new UserInfosDTO();
			userInfoList.setUserInfoDTOList(userDTOs);

			reBody = PojoMapper.toJson(userInfoList);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(entId, ex);
		}
		return reBody;
	}

	/**
	 * 删除企业员工，同时删除一个或者多个
	 * 
	 * @param employeeList
	 *            员工的数据传输对象 List
	 * @return 成功标示 OK
	 */
	@RequestMapping(value = RESTurl.deleteEnterpriseUsers, method = RequestMethod.POST)
	public @ResponseBody
	String deleteUsers(@RequestBody String usersDTOJson) {
		String reBody = CommConstants.ERROR_MARK;
		try {
			UserInfosDTO emps = PojoMapper.fromJsonAsObject(usersDTOJson, UserInfosDTO.class);
			reBody = userLogic.deleteEnterpriseUsers(emps);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(usersDTOJson, ex);
		}
		return reBody;
	}

	/**
	 * 获取所有同事的在线状态
	 * 
	 * @param entId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getEmployeesStatus, method = RequestMethod.POST)
	public @ResponseBody
	String getEmployeesStatus(@RequestBody String entId) {
		String reBody = "";
		try {
			UserStatusesDTO statusesDTO = userLogic.getEmployeesStatus(Long.parseLong(entId));
			reBody = PojoMapper.toJson(statusesDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error("", ex);
		}
		return reBody;
	}

	/**
	 * 取企业用户，不包括状态及角色
	 * @return
	 */
	@RequestMapping(value = RESTurl.getUsersByEntId, method = RequestMethod.POST)
	public @ResponseBody String getUsersByEntId(@RequestBody String entId) {
		String reBody = ErrorType.error500.name();
		try {
			List<UserInfoDTO> userDTOs = userLogic.getUsersByEntId(Long.parseLong(entId));
			UserInfosDTO userInfosDTO = new UserInfosDTO();
			userInfosDTO.setUserInfoDTOList(userDTOs);
			reBody = PojoMapper.toJson(userInfosDTO);
		}
		catch (Exception e) {
			reBody = ErrorType.error500.name();
			logger.error(reBody, e);
		}
		return reBody;
	}

	/**
	 * 取企业部门和成员，不包括角色和状态
	 * @param entId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getDepartmentAndUserByEntId, method = RequestMethod.POST)
	public @ResponseBody
	String getDepartmentAndUserByEntId(@RequestBody String entId) {
		String reBody = "";
		try {
			DepartmentAndUserDTO depAndUserDTO = userLogic.getDepartmentAndUserByEntId(Long.parseLong(entId));
			reBody = PojoMapper.toJson(depAndUserDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(entId, ex);
		}
		return reBody;
	}

	/**
	 * 取企业部门和成员，包含角色，不包含状态
	 * 
	 * @param entId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getDepartmentAndUserWithRolesByEntId, method = RequestMethod.POST)
	public @ResponseBody
	String getDepartmentAndUserWithRolesByEntId(@RequestBody String entId) {
		String reBody = "";
		try {
			DepartmentAndUserDTO depAndUserDTO = userLogic.getDepartmentAndUserWithRolesByEntId(Long.parseLong(entId));
			reBody = PojoMapper.toJson(depAndUserDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(entId, ex);
		}

		return reBody;
	}

	/**
	 * 用户取企业部门和成员及在线状态
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getDepartmentAndUserWithStatusByUserId, method = RequestMethod.POST)
	public @ResponseBody
	String getDepartmentAndUserWithStatusByUserId(@RequestBody String userId) {
		String reBody = "";
		try {
			DepartmentAndUserDTO depAndUserDTO = userLogic.getDepartmentAndUserWithStatusByUserId(Long.parseLong(userId));
			reBody = PojoMapper.toJson(depAndUserDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(userId, ex);
		}
		return reBody;
	}

	/**
	 * 用户取企业部门和成员及在线状态
	 * 
	 * @param entId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getDepartmentAndUserWithStatusByEntId, method = RequestMethod.POST)
	public @ResponseBody
	String getDepartmentAndUserWithStatusByEntId(@RequestBody String entId) {
		String reBody = "";
		try {
			DepartmentAndUserDTO depAndUserDTO = userLogic.getDepartmentAndUserWithStatusByEntId(Long.parseLong(entId));
			reBody = PojoMapper.toJson(depAndUserDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(entId, ex);
		}
		return reBody;
	}

	/**
	 * 取在线用户和离线用户ID
	 * 
	 * @return
	 */
	@RequestMapping(value = RESTurl.getAllUserIds, method = RequestMethod.POST)
	public @ResponseBody
	String getAllUserIds() {
		String result = ErrorType.error500.name();
		try {
			result = PojoMapper.toJson(userLogic.getAllUserIdsByCategory());
		}
		catch (Exception e) {
			logger.error(result, e);
		}
		return result;
	}

	@RequestMapping(value = RESTurl.changeUserIcon, method = RequestMethod.POST)
	public @ResponseBody
	String changeUserIcon(@RequestBody String postData) {
		String result = ErrorType.error500.name();
		try {
			UserIconDTO userIconDTO = PojoMapper.fromJsonAsObject(postData, UserIconDTO.class);
			userLogic.changeUserIcon(userIconDTO);
			result = CommConstants.OK_MARK;
		}
		catch (Exception e) {
			result = ErrorType.error500.name();
			logger.error(result, e);
		}
		return result;
	}

}
