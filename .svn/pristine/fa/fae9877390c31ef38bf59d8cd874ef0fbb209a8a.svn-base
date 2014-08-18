package com.qycloud.oatos.filecache.test.logic;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.conlect.oatos.dto.client.SaveFileDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.filecache.domain.logic.FileLogic;
import com.qycloud.oatos.filecache.test.BaseTest;

public class FileLogicTest extends BaseTest {

	@SpringBeanByType
	private FileLogic fileLogic;

	@Test
	public void saveFile() {
		SaveFileDTO fileDTO = new SaveFileDTO();
		fileDTO.setUserId(3317);
		fileDTO.setToId(3317);
		fileDTO.setFolderId(3324L);
		fileDTO.setToType(CommConstants.FILE_TYPE_SHAREDISK);
		fileDTO.setName("文件测试2.oatw");
		fileDTO.setType(CommConstants.FILE_NEW);
		fileDTO.setContent("test========test");
		String postData = PojoMapper.toJson(fileDTO);
		String re = fileLogic.saveFile(postData, token);
		System.out.println(re);
	}
}
