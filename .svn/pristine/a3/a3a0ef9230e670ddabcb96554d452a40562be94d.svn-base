package com.qycloud.oatos.server.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.conlect.oatos.dto.client.DepartmentAndUserDTO;
import com.conlect.oatos.dto.client.EnterpriseUserDTO;
import com.conlect.oatos.dto.client.SimpleUserInfosDTO;
import com.conlect.oatos.dto.client.UserInfoCategoryDTO;
import com.conlect.oatos.dto.client.UserInfoDTO;
import com.conlect.oatos.dto.client.UserInfosDTO;
import com.conlect.oatos.dto.client.UserStatusesDTO;
import com.conlect.oatos.dto.client.UsualContactDTO;
import com.conlect.oatos.dto.client.user.UserImportSaveDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.dto.status.UserStatus;
import com.conlect.oatos.http.PojoMapper;


import com.qycloud.oatos.server.security.Security;

/**
 * UserService服务测试类
 * @author xiao.min
 * pass  如果测试未覆盖，请及时向我反映 xiaomin@qycloud.com
 * 
 *
 */
public class UserServiceTest extends BaseServiceTest {
	
	private static String NEWUSERID = "userID";

	/**
	 * 添加常用联系人
	 * pass
	 */
	@Test
	public void setUsualContactListByUserId(){
		System.out.println("===========================添加常用联系人===============================");
		List<Long> usualContactUserList = new ArrayList<Long>();
		usualContactUserList.add(new Long(221));
		usualContactUserList.add(new Long(227));
		
		UsualContactDTO aContactDTO = new UsualContactDTO(new Long(233),usualContactUserList);
		//String postData = PojoMapper.toJson(aContactDTO);
		String s = postData(RESTurl.setUsualContactListByUserId,aContactDTO);
		System.out.print(s);
		if("error500".equals(s)){
			fail(s);
		}
		assertEquals(CommConstants.OK_MARK, s);
		
		System.out.println("====================删除常用联系人===========================");
		
		String s1 = postData(RESTurl.deleteUsualContact,aContactDTO);
		System.out.print(s1);
		if("error500".equals(s1)){
			fail(s1);
		}
		assertEquals(CommConstants.OK_MARK, s1);
	}
	
	
	
	
	
	/**
	 * 添加企业员工
	 * pass
	 */
	@Test
	public void addEnterpriseUser(){
	
		List<EnterpriseUserDTO> udtos = new ArrayList<EnterpriseUserDTO>();
		
		System.out.println("================添加企业员工================");
		EnterpriseUserDTO eUserDTO = new EnterpriseUserDTO();
		eUserDTO.setAccount("testemp"+new Random().nextInt(100000));
		eUserDTO.setEnterpriseId(1234);
		//eUserDTO.setUserId(sequence.getNextId());
		//给密码加密
	    String p ="123456";
		String noce = Security.randomCharString();
		String sha256 = Security.SHA256(p);
		String hash = Security.SHA256(eUserDTO.getAccount() + sha256 + noce);
		String h = Security.byteStringToHexString(sha256);
		String ep = new Security().codeDecode(noce, h);
		eUserDTO.setPassword(ep);
		eUserDTO.setNonce(noce);
		eUserDTO.setHashKey(hash);
		
		EnterpriseUserDTO eUserDTO2 = new EnterpriseUserDTO();
		eUserDTO2.setAccount("testemp"+new Random().nextInt(100000));
		eUserDTO2.setEnterpriseId(1234);
		//给密码加密
		String p1 ="123456";
		String noce1 = Security.randomCharString();
		String sha2561 = Security.SHA256(p1);
		String hash1 = Security.SHA256(eUserDTO2.getAccount() + sha2561 + noce1);
		String h1 = Security.byteStringToHexString(sha2561);
		String ep1 = new Security().codeDecode(noce1, h1);
		
		eUserDTO2.setPassword(ep1);
		eUserDTO2.setNonce(noce1);
		eUserDTO2.setHashKey(hash1);
		
		String s = postData(RESTurl.addEnterpriseUser,eUserDTO);
		System.out.println(s);
			if("error500".equals(s)){
				fail(s);
			}
		idmap.put(NEWUSERID, Long.parseLong(s));
			

		System.out.println("============用户批量导入====================");
		
		//udtos.add(eUserDTO);
		udtos.add(eUserDTO2);
		
		UserImportSaveDTO  imports = new UserImportSaveDTO();
		imports.setEntId(1234);
		imports.setUserList(udtos);
 		
		//userLogic.importUser(userImportDTO)
		String s2 = postData(RESTurl.addEnterpriseUserList,imports );
		System.out.println(s2);
		
		if("error500".equals(s2)){
			fail(s2);
		}
		assertEquals(CommConstants.OK_MARK,s2);
		
		
//		List<EnterpriseUserDTO> udtos = new ArrayList<EnterpriseUserDTO>();
//		EnterpriseUserDTO eUserDTO = new EnterpriseUserDTO();
		System.out.println("============删除企业员工=单独测试通过===================");
		eUserDTO.setUserId(eUserDTO.getUserId());
		//eUserDTO.setUserId(104514l);
		//udtos.add(eUserDTO);
//		String s3 = postData(RESTurl.deleteEnterpriseUsers ,udtos);
//		System.out.println(s3);
//		if("error500".equals(s3)){
//			fail(s3);
//		}
//		assertEquals(CommConstants.OK_MARK,s3);
		
		
	}
	/**
	 * 修改员工信息和登录密码
	 */
	@Test
	public void updateEnterpriseUserAndPassword(){
		
		
		
//		System.out.println("=============批量修改用户信息=================");
//		UserInfoDTO uto2 = new UserInfoDTO();
//		String re1 = postData(RESTurl.updateUsers,uto2);
//		
//		if("error500".equals(re1)){
//			fail(re1);
//		}
//		assertEquals(CommConstants.OK_MARK, re1);
	}
	
	
	/***
	 * 删除员工
	 */
	@Test
	public void deleteEnterpriseUsers(){
		
	}
	
	
	
