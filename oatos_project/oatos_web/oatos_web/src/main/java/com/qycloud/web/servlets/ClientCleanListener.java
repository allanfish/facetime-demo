package com.qycloud.web.servlets;

import static com.conlect.oatos.utils.SysLogger.osLogger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.conlect.oatos.dto.autobean.IMessageDTO;
import com.conlect.oatos.dto.client.ClientToken;
import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.client.SimpleUserInfoDTO;
import com.conlect.oatos.dto.client.SimpleUserInfosDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.MessageType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.utils.SimpleCallback;
import com.qycloud.web.MQMessageService;
import com.qycloud.web.ServiceUtil;
import com.qycloud.web.activemq.AjaxWebClient;
import com.qycloud.web.activemq.AmqContainer;

/**
 * 清理过时的web客户端连接
 * 
 * @author yufei
 *
 */
public class ClientCleanListener {

	/**
	 * 监听器延迟时间, 单位ms.
	 */
	private static final long TIMER_DELAY = 60000;
	/**
	 *  监听器执行间隔, 单位ms
	 */
	private static final long TIMER_PERIOD = 300000;

	private ScheduledExecutorService scheduledExecutor;
	private static ClientCleanListener instance = null;

	private ClientCleanListener() {
	}

	public static final ClientCleanListener get() {
		if (instance == null)
			instance = new ClientCleanListener();
		return instance;
	}

	public void destory() {
		if (scheduledExecutor != null)
			scheduledExecutor.shutdown();
	}

	public void execute() {
		scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutor.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				osLogger.info("[start]-[execute]-[ClientCleanListener]- clean expired web client!");
				final HashMap<String, AjaxWebClient> ajaxWebClients = AmqContainer.getAjaxWebClients();
				osLogger.info("ajax web client total:" + ajaxWebClients.size());
				StringBuilder expiredCount= new StringBuilder("");
				for (final Entry<String, AjaxWebClient> entry : ajaxWebClients.entrySet()) {
					final AjaxWebClient client = entry.getValue();
					// close an expired client and remove it from the ajaxWebClients hash.
					if (!client.isExpired()) {
						continue;
					}

					ClientToken token = new ClientToken(client.getToken());
					expiredCount.append(token.getUserId()).append(",");
					osLogger.debug("Removing expired AjaxWebClient: " + entry.getKey());
					synchronized (ajaxWebClients) {
						if (client.getToken() != null) {
							Executors.newSingleThreadExecutor().execute(new SignOutThread(token, new SimpleCallback() {

								@Override
								public void execute() {
									ajaxWebClients.remove(entry.getKey());
									client.close();
								}
							}));
						}
					}
				}
				if(expiredCount.length() > 0) {
					expiredCount.replace(expiredCount.length()-1, expiredCount.length(), "");
					osLogger.info("remove these users while userid in ["+expiredCount.toString()+"]");
				} 
				osLogger.info("[end]-[execute]-[ClientCleanListener]");
			}

		}, TIMER_DELAY, TIMER_PERIOD, TimeUnit.MILLISECONDS);
	}

	/**
	 * 用户退出系统线程
	 */
	private static class SignOutThread implements Runnable {
		private ClientToken clientToken;
		private SimpleCallback callback;

		public SignOutThread(ClientToken token, SimpleCallback callback) {
			this.clientToken = token;
			this.callback = callback;
		}

		@Override
		public void run() {
			String result = ServiceUtil.logout(clientToken.getUserIdAndToken(), clientToken.getUserIdAsString());
			if (!CommConstants.OK_MARK.equals(result)) {
				osLogger.info("[SignoutThread] - force user logout fail!");
				return;
			}
			try {
				String usersJson = ServiceUtil.invoke(clientToken.getUserIdAndToken(), RESTurl.getOnlineBuddyAndColleague,
						String.valueOf(clientToken.getUserId()));
				SimpleUserInfosDTO userInfosDTO = PojoMapper.fromJsonAsObject(usersJson, SimpleUserInfosDTO.class);
				if (userInfosDTO != null) {
					List<SimpleUserInfoDTO> userInfos = userInfosDTO.getUserList();
					if (userInfos != null) {
						/**
						 * send message(type : UserLeave) inform user's
						 * buddies and colleagues
						 */
						// setup message
						IMessageDTO messageDTO = new MessageDTO();
						messageDTO.setSender(clientToken.getUserId());
						messageDTO.setMessageType(MessageType.UserLeave);
						messageDTO.setSendDate(new Date());
						for (SimpleUserInfoDTO userInfo : userInfos) {
							messageDTO.setReceiver(userInfo.getUserId());
							// send message
							MQMessageService.getInstance().sendMessage(messageDTO);
						}
					}
				}
				osLogger.info("[SignoutThread] - force user logout success!");
				callback.execute();
			} catch (Exception e) {
				osLogger.info("[SignoutThread] - force user logout fail!");
				osLogger.warn(e);
			}
		}
	}

	private static interface SimpleCallback {
		void execute();
	}
}
