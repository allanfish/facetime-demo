package com.conlect.oatos.test;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import junit.framework.Assert;

import org.junit.Test;

import com.conlect.oatos.dto.autobean.mail.IMailAccountDTO;
import com.conlect.oatos.dto.autobean.mail.IMailDTO;
import com.conlect.oatos.dto.client.mail.MailAccountDTO;
import com.conlect.oatos.dto.client.mail.MailAttachDTO;
import com.conlect.oatos.dto.client.mail.MailDTO;
import com.conlect.oatos.dto.client.mail.MailFolderDTO;
import com.conlect.oatos.dto.client.mail.MailFolderListDTO;
import com.conlect.oatos.dto.client.mail.MailQueryDTO;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.mail.MailHelper;
import com.conlect.oatos.mail.MailReceiver;
import com.conlect.oatos.mail.MailSender;
import com.conlect.oatos.utils.DateUtils;

public class MailHelperTest {

	private static final String FOLDER_URL_GMAIL = "imaps://pinterestyufei%40gmail.com@imap.gmail.com/INBOX";
	private static final String INBOX_URL_GMAIL2 = "imaps://oatosyufei%40gmail.com@imap.gmail.com/INBOX";
	private static final String FOLDER_URL_163 = "imaps://googleyufei%40163.com@imap.163.com/INBOX";
	private static final String FOLDER_URL_QQ = "imaps://googleyufei%40qq.com@imap.qq.com/INBOX";
	public static final String MAIL_TYPE_163 = "163";
	public static final String MAIL_TYPE_QQ = "qq";
	public static final String MAIL_TYPE_126 = "126";
	public static final String MAIL_TYPE_GMAIL = "gmail";

	@Test
	public void testTimer() {
		System.out.println(new Date(1348452102655L));
	}

	@Test
	public void test() {
		MailFolderListDTO folderList = new MailFolderListDTO();
		String result = PojoMapper.toJson(folderList);
		System.out.println("result:" + result);
		System.out.println(PojoMapper.fromJsonAsObject(result, MailFolderListDTO.class));
	}

	@Test
	public void receiveLatestFolder() throws Exception {
		MailQueryDTO query = new MailQueryDTO();
		query.setFolderurl(INBOX_URL_GMAIL2);
		query.setLatestReceiveDate(DateUtils.str2date("2012-09-19 13:37:00 ", DateUtils.TIMESTAMP_FORMAT_STR));
		MailHelper.receiveNewestFolder(getGmailDTO2(), query);
	}

	@Test
	public void testSaveDraftMail() throws Exception {
		IMailAccountDTO mailAcc = get163DTO();
		MailDTO mail = create163Mail();
		MailHelper.saveDraftMail(mailAcc, mail);
	}

	private MailDTO create163Mail() {
		MailDTO mail = new MailDTO();
		mail.setSender("googleyufei@163.com");
		mail.setRecieve("googleyufei@126.com");
		mail.setContent("hello world <br>");
		mail.setSendDate(new Date());
		mail.setSubject("test save draft");
		return mail;
	}

	@Test
	public void getFolderInfoOf126Mail() throws Exception {
		IMailAccountDTO mailAcc = get126DTO();
		List<MailFolderDTO> list = MailHelper.takeMailFolders(mailAcc, createQueryInfo());
		for (MailFolderDTO mailFolder : list) {
			System.out.println(mailFolder);
		}
	}

	@Test
	public void getFolderInfoOf163Mail() throws Exception {
		List<MailFolderDTO> list = MailHelper.takeMailFolders(get163DTO(), createQueryInfo());
		for (MailFolderDTO mailFolder : list) {
			System.out.println(mailFolder);
		}
	}

	@Test
	public void getFolderInfoOfGmail() throws Exception {
		List<MailFolderDTO> list = MailHelper.takeMailFolders(getGmailDTO(), createQueryInfo());
		for (MailFolderDTO mailFolder : list) {
			System.out.println(mailFolder);
		}
	}

