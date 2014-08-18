package com.conlect.oatos.test;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSenderTest {

	private String host = "smtp.qq.com";
	private String protocol = "smtp";
	private String from = "XXXXX@qq.com";
	private String to = "XXXXXX@qq.com";
	private String subject = "这是一个带附件和图片的邮件";
	private String content = "<html><body> hello world</body></html>";
	private String username = "XXXXXX";
	private String password = "XXXXXX";

	/**
	 * 主方法调用此方法用来发送邮件
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void sendMail() throws AddressException, MessagingException {

		//创建session所用到的东西
		Properties props = new Properties();
		//发送邮件的服务器
		props.setProperty("mail.smtp.host", host);
		//是否采取验证用户模式
		props.setProperty("mail.smtp.auth", "true");
		//发送邮件的模式 smtp
		props.setProperty("mail.transport.protocol", protocol);

		Session session = Session.getDefaultInstance(props, new Authenticator() {//设置用户名密码
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						// TODO Auto-generated method stub
						return new PasswordAuthentication(username, password);
					}
				});
		//启动调试模式
		session.setDebug(true);

		Message msg = new MimeMessage(session);
		//设置发送者地址
		msg.setFrom(new InternetAddress(from));
		//设置发送模式 Message.RecipientType.To
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		//设置标题
		msg.setSubject(subject);
		//设置正文 在此是一个复杂的邮件正文
		msg.setContent(getMultipart());
		//保存以上的东西
		msg.saveChanges();

		//发送邮件
		Transport smtp = session.getTransport();
		smtp.connect(host, username, password);
		smtp.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
		smtp.close();

	}

	/**
	 * 获取复杂邮件的Multipart部分
	 * @return
	 * @throws MessagingException
	 */
	public Multipart getMultipart() throws MessagingException {
		Multipart multipart = new MimeMultipart("mixed");
		multipart.addBodyPart(getContentPart());
		multipart.addBodyPart(createAttachment(new File("d://1.txt")));
		multipart.addBodyPart(createAttachment(new File("d://f32589add68cdf2c4a36d63f.jpg")));

		return multipart;
	}

	/**
	 * 创建正文
	 * @return
	 * @throws MessagingException
	 */
	public BodyPart getContentPart() throws MessagingException {
		BodyPart body = new MimeBodyPart();
		Multipart relate = new MimeMultipart("related");

		relate.addBodyPart(createHtmlBody());
		relate.addBodyPart(createImagePart(new File("d://picture//WA016.jpg"), "image1"));
		relate.addBodyPart(createImagePart(new File("d://picture//qiang007.jpg"), "image2"));

		body.setContent(relate);

		return body;
	}

	/**
	 * 设置正文中的图片
	 * @param file
	 * @param name
	 * @throws MessagingException
	 */

	public BodyPart createImagePart(File file, String name) throws MessagingException {
		MimeBodyPart image = new MimeBodyPart();

		DataSource ds = new FileDataSource(file);
		image.setDataHandler(new DataHandler(ds));
		image.setFileName(name);
		image.setContentID(name);

		return image;

	}

	/**
	 * 添加附件功能
	 * @param file
	 * @return
	 * @throws MessagingException
	 */

	public BodyPart createAttachment(File file) throws MessagingException {
		BodyPart attach = new MimeBodyPart();

		DataSource ds = new FileDataSource(file);
		attach.setDataHandler(new DataHandler(ds));

		return attach;
	}

	/**
	 * 创建html消息
	 * @return
	 * @throws MessagingException
	 */
	public BodyPart createHtmlBody() throws MessagingException {
		BodyPart html = new MimeBodyPart();

		html.setContent(content, "text/html;charset=gbk");

		return html;
	}

	/**
	 * 获取alternative邮件
	 * @return
	 * @throws MessagingException
	 */
	public Multipart getAlternativeMultipart() throws MessagingException {
		Multipart alternative = new MimeMultipart("alternative");
		BodyPart text = new MimeBodyPart();
		text.setContent("请浏览html", "text/plain;charset=gbk");
		alternative.addBodyPart(text);

		BodyPart html = new MimeBodyPart();
		html.setContent(content, "text/html;charset=gbk");
		alternative.addBodyPart(html);

		return alternative;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MailSenderTest mail = new MailSenderTest();
		try {
			mail.sendMail();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}