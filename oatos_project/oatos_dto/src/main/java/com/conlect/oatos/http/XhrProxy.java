package com.conlect.oatos.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.utils.SimpleCallback;
import com.conlect.oatos.utils.SysLogger;

public class XhrProxy {

	// 日志
	private final static Logger logger = SysLogger.getOsLogger();

	private static PoolingClientConnectionManager cm;
	private HttpClient httpClient;

	private static XhrProxy instance = new XhrProxy();

	private XhrProxy() {
		cm = new PoolingClientConnectionManager(
				SchemeRegistryFactory.createDefault(), 30, TimeUnit.SECONDS);
		// 最大连接数
		cm.setMaxTotal(10000);
		// 每个路由的默认最大连接数
		cm.setDefaultMaxPerRoute(1000);
		// 启动监控线程
		new IdleConnectionEvictor(cm).start();

		httpClient = new DefaultHttpClient(cm);
	}

	public static XhrProxy getInstance() {
		return instance;
	}

	/**
	 * get
	 * 
	 * @param serviceKey
	 * @param urlPath
	 * @param token
	 * @param parameters
	 * @return
	 */
	public String getFromUrl(final String getURL, final String token,
			final String parameters) {
		String result = "";
		try {
			HttpEntity entity = get(getURL, token, parameters);
			if (entity != null) {
				result = EntityUtils.toString(entity);
			} else {
				result = ErrorType.error404.name();
			}
		} catch (Exception ex) {
			logger.error(getURL, ex);
			result = ErrorType.error500.name();
		}
		return result;
	}

	/**
	 * get
	 * 
	 * @param urlPath
	 * @param token
	 * @param parameters
	 * @return
	 */
	public HttpEntity get(final String urlPath, final String token,
			final String parameters) {
		String getURL = urlPath;
		if (parameters != null && !parameters.isEmpty()) {
			getURL = getURL + "?" + parameters;
		}
		HttpGet httpGet = new HttpGet(getURL);
		httpGet.setHeader(RESTurl.UserTokenkey, token);
		HttpEntity entity = null;
		try {
			HttpResponse response = httpClient.execute(httpGet);
			entity = response.getEntity();
		} catch (Exception ex) {
			logger.error(getURL, ex);
		}
		return entity;
	}

	/**
	 * get
	 * 
	 * @param urlPath
	 * @param token
	 * @param parameters
	 * @return
	 */
	public void get(final String urlPath, final String token,
			SimpleCallback<HttpResponse> callback) {
		HttpGet httpGet = new HttpGet(urlPath);
		httpGet.setHeader(RESTurl.UserTokenkey, token);
		httpGet.setHeader("Content-Type", "text/plain; charset=UTF-8");
		try {
			HttpResponse response = httpClient.execute(httpGet);
			callback.onSuccess(response);
		} catch (Exception ex) {
			logger.error(urlPath, ex);
			callback.onFailure(ex);
		}
	}

