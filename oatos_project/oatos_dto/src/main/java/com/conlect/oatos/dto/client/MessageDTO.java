package com.conlect.oatos.dto.client;

import java.util.Date;

import com.conlect.oatos.dto.autobean.IMessageDTO;
import com.conlect.oatos.dto.status.MessageStatus;

/**
 * 客服端信息
 * 
 * @author PeterGuo
 */
public class MessageDTO implements BaseDTO, IMessageDTO {
	private static final long serialVersionUID = 1L;

	/**
	 * 一次会话的ID
	 */
	public long chatId;
	/**
	 * 聊天记录ID
	 */
	public String messageId;
	/**
	 * 消息类型，决定消息的处理方式
	 */
	public String messageType;
	/**
	 * 是否是离线消息 true是 false不是
	 */
	public boolean offlineMessage = false;
	/**
	 * 发送者 ID
	 */
	public Long sender;
	/**
	 * 接收者 ID
	 */
	public long receiver;
	/**
	 * 发送时间
	 */
	public Date sendDate = new Date();
	/**
	 * 消息内容
	 */
	public String messageBody;
	/**
	 * 消息语言
	 */
	public String language;
	/**
	 * 当是群消息时，群ID
	 */
	public Long groupId;
	/**
	 * 发送者名称
	 */
	public String fromUser;
	/**
	 * 接收者名称
	 */
	public String receiverName;
	/**
	 * 消息状态
	 */
	public String status = MessageStatus.New;
	/**
	 * 消息是否已显示
	 */
	private boolean showed = false;
	/**
	 * 消息是否已确认
	 */
	private boolean confirmed = false;
	/**
	 * 消息是否正显示
	 */
	private boolean displaying = false;

	public MessageDTO() {
	}

	public MessageDTO(long chatId, String messageId, String messageType,
			Long sender, long receiver, Date sendDate, String messageBody,
			String language, Long groupId, String fromUser) {
		super();
		this.chatId = chatId;
		this.messageId = messageId;
		this.messageType = messageType;
		this.sender = sender;
		this.receiver = receiver;
		this.sendDate = sendDate;
		this.messageBody = messageBody;
		this.language = language;
		this.groupId = groupId;
		this.fromUser = fromUser;
	}

	public MessageDTO(IMessageDTO messageDTO) {
		setChatId(messageDTO.getChatId());
		setFromUser(messageDTO.getFromUser());
		setGroupId(messageDTO.getGroupId());
		setLanguage(messageDTO.getLanguage());
		setMessageBody(messageDTO.getMessageBody());
		setMessageId(messageDTO.getMessageId());
		setMessageType(messageDTO.getMessageType());
		setReceiver(messageDTO.getReceiver());
		setReceiverName(messageDTO.getReceiverName());
		setSendDate(messageDTO.getSendDate());
		setSender(messageDTO.getSender());
		setStatus(messageDTO.getStatus());
		setShowed(messageDTO.isShowed());
		setConfirmed(messageDTO.isConfirmed());
	}

	@Override
	public long getChatId() {
		return chatId;
	}

	@Override
	public void setChatId(long chatId) {
		this.chatId = chatId;
	}

	@Override
	public String getMessageId() {
		return messageId;
	}

	@Override
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	@Override
	public String getMessageType() {
		return messageType;
	}

	@Override
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	@Override
	public boolean isOfflineMessage() {
		return offlineMessage;
	}

	@Override
	public void setOfflineMessage(boolean isOfflineMessage) {
		this.offlineMessage = isOfflineMessage;
	}

	@Override
	public Long getSender() {
		return sender;
	}

	@Override
	public void setSender(Long sender) {
		this.sender = sender;
	}

	@Override
	public long getReceiver() {
		return receiver;
	}

	@Override
	public void setReceiver(long receiver) {
		this.receiver = receiver;
	}

	@Override
	public Date getSendDate() {
		return sendDate;
	}

	@Override
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	@Override
	public String getMessageBody() {
		return messageBody;
	}

	@Override
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	@Override
	public String getLanguage() {
		return language;
	}

	@Override
	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public Long getGroupId() {
		return groupId;
	}

	@Override
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Override
	public String getFromUser() {
		return fromUser;
	}

	@Override
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getReceiverName() {
		return receiverName;
	}

	@Override
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	@Override
	public boolean isShowed() {
		return showed;
	}

	@Override
	public void setShowed(boolean showed) {
		this.showed = showed;
	}

	@Override
	public boolean isConfirmed() {
		return confirmed;
	}

	/**
	 * @param confirmed
	 *            the confirmed to set
	 */
	@Override
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	@Override
	public boolean isDisplaying() {
		return displaying;
	}

	@Override
	public void setDisplaying(boolean displaying) {
		this.displaying = displaying;
	}

	@Override
	public String toString() {
		return "MessageDTO [messageType=" + messageType + ", sender=" + sender
				+ ", receiver=" + receiver + ", sendDate=" + sendDate
				+ ", messageBody=" + messageBody + ", receiverName="
				+ receiverName + ", status=" + status + "]";
	}

}
