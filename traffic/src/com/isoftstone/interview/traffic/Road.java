package com.isoftstone.interview.traffic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Road {

	private List<String> vehicles = new ArrayList<String>();
	private String name;

	/**
	 * 每一方向对应着一个Road对象, 每一个Road都会时刻新增车辆. <br>
	 * 每隔一秒Road都要检查灯是否是绿的, 如果是则减少第一辆车. <br>
	 * 
	 * @param name
	 */
	public Road(String name) {
		this.name = name;

		// 模拟路上新增车辆
		ExecutorService thread = Executors.newFixedThreadPool(1);
		thread.execute(new Runnable() {
			public void run() {
				for (int i = 1; i < 1000; i++) {
					try {
						Thread.sleep((new Random().nextInt(10) + 1) * 1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					vehicles.add(Road.this.name + i);
				}
			}
		});

		// 每隔一秒检测当前路对应的灯是否绿
		ScheduledExecutorService timer = Executors
				.newSingleThreadScheduledExecutor();
		timer.scheduleAtFixedRate(new Runnable() {
			public void run() {
				boolean lighted = Lamp.valueOf(Road.this.name).isLighted();
				if (!lighted) {
					return;
				}
				if (!vehicles.isEmpty()) {
					System.out.println(vehicles.get(0) + "开过去");
					vehicles.remove(0);
				}
			}
		}, 1, 1, TimeUnit.SECONDS);
	}
}
