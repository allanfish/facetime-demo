package com.qycloud.oatos.server.test.service;


import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.logic.SequenceLogic;
import com.qycloud.oatos.server.security.Security;

/**
 * base service test
 * 
 * @author yang
 * 
 */
@SpringApplicationContext({ "applicationContext.xml", "oatos-servlet.xml"})
public class BaseServiceTest extends UnitilsJUnit4 {
	
	//企业id
	protected static Long entId = 1234L;
	//用户
	protected static long userId3885 =249509L;
	//企业网盘文件夹
	
	protected static Long New_folder =1346L;
	
	protected static Long Admin1234=1235L;
	
	protected static Long New_folder1 =1347L;
	protected static Long ligao =263081L;
	protected static Long saas =262812L;
	
	protected static Long user1 = 25l;
	protected static Long user2 = 34l;
	
	
	
	
	protected static  Map<String,Long> idmap = new HashMap<String,Long>();

	@SpringBeanByType
	protected RestTemplate restTemplate;

//	protected String appService = "http://192.168.1.68:8080/server/oatos/";
	



	protected String appService = "http://localhost:8980/server/oatos/";
	//protected String appService = "http://192.168.1.68:8080/server/oatos/";

	protected String fileService ="http://192.168.1.68/filecache/oatos/";
	

	protected static String token = "";

	protected static HttpHeaders headers;
	
	@SpringBeanByType
	protected SequenceLogic sequence;
	
	@BeforeClass
	public static void beforeClass() {

		
		//token = Security.CreateToken("1");

		token = Security.CreateToken("249509");

		//token = Security.CreateToken("23");


		headers = new HttpHeaders();
		headers.set(RESTurl.UserTokenkey, token);
		System.out.println(token);
	}


	protected String get(String url, Map<String, String> params) {
		String getUrl = appService + url;
		return restTemplate.getForObject(getUrl, String.class, params);
	}
	protected String get(String url) {
		String getUrl = appService + url;
		Map<String, String> params = new HashMap<String, String>();
		return restTemplate.getForObject(getUrl, String.class, params);
	}

	protected String post(String url, String postData) {
		return postForObject(url, postData, String.class);
	}

	protected <T> T postForObject(String url, String postData, Class<T> returnType) {
		HttpEntity<String> entity = new HttpEntity<String>(postData, headers);
		String postUrl = appService + url;
		T r = restTemplate.postForObject(postUrl, entity, returnType);
		return r;
	}

	protected String post(String url) {
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String postUrl = appService + url;
		String r = restTemplate.postForObject(postUrl, entity, String.class);
		return r;
	}

	/***
	 * 包装数据发送方法
	 * 
	 * @param url
	 * @param obj
	 * @return String
	 */
	protected String postData(String url, Object obj) {
		if (obj != null) {
			if (obj instanceof String) {

				return post(url, obj.toString());
			} else {
				String json = PojoMapper.toJson(obj);
				System.out.println(json);
				return post(url, json);
			}
		} else {
			return post(url);
		}
	}
	
}
