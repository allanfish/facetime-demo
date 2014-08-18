package com.qycloud.oatos.convert.test.logic;

import java.util.Date;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool extends ThreadPoolExecutor {

	public ThreadPool() {
		super(3, 3, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<Runnable>());
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
//		TaskTest task = (TaskTest) r;
//		System.out.println(task.getNo() + " --before-- " + new Date());
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
//		TaskTest task = (TaskTest) r;
//		System.out.println(task.getNo() + " --after-- " + new Date());
	}
}
