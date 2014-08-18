package com.qycloud.oatos.server.test.service;

import java.util.Date;

import org.junit.Test;

import com.conlect.oatos.dto.client.mail.MailDTO;
import com.conlect.oatos.dto.status.RESTurl;

public class SendMailServiceTest extends BaseServiceTest {
	
	/**
	 * pass
	 */
	@Test
	public void sendMail(){
		MailDTO mail = new MailDTO();
//		mail.setBcc(bcc);
//		mail.setCc(cc);
//		mail.setContent(content);
		mail.setSender("xm.xiaom@gmail.com");
		mail.setSubject("听说你发财了");
		mail.setRecieve("xiao4570849@126.com");
		mail.setContent("你好我是正文,其实这是一封测试邮件，测试发送多个用户的邮件，欢迎您来深圳企业云科技，这里帅男无数，美女没有");
		mail.setSendDate(new Date());
		
		String re = postData(RESTurl.sendMail,mail);
		System.out.println(re);
		
		
	}
}
