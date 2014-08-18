package com.david.mail.demo;

/**
 * JavaMail邮件发送-发送一个文本邮件和一些问题说明
 *http://cuisuqiang.iteye.com/blog/1585167
 */
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
// 如果你使用JDK1.4以上运行 会出现 Exception in thread "main" java.lang.NoClassDefFoundError: com/sun/mail/util/LineInputStream
// 需要你找到 javaee.jar 删掉 里面的 mail 包

public class TextMail {
	public static void main(String[] args) throws Exception {
		// 配置文件对象
		Properties props = new Properties();
		// 邮箱服务地址
		props.put("mail.smtp.host ", "smtp.163.com ");
		// 是否进行验证
		props.put("mail.smtp.auth", "true");
		// 创建一个会话
		Session session = Session.getInstance(props);
		// 打开调试，会打印与邮箱服务器回话的内容
		session.setDebug(true);
		Message message = new MimeMessage(session);
		// 如果发送人没有写对，那么会出现 javamail 550 Invalid User
		// 如果发送人写的和使用的帐号不一致，则会出现 553 Mail from must equal authorized user
		InternetAddress from = new InternetAddress("test20120711120200@163.com");
		from.setPersonal(MimeUtility.encodeText("java小强<test20120711120200@163.com>"));
		message.setFrom(from);
		InternetAddress to = new InternetAddress("455213313@qq.com");
		message.setRecipient(Message.RecipientType.TO, to);
		message.setSubject(MimeUtility.encodeText("强哥邀请，谁敢不从！"));
		message.setText("强哥邀请你访问我的博客：http://cuisuqiang.iteye.com/！");
		message.setSentDate(new Date());
		Transport transport = session.getTransport("smtp");
		// 具体你使用邮箱的smtp地址和端口，应该到邮箱里面查看，如果使用了SSL，网易的端口应该是 465/994
		transport.connect("smtp.163.com", 25, "test20120711120200", "test123456");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		System.out.println("发送完毕");
	}
}