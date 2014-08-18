/**
 * 
 */
package com.qycloud.oatos.server.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.conlect.oatos.dto.client.GetHistoryDTO;
import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.client.MessagesDTO;
import com.conlect.oatos.dto.client.SysMsgDTO;
import com.conlect.oatos.dto.client.UserDeviceTokensDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;

/**
 * @author xiao.min
 *pass
 */
public class MessageServiceTest extends BaseServiceTest {
	
	
	/**
	 * 处理消息，消息设为已读
	 * pass
	 */
	@Test
	public void confirmMessage (){
		System.out.println("------------处理消息，消息设为已读---------------");
		//参数
		MessageDTO ms = new MessageDTO();
		ms.setMessageId("261064");
		//ms.setChatId(105);
		ms.setFromUser("34");
	//	ms.setConfirmed(false);
		ms.setReceiver(40);
		ms.setReceiverName("40");
		ms.setMessageType("CHAT");
		ms.setSender(34L);
		
		//地址
		String res = postData(RESTurl.confirmMessage,ms);
		System.out.println(res);
		
		//验证
		if("error500".equals(res)){
			fail(res);
		}
		
		assertEquals(CommConstants.OK_MARK, res);
	}
	
	/**
	 * 分页取聊天历史记录
	 *   pass
	 */
	@Test
	public void getChatHistory (){
		System.out.println("------------分页取聊天历史记录---------------");
		//参数
		GetHistoryDTO gh = new GetHistoryDTO();
		gh.setUserId(34);
		gh.setMaxResults(200);
		gh.setSkipResults(0);
		gh.setBuddyUserId(40);
		//地址
		String res = postData(RESTurl.getChatHistory,gh);
		System.out.println(res);
		
		//验证
		if("error500".equals(res)){
			fail(res);
		}
		MessagesDTO mess = PojoMapper.fromJsonAsObject(res, MessagesDTO.class);
	}
	/**
	 *  取企业信息记录
	 *  pass
	 */
	@Test
	public void getEntRecord  (){
		System.out.println("------------ 取企业信息记录---------------");
		//参数
		MessageDTO ms = new MessageDTO();
	//	ms.set
		
		//地址
		String res = postData(RESTurl.getEntRecord,ms);
		System.out.println(res);
		
		//验证
		if("error500".equals(res)){
			fail(res);
		}
		MessagesDTO mess = PojoMapper.fromJsonAsObject(res, MessagesDTO.class);
		//assertEquals(CommConstants.OK_MARK, res);
	}
	/**
	 *   根据发送者及消息类型取消息记录
	 *   pass
	 */
	@Test
	public void getMessageRecord  (){
		System.out.println("------------  根据发送者及消息类型取消息记录---------------");
		//参数
		//MessageDTO ms = new MessageDTO();
		String userId ="40";
		
		//地址
		String res = postData(RESTurl.getMessageRecord,userId);
		System.out.println(res);
		
		//验证
		if("error500".equals(res)){
			fail(res);
		}
		MessagesDTO mess = PojoMapper.fromJsonAsObject(res, MessagesDTO.class);
		//assertEquals(CommConstants.OK_MARK, res);
	}
	/**
	 *  取未读消息
	 *  pass
	 */
	@Test
	public void getUnreadMessage (){
		System.out.println("------------ 取未读消息---------------");
		//参数
		String userId ="40";
		
		//地址
		String res = postData(RESTurl.getUnreadMessage,userId);
		System.out.println(res);
		
		//验证
		if("error500".equals(res)){
			fail(res);
		}
		MessagesDTO mess = PojoMapper.fromJsonAsObject(res, MessagesDTO.class);
		//assertEquals(CommConstants.OK_MARK, res);
	}
	/**
	 *  返回未读的系统消息
	 *  pass
	 */
	@Test
	public void getUnsendSysMsg (){
		System.out.println("------------ 返回未读的系统消息---------------");
		//参数
	//	MessageDTO ms = new MessageDTO();
		
		//地址
		String res = postData(RESTurl.getUnsendSysMsg,null);
		System.out.println(res);
		
		//验证
		if("error500".equals(res)){
			fail(res);
		}
		 SysMsgDTO mess = PojoMapper.fromJsonAsObject(res,  SysMsgDTO.class);
		//assertEquals(CommConstants.OK_MARK, res);
	}
	/**
	 *  获取有离线消息用户的ios设备标志
	 *  pass
	 */
	@Test
	public void getUserDeviceTokensHasUnreadMsg   (){
		System.out.println("------------ 获取有离线消息用户的ios设备标志---------------");
		//参数
		//MessageDTO ms = new MessageDTO();
		
		//地址
		String res = postData(RESTurl.getUserDeviceTokensHasUnreadMsg,null);
		System.out.println(res);
		
		//验证
		if("error500".equals(res)){
			fail(res);
		}
		 UserDeviceTokensDTO mess = PojoMapper.fromJsonAsObject(res,  UserDeviceTokensDTO.class);
		assertEquals(mess.getUserDeviceTokenList().get(0).getUserId(), 103527);
	}
	/**
	 *   判断是否有未读消息
	 *   pass
	 */
	@Test
	public void isExistUnreadMessage(){
		System.out.println("------------ 判断是否有未读消息--------------");
		//参数
		//MessageDTO ms = new MessageDTO();
		String userId ="40";
		//地址
		String res = postData(RESTurl.isExistUnreadMessage,userId);
		System.out.println(res);
		
		//验证
		if("error500".equals(res)){
			fail(res);
		}
		MessagesDTO mess = PojoMapper.fromJsonAsObject(res, MessagesDTO.class);
		//assertEquals(CommConstants.OK_MARK, res);
	}
	/**
	 *   批量保存消息至数据库
	 *   pass
	 */
	@Test
	public void saveMessages(){
		System.out.println("------------ 批量保存消息至数据库---------------");
		//参数
		MessageDTO ms = new MessageDTO();
		ms.setMessageBody("你好，你是谁，请问你是帅，不说我就开枪了");
		ms.setMessageType("Chat");
		ms.setFromUser("34");
		ms.setReceiverName("");
		ms.setReceiver(40);
		ms.setStatus("New");
		ms.setSender(34l);
		ms.setConfirmed(false);
		ms.setChatId(1231456);
		ms.setDisplaying(true);
		
		MessageDTO ms1 = new MessageDTO();
		ms1.setMessageBody("你好，你是谁，请问你是帅，不说我就开枪了");
		ms1.setMessageType("Chat");
		ms1.setFromUser("34");
		ms1.setReceiver(40);
		ms1.setStatus("New");
		ms1.setSender(34l);
		ms1.setConfirmed(false);
		ms1.setChatId(1231456);
		ms1.setDisplaying(true);
		
		
		List<MessageDTO> dtoList = new ArrayList<MessageDTO>();
		dtoList.add(ms);
		dtoList.add(ms1);
		
		MessagesDTO msList = new MessagesDTO();
		msList.setMessageDTOList(dtoList);
		
	//	messageLogic.saveMessages(dtoList);
		
		//地址
		String res = postData(RESTurl.saveMessages,msList);
		System.out.println(res);
		
		//验证
		if("error500".equals(res)){
			fail(res);
		}
		MessagesDTO mess = PojoMapper.fromJsonAsObject(res, MessagesDTO.class);
		assertEquals(CommConstants.OK_MARK, res);
	}
	/**
	 *  保存系统消息
	 *  pass
	 */
	@Test
	public void saveSysMsgs   (){
		System.out.println("------------ 保存系统消息- 后台未实现--------------");
		//参数
//		MessageDTO ms = new MessageDTO();
//		ms.setConfirmed(confirmed);
//		ms.setLanguage(language);
//		ms.setMessageType(messageType);
//		
//		MessagesDTO mssdto =new MessagesDTO();
//		mssdto.setMessageDTOList(messagesDTOs);
		//地址
		//String res = postData(RESTurl.saveSysMsgs,ms);
//		System.out.println(res);
//		
//		//验证
//		if("error500".equals(res)){
//			fail(res);
//		}
////		MessagesDTO mess = PojoMapper.fromJsonAsObject(res, MessagesDTO.class);
//		assertEquals(CommConstants.OK_MARK, res);
	}
	/**
	 *   保存消息至数据库
	 *   pass
	 */
	@Test
	public void sendMessage(){
		System.out.println("------------  保存消息至数据库---------------");
		//参数
		MessageDTO ms1 = new MessageDTO();
		ms1.setMessageBody("你好，你是谁，请问你是帅，不说我就开枪了");
		ms1.setMessageType("Chat");
		ms1.setFromUser("34");
		ms1.setReceiver(40);
		ms1.setStatus("New");
		ms1.setSender(34l);
		ms1.setConfirmed(false);
		ms1.setChatId(1231456);
		ms1.setDisplaying(true);
		
		//地址
		String res = postData(RESTurl.sendMessage,ms1);
		System.out.println(res);
		
		//验证
		if("error500".equals(res)){
			fail(res);
		}
		//MessagesDTO mess = PojoMapper.fromJsonAsObject(res, MessagesDTO.class);
		assertEquals(CommConstants.OK_MARK, res);
	}

}
