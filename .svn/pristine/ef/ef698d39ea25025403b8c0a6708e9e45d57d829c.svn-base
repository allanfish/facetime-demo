package com.qycloud.oatos.convert.test.logic;

import java.util.Date;

public class TaskTest implements Runnable, Comparable<TaskTest> {

	private int no;

	public TaskTest(int no) {
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	@Override
	public void run() {
		System.out.println(no + " ---- " + new Date());
		try {
			for (int i = 0; i < 100; i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int compareTo(TaskTest o) {
		return no - o.getNo();
	}

}
