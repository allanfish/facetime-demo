package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.CustomerDTO;
import com.conlect.oatos.dto.client.DepartmentDTO;
import com.conlect.oatos.dto.client.DepartmentsDTO;
import com.conlect.oatos.dto.client.EnterpriseDTO;
import com.conlect.oatos.dto.client.LimitDTO;
import com.conlect.oatos.dto.client.UserInfoDTO;
import com.conlect.oatos.dto.client.admin.AdminDataDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 企业REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>企业注册
 * <li>获取一个客户ID
 * <li>获取客服的信息(客服是UserInfoDTO）
 * <li>判断企业名称是否已经注册过，注册过的返回 true。
 * <li>批量新建部门
 * <li>获取企业所有部门的信息
 * <li>获取企业信息
 * <li>获取部门信息
 * <li>修改部门信息
 * <li>删除部门
 * <li>修改企业信息
 * <li>插入 ProductKey
 * <li>获取免费的产品号
 * <li>修改企业产品序列号
 * <li>判断企业产品序列号激活状态
 * <li>获取下载速度限制
 * <li>新建部门
 * <li>获取企业管理员登录后初始信息（企业信息，管理员信息）
 * </ul>
 * 
 * @author yang
 */
public interface EnterpriseUrl {
	/**
	 * <p>
	 * 企业注册服务
	 * </p>
	 * <p>
	 * <b>参数：</b>{@link EnterpriseDTO} 企业信息 DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK} OK
	 * <li>{@link ErrorType#errorInvalidProductKey} 无效的产品序列号
	 * <li>{@link ErrorType#errorEnterpriseAlreadyExist} 企业已经存在
	 * </ul>
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String registerEnterprise = "/pub/ent/admin/registerEnterprise";
	/**
	 * <p>
	 * 获取一个客户ID
	 * </p>
	 * <p>
	 * <b>参数：</b>{@link CustomerDTO} 临时客户DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b>token 用户令牌
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */

	String createCustomerId = "/pub/ent/getCustomerId";
	/**
	 * <p>
	 * 获取客服的信息
	 * </p>
	 * <p>
	 * <b>参数：</b>enterpriseId 企业ID
	 * </p>
	 * <p>
	 * <b>正常返回：</b> {@link UserInfoDTO} 用户信息DTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String getCustomerSerivceInfo = "/sc/ent/getCustomerSerivceInfo";

	/**
	 * <p>
	 * 判断企业名称是否已经注册过，注册过的返回 true。
	 * </p>
	 * <p>
	 * <b>参数：</b>entName 企业名字
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link CommConstants#OK_MARK}
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String existEnterprise = "/pub/ent/existEnterprise";
	/**
	 * <p>
	 * 批量新建部门
	 * </p>
	 * <p>
	 * <b>参数：</b>{@link DepartmentsDTO} 部门 list DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link CommConstants#OK_MARK} OK
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorEnterpriseNotExist} 企业不存在
	 * </ul>
	 */
	String addDepartments = "/sc/ent/addDepartments";

	/**
	 * <p>
	 * 更新部门的排序Level
	 * </p>
	 * <p>
	 * <b>参数：</b>{@link DepartmentsDTO} 部门 list DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link CommConstants#OK_MARK} OK
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String updateDepartmentsLevel = "/sc/ent/updateDepartmentsLevel";

	/**
	 * <p>
	 * 获取企业所有部门的信息
	 * </p>
	 * <p>
	 * <b>参数：</b>entpriseId 企业ID
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link DepartmentsDTO} 部门 list DTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String getDepartments = "/sc/ent/getDepartments";

	/**
	 * <p>
	 * 获取企业信息
	 * </p>
	 * <p>
	 * <b>参数：</b>enterpriseId 企业的ID
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link EnterpriseDTO}企业信息 DTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String getEnterprise = "/sc/ent/getEnterprise";
	/**
	 * <p>
	 * 获取部门信息
	 * </p>
	 * <p>
	 * <b>参数：</b>departmentId 部门的ID
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link DepartmentDTO}部门DTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String getDepartment = "/sc/ent/getDepartment";

	/**
	 * <p>
	 * 修改部门信息
	 * </p>
	 * <p>
	 * <b>参数：</b>{@link DepartmentDTO} 部门DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link CommConstants#OK_MARK}
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String updateDepartment = "/sc/ent/updateDepartment";

	/**
	 * <p>
	 * 删除部门
	 * </p>
	 * <p>
	 * <b>参数：</b>{@link DepartmentDTO} 部门 DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK} OK
	 * <li>{@link ErrorType#errorDeleteDepartUserExist} 当员工存在时，删除部门失败
	 * </ul>
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}:服务器内部错误
	 * </ul>
	 */
	String deleteDepartment = "/sc/ent/deleteDepartment";

	/**
	 * <p>
	 * 修改企业信息
	 * </p>
	 * <p>
	 * <b>参数：</b>{@link EnterpriseDTO} 企业信息 DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link CommConstants#OK_MARK}
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String updateEnterprise = "/sc/ent/updateEnterprise";

	/**
	 * 
	 * <p>
	 * 插入 ProductKey(此方法已废弃)
	 * </p>
	 * <p>
	 * <b>参数：</b>sales
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link CommConstants#OK_MARK}
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String generatorProductKey = "/sc/ent/generatorProductKey";

	/**
	 * <p>
	 * 获取免费的产品号
	 * </p>
	 * <p>
	 * <b>参数：</b>无
	 * </p>
	 * <p>
	 * <b>正常返回：</b>无
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String getFreeProductKey = "/pub/ent/getFreeProductKey";
	/**
	 * <p>
	 * 修改企业产品序列号
	 * </p>
	 * <p>
	 * <b>参数：</b>{@link EnterpriseDTO} 企业信息 DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorInvalidProductKey} 无效的产品序列号
	 * <li>{@link ErrorType#errorProductKeyFree} 免费的产品key
	 * <li>{@link CommConstants#OK_MARK} OK
	 * </ul>
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}:服务器内部错误
	 * </ul>
	 */
	String registerProductKey = "/sc/ent/registerProductKey";

	/**
	 * <p>
	 * 判断企业产品序列号激活状态
	 * </p>
	 * <p>
	 * <b>参数：</b>entId 企业的ID
	 * </p>
	 * <p>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorInvalidProductKey} 无效的产品序列号
	 * <li>{@link ErrorType#errorProductKeyFree} 免费的产品key
	 * <li>{@link CommConstants#OK_MARK} OK
	 * </ul>
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}:服务器内部错误
	 * </ul>
	 */
	String checkProductKey = "/sc/ent/checkProductKey";
	/**
	 * <p>
	 * 获取下载速度限制
	 * </p>
	 * <p>
	 * <b>参数：</b>userId 用户ID
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link LimitDTO} 上传下载限制 DTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}:服务器内部错误
	 * </ul>
	 */
	String getUploadLimit = "/sc/ent/getUploadLimit";

	/**
	 * <p>
	 * 新建部门
	 * </p>
	 * <p>
	 * <b>参数：</b>{@link DepartmentDTO} 部门 DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link DepartmentDTO} 部门DTO
	 * <li>{@link ErrorType#errorSameName} 相同的名字
	 * </ul>
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}:服务器内部错误
	 * </ul>
	 */
	String addDepartment = "/sc/ent/addDepartment";
	/**
	 * <p>
	 * 获取企业管理员登录后初始信息
	 * </p>
	 * <p>
	 * <b>参数：</b>token 令牌
	 * </p>
	 * <p>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link AdminDataDTO} 企业管理员登录后，取初始化数据DTO
	 * </ul>
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}:服务器内部错误
	 * </ul>
	 */
	String getAdminData = "/sc/ent/getAdminData";
}