	private MailQueryDTO createQueryInfo() {
		MailQueryDTO queryInfo = new MailQueryDTO();
		queryInfo.setBeginIndex(0);
		queryInfo.setMaxSize(400);
		return queryInfo;
	}

	@Test
	public void getGmailMailAcc() {
		IMailAccountDTO mailAcc = this.getGmailDTO();
		System.out.println(mailAcc);
	}

	@Test
	public void testConnect126() throws Exception {
		IMailAccountDTO mailAcc = get126DTO();
		Store result = MailHelper.getStore(mailAcc);
		System.out.println("126 connect result:" + (result != null));
	}

	@Test
	public void testConnect163() {
		Store store = MailHelper.getStore(get163DTO());
		Assert.assertNotNull(store);
	}

	@Test
	public void testConnectQQ() {
		Store store = MailHelper.getStore(getQQDTO());
		Assert.assertNotNull(store);
	}

	@Test
	public void testConnectGmail() throws Exception {
		IMailAccountDTO mailAcc = getGmailDTO();
		Store result = MailHelper.getStore(mailAcc);
		System.out.println("gmail connect result:" + (result != null));
	}

	@Test
	public void testHotmail() throws Exception {
		IMailAccountDTO mailAcc = getHotmailDTO();
		Store result = MailHelper.getStore(mailAcc);
		System.out.println("hotmail connect result:" + (result != null));
	}

	@Test
	public void testConnectGmailByPOP3() throws Exception {
		IMailAccountDTO mailAcc = getGmailDTO();
		mailAcc.setMailProtocol(MailHelper.PROTOCOL_POP3);
		Store result = MailHelper.getStore(mailAcc);
		System.out.println("gmail connect result:" + (result != null));
	}

	@Test
	public void testCreateMailFolderQueryDTOWithMessageId() {
		System.out.println("=====================163 Mail===============");
		String messageId = "<1115205010.196309001314407903140.JavaMail.weibo@service.netease.com>";
		MailQueryDTO mailQuery = createMailFolderQuery(MAIL_TYPE_163);
		mailQuery.setMessageId(messageId);
		System.out.println(mailQuery);

		System.out.println("\n=====================Gmail===============");
		messageId = "<201204282147315219000@grgbanking.com>";
		mailQuery = createMailFolderQuery(MAIL_TYPE_GMAIL);
		mailQuery.setMessageId(messageId);
		System.out.println(mailQuery);

	}

	@Test
	public void testDeleteMails() throws Exception {
		MailHelper.deleteMails(get163DTO(), FOLDER_URL_163, new String[] { "<tencent_0848A8073633492765153FA9@qq.com>",
				"<68c5cc31a5fc48c5df22d651e0fdccc5@mailer.dzone.com>",
				"<ea9b41f7256905afdf7c01ff7ff137f0@mailer.dzone.com>",
				"<1009018341.0.1342672903223.JavaMail.YUFEI@conlect10-pc>" });
	}

	@Test
	public void get163MailAcc() {
		IMailAccountDTO mailAcc = this.get163DTO();
		System.out.println(mailAcc);
	}

	@Test
	public void getMailFoldersOf126() throws Exception {
		MailHelper.takeMailFolders(get126DTO(), createQueryInfo());
	}

	@Test
	public void getMailFoldersOf163() throws Exception {
		List<MailFolderDTO> folderList = MailHelper.takeMailFolders(get163DTO(), createQueryInfo());
		for (MailFolderDTO folder : folderList) {
			System.out.println(folder.toString());
		}
	}

	@Test
	public void getMailFoldersOfQQ() throws Exception {
		MailHelper.takeMailFolders(getQQDTO(), createQueryInfo());
	}

