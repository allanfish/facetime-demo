package com.qycloud.oatos.server.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.CheckPermissionDTO;
import com.conlect.oatos.dto.client.CheckPermissionsDTO;
import com.conlect.oatos.dto.client.RoleDTO;
import com.conlect.oatos.dto.client.RolePermissionsDTO;
import com.conlect.oatos.dto.client.RoleUsersDTO;
import com.conlect.oatos.dto.client.RolesDTO;
import com.conlect.oatos.dto.client.UserRolesDTO;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.logic.RoleLogic;
import com.qycloud.oatos.server.util.BllLogger;
import com.qycloud.oatos.server.util.LogicException;

/**
 * 角色权限服务
 * @author yang
 *
 */
@Controller("RoleService")
public class RoleService {

	private final static Logger logger = BllLogger.getLogger();

	@Autowired
	private RoleLogic roleLogic;

	/**
	 * 添加角色
	 * @param roleString
	 * @return
	 */
	@RequestMapping(value = RESTurl.addRole, method = RequestMethod.POST)
	public @ResponseBody
	String addRole(@RequestBody String roleString) {
		String response = "";
		try {
			RoleDTO role = PojoMapper.fromJsonAsObject(roleString,
					RoleDTO.class);
			response = roleLogic.addRole(role);
		} catch (LogicException ex) {
			response = ex.getError().name();
		} catch (Exception ex) {
			response = ErrorType.error500.name();
			logger.error(roleString, ex);
		}

		return response;
	}

	/**
	 * 添加角色列表
	 * @param roleString
	 * @return
	 */
	@RequestMapping(value = RESTurl.addRoleList, method = RequestMethod.POST)
	public @ResponseBody
	String addRoleList(@RequestBody String roleString) {
		String response = "";
		try {
			RolesDTO roles = PojoMapper.fromJsonAsObject(roleString,
					RolesDTO.class);
			response = roleLogic.addRoleList(roles.getRoleList());
		} catch (LogicException ex) {
			response = ex.getError().name();
		} catch (Exception ex) {
			response = ErrorType.error500.name();
			logger.error(roleString, ex);
		}

		return response;
	}

	/**
	 * 修改角色
	 * @param roleString
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateRole, method = RequestMethod.POST)
	public @ResponseBody
	String updateRole(@RequestBody String roleString) {
		String response = "";
		try {
			RoleDTO role = PojoMapper.fromJsonAsObject(roleString,
					RoleDTO.class);
			response = roleLogic.updateRole(role);
		} catch (LogicException ex) {
			response = ex.getError().name();
		} catch (Exception ex) {
			response = ErrorType.error500.name();
			logger.error(roleString, ex);
		}
		return response;
	}

	/**
	 * 删除角色
	 * @param roleString
	 * @return
	 */
	@RequestMapping(value = RESTurl.deleteRole, method = RequestMethod.POST)
	public @ResponseBody
	String deleteRole(@RequestBody String roleString) {
		String response = "";
		try {
			RoleDTO role = PojoMapper.fromJsonAsObject(roleString,
					RoleDTO.class);
			response = roleLogic.deleteRole(role);
		} catch (Exception ex) {
			response = ErrorType.error500.name();
			logger.error(roleString, ex);
		}
		return response;
	}

