package com.qycloud.oatos.server.test.service;

import org.junit.Test;
import static org.junit.Assert.*;
import org.unitils.spring.annotation.SpringBeanByType;

import com.conlect.oatos.dto.client.EnterpriseLoginDTO;
import com.conlect.oatos.dto.client.PasswordChangeDTO;
import com.conlect.oatos.dto.client.ReLoginDTO;
import com.conlect.oatos.dto.client.UserStatusDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.dao.LoginLogMapper;
import com.qycloud.oatos.server.dao.LoginMapper;
import com.qycloud.oatos.server.security.Security;
/**
 * 登录服务测试用例
 * @author xiao.min
 * pass
 */
public class LoginServiceTest extends BaseServiceTest {
	
	
	
	
	/**
	 * 企业管理员登录, 验证token
	 * pass
	 */
	@Test
	public void adminLogin(){
		System.out.println("===========企业管理员登录=========");
		EnterpriseLoginDTO  login = new EnterpriseLoginDTO();
		login.setEnterpriseName("yufei");
		String account = "admin";
		String p ="123456";
		
		String noce = Security.randomCharString();
		String sha256 = Security.SHA256(p);
		String hash = Security.SHA256(account + sha256 + noce);
		String h = Security.byteStringToHexString(sha256);
		String ep = new Security().codeDecode(noce, h);
		
		
		
		//EnterpriseLoginDTO login = new EnterpriseLoginDTO();
		login.setAccount(account);
		System.out.println(account);
		login.setPassword(ep);
		login.setNonce(noce);
		login.setHashKey(hash);
		
		//System.out.println(pwd);
		//login.setPassword(pwd);
		String token  = postData(RESTurl.adminLogin,login);
		System.out.println(token);
		
		System.out.println("=========== 检查token是否有效 =========");
		//验证token
		String realTonken = token.substring(6,token.length());
		System.out.println("real Tonken");
		String re  = postData(RESTurl.checkToken ,realTonken);
		System.out.println(re);
		
		
		
		
	}
	/**
	 * 修改企业管理员登录密码=
	 * pass
	 */
	@Test
	public void changeAdminPassword(){
		System.out.println("===========修改企业管理员登录密码=========");
		
		//-----------------------
		String old ="123456";
		
		//修改密码
		PasswordChangeDTO pwdChange = new  PasswordChangeDTO();
		
		String p ="1234567";
		
		String noce = Security.randomCharString();
		String sha256 = Security.SHA256(p);
		String h = Security.byteStringToHexString(sha256);
		String ep = new Security().codeDecode(noce, h);
		String hash = Security.SHA256(pwdChange.getId() + sha256 + noce);
		
		pwdChange.setId(10313);
		pwdChange.setNewPassword(ep);
		pwdChange.setNonce(noce);
		
		pwdChange.setHashKey(hash);
		
		pwdChange.setOldPasswrod(Security.SHA256(old));
		 
		String re1  = postData(RESTurl.changeAdminPassword,pwdChange);
		System.out.println(re1);
		if("error500".equals(re1)){
			fail(re1);
		}
		//assertEquals(CommConstants.OK_MARK, re1); 
		assertEquals(ErrorType.errorCheckHashkey.toString(), re1); 
		
	}
	/**
	 * 修改用户密码
	 * ·pass
	 */
	@Test
	public void changePassword(){
		System.out.println("===========修改用户密码=========");
		PasswordChangeDTO pwdChange = new  PasswordChangeDTO();
		pwdChange.setId(61054);
		
		String old ="1234567";
		String p2 ="123456";
		
		pwdChange.setOldPasswrod(Security.SHA256(old));
		
		String nonce = Security.randomCharString();
		String sha256 = Security.SHA256(p2);
		String h = Security.byteStringToHexString(sha256);
		String ep = new Security().codeDecode(nonce, h);
		String hash = Security.SHA256(pwdChange.getId() + sha256 + nonce);
		
		pwdChange.setNewPassword(ep);
		pwdChange.setHashKey(hash);
		pwdChange.setNonce(nonce);
		
		String re  = postData(RESTurl.changePassword,pwdChange);
		
		System.out.println(re);
		
		if("error500".equals(re)){
			fail(re);
		}
		//assertEquals(CommConstants.OK_MARK, re);
		assertEquals(ErrorType.errorWrongOldPWD.toString(), re);
		
	}