	@Test
	public void getMailFoldersOfGmail() throws Exception {
		List<MailFolderDTO> folderList = MailHelper.takeMailFolders(getGmailDTO(), createQueryInfo());
		for (MailFolderDTO folder : folderList) {
			System.out.println(folder.toString());
		}
	}

	@Test
	public void getMailFoldersOfHotmail() throws Exception {
		List<MailFolderDTO> folderList = MailHelper.takeMailFolders(getHotmailDTO(), createQueryInfo());
		for (MailFolderDTO folder : folderList) {
			System.out.println(folder.toString());
		}
	}

	@Test
	public void getMailFolderQueryOf163Mail() {
		MailQueryDTO mailQuery = this.createMailFolderQuery(MAIL_TYPE_163);
		System.out.println(mailQuery);
	}

	@Test
	public void getMailFolderQueryOfGmail() {
		MailQueryDTO query = this.createMailFolderQuery(MAIL_TYPE_GMAIL);
		System.out.println(query);
	}

	@Test
	public void getMailInfoOf163() throws Exception {
		String messageId = "<CAAAGmp94yt_oNZTAMyvHa3cYuWHEkxrV78xDCc+SKrr8KSFx7w@mail.gmail.com>";
		MailDTO mail = MailHelper.getMailInfo(get163DTO(), FOLDER_URL_163, messageId, null);
		mail.printInfo();
	}

	@Test
	public void getMailInfoByMessageNumOf163() throws Exception {
		String messageId = null;
		MailDTO mail = MailHelper.getMailInfo(get163DTO(), FOLDER_URL_163, messageId, null);
		mail.printInfo();
	}

	@Test
	public void getMailInfoOfGmail() throws Exception {
		String messageId = "<201204282147315219000@grgbanking.com>";
		MailQueryDTO mailQuery = createMailFolderQuery(MAIL_TYPE_GMAIL);
		mailQuery.setMessageId(messageId);

		List<MailDTO> list = MailHelper.getMails(getGmailDTO(), mailQuery);
		Assert.assertEquals(1, list.size());

		MailDTO mail = list.get(0);
		Assert.assertEquals(messageId, mail.getMessageId());
	}

	@Test
	public void getMailListOf163Mail() throws Exception {
		MailQueryDTO mailQuery = createMailFolderQuery(MAIL_TYPE_163);
		List<MailDTO> mailList = MailHelper.getMails(get163DTO(), mailQuery);
		for (MailDTO mail : mailList) {
			System.out.println("{[subject]:" + mail.getSubject() + ", [message numb]:" + mail.getMessageNum() + "}");
		}
	}

	@Test
	public void getMailListOfQQ() throws Exception {
		MailQueryDTO mailQuery = createMailFolderQuery(MAIL_TYPE_QQ);
		List<MailDTO> mailList = MailHelper.getMails(getQQDTO(), mailQuery);
		for (MailDTO mail : mailList) {
			mail.printSimpleInfo();
		}
	}

	@Test
	public void getUnreadMailListOf163Mail() throws Exception {
	}

	@Test
	public void getMailListOfGmail() throws Exception {
		MailQueryDTO mailQuery = createMailFolderQuery(MAIL_TYPE_GMAIL);
		List<MailDTO> mailList = MailHelper.getMails(getGmailDTO(), mailQuery);
		for (MailDTO mail : mailList) {
			System.out.println("{[subject]:" + mail.getSubject() + ", [message numb]:" + mail.getMessageNum() + "}");
		}
	}

	@Test
	public void testReceive() throws Exception {
		Store store = MailHelper.getStore(get163DTO());
		Folder folder = store.getFolder("订阅邮件");
		folder.open(Folder.READ_WRITE);
		Message msgs[] = folder.getMessages();
		int count = msgs.length;
		System.out.println("Message Count:" + count);
		System.out.println("Message unread:" + folder.getUnreadMessageCount());
		MailReceiver rm = null;
		for (int i = 0; i < count; i++) {
			MimeMessage mimeMsg = (MimeMessage) msgs[i];
			rm = new MailReceiver(mimeMsg);
			rm.receive();
		}
	}

