package com.conlect.oatos.dto.autobean.pay;

import java.io.Serializable;
import java.util.Date;

import com.conlect.oatos.dto.status.OrderItemStatus;
import com.conlect.oatos.dto.status.OrderItemType;

public interface IBuyServiceDTO extends Serializable {

	public abstract void setStatus(OrderItemStatus status);

	public abstract OrderItemStatus getStatus();

	public abstract void setEndDate(Date endDate);

	public abstract Date getEndDate();

	public abstract void setStartDate(Date startDate);

	public abstract Date getStartDate();

	public abstract void setBuyYear(int buyYear);

	public abstract int getBuyYear();

	public abstract void setAmount(int amount);

	public abstract int getAmount();

	public abstract void setType(OrderItemType type);

	public abstract OrderItemType getType();

}
