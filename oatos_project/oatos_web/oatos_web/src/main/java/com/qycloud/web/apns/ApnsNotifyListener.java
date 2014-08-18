package com.qycloud.web.apns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.conlect.oatos.dto.client.UserDeviceTokenDTO;
import com.conlect.oatos.dto.client.UserDeviceTokensDTO;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.conlect.oatos.utils.SysLogger;
import com.qycloud.web.utils.ConfigUtil;

/**
 * iOS设备离线消息通知监听器
 * 
 * @author Allan
 *
 */
public class ApnsNotifyListener {

	/**指定频率, 2h. 单位为毫秒 */
	private static final int FIX_RATE = 7200000;
	/**延迟时间, 2h. 单位为毫秒*/
	private static final int INIT_DELAY = 7200000;
	private static final String TEST_DEVICE_TOKEN = "58af3fe9 3431247d 9132fe4a b7356c87 b1905713 0c5271f1 16551709 328b1e33";
	private static final boolean isTestEnv = false;

	private static ApnsNotifyListener that;
	private ScheduledExecutorService scheduler;

	public static final ApnsNotifyListener get() {
		if (that == null) {
			that = new ApnsNotifyListener();
		}
		return that;
	}

	public void execute(final String identifyFile) {
		SysLogger.getOsLogger().info("[START] ApnsNotifyListener");
		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				SysLogger.getOsLogger().info("[START] Scan need send apns msg deviceToken");
				List<String> deviceTokens = isTestEnv ? Arrays.asList(new String[] { TEST_DEVICE_TOKEN })
						: getNeedNotifyDeviceTokens();
				SysLogger.getOsLogger().info("Have " + deviceTokens.size() + " need send apns notify msg!");
				if (deviceTokens.size() > 0) {
					ApnsMessageSender sender = new ApnsMessageSender(identifyFile);
					sender.send(deviceTokens, "您有离线消息, 请登录系统查收!");
				}
				SysLogger.getOsLogger().info("[END] send apns notify msg success !");
			}

		}, INIT_DELAY, FIX_RATE, TimeUnit.MILLISECONDS);
	}

	public void destory() {
		if (scheduler != null) {
			scheduler.shutdown();
			scheduler = null;
			that = null;
		}
	}

	private List<String> getNeedNotifyDeviceTokens() {
		List<String> tokenList = new ArrayList<String>(0);
		try {
			String result = XhrProxy.getInstance().post(
					ConfigUtil.getServiceUrl(RESTurl.getUserDeviceTokensHasUnreadMsg), "", "");
			UserDeviceTokensDTO deviceTokens = PojoMapper.fromJsonAsObject(result, UserDeviceTokensDTO.class);
			for (UserDeviceTokenDTO userDeviceToken : deviceTokens.getUserDeviceTokenList()) {
				tokenList.add(userDeviceToken.getDeviceToken());
			}
		} catch (Exception e) {
			SysLogger.getOsLogger().warn(RESTurl.getUserDeviceTokensHasUnreadMsg + " invoke FAIL!", e);
		}
		return tokenList;
	}
}
