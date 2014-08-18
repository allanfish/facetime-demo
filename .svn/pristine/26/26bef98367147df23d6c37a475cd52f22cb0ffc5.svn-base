package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.DepartmentAndUserDTO;
import com.conlect.oatos.dto.client.EnterpriseUserDTO;
import com.conlect.oatos.dto.client.SimpleUserInfosDTO;
import com.conlect.oatos.dto.client.UserIconDTO;
import com.conlect.oatos.dto.client.UserInfoCategoryDTO;
import com.conlect.oatos.dto.client.UserInfoDTO;
import com.conlect.oatos.dto.client.UserInfosDTO;
import com.conlect.oatos.dto.client.UserStatusesDTO;
import com.conlect.oatos.dto.client.user.UserImportSaveDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.UserStatus;

/**
 * <p>
 * 同事REST服务请求地址
 * </p>
 * 
 * 主要功能:
 * <ul>
 * <li>添加企业员工
 * <li>用户导入
 * <li>获取单个用户信息
 * <li>修改用户信息
 * <li>批量修改用户信息
 * <li>修改员工信息和登录密码
 * <li>取某用户的在线状态
 * <li>取在线同事(基本信息)
 * <li>统计企业在线用户数
 * <li>按企业id取企业用户，包含角色信息
 * <li>删除企业员工
 * <li>获取所有同事的在线状态
 * <li>按企业id取企业用户，不包括状态及角色
 * <li>按企业id取企业部门和成员，不包括角色和状态
 * <li>按企业id取企业部门和成员，包含角色，不包含状态
 * <li>按用户id取企业部门和成员,包含在线状态，不包含角色信息
 * <li>取在线用户和离线用户ID
 * </ul>
 * 
 * @author huhao
 * 
 */
public interface UserUrl {

