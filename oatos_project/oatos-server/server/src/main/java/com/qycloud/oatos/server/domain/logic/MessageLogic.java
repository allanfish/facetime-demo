/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package com.qycloud.oatos.server.domain.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conlect.oatos.dto.autobean.IShareFileRecordDTO;
import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.client.MessagesDTO;
import com.conlect.oatos.dto.client.ShareFileRecordDTO;
import com.conlect.oatos.dto.client.SysMsgDTO;
import com.conlect.oatos.dto.client.UserDeviceTokenDTO;
import com.conlect.oatos.dto.client.UserDeviceTokensDTO;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.MessageStatus;
import com.conlect.oatos.dto.status.MessageType;
import com.conlect.oatos.dto.status.UserStatus;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.dao.ConferenceMapper;
import com.qycloud.oatos.server.dao.LoginLogMapper;
import com.qycloud.oatos.server.dao.MessageMapper;
import com.qycloud.oatos.server.dao.ShareFileHistoryMapper;
import com.qycloud.oatos.server.dao.ShareFileRecordMapper;
import com.qycloud.oatos.server.dao.ShareFolderHistoryMapper;
import com.qycloud.oatos.server.domain.entity.ConferenceMember;
import com.qycloud.oatos.server.domain.entity.LoginLog;
import com.qycloud.oatos.server.domain.entity.Message;
import com.qycloud.oatos.server.domain.entity.ShareFileHistory;
import com.qycloud.oatos.server.domain.entity.ShareFileRecord;
import com.qycloud.oatos.server.domain.entity.ShareFolderHistory;
import com.qycloud.oatos.server.domain.entity.UserDeviceToken;
import com.qycloud.oatos.server.domain.model.ChatGroupModel;
import com.qycloud.oatos.server.logic.task.MessageTask;
import com.qycloud.oatos.server.util.CacheClient;
import com.qycloud.oatos.server.util.CacheKey;

/**
 * 消息服务
 * @author yang
 *
 */
@Service("MessageLogic")
public class MessageLogic {

	// 消息数据持久层对象
	@Autowired
	private MessageMapper messageMapper;

	@Autowired
	private MessageTask messageTask;

	@Autowired
	private LoginLogMapper loginLogMapper;

	@Autowired
	private ConferenceMapper conferenceMapper;

	@Autowired
	private ShareFileRecordMapper shareFileRecordMapper;

	@Autowired
	private ShareFileHistoryMapper shareFileHistoryMapper;

	@Autowired
	private ShareFolderHistoryMapper shareFolderHistoryMapper;

	@Autowired
	private UserLogic userLogic;

