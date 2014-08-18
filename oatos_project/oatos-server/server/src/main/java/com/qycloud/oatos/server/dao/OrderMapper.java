package com.qycloud.oatos.server.dao;

import java.util.List;

import com.qycloud.oatos.server.domain.entity.OrderInfo;
import com.qycloud.oatos.server.domain.entity.OrderItem;
import com.qycloud.oatos.server.domain.entity.ServiceStatus;
import com.qycloud.oatos.server.domain.entity.ServiceType;

/**
 * 订单支付实体层
 * @author yang
 * 
 */
public interface OrderMapper {

	/**
	 * 保存订单信息
	 * @param order
	 */
	void addOrderInfo(OrderInfo order);

	/**
	 * 获取订单信息
	 * @param orderId
	 * @return
	 */
	OrderInfo getOrderInfoById(long orderId);

	/**
	 * 根据交易编号取订单信息
	 * @param tradeNo
	 * @return
	 */
	OrderInfo getOrderInfoByTradeNo(String tradeNo);

	/**
	 * 修改订单支付状态
	 * @param order
	 */
	void updateOrderStatus(OrderInfo order);

	/**
	 * 获取订单项目
	 * @param orderId
	 * @return
	 */
	List<OrderItem> getOrderItemsByOrderId(long orderId);
	
	/**
	 * 通过企业ID获得获得订单项目
	 * @param entId
	 * @return
	 */
	List<OrderItem> getOrderItemsByEntId(long entId);

	/**
	 * 保存订单项目信息
	 * @param orderItems
	 */
	void insertOrderItems(List<OrderItem> orderItems);

	/**
	 * 获取服务种类价格信息
	 * @return
	 */
	List<ServiceType> getServiceTypes();

	/**
	 * 获取服务状态信息
	 * @param entId
	 * @return
	 */
	ServiceStatus getServiceStatus(long entId);

	/**
	 * 保存服务状态信息
	 * @param status
	 */
	void insertServiceStatus(ServiceStatus status);

	/**
	 * 修改服务状态信息
	 * @param status
	 */
	void updateServiceStatus(ServiceStatus status);
}
