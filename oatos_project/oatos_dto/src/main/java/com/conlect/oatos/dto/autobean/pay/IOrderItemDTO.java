package com.conlect.oatos.dto.autobean.pay;

import java.io.Serializable;
import java.util.Date;

public interface IOrderItemDTO extends Serializable {

	long getId();

	void setId(long id);

	long getOrderId();

	void setOrderId(long orderId);

	long getServiceId();

	void setServiceId(long serviceId);

	long getEntId();

	void setEntId(long entId);

	int getAmount();

	void setAmount(int amount);

	float getRatio();

	void setRatio(float ratio);

	int getPerPrice();

	void setPerPrice(int perPrice);

	double getPrice();

	void setPrice(double price);

	double getSave();

	void setSave(double save);

	double getOffset();

	void setOffset(double offset);

	int getBuyYear();

	void setBuyYear(int buyYear);

	Date getStartDate();

	void setStartDate(Date startDate);

	Date getEndDate();

	void setEndDate(Date endDate);

	String getServiceName();

	void setServiceName(String serviceName);

	public abstract void setItemName(String itemName);

	public abstract String getItemName();

	public abstract void setItemId(String itemId);

	public abstract String getItemId();
}
