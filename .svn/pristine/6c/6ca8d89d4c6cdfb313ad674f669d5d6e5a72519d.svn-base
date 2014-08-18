package com.qycloud.oatos.server.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitils.spring.annotation.SpringBeanByType;

import static org.junit.Assert.*;

import com.conlect.oatos.dto.autobean.mail.IMailAccountDTO;
import com.conlect.oatos.dto.client.NetworkFilesDTO;
import com.conlect.oatos.dto.client.mail.MailAccountDTO;
import com.conlect.oatos.dto.client.mail.MailAccountsDTO;
import com.conlect.oatos.dto.client.mail.MailAttachDTO;
import com.conlect.oatos.dto.client.mail.MailContactDTO;
import com.conlect.oatos.dto.client.mail.MailContactGroupDTO;
import com.conlect.oatos.dto.client.mail.MailContactGroupsDTO;
import com.conlect.oatos.dto.client.mail.MailContactsDTO;
import com.conlect.oatos.dto.client.mail.MailDTO;
import com.conlect.oatos.dto.client.mail.MailFileDTO;
import com.conlect.oatos.dto.client.mail.MailFolderDTO;
import com.conlect.oatos.dto.client.mail.MailFolderListDTO;
import com.conlect.oatos.dto.client.mail.MailListDTO;
import com.conlect.oatos.dto.client.mail.MailQueryDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.url.MailUrl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.dao.MailAccountMapper;
import com.qycloud.oatos.server.domain.entity.MailAccount;
import com.qycloud.oatos.server.domain.entity.MailAttach;

public class MailServiceTest extends BaseServiceTest{
	
	private static Map<String,Long> map =new HashMap<String,Long>();
	
	private static Long userId35558 = 249509L;
	
	private static String MailId="mailAccountId";
	private static String GroupId="mailContactGroupId";
	private static String contectId="mailContactId";
	
	
	@SpringBeanByType
	private MailAccountMapper mailAccountMapper;
	
	/***
	 * 增加邮件账号
	 * pass
	 */
	@Test
	public void addMailAccount(){
		System.out.println("================增加邮件账号================");
		MailAccountDTO mac = new MailAccountDTO();
		
		mac.setMailAddress("manbuzhiwu@126.com");
		mac.setUserId(userId35558);
		mac.setPassword("x4570849");
		mac.setAccountDesc("manbuzhiwu email");
		mac.setMailProtocol("IMAP");
		mac.setSavePwd(false);
		mac.setSenderName("bigTree");
		
		String re = postData(MailUrl.addMailAccount,mac);
		System.out.println(re);
		
		MailAccountDTO ima = PojoMapper.fromJsonAsObject(re, MailAccountDTO.class);
		
		assertEquals(ima.getMailAddress(),"manbuzhiwu@126.com" );
		map.put(MailId, ima.getMailAccountId());
		
	}
	
	/***
	 *  新建联系人分组
	 *  pass
	 */
	@Test
	public void addMailContactGroup(){
		System.out.println("================ 新建联系人分组==================");
		
		MailContactGroupDTO mc = new MailContactGroupDTO();
		mc.setGroupName("高中同学");
		//mc.setMailContactGroupId(mailContactGroupId)
		mc.setUserId(userId35558);
		
		
		String re = postData(MailUrl.addMailContactGroup ,mc);
		System.out.println(re);
		
		 MailContactGroupDTO  remc = PojoMapper.fromJsonAsObject(re,  MailContactGroupDTO.class);
		
		assertEquals(remc.getGroupName(),"高中同学" );
		
		map.put(GroupId, remc.getMailContactGroupId());
		
	}
	
