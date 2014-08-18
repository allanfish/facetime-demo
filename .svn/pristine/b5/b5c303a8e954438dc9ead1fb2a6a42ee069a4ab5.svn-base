package com.qycloud.oatos.server.util;

import java.io.IOException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;

import org.apache.log4j.Logger;

import com.qycloud.oatos.server.security.Security;

/**
 * 缓存服务器访问类
 * 
 * @author PeterGuo
 * 
 */
public class CacheClient {

	// 缓存数据到期时间（秒数）, 默认保存一天
	public static final int DefaultSecondsExpired = 3600 * 24;

	// key节分隔符
	public static final char KeySpearator = ':';

	// 缓存服务器Key，读取缓存服务器的IP地址的配置信息
	private static final String CacheServersIPKey = "Memcached.Servers";

	// 缓存服务器访问ID， 读取缓存服务器访问ID的信息
	private static final String CacheAIDKey = "Memcached.AID";

	// 缓存服务器访问的密码Key， 读取缓存服务器访问密码
	private static final String PasswordKey = "Memcached.Password";

	// 加密 cache key 用的字符串
	private static String AIDCacheKey = null;

	// 日志
	private static Logger logger = Logger.getLogger(CacheClient.class);

	// 缓存客户端
	private static CacheClient cacheClient = null;

	// spy memcached 客户端实现
	private static MemcachedClient spyMemcachedClient = null;

	private CacheClient() {

	}

	/**
	 * 获取Cache客户端实例
	 * 
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	public static CacheClient getInstance() {
		String aid = ConfigUtil.getValue(CacheAIDKey);
		String pwd = ConfigUtil.getValue(PasswordKey);

		if (cacheClient == null) {
			synchronized (CacheClient.class) {
				String cacheServerList = ConfigUtil.getValue(CacheServersIPKey);
				int beginIndex = 0;
				int endIndex = 9;

				try {
					// spy memcached client, 执行缓存服务器的访问
					spyMemcachedClient = new MemcachedClient(
							new BinaryConnectionFactory(),
							AddrUtil.getAddresses(cacheServerList));

					// 缓存客户端包装类，提供加密的key
					cacheClient = new CacheClient();

					// 加密 cache key 用的字符串
					AIDCacheKey = Security.SHA256(aid).substring(beginIndex,
							endIndex)
							+ KeySpearator;
				} catch (IOException ex) {
					logger.error(ex);
				}
			}
		}

		return cacheClient;
	}

	/**
	 * 保存对象到缓存服务器，默认保存一天
	 * 
	 * @param key
	 * @param cacheData
	 */
	public void set(String key, Object cacheData) {
		this.set(key, DefaultSecondsExpired, cacheData);
	}

	/**
	 * 保存对象到缓存服务器，给定保存时间（秒数）
	 * 
	 * @param key
	 * @param secondsExpired
	 * @param cacheData
	 */
	public void set(String key, int secondsExpired, Object cacheData) {
		spyMemcachedClient.set(AIDCacheKey + key, secondsExpired, cacheData);
	}

	/**
	 * 根据给定的key获取缓存对象
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return spyMemcachedClient.get(AIDCacheKey + key);
	}

	/**
	 * 根据给定的key，删除缓存的数据
	 * 
	 * @param key
	 */
	public void delete(String key) {
		spyMemcachedClient.delete(AIDCacheKey + key);
	}

	/**
	 * 清除所有缓存数据
	 */
	public void flush() {
		spyMemcachedClient.flush();
	}
}
