package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.BlockDTO;
import com.conlect.oatos.dto.client.BlocksDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 阻止联系人REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>用户取阻止通信的用户id(黑名单)
 * <li>用户取阻止通信的用户id(黑名单)
 * <li>取企业阻止通信列表(不可见)
 * <li>取企业阻止通信列表(不可见)
 * <li>用户解除阻止通信的用户id(黑名单)
 * <li>管理员删除企业通信阻止(不可见)
 * <li>用户取被那些同事设置为阻止通信
 * </ul>
 * 
 * @author yang
 * 
 */
public interface BlockListUrl {

	/**
	 * <p>
	 * 用户取阻止通信的用户列表(黑名单)
	 * </p>
	 * <b>参数： </b>userId 用户ID <br>
	 * <p>
	 * <b>正常返回： </b>{@link BlockDTO}阻止人信息DTO <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getBlockListByUserId = "/sc/block/getBlockListByUserId";
	/**
	 * <p>
	 * 用户设置阻止通信的用户列表(黑名单)
	 * </p>
	 * <b>参数： </b>{@link BlockDTO} 阻止人DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String setBlockListByUserId = "/sc/block/setBlockListByUserId";
	/**
	 * <p>
	 * 取企业阻止通信列表(管理员设置不可见)
	 * </p>
	 * <b>参数： </b>userId 用户ID <br>
	 * <p>
	 * <b>正常返回： </b>{@link BlockDTO} 组织人DTO <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getEnterpriseBlocksByUserId = "/sc/block/getEnterpriseBlocksByUserId";
	/**
	 * <p>
	 * 管理员设置阻止通信列表(不可见)
	 * </p>
	 * <b>参数： </b>{@link BlocksDTO}阻止联系人列表DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String updateEnterpriseBlocks = "/sc/block/updateEnterpriseBlocks";
	/**
	 * <p>
	 * 用户解除阻止通信的用户id
	 * </p>
	 * <b>参数： </b>{@link BlockDTO}阻止人信息DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String deleteBlock = "/sc/block/deleteBlock";
	/**
	 * <p>
	 * 管理员删除企业通信阻止(不可见)
	 * </p>
	 * <b>参数： </b>{@link BlockDTO}阻止人信息DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String deleteEnterpriseBlock = "/sc/block/deleteEnterpriseBlock";
	/**
	 * <p>
	 * 用户取被那些同事设置为阻止通信
	 * </p>
	 * <b>参数： </b>userId 用户ID <br>
	 * <p>
	 * <b>正常返回： </b>{@link BlockDTO}阻止人信息DTO <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getBlockedByListByUserId = "/sc/block/getBlockedByListByUserId";

}