	/***
	 * 获取单个用户信息
	 * pass
	 */
	@Test
	public void getUserProfile(){
		System.out.println("+++++++++++++++获取单个用户信息+++++++++++++++");
		
		String s4 = postData(RESTurl.getUserProfile,userId3885+"");
		System.out.println(s4);
		if("error500".equals(s4)){
			fail(s4);
		}
		UserInfoDTO cate = PojoMapper.fromJsonAsObject(s4, UserInfoDTO.class);
		assertEquals(cate.getUserId()+"",userId3885+"");
		
		
		System.out.println("==========修改员工信息和登录密码===========");
		
		//UserInfoDTO info = new UserInfoDTO();
		//info.setUserId(117184);
//		String p1 ="123456";
//		String noce1 = Security.randomCharString();
//		String sha2561 = Security.SHA256(p1);
//		String hash1 = Security.SHA256(info.getUserName() + sha2561 + noce1);
//		String h1 = Security.byteStringToHexString(sha2561);
//		String ep1 = new Security().codeDecode(noce1, h1);
//		info.setNonce(noce1);
		
		//info.setHashKey(hash1);
		cate.setUserName("疯癫打折");
		//info.setPassword(ep1);
		
	//	String re = postData(RESTurl.updateEnterpriseUserAndPassword,cate);
		//assertEquals(CommConstants.OK_MARK, re);
	}
	
	
	
	
	/***
	 * 修改用户信息
	 *  pass
	 */
	@Test
	public void updateUserProfile(){
		System.out.println("==================修改用户信息======================");
		UserInfoDTO uto = new UserInfoDTO();
		uto.setUserId(0L);
		uto.setUserName("中国人");
		uto.setContactDisplay(1);
		
		String s5 = postData(RESTurl.updateUserProfile,uto);
		System.out.print(s5);
		if("error500".equals(s5)){
			fail(s5);
		}
		assertEquals(CommConstants.OK_MARK, s5);
	}
	
	/***
	 * 取在线用户和离线用户ID
		pass
	 */
	@Test
	public void getAllUserIds (){
		
		System.out.println("============取在线用户和离线用户ID====================");
		String s = postData(RESTurl.getAllUserIds,null);
		System.out.println(s);
		if("error500".equals(s)){
			fail(s);
		}
		
		UserInfoCategoryDTO cate = PojoMapper.fromJsonAsObject(s, UserInfoCategoryDTO.class);
		System.out.println(cate.getOnlineUserIds().size());
		System.out.println(cate.getOfflineUserIds().size());
		
		
	}
	/***
	 * 按企业id取企业部门和成员，不包括角色和状态
	 * pass
	 */
	@Test
	public void getDepartmentAndUserByEntId (){
		System.out.println("-------按企业id取企业部门和成员，不包括角色和状态--------");
		String s = postData(RESTurl.getDepartmentAndUserByEntId,"1234");
		System.out.println(s);
		if("error500".equals(s)){
			fail(s);
		}
		DepartmentAndUserDTO cate = PojoMapper.fromJsonAsObject(s, DepartmentAndUserDTO.class);
		
	}
	
