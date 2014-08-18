package com.qycloud.web.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String getURL = "http://app.oatos.com/os/getVersion";
		
		HttpGet httpGet = new HttpGet(getURL);
		httpGet.setHeader("platform", "mobile");
		HttpEntity entity = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(httpGet);
			entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			System.out.println(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
