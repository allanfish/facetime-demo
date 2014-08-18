package com.qycloud.web.test.base;

import org.junit.Test;

import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;

public class SendMessageServiceTest {

	XhrProxy http = XhrProxy.getInstance();

	private static final String token = "";

	@Test
	public void testSendMessage() {
		MessageDTO message = new MessageDTO();

		http.post("http://localhost/os/sendmessage", token, PojoMapper.toJson(message));
	}
}
