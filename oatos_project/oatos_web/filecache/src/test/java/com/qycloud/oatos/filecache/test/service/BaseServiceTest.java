package com.qycloud.oatos.filecache.test.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.BeforeClass;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.http.PojoMapper;

/**
 * base service test
 * 
 * @author yang
 * 
 */
@SpringApplicationContext({ "applicationContext.xml" })
public class BaseServiceTest extends UnitilsJUnit4 {

	@SpringBeanByType
	protected RestTemplate restTemplate;

	protected String appService = "http://localhost:8980/filecache/oatos/";

	protected static String token = "";

	protected static HttpHeaders headers;

	@BeforeClass
	public static void beforeClass() {
		token = "101287@bb0a44d34d2ef22ddd3bcfc72aece8ec76968257j3npsx6v4gxwir8ec119ay6z61369786995498";
		headers = new HttpHeaders();
		headers.set(RESTurl.UserTokenkey, token);
	}

	protected String post(String url, String postData) {
		HttpEntity<String> entity = new HttpEntity<String>(postData, headers);
		return post(url, entity);
	}

	protected String post(String url, File f) throws IOException {
		InputStream in = new FileInputStream(f);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		FileUtil.copyStream(in, out);
		return post(url, out.toByteArray());
	}

	protected String post(String url, byte[] b) {
		HttpEntity<byte[]> entity = new HttpEntity<byte[]>(b, headers);
		return post(url, entity);
	}

	protected String post(String url, byte[] b, FileUploadDTO bean) {
		headers.set(RESTurl.postJsonData, PojoMapper.toJson(bean));
		HttpEntity<byte[]> entity = new HttpEntity<byte[]>(b, headers);
		return post(url, entity);
	}

	protected String post(String url, HttpEntity<?> entity) {
		String postUrl = appService + url;
		String r = restTemplate.postForObject(postUrl, entity, String.class);
		return r;
	}

}
