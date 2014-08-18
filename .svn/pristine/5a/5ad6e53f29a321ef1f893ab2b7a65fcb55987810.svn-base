package com.conlect.oatos.mail;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimePart;
import javax.mail.internet.MimeUtility;

import com.conlect.oatos.dto.client.mail.MailAttachDTO;
import com.conlect.oatos.dto.client.mail.MailDTO;
import com.conlect.oatos.utils.DateUtils;
import com.conlect.oatos.utils.StringUtils;

public class MailReceiver {

	private MimeMessage msg = null;
	private StringBuffer bodytext = new StringBuffer();
	private List<MailAttachDTO> attachDTOs = new ArrayList<MailAttachDTO>();

	public MailReceiver(MimeMessage msg) {
		this.msg = msg;
	}

	/**
	 * 获取发送邮件者信息
	 */
	public String getSender() {
		String fromaddr = "";
		InternetAddress[] address;
		try {
			address = (InternetAddress[]) msg.getFrom();
			if (address == null || address.length == 0)
				return fromaddr = "无发送者";
			String from = address[0].getAddress();
			if (from == null) {
				from = "";
			}
			String personal = address[0].getPersonal();
			if (personal == null) {
				personal = "";
			}
			if (personal
					.matches("^.*<[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,4}>.*$")) {
				return personal;
			}
			fromaddr = personal + "<" + from + ">";
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return fromaddr;
	}

	/**
	 * 获取邮件收件人，抄送，密送的地址和信息。根据所传递的参数不同 "to"-->收件人,"cc"-->抄送人地址,"bcc"-->密送地址
	 * 
	 * @param type
	 *            BCC, CC, TO
	 */
	public String getMailAddress(String type) throws MessagingException,
			UnsupportedEncodingException {
		String mailaddr = "";
		String addrType = type.toUpperCase();
		InternetAddress[] address = null;

		if (addrType.equals("TO") || addrType.equals("CC")
				|| addrType.equals("BCC")) {
			if (addrType.equals("TO")) {
				address = (InternetAddress[]) msg
						.getRecipients(Message.RecipientType.TO);
			}
			if (addrType.equals("CC")) {
				address = (InternetAddress[]) msg
						.getRecipients(Message.RecipientType.CC);
			}
			if (addrType.equals("BCC")) {
				address = (InternetAddress[]) msg
						.getRecipients(Message.RecipientType.BCC);
			}

			if (address != null) {
				for (int i = 0; i < address.length; i++) {
					String mail = address[i].getAddress();
					if (mail == null) {
						mail = "";
					} else {
						mail = MimeUtility.decodeText(mail);
					}
					String personal = address[i].getPersonal();
					if (personal == null) {
						personal = "";
					} else {
						personal = MimeUtility.decodeText(personal);
					}
					String compositeto = personal + "<" + mail + ">";
					mailaddr += "," + compositeto;
				}
				if (StringUtils.isValid(mailaddr))
					mailaddr = mailaddr.substring(1);
			}
		} else {
			throw new RuntimeException("Error email Type!");
		}
		return mailaddr;
	}

	/**
	 * 获取邮件主题
	 */
	public String getSubject() {
		String subject = "无主题";
		try {
			subject = MimeUtility.decodeText(msg.getSubject());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (subject == null) {
			subject = "无主题";
		}
		return subject;
	}

	/**
	 * 获取邮件发送日期
	 * 
	 * @return
	 * @throws MessagingException
	 */
	public String getSendDate() throws MessagingException {
		Date sendDate = msg.getSentDate();
		SimpleDateFormat smd = new SimpleDateFormat(
				DateUtils.TIMESTAMP_FORMAT_STR);
		return smd.format(sendDate);
	}

	/**
	 * 获取邮件正文内容
	 */
	public String getBodyText() {
		return bodytext.toString();
	}

	/**
	 * 解析邮件，将得到的邮件内容保存到一个stringBuffer对象中，解析邮件 主要根据MimeType的不同执行不同的操作，一步一步的解析
	 * 
	 * @param part
	 * @throws MessagingException
	 * @throws IOException
	 */
	private void parseContent(Part part, boolean multipartFlag)
			throws MessagingException, IOException {
		String contentType = part.getContentType();
		int nameindex = contentType.indexOf("name");
		boolean conname = false;
		if (nameindex != -1) {
			conname = true;
		}
		if (part.isMimeType("text/plain") && !conname) {
			String content = (String) part.getContent();
			if (!multipartFlag && content != null
					&& content.indexOf("<html>") == -1
					&& content.indexOf("<HTML>") == -1) {
				content = addMissHtml(content);
			}
		} else if ((part.isMimeType("text/html")) && !conname) {
			String content = (String) part.getContent();
			if (content != null && content.indexOf("<html>") == -1
					&& content.indexOf("<HTML>") == -1) {
				content = addMissHtml(content);
			}
			bodytext.append(content);
		} else if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent();
			int count = multipart.getCount();
			for (int i = 0; i < count; i++) {
				parseContent(multipart.getBodyPart(i), true);
			}
		} else if (part.isMimeType("message/rfc822")) {
			parseContent((Part) part.getContent(), true);
		} else {
			// do nothing
		}

	}

	/**
	 * 因为在Linux环境下, content-type为text/html的邮件如果缺失了头部格式则解析失败的问题.
	 * 
	 * @param htmlBody
	 * @return 正确格式的html
	 */
	private String addMissHtml(String htmlBody) {
		StringBuilder miss = new StringBuilder();
		miss.append("<!DOCTYPE html>\r\n");
		miss.append("<html>\r\n");
		miss.append("<head>\r\n");
		miss.append("<meta http-equiv=\\\"Content-Type\\\" content=\\\"text/html; charset=gbk\\\" />");
		miss.append("</head>\r\n");

		miss.append("<body>\r\n");
		miss.append(htmlBody);
		miss.append("\r\n</body>\r\n");

		miss.append("</html>\r\n");
		return miss.toString();
	}

	/**
	 * 判断邮件是否需要回执，如需回执返回true，否则返回false
	 * 
	 * @return
	 * @throws MessagingException
	 */
	public boolean getReplySign() throws MessagingException {
		boolean replySign = false;
		String needreply[] = msg.getHeader("Disposition-Notification-TO");
		if (needreply != null) {
			replySign = true;
		}
		return replySign;
	}

	/**
	 * 获取此邮件的message-id
	 * 
	 * @return
	 * @throws MessagingException
	 */
	public String getMessageId() throws MessagingException {
		return msg.getMessageID();
	}

	/**
	 * 判断此邮件是否已读，如果未读则返回false，已读返回true
	 * 
	 * @return
	 * @throws MessagingException
	 */
	private boolean haveRead() {
		boolean isnew = false;
		try {
			if (msg.isSet(Flags.Flag.SEEN)) {
				return true;
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return isnew;
	}

	/**
	 * 判断是是否包含附件
	 * 
	 * @param part
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	private boolean haveAttach(Part part) {
		boolean haveAttach = false;
		try {
			if (part.isMimeType("multipart/*")) {
				Multipart multipart = (Multipart) part.getContent();
				int count = multipart.getCount();
				for (int i = 0; i < count; i++) {
					BodyPart bodypart = multipart.getBodyPart(i);
					String dispostion = bodypart.getDisposition();
					if ((dispostion != null)
							&& (dispostion.equals(Part.ATTACHMENT))) {
						return true;
					} else if (bodypart.isMimeType("multipart/*")) {
						haveAttach = haveAttach(bodypart);
						if (haveAttach) {
							return haveAttach;
						}
					} else {
						String conType = bodypart.getContentType();
						if (conType.toLowerCase().indexOf("appliaction") != -1) {
							return true;
						}
						if (conType.toLowerCase().indexOf("name") != -1) {
							return true;
						}
					}
				}
			} else if (part.isMimeType("message/rfc822")) {
				haveAttach = haveAttach((Part) part.getContent());
				if (haveAttach) {
					return haveAttach;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private void parseAttach(Part part) throws Exception {
		if (part.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) part.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				BodyPart mpart = mp.getBodyPart(i);
				String dispostion = mpart.getDisposition();
				if (mpart.isMimeType("multipart/*")) {
					parseAttach(mpart);
				} else if ((dispostion != null)
						&& (dispostion.equals(Part.ATTACHMENT))) {
					String filename = "";
					filename = mpart.getFileName();
					if (!StringUtils.isValid(filename))
						continue;
					filename = getUtf8Str(filename);

					// setup mail attach
					String contentType = mpart.getContentType();
					attachDTOs
							.add(createAttachDTO(mpart, filename, contentType));
				}
			}
		} else if (part.isMimeType("message/rfc822")) {
			parseAttach((Part) part.getContent());
		}
	}

	private MailAttachDTO createAttachDTO(BodyPart mpart, String filename,
			String contentType) throws MessagingException {
		MailAttachDTO mailAttachDTO = new MailAttachDTO();
		mailAttachDTO.setFileName(filename);
		mailAttachDTO.setSize((long) mpart.getSize());
		mailAttachDTO.setMimeType(contentType.substring(0,
				contentType.indexOf(";")));
		if (mpart instanceof MimePart) {
			MimePart mimePart = (MimePart) mpart;
			mailAttachDTO.setContentId(mimePart.getContentID());
		}
		return mailAttachDTO;
	}

	private String getUtf8Str(String raw) {
		try {
			return MimeUtility.decodeText(raw);
		} catch (Exception e) {
			return raw;
		}
	}

	public Date getReceivedDate() {
		try {
			return DateUtils.date2date(msg.getReceivedDate(),
					DateUtils.TIMESTAMP_FORMAT_STR);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return DateUtils.now();
	}

	private MailDTO receiveSimpleInfo() throws MessagingException, IOException {
		MailDTO mail = new MailDTO();
		mail.setMessageId(getMessageId());
		mail.setMessageNum(msg.getMessageNumber());
		mail.setSender(getSender());
		mail.setSubject(getSubject());
		mail.setSendDate(getReceivedDate());
		mail.setSize(msg.getSize());
		mail.setBcc(getMailAddress("BCC"));
		mail.setCc(getMailAddress("CC"));
		mail.setRecieve(getMailAddress("TO"));
		mail.setHaveRead(haveRead());
		// TODO 考虑不要获取是否附件标识，因为获取该标识需下载整封邮件(包括附件)
		boolean flag = haveAttach(msg);
		mail.setHaveAttach(flag);
		return mail;
	}

	public MailDTO receive() throws Exception {
		boolean flag = haveAttach(msg);
		parseContent(msg, false);

		MailDTO mail = receiveSimpleInfo();
		mail.setContent(getBodyText());
		mail.setHaveAttach(flag);
		if (flag) {
			parseAttach(msg);
			mail.setAttachs(attachDTOs);
		}
		return mail;
	}

	/**
	 * 对应附件输出流
	 * 
	 * @param attachFileName
	 *            附件文件名
	 * @return
	 */
	public InputStream getAttachInputStream(String attachFileName) {
		try {
			return extractStream(msg, attachFileName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private InputStream extractStream(Part part, String attachFileName)
			throws Exception {
		if (part.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) part.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				BodyPart mpart = mp.getBodyPart(i);
				String dispostion = mpart.getDisposition();
				if (dispostion != null
						&& (dispostion.equals(Part.ATTACHMENT) || dispostion
								.equals(Part.INLINE))) {
					String fileName = getUtf8Str(mpart.getFileName());
					if (attachFileName.equals(fileName)) {
						return mpart.getInputStream();
					}
				} else if (mpart.isMimeType("multipart/*")) {
					InputStream extractStream = extractStream(mpart,
							attachFileName);
					if (extractStream != null) {
						return extractStream;
					}
				} else {
					String fileName = getUtf8Str(mpart.getFileName());
					if (attachFileName.equals(fileName)) {
						return mpart.getInputStream();
					}
				}
			}

		} else if (part.isMimeType("message/rfc822")) {
			InputStream extractStream = extractStream((Part) part.getContent(),
					attachFileName);
			if (extractStream != null) {
				return extractStream;
			}
		}
		return null;
	}

}