	private Date getMonthBeforeDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long now = cal.getTimeInMillis();
		long t = now - 30 * 24 * 60 * 60 * 1000L;
		return new Date(t);
	}

	/**
	 * 获取某用户下的消息(用于提示显示有未读消息)
	 * 
	 * @param userId
	 * @return
	 */
	public List<Message> getUnreadMessageByUserId(long userId) throws DataAccessException {
		Message messageModel = new Message();
		messageModel.setReceiverId(userId);
		messageModel.setStatus(MessageStatus.New);

		List<Message> msgList = new ArrayList<Message>();
		
		// get conference invite
		List<ConferenceMember> cons = conferenceMapper.getUnconfirmedConferenceByUserId(userId);
		if (cons != null) {
	        for (ConferenceMember c : cons) {
	        	Message m = new Message();
	        	m.setMessageId(c.getConferenceId());
	        	m.setMessageType(MessageType.ConferenceInvite);
	        	if (c.getInviteUserId() != null) {
	        		m.setSenderId(c.getInviteUserId());
	        		m.setSender(c.getInviteUserName());
                } else {
					m.setSenderId(c.getConferenceId());
				}
	        	m.setReceiverId(userId);
	        	m.setMessageBody(String.valueOf(c.getConferenceId()));
	        	if (c.getInviteTime() != null) {
	                m.setSendTime(c.getInviteTime());
                } else {
					m.setSendTime(new Date());
				}
	        	msgList.add(m);
            }
        }

		msgList.addAll(messageTask.getUnreadMessage(userId));

		return msgList;
	}

	/**
	 * 取消息记录
	 */
	public List<Message> getMessageRecord(long userId) throws DataAccessException {
		List<Message> msgRecord = new ArrayList<Message>();

		// system message
		Message buddyInviteMsg = new Message();
		buddyInviteMsg.setReceiverId(userId);
		buddyInviteMsg.setMessageType(MessageType.SystemMsg);
		Collection<Message> buddyInviteRecord = getSystemMessageRecordByReceiverAndType(buddyInviteMsg);
		msgRecord.addAll(buddyInviteRecord);
		
		buddyInviteMsg.setReceiverId(userId);
		buddyInviteMsg.setMessageType(MessageType.SystemVideoMsg);
		msgRecord.addAll(getSystemMessageRecordByReceiverAndType(buddyInviteMsg));

		// chat message
		Message recordMsg = new Message();
		recordMsg.setSenderId(userId);
		recordMsg.setReceiverId(userId);
		recordMsg.setSendTime(getMonthBeforeDate());

		// video record
		recordMsg.setMessageType(MessageType.VideoInvite);
		Collection<Message> videoInviteRecord = getChatRecordByType(recordMsg);
		msgRecord.addAll(videoInviteRecord);

		// audio record
		recordMsg.setMessageType(MessageType.AudioInvite);
		Collection<Message> audioInviteRecord = getChatRecordByType(recordMsg);
		msgRecord.addAll(audioInviteRecord);

		// voice record
		recordMsg.setMessageType(MessageType.VoiceMail);
		List<Message> voiceMsg = messageMapper.getChatRecordByReceiverAndType(userId, MessageType.VoiceMail);
		msgRecord.addAll(voiceMsg);

		// chat record
		recordMsg.setMessageType(MessageType.ChatMessage);
		Collection<Message> chatRecord = getChatRecordByReceiverAndType(recordMsg);
		msgRecord.addAll(chatRecord);

		return msgRecord;
	}

	/**
	 * 接收到消息后需要确定的消息
	 * 
	 * @param messageModel
	 *            消息包
	 * @throws Exception
	 */
	@Transactional
	public void confirmMessage(Message messageModel) {
		if (messageModel.getMessageType() != null && CommonUtil.isSystemMsg(messageModel.getMessageType())) {
			// TODO
		}
		else {
			messageTask.updateChatMessageStatusToRead(messageModel);
		}

	}

	/**
	 * 发送消息
	 * 
	 * @param messageModel
	 * @throws Exception
	 */
	public void sendMessage(Message messageModel) {

		String type = messageModel.getMessageType();
		if (CommonUtil.isSystemMsg(type)) {
			// TODO
		}
		else if (CommonUtil.isSaveMessage(type)) {
			// chat message
			if (MessageType.InstantFile.equals(type) || MessageType.VideoInvite.equals(type)
			        || MessageType.VideoStart.equals(type) || MessageType.VideoRefuse.equals(type)
			        || MessageType.VideoEnd.equals(type) || MessageType.AudioInvite.equals(type)
			        || MessageType.AudioStart.equals(type) || MessageType.AudioRefuse.equals(type)
			        || MessageType.AudioEnd.equals(type)) {
				messageModel.setStatus(MessageStatus.Read);
			}
			messageTask.loadMessage(messageModel);
		}
	}

	@Transactional
	public void saveMessages(List<MessageDTO> messageList) {
		for (MessageDTO msg : messageList) {
			sendMessage(new Message(msg));
		}
	}

	/**
	 * 取聊天历史记录
	 */
	public MessagesDTO getChatHistory(long userId, long buddyUserId, int skipResults, int maxResults) {

		long count = messageMapper.getChatHistoryCount(userId, buddyUserId);

		if ((skipResults < 0) || (skipResults >= count)) {
			int pageNo = (int) ((count - 1) / maxResults + 1);
			skipResults = maxResults * (pageNo - 1);
		}

		List<Message> messageList = messageMapper.getChatHistory(userId, buddyUserId, skipResults, maxResults);
		List<MessageDTO> messageDtoList = new ArrayList<MessageDTO>();

		for (Message msg : messageList) {
			messageDtoList.add(msg.toMessageDTO());
		}

		MessagesDTO messagesDTO = new MessagesDTO();
		messagesDTO.setMessageDTOList(messageDtoList);
		messagesDTO.setCount(count);

		return messagesDTO;
	}
	
	public List<Message> getEntRecord(Message messageModel) {
		messageModel.setSendTime(getMonthBeforeDate());

		List<Message> msgList = new ArrayList<Message>();
		if (MessageType.Login.equals(messageModel.getMessageType())) {
			LoginLog loginLog = new LoginLog();
			loginLog.setUserId(messageModel.getSenderId());
			loginLog.setTime(messageModel.getSendTime());
			List<LoginLog> loginLogs = loginLogMapper.getLoginLog(loginLog);
			for (LoginLog log : loginLogs) {
				Message msg = new Message();
				msg.setMessageType(MessageType.Login);
				msg.setMessageId(log.getId());
				msg.setSenderId(log.getUserId());
				msg.setMessageBody(log.getIp());
				msg.setSendTime(log.getTime());
				msgList.add(msg);
			}
		}
		else if (MessageType.ChatMessage.equals(messageModel.getMessageType())) {
			msgList.addAll(getChatRecordByType(messageModel));
		}
		else if (MessageType.VideoInvite.equals(messageModel.getMessageType())
		        || MessageType.AudioInvite.equals(messageModel.getMessageType())) {
			msgList = messageMapper.getChatRecordByType(messageModel.getSenderId(), messageModel.getReceiverId(), messageModel.getMessageType());
		}
		else if (MessageType.VoiceMail.equals(messageModel.getMessageType())) {
			msgList = messageMapper.getChatRecordByType(messageModel.getSenderId(), messageModel.getReceiverId(), messageModel.getMessageType());
		}
		else if (MessageType.FileDown.equals(messageModel.getMessageType())
				|| MessageType.fileAccessRecord.equals(messageModel.getMessageType())) {
			// 取文件浏览，下载记录
			List<ShareFileRecord> records = shareFileRecordMapper.getRecordByUserIdAndType(messageModel.getSenderId(), messageModel.getMessageType());
			for (ShareFileRecord r : records) {
				Message msg = new Message();
				msg.setMessageId(r.getRecordId());
				msg.setMessageType(messageModel.getMessageType());
				msg.setSendTime(r.getAccessTime());
				msg.setSenderId(messageModel.getSenderId());
				msg.setMessageBody(PojoMapper.toJson(r.toRecordDTO()));
				msgList.add(msg);
            }
			if (MessageType.fileAccessRecord.equals(messageModel.getMessageType())) {
				// 取文件操作记录
	            List<ShareFileHistory> hs = shareFileHistoryMapper.getShareFileHistoryByUserId(messageModel.getSenderId());
	            for (ShareFileHistory h : hs) {
					Message msg = new Message();
					msg.setMessageId(h.getVersion());
					msg.setMessageType(messageModel.getMessageType());
					msg.setSendTime(h.getUpdateTime());
					msg.setSenderId(messageModel.getSenderId());
					IShareFileRecordDTO r = new ShareFileRecordDTO();
					r.setRecordId(h.getVersion());
					r.setFileId(h.getFileId());
					r.setName(h.getName());
					r.setRecordType(h.getOperation());
					r.setAccessTime(h.getUpdateTime());
					r.setFolderId(h.getFolderId());
					r.setUserId(h.getUserId());
					msg.setMessageBody(PojoMapper.toJson(r));
					msgList.add(msg);
                }
	            // 取文件夹操作记录
	            List<ShareFolderHistory> fs = shareFolderHistoryMapper.getShareFolderHistoryByUserId(messageModel.getSenderId());
	            for (ShareFolderHistory h : fs) {
	            	Message msg = new Message();
					msg.setMessageId(h.getVersion());
					msg.setMessageType(messageModel.getMessageType());
					msg.setSendTime(h.getUpdateTime());
					msg.setSenderId(messageModel.getSenderId());
					IShareFileRecordDTO r = new ShareFileRecordDTO();
					r.setRecordId(h.getVersion());
					r.setFolderId(h.getFolderId());
					r.setName(h.getName());
					r.setRecordType(h.getOperation());
					r.setAccessTime(h.getUpdateTime());
					r.setUserId(h.getUserId());
					msg.setMessageBody(PojoMapper.toJson(r));
					msgList.add(msg);
                }
            }
		}
		return msgList;
	}

	/**
	 * 发送群消息
	 * 
	 */
	public void sendGroupMessage(Message messageModel) {

		// 从缓存中获取群
		String key = CacheKey.Group + String.valueOf(messageModel.getReceiverId());

		ChatGroupModel chatModel = (ChatGroupModel) CacheClient.getInstance().get(key);

		if (chatModel != null) {
			// TODO
			// messageModel.setToUserIds(chatModel.getUsers());


			// 启用专用线程保存消息
			messageTask.loadMessage(messageModel);
		}
	}

	/**
	 * get chat record by type
	 * 
	 * @param messageModel
	 * @return
	 */
	private Collection<Message> getChatRecordByType(Message messageModel) {
		List<Message> chatList = messageMapper.getChatRecordByType(messageModel.getSenderId(), messageModel.getReceiverId(), messageModel.getMessageType());
		Map<Long, Message> msgMap = new HashMap<Long, Message>();
		for (Message m : chatList) {
			long userId;
			if (m.getSenderId().longValue() == messageModel.getSenderId().longValue()) {
				userId = m.getReceiverId();
			}
			else {
				userId = m.getSenderId();
			}
			Message v = msgMap.get(userId);
			if (v == null || m.getSendTime().compareTo(v.getSendTime()) > 0) {
				msgMap.put(userId, m);
			}
		}
		return msgMap.values();
	}

	/**
	 * get chat record by receiver and type
	 * 
	 * @param messageModel
	 * @return
	 */
	private Collection<Message> getChatRecordByReceiverAndType(Message messageModel) {
		List<Message> chatList = messageMapper.getChatRecordByReceiverAndType(messageModel.getReceiverId(), messageModel.getMessageType());
		Map<Long, Message> msgMap = new HashMap<Long, Message>();
		for (Message m : chatList) {
			long userId = m.getSenderId();
			Message v = msgMap.get(userId);
			if (v == null || m.getSendTime().compareTo(v.getSendTime()) > 0) {
				msgMap.put(userId, m);
			}
		}
		return msgMap.values();
	}

	/**
	 * get system message record by receiver and type
	 * 
	 * @param messageModel
	 * @return
	 */
	private List<Message> getSystemMessageRecordByReceiverAndType(Message messageModel) {
		// TODO
		List<Message> chatList = new ArrayList<Message>();
		return chatList;
	}

    public int isExistUnreadMessage(long userId) {
    	// chat message
		int e = messageTask.isExistUnreadMessage(userId);
		// conference invite
		List<ConferenceMember> cons = conferenceMapper.getUnconfirmedConferenceByUserId(userId);
	    if (cons != null && cons.size() > 0) {
            e = e + cons.size();
        }
		return e;
    }

	public List<SysMsgDTO> getUnsendSysMsgs() {
		// TODO
		return null;
	}

	public void saveSysMsgs(MessagesDTO sysMsgs) {
		// TODO

	}

	/**
	 * 获取有离线消息用户的ios设备标志
	 * @return
	 */
	public UserDeviceTokensDTO getUserDeviceTokensHasUnreadMsg() {
		List<UserDeviceToken> users = messageMapper.getUserDeviceTokensHasUnreadMsg();
		
		List<UserDeviceTokenDTO> tokenDTOs = new ArrayList<UserDeviceTokenDTO>();
		for (UserDeviceToken u : users) {
			String status = userLogic.getUserStatus(u.getUserId());
			// 将离线用户加入列表
			if (UserStatus.Logout.equals(status) || UserStatus.Offline.equals(status)) {
				tokenDTOs.add(u.toUserDeviceTokenDTO());
			}
		}
		UserDeviceTokensDTO tokensDTO = new UserDeviceTokensDTO();
		tokensDTO.setUserDeviceTokenList(tokenDTOs);
		return tokensDTO;
	}
}