	@Test
	public void sendMailWithAttach() throws Exception {
		MailDTO mail = new MailDTO();
		mail.setRecieve("googleyufei@126.com");
		mail.setSender("googleyufei@163.com");
		mail.setSubject("test send mail with attach");
		mail.setContent("<font color='red'>强哥邀请你访问我的博客：http://cuisuqiang.iteye.com/！</font>");
		mail.setHaveAttach(true);
		mail.getAttachs().add(createAttachDTO("box.txt", ""));
		MailHelper.sendMail(get163DTO(), mail);
	}

	private MailAttachDTO createAttachDTO(String fileName, String filePath) {
		MailAttachDTO attachDTO = new MailAttachDTO();
		attachDTO.setFileName(fileName);
		attachDTO.setFilePath(filePath);
		return attachDTO;
	}

	@Test
	public void sendMailWithoutAttach() throws Exception {
		for (int i = 0; i < 10; i++) {
			Thread.sleep(2000);
			MailDTO mail = new MailDTO();
			mail.setSender(get163DTO().getMailAddress());
			mail.setRecieve(get126DTO().getMailAddress());
			mail.setSubject(String.format("test send mail >> [%d]", i + 5));
			mail.setContent("<div style='color:red;font-size:29'>飞哥邀请你访问我的博客：http://googleyufei.iteye.com/！</div>");
			MailHelper.sendMail(get163DTO(), mail);
		}
	}

	@Test
	public void sendReplyMailOfFastType() throws Exception {
		String messageId = "<1002885402.7.1348032957967.JavaMail.david@david-PC>";
		MailDTO mail = MailHelper.getMailInfo(get163DTO(), FOLDER_URL_163, messageId, null);

		MailDTO replyMail = new MailDTO();
		replyMail.setMailAccountId(mail.getMailAccountId());
		replyMail.setFolderUrl(mail.getFolderUrl());
		replyMail.setMessageId(messageId);
		replyMail.setRecieve(getSafeMailAddr(mail.getSender()));
		replyMail.setCc(mail.getCc());
		replyMail.setBcc(mail.getBcc());
		replyMail.setContent("this is a test fast reply !");
		replyMail.setReplyType(IMailDTO.FAST_REPLY);
		MailSender.sendReplyMail(get163DTO(), replyMail);
	}

	@Test
	public void sendReplyGamilOfFastType() throws Exception {
		String messageId = "<1593111626.9.1348034886658.JavaMail.david@david-PC>";
		MailDTO mail = MailHelper.getMailInfo(getGmailDTO2(), INBOX_URL_GMAIL2, messageId, null);

		MailDTO replyMail = new MailDTO();
		replyMail.setMailAccountId(mail.getMailAccountId());
		replyMail.setFolderUrl(mail.getFolderUrl());
		replyMail.setMessageId(messageId);
		replyMail.setRecieve(getSafeMailAddr(mail.getSender()));
		replyMail.setCc(mail.getCc());
		replyMail.setBcc(mail.getBcc());
		replyMail.setContent("this is a test fast reply !");
		replyMail.setReplyType(IMailDTO.FAST_REPLY);
		MailSender.sendReplyMail(getGmailDTO2(), replyMail);
	}

	@Test
	public void testSetMailRead() throws Exception {
		MailHelper.setMailRead(get163DTO(), FOLDER_URL_163, "<tencent_5EAA72526798644C27FC4F0F@qq.com>", null);
	}

