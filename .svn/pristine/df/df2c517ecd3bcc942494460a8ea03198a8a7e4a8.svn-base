package com.qycloud.oatos.server.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.GetHistoryDTO;
import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.client.MessagesDTO;
import com.conlect.oatos.dto.client.UserDeviceTokensDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.entity.Message;
import com.qycloud.oatos.server.domain.logic.MessageLogic;
import com.qycloud.oatos.server.util.BllLogger;

/**
 * 消息服务
 * @author yang
 *
 */
@Controller("MessageService")
public class MessageService {
	private final static Logger logger = BllLogger.getLogger();

	@Autowired
	private MessageLogic messageLogic;

	/**
	 * 取未读消息
	 * 
	 * @param response
	 * @param linkId
	 */
	@RequestMapping(value = RESTurl.getUnreadMessage, method = RequestMethod.POST)
	@ResponseBody
	public String getUnreadMessage(@RequestBody String requestBody) {
		String reBody = "";
		try {
			long userId = Long.parseLong(requestBody);
			List<Message> messageList = messageLogic.getUnreadMessageByUserId(userId);
			List<MessageDTO> messageDtoList = new ArrayList<MessageDTO>();
			for (Message message : messageList) {
				messageDtoList.add(message.toMessageDTO());
			}
			MessagesDTO messagesDTO = new MessagesDTO();
			messagesDTO.setMessageDTOList(messageDtoList);
			reBody = PojoMapper.toJson(messagesDTO);
		}
		catch (Exception ex)
		{
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 处理消息，设置消息状态为已读
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.confirmMessage, method = RequestMethod.POST)
	@ResponseBody
	public String confirmMessage(@RequestBody String requestBody) {
		String reBody = "";
		try {
			MessageDTO messageDTO = PojoMapper.fromJsonAsObject(requestBody, MessageDTO.class);
			Message messageModel = new Message(messageDTO);
			messageLogic.confirmMessage(messageModel);

			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 根据发送者及消息类型取消息记录
	 * 
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.getMessageRecord, method = RequestMethod.POST)
	public @ResponseBody
	String getMessageRecord(@RequestBody String requestBody) {
		String reBody = "";

		try {
			long userId = Long.parseLong(requestBody);

			List<Message> messageList = messageLogic.getMessageRecord(userId);
			List<MessageDTO> messageDtoList = new ArrayList<MessageDTO>();

			for (Message message : messageList) {
				messageDtoList.add(message.toMessageDTO());
			}

			MessagesDTO messagesDTO = new MessagesDTO();
			messagesDTO.setMessageDTOList(messageDtoList);
			messagesDTO.setCount(messageDtoList.size());
			reBody = PojoMapper.toJson(messagesDTO);

		}
		catch (Exception ex)
		{
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 保存消息至数据库
	 * 
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.sendMessage, method = RequestMethod.POST)
	@ResponseBody
	public String sendMessage(@RequestBody String requestBody) {
		String reBody = "";
		try {
			MessageDTO dto = PojoMapper.fromJsonAsObject(requestBody, MessageDTO.class);
			Message message = new Message(dto);
			messageLogic.sendMessage(message);

			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex)
		{
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 分页取聊天历史记录
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getChatHistory, method = RequestMethod.POST)
	public @ResponseBody
	String getChatHistory(@RequestBody String requestBody) {
		String reBody = "";
		try {
			// 分页获取聊天记录
			GetHistoryDTO historyDTO = PojoMapper.fromJsonAsObject(requestBody, GetHistoryDTO.class);
			long userId = historyDTO.getUserId();
			long buddyUserId = historyDTO.getBuddyUserId();
			int skipResults = historyDTO.getSkipResults();
			int maxResults = historyDTO.getMaxResults();

			MessagesDTO messagesDTO = messageLogic.getChatHistory(userId, buddyUserId, skipResults, maxResults);

			reBody = PojoMapper.toJson(messagesDTO);
		}
		catch (Exception ex)
		{
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 批量保存消息至数据库
	 * 
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.saveMessages, method = RequestMethod.POST)
	@ResponseBody
	public String saveMessages(@RequestBody String requestBody) {
		String reBody = "";
		try {
			MessagesDTO messagesDTO = PojoMapper.fromJsonAsObject(requestBody, MessagesDTO.class);

			messageLogic.saveMessages(messagesDTO.getMessageDTOList());

			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex)
		{
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 保存系统消息
	 * 
	 * @return
	 */
	@RequestMapping(value = RESTurl.saveSysMsgs, method = RequestMethod.POST)
	@ResponseBody
	public String saveSysMsgs(@RequestBody String strSysMsgs) {
		String reBody = "";
		try {
			MessagesDTO sysMsgs = PojoMapper.fromJsonAsObject(strSysMsgs, MessagesDTO.class);
			messageLogic.saveSysMsgs(sysMsgs);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex)
		{
			reBody = ErrorType.error500.name();
			logger.error(strSysMsgs, ex);
		}
		return reBody;
	}

	/**
	 * 是否可以发送离线文件
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = RESTurl.canSendOfflineFile, method = RequestMethod.POST)
	public @ResponseBody
	String canSendOfflineFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			reBody = String.valueOf(true);
		}
		catch (Exception ex)
		{
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 取企业信息记录
	 * 
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.getEntRecord, method = RequestMethod.POST)
	public @ResponseBody
	String getEntRecord(@RequestBody String requestBody) {
		String reBody = "";
		try {
			MessageDTO message = PojoMapper.fromJsonAsObject(requestBody, MessageDTO.class);

			List<Message> messageList = messageLogic.getEntRecord(new Message(message));
			List<MessageDTO> messageDtoList = new ArrayList<MessageDTO>();
			for (Message m : messageList) {
				messageDtoList.add(m.toMessageDTO());
			}
			MessagesDTO messagesDTO = new MessagesDTO();
			messagesDTO.setMessageDTOList(messageDtoList);
			messagesDTO.setCount(messageDtoList.size());
			reBody = PojoMapper.toJson(messagesDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 判断是否有未读消息
	 * 
	 * @param requestBody
	 */
	@RequestMapping(value = RESTurl.isExistUnreadMessage, method = RequestMethod.POST)
	@ResponseBody
	public String isExistUnreadMessage(@RequestBody String requestBody) {
		String reBody = "";
		try {
			long userId = Long.parseLong(requestBody);
			int c = messageLogic.isExistUnreadMessage(userId);
			reBody = String.valueOf(c);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 
	 * 返回未读的系统消息
	 */
	@RequestMapping(value = RESTurl.getUnsendSysMsg, method = RequestMethod.POST)
	@ResponseBody
	public String getUnsendSysMsg() {
		String reBody = "";
		try {
			reBody = PojoMapper.toJson(messageLogic.getUnsendSysMsgs());
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(reBody, ex);
		}
		return reBody;
	}

	/**
	 * @see RESTurl#getUserDeviceTokensHasUnreadMsg
	 */
	@RequestMapping(value = RESTurl.getUserDeviceTokensHasUnreadMsg, method = RequestMethod.POST)
	@ResponseBody
	public String getUserDeviceTokensHasUnreadMsg() {
		String reBody = "";
		try {
			UserDeviceTokensDTO tokensDTO = messageLogic.getUserDeviceTokensHasUnreadMsg();
			reBody = PojoMapper.toJson(tokensDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(reBody, ex);
		}
		return reBody;
	}

}
