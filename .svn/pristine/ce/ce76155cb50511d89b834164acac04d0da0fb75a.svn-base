package com.qycloud.oatos.server.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.Message;
import com.qycloud.oatos.server.domain.entity.UserDeviceToken;

/**
 * 消息实体层
 * @author yang
 *
 */
public interface MessageMapper {

	/**
	 * 获取未读消息
	 * 
	 * @param receiverId
	 * @return
	 */
	List<Message> getUnreadMsgByReceiverId(long receiverId);

	/**
	 * 获取未读消息总数
	 * 
	 * @param receiverId
	 * @return
	 */
	int getUnreadMsgCountByReceiverId(long receiverId);

	/**
	 * 设置聊天消息为已读
	 * @param senderId
	 * @param receiverId
	 * @param sendTime
	 */
	void updateChatMsgRead(@Param("senderId") long senderId, @Param("receiverId") long receiverId, @Param("sendTime") Date sendTime);

	/**
	 * 批量插入聊天记录
	 * 
	 * @param messages
	 */
	void insertMessages(List<Message> messages);

	/**
	 * 分页取聊天历史记录
	 * @param senderId
	 * @param receiverId
	 * @param skipResults
	 * @param maxResults
	 * @return
	 */
	List<Message> getChatHistory(@Param("senderId") long senderId, @Param("receiverId") long receiverId, @Param("skipResults") int skipResults, @Param("maxResults") int maxResults);

	/**
	 * 取聊天历史记录条数
	 * @param senderId
	 * @param receiverId
	 * @return
	 */
	long getChatHistoryCount(@Param("senderId") long senderId, @Param("receiverId") long receiverId);

	/**
	 * 按接收者取聊天消息记录
	 * @param receiverId
	 * @param messageType
	 * @return
	 */
	List<Message> getChatRecordByReceiverAndType(@Param("receiverId") long receiverId, @Param("messageType") String messageType);

	/**
	 * 取聊天消息记录
	 * @param senderId
	 * @param receiverId
	 * @param messageType
	 * @return
	 */
	List<Message> getChatRecordByType(@Param("senderId") long senderId, @Param("receiverId") long receiverId, @Param("messageType") String messageType);

	/**
	 * 获取有device_token且有未读消息的用户
	 * @return
	 */
	List<UserDeviceToken> getUserDeviceTokensHasUnreadMsg();
}
