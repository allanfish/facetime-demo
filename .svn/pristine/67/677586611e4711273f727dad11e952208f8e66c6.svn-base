package com.conlect.oatos.mail;

import java.net.URL;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.conlect.oatos.dto.autobean.mail.IMailAccountDTO;
import com.conlect.oatos.dto.autobean.mail.IMailAttachDTO;
import com.conlect.oatos.dto.autobean.mail.IMailDTO;
import com.conlect.oatos.dto.client.mail.MailDTO;
import com.conlect.oatos.utils.StringUtils;

/**
 * 邮件发送工具类
 */
public class MailSender {

	public static void saveDraftMail(IMailAccountDTO mailAcc, MailDTO mail)
			throws Exception {
		Session session = MailHelper.getSession(mailAcc);
		Message message = new MimeMessage(session);

		InternetAddress from = new InternetAddress(mailAcc.getMailAddress());
		from.setPersonal(MimeUtility.encodeText(mailAcc.getAccountDesc() + "<"
				+ mailAcc.getMailAddress() + ">"));
		message.setFrom(from);
		InternetAddress to = new InternetAddress(mail.getRecieve());
		message.setRecipient(Message.RecipientType.TO, to);
		if (StringUtils.isValid(mail.getCc())) {
			message.setRecipients(RecipientType.CC, getCcAddrs(mail.getCc()));
		}
		if (StringUtils.isValid(mail.getBcc())) {
			message.setRecipients(RecipientType.BCC, getCcAddrs(mail.getBcc()));
		}

		message.setSubject(MimeUtility.encodeText(mail.getSubject()));
		message.setSentDate(new Date());
		setMailContent(mail, message);
		message.setFlag(Flags.Flag.DRAFT, true);
		message.saveChanges();
		System.out.println("保存完毕");
	}

	/**
	 * 发送非文本内容的邮件, 可以带附件
	 * 
	 * @param mailAcc
	 * @param mail
	 * @throws Exception
	 */
	public static MimeMessage sendMail(IMailAccountDTO mailAcc, MailDTO mail)
			throws Exception {
		Session session = MailHelper.getSession(mailAcc);
		MimeMessage message = new MimeMessage(session);

		InternetAddress from = new InternetAddress(mailAcc.getMailAddress());
		from.setPersonal(MimeUtility.encodeText(mailAcc.getAccountDesc() + "<"
				+ mailAcc.getMailAddress() + ">"));
		message.setFrom(from);
		InternetAddress to = new InternetAddress(mail.getRecieve());
		message.setRecipient(Message.RecipientType.TO, to);
		if (StringUtils.isValid(mail.getCc())) {
			message.setRecipients(RecipientType.CC, getCcAddrs(mail.getCc()));
		}
		if (StringUtils.isValid(mail.getBcc())) {
			message.setRecipients(RecipientType.BCC, getCcAddrs(mail.getBcc()));
		}

		message.setSubject(MimeUtility.encodeText(mail.getSubject()));
		message.setSentDate(new Date());
		setMailContent(mail, message);
		message.saveChanges();

		Transport transport = MailHelper.getTransport(session, mailAcc);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		System.out.println("发送完毕");
		return message;
	}

	/**
	 * 邮件快捷回复
	 * 
	 * @param mailAcc
	 * @param mail
	 * @throws Exception
	 */
	public static void sendReplyMail(IMailAccountDTO mailAcc, MailDTO mail)
			throws Exception {
		Session session = MailHelper.getSession(mailAcc);
		Store store = MailHelper.getStore(session, mailAcc);
		Folder folder = MailHelper.getFolder(store, mail.getFolderUrl());
		folder.open(Folder.READ_WRITE);
		MimeMessage msg = MailHelper.getMessage(folder, mail.getMessageId());
		if (msg == null) {
			return;
		}

		MimeMessage message = (MimeMessage) (IMailDTO.ALL_REPLY == mail
				.getReplyType() ? msg.reply(true) : msg.reply(false));
		InternetAddress from = new InternetAddress(mailAcc.getMailAddress());
		from.setPersonal(MimeUtility.encodeText(mailAcc.getAccountDesc() + "<"
				+ mailAcc.getMailAddress() + ">"));
		message.setFrom(from);
		InternetAddress to = new InternetAddress(mail.getRecieve());
		message.setRecipient(Message.RecipientType.TO, to);
		if (StringUtils.isValid(mail.getSubject())) {
			message.setSubject(MimeUtility.encodeText(mail.getSubject()));
		}
		message.setSentDate(new Date());

		if (IMailDTO.ALL_REPLY == mail.getReplyType()) {
			if (StringUtils.isValid(mail.getCc())) {
				message.setRecipients(RecipientType.CC,
						getCcAddrs(mail.getCc()));
			}
			if (StringUtils.isValid(mail.getBcc())) {
				message.setRecipients(RecipientType.BCC,
						getCcAddrs(mail.getBcc()));
			}
		}

		// 添加Content
		setMailContent(mail, message);

		message.saveChanges();
		Transport transport = MailHelper.getTransport(session, mailAcc);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		folder.close(true);
		store.close();
		System.out.println("发送完毕");
	}

	private static Address[] getCcAddrs(String cc) {
		String[] strAddrs = StringUtils.split(cc, ";");
		InternetAddress[] addrs = new InternetAddress[strAddrs.length];
		for (int i = 0; i < strAddrs.length; i++) {
			try {
				addrs[i] = new InternetAddress(strAddrs[i]);
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
		return addrs;
	}

	private static void setMailContent(MailDTO mail, Message message)
			throws Exception {
		boolean hasAttach = mail.isHaveAttach() && mail.getAttachs().size() > 0;
		MimeMultipart msgMultipart = hasAttach ? new MimeMultipart("mixed")
				: new MimeMultipart();
		// 邮件内容
		MimeBodyPart htmlPart = new MimeBodyPart();
		htmlPart.setContent(mail.getContent(), "text/html;charset=UTF-8");
		// 组装的顺序非常重要，一定要先组装文本域，再组装文件
		msgMultipart.addBodyPart(htmlPart);

		// 附件内容
		if (hasAttach) {
			for (IMailAttachDTO attachDTO : mail.getAttachs()) {
				MimeBodyPart file = new MimeBodyPart();
				// 附件区别内嵌内容的一个特点是有文件名，为防止中文乱码要编码
				file.setFileName(MimeUtility.encodeText(attachDTO.getFileName()));
				DataSource ds = null;
				if (attachDTO.getFilePath().startsWith("http"))
					ds = new URLDataSource(new URL(attachDTO.getFilePath()));
				else
					ds = new FileDataSource(attachDTO.getFilePath());
				DataHandler dh = new DataHandler(ds);
				file.setDataHandler(dh);
				msgMultipart.addBodyPart(file);
			}
		}
		message.setContent(msgMultipart);
	}
}
