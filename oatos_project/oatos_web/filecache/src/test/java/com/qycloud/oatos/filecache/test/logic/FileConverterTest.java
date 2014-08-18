package com.qycloud.oatos.filecache.test.logic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.conlect.oatos.dto.client.doc.DocConvertDTO;
import com.conlect.oatos.dto.status.DocConvertType;
import com.conlect.oatos.file.FileConverter;

public class FileConverterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("rmi-client.xml");
		FileConverter converter = context.getBean("fileConverter", FileConverter.class);
		
		DocConvertDTO docDTO = new DocConvertDTO();
		docDTO.setDiskRootPath("D:/test/");
		docDTO.setSourcePath("协同平台沟通_成建兵20121223.pptx");
		docDTO.setTargetPath("test/协同平台沟通_成建兵20121223.pdf");
		docDTO.setConvertType(DocConvertType.DOC_TO_PDF);
		
		String r = converter.convert(docDTO);
		System.out.println(r);
	}

}
