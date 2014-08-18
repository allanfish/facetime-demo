package com.qycloud.oatos.server.test.logic;

import java.util.UUID;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.conlect.oatos.dto.client.EnterpriseLoginDTO;
import com.conlect.oatos.dto.status.UserAgent;
import com.qycloud.oatos.server.domain.entity.User;
import com.qycloud.oatos.server.domain.logic.LoginLogic;
import com.qycloud.oatos.server.test.BaseTest;
import com.qycloud.oatos.server.test.EncryptModel;
import com.qycloud.oatos.server.test.EncryptUtil;

public class LoginLogicTest extends BaseTest {

	@SpringBeanByType
	private LoginLogic loginLogic;

	@Test
	public void userLogin() {

		String entName = enterprise.getEnterpriseName();
		String account = user.getUserName();
		String p = "123456";

		EncryptModel encrypt = EncryptUtil.encrypt(account, p);

		EnterpriseLoginDTO login = new EnterpriseLoginDTO();
		login.setEnterpriseName(entName);
		login.setAccount(account);
		login.setPassword(encrypt.getPassword());
		login.setNonce(encrypt.getNonce());
		login.setHashKey(encrypt.getHashKey());
		login.setClientId(UUID.randomUUID().toString());
		login.setDeviceToken("ABC");
		login.setAgent(UserAgent.Web);
		String r = loginLogic.userLogin(login);
		System.out.println(r);
	}

	@Test
	public void adminLogin() {
		String entName = enterprise.getEnterpriseName();
		String account = user.getUserName();
		String p = "123456";

		EncryptModel encrypt = EncryptUtil.encrypt(account, p);
		
		EnterpriseLoginDTO login = new EnterpriseLoginDTO();
		login.setEnterpriseName(entName);
		login.setAccount(account);
		login.setPassword(encrypt.getPassword());
		login.setNonce(encrypt.getNonce());
		login.setHashKey(encrypt.getHashKey());

		String r = loginLogic.adminLogin(login);
		System.out.println(r);
	}
	
	@Test
	public void getUser() {
		User u = userMapper.getUser(user.getUserId());
		System.out.println(u.getDeviceToken());
	}
	
}
