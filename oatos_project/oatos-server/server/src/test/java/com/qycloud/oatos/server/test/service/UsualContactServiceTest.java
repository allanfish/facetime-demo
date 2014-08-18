package com.qycloud.oatos.server.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.FailedLoginException;
import javax.validation.constraints.AssertFalse;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.conlect.oatos.dto.client.UsualContactDTO;
import com.conlect.oatos.dto.client.UsualContactGroupDTO;
import com.conlect.oatos.dto.client.UsualContactGroupsDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.dto.status.url.UsualContactUrl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.dao.UsualContactMapper;
import com.qycloud.oatos.server.domain.logic.SequenceLogic;

/**
 * UsualContactService服务测试类
 * @author huhao
 * @update xiao.min 
 * @date
 */
public class UsualContactServiceTest extends BaseServiceTest {
	
	
	/**
	 * 添加常用联系人
	 * pass
	 */
	@Test
	public void setUsualContactListByUserId(){
		System.out.println("=============设置常用联系人==============");
		
		List<Long> usualContactUserList = new ArrayList<Long>();
		usualContactUserList.add(23L);
		usualContactUserList.add(0L);
		
		
		UsualContactDTO aContactDTO = new UsualContactDTO(420L,usualContactUserList);
		String postData = PojoMapper.toJson(aContactDTO);
		String s = post(RESTurl.setUsualContactListByUserId,postData);
		System.out.print(s);
		
	}
	
	/**
	 * 得到常用联系人的分组列表和详细信息
	 *    bug
	 */
	@Test
	public void getUsualContactGroupsByUserId(){
		System.out.println("===========================");
		System.out.println("得到常用联系人的分组列表和详细信息");
		String s = post(UsualContactUrl.getUsualContactGroupsByUserId,"101287");
		System.out.println(s);
		UsualContactGroupsDTO usualContactGroupsDTO = PojoMapper.fromJsonAsObject(s, UsualContactGroupsDTO.class);
		assertNotNull(usualContactGroupsDTO);
		assertEquals(-1, usualContactGroupsDTO.getUserList().get(0).getAge());
		System.out.println(usualContactGroupsDTO.getUserList().get(0).getAge());
		if (usualContactGroupsDTO.getUserList().get(0).getAge()!=-1) {
			fail("-1");
		}
		
		System.out.println("测试成功！");
		System.out.println("=========================");
		
	}
	
	/**
	 *  删除常用联系人
	 *  pass
	 */
	@Test
	public void deleteUsualContact(){
		List<Long> usualContactUserList = new ArrayList<Long>();
		usualContactUserList.add(0L);
		usualContactUserList.add(23L);
		
		UsualContactDTO aContactDTO = new UsualContactDTO(420L,usualContactUserList);
		String postData = PojoMapper.toJson(aContactDTO);
		String s = post(RESTurl.deleteUsualContact,postData);
		System.out.print(s);
	}
	
	/**
	 * 创建常用联系人分组
	 * pass
	 */
	@Test
	public void addUsualContactGroup(){
		System.out.println("-----------创建常用联系人分组---------------");
		UsualContactGroupDTO usualContactGroupDTO = new UsualContactGroupDTO();
		//usualContactGroupDTO.setGroupId(sequence.getNextId());
		usualContactGroupDTO.setName("深圳好友1");
		usualContactGroupDTO.setOrderValue(1);
		usualContactGroupDTO.setUserId(0L);
		String responseBody = post(RESTurl.addUsualContactGroup,PojoMapper.toJson(usualContactGroupDTO));
		System.out.println(responseBody);
		
		
	}
	/***
	 * 更新好友联系分组
	 * pass
	 */
	@Test
	public void updateUsualContactGroup(){
		UsualContactGroupDTO usualContactGroupDTO = new UsualContactGroupDTO();
		usualContactGroupDTO.setGroupId(8224L);
		usualContactGroupDTO.setName("深圳好友2");
		usualContactGroupDTO.setOrderValue(1);
		usualContactGroupDTO.setUserId(0L);
		String responseBody = post(RESTurl.updateUsualContactGroup,PojoMapper.toJson(usualContactGroupDTO));
		System.out.println(responseBody);
	}
	/***
	 * 删除好友联系人
	 * pass
	 */
	@Test
	public void deleteUsualContactGroup(){
		UsualContactGroupDTO usualContactGroupDTO = new UsualContactGroupDTO();
		usualContactGroupDTO.setGroupId(8424L);
		String responseBody = post(RESTurl.deleteUsualContactGroup,PojoMapper.toJson(usualContactGroupDTO));
		System.out.println(responseBody);
	}
	
	
	/**
	 * 更新好友联系人
	 * pass
	 */
	@Test
	public void  updateUsualContact() {
		System.out.println("=====================");
		System.out.println("测试更新常用联系人组");
		UsualContactDTO usualContactDTO = new UsualContactDTO();
		usualContactDTO.setGroupId(3L);
		usualContactDTO.setUserId(0L);
		List<Long> blackUserList = new ArrayList<Long>();
		blackUserList.add(1L);
		blackUserList.add(23L);
		usualContactDTO.setUsualContactUserList(blackUserList);
		String s = post(UsualContactUrl.updateUsualContact, PojoMapper.toJson(usualContactDTO));
		System.out.println(s);
		if (!s.endsWith(CommConstants.OK_MARK)) {
			fail(s);
		}
		System.out.println("测试通过!");
		System.out.println("====================");
	}
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	

	
	
	
}
