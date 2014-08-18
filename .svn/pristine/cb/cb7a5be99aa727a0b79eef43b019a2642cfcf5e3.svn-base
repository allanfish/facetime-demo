/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.conlect.oatos.dto.autobean.IMessageDTO;
import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.status.MessageStatus;

/**
 * 消息
 * @author yang
 *
 */
public class Message implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	/**
	 * 信息ID
	 */
	private long messageId;
	/**
	 * 消息类型<br>
	 * {@link com.conlect.oatos.dto.status.MessageType}
	 */
	private String messageType;
	/**
	 * 信息发送者ID
	 */
	private Long senderId;
	/**
	 * 发送者名称
	 */
	private String sender;

	/**
	 * 信息内容
	 */
	private String messageBody;

	/**
	 * 消息接收者姓名
	 */
	private String receiver;
	/**
	 * 信息接受者ID(当是群的时候，接受者为群里接收者的ID)
	 */
	private long receiverId;

	/**
	 * 当是群消息时，群ID
	 */
	private Long groupId;

	/**
	 * 信息发送时间
	 */
	private Date sendTime;

	/**
	 * 消息状态<br>
	 * {@link com.conlect.oatos.dto.status.MessageStatus}
	 */
	private String status = MessageStatus.New;

	public Message() {
	}

	/**
	 * 
	 * @param messageDTO
	 */
	public Message(IMessageDTO messageDTO) {
		try {
			this.messageId = Long.parseLong(messageDTO.getMessageId());
		} catch (Exception e) {
		}
		this.messageType = messageDTO.getMessageType();
		this.senderId = messageDTO.getSender();
		this.sender = messageDTO.getFromUser();
		this.messageBody = messageDTO.getMessageBody();
		this.receiverId = messageDTO.getReceiver();
		this.receiver = messageDTO.getReceiverName();
		this.sendTime = messageDTO.getSendDate();
		this.status = messageDTO.getStatus();

		this.groupId = messageDTO.getGroupId();
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long chatMessageId) {
		this.messageId = chatMessageId;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(long receiver) {
		this.receiverId = receiver;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSender() {
		return sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	/**
	 * convert to MessageDTO
	 * 
	 * @return
	 */
	public MessageDTO toMessageDTO() {
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setMessageId(String.valueOf(this.messageId));
		messageDTO.setMessageType(this.messageType);
		messageDTO.setSender(this.senderId);
		messageDTO.setFromUser(this.sender);
		messageDTO.setMessageBody(this.messageBody);
		messageDTO.setReceiver(this.receiverId);
		messageDTO.setReceiverName(this.receiver);
		messageDTO.setSendDate(this.sendTime);
		messageDTO.setStatus(this.status);
		messageDTO.setGroupId(this.groupId);

		return messageDTO;
	}

}
