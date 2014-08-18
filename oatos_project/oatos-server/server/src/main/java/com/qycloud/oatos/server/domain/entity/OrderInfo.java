package com.qycloud.oatos.server.domain.entity;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.conlect.oatos.dto.client.pay.OrderDTO;

/**
 * 订单信息
 * @author yang
 *
 */
public class OrderInfo {
	/**
	 * 订单ID
	 */
	private long orderId;

	/**
	 * 企业ID
	 */
	private long entId;

	/**
	 * 交易编号
	 */
	private String tradeNo;

	/**
	 * 价格
	 */
	private double price;

	/**
	 * 抵扣金额
	 */
	private double offset;

	/**
	 * 折扣金额
	 */
	private double save;

	/**
	 * 订单类型<br>
	 * {@link com.conlect.oatos.dto.status.OrderType}
	 */
	private String type;

	/**
	 * 支付日期
	 */
	private Date payDate;

	/**
	 * 下单时间
	 */
	private Date createDate;

	/**
	 * 订单支付状态<br>
	 * {@link com.conlect.oatos.dto.status.OrderStatus}
	 */
	private String status;

	/**
	 * 企业名
	 */
	private String entName;

	public OrderInfo() {

	}

	public OrderInfo(OrderDTO order) {
		BeanUtils.copyProperties(order, this);
	}

	public OrderDTO toOrderDTO() {
		OrderDTO dto = new OrderDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getEntId() {
		return entId;
	}

	public void setEntId(long entId) {
		this.entId = entId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getOffset() {
		return offset;
	}

	public void setOffset(double offset) {
		this.offset = offset;
	}

	public double getSave() {
		return save;
	}

	public void setSave(double save) {
		this.save = save;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

}