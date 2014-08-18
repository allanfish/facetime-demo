package com.grgbanking.view.scheduling;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class TestJobBus1 implements IJob{
	private static Log log=LogFactory.getLog(TestJobBus1.class);
	
	/**
	 * Í£»úÈÎÎñ
	 */
	
	public void executeInternal(){	
		try {
			System.out.println("-------------TestJobBus1 start-------------"+ InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
