package com.conlect.oatos.dto.client.pay;

import java.util.Date;

import com.conlect.oatos.dto.autobean.pay.IBuyServiceDTO;
import com.conlect.oatos.dto.status.OrderItemStatus;
import com.conlect.oatos.dto.status.OrderItemType;

@SuppressWarnings("serial")
public class BuyServiceDTO implements IBuyServiceDTO {

	private OrderItemType type;
	private int amount;
	private int buyYear;
	private Date startDate;
	private Date endDate;
	private OrderItemStatus status;

	@Override
	public OrderItemType getType() {
		return type;
	}

	@Override
	public void setType(OrderItemType type) {
		this.type = type;
	}

	@Override
	public int getAmount() {
		return amount;
	}

	@Override
	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public int getBuyYear() {
		return buyYear;
	}

	@Override
	public void setBuyYear(int buyYear) {
		this.buyYear = buyYear;
	}

	@Override
	public Date getStartDate() {
		return startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public Date getEndDate() {
		return endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public OrderItemStatus getStatus() {
		return status;
	}

	@Override
	public void setStatus(OrderItemStatus status) {
		this.status = status;
	}
}