	/**
	 * 修改用户状态=
	 * ·pass
	 */
	@Test
	public void changeUserStatus(){
		System.out.println("=========== 修改用户状态=========");
		UserStatusDTO status = new  UserStatusDTO();
		status.setUserId(61054);
		status.setUserStatus("REG");
		 
		String re  = postData(RESTurl.changeUserStatus,status);
		
		if("error500".equals(re)){
			fail(re);
		}
		assertEquals(CommConstants.OK_MARK, re);
	}
	
	/**
	 * 验证用户登录密码 
	 * pass
	 */
	@Test
	public void checkUserPassword(){
		System.out.println("===========验证用户登录密码 =========");
		 PasswordChangeDTO pwdChange = new  PasswordChangeDTO();
			pwdChange.setId(61054);
			
			String old ="1234567";
			String p2 ="1234567";
			
			//pwdChange.setOldPasswrod(Security.SHA256(old));
			
			String nonce = Security.randomCharString();
			String sha256 = Security.SHA256(old);
			String h = Security.byteStringToHexString(sha256);
			String ep = new Security().codeDecode(nonce, h);
			String hash = Security.SHA256(pwdChange.getId() + sha256 + nonce);
			
			pwdChange.setOldPasswrod(ep);
			pwdChange.setHashKey(hash);
			pwdChange.setNonce(nonce);
		 
		 String re  = postData(RESTurl.checkUserPassword ,pwdChange);
			System.out.println(re);
			
			if("error500".equals(re)){
				fail(re);
			}
		//	assertEquals(CommConstants.OK_MARK, re);
				assertEquals(ErrorType.errorWrongPWD.toString(), re);
		
	}
	/**
	 *  企业用户登录服务
	 *  其他·pass，退出bug
	 */
	@Test
	public void enterpriseUserLogin(){
		System.out.println("=========== 企业用户登录服务=========");
		 EnterpriseLoginDTO enterLogin = new  EnterpriseLoginDTO();
		 
		 
		 enterLogin.setEnterpriseName("yufei");
			String account = "王飞";
			String p ="123456";
			
			String noce = Security.randomCharString();
			String sha256 = Security.SHA256(p);
			String hash = Security.SHA256(account + sha256 + noce);
			String h = Security.byteStringToHexString(sha256);
			String ep = new Security().codeDecode(noce, h);
			
			
			
			//EnterpriseLoginDTO login = new EnterpriseLoginDTO();
			enterLogin.setAccount(account);
			System.out.println(account);
			enterLogin.setPassword(ep);
			enterLogin.setNonce(noce);
			enterLogin.setHashKey(hash);
		 
			String re  = postData(RESTurl.enterpriseUserLogin ,enterLogin);
			System.out.println(re);

			if("error500".equals(re)){
				fail(re);
			}
			assertNotNull( re);
			
			
			System.out.println("=========== 重新登录=========");
			 ReLoginDTO  reLogin = new  ReLoginDTO ();
			 reLogin.setToken(re);
			 String re1  = postData(RESTurl.reLogin ,reLogin);
			 System.out.println(re1);
			 
			 if("error500".equals(re)){
					fail(re);
				}
				assertNotNull( re);
			 /****************bug***********************/
				System.out.println("=========== 退出登录=========");
				String re2  = postData(RESTurl.logout ,61054);
				 System.out.println(re2);
		 
		 
	}
	
	/**
	 *   客服的客户服务计数减1
	 *   pass
	 */
	@Test
	public void reductionCustomerCounter(){
		System.out.println("===========  客服的客户服务计数减1=========");
		String re  = postData(RESTurl.reductionCustomerCounter ,61054);
		 System.out.println(re);
	}

	

}
