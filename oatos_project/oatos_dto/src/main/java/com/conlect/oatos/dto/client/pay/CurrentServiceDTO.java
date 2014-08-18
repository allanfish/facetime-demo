package com.conlect.oatos.dto.client.pay;

import com.conlect.oatos.dto.autobean.pay.ICurrentServiceDTO;
import com.conlect.oatos.dto.status.OrderItemType;

@SuppressWarnings("serial")
public class CurrentServiceDTO implements ICurrentServiceDTO {

	private OrderItemType type;// 服务类型
	private long freeSize;
	private long buySize;
	private long usedSize;

	@Override
	public OrderItemType getType() {
		return type;
	}

	@Override
	public void setType(OrderItemType type) {
		this.type = type;
	}

	@Override
	public long getFreeSize() {
		return freeSize;
	}

	@Override
	public void setFreeSize(long freeSize) {
		this.freeSize = freeSize;
	}

	@Override
	public long getBuySize() {
		return buySize;
	}

	@Override
	public void setBuySize(long buySize) {
		this.buySize = buySize;
	}

	@Override
	public long getUsedSize() {
		return usedSize;
	}

	@Override
	public void setUsedSize(long usedSize) {
		this.usedSize = usedSize;
	}

}
