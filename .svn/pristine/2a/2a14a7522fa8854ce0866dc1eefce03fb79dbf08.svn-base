package com.qycloud.oatos.server.test.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.conlect.oatos.dto.client.pay.CurrentServiceListDTO;
import com.conlect.oatos.dto.client.pay.EntPayDTO;
import com.conlect.oatos.dto.client.pay.OrderDTO;
import com.conlect.oatos.dto.client.pay.OrderItemDTO;
import com.conlect.oatos.dto.client.pay.OrderItemListDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
/**
 * 
 * @author xiao.min
 *
 */
public class OrderServiceTest extends BaseServiceTest {

	/***
	 *  返回企业当前的服务信息
	 *  pass
	 */
	@Test
	public void getCurrServiceList(){
		System.out.println("======== 返回企业当前的服务信息========");
		String s = postData(RESTurl.getCurrServiceList, "1234");
		System.out.println(s);
		if("error500".equals(s)){
			fail(s);
		}
		CurrentServiceListDTO csdto  = PojoMapper.fromJsonAsObject(s,CurrentServiceListDTO.class );
		
	}
	/***
	 *   获取企业购买服务列表
	 *  pass
	 */
	@Test
	public void getBuyServiceList(){
		System.out.println("=-============= 获取企业购买服务列表===============");
		String s = postData(RESTurl.getBuyServiceList, "1234");
		//BuyServiceListDTO buy = orderLogic.getBuyServiceList(1234l);
		//System.out.println(buy.getList().size()+"==="+buy.getList());
		
		System.out.println(s);
		
		//if("error500".equals(s)){
			//fail(s);
	//	}
		//BuyServiceListDTO  csdto  = PojoMapper.fromJsonAsObject(s, BuyServiceListDTO.class );
		
	}
	/**
	 *   获取企业服务状态信息
	 *   pass
	 */
	@Test
	public void getEntPayInfo(){
		System.out.println("===================== 获取企业服务状态信息========================");
		String s = post(RESTurl.getEntPayInfo, "1234");
		System.out.println(s);
		if("error500".equals(s)){
			fail(s);
		}
		EntPayDTO  csdto  = PojoMapper.fromJsonAsObject(s, EntPayDTO.class );
		assertEquals(csdto.getEntId(), 1234l);
	}
	
	/**
	 * 根据交易编号获取订单信息
	 * pass
	 */
	@Test
	public void getOrderByTradeNo(){
		System.out.println("===========根据交易编号获取订单信息=========");
		String s = postData(RESTurl.getOrderByTradeNo,"6039849");
		System.out.print(s);
		 OrderDTO  csdto  = PojoMapper.fromJsonAsObject(s,  OrderDTO.class );
	}
	
	/**
	 *  获取企业购买的订单项列表
	 * pass
	 */
	@Test
	public void getOrderItemList(){
		System.out.println("================ 获取企业购买的订单项列表===============");
		String s = post(RESTurl.getOrderItemList,"1234");
		System.out.print(s);
		OrderItemListDTO  csdto  = PojoMapper.fromJsonAsObject(s,  OrderItemListDTO.class );
		 
	}
	/**
	 * 获取服务价格列表
	 * pass
	 */
	@Test
	public void getServicePriceList(){
		System.out.println("================获取服务价格列表==============");
		String s = postData(RESTurl.getServicePriceList,"1234");
		System.out.print(s);
		//ServiceTypeListDTO  csdto  = PojoMapper.fromJsonAsObject(s,  ServiceTypeListDTO.class);
	}
	
	
	/** 
	 *  保存订单   pass
	 *  支付完成   pass
	 */
	@Test
	public void saveOrder  (){
		System.out.println("================ 保存订单==============");
		
		OrderItemDTO dto =new OrderItemDTO();
		dto.setAmount(10);
		dto.setBuyYear(2);
		//dto.setEndDate(endDate);
		dto.setEntId(1234);
		dto.setItemName("jiaoyi");
		dto.setOffset(0);
		dto.setOrderId(3241);
		dto.setPerPrice(100);
		dto.setPrice(880);
		dto.setRatio(0.88f);
		dto.setSave(240);
		dto.setServiceId(2);
		dto.setServiceName("dier");
		dto.setStartDate(new Date());
		
		List<OrderItemDTO> dtoList = new ArrayList<OrderItemDTO>();
		dtoList.add(dto);
		
		
		OrderDTO order = new OrderDTO();
		order.setCreateDate(new Date());
		order.setOrderItems(dtoList);
		order.setOrderId(5889);
		order.setEntId(1234);
		order.setOffset(0);
		order.setEntName("");
		order.setPayDate(null);
		order.setPrice(3041);
		order.setSave(408);
		order.setStatus("UNPAY");
		order.setTradeNo("60398"+new Random().nextInt(1000));
		order.setType("NORMAL");
		//String	result = orderLogic.saveOrder(order);
		//orderLogic.
		//System.out.println(result);
		
		String s = postData(RESTurl.saveOrder ,order);
		System.out.print(s);
		
		if("error500".equals(s)){
			fail(s);
		}
		assertEquals(CommConstants.OK_MARK, s);
		
		System.out.println("================ 支付完成==============");
		
		String re = postData(RESTurl.payComplete,order);
		System.out.println(re);
		if("error500".equals(re)){
			fail(re);
		}
		assertEquals(CommConstants.OK_MARK, re);
	}

}
