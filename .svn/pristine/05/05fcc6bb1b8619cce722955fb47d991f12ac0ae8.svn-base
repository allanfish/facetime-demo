package com.david.mail.demo;

import java.util.Date;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * JavaMail邮件发送-发送非纯文本邮件
 * @author YUFEI
 *
 */
public class HTMLMail2 {
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.host ", "smtp.163.com ");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);
		session.setDebug(true);
		Message message = new MimeMessage(session);
		InternetAddress from = new InternetAddress("test20120711120200@163.com");
		from.setPersonal(MimeUtility.encodeText("风中落叶<test20120711120200@163.com>"));
		message.setFrom(from);
		InternetAddress to = new InternetAddress("455213313@qq.com");
		message.setRecipient(Message.RecipientType.TO, to);
		message.setSubject(MimeUtility.encodeText("强哥邀请，谁敢不从！"));
		message.setSentDate(new Date());
		// 构建邮件内容对象
		Multipart mm = new MimeMultipart();
		// 构建一个消息内容块
		BodyPart mbpFile = new MimeBodyPart();
		mbpFile.setContent("<font color='red'>强哥邀请你访问我的博客：http://cuisuqiang.iteye.com/！</font>",
				"text/html;charset=UTF-8");
		mm.addBodyPart(mbpFile);
		message.setContent(mm);
		message.saveChanges();
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.163.com", 25, "test20120711120200", "test123456");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		System.out.println("发送完毕");
	}
}
