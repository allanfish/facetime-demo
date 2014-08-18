package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.ShareLinkDTO;
import com.conlect.oatos.dto.client.ShareLinkInfoDTO;
import com.conlect.oatos.dto.client.ShareLinkMailDTO;
import com.conlect.oatos.dto.client.ShareLinksDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 外链REST服务请求地址
 * </p>
 * 
 * 主要功能:
 * <ul>
 * <li>创建共享链接
 * <li>更新共享链接
 * <li>删除共享链接
 * <li>按code获取共享链接
 * <li>获取企业的外链
 * <li>获取文件夹或文件的外链
 * <li>按id获取共享链接
 * <li>检查外链是否可以下载
 * <li>更新共享链接下载次数和流量
 * <li>发送共享链接邮件
 * </ul>
 * 
 * @author huhao
 * 
 */
public interface ShareLinkUrl {
	/**
	 * <p>
	 * 创建共享链接
	 * </p>
	 * <b>参数： </b>{@link ShareLinkDTO} 共享链接DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link ShareLinkDTO} 共享链接DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String createShareLink = "/sc/shareLink/createShareLink";
	/**
	 * <p>
	 * 更新共享链接
	 * </p>
	 * <b>参数： </b>{@link ShareLinkDTO} 共享链接DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String updateShareLink = "/sc/shareLink/updateShareLink";

	/**
	 * <p>
	 * 删除共享链接
	 * </p>
	 * <b>参数： </b>linkId 链接ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String deleteShareLink = "/sc/shareLink/deleteShareLink";
	/**
	 * <p>
	 * 获取共享链接
	 * </p>
	 * <b>参数： </b>linkCode 链接代码<br>
	 * <p>
	 * <b>正常返回： </b>{@link ShareLinkInfoDTO} 共享链接信息DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getShareLinkByCode = "/pub/shareLink/getShareLinkByCode";

	/**
	 * <p>
	 * 获取共享链接
	 * </p>
	 * <b>参数： </b>enterpriseId 企业ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link ShareLinksDTO} 共享链接DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getShareLinkByEntId = "/sc/shareLink/getShareLinkByEntId";

	/**
	 * 获取共享链接
	 * 
	 * @param ShareLinkDTO
	 * @return ShareLinkDTO
	 */

	/**
	 * <p>
	 * 获取共享链接
	 * </p>
	 * <b>参数： </b>{@link ShareLinkDTO} 共享链接DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link ShareLinkDTO} 共享链接DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getExistShareLink = "/sc/shareLink/getExistShareLink";
	/**
	 * <p>
	 * 获取共享链接
	 * </p>
	 * <b>参数： </b>linkId <br>
	 * <p>
	 * <b>正常返回： </b>{@link ShareLinkDTO} 共享链接DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getShareLinkByLinkId = "/sc/shareLink/getShareLinkByLinkId";

	/**
	 * <p>
	 * 验证共享链接是否可以下载
	 * </p>
	 * <b>参数： </b>linkID<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorFileNotFound}文件不存在
	 * <li>{@link ErrorType#errorExpirationTimeOver}过期了
	 * <li>{@link ErrorType#errorDownCountOver}超过下载数了
	 * <li>{@link ErrorType#errorFlowOver}流量超出
	 * </ul>
	 */
	String checkShareLinkDownload = "/sc/shareLink/checkShareLinkDownload";
	/**
	 * <p>
	 * 更新共享链接下载次数
	 * </p>
	 * <b>参数： </b>linkId:size：外链id+文件大小kb<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String updateShareLinkDownCount = "/sc/shareLink/updateShareLinkDownCount";

	/**
	 * <p>
	 * 发送共享链接
	 * </p>
	 * <b>参数： </b>{@link ShareLinkMailDTO} 外链邮件DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String sendShareLinkMail = "/sc/shareLink/sendShareLinkMail";

}
