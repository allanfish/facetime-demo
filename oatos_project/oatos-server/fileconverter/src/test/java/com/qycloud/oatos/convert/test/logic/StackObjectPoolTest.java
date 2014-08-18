package com.qycloud.oatos.convert.test.logic;

import java.util.Date;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.StackObjectPool;

public class StackObjectPoolTest implements PoolableObjectFactory<PoolObject>{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StackObjectPoolTest factory = new StackObjectPoolTest();
		StackObjectPool<PoolObject> pool = new StackObjectPool<PoolObject>(factory, 5);
		PoolObject obj = null;
		try {
			obj = pool.borrowObject();
			System.out.println(obj.getIndex());
//			Thread.sleep(6000);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (obj != null) {
					System.out.println("return:" + obj.getIndex());
					pool.returnObject(obj);
				}
			} catch (Exception ex) {
				// TODO: handle exception
			}
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
