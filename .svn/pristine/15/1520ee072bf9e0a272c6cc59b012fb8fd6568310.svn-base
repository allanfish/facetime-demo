package com.conlect.oatos.dto.status;

public enum OrderStatus {

	/**
	 * 订单支付成功
	 */
	PAY_OK,
	/**
	 * 激活状态
	 */
	ACTIVE,
	/**
	 * 订单支付失败
	 */
	PAY_FAIL,

	/**
	 * 订单已经过期
	 */
	EXPIRED,

	/**
	 * 未支付
	 */
	UNPAY;

	/**
	 * 判断支付是否到期
	 * 
	 * @param payStatus
	 * @return
	 */
	public static boolean isPayExpired(String payStatus) {
		return payStatus != null && EXPIRED.name().equals(payStatus);
	}
}
