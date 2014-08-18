package com.qycloud.oatos.server.test.logic;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.conlect.oatos.dto.client.EnterpriseDTO;
import com.conlect.oatos.dto.status.ErrorType;
import com.qycloud.oatos.server.domain.logic.EnterpriseLogic;
import com.qycloud.oatos.server.test.BaseTest;
import com.qycloud.oatos.server.test.EncryptModel;
import com.qycloud.oatos.server.test.EncryptUtil;
import com.qycloud.oatos.server.util.LogicException;

/**
 * 企业服务测试类
 * 
 * @author huhao
 * 
 */
public class EnterpriseLogicTest extends BaseTest {
	@SpringBeanByType
	private EnterpriseLogic enterpriseLogic;

	@Test
	public void registerEnterprise() {

		String entName = "qyloud-test-" + sequence.getNextId();
		String password = "123456";

		EncryptModel encrypt = EncryptUtil.encrypt(entName, password);

		EnterpriseDTO enterpriseDTO = new EnterpriseDTO();
		enterpriseDTO.setEnterpriseName(entName);
		enterpriseDTO.setAdminName("超级管理员");
		enterpriseDTO.setAdminPassword(encrypt.getPassword());
		enterpriseDTO.setAddress("留仙一路");
		enterpriseDTO.setContact("钟小姐");
		enterpriseDTO.setMail("test@qycloud.com");
		enterpriseDTO.setMobile("12345678900");
		enterpriseDTO.setNonce(encrypt.getNonce());
		enterpriseDTO.setHashKey(encrypt.getHashKey());

		String result = null;
		try {
			result = enterpriseLogic.registerEnterprise(enterpriseDTO);
		} catch (LogicException ex) {
			result = ex.getError().name();
		} catch (Exception ex) {
			result = ErrorType.error500.name();
			ex.printStackTrace();
		}
		System.out.println(result);
	}
}