	/***
	 * 按企业id取企业部门和成员，包含角色，不包含状态
	 * pass
	 */
	@Test
	public void getDepartmentAndUserWithRolesByEntId (){
		System.out.println("-------按企业id取企业部门和成员，包含角色，不包含状态--------");
		String s = postData(RESTurl.getDepartmentAndUserWithRolesByEntId,"1234");
		System.out.println(s);
		
		if("error500".equals(s)){
			fail(s);
		}
		DepartmentAndUserDTO cate = PojoMapper.fromJsonAsObject(s, DepartmentAndUserDTO.class);
		assertEquals(cate.getDepartmentList().get(0).getDepartmentId(), 1262);
		
	}
	/***
	 *按用户id取企业部门和成员,包含在线状态，不包含角色信息
	 *pass
	 */
	@Test
	public void getDepartmentAndUserWithStatusByUserId (){
		System.out.println("==============按企业id取企业部门和成员，包含角色，不包含状态============");
		String s = postData(RESTurl.getDepartmentAndUserWithStatusByUserId,"204");
		System.out.println(s);
		
		if("error500".equals(s)){
			fail(s);
		}
		if(s!=null){
		DepartmentAndUserDTO cate = PojoMapper.fromJsonAsObject(s, DepartmentAndUserDTO.class);
		}
		
	}
	
	/***
	 *获取所有同事的在线状态
	 * pass  
	 */
	@Test
	public void getEmployeesStatus (){
		System.out.println("================获取所有同事的在线状态================");
		String s = postData(RESTurl.getEmployeesStatus,"1234");
		System.out.println(s);
		
		if("error500".equals(s)){
			fail(s);
		}
		//格式转化出错
		UserStatusesDTO userStatus = PojoMapper.fromJsonAsObject(s, UserStatusesDTO.class);
		
	}
	/***
	 *按企业id取企业用户，包含角色信息
	 *  pass
	 */
	@Test
	public void getEnterpriseUserListWithRoles (){
		System.out.println("============按企业id取企业用户，包含角色信息===============");
		String s = postData(RESTurl.getEnterpriseUserListWithRoles,"1234");
		
		System.out.println(s);
		
		if("error500".equals(s)){
			fail(s);
		}
		UserInfosDTO cate = PojoMapper.fromJsonAsObject(s, UserInfosDTO.class);
		//assertEquals(cate.getUserInfoDTOList().get(0).getUserId(), 104075);
		
	}
	
	/***
	 *取在线同事(基本信息)
	 * pass
	 */
	@Test
	public void getOnlineBuddyAndColleague (){
		System.out.println("++++++++++++ 取在线同事(基本信息) +++++++++++++++");

		String s = postData(RESTurl.getOnlineBuddyAndColleague,"103510");
		System.out.println("re---> "+s);

		if("error500".equals(s)){
			fail(s);
		}
		SimpleUserInfosDTO cate = PojoMapper.fromJsonAsObject(s, SimpleUserInfosDTO.class);
		//assertEquals(cate.getUserList().get(0).getUserId(), 103587);
		
	}
	
	
	/***
	 *按企业id取企业用户，不包括状态及角色
	 * pass
	 */
	@Test
	public void getUsersByEntId(){
		System.out.println("============按企业id取企业用户，不包括状态及角色===============");
		String s = postData(RESTurl.getUsersByEntId,"1234");
		
		System.out.println(s);
		if("error500".equals(s)){
			fail(s);
		}
		UserInfosDTO cate = PojoMapper.fromJsonAsObject(s, UserInfosDTO.class);
		System.out.println(cate.getUserInfoDTOList().size());	
	}
	
	/***
	 *取某用户的在线状态
	 *pass
	 */
	@Test
	public void getUserStatus(){
		System.out.println("====================取某用户的在线状态==================");


		String s = postData(RESTurl.getUserStatus,"58");

		
		System.out.println(s);
		if("error500".equals(s)){
			fail(s);
		}
		assertEquals(UserStatus.Logout, s);
	}
	/***
	 *统计企业在线用户数
	 * bug  405 Method Not Allowed
	 */
	@Test
	public void statisticalUser(){
		System.out.println("============统计企业在线用户数==========");
		String s = get(RESTurl.statisticalUser);
		System.out.println(s);
		if("error500".equals(s)){
			fail(s);
		}
		
	}
	
	
	
	
	

	
	
	
}
