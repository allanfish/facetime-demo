package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.client.MessagesDTO;
import com.conlect.oatos.dto.client.SysMsgDTO;
import com.conlect.oatos.dto.client.UserDeviceTokensDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 消息REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>取未读消息
 * <li>处理消息，消息设为已读
 * <li>根据发送者及消息类型取消息记录
 * <li>保存消息至数据库
 * <li>分页取聊天历史记录
 * <li>批量保存消息至数据库
 * <li>保存系统消息
 * <li>取企业信息记录
 * <li>判断是否有未读消息
 * <li>返回未读的系统消息
 * </ul>
 * 
 */
public interface MessageUrl {
	/**
	 * <p>
	 * 取未读消息
	 * </p>
	 * <b>参数： </b> 用户ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link MessagesDTO}消息DTO <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getUnreadMessage = "/sc/message/getOfflineMessage";
	/**
	 * <p>
	 * 处理消息，消息设为已读
	 * </p>
	 * <b>参数： </b>{@link MessageDTO}消息DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String confirmMessage = "/sc/message/confirmMessage";
	/**
	 * <p>
	 * 根据发送者及消息类型取消息记录
	 * </p>
	 * <b>参数： </b>userId 用户ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link MessagesDTO}消息DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getMessageRecord = "/sc/message/getMessageRecord";

	/**
	 * <p>
	 * 保存消息至数据库
	 * </p>
	 * <b>参数： </b>{@link MessageDTO}消息DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String sendMessage = "/sc/message/sendMessage";

	/**
	 * <p>
	 * 分页取聊天历史记录
	 * </p>
	 * <b>参数： </b>userId 用户ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link MessagesDTO}消息DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getChatHistory = "/sc/message/getChatHistory";

	/**
	 * <p>
	 * 批量保存消息至数据库
	 * </p>
	 * <b>参数： </b>{@link MessageDTO}消息DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String saveMessages = "/sc/message/saveMessages";

	/**
	 * <p>
	 * 保存系统消息
	 * </p>
	 * <b>参数： </b>{@link MessageDTO}消息DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String saveSysMsgs = "/pub/message/saveSysMsgs";

	/**
	 * <p>
	 * 取企业信息记录
	 * </p>
	 * <b>参数： </b>{@link MessageDTO}消息DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link MessagesDTO}消息DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getEntRecord = "/sc/message/getEntRecord";

	/**
	 * <p>
	 * 判断是否有未读消息
	 * </p>
	 * <b>参数： </b>userId 用户ID<br>
	 * <p>
	 * <b>正常返回： </b>未读邮件数目<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String isExistUnreadMessage = "/sc/message/isExistUnreadMessage";
	/**
	 * <p>
	 * 返回未读的系统消息
	 * </p>
	 * <b>参数： </b>无<br>
	 * <p>
	 * <b>正常返回： </b>{@link SysMsgDTO} 系统消息listDT<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getUnsendSysMsg = "/pub/message/getUnsendSysMsg";

	/**
	 * <p>
	 * 获取有离线消息用户的ios设备标志
	 * </p>
	 * <b>参数： </b><br>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link UserDeviceTokensDTO}：用户ios设备标志list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getUserDeviceTokensHasUnreadMsg = "/pub/message/checkEntDiskSize";

}
