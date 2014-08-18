package com.isoftstone.interview.traffic;

public enum Lamp {

	S2N("S2W", "N2S", false), S2W("E2W", "N2E", false), E2W("E2S", "W2E", false), E2S(
			"S2N", "W2N", false), N2S("S2W", "S2N", false), N2E("E2W", "S2W",
			false), W2E("E2S", "E2W", false), W2N("S2N", "E2S", false), S2E(
			null, null, true), E2N(null, null, true), N2W(null, null, true), W2S(
			null, null, true);

	private boolean lighted;
	private String next;
	private String opposite;

	private Lamp(String next, String opposite, boolean lighted) {
		this.opposite = opposite;
		this.next = next;
		this.lighted = lighted;
	}

	public void light() {
		System.out.println(this.toString() + "变绿. 有六个方向的车在跑.");
		this.lighted = true;
		if (opposite != null) {
			Lamp.valueOf(opposite).lighted = true;
		}
		if (next != null) {
			Lamp.valueOf(next).lighted = false;
		}
	}

	public Lamp backOut() {
		System.out.println(this.toString() + "变红!!!\n");
		this.lighted = false;
		if (opposite != null) {
			Lamp.valueOf(opposite).lighted = false;
		}
		Lamp nextLamp = null;
		if (next != null) {
			nextLamp = Lamp.valueOf(next);
			nextLamp.light();
		}
		return nextLamp;
	}

	public boolean isLighted() {
		return lighted;
	}

	public void setLighted(boolean lighted) {
		this.lighted = lighted;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getOpposite() {
		return opposite;
	}

	public void setOpposite(String opposite) {
		this.opposite = opposite;
	}

}