	public String postAsService(final String postURL, String serviceType,
			final String token, final String postData) {
		String result = null;
		try {
			HttpEntity postEntity = new StringEntity(postData, "UTF-8");
			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, token);
			headers.put("Content-Type", "text/html;charset=UTF-8");
			headers.put("Connection", "close");
			if (serviceType != null) {
				headers.put("serviceType", serviceType);
			}
			HttpEntity resultEntity = null;
			HttpPost httpPost = new HttpPost(postURL);
			if (headers != null) {
				for (String key : headers.keySet()) {
					httpPost.setHeader(key, headers.get(key));
				}
			}
			try {
				httpPost.setEntity(postEntity);
				HttpResponse response = httpClient.execute(httpPost);
				resultEntity = response.getEntity();
			} catch (IOException ex) {
				logger.error("url:" + postURL, ex);
			}
			if (resultEntity != null) {
				result = EntityUtils.toString(resultEntity);
			} else {
				result = ErrorType.error404.name();
			}
		} catch (IOException ex) {
			logger.error("url:" + postURL + ", postData:" + postData, ex);
			result = ErrorType.error500.name();
		}
		return result;
	}

	/**
	 * post
	 * 
	 * @param postURL
	 * @param token
	 * @param postData
	 * @return
	 */
	public String post(final String postURL, final String token,
			final String postData) {
		String result = "";
		try {
			HttpEntity postEntity = new StringEntity(postData, "UTF-8");
			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, token);
			headers.put("Content-Type", "text/plain; charset=UTF-8");
			HttpEntity entity = postForEntity(postURL, postEntity, headers);
			if (entity != null) {
				result = EntityUtils.toString(entity);
			}
		} catch (IOException ex) {
			logger.error("url:" + postURL + ", postData:" + postData, ex);
			result = ErrorType.error500.name();
		}
		return result;
	}

	/**
	 * post
	 * 
	 * @param postURL
	 * @param token
	 * @param postEntity
	 * @return
	 */
	public String post(final String postURL, final String token,
			final HttpEntity postEntity) {
		String result = "";
		try {
			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, token);
			HttpEntity entity = postForEntity(postURL, postEntity, headers);
			if (entity != null) {
				result = EntityUtils.toString(entity);
			}
		} catch (IOException ex) {
			logger.error(postURL, ex);
			result = ErrorType.error500.name();
		}
		return result;
	}

	/**
	 * post
	 * 
	 * @param postURL
	 * @param postEntity
	 * @param headers
	 * @return
	 */
	public String post(final String postURL, final HttpEntity postEntity,
			Map<String, String> headers) {
		String result = "";
		try {
			HttpEntity entity = postForEntity(postURL, postEntity, headers);
			if (entity != null) {
				result = EntityUtils.toString(entity);
			}
		} catch (Exception ex) {
			logger.error(postURL, ex);
			result = ErrorType.error500.name();
		}

		return result;
	}

	/**
	 * post
	 * 
	 * @param postURL
	 * @param postEntity
	 * @param headers
	 * @return
	 */
	public HttpEntity postForEntity(final String postURL,
			final HttpEntity postEntity, Map<String, String> headers) {
		HttpEntity result = null;
		HttpPost httpPost = new HttpPost(postURL);
		if (headers != null) {
			for (String key : headers.keySet()) {
				httpPost.setHeader(key, headers.get(key));
			}
		}
		if (postEntity != null) {
			httpPost.setEntity(postEntity);
		}
		HttpResponse response = postForResponse(postURL, postEntity, headers);
		result = response.getEntity();
		return result;
	}

	/**
	 * post
	 * 
	 * @param postURL
	 * @param postEntity
	 * @param headers
	 * @return
	 */
	public HttpResponse postForResponse(final String postURL,
			final HttpEntity postEntity, Map<String, String> headers) {
		HttpResponse result = null;
		HttpPost httpPost = new HttpPost(postURL);
		if (headers != null) {
			for (String key : headers.keySet()) {
				httpPost.setHeader(key, headers.get(key));
			}
		}
		try {
			if (postEntity != null) {
				httpPost.setEntity(postEntity);
			}
			result = httpClient.execute(httpPost);
		} catch (IOException ex) {
			logger.error(postURL, ex);
		}
		return result;
	}

	public String postAsForm(final String postURL, final String token,
			Map<String, String> formAttrs) {
		String result = null;
		try {
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			for (Entry<String, String> entry : formAttrs.entrySet()) {
				parameters.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
			UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(
					parameters, "UTF-8");

			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, token);
			headers.put("Content-Type", "application/x-www-form-urlencoded");
			headers.put("Connection", "close");

			HttpEntity resultEntity = null;
			HttpPost httpPost = new HttpPost(postURL);
			if (headers != null) {
				for (String key : headers.keySet()) {
					httpPost.setHeader(key, headers.get(key));
				}
			}
			try {
				httpPost.setEntity(reqEntity);
				HttpResponse response = httpClient.execute(httpPost);
				resultEntity = response.getEntity();
			} catch (IOException ex) {
				logger.error("url:" + postURL, ex);
			}
			if (resultEntity != null) {
				result = EntityUtils.toString(resultEntity);
			} else {
				result = ErrorType.error404.name();
			}
		} catch (IOException ex) {
			logger.error("url:" + postURL + ", formAttrs:" + formAttrs, ex);
			result = ErrorType.error500.name();
		}
		return result;
	}

	/**
	 * 连接监控，关闭过期连接
	 * 
	 * @author yang
	 * 
	 */
	private class IdleConnectionEvictor extends Thread {
		private final ClientConnectionManager connMgr;

		public IdleConnectionEvictor(ClientConnectionManager connMgr) {
			this.connMgr = connMgr;
		}

		@Override
		public void run() {
			try {
				while (true) {
					synchronized (this) {
						wait(30000);
						// 关闭过期连接
						connMgr.closeExpiredConnections();
						// 关闭空闲超过40s的连接
						connMgr.closeIdleConnections(40, TimeUnit.SECONDS);
					}
				}
			} catch (Exception ex) {
				logger.warn("", ex);
			}
		}
	}
}
