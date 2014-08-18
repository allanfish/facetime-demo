package com.qycloud.web.servlets;

import static com.conlect.oatos.utils.SysLogger.osLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.codehaus.jackson.type.TypeReference;

import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.client.MessagesDTO;
import com.conlect.oatos.dto.client.SysMsgDTO;
import com.conlect.oatos.dto.client.UserInfoCategoryDTO;
import com.conlect.oatos.dto.status.MessageStatus;
import com.conlect.oatos.dto.status.MessageType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.conlect.oatos.utils.SysLogger;
import com.qycloud.web.MQMessageService;
import com.qycloud.web.utils.ConfigUtil;

/**
 * 发送系统消息监听者
 * 
 * @author YUFEI
 * @created 2012-10-25
 * @IDE eclipse
 */
public class SysMsgListener {

	/**
	 * 是否允许执行发送系统消息的定时器Key
	 */
	public static final String KEY_SYS_MSG_TIMER_ALLOW = "SysMsgTimerAllow";
	/**
	 * 发送系统消息定时执行时间间隔Key
	 */
	public static final String KEY_SYS_MSG_TIMER_PERIOD = "SysMsgTimerPeriod";

	/**
	 * 发送系统消息定时执行时间间隔
	 */
	private final int SYS_MSG_TIMER_PERIOD;
	/**
	 * 是否允许执行发送系统消息的定时器
	 */
	private final boolean SYS_MSG_TIMER_ALLOW;

	private ScheduledExecutorService scheduledExecutor;
	private static SysMsgListener instance = null;

	private SysMsgListener() {
		boolean sysMsgTimerAllow = false;
		int sysMsgTimerPeriod = 1800;
		try {
			sysMsgTimerAllow = Boolean.parseBoolean(ConfigUtil.getValue(KEY_SYS_MSG_TIMER_ALLOW));
			sysMsgTimerPeriod = Integer.parseInt(ConfigUtil.getValue(KEY_SYS_MSG_TIMER_PERIOD));
		} catch (Exception e) {
			sysMsgTimerAllow = false;
			sysMsgTimerPeriod = 1800;
		}
		SYS_MSG_TIMER_ALLOW = sysMsgTimerAllow;
		SYS_MSG_TIMER_PERIOD = sysMsgTimerPeriod;
	}

	public static final SysMsgListener get() {
		if (instance == null)
			instance = new SysMsgListener();
		return instance;
	}

	public void destory() {
		if (scheduledExecutor != null) {
			scheduledExecutor.shutdown();
		}
	}

	private static class MQMessageSendThread implements Runnable {

		private List<MessageDTO> messages;
		private boolean isOnline = false;

		public MQMessageSendThread(List<MessageDTO> messages, boolean isOnline) {
			this.messages = messages;
			this.isOnline = isOnline;
		}

		@Override
		public void run() {
			if (isOnline) {
				for (MessageDTO message : messages) {
					MQMessageService.getInstance().sendMessage(null, message, false);
				}
				SysLogger.osLogger.info("send " + messages.size() + " sys msgs to MQ !");
			}
			String sysMsgs = PojoMapper.toJson(new MessagesDTO(messages));
			XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.saveSysMsgs), null, sysMsgs);
			SysLogger.osLogger.info("save " + messages.size() + " sys msgs to database !");
		}
	}

	private List<MessageDTO> getMessages(List<Long> userIds, List<SysMsgDTO> msgList, boolean isOnline) {
		List<MessageDTO> result = new ArrayList<MessageDTO>();
		for (Long userId : userIds) {
			for (SysMsgDTO sysMsg : msgList) {
				if (sysMsg.getSendDate().getTime() < System.currentTimeMillis()
						|| sysMsg.getSendDate().getTime() - System.currentTimeMillis() < SYS_MSG_TIMER_PERIOD * 1000) {
					MessageDTO messageDTO = new MessageDTO();
					messageDTO.setSender(0L);
					messageDTO.setReceiver(userId);
					messageDTO.setReceiverName(" ");
					messageDTO.setFromUser("OATOS");
					messageDTO.setSendDate(sysMsg.getSendDate());
					messageDTO.setMessageType(sysMsg.getMessageType());
					messageDTO.setMessageBody(sysMsg.getContent());
					messageDTO.setStatus(isOnline ? MessageStatus.Read : MessageStatus.New);
					result.add(messageDTO);

					if (sysMsg.getMessageType().equals(MessageType.SystemVideoMsg)) {
						String fileName = sysMsg.getContent();
						// TODO copy system files
						//						File sysDir = FileUtil.getSystemPath();
						//						if (sysDir != null)
						//							FileService.getInstance().copySystemFile(
						//									userId,
						//									new File(sysDir, CommonUtil.string2ASCII(CommonUtil.getFilePrefixName(fileName)) + "."
						//											+ CommonUtil.getFileSuffixName(fileName)));
					}
				}
			}
		}
		return result;
	}

	private UserInfoCategoryDTO getAllUserIds() {
		try {
			String result = XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.getAllUserIds), "", "");
			return PojoMapper.fromJsonAsObject(result, UserInfoCategoryDTO.class);
		} catch (Exception e) {
			SysLogger.osLogger.error("getAllUserIds error!", e);
		}
		return null;
	}

	public void execute() {

		if (!SYS_MSG_TIMER_ALLOW) {
			return;
		}

		scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutor.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				osLogger.info("[start]-[execute]-[SysMsgListener] - send systen msg to user!");
				try {
					String result = XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.getUnsendSysMsg), "",
							"");
					List<SysMsgDTO> msgList = PojoMapper.fromJsonAsArray(result, new TypeReference<List<SysMsgDTO>>() {
					});
					if (msgList != null && msgList.size() > 0) {
						SysLogger.osLogger.info("getUnsendSysMsg size:" + msgList.size());
						sendSysMsg(msgList);
					} else {
						SysLogger.osLogger.info("getUnsendSysMsg msgList is null.");
					}
				} catch (Exception e) {
					SysLogger.osLogger.error("SysMsgListener execute error!", e);
				}
				osLogger.info("[end]-[execute]-[SysMsgListener]");
			}

		}, SYS_MSG_TIMER_PERIOD, SYS_MSG_TIMER_PERIOD, TimeUnit.SECONDS);
	}

	/**
	 * 发送系统消息
	 * 
	 * @param msgList
	 */
	private void sendSysMsg(List<SysMsgDTO> msgList) {
		UserInfoCategoryDTO userIds = getAllUserIds();
		if (userIds != null) {
			List<Long> onlineUserIds = userIds.getOnlineUserIds();
			List<MessageDTO> onlineMsgs = getMessages(onlineUserIds, msgList, true);
			Executors.newSingleThreadExecutor().execute(new MQMessageSendThread(onlineMsgs, true));
			SysLogger.osLogger.info("onlineUserIds size:" + onlineUserIds.size() + ", onlineMsgs size:"
					+ onlineMsgs.size());

			List<Long> offlineUserIds = userIds.getOfflineUserIds();
			List<MessageDTO> offlineMsgs = getMessages(offlineUserIds, msgList, false);
			Executors.newSingleThreadExecutor().execute(new MQMessageSendThread(offlineMsgs, false));
			SysLogger.osLogger.info("offlineUserIds size:" + offlineUserIds.size() + ", offlineMsgs size:"
					+ offlineMsgs.size());
		}
	}
}