	private MailQueryDTO createMailFolderQuery(String mailType) {
		MailQueryDTO query = new MailQueryDTO();
		if (MAIL_TYPE_163.equals(mailType)) {
			query.setFolderurl(FOLDER_URL_163);
		} else if (MAIL_TYPE_GMAIL.equals(mailType)) {
			query.setFolderurl(FOLDER_URL_GMAIL);
		} else if (MAIL_TYPE_QQ.equals(mailType)) {
			query.setFolderurl(FOLDER_URL_QQ);
		}
		query.setBeginIndex(0);
		query.setMaxSize(60);
		query.setMailAccountId(201);
		query.setMaxSentDate(new Date());
		query.setMinSentDate(DateUtils.addMonths(new Date(), -1));
		return query;
	}

	private IMailAccountDTO get126DTO() {
		IMailAccountDTO mail126 = new MailAccountDTO();
		mail126.setMailAddress("googleyufei@126.com");
		mail126.setPassword("mind#523625");
		mail126.setUserId(21);
		mail126.setSavePwd(true);
		mail126.setAccountDesc("126");
		mail126.setSenderName("余飞");
		return mail126;
	}

	private IMailAccountDTO get163DTO() {
		IMailAccountDTO mail163 = new MailAccountDTO();
		mail163.setMailAddress("googleyufei@163.com");
		mail163.setPassword("mind#523625");
		mail163.setUserId(21);
		mail163.setSavePwd(true);
		mail163.setAccountDesc("163");
		mail163.setSenderName("余飞");
		return mail163;
	}

	private IMailAccountDTO getQQDTO() {
		IMailAccountDTO qqMail = new MailAccountDTO();
		qqMail.setMailAddress("googleyufei@qq.com");
		qqMail.setPassword("mind#523625");
		qqMail.setUserId(21);
		qqMail.setSavePwd(true);
		qqMail.setAccountDesc("163");
		qqMail.setSenderName("余飞");
		return qqMail;
	}

	private IMailAccountDTO getGmailDTO() {
		IMailAccountDTO mailAcc = new MailAccountDTO();
		mailAcc.setMailAddress("pinterestyufei@gmail.com");
		mailAcc.setPassword("mind#523625");
		mailAcc.setUserId(21);
		mailAcc.setSavePwd(true);
		mailAcc.setAccountDesc("163");
		mailAcc.setSenderName("余飞");
		return mailAcc;
	}

	private IMailAccountDTO getGmailDTO2() {
		IMailAccountDTO mailAcc = new MailAccountDTO();
		mailAcc.setMailAddress("oatosyufei@gmail.com");
		mailAcc.setPassword("mind#523625");
		mailAcc.setUserId(21);
		mailAcc.setSavePwd(true);
		mailAcc.setAccountDesc("oatosyufei");
		mailAcc.setSenderName("余飞");
		return mailAcc;
	}

	private IMailAccountDTO getHotmailDTO() {
		IMailAccountDTO mailAcc = new MailAccountDTO();
		mailAcc.setMailAddress("googleyufei@hotmail.com");
		mailAcc.setPassword("mind#523625");
		mailAcc.setUserId(21);
		mailAcc.setSavePwd(true);
		mailAcc.setAccountDesc("hotmail");
		mailAcc.setSenderName("余飞");
		mailAcc.setMailProtocol("pop3");
		return mailAcc;
	}

	@Test
	public void getMailAddr() {
		String mailAddr = "<googleyufei@163.com>";
		mailAddr = getSafeMailAddr(mailAddr);
		System.out.println(mailAddr);
	}

	public static String getSafeMailAddr(String mailAddr) {
		Pattern reg = Pattern.compile("[\\w_]+@[\\w_]+\\.[A-Za-z]{2,4}");
		Matcher exec = reg.matcher(mailAddr);
		if (exec.find()) {
			return exec.group(0);
		}
		return mailAddr;
	}
	
	public static void main(String[] args) {
		String fileName ="hellp.text";
		System.out.println(CommonUtil.string2ASCII(fileName));
		
		String zhFileName = "你好中国.txt";
		System.out.println(CommonUtil.string2ASCII(zhFileName));
	}
	
}
