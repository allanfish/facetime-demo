package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.pay.BuyServiceListDTO;
import com.conlect.oatos.dto.client.pay.CurrentServiceListDTO;
import com.conlect.oatos.dto.client.pay.EntPayDTO;
import com.conlect.oatos.dto.client.pay.OrderDTO;
import com.conlect.oatos.dto.client.pay.OrderItemListDTO;
import com.conlect.oatos.dto.client.pay.ServiceTypeListDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 订单REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>保存订单
 * <li>支付完成
 * <li>获取企业服务状态信息
 * <li>根据交易编号获取订单信息
 * <li>获取企业购买服务列表
 * <li>获取服务价格列表
 * <li>获取企业购买的订单项列表
 * <li>返回企业当前的服务信息
 * </ul>
 * 
 * @author huhao
 * 
 */
public interface OrderUrl {

	/**
	 * <p>
	 * 保存订单
	 * </p>
	 * <p>
	 * <b>参数：</b>{@link OrderDTO} 订单 DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b> {@link CommConstants#OK_MARK} OK
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String saveOrder = "/pub/order/saveOrder";

	/**
	 * <p>
	 * 支付完成
	 * </p>
	 * <p>
	 * <b>参数：</b>{@link OrderDTO} 订单 DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b> {@link CommConstants#OK_MARK} OK
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorOrderNotExist} 订单不存在
	 * </ul>
	 */
	String payComplete = "/pub/pay/complete";
	/**
	 * <p>
	 * 获取企业服务状态信息
	 * </p>
	 * <p>
	 * <b>参数：</b>EntId 企业ID
	 * </p>
	 * <p>
	 * <b>正常返回：</b> {@link EntPayDTO} 企业购买DTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getEntPayInfo = "/pub/pay/info";
	/**
	 * <p>
	 * 根据交易编号获取订单信息
	 * </p>
	 * <p>
	 * <b>参数：</b> tradeNo 交易号
	 * </p>
	 * <p>
	 * <b>正常返回：</b> {@link OrderDTO} 订单 DTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorOrderNotExist} 订单不存在
	 * </ul>
	 */
	String getOrderByTradeNo = "/pub/order/getOrderByTradeNo";
	/**
	 * <p>
	 * 获取企业购买服务列表
	 * </p>
	 * <p>
	 * <b>参数：</b>EntId 企业ID
	 * </p>
	 * <p>
	 * <b>正常返回：</b> {@link BuyServiceListDTO} 企业购买服务listDTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getBuyServiceList = "/sc/service/getBuyServiceList";
	/**
	 * <p>
	 * 获取服务价格列表
	 * </p>
	 * <p>
	 * <b>参数：</b>EntId 企业ID
	 * </p>
	 * <p>
	 * <b>正常返回：</b> {@link ServiceTypeListDTO}服务类型DTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getServicePriceList = "/sc/service/getServicePriceList";
	/**
	 * <p>
	 * 获取企业购买的订单项列表
	 * </p>
	 * <p>
	 * <b>参数：</b>EntId 企业ID
	 * </p>
	 * <p>
	 * <b>正常返回：</b> {@link OrderItemListDTO} 订单项目listDTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getOrderItemList = "/sc/service/getOrderList";
	/**
	 * <p>
	 * 返回企业当前的服务信息
	 * </p>
	 * <p>
	 * <b>参数：</b>EntId 企业ID
	 * </p>
	 * <p>
	 * <b>正常返回：</b> {@link CurrentServiceListDTO} 企业当前服务listDTO
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getCurrServiceList = "/sc/service/getCurrServiceList";

}
