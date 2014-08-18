package com.conlect.oatos.dto.autobean.pay;

import java.io.Serializable;

import com.conlect.oatos.dto.status.OrderItemType;

public interface ICurrentServiceDTO extends Serializable {

	public abstract void setUsedSize(long usedSize);

	public abstract long getUsedSize();

	public abstract void setBuySize(long buySize);

	public abstract long getBuySize();

	public abstract void setFreeSize(long freeSize);

	public abstract long getFreeSize();

	public abstract void setType(OrderItemType type);

	public abstract OrderItemType getType();

}
