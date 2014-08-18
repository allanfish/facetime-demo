package com.conlect.oatos.mail;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.IllegalWriteException;
import javax.mail.Message;
import javax.mail.MessageRemovedException;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.MimeMessage;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.MessageIDTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SentDateTerm;
import javax.mail.search.SubjectTerm;

import com.conlect.oatos.dto.autobean.mail.IMailAccountDTO;
import com.conlect.oatos.dto.client.mail.MailDTO;
import com.conlect.oatos.dto.client.mail.MailFolderDTO;
import com.conlect.oatos.dto.client.mail.MailQueryDTO;
import com.conlect.oatos.utils.StringUtils;
import com.conlect.oatos.utils.SysLogger;

public class MailHelper {

	public static class SimpleAuthenticator extends Authenticator {

		private String username;

		private String password;

		public SimpleAuthenticator(String username, String password) {
			this.username = username;
			this.password = password;
		}

		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(this.username, this.password);

		}

	}

	public static final String PROTOCOL_POP3 = "pop3";
	public static final String PROTOCOL_IMAPS = "imaps";
	public static final String MAIL_SMTP_PORT = "mail.smtp.port";
	public static final String MAIL_SMTP_HOST = "mail.smtp.host";
	public static final String MAIL_POP3_PORT = "mail.pop3.port";
	public static final String MAIL_POP3_HOST = "mail.pop3.host";
	public static final String MAIL_IMAP_HOST = "mail.imap.host";
	public static final String MAIL_IMAP_PORT = "mail.imap.port";
	public static final String MAIL_STORE_PROTOCOL = "mail.store.protocol";

	public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
	private static final Pattern pattern = Pattern.compile("@[^\\.]*\\.");

	private static MailFolderDTO asFolderDTO(IMailAccountDTO mailAcc,
			Folder folder) throws Exception {
		return asFolderDTO(mailAcc, folder, null);
	}

	private static MailFolderDTO asFolderDTO(IMailAccountDTO mailAcc,
			Folder folder, MailQueryDTO queryInfo) throws Exception {
		SysLogger.defaultLogger.debug("[start]-[receive folder:"
				+ folder.getFullName() + "]");
		MailFolderDTO mailFolder = new MailFolderDTO();
		mailFolder.setMailAccountId(mailAcc.getMailAccountId());
		mailFolder.setName(folder.getName());
		mailFolder.setFullName(folder.getFullName());
		mailFolder.setUrlName(folder.getURLName() + "");
		mailFolder.setParentFolderUrl(folder.getParent() != null ? folder
				.getParent().getURLName().toString() : null);
		mailFolder.setMsgCount(folder.getMessageCount());
		mailFolder.setUnreadMsgCount(folder.getUnreadMessageCount());

		if (queryInfo.getLatestReceiveDate() != null) {
			SysLogger.defaultLogger.debug("queryInfo.getLatestReceiveDate(), "
					+ queryInfo.getLatestReceiveDate());
			List<MailDTO> latestMails = getLatestMails(mailAcc, folder,
					queryInfo.getLatestReceiveDate());
			if (latestMails.size() > 0) {
				mailFolder.setMailList(latestMails);
				mailFolder.setLatestReceiveDate(mailFolder.getMailList().get(0)
						.getSendDate());
			} else {
				mailFolder.setLatestReceiveDate(queryInfo
						.getLatestReceiveDate());
			}
		} else {
			queryInfo.setFolderurl(mailFolder.getUrlName());
			List<MailDTO> mails = getMails(mailAcc, folder, queryInfo);
			if (mails.size() > 0) {
				mailFolder.setMailList(mails);
				mailFolder.setLatestReceiveDate(mails.get(0).getSendDate());
			} else {
				mailFolder.setLatestReceiveDate(mailFolder
						.getLatestReceiveDate());
			}
		}
		try {
			Folder[] childList = folder.list();
			if (childList != null && childList.length > 0) {
				for (Folder child : childList) {
					mailFolder.getChildren().add(asFolderDTO(mailAcc, child));
				}
			}
		} catch (Exception e) {
			SysLogger.defaultLogger.debug("this folder has not children");
		}
		SysLogger.defaultLogger.debug(String.format(
				"[end]-[receive fodler:%s]-[msgCount:%d]-[mailList.size:%d]",
				folder.getFullName(), mailFolder.getMsgCount(), mailFolder
						.getMailList().size()));
		return mailFolder;
	}

	public static boolean canConnect(IMailAccountDTO mailAcc) {
		SysLogger.defaultLogger.debug("start canConnect(..)");
		return getStore(mailAcc) != null;
	}

	public static void close(Store store, Folder folder) {
		try {
			if (store != null) {
				store.close();
			}
			if (folder != null) {
				folder.close(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 会直接删除邮件服务器上的邮件, 且在'已删除'中找不到.
	 * 
	 * @param mailAcc
	 * @param mailQuery
	 * @throws Exception
	 */
	public static void deleteMails(IMailAccountDTO mailAcc, String folderUrl,
			List<String> messageIds, List<String> subjects) throws Exception {
		try {
			Session session = MailHelper.getSession(mailAcc);
			Store store = MailHelper.getStore(session, mailAcc);
			Folder folder = MailHelper.getFolder(store, folderUrl);
			folder.open(Folder.READ_WRITE);

			Message[] msgs = getMessages(folder, messageIds, subjects);
			folder.setFlags(msgs, new Flags(Flag.DELETED), true);
			folder.close(true); // 必须是true ,否则删除报错
			store.close();
			System.out.println("成功删除邮件:");
		} catch (MessageRemovedException ex) {
			// 不做处理, 邮件会被删除
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 会直接删除邮件服务器上的邮件, 且在'已删除'中找不到.
	 * 
	 * @param mailAcc
	 * @param mailQuery
	 * @throws Exception
	 */
	public static void deleteMails(IMailAccountDTO mailAcc, String folderUrl,
			String[] messageIds) throws Exception {
		try {
			Session session = MailHelper.getSession(mailAcc);
			Store store = MailHelper.getStore(session, mailAcc);
			Folder folder = MailHelper.getFolder(store, folderUrl);
			folder.open(Folder.READ_WRITE);
			Message[] msgs = getMessages(folder, messageIds);
			folder.setFlags(msgs, new Flags(Flag.DELETED), true);
			folder.close(true); // 必须是true ,否则删除报错
			store.close();
			System.out.println("成功删除邮件:");
		} catch (MessageRemovedException ex) {
			// 不做处理, 邮件会被删除
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 获取邮件附件输出流
	 * 
	 * @param mailAcc
	 * @param folderurl
	 * @param messageId
	 * @param attachFileName
	 * @return
	 */
	public static InputStream getAttachInputStream(IMailAccountDTO mailAcc,
			String folderurl, String messageId, String attachFileName) {
		try {
			Store store = getStore(mailAcc);
			URLName urlname = new URLName(folderurl);
			Folder folder = store.getFolder(urlname);
			if (folder == null || !folder.exists()) {
				return null;
			}
			folder.open(Folder.READ_WRITE);
			SearchTerm messageIdTerm = new MessageIDTerm(messageId);
			Message[] msgs = folder.search(messageIdTerm);
			if (msgs == null || msgs.length == 0) {
				return null;
			}

			MailReceiver rm = new MailReceiver((MimeMessage) msgs[0]);
			SysLogger.defaultLogger.debug("download attach from mailbox ok!");
			return rm.getAttachInputStream(attachFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Properties getConnectProperties(IMailAccountDTO mailAcc,
			boolean isIMAP) {
		Properties properties = new Properties();
		if (mailAcc.isUserSetting()) {
			properties.setProperty(MAIL_SMTP_HOST, mailAcc.getSenderServer());
			properties.setProperty(MAIL_SMTP_PORT, mailAcc.getSenderPort());
			properties.setProperty(isIMAP ? MAIL_IMAP_HOST : MAIL_POP3_HOST,
					mailAcc.getReceiverServer());
			properties.setProperty(isIMAP ? MAIL_IMAP_PORT : MAIL_POP3_PORT,
					mailAcc.getReceiverPort());
			properties.setProperty(MAIL_STORE_PROTOCOL, isIMAP ? PROTOCOL_IMAPS
					: PROTOCOL_POP3);
			properties.setProperty(MAIL_TRANSPORT_PROTOCOL, "smtp");
		} else if (!mailAcc.isUserSetting()) {
			String mailType = getMailType(mailAcc.getMailAddress());
			properties = MailConfigure.getDefaultConfig(mailType, isIMAP);
		}
		return properties;
	}

	private static String getMailType(String mailAddr) {
		Matcher matcher = pattern.matcher(mailAddr);
		if (matcher.find()) {
			String group = matcher.group(0);
			return group.substring(1, group.length() - 1);
		}
		throw new AssertionError(String.format("mailAddr %s is invalid",
				mailAddr));
	}

	public static Folder getFolder(Store store, String folderUrl) {
		try {
			URLName urlname = new URLName(folderUrl);
			Folder folder = store.getFolder(urlname);
			if (folder == null || !folder.exists()) {
				System.out.println("folder " + folderUrl + " is not exists");
				return null;
			}
			return folder;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static MailFolderDTO getMailFolder(IMailAccountDTO mailAcc,
			String folderUrl) throws Exception {
		Store store = getStore(mailAcc);
		Folder folder = getFolder(store, folderUrl);
		if (folder == null || !folder.exists()) {
			return null;
		}

		MailFolderDTO folderBean = asFolderDTO(mailAcc, folder);
		// folderBean.setChildren(getSubFolder(folder, mailAcc));
		folderBean.setMailAccountId(mailAcc.getMailAccountId());
		return folderBean;
	}

	public static MailDTO getMailInfoByMessageId(IMailAccountDTO mailAcc,
			String folderUrl, String messageId) {
		return getMailInfo(mailAcc, folderUrl, messageId, null);
	}

	/**
	 * 当messageId为空时, 通过邮件主题查询邮件
	 * 
	 * @param mailAcc
	 * @param folderUrl
	 * @param subject
	 * @return
	 */
	public static MailDTO getMailInfoBySubject(IMailAccountDTO mailAcc,
			String folderUrl, String subject) {
		return getMailInfo(mailAcc, folderUrl, null, subject);
	}

	/**
	 * 返回指定邮箱, 文件夹, 邮件ID的邮件信息
	 * 
	 * @param mailAcc
	 *            邮箱账号
	 * @param messageNum
	 *            邮件在文件夹中的index. 针对163邮箱有些邮件的messageId为null的情况
	 * @param mailQuery
	 *            邮件文件夹:{@link MailQueryDTO#getFolderurl()} , 邮件ID:
	 *            {@link MailQueryDTO#getMessageId()}
	 * 
	 */
	public static MailDTO getMailInfo(IMailAccountDTO mailAcc,
			String folderUrl, String messageId, String subject) {
		Session session = MailHelper.getSession(mailAcc);
		Store store = MailHelper.getStore(session, mailAcc);
		Folder folder = MailHelper.getFolder(store, folderUrl);
		MimeMessage msg = null;
		MailDTO mail = null;
		try {
			folder.open(Folder.READ_ONLY);
			msg = getMessage(folder, messageId, subject);
			MailReceiver rm = new MailReceiver(msg);
			mail = rm.receive();
			mail.setFolderUrl(folderUrl);
			mail.setMailAccountId(mailAcc.getMailAccountId());
		} catch (Exception e) {
			e.printStackTrace();
			SysLogger.defaultLogger.error("getMailInfo, exception="
					+ e.getMessage());
		}
		return mail;
	}

	/**
	 * 返回指定邮箱, 文件夹下的邮件信息列表
	 */
	public static List<MailDTO> getMails(IMailAccountDTO mailAcc,
			MailQueryDTO queryInfo) {
		List<MailDTO> list = new ArrayList<MailDTO>();
		try {
			Store store = getStore(mailAcc);
			Folder folder = store.getFolder(new URLName(queryInfo
					.getFolderurl()));
			folder.open(Folder.READ_ONLY);
			return getMails(mailAcc, folder, queryInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 返回指定邮箱, 文件夹下的邮件信息列表
	 */
	public static List<MailDTO> getMails(IMailAccountDTO mailAcc,
			Folder folder, MailQueryDTO queryInfo) {
		List<MailDTO> list = new ArrayList<MailDTO>();
		try {
			folder.open(Folder.READ_ONLY);
			List<SearchTerm> termList = new ArrayList<SearchTerm>();
			SearchTerm term = null;
			if (queryInfo.getMaxSentDate() != null) {
				termList.add(new SentDateTerm(ComparisonTerm.LE, queryInfo
						.getMaxSentDate()));
			}
			if (queryInfo.getMinSentDate() != null)
				termList.add(new SentDateTerm(ComparisonTerm.GE, queryInfo
						.getMinSentDate()));

			if (termList.size() == 1) {
				term = termList.get(0);
			} else if (termList.size() > 1) {
				term = new AndTerm(termList.toArray(new SearchTerm[] {}));
			}
			Message[] msgs = null;
			if (term != null)
				msgs = folder.search(term);
			else
				msgs = folder.getMessages();
			for (Message msg : msgs) {
				MailReceiver rm = new MailReceiver((MimeMessage) msg);
				MailDTO mail = rm.receive();
				mail.setFolderUrl(folder.getURLName().toString());
				mail.setMailAccountId(mailAcc.getMailAccountId());
				list.add(mail);
			}
			sortMailList(list);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取最新的邮件
	 */
	private static List<MailDTO> getLatestMails(IMailAccountDTO mailAcc,
			Folder folder, Date latestReceiveDate) throws MessagingException {
		List<MailDTO> list = new ArrayList<MailDTO>();
		folder.open(Folder.READ_ONLY);
		Message[] msgs = null;
		String mailType = getMailType(mailAcc.getMailAddress());
		if ("gmail".equalsIgnoreCase(mailType)
				|| "googlemail".equalsIgnoreCase(mailType)) {
			msgs = folder.getMessages();
		} else {
			SearchTerm term = new SentDateTerm(ComparisonTerm.GT,
					latestReceiveDate);
			msgs = folder.search(term);
		}
		for (Message msg : msgs) {
			if (msg.getSentDate() != null && latestReceiveDate != null) {
				if (msg.getSentDate().after(latestReceiveDate)) {
					try {
						MailReceiver rm = new MailReceiver((MimeMessage) msg);
						MailDTO mail = rm.receive();
						mail.setFolderUrl(folder.getURLName().toString());
						mail.setMailAccountId(mailAcc.getMailAccountId());
						list.add(mail);
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
		}
		sortMailList(list);
		return list;
	}

	/**
	 * 查询文件夹下指定messageId的邮件
	 * 
	 * @param folder
	 * @param messageId
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage getMessage(Folder folder, String messageId)
			throws Exception {
		return getMessage(folder, messageId, null);
	}

	/**
	 * 返回文件夹下messageId或messageNum的邮件信息
	 * 
	 * @param folder
	 * @param messageId
	 * @param messageNum
	 *            对于某些邮件的messageId为null时, 可以通过messageNum
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage getMessage(Folder folder, String messageId,
			String subject) throws Exception {
		SearchTerm condition = null;
		if (StringUtils.isValid(messageId)) {
			condition = new MessageIDTerm(messageId);
		} else if (StringUtils.isValid(subject)) {
			condition = new SubjectTerm(subject);
		}
		Message[] msgs = folder.search(condition);
		if (msgs != null && msgs.length == 1) {
			return (MimeMessage) msgs[0];
		}
		return null;
	}

	public static MimeMessage[] getMessages(Folder folder,
			List<String> messageIds, List<String> subjects) throws Exception {
		List<MimeMessage> list = new ArrayList<MimeMessage>();
		if (messageIds != null) {
			for (String messageId : messageIds) {
				if (messageId == null) {
					continue;
				}
				MimeMessage message = getMessage(folder, messageId);
				if (message != null) {
					list.add(message);
				}
			}
		}
		if (subjects != null && subjects.size() > 0) {
			for (String subject : subjects) {
				if (!StringUtils.isValid(subject)) {
					continue;
				}
				MimeMessage message = getMessage(folder, null, subject);
				if (message != null) {
					list.add(message);
				}
			}
		}
		return list.toArray(new MimeMessage[] {});
	}

	public static MimeMessage[] getMessages(Folder folder, String[] messageIds)
			throws Exception {
		List<MimeMessage> list = new ArrayList<MimeMessage>();
		for (String messageId : messageIds) {
			if (messageId != null) {
				MimeMessage message = getMessage(folder, messageId);
				if (message != null) {
					list.add(message);
				}
			}
		}
		return list.toArray(new MimeMessage[] {});
	}

	public static Session getSession(IMailAccountDTO mailAcc) {
		boolean isPOP = StringUtils.isValid(mailAcc.getMailProtocol())
				&& mailAcc.getMailProtocol().equals("pop3");
		Properties props = getConnectProperties(mailAcc, !isPOP);
		SysLogger.defaultLogger.debug("mail props:" + props);
		try {
			Session session = Session.getDefaultInstance(
					props,
					new SimpleAuthenticator(mailAcc.getMailAddress(), mailAcc
							.getPassword()));
			session.setDebug(false);
			session.setDebugOut(System.out);
			return session;
		} catch (Exception e) {
			e.printStackTrace();
			SysLogger.defaultLogger
					.error("exec: getSession(MailAccountDTO mailAcc) throw exception");
			SysLogger.defaultLogger.error(e);
			return null;
		}
	}

	public static Store getStore(IMailAccountDTO mailAcc) {
		boolean isPOP = StringUtils.isValid(mailAcc.getMailProtocol())
				&& mailAcc.getMailProtocol().equals("pop3");
		Properties props = getConnectProperties(mailAcc, !isPOP);
		SysLogger.defaultLogger.debug("getstore,props=" + props);
		Store store = null;
		try {
			Session session = getSession(mailAcc);
			if (isPOP) {
				URLName url = new URLName(
						props.getProperty(MAIL_STORE_PROTOCOL),
						props.getProperty(MAIL_POP3_HOST),
						Integer.parseInt(props.getProperty(MAIL_POP3_PORT)),
						"", mailAcc.getMailAddress(), mailAcc.getPassword());
				store = session.getStore(url);
				store.connect();
			} else {
				store = session.getStore(PROTOCOL_IMAPS);
				store.connect(props.getProperty(MAIL_IMAP_HOST),
						mailAcc.getMailAddress(), mailAcc.getPassword());
			}
			SysLogger.defaultLogger.debug("get mail store success!");
			return store;
		} catch (Exception e) {
			e.printStackTrace();
			SysLogger.defaultLogger
					.error("exec: getStore(MailAccountDTO mailAcc) throw exception!");
			SysLogger.defaultLogger.error(e);
			return null;
		}
	}

	public static Store getStore(Session session, IMailAccountDTO mailAcc) {
		boolean isPOP = StringUtils.isValid(mailAcc.getMailProtocol())
				&& mailAcc.getMailProtocol().equals("pop3");
		Properties props = getConnectProperties(mailAcc, !isPOP);
		Store store = null;
		try {
			if (isPOP) {
				URLName url = new URLName(
						props.getProperty(MAIL_STORE_PROTOCOL),
						props.getProperty(MAIL_POP3_HOST),
						Integer.parseInt(props.getProperty(MAIL_POP3_PORT)),
						"", mailAcc.getMailAddress(), mailAcc.getPassword());
				store = session.getStore(url);
				store.connect();
			} else {
				store = session.getStore(PROTOCOL_IMAPS);
				store.connect(props.getProperty(MAIL_IMAP_HOST),
						mailAcc.getMailAddress(), mailAcc.getPassword());
			}
			return store;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Transport getTransport(Session session,
			IMailAccountDTO mailAcc) {
		try {
			Properties props = getConnectProperties(mailAcc, false);
			Transport transport = session.getTransport(props
					.getProperty(MAIL_TRANSPORT_PROTOCOL));
			transport.connect(props.getProperty(MAIL_SMTP_HOST),
					mailAcc.getMailAddress(), mailAcc.getPassword());
			return transport;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param account
	 * @param mailQueryDTO
	 * @return
	 * @throws Exception
	 */
	public static MailFolderDTO receiveNewestFolder(IMailAccountDTO account,
			MailQueryDTO mailQueryDTO) throws Exception {
		Store store = getStore(account);
		Folder folder = getFolder(store, mailQueryDTO.getFolderurl());
		if (folder == null || !folder.exists()) {
			return null;
		}

		MailFolderDTO folderBean = asFolderDTO(account, folder, mailQueryDTO);
		return folderBean;
	}

	public static void saveDraftMail(IMailAccountDTO mailAcc, MailDTO mail)
			throws Exception {
		MailSender.saveDraftMail(mailAcc, mail);
	}

	/**
	 * 发送文本/附件内容的邮件
	 * 
	 * @param mailAcc
	 * @param mail
	 * @throws Exception
	 */
	public static MimeMessage sendMail(IMailAccountDTO mailAcc, MailDTO mail)
			throws Exception {
		return MailSender.sendMail(mailAcc, mail);
	}

	/**
	 * 邮件快捷回复
	 * 
	 * @param mailAcc
	 * @param mail
	 * @throws Exception
	 */
	public static void sendReplyMail(IMailAccountDTO mailAcc, MailDTO mail) {
		try {
			MailSender.sendReplyMail(mailAcc, mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将邮件置为已读
	 * 
	 * @param mailAcc
	 * @param folderUrl
	 * @param messageId
	 * @param messageNum
	 * @throws Exception
	 */
	public static void setMailRead(IMailAccountDTO mailAcc, String folderUrl,
			String messageId, String subject) throws Exception {
		if (PROTOCOL_POP3.equals(mailAcc.getMailProtocol())) {
			return;
		}
		try {
			Session session = MailHelper.getSession(mailAcc);
			Store store = MailHelper.getStore(session, mailAcc);
			Folder folder = MailHelper.getFolder(store, folderUrl);
			folder.open(Folder.READ_WRITE);
			MimeMessage msg = MailHelper.getMessage(folder, messageId, subject);
			if (msg != null && !msg.isSet(Flag.SEEN)) {
				msg.setFlag(Flag.SEEN, true);
				msg.saveChanges();
			}
			folder.close(true);
			store.close();
		} catch (IllegalWriteException ex) {
			// 此处会报javax.mail.IllegalWriteException: IMAPMessage is read-only异常,
			// 当邮件已经被置为已读
		} catch (MessagingException e) {
			throw e;
		}
	}

	/**
	 * 通过messageNum 来排序. 在Gmail的某种情况会导致问题, 比如我的Gmail中关联了多个邮件,
	 * 此时messageNum排列的顺序和sentDate就不是一样的.
	 * 
	 * @param list
	 */
	private static void sortMailList(List<MailDTO> list) {
		Collections.sort(list, new Comparator<MailDTO>() {
			@Override
			public int compare(MailDTO o1, MailDTO o2) {
				if (o1.getSendDate() != null && o2.getSendDate() != null) {
					if (o2.getSendDate().after(o1.getSendDate())) {
						return 1;
					} else if (o2.getSendDate().before(o1.getSendDate())) {
						return -1;
					} else {
						return 0;
					}
				}
				return 0;
			}
		});
	}

	public static List<MailFolderDTO> takeMailFolders(IMailAccountDTO mailAcc,
			MailQueryDTO queryInfo) {
		Store store = getStore(mailAcc);
		List<MailFolderDTO> list = new ArrayList<MailFolderDTO>();
		if (store == null) {
			return list;
		}
		try {
			if (PROTOCOL_POP3.equals(mailAcc.getMailProtocol())) {
				Folder inboxFolder = store.getDefaultFolder().list()[0];
				MailFolderDTO folderBean = asFolderDTO(mailAcc, inboxFolder,
						queryInfo);
				list.add(folderBean);
				return list;
			}
			Folder[] folders = store.getDefaultFolder().list();
			for (Folder topFolder : folders) {
				try {
					MailFolderDTO folderBean = asFolderDTO(mailAcc, topFolder,
							queryInfo);
					folderBean.setParentFolderUrl(null);
					list.add(folderBean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
