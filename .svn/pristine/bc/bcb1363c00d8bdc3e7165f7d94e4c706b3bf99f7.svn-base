package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.CheckPermissionDTO;
import com.conlect.oatos.dto.client.CheckPermissionsDTO;
import com.conlect.oatos.dto.client.RoleDTO;
import com.conlect.oatos.dto.client.RolePermissionsDTO;
import com.conlect.oatos.dto.client.RoleUsersDTO;
import com.conlect.oatos.dto.client.RolesDTO;
import com.conlect.oatos.dto.client.UserRolesDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 角色权限REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>增加角色
 * <li>增加角色列表
 * <li>修改角色
 * <li>删除角色
 * <li>指定企业ID，获取企业所有角色数据
 * <li>指定企业ID， 获取企业中所有角色和文件夹之间的权限关系
 * <li>指定企业ID， 获取企业所有用户和角色数据
 * <li>指定企业ID， 获取企业所有用户和角色数据
 * <li>保存角色的文件夹权限信息
 * <li>保存用户的角色
 * <li>检查文件操作权限
 * <li>批量检查文件操作权限
 * <li>取角色及用户
 * <li>更新用户角色
 * </ul>
 * 
 * @author yang
 * 
 */
public interface RoleUrl {
	/**
	 * <p>
	 * 增加角色
	 * </p>
	 * <b>参数： </b>{@link RoleDTO} 角色DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link RoleDTO} 角色DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#errorSameName} 同名角色错误
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String addRole = "/sc/shareDisk/addRole";
	/**
	 * <p>
	 * 增加角色列表
	 * </p>
	 * <b>参数： </b>{@link RolesDTO} 角色DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link RolesDTO} 角色DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#errorSameName} 同名角色错误
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String addRoleList = "/sc/ShareDisk/addRoleList";

	/**
	 * <p>
	 * 修改角色
	 * </p>
	 * <b>参数： </b>{@link RoleDTO} 角色DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#errorSameName} 同名角色错误
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String updateRole = "/sc/shareDisk/updateRole";

	/**
	 * <p>
	 * 删除角色
	 * </p>
	 * <b>参数： </b>{@link RoleDTO} 角色DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String deleteRole = "/sc/shareDisk/deleteRole";

	/**
	 * <p>
	 * 指定企业ID，获取企业所有角色数据
	 * </p>
	 * <b>参数： </b>enterpriseId企业ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link RolesDTO} 角色列表DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getRolesByEnterprise = "/sc/shareDisk/getRolesByEnterprise";
	/**
	 * <p>
	 * 指定企业ID， 获取企业中所有角色和文件夹之间的权限关系
	 * </p>
	 * <b>参数： </b> enterpriseId企业ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link RolePermissionsDTO} 角色企业网盘权限listDTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getRolePermissionsByEnterprise = "/sc/shareDisk/getRolePermissionsByEnterprise";
	/**
	 * <p>
	 * 指定企业ID， 获取企业所有用户和角色数据
	 * </p>
	 * <b>参数： </b>enterpriseId企业ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link UserRolesDTO} 用户角色DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getUserRolesByEnterprise = "/sc/shareDisk/getUserRolesByEnterprise";

	/**
	 * <p>
	 * 保存角色的文件夹权限信息
	 * </p>
	 * <b>参数： </b>{@link RolePermissionsDTO} 角色权限DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String savePermissions = "/sc/shareDisk/savePermissions";

	/**
	 * <p>
	 * 保存用户的角色
	 * </p>
	 * <b>参数： </b>{@link UserRolesDTO} 用户角色DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String saveUserRoles = "/sc/shareDisk/saveUserRoles";
	/**
	 * <p>
	 * 检查文件操作权限
	 * </p>
	 * <b>参数： </b>{@link CheckPermissionDTO} 检查权限DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorNoPermission} 无权限
	 * </ul>
	 */
	String checkPermission = "/sc/shareDisk/checkPermission";
	/**
	 * <p>
	 * 批量检查文件操作权限
	 * </p>
	 * <b>参数： </b>{@link CheckPermissionsDTO} 批量检查权限DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorNoPermission} 无权限
	 * </ul>
	 */
	String checkPermissions = "/sc/shareDisk/checkPermissions";
	/**
	 * <p>
	 * 取角色及用户
	 * </p>
	 * <b>参数： </b>enterpriseId 企业ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link RoleUsersDTO} 用户角色DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getRoleUsersByEntId = "/sc/shareDisk/getRoleUsersByEntId";

	/**
	 * <p>
	 * 更新用户角色
	 * </p>
	 * <b>参数： </b>{@link UserRolesDTO} 用户角色DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String updateRoleUsers = "/sc/shareDisk/updateRoleUsers";

}
