package com.qycloud.oatos.convert.test.logic;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class ThreadPoolTest {

	public static void main(String[] args) {

		ThreadPool e = new ThreadPool();
		Future<?> future = e.submit(new TaskTest(100));
		try {
			future.get(10, TimeUnit.SECONDS);
		} catch (InterruptedException e1) {
			future.cancel(false);
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TimeoutException e1) {
			future.cancel(false);
		}
		
//		for (int i = 20; i > 0; i--) {
//			TaskTest task = new TaskTest(i);
//			e.execute(task);
//		}

		System.out.println("over");
	}

}