	/**
	 * 指定企业ID，获取企业所有角色数据
	 * @param enterpriseId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getRolesByEnterprise, method = RequestMethod.POST)
	public @ResponseBody
	String getRolesByEnterprise(@RequestBody String enterpriseId) {
		String response = "";
		try {
			RolesDTO rolesDto = roleLogic.getRolesByEnterprise(Long
					.parseLong(enterpriseId));
			response = PojoMapper.toJson(rolesDto);
		} catch (Exception ex) {
			response = ErrorType.error500.name();
			logger.error(enterpriseId, ex);
		}
		return response;
	}

	/**
	 * 指定企业ID， 获取企业中所有角色和文件夹之间的权限关系
	 * @param enterpriseId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getRolePermissionsByEnterprise, method = RequestMethod.POST)
	public @ResponseBody
	String getRolePermissionsByEnterprise(@RequestBody String enterpriseId) {
		String response = "";
		try {
			RolePermissionsDTO permissionsDto = roleLogic.getRolePermissionsByEnterprise(Long.parseLong(enterpriseId));
			response = PojoMapper.toJson(permissionsDto);
		} catch (Exception ex) {
			response = ErrorType.error500.name();
			logger.error(enterpriseId, ex);
		}
		return response;
	}

	/**
	 * 指定企业ID， 获取企业所有用户和角色数据
	 * @param enterpriseId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getUserRolesByEnterprise, method = RequestMethod.POST)
	public @ResponseBody
	String getUserRolesByEnterprise(@RequestBody String enterpriseId) {
		String response = "";
		try {
			UserRolesDTO rolesDto = roleLogic
					.getUserRolesByEnterprise(Long.parseLong(enterpriseId));
			response = PojoMapper.toJson(rolesDto);
		} catch (Exception ex) {
			response = ErrorType.error500.name();
			logger.error(enterpriseId, ex);
		}
		return response;
	}

	/**
	 * 保存角色的文件夹权限信息
	 * @param enterpriseId
	 * @return
	 */
	@RequestMapping(value = RESTurl.savePermissions, method = RequestMethod.POST)
	public @ResponseBody
	String updatePermissions(@RequestBody String rolePermissionsDTOString) {
		String response = "";
		try {
			RolePermissionsDTO permissionsDto = PojoMapper.fromJsonAsObject(
					rolePermissionsDTOString, RolePermissionsDTO.class);
			response = roleLogic.updatePermissions(permissionsDto);
		} catch (Exception ex) {
			response = ErrorType.error500.name();
			logger.error(rolePermissionsDTOString, ex);
		}
		return response;
	}

	/**
	 * 保存用户的角色
	 * @param enterpriseId
	 * @return
	 */
	@RequestMapping(value = RESTurl.saveUserRoles, method = RequestMethod.POST)
	public @ResponseBody
	String saveUserRoles(@RequestBody String userRolesDTOString) {
		String response = "";
		try {
			UserRolesDTO userRolesDto = PojoMapper.fromJsonAsObject(
					userRolesDTOString, UserRolesDTO.class);
			response = roleLogic.saveUserRoles(userRolesDto);
		} catch (Exception ex) {
			response = ErrorType.error500.name();
			logger.error(userRolesDTOString, ex);
		}

		return response;
	}

	/**
	 * 检查文件操作权限
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.checkPermission, method = RequestMethod.POST)
	@ResponseBody
	public String checkPermission(@RequestBody String requestBody) {
		String reBody = "";
		try {
			CheckPermissionDTO checkDTO = PojoMapper.fromJsonAsObject(requestBody, CheckPermissionDTO.class);
			reBody = roleLogic.checkPermission(checkDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 批量检查文件操作权限
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.checkPermissions, method = RequestMethod.POST)
	@ResponseBody
	public String checkPermissions(@RequestBody String requestBody) {
		String reBody = "";
		try {
			CheckPermissionsDTO checkDTO = PojoMapper.fromJsonAsObject(requestBody, CheckPermissionsDTO.class);
			reBody = roleLogic.checkPermissions(checkDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 获取企业下的角色及用户
	 * @param entId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getRoleUsersByEntId, method = RequestMethod.POST)
	@ResponseBody
	public String getRoleUsersByEntId(@RequestBody String entId) {
		String reBody = "";
		try {
			RoleUsersDTO roleUsersDTO = roleLogic.getRoleUsersByEntId(Long.parseLong(entId));
			reBody = PojoMapper.toJson(roleUsersDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(entId, ex);
		}
		return reBody;
	}

	/**
	 * 修改用户角色
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateRoleUsers, method = RequestMethod.POST)
	@ResponseBody
	public String updateRoleUsers(@RequestBody String requestBody) {
		String reBody = "";
		try {
			RoleUsersDTO roleUsersDTO = PojoMapper.fromJsonAsObject(requestBody, RoleUsersDTO.class);
			reBody = roleLogic.updateRoleUsers(roleUsersDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

}
