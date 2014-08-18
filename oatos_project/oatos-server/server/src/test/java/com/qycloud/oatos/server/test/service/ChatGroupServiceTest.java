package com.qycloud.oatos.server.test.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.conlect.oatos.dto.client.ChatGroupDTO;
import com.conlect.oatos.dto.client.ChatGroupMemberDTO;
import com.conlect.oatos.dto.client.ChatGroupMembersDTO;
import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;

/**
 * 
 * @author jiuyuehe
 * pass
 */
public class ChatGroupServiceTest extends BaseServiceTest {
	
	private static String CHATGROUP_ID="group_id";
	
	/**
	 * 新建临时聊天群
	 */
	@Test
	public void addGroup(){
		System.out.println("------------新建临时聊天群---------------");
		//参数
		 ChatGroupDTO cg = new  ChatGroupDTO();
		 cg.setGroupName("就是要聊天");
		// cg.setUserId();
		//地址
		String res = postData(RESTurl.addGroup,cg);
		System.out.println(res);
		
		//验证
		if("error500".equals(res)){
			fail(res);
		}
		
		//assertEquals(CommConstants.OK_MARK, res);
		assertNotNull(res);
		
		idmap.put(CHATGROUP_ID, Long.parseLong(res));
		
	}
	
	/**
	 *添加群成员
	 */
	@Test
	public void addGroupMember(){
		System.out.println("------------添加群成员---------------");
		//参数
		ChatGroupMemberDTO ms = new  ChatGroupMemberDTO();
		ms.setMemberName("testmp873");
		ms.setMemberUserId(104850);
		ms.setGroupId(idmap.get(CHATGROUP_ID));
		//ms.setMemberUserId(memberUserId);
		ms.setUserHeaderPhoto("");
		ms.setUserId(116255);
		
		//地址
		String res = postData(RESTurl.addGroupMember,ms);
		System.out.println(res);
		
		//验证
		if("error500".equals(res)){
			fail(res);
		}
		
		//assertEquals(ErrorType.errorExitMemeber.toString(), res);
	}
	

	/**
	 * 取群众成员
	 */
	@Test
	public void getGroupMembers(){
		System.out.println("------------取群众成员---------------");
		//参数
		//ChatGroupMemberDTO ms = new  ChatGroupMemberDTO();
		String groupId =""+idmap.get(CHATGROUP_ID);
		//地址
		String res = postData(RESTurl.getGroupMembers,groupId);
		System.out.println(res);
		
		//验证
		if("error500".equals(res)){
			fail(res);
		}
		ChatGroupMembersDTO ccdto = PojoMapper.fromJsonAsObject(res,  ChatGroupMembersDTO.class);
		
		//System.out.println(ccdto.getChatGroupMemberDTOs().size());
		
		//assertEquals(ccdto.getChatGroupMemberDTOs().get(0).getGroupId(), 260569);
		
	}
	
	/**
	 * 退出群
	 */
	@Test
	public void removeGroupMember(){
		System.out.println("------------ 退出群---------------");
		//参数
		ChatGroupMemberDTO ms = new  ChatGroupMemberDTO();
		ms.setMemberName("testmp873");
		ms.setMemberUserId(104850);
		ms.setGroupId(idmap.get(CHATGROUP_ID));
		//ms.setMemberUserId(memberUserId);
		ms.setUserHeaderPhoto("");
		ms.setUserId(116255);
		
		
		//地址
		String res = postData(RESTurl.removeGroupMember,ms);
		System.out.println(res);
		
		//验证
		if("error500".equals(res)){
			fail(res);
		}
		
		assertEquals(CommConstants.OK_MARK, res);
	}
	
	/**
	 * 删除聊天群
	 */
	@Test
	public void deleteGroup(){
		System.out.println("------------ 删除聊天群---------------");
		//参数
		ChatGroupDTO ms = new  ChatGroupDTO();
		ms.setId(idmap.get(CHATGROUP_ID));
		ms.setUserId(116255);
		
		//地址
		String res = postData(RESTurl.deleteGroup,ms);
		System.out.println(res);
		
		//验证
		if("error500".equals(res)){
			fail(res);
		}
		
		assertEquals(CommConstants.OK_MARK, res);
	}
	
}
