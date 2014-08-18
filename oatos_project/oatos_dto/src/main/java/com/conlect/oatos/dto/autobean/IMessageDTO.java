package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.Date;

/**
 * Definition of message
 * 
 * @author jinkerjiang
 * 
 */
public interface IMessageDTO extends Serializable {

	/**
	 * Get chat id
	 * 
	 * @return chatId
	 */
	long getChatId();

	/**
	 * Set chat id
	 * 
	 * @param chatId
	 */
	void setChatId(long chatId);

	/**
	 * Get message id
	 * 
	 * @return message id
	 */
	String getMessageId();

	/**
	 * Set message id
	 * 
	 * @param messageId
	 */
	void setMessageId(String messageId);

	/**
	 * Get message type
	 * 
	 * @return {@link com.conlect.oatos.dto.status.MessageType}
	 */
	String getMessageType();

	/**
	 * Set message type
	 * 
	 * @param messageType
	 *            {@link com.conlect.oatos.dto.status.MessageType}
	 */
	void setMessageType(String messageType);

	/**
	 * Get message isOfflineMessage
	 * 
	 * @return true false
	 */
	boolean isOfflineMessage();

	/**
	 * set message isOfflineMessage
	 * 
	 * @param isOfflineMessage
	 */
	void setOfflineMessage(boolean isOfflineMessage);

	/**
	 * Get sender id
	 * 
	 * @return sender id
	 */
	Long getSender();

	/**
	 * Set sender id
	 * 
	 * @param sender
	 */
	void setSender(Long sender);

	/**
	 * Get receiver id
	 * 
	 * @return receiver id
	 */
	long getReceiver();

	/**
	 * Set receiver id
	 * 
	 * @param receiver
	 */
	void setReceiver(long receiver);

	/**
	 * Get send date
	 * 
	 * @return send date
	 */
	Date getSendDate();

	/**
	 * Set send date
	 * 
	 * @param sendDate
	 */
	void setSendDate(Date sendDate);

	/**
	 * Get message body
	 * 
	 * @return message body
	 */
	String getMessageBody();

	/**
	 * Set message body
	 * 
	 * @param messageBody
	 */
	void setMessageBody(String messageBody);

	/**
	 * Get language
	 * 
	 * @return language
	 */
	String getLanguage();

	/**
	 * Set language
	 * 
	 * @param language
	 */
	void setLanguage(String language);

	/**
	 * Get group id
	 * 
	 * @return group id
	 */
	Long getGroupId();

	/**
	 * Set group id
	 * 
	 * @param groupId
	 */
	void setGroupId(Long groupId);

	/**
	 * Get name of message sender
	 * 
	 * @return name of message sender
	 */
	String getFromUser();

	/**
	 * Set name of message sender
	 * 
	 * @param fromUser
	 */
	void setFromUser(String fromUser);

	/**
	 * Get receiver's name
	 * 
	 * @return receiver's name
	 */
	String getReceiverName();

	/**
	 * Set receiver's name
	 * 
	 * @param receiverName
	 */
	void setReceiverName(String receiverName);

	/**
	 * Get message's status
	 * 
	 * @return {@link com.conlect.oatos.dto.status.MessageStatus}
	 */
	String getStatus();

	/**
	 * Set message's status, value :
	 * {@link com.conlect.oatos.dto.status.MessageStatus}
	 * 
	 * @param status
	 */
	void setStatus(String status);

	/**
	 * If message showed
	 * 
	 * @return message showed
	 */
	boolean isShowed();

	/**
	 * Set message showed
	 * 
	 * @param showed
	 */
	void setShowed(boolean showed);

	/**
	 * If message confirmed
	 * 
	 * @return message confirmed
	 */
	boolean isConfirmed();

	/**
	 * Set message confirmed
	 * 
	 * @param confirmed
	 */
	void setConfirmed(boolean confirmed);

	/**
	 * If message is displaying
	 * 
	 * @return message is displaying
	 */
	boolean isDisplaying();

	/**
	 * Set message displaying status
	 * 
	 * @param displaying
	 */
	void setDisplaying(boolean displaying);
}