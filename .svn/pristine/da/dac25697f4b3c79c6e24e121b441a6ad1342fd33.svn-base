package com.qycloud.oatos.filecache.test.logic;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.conlect.oatos.dto.client.ViewFileDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.filecache.logic.FileViewLogic;
import com.qycloud.oatos.filecache.test.BaseTest;

public class FileViewLogicTest extends BaseTest {
	
	@SpringBeanByType
	private FileViewLogic fileViewLogic;
	
	@Test
	public void viewFileAsPdf() {
		ViewFileDTO fileDTO = new ViewFileDTO();
		fileDTO.setFileId(6802L);
		fileDTO.setFolderId(6242L);
		fileDTO.setUserId(23L);
		fileDTO.setGuid("aa858c68-6dd4-4d43-b9ab-32c2603c0bde.doc");
		fileDTO.setName("OpenKM配置指南.doc");
		fileDTO.setType(CommConstants.FILE_TYPE_ONLINEDISK);
		
		try {
			String result = fileViewLogic.viewFileAsPdf(PojoMapper.toJson(fileDTO), token);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
