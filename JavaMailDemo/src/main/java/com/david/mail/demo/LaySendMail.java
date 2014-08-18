package com.david.mail.demo;

/**
 JavaMail邮件发送-将邮件保存到本地和发送一封本地邮件
 * @author YUFEI
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 * @说明 保存一个邮件
 * @author cuisuqiang
 * @version 1.0
 * @since
 */
public class LaySendMail {
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.host ", "smtp.163.com ");
		props.put("mail.smtp.port", 25);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);
		session.setDebug(true);
		Message message = new MimeMessage(session);
		InternetAddress from = new InternetAddress("test20120711120200@163.com");
		from.setPersonal(MimeUtility.encodeText("java小强<test20120711120200@163.com>"));
		message.setFrom(from);
		InternetAddress to = new InternetAddress("test20120711120200@163.com");
		message.setRecipient(Message.RecipientType.TO, to);
		message.setSubject(MimeUtility.encodeText("强哥邀请，谁敢不从！"));
		message.setText("强哥邀请你访问我的博客：http://cuisuqiang.iteye.com/！");
		message.setSentDate(new Date());
		// 邮件对象
		File file = new File("C:\\textmail.eml");
		// 获得输出流
		OutputStream ips = new FileOutputStream(file);
		// 把邮件内容写入到文件
		message.writeTo(ips);
		// 关闭流
		ips.close();
		System.out.println("发送完毕");
	}
}

class SendCurrentMail {
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);
		// 现有邮件文件
		File file = new File("C:\\textmail.eml");
		FileInputStream fis = new FileInputStream(file);
		// 创建邮件对象
		Message message = new MimeMessage(session, fis);
		message.setSentDate(new Date());
		message.saveChanges();
		// 发送邮件
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.163.com", 25, "test20120711120200", "test123456");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		fis.close();
		System.out.println("发送完毕");
	}
}