	/***
	 * 增加常用联系人
	 * pass
	 */
	@Test
	public void addMailContact(){
		System.out.println("================增加常用联系人==================");
		
		MailContactDTO mc = new MailContactDTO();
		mc.setMailAddress("461783853@qq.com");
		mc.setMailContactGroupId(map.get(GroupId));
		mc.setName("Bajao");
		mc.setPhone("123456");
		mc.setUserId(userId35558);
		
		String re = postData(MailUrl.addMailContact,mc);
		System.out.println(re);
		
		MailContactDTO  remc = PojoMapper.fromJsonAsObject(re, MailContactDTO.class);
		
		assertEquals(remc.getMailAddress(),"461783853@qq.com" );
		
		map.put(contectId, mc.getMailContactId());
	}
	
	
	/***
	 *   邮箱帐户增加文件和附件
	 *   
	 */
	@Test
	public void addMailFile(){
		System.out.println("================  邮箱帐户增加文件和附件==================");
		
		MailAttach ma = new MailAttach();
		//ma.set
		
		 MailFileDTO mc = new  MailFileDTO();
		 //mc.setNetworkFile();
		// mc.setMailAttach(mailAttach);
		 
		
		String re = postData(MailUrl.addMailFile ,mc);
		System.out.println(re);
		
		 MailFileDTO  remc = PojoMapper.fromJsonAsObject(re,  MailFileDTO.class);
		
		//assertEquals(remc.getMailAddress(),"manbuzhiwu@126.com" );
		
	}
	
	/***
	 * 更新邮件帐户
	 * pass
	 */
	@Test
	public void updateMailAccount(){
		System.out.println("================更新邮件帐户==================");
		
		MailAccountDTO mac = new  MailAccountDTO();
		mac.setMailAddress("manbuzhiwu@126.com");
		mac.setUserId(userId35558);
		mac.setPassword("x4570849");
		mac.setAccountDesc("manbuzhiwu3232 email");
		mac.setMailProtocol("IMAP");
		mac.setSavePwd(false);
		mac.setSenderName("bigTree1");
		
		mac.setMailAccountId(map.get(MailId));
		
		String re = postData(MailUrl.updateMailAccount  ,mac);
		System.out.println(re);
		
		MailAccountDTO  remc = PojoMapper.fromJsonAsObject(re,  MailAccountDTO.class);
		
	}
	
	/***
	 * 修改联系人信息
	 * pass
	 */
	@Test
	public void updateMailContact (){
		System.out.println("================修改联系人信息=================");
		
		MailContactDTO  mail = new  MailContactDTO();
		
		mail.setMailAddress("461783853@qq.com");
		mail.setMailContactGroupId(map.get(GroupId));
		mail.setName("Bajao123");
		mail.setPhone("123456");
		mail.setUserId(userId35558);
		mail.setMailContactId(map.get(contectId));
		
		String re = postData(MailUrl.updateMailContact  ,mail);
		System.out.println(re);
		
		assertEquals(CommConstants.OK_MARK,re);
	}
	
	/***
	 * 修改联系人分组信息
	 * pass
	 */
	@Test
	public void updateMailContactGroup(){
		System.out.println("================修改联系人分组信息==================");
		
		MailContactGroupDTO mc = new  MailContactGroupDTO();
		mc.setGroupName("亲人");
		mc.setMailContactGroupId(map.get(GroupId));
		mc.setUserId(userId35558);
		
		String re = postData(MailUrl.updateMailContactGroup ,mc);
		System.out.println(re);
		
		assertEquals(CommConstants.OK_MARK,re);
	}
	
	

	
	/***
	 *  已存在邮件附件 
	 *  bug-----
	 */
	@Test
	public void existMailAttach() {
		System.out.println("===============已存在邮件附件 ==================");
		
		 MailQueryDTO mc = new  MailQueryDTO();
		 mc.setMailAccountId(249509L);
		 mc.setAttachId(340797L);
		 mc.setMailId(340796L);
		
		String re = postData(MailUrl.existMailAttach ,mc);
		System.out.println(re);
		
	}
	/***
	 *  通过文件名和用户token得到个人网盘中邮件附件的文件列表
	 */
	@Test
	public void getAttachFiles(){
		System.out.println("==============通过文件名和用户token得到个人网盘中邮件附件的文件列表=============");
		
		String fileName="test.pdf";
		
		String f = 	CommonUtil.string2ASCII(fileName);
		
		String re = postData(MailUrl.getAttachFiles,f);
		System.out.println(re);
		if ("error500".equals(re)) {
			fail(re);
		}
		
		
		NetworkFilesDTO  remc = PojoMapper.fromJsonAsObject(re, NetworkFilesDTO.class);
		
		if(remc.getNetworkFileDTOList().size()>0){
		
		assertEquals(remc.getNetworkFileDTOList().get(0).getName(),fileName );
		
		System.out.println("===========测试成功=============");
		}
		
	}
	/***
	 *  返回邮箱账号信息
	 *  bug
	 */
	@Test
	public void getMailAccount(){
		System.out.println("================返回邮箱账号信息==================");
		
		//MailContactDTO mc = new MailContactDTO();
		String mailAccountId = "249509";
		
		String re = postData(MailUrl.getMailAccount ,mailAccountId);
		System.out.println(re);
		
		IMailAccountDTO  remc = PojoMapper.fromJsonAsObject(re, IMailAccountDTO.class);
		
		//assertEquals(remc.getMailAddress(),"manbuzhiwu@126.com" );
		
	}
	/***
	 * 查找用户帐号
	 * pass
	 */
	@Test
	public void getMailAccountsByUserId (){
		System.out.println("================查找用户帐号==================");
		
		String userId = ""+userId35558;
		
		String re = postData(MailUrl.getMailAccountsByUserId ,userId);
		System.out.println(re);
		
		MailAccountsDTO remc = PojoMapper.fromJsonAsObject(re, MailAccountsDTO.class);
		
		
	}
	/***
	 * 获取附件信息
	 * api - bug 
	 * pass
	 */
	@Test
	public void getMailAttachById (){
		System.out.println("================获取附件信息==================");
		
		String attachId ="340797";
		String re = postData(MailUrl.getMailAttachById ,attachId);
		
		System.out.println(re);
		assertNotSame( ErrorType.error500.toString(), re);
		
		 MailAttachDTO  remc = PojoMapper.fromJsonAsObject(re,  MailAttachDTO.class);
		
		assertEquals(remc.getFileName(),"2013-04-05 22.22.14.jpg" );
		
	}
	/***
	 *  获取联系人分组列表
	 *  pass
	 */
	@Test
	public void getMailContactGroups (){
		System.out.println("===============获取联系人分组列表================");
		
		String  userId = ""+userId35558;
		
		String re = postData(MailUrl.getMailContactGroups ,userId);
		System.out.println(re);
		
		assertNotSame( ErrorType.error500.toString(), re);
		
		MailContactGroupsDTO  remc = PojoMapper.fromJsonAsObject(re,  MailContactGroupsDTO.class);
		
		
	}
	
	/***
	 *  获取联系人列表
	 *  (构造 mail_contact,mail_contact_group数据) by huhao
	 */
	@Test
	public void getMailContacts(){
		System.out.println("================ 获取联系人列表==================");
		
		//MailContactDTO mc = new MailContactDTO();
		String userId = ""+"23";
		
		String re = postData(MailUrl.getMailContacts,userId);
		
		System.out.println(re);
		
		if(re!=null){
		assertNotSame( ErrorType.error500.toString(), re);
		
		MailContactsDTO  remc = PojoMapper.fromJsonAsObject(re,  MailContactsDTO.class);
		}
		
		
	}
	/***
	 *  获取邮件文件夹 列表
	 */
	@Test
	public void getMailFolders(){
		System.out.println("================ 获取邮件文件夹列表 ==================");
		
		//MailContactDTO mc = new MailContactDTO();
		String mailAccountId ="249509";
		
		String re = postData(MailUrl.getMailFolders,mailAccountId);
		System.out.println(re);
		
		MailFolderListDTO  remc = PojoMapper.fromJsonAsObject(re, MailFolderListDTO.class);
		
	//	assertEquals(remc.getMailAddress(),"manbuzhiwu@126.com" );
		
	}
	/***
	 * 从数据库中获取邮件信息, 包括附件信息
	 * pass
	 */
	@Test
	public void getMailInfo(){
		System.out.println("========从数据库中获取邮件信息, 包括附件信息============");
		
		//MailContactDTO mc = new MailContactDTO();
		MailQueryDTO mailq = new MailQueryDTO();
		
		mailq.setMailAccountId(249509);
		mailq.setMailId(340796);
		
		String re = postData(MailUrl.getMailInfo,mailq);
		System.out.println(re);
		
		 MailDTO  remc = PojoMapper.fromJsonAsObject(re,  MailDTO.class);
		
		//assertEquals(remc.getMailAddress(),"manbuzhiwu@126.com" );
		
	}
	/***
	 * 获得邮件列表
	 * pass
	 */
	@Test
	public void getMailList(){
		System.out.println("================获得邮件列表==================");
		
		 MailQueryDTO mc = new  MailQueryDTO();
		 mc.setMailAccountId(249509);
		// mc.setMailId(340796);
		
		String re = postData(MailUrl.getMailList ,mc);
		System.out.println(re);
		
		 MailListDTO  remc = PojoMapper.fromJsonAsObject(re, MailListDTO.class);
		
		//assertEquals(remc.getMailAddress(),"manbuzhiwu@126.com" );
		
	}
	/***
	 * 获取最新的邮件文件夹
	 * bug
	 */
	@Test
	public void receiveLatestMailFolder(){
		System.out.println("=============获取最新的邮件文件夹===============");
		
		 MailQueryDTO mc = new  MailQueryDTO();
		 //mc.setMailAccountId(249509);
		 mc.setMailAccountId(1L);
			// mc.setMailId(340796);
		String re = postData(MailUrl.receiveLatestMailFolder,mc);
		System.out.println(re);
		
		if(re!=null){
		 MailFolderDTO  remc = PojoMapper.fromJsonAsObject(re,  MailFolderDTO.class);
		}
		
		//assertEquals(remc.getMailAddress(),"manbuzhiwu@126.com" );
		
	}
	/***
	 *  保存邮件到草稿箱
	 *  ps:(测试数据不足  mail_accout 里要有数据 userId根据实际情况)by huhao
	 */
	@Test
	public void saveMailDraft (){
		System.out.println("=============== 保存邮件到草稿箱 ==================");
		
		
//		MailAccount mac = new MailAccount();
//		
//		mac.setMailAddress("manbuzhiwu@126.com");
//		mac.setUserId(23L);
//		mac.setPassword("x4570849");
//		mac.setAccountDesc("manbuzhiwu email");
//		mac.setMailProtocol("IMAP");
//		mac.setSavePwd(false);
//		mac.setSenderName("bigTree");
//		mac.setAccountDesc("11");
//		mac.setMailAccountId(sequence.getNextId());
//		mac.setReceiverPort("80");
//		mac.setReceiverServer("111");
//		mac.setSenderName("11");
//		mac.setSenderServer("11");
//		mac.setUserSetting(true);
//		
//		
//		mailAccountMapper.addMailAccount(mac);
//		System.out.println("=============== 添加邮件账户成功！ ==================");
		
		MailDTO mail = new MailDTO();
		mail.setSubject("听说你发财了");
		mail.setRecieve("xiao4570849@126.com");
		mail.setContent("你好我是正文,其实这是一封测试邮件，测试发送多个用户的邮件，欢迎您来深圳企业云科技，这里帅男无数，美女没有");
		mail.setSendDate(new Date());
		//mail.setMailAccountId(mac.getMailAccountId());
		
		String re = postData(MailUrl.saveMailDraft,mail);
		System.out.println(re);
		//assertEquals( CommConstants.OK_MARK,re);
		
	}
	/***
	 * 保存邮件文件夹
	 * pass
	 */
	@Test
	public void saveMailFolders(){
		System.out.println("===============保存邮件文件夹==================");
		
		 MailFolderDTO mf = new MailFolderDTO();
		 mf.setMailAccountId(340679);
		 mf.setFullName("test12");
		 mf.setParentFolderUrl("imaps://manbuzhiwu%40126.com@imap.126.com/test123");
		 
		List<MailFolderDTO> mlist = new ArrayList<MailFolderDTO>();
		mlist.add(mf);
		 
		 MailFolderListDTO  mc = new  MailFolderListDTO();
		 mc.setFolders(mlist);
		
		String re = postData(MailUrl.saveMailFolders ,mc);
		System.out.println(re);
		
		 MailFolderListDTO  remc = PojoMapper.fromJsonAsObject(re,  MailFolderListDTO.class);
		
		//assertEquals(remc.getMailAddress(),"manbuzhiwu@126.com" );
		
	}
	/***
	 *  设置邮件已读
	 *  pass
	 *  
	 */
	@Test
	public void setMailRead(){
		System.out.println("=============== 设置邮件已读==================");
		
		String mailId =""+340796;
		String re = postData(MailUrl.setMailRead ,mailId);
		System.out.println(re);
		assertEquals(CommConstants.OK_MARK,re );
		
	}
	

	/***
	 * 删除联系人 
	 */
	@Test
	public void deleteMailContact (){
		System.out.println("================删除联系人 ==================");
		
		//MailContactDTO mc = new MailContactDTO();
		String mailContactId=map.get(contectId)+"";
		
		String re = postData(MailUrl.deleteMailContact ,mailContactId);
		System.out.println(re);
		
		assertEquals( CommConstants.OK_MARK,re );		
	}
	
	/***
	 *   删除联系人分组信息
	 *   (mailContactGroupId =""+groupId+"&"+userId)  by huhao
	 */
	@Test
	public void deleteMailContactGroup(){
		System.out.println("================删除联系人分组信息==================");
		String groupId = "1";
		long userId = 23L;
		//MailContactDTO mc = new MailContactDTO();
		String mailContactGroupId =""+groupId+"&"+userId;
		
		String re = postData(MailUrl.deleteMailContactGroup,mailContactGroupId);
		System.out.println(re);
		
//		MailContactDTO  remc = PojoMapper.fromJsonAsObject(re, MailContactDTO.class);
		
		assertEquals( CommConstants.OK_MARK,re );	
		
	}
	/***
	 *  删除邮件
	 *  (初始化数据 mail_accout ,mail,mail_folder 3张表的数据) by huhao
	 */
	//@Test
	@Ignore
	public void  deleteMails(){
		System.out.println("================ 删除邮件==================");
		
		//MailContactDTO mc = new MailContactDTO();
		MailQueryDTO mailQueryDTO = new MailQueryDTO();
		mailQueryDTO.setMailAccountId(1L);
		List<Long> mailIdsList = new ArrayList<Long>();
		mailIdsList.add(1L);
		mailIdsList.add(2L);
		mailQueryDTO.setMailIds(mailIdsList);
		mailQueryDTO.setFolderId(1L);
		
		String re = postData(MailUrl.deleteMails,mailQueryDTO);
		
		System.out.println(re);
		assertEquals( CommConstants.OK_MARK,re );
		
	}
	

	/***
	 * 删除邮件账号
	 */
	@Test
	public void deleteMailAccount (){
		System.out.println("================删除邮件账号================");
		
		String mailAccountId =""+map.get(MailId);
		
		String re = postData(MailUrl.deleteMailAccount ,mailAccountId);
		System.out.println(re);
		assertEquals( CommConstants.OK_MARK,re );
		
	}

}
