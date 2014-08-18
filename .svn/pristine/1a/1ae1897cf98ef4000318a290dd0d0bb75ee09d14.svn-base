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
 * JavaMail邮件发送-为你的邮件增加背景音乐和背景图片
 * http://cuisuqiang.iteye.com/blog/1585856
 * @author YUFEI
 */
public class HTMLMail {
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.host ", "smtp.163.com ");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);
		Message message = new MimeMessage(session);
		InternetAddress from = new InternetAddress("test20120711120200@163.com");
		from.setPersonal(MimeUtility.encodeText("风中落叶<test20120711120200@163.com>"));
		message.setFrom(from);
		InternetAddress to = new InternetAddress("test20120711120200@163.com");
		message.setRecipient(Message.RecipientType.TO, to);
		message.setSubject(MimeUtility.encodeText("强哥邀请，谁敢不从！"));
		message.setSentDate(new Date());
		// 构建邮件内容对象
		Multipart mm = new MimeMultipart();
		// 构建一个消息内容块
		BodyPart mbpFile = new MimeBodyPart();
		mbpFile.setContent(
				"<body background='http://dl.iteye.com/upload/picture/pic/110267/e244bda9-9034-36e3-87fd-807629b84222.jpg'>"
						+ "<font color='red'>强哥邀请你访问我的博客：http://cuisuqiang.iteye.com/！</font></body>",
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
