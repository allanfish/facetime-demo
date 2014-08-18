package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.admin.AdminsDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 二级管理员REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>取企业管理员信息
 * <li>添加企业管理员
 * <li>修改企业管理员
 * <li>删除企业管理员
 * </ul>
 * 
 * @author yang
 * 
 */
public interface AdminUrl {
	/**
	 * <p>
	 * 获取企业的二级管理员
	 * </p>
	 * <b>参数： </b>enterprise id 企业id <br>
	 * <p>
	 * <b>正常返回： </b>{@link AdminsDTO}管理员DTO <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getAdminsByEntId = "/sc/admin/getAdminsByEntId";
	/**
	 * <p>
	 * 添加企业管理员
	 * </p>
	 * <b>参数： </b>{@link AdminsDTO} 企业管理员DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String addAdmins = "/sc/admin/addAdmins";

	/**
	 * <p>
	 * 修改企业管理员
	 * </p>
	 * <b>参数： </b>{@link AdminsDTO} 企业管理员DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String updateAdmins = "/sc/admin/updateAdmins";

	/**
	 * <p>
	 * 删除企业管理员
	 * </p>
	 * <b>参数： </b>{@link AdminsDTO} 企业管理员DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String deleteAdmin = "/sc/admin/deleteAdmin";

}
