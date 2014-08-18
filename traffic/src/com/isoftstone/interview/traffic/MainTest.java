package com.isoftstone.interview.traffic;

import java.util.ArrayList;
import java.util.List;

public class MainTest {

	public static void main(String[] args) {

		List<Road> roadList = new ArrayList<Road>();
		Lamp[] lamps = Lamp.values();
		for (Lamp lamp : lamps) {
			roadList.add(new Road(lamp.toString()));
		}

		new LampController();
	}

}
