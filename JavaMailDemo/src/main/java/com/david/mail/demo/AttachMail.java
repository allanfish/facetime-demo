package com.david.mail.demo;

/**
 * JavaMail邮件发送-发送带附件的邮件
 * http://cuisuqiang.iteye.com/blog/1586023
 * @author YUFEI
 *
 */
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class AttachMail {
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
		MimeMultipart msgMultipart = new MimeMultipart("mixed");// 指定为混合关系
		message.setContent(msgMultipart);
		// 邮件内容
		MimeBodyPart htmlPart = new MimeBodyPart();
		htmlPart.setContent(
				"<body background='http://dl.iteye.com/upload/picture/pic/110267/e244bda9-9034-36e3-87fd-807629b84222.jpg'>"
						+ "<div style='position: absolute; left: 390px; top: 150px;height: "
						+ "100px;width: 200px;' align='center'>" + "<font color='red'>这是测试邮件，请勿回复</font>"
						+ "</div></body>", "text/html;charset=UTF-8");
		// TODO 组装的顺序非常重要，一定要先组装文本域，再组装文件
		msgMultipart.addBodyPart(htmlPart);
		// 组装附件
		MimeBodyPart file = new MimeBodyPart();
		FileDataSource file_datasource = new FileDataSource("D:\\img201008031058340.zip");
		DataHandler dh = new DataHandler(file_datasource);
		file.setDataHandler(dh);
		// 附件区别内嵌内容的一个特点是有文件名，为防止中文乱码要编码
		file.setFileName(MimeUtility.encodeText(dh.getName()));
		msgMultipart.addBodyPart(file);
		message.saveChanges();
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.163.com", 25, "test20120711120200", "test123456");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		System.out.println("发送完毕");
	}
}