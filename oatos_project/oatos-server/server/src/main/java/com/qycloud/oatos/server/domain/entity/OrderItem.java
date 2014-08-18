package com.qycloud.oatos.server.domain.entity;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.conlect.oatos.dto.client.pay.OrderItemDTO;

/**
 * 订单项目信息
 * @author yang
 *
 */
public class OrderItem {
	/**
	 * 订单项目ID
	 */
	private long id;

	/**
	 * 订单ID
	 */
	private long orderId;

	/**
	 * 购买服务ID
	 */
	private long serviceId;

	/**
	 * 企业ID
	 */
	private long entId;

	/**
	 * 购买数量
	 */
	private int amount;

	/**
	 * 折扣率
	 */
	private float ratio;

	/**
	 * 单价
	 */
	private int perPrice;

	/**
	 * 价格
	 */
	private double price;

	/**
	 * 折扣金额
	 */
	private double save;

	/**
	 * 抵扣金额
	 */
	private double offset;

	/**
	 * 购买年限
	 */
	private int buyYear;

	/**
	 * 开始时间
	 */
	private Date startDate;

	/**
	 * 结束时间
	 */
	private Date endDate;

	/**
	 * 服务名<br>
	 * {@link com.conlect.oatos.dto.status.OrderItemType}
	 */
	private String serviceName;

	public OrderItem() {

	}

	public OrderItem(OrderItemDTO item) {
		BeanUtils.copyProperties(item, this);
	}

	public OrderItemDTO toOrderItemDTO() {
		OrderItemDTO dto = new OrderItemDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getServiceId() {
		return serviceId;
	}

	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}

	public long getEntId() {
		return entId;
	}

	public void setEntId(long entId) {
		this.entId = entId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public float getRatio() {
		return ratio;
	}

	public void setRatio(float ratio) {
		this.ratio = ratio;
	}

	public int getPerPrice() {
		return perPrice;
	}

	public void setPerPrice(int perPrice) {
		this.perPrice = perPrice;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSave() {
		return save;
	}

	public void setSave(double save) {
		this.save = save;
	}

	public double getOffset() {
		return offset;
	}

	public void setOffset(double offset) {
		this.offset = offset;
	}

	public int getBuyYear() {
		return buyYear;
	}

	public void setBuyYear(int buyYear) {
		this.buyYear = buyYear;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}