package com.qycloud.oatos.convert.test.logic;

import java.util.Date;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

public class GenericObjectPoolTest implements PoolableObjectFactory<PoolObject>{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericObjectPoolTest factory = new GenericObjectPoolTest();
		GenericObjectPool<PoolObject> pool = new GenericObjectPool<PoolObject>(factory);
		// 可从池中借出对象的最大数目
		pool.setMaxActive(5);
		// 池中可保存对象数目上限
		pool.setMaxIdle(5);
		// 当借出对象数目超过限制时，抛出异常
		pool.setWhenExhaustedAction(GenericObjectPool.WHEN_EXHAUSTED_FAIL);
		// 借出对象时，是否进行有效性检查
		pool.setTestOnBorrow(true);
		// 还回对象时，是否进行有效性检查
		pool.setTestOnReturn(true);
		// 后台对象清理间隔时间
		pool.setTimeBetweenEvictionRunsMillis(5000 / 2);
		// 对象清理时，视休眠超过多少时间的对象为过期
		pool.setMinEvictableIdleTimeMillis(5000);
		// 清理对象时，是否对没有过期的对象进行有效性检查，无效对象将被回收
		pool.setTestWhileIdle(true);
		for (int i = 0; i < 20; i++) {
			PoolObject obj = null;
			try {
				obj = pool.borrowObject();
				System.out.println(obj.getIndex());
				Thread.sleep(1000);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (obj != null) {
//						System.out.println("return:" + obj.getIndex());
//						pool.returnObject(obj);
					}
				} catch (Exception ex) {
					// TODO: handle exception
				}
			}
			System.out.println("getNumActive: " + pool.getNumActive());
			System.out.println("getNumIdle: " + pool.getNumIdle());
		}
	}

	@Override
	public PoolObject makeObject() throws Exception {
		PoolObject obj = new PoolObject();
		obj.setCreateTime(new Date());
		System.out.println("make:" + obj.getIndex());
		return obj;
	}

	@Override
	public void destroyObject(PoolObject obj) throws Exception {
		System.out.println("destroy:" + obj.getIndex());
		obj = null;
	}

	@Override
	public boolean validateObject(PoolObject obj) {
		System.out.println("validate:" + obj.getIndex());
		long t = new Date().getTime() - obj.getUpdateTime().getTime();
		return t < 1000 * 5;
	}

	@Override
	public void activateObject(PoolObject obj) throws Exception {
		System.out.println("activate:" + obj.getIndex());
		obj.setUpdateTime(new Date());
	}

	@Override
	public void passivateObject(PoolObject obj) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("passivate:" + obj.getIndex());
	}

}
