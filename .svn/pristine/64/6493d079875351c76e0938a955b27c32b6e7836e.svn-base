package com.qycloud.oatos.server.test.logic;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.conlect.oatos.dto.client.mail.MailFolderDTO;
import com.conlect.oatos.dto.client.mail.MailQueryDTO;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.dao.MailFolderMapper;
import com.qycloud.oatos.server.domain.entity.MailFolder;
import com.qycloud.oatos.server.domain.logic.MailLogic;
import com.qycloud.oatos.server.test.BaseTest;

public class MailLogicTest extends BaseTest {

	@SpringBeanByType
	private MailLogic mailLogic;
	
	@SpringBeanByType
	private MailFolderMapper mailFolderMapper;

	@Test  
	/**
	 * 初始化(mail_account,mail_folder数据) by huhao
	 */
	public void receiveLatestMailFolder() throws Exception {
		
		MailFolder folder = mailFolderMapper.getMailFolderById(374251);
		
		MailQueryDTO queryDTO = new MailQueryDTO();
		queryDTO.setMailAccountId(folder.getMailAccountId());
		queryDTO.setFolderId(folder.getMailFolderId());
		queryDTO.setFolderurl(folder.getFolderUrl());
		queryDTO.setLatestReceiveDate(folder.getLatestReceiveDate());

		MailFolderDTO folderDTO = mailLogic.receiveLatestMailFolder(queryDTO);
		System.out.println(PojoMapper.toJson(folderDTO));
	}
}