	/**
	 * <p>
	 * 添加企业用户
	 * </p>
	 * <p>
	 * <b>参数:</b>{@link EnterpriseUserDTO} 企业员工DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b>userId 用户的ID
	 * </p>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorCheckHashkey}数据未通过安全验证
	 * <li>{@link ErrorType#errorEnterpriseNotExist}企业不存在
	 * <li>{@link ErrorType#errorUserNoOver}用户数已经用完
	 * <li>{@link ErrorType#errorPayExpired}购买的用户已经过期
	 * <li>{@link ErrorType#errorEmployeeAlreadyExist}用户名已经存在
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String addEnterpriseUser = "/sc/ent/addEnterpriseUser";
	/**
	 * <p>
	 * 用户导入
	 * </p>
	 * <p>
	 * <b>参数：</b> {@link UserImportSaveDTO} 用户导入DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link CommConstants#OK_MARK } OK
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#errorCheckHashkey}数据未通过安全验证
	 * <li>{@link ErrorType#errorEnterpriseNotExist}企业不存在
	 * <li>{@link ErrorType#errorUserNoOver}用户数已经用完
	 * <li>{@link ErrorType#errorPayExpired}购买的用户已经过期
	 * <li>{@link ErrorType#errorEmployeeAlreadyExist}用户名已经存在
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String addEnterpriseUserList = "/sc/ent/addEnterpriseUserList";

	/**
	 * <p>
	 * 获取单个用户信息
	 * </p>
	 * <p>
	 * <b>参数：</b>userId用户ID
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link UserInfoDTO} 用户信息
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String getUserProfile = "/sc/login/getUserProfile";

	/**
	 * <p>
	 * 修改用户信息
	 * </p>
	 * <p>
	 * <b>参数：</b>{@link UserInfoDTO} 用户信息DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link CommConstants#OK_MARK } OK
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String updateUserProfile = "/sc/login/updateUserProfile";

	/**
	 * <p>
	 * 批量修改用户信息
	 * </p>
	 * <p>
	 * <b>参数：</b>{@link UserInfosDTO} 用户信息list DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link CommConstants#OK_MARK } OK
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String updateUsers = "/sc/login/updateUsers";

	/**
	 * <p>
	 * 修改员工信息和登录密码
	 * </p>
	 * <p>
	 * <b>参数：</b>{@link UserInfoDTO} 用户信息DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link CommConstants#OK_MARK } OK
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String updateEnterpriseUserAndPassword = "/sc/ent/updateEnterpriseUserAndPassword";

	/**
	 * <p>
	 * 取某用户的在线状态
	 * </p>
	 * <p>
	 * <b>参数：</b>userId用户ID
	 * </p>
	 * <p>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link UserStatus#Logout} 退出
	 * <li>{@link UserStatus#Busy} 忙碌
	 * <li>{@link UserStatus#Corbet}隐身
	 * <li>{@link UserStatus#Leave}离开
	 * <li>{@link UserStatus#Offline} 不在线
	 * <li>{@link UserStatus#Online} 在线
	 * </ul>
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String getUserStatus = "/sc/login/getUserStatus";
	/**
	 * <p>
	 * 取在线同事(基本信息)
	 * </p>
	 * <p>
	 * <b>参数：</b>userId 用户ID
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link SimpleUserInfosDTO}用户基本信息list DTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String getOnlineBuddyAndColleague = "/sc/login/getOnlineBuddyAndColleague";

	/**
	 * <p>
	 * 统计企业在线用户数
	 * </p>
	 * <p>
	 * <b>参数：</b>无
	 * </p>
	 * <p>
	 * <b>正常返回：</b>企业Id,在线用户数
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String statisticalUser = "/pub/statisticalUser";

	/**
	 * <p>
	 * 按企业id取企业用户，包含角色信息
	 * </p>
	 * <p>
	 * <b>参数：</b>企业id
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link UserInfosDTO}用户信息list DTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String getEnterpriseUserListWithRoles = "/sc/ent/getEnterpriseUserList";

	/**
	 * <p>
	 * 删除企业员工
	 * </p>
	 * <p>
	 * <b>参数：</b>{@link UserInfosDTO} 用户信息list DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link CommConstants#OK_MARK } OK
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String deleteEnterpriseUsers = "/sc/ent/deleteEnterpriseUsers";
	/**
	 * <p>
	 * 获取所有同事的在线状态
	 * </p>
	 * <p>
	 * <b>参数：</b>企业id
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link UserStatusesDTO} 用户状态list DTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String getEmployeesStatus = "/sc/ent/getEmployeesStatus";

	/**
	 * <p>
	 * 按企业id取企业用户，不包括状态及角色
	 * </p>
	 * <p>
	 * <b>参数：</b>企业id
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link UserInfosDTO} 用户信息list DTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String getUsersByEntId = "/sc/ent/getEmployeesByEntId";
	/**
	 * <p>
	 * 按企业id取企业部门和成员，不包括角色和状态
	 * </p>
	 * <p>
	 * <b>参数：</b>企业id
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link DepartmentAndUserDTO}企业部门和用户 DTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String getDepartmentAndUserByEntId = "/sc/ent/getDepartmentAndUserByEntId";

	/**
	 * <p>
	 * 按企业id取企业部门和成员，包含角色，不包含状态
	 * </p>
	 * <p>
	 * <b>参数：</b>企业id
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link DepartmentAndUserDTO}企业部门和用户 DTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String getDepartmentAndUserWithRolesByEntId = "/sc/ent/getDepartmentAndUserWithRolesByEntId";

	/**
	 * <p>
	 * 按用户id取企业部门和成员,包含在线状态，不包含角色信息
	 * </p>
	 * <p>
	 * <b>参数：</b>userId 用户id
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link DepartmentAndUserDTO}企业部门和用户 DTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String getDepartmentAndUserWithStatusByUserId = "/sc/ent/getDepartmentAndUserWithStatusByUserId";
	/**
	 * <p>
	 * 取在线用户和离线用户ID
	 * </p>
	 * <p>
	 * <b>参数：</b>无
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link UserInfoCategoryDTO} 用户信息分类DTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String getAllUserIds = "/pub/ent/getAllUserIds";

	/**
	 * <p>
	 * 修改用户头像
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link UserIconDTO}的JSON：用户头像修改信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：修改成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String changeUserIcon = "/sc/user/changeUserIcon";
}
