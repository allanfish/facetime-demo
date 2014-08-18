package com.isoftstone.interview.traffic;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LampController {

	private Lamp currentLamp;

	public LampController() {
		currentLamp = Lamp.S2N;
		currentLamp.light();

		ScheduledExecutorService timer = Executors
				.newSingleThreadScheduledExecutor();
		timer.scheduleAtFixedRate(new Runnable() {
			public void run() {
				currentLamp = currentLamp.backOut();
			}
		}, 10, 10, TimeUnit.SECONDS);
	}
}
