package com.qycloud.oatos.server.logic.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conlect.oatos.dto.status.MessageStatus;
import com.conlect.oatos.dto.status.MessageType;
import com.qycloud.oatos.server.dao.MessageMapper;
import com.qycloud.oatos.server.dao.UserMapper;
import com.qycloud.oatos.server.domain.entity.Message;
import com.qycloud.oatos.server.domain.logic.SequenceLogic;
import com.qycloud.oatos.server.util.BllLogger;

/**
 * message load task
 * @author yang
 *
 */
@Component("MessageTask")
public class MessageTask {

	@Autowired
	private MessageMapper messageDao;
	@Autowired
	private UserMapper userDao;
	@Autowired
	private SequenceLogic sequence;
	
	private static final Logger logger = BllLogger.getLogger();
	
	// 消息缓存大小 
	private final static int MAX_MSG_LIST_SIZE = 1000;

	// 线程安全的队列
	private static ConcurrentLinkedQueue<Message> queueMessage = new ConcurrentLinkedQueue<Message>();
	
	public void execute() {

		while (!queueMessage.isEmpty()) {
			synchronized (this) {
				List<Message> msgs = new ArrayList<Message>();
				for (int i = 0; i < MAX_MSG_LIST_SIZE; i++) {
					if (!queueMessage.isEmpty()) {
						Message m = queueMessage.poll();
						msgs.add(m);
					}
					else {
						break;
					}
				}
				if (msgs.size() > 0) {
					new ReadMessageQueueThread(msgs);
	            }
            }
		}
	}
	
	/**
	 * get unread messages
	 * @param userId
	 * @return
	 */
	public synchronized List<Message> getUnreadMessage(long userId) {
		List<Message> msgList = new ArrayList<Message>();
		
		Message chat = new Message();
		chat.setStatus(MessageStatus.New);
		msgList.addAll(messageDao.getUnreadMsgByReceiverId(userId));

		List<Message> tmpList = new ArrayList<Message>();
		Message m = null;
		while ((m = queueMessage.poll()) != null) {
			tmpList.add(m);
	        if (userId == m.getReceiverId()
	        		&& MessageStatus.New.equals(m.getStatus())) {
	            msgList.add(m);
            }
        }
		if (tmpList.size() > 0) {
			queueMessage.addAll(tmpList);
        }
		return msgList;
	}
	
	/**
	 * exist unread messages 
	 * @param userId
	 * @return
	 */
	public synchronized int isExistUnreadMessage(long userId) {

		int c = messageDao.getUnreadMsgCountByReceiverId(userId);
		
		List<Message> msgList = new ArrayList<Message>();
		Message m = null;
		while ((m = queueMessage.poll()) != null) {
			msgList.add(m);
	        if (userId == m.getReceiverId()
	        		&& MessageStatus.New.equals(m.getStatus())) {
	            c = c + 1;
            }
        }
		if (msgList.size() > 0) {
            queueMessage.addAll(msgList);
        }
		return c;
	}

	/**
	 * update status read
	 * @param messageModel
	 */
	public synchronized void updateChatMessageStatusToRead(Message message) {
		message.setStatus(MessageStatus.Read);
		
		messageDao.updateChatMsgRead(message.getSenderId(), message.getReceiverId(), message.getSendTime());

		List<Message> msgList = new ArrayList<Message>();
		Message msg = null;
		while ((msg = queueMessage.poll()) != null) {
			msgList.add(msg);
	        if (message.getReceiverId() == msg.getReceiverId()
	        		&& message.getSenderId().equals(msg.getSenderId())
	        		&& msg.getSendTime().compareTo(message.getSendTime()) <= 0
	        		&& (MessageType.ChatMessage.equals(msg.getMessageType())
	        				|| MessageType.VoiceMail.equals(msg.getMessageType())
	        				|| MessageType.InstantFile.equals(msg.getMessageType())
	        				|| MessageType.ShareFileInPrivacy.equals(msg.getMessageType())
	        				|| MessageType.OfflineFile.equals(msg.getMessageType()))) {
	        	msg.setStatus(MessageStatus.Read);
            }
        }
		if (msgList.size() > 0) {
            queueMessage.addAll(msgList);
        }
	}

	/**
	 * 将消息写入队列
	 * 
	 * @param message
	 */
	public synchronized void loadMessage(Message message) {
		// TODO
		if (message.getSender() == null && message.getSenderId() != null) {
	        message.setSender(userDao.getUser(message.getSenderId()).getUserName());
        }
		if (message.getReceiver() == null) {
	        message.setReceiver(String.valueOf(message.getReceiverId()));
        }
		queueMessage.add(message);
		if (queueMessage.size() >= MAX_MSG_LIST_SIZE) {
	        execute();
        }
	}
	
	/**
	 * 将消息写入队列
	 * 
	 * @param message
	 */
	public synchronized void loadMessage(List<Message> messageList) {
		// TODO
		for (Message message : messageList) {
			if (message.getSender() == null && message.getSenderId() != null) {
		        message.setSender(userDao.getUser(message.getSenderId()).getUserName());
	        }
			if (message.getReceiver() == null) {
		        message.setReceiver(String.valueOf(message.getReceiverId()));
	        }
        }
		queueMessage.addAll(messageList);
		if (queueMessage.size() >= MAX_MSG_LIST_SIZE) {
			execute();
        }
	}

	/**
	 * 
	 * @author yang
	 *
	 */
	private class ReadMessageQueueThread extends Thread {

		private List<Message> messageList;

		public ReadMessageQueueThread(List<Message> messageList) {
	        this.messageList = messageList;
	        start();
        }

		/**
		 * 每一秒最多插入sleepTime条记录,目前测试数据库的压力只承受每秒sleepTime条
		 */
		@Override
		public void run() {
			try {
				logger.info("start insert chat message into db: " + messageList.size());
				long start = System.currentTimeMillis();
				if (messageList.size() > 0) {
					for (Message msg : messageList) {
						msg.setMessageId(sequence.getNextId());
					}
					messageDao.insertMessages(messageList);
				}
				long cost = System.currentTimeMillis() - start;
				logger.info("insert chat message into db finish: " + messageList.size() + " lines, cost time: " + cost + "ms");
			}
			catch (Exception ex) {
              logger.error(ex.getMessage());
              loadMessage(messageList);
			}
		}

	}
}
