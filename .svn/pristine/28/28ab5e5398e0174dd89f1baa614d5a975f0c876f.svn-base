package com.qycloud.oatos.server.test.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

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
import com.qycloud.oatos.server.security.Security;

/**
 * base service test
 * 
 * @author yang
 * 
 */
@SpringApplicationContext({ "applicationContext.xml" })
public class BaseFileServiceTest extends UnitilsJUnit4 {
	
	//企业id
	protected static Long entId = 1234L;
	//用户
	protected static Long userId3885 =249509L;
	//企业网盘文件夹
	protected static Long New_folder =1346L;
	
	protected static Long Admin1234=1235L;
	
	protected static Long New_folder1 =1347L;
	protected static Long ligao =263081L;
	protected static Long saas =262812L;

	@SpringBeanByType
	protected RestTemplate restTemplate;

	protected String appService = "http://192.168.1.68/filecache/oatos/";

	protected static String token = "";

	protected static HttpHeaders headers;

	@BeforeClass
	public static void beforeClass() {
		token = Security.CreateToken("1");
		headers = new HttpHeaders();
		headers.set(RESTurl.UserTokenkey, token);
	}
	
	protected String getData(String url,Map<String,?> map){
		String getUrl = appService+url;
		return restTemplate.getForObject(getUrl, String.class, map);
		
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
			//return post(url,null);
			System.out.println("请求错误");
			return null;
		}
	}
}
