//Copyright(C) 2013 by QYCloud,Inc.All rigthts reserved.
package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.EnterpriseLoginDTO;
import com.conlect.oatos.dto.client.PasswordChangeDTO;
import com.conlect.oatos.dto.client.ReLoginDTO;
import com.conlect.oatos.dto.client.UserStatusDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 登录REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>企业管理员登录
 * <li>企业用户登录，登出
 * <li>修改企业管理员密码
 * <li>修改用户登录密码
 * <li>用户修改在线状态
 * <li>客服服务客户数目减1
 * </ul>
 * 
 * @author yang
 * 
 */
public interface LoginUrl {

	/**
	 * <p>
	 * 企业管理员登录服务
	 * </p>
	 * <p>
	 * <b>参数： </b>{@link EnterpriseLoginDTO} 企业用户登录DTO <br>
	 * </p>
	 * <p>
	 * <b>正常返回： </b>toeken 用户令牌 <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#errorCheckHashkey} 数据未通过安全验证
	 * <li>{@link ErrorType#errorWrongEnterpriseName} 企业名错误
	 * <li>{@link ErrorType#errorAuditFail} 该企业服务已停止
	 * <li>{@link ErrorType#errorAccountExpired} 该企业服务已过期
	 * <li>{@link ErrorType#errorWrongAccount} 用户名错误
	 * <li>{@link ErrorType#errorWrongPWD} 密码错误
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String adminLogin = "/pub/ent/admin/login";

	/**
	 * <p>
	 * 企业用户登录服务
	 * </p>
	 * <p>
	 * <b>参数： </b> {@link EnterpriseLoginDTO} 企业用户登录DTO <br>
	 * </p>
	 * <p>
	 * <b>正常返回： </b>toeken 用户令牌
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#errorCheckHashkey} 数据未通过安全验证
	 * <li>{@link ErrorType#errorAuditFail} 该企业服务已停止
	 * <li>{@link ErrorType#errorAccountExpired} 该企业服务已过期
	 * <li>{@link ErrorType#errorWrongAccount} 企业名或用户名错误
	 * <li>{@link ErrorType#errorUserLocked} 用户被锁定，禁止登陆
	 * <li>{@link ErrorType#errorPayExpired} 购买的服务已过期
	 * <li>{@link ErrorType#errorWrongPWD} 密码错误
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String enterpriseUserLogin = "/pub/ent/enterpriseUserLogin";

	/**
	 * <p>
	 * 修改企业管理员登录密码
	 * </p>
	 * <p>
	 * <b>参数： </b> {@link PasswordChangeDTO} 用户密码修改DTO
	 * </p>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK
	 * </p>
	 * <b>异常返回： </b><br>
	 * <ul>
	 * <li>{@link ErrorType#errorCheckHashkey} 数据未通过安全验证
	 * <li>{@link ErrorType#errorWrongPWD} 旧密码错误
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String changeAdminPassword = "/sc/ent/changeAdminPassword";

	/**
	 * <p>
	 * 退出登录
	 * </p>
	 * <p>
	 * <b>参数： </b> userId 用户ID
	 * </p>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#errorCheckToken} token验证失败
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String logout = "/sc/login/logout";

	/**
	 * <p>
	 * 修改用户状态
	 * </p>
	 * <p>
	 * <b>参数： </b>{@link UserStatusDTO} 用户状态DTO
	 * </p>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String changeUserStatus = "/sc/login/changeUserStatus";

	/**
	 * <p>
	 * 修改用户密码
	 * </p>
	 * <p>
	 * <b>参数: </b> {@link PasswordChangeDTO} 用户密码修改
	 * </p>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorCheckHashkey} 数据未通过安全验证
	 * <li>{@link ErrorType#errorWrongOldPWD} 旧密码错误
	 * </ul>
	 */
	String changePassword = "/sc/login/changePassword";

	/**
	 * <p>
	 * 检查token是否有效
	 * </p>
	 * <p>
	 * <b>参数: </b>token 用户令牌
	 * </p>
	 * <p>
	 * <b>正常返回： </b>
	 * </p>
	 * <ul>
	 * <li>true
	 * <li>false
	 * </ul>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String checkToken = "/sc/login/checkToken";

	/**
	 * <p>
	 * 客服的客户服务计数减1
	 * </p>
	 * <p>
	 * <b>参数： </b>userId 用户ID
	 * </p>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK
	 * </p>
	 * <b>异常返回： </b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorUserIsOffline} 用户不在线
	 * </ul>
	 */
	String reductionCustomerCounter = "/sc/login/reductionCustomerCounter";

	/**
	 * <p>
	 * 重新登录
	 * </p>
	 * <p>
	 * <b>参数： </b>{@link ReLoginDTO} 重新登录DTO
	 * </p>
	 * <p>
	 * <b>正常返回： </b>token 用户令牌
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String reLogin = "/sc/login/reLogin";

	/**
	 * <p>
	 * 验证用户登录密码
	 * </p>
	 * <p>
	 * <b>参数： </b>
	 * </p>{@link PasswordChangeDTO} 用户密码修改DTO
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK
	 * </p>
	 * <b>异常返回： </b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误 <br>
	 * <li>{@link ErrorType#errorCheckHashkey} 数据未通过安全验证<br>
	 * <li>{@link ErrorType#errorWrongPWD} 密码错误<br>
	 * </ul>
	 */
	String checkUserPassword = "/sc/ent/checkUserPassword";
}
