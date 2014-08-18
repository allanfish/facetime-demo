package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.mail.MailDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 发送邮件REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>发送邮件
 * </ul>
 * 
 * @author huhao
 * 
 */
public interface SendMailUrl {
	/**
	 * <p>
	 * 发送邮件
	 * </p>
	 * <b>参数： </b>{@link MailDTO} 邮件DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String sendMail = "/sc/mail/sendMail";

}
