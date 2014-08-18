package com.qycloud.oatos.server.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.pay.BuyServiceListDTO;
import com.conlect.oatos.dto.client.pay.CurrentServiceListDTO;
import com.conlect.oatos.dto.client.pay.EntPayDTO;
import com.conlect.oatos.dto.client.pay.OrderDTO;
import com.conlect.oatos.dto.client.pay.OrderItemListDTO;
import com.conlect.oatos.dto.client.pay.ServiceTypeListDTO;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.logic.OrderLogic;
import com.qycloud.oatos.server.util.BllLogger;
import com.qycloud.oatos.server.util.LogicException;

/**
 * 订单支付服务
 * @author yang
 *
 */
@Controller("OrderService")
public class OrderService {

	private final static Logger logger = BllLogger.getLogger();
	
	@Autowired
	private OrderLogic orderLogic;

	/**
	 * 保存订单信息
	 * @param strOrder
	 * @return
	 */
	@RequestMapping(value = RESTurl.saveOrder)
	public @ResponseBody
	String saveOrder(@RequestBody String strOrder) {
		String result = ErrorType.error500.name();
		try {
			result = orderLogic.saveOrder(PojoMapper.fromJsonAsObject(strOrder, OrderDTO.class));
		}
		catch (Exception e) {
			result = ErrorType.error500.name();
			logger.error(strOrder, e);
		}
		logger.debug(strOrder + "\nrestlt:\n" + result);
		return result;
	}

	/**
	 * 订单支付完成
	 * @param strOrderInfo
	 * @return
	 */
	@RequestMapping(value = RESTurl.payComplete, method = RequestMethod.POST)
	public @ResponseBody
	String payComplete(@RequestBody String strOrderInfo) {
		String result = "";
		try {
			OrderDTO orderInfo = PojoMapper.fromJsonAsObject(strOrderInfo, OrderDTO.class);
			result =  orderLogic.payComplete(orderInfo);
		}
		catch (Exception e) {
			result = ErrorType.error500.name();
			logger.error(strOrderInfo, e);
		}
		logger.debug(strOrderInfo);
		return result;
	}

	/**
	 * 获取企业服务状态信息
	 * @param strEntId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getEntPayInfo, method = RequestMethod.POST)
	public @ResponseBody
	String getEntPayInfo(@RequestBody String strEntId) {
		String result = "";
		try {
			EntPayDTO userPay = orderLogic.getUserPayInfo(Long.parseLong(strEntId));
			result = PojoMapper.toJson(userPay);
		}
		catch (Exception e) {
			result = ErrorType.error500.name();
			logger.error(strEntId, e);
		}
		logger.debug(strEntId + "\nrestlt:\n" + result);
		return result;
	}

	/**
	 * 根据交易编号获取订单信息
	 * @param tradeNo
	 * @return
	 */
	@RequestMapping(value = RESTurl.getOrderByTradeNo, method = RequestMethod.POST)
	public @ResponseBody
	String getOrderByTradeNo(@RequestBody String tradeNo) {
		String result = "";
		try {
			OrderDTO order = orderLogic.getOrderByTradeNo(tradeNo);
			result = PojoMapper.toJson(order);
		}
		catch (LogicException le) {
			result = le.getError().name();
		}
		catch (Exception e) {
			result = ErrorType.error500.name();
			logger.error(tradeNo, e);
		}
		logger.debug(tradeNo + "\nrestlt:\n" + result);
		return result;
	}
	/**
	 * 获取企业购买服务列表
	 * @param entId
	 * @return
	 */
	
	@RequestMapping(value = RESTurl.getBuyServiceList, method = RequestMethod.POST)
    @ResponseBody
	public String getBuyServiceList(@RequestBody String entId) {
		String reBody = "";
		try {
			BuyServiceListDTO buyServiceList = orderLogic.getBuyServiceList(new Long(entId));
			reBody = PojoMapper.toJson(buyServiceList);
		} catch (Exception e) {
			
			reBody = ErrorType.error500.name();
			logger.error(entId,e);
		}
		return reBody;
	}
	
	/**
	 * 获取服务价格列表
	 * @return
	 */
	
	@RequestMapping(value = RESTurl.getServicePriceList,method = RequestMethod.POST)
	@ResponseBody
	public String getServicePriceList(){
		String reBody = "";
		try {
			ServiceTypeListDTO serviceTypeList = orderLogic.getServicePriceList();
			reBody = PojoMapper.toJson(serviceTypeList);
		} catch (Exception e) {
			reBody = ErrorType.error500.name();
			logger.error(e);
		}
		return reBody;
	}
	
	/**
	 * 获取企业购买的订单项列表
	 * @param entId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getOrderItemList,method = RequestMethod.POST)
	@ResponseBody
	public String getOrderItemList(@RequestBody String entId){
		String resBody = "";
		try {
			OrderItemListDTO orderItemList = orderLogic.getOrderItemList(new Long(entId));
			resBody = PojoMapper.toJson(orderItemList);
		} catch (Exception e) {
			resBody = ErrorType.error500.name();
			logger.error(entId, e);
		}
		return resBody;
	}
	
	/**
	 * 返回企业当前的服务信息
	 * @param entId
	 * @return
	 */
	
	@RequestMapping(value = RESTurl.getCurrServiceList,method = RequestMethod.POST)
	@ResponseBody
	public String getCurrServiceList(@RequestBody String entId){
		String resBody = "";
		try {
			CurrentServiceListDTO currentDTO = orderLogic.getCurrServiceList(new Long(entId));
			resBody = PojoMapper.toJson(currentDTO);
		} catch (Exception e) {
			resBody = ErrorType.error500.name();
			logger.error(entId, e);
		}
		return resBody;
	}
	
}
