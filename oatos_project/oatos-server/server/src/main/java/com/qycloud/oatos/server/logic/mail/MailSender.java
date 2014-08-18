package com.qycloud.oatos.server.logic.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.conlect.oatos.dto.client.mail.ExtendMailDTO;
import com.conlect.oatos.dto.client.mail.MailDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.qycloud.oatos.server.util.BllLogger;
import com.qycloud.oatos.server.util.ConfigUtil;
import com.qycloud.oatos.server.util.MyConstants;

/**
 * 对象名称：发送邮件实现
 * 
 * 作者： 郭天良
 * 
 * 完成日期：20100926
 * 
 * 对象内容：
 */
@Service("MailSender")
public class MailSender {

	private static final Logger logger = BllLogger.getLogger();

	// mail内容的格式和编码
	private static final String messageContentMimeType = "text/html; charset=UTF-8";

	/**
	 * send email
	 */
	public void sendMail(MailDTO mailDTO) {
		SendMailThread sendMailThread = new SendMailThread(mailDTO);
		sendMailThread.start();
	}

	/**
	 * 批量发送邮件
	 */
	public void sendMail(List<MailDTO> mailList) {
		SendMailThread sendMailThread = new SendMailThread(mailList);
		sendMailThread.start();
	}

	/**
	 * 发送邮件(带邮件发送信息)
	 */
	public void sendMail(ExtendMailDTO dto) {
		// 启动线程，发送邮件
		SendMailThread sendMailThread = new SendMailThread(dto.getMailDTO());
		sendMailThread.start();
	}

	/**
	 * 发送邮件线程
	 */
	private class SendMailThread extends Thread {

		private List<MailDTO> mailList = new ArrayList<MailDTO>();

		private String sysMailSendHost = ConfigUtil.getValue(MyConstants.SysMailSendHost);

		public SendMailThread(MailDTO mailDto) {
			mailList.add(mailDto);
		}

		public SendMailThread(List<MailDTO> mailList) {
			this.mailList.addAll(mailList);
		}

		@Override
		public void run() {
			for (MailDTO mailDto : mailList) {
				MailAuthenticator auth = null;
				Transport trans = null;
				try {
					auth = MailAuthenticator.getNewAuthenticator();
					mailDto.setSender(auth.getUser());

					logger.info("send email from[" + auth.getUser() + "] to [" + mailDto.getRecieve() + "], subject["
					        + mailDto.getSubject() + "] start");

					Properties props = System.getProperties();
					props.put("mail.smtp.host", sysMailSendHost);
					props.put("mail.smtp.auth", "true");

					Session session = Session.getDefaultInstance(props, (Authenticator) auth);
					session.setDebug(false);
					MimeMessage msg = new MimeMessage(session);

					fillMail(msg, mailDto);
					trans = session.getTransport("smtp");
					trans.connect(sysMailSendHost, auth.getUser(), auth.getPassword());

					// send the message
					trans.sendMessage(msg, msg.getAllRecipients());

					trans.close();

					logger.info("send email from[" + auth.getUser() + "] to [" + mailDto.getRecieve() + "], subject["
					        + mailDto.getSubject() + "] finish");

				}
				catch (Exception ex) {
					logger.error("send email from[" + auth.getUser() + "] to [" + mailDto.getRecieve() + "], subject["
					        + mailDto.getSubject() + "] failed", ex);
//					ex.printStackTrace();
				}
				finally {
					try {
						if (trans != null && trans.isConnected()) {
							trans.close();
						}
					}
					catch (Exception ex) {
						logger.error("close send email session", ex);
					}
				}
			}

		}

		/**
		 * 准备邮件的内容和附件内容
		 * 
		 * @param session
		 * @param msg
		 * @throws IOException
		 * @throws MessagingException
		 */
		private void fillMail(MimeMessage msg, MailDTO mailDto) throws IOException, MessagingException {

			Multipart mPart = new MimeMultipart();
			// TODO sender name
			msg.setFrom(new InternetAddress(mailDto.getSender(), "OATOS", CommConstants.CHARSET_UTF_8));
			// msg.setFrom(new InternetAddress(mailDto.sender));

			if (mailDto.getRecieve() != null) {
				InternetAddress[] address = InternetAddress.parse(mailDto.getRecieve());
				msg.setRecipients(Message.RecipientType.TO, address);
			}
			else {
				return;
			}

			if (mailDto.getCc() != null) {
				InternetAddress[] ccaddress = InternetAddress.parse(mailDto.getCc());
				msg.setRecipients(Message.RecipientType.CC, ccaddress);
			}
			if (mailDto.getBcc() != null) {
				InternetAddress[] bccaddress = InternetAddress.parse(mailDto.getBcc());
				msg.setRecipients(Message.RecipientType.BCC, bccaddress);
			}
			msg.setSubject(mailDto.getSubject());
			InternetAddress[] replyAddress =
			{ new InternetAddress(mailDto.getSender()) };
			msg.setReplyTo(replyAddress);

			// create and fill the first message part
			MimeBodyPart mBodyContent = new MimeBodyPart();

			mBodyContent.setContent(mailDto.getContent(), messageContentMimeType);

			mPart.addBodyPart(mBodyContent);

			// 发送附件
			// TODO
			// if (!mailDto.filePathName.isEmpty())
			// {
			// String[] attachedFileList = mailDto.filePathName.split(",");
			// String[] realFileNames = mailDto.fileName.split(",");
			// int count = attachedFileList.length;
			//
			// for (int i = 0; i < count; i++)
			// {
			// MimeBodyPart mBodyPart = new MimeBodyPart();
			// // attach the file to the message
			// // TODO
			// // FileDataSource fds = new FileDataSource(attachPath +
			// attachedFileList[i]);
			// // mBodyPart.setDataHandler(new DataHandler(fds));
			// String fileName;
			// if (realFileNames.length > i + 1)
			// {
			// fileName = realFileNames[i];
			// } else
			// {
			// fileName = attachedFileList[i];
			// }
			// mBodyPart.setFileName(fileName);
			// mPart.addBodyPart(mBodyPart);
			//
			// }
			// }
			msg.setContent(mPart);
			msg.setSentDate(new Date());
		}

	}

}
