package com.javase.util;

import java.awt.Robot;
import java.util.Random;

public class RobotTest {

	public static void main(String[] args) throws Exception {
		Robot robot = new Robot();
		Random random = new Random();
		while (true) {
			Thread.sleep(300000);
			robot.mouseMove(random.nextInt(500), 500);
			robot.delay(60000);
		}
	}
}
