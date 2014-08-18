package com.qycloud.web.activemq;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.activemq.MessageAvailableConsumer;

import com.conlect.oatos.dto.client.ClientToken;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.utils.ConfigUtil;
import com.qycloud.web.utils.Logs;

/**
 * Collection of all data needed to fulfill requests from a single web client.
 * 包装了Activemq信息的对象
 */
public class AjaxWebClient extends WebClient {
	private static final org.apache.log4j.Logger LOG = Logs.getLogger();
	private static XhrProxy proxy = XhrProxy.getInstance();
	private final static String serverId = UUID.randomUUID().toString();

	private static long index = 0;

	// an instance which has not been accessed in this many milliseconds can be removed.
	final long expireAfter = 60000;

	Map<MessageAvailableConsumer, String> consumerIdMap;
	Map<MessageAvailableConsumer, String> destinationNameMap;
	AjaxListener listener;
	Long lastAccessed;
	private String token;
	private String serverProxyId = serverId + "-" + (index++);
	
	public AjaxWebClient(HttpServletRequest request, long maximumReadTimeout) {
		// 'id' meaning the first argument to the JavaScript addListener() function.
		// used to indicate which JS callback should handle a given message.
		this.consumerIdMap = new HashMap<MessageAvailableConsumer, String>();
		
		// map consumers to destinations like topic://test, etc.
		this.destinationNameMap = new HashMap<MessageAvailableConsumer, String>();

		this.listener = new AjaxListener(this, maximumReadTimeout);

		this.lastAccessed = this.getNow();

		token = extractToken(request);
	}

	/**
	 * Get token from request, if exist return null
	 * 
	 * @param request
	 * @return
	 */
	public String extractToken(HttpServletRequest request) {
		String token = null;

		// get token from parameter
		token = request.getParameter(MyConst.COOKIE_USER_TOKEN);
		// from header
		if (token == null) {
			token = request.getHeader(MyConst.COOKIE_USER_TOKEN);
		}

		if (token == null) {
			token = request.getParameter(RESTurl.UserTokenkey);
		}

		if (token == null) {
			token = request.getHeader(RESTurl.UserTokenkey);
		}

		if (token != null) {
			try {
				token = URLDecoder.decode(token, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		if (token != null) {
			this.token = token;
		}

		return token;
	}

	public void putConsumerId(MessageAvailableConsumer consumer, String consumerId) {
		this.consumerIdMap.put(consumer, consumerId);
	}
	
	public String removeConsumer(MessageAvailableConsumer consumer) {
		 return this.consumerIdMap.remove(consumer);
	}
	
	public String getConsumerId(MessageAvailableConsumer consumer) {
		return this.consumerIdMap.get(consumer);
	}

	public Map<MessageAvailableConsumer, String> getDestinationNameMap() {
		return this.destinationNameMap;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		LOG.info("set token, old token=" + this.token + ", new token=" + token);
		this.token = token;
	}

	public AjaxListener getListener() {
		return this.listener;
	}

	public long getMillisSinceLastAccessed() {
		return this.getNow() - this.lastAccessed;
	}

	public void updateLastAccessed() {
		this.lastAccessed = this.getNow();
		listener.access();
	}

	public boolean isExpired() {
		boolean returnVal = false;
		if (this.getMillisSinceLastAccessed() > this.expireAfter) {
			returnVal = true;
		}
		return returnVal;
	}

	protected long getNow() {
		return System.currentTimeMillis();
	}

	public String getServerProxyId() {
		return serverProxyId;
	}

	/**
	 * 检查token是否最后有效token
	 * 
	 * @param token
	 * @return
	 */
	public static boolean checkToken(ClientToken token) {
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.checkToken), token.getUserIdAndToken(), token.getUserIdAndToken());
		return Boolean.valueOf(result);
	}

	/**
	 * 检查token是否最后有效token
	 * 
	 * @param token
	 * @return
	 */
	public static boolean checkToken(String token) {
		try {
			ClientToken clientToken = new ClientToken(token);
			return checkToken(clientToken);
		} catch (Exception e) {
			return false;
		}
	}
}
