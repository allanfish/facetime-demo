package com.qycloud.oatos.convert.domain.logic;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.qycloud.oatos.convert.util.ConfigUtil;
import com.qycloud.oatos.convert.util.Logs;

/**
 * office连接池
 * @author yang
 *
 */
public class OfficeConnectionPool implements PoolableObjectFactory<SocketOpenOfficeConnection>{

	/**
	 * office连接端口
	 */
	private static final int OFFICE_CONNECTION_PORT = 8100;

	/**
	 * 最大连接数
	 */
	private static final int MAX_POOL_SIZE = ConfigUtil.getIntValue(ConfigUtil.MAX_OFFICE_TASK);
	
	/**
	 * 空闲连接保持时间
	 */
	private static final int KEEP_ALIVE_TIME = 5 * 60 * 1000;

	/**
	 * 连接池
	 */
	private GenericObjectPool<SocketOpenOfficeConnection> pool = null;
	
	private static OfficeConnectionPool instance = new OfficeConnectionPool();

	public static OfficeConnectionPool getInstance() {
		return instance;
	}

	private OfficeConnectionPool() {
		pool = new GenericObjectPool<SocketOpenOfficeConnection>(this);
		// 可从池中借出对象的最大数目
		pool.setMaxActive(MAX_POOL_SIZE);
		// 池中可保存对象数目上限
		pool.setMaxIdle(MAX_POOL_SIZE);
		// 当借出对象数目超过限制时，抛出异常
		pool.setWhenExhaustedAction(GenericObjectPool.WHEN_EXHAUSTED_FAIL);
		// 借出对象时，是否进行有效性检查
		pool.setTestOnBorrow(true);
		// 还回对象时，是否进行有效性检查
		pool.setTestOnReturn(true);
		// 后台对象清理间隔时间
		pool.setTimeBetweenEvictionRunsMillis(KEEP_ALIVE_TIME / 2);
		// 对象清理时，视休眠超过多少时间的对象为过期
		pool.setMinEvictableIdleTimeMillis(KEEP_ALIVE_TIME);
		// 清理对象时，是否对没有过期的对象进行有效性检查，无效对象将被回收
		pool.setTestWhileIdle(true);
	}

	/**
	 * 获取一个空闲的连接
	 * @return
	 * @throws Exception
	 */
	public synchronized SocketOpenOfficeConnection getConnection() throws Exception {
		return pool.borrowObject();
	}

	/**
	 * 释放连接，将连接置为可用
	 * @param con
	 */
	public synchronized void release(SocketOpenOfficeConnection con) {
		try {
			if (con != null) {
				pool.returnObject(con);
			}
		} catch (Exception ex) {
			Logs.getLogger().error("", ex);
		}
	}

	/**
	 * 在必要时创建新的连接
	 */
	@Override
	public SocketOpenOfficeConnection makeObject() throws Exception {
		SocketOpenOfficeConnection con = new SocketOpenOfficeConnection(OFFICE_CONNECTION_PORT);
		return con;
	}

	/**
	 * 销毁连接
	 */
	@Override
	public void destroyObject(SocketOpenOfficeConnection obj) throws Exception {
		if (obj != null && obj.isConnected()) {
			obj.disconnect();
		}
		obj = null;
	}

	/**
	 * 校验连接是否有效，失效的连接会被销毁
	 */
	@Override
	public boolean validateObject(SocketOpenOfficeConnection obj) {
		return obj != null && obj.isConnected();
	}

	/**
	 * 激活链接，设置为适合开始使用的状态
	 */
	@Override
	public void activateObject(SocketOpenOfficeConnection obj) throws Exception {
		if (!obj.isConnected()) {
			obj.connect();
		}
	}

	/**
	 * 挂起连接，设置为适合开始休眠的状态
	 */
	@Override
	public void passivateObject(SocketOpenOfficeConnection obj)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
