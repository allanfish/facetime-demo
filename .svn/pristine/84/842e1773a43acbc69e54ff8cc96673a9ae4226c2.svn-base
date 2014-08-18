package com.qycloud.oatos.server.test.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.conlect.oatos.dto.client.ConferenceDTO;
import com.conlect.oatos.dto.client.ConferenceDocDTO;
import com.conlect.oatos.dto.client.ConferenceDocsDTO;
import com.conlect.oatos.dto.client.ConferenceMemberDTO;
import com.conlect.oatos.dto.client.ConferenceMembersDTO;
import com.conlect.oatos.dto.client.ConferencesDTO;
import com.conlect.oatos.dto.client.CreateConferenceDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;

public class ConferenceServiceTest extends BaseServiceTest {
	
	private static Long ENT_ID = 1234L; 
	private static Long userId3885 =249509L;
	private static String CONID ="conId";
	private static String CONDOC="condocId";
	
	private static Map<String,Long> map = new HashMap<String,Long>();
	
	/**
	 * 创建会议
	 * pass
	 */
	@Test
	public void createConference(){
		System.out.println("================创建会议==============");
		CreateConferenceDTO  cc = new CreateConferenceDTO();
		cc.setCreaterName("开个会啊");
		cc.setCreaterId(userId3885);
		cc.setEnterpriseId(ENT_ID);
		cc.setTheme("kaihui");
		cc.setType(0);
		cc.setStartTime(new Date());
		cc.setStatus("new");
		
		String re  = postData(RESTurl.createConference ,cc);
		System.out.println("结果  = "+re);
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		assertNotNull(re);
		
		map.put(CONID,Long.parseLong(re) );
		
		
		
		System.out.println("============修改会议==================");
		
		
		cc.setId(Long.parseLong(re));
		
		cc.setTheme("生活多多乐");
		
		String re1  = postData(RESTurl.updateConference ,cc);
		System.out.println("结果 = "+re1);
		
		if( ErrorType.error500.toString().equals(re1)){
			fail(re1);
		}
		assertEquals( CommConstants.OK_MARK, re1);
		
//		System.out.println("============修改会议状态==================");
//		
//		cc.setStatus("held");
//		String re2  = postData(RESTurl.updateConferenceStatus ,cc);
//		
//		System.out.println("结果 = "+re2);
//		
//		if( ErrorType.error500.toString().equals(re2)){
//			fail(re2);
//		}
//		ConferenceDocDTO sfaf = PojoMapper.fromJsonAsObject(re2,  ConferenceDocDTO.class);
//		assertNotNull(sfaf);
		
	}
	
	/***
	 *    修改会议状态
	 *    pass
	 */
	@Test
	public void updateConferenceStatus(){
		System.out.println("============修改会议状态==================");
		
		ConferenceDTO cf = new  ConferenceDTO();
		cf.setCreaterName("开个会啊");
		cf.setCreaterId(userId3885);
		cf.setEnterpriseId(ENT_ID);
		cf.setTheme("kaihui");
		cf.setType(0);
		cf.setStartTime(new Date());
		cf.setStatus("ended");
		cf.setId(map.get(CONID));
		
		String re  = postData(RESTurl.updateConferenceStatus ,cf);
		
		System.out.println("结果 = "+re);
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		//ConferenceDTO sfaf = PojoMapper.fromJsonAsObject(re,  ConferenceDTO.class);
		//assertNotNull(sfaf);
		assertEquals( CommConstants.OK_MARK, re);
	}
	
	
	/***
	 *   添加会议文档中心文件
	 */
	@Test
	public void addConferenceDoc(){
		System.out.println("============添加会议文档中心文件==================");
		
		ConferenceDocDTO cf = new ConferenceDocDTO();
		cf.setDiskFileId(308422L);
		cf.setEnterpriseId(ENT_ID);
		cf.setGuid("443db64a-4bfa-4bbc-98ba-0416ab8df4b5.html");
		cf.setName("文档11112.oatw");
		cf.setSize(1);
		cf.setUserId(249509L);
		cf.setType("oatw");
		cf.setConferenceId(map.get(CONID));
		 
		String re  = postData(RESTurl.addConferenceDoc ,cf);
		System.out.println("结果 = "+re);
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		//assertEquals( CommConstants.OK_MARK, re);
		ConferenceDocDTO sfaf = PojoMapper.fromJsonAsObject(re,ConferenceDocDTO.class);
		assertNotNull(sfaf);
		assertEquals(sfaf.getEnterpriseId(), ENT_ID);
		map.put(CONDOC, sfaf.getFileId());
		
		
		System.out.println("============批量添加会议文档中心文件==================");
		List<ConferenceDocDTO> cdd = new ArrayList<ConferenceDocDTO>();
			cdd.add(cf);
		ConferenceDocsDTO cfs = new  ConferenceDocsDTO();
			cfs.setDocList(cdd);
		
		String re1  = postData(RESTurl.addConferenceDocs,cfs);
		
		System.out.println("结果 = "+re1);
		
		if( ErrorType.error500.toString().equals(re1)){
			fail(re1);
		}
		//assertEquals( CommConstants.OK_MARK, re);
		ConferenceDocsDTO sfafse = PojoMapper.fromJsonAsObject(re1,   ConferenceDocsDTO.class);
		assertNotNull(sfafse);
		
		
		
	}
//	/***
//	 *   批量添加会议文档中心文件
//	 */
//	@Test
//	public void addConferenceDocs (){
//		System.out.println("============批量添加会议文档中心文件==================");
//		
//		ConferenceDocsDTO cfs = new  ConferenceDocsDTO();
//		
//		String re  = postData(RESTurl.addConferenceDocs,cfs);
//		
//		System.out.println("结果 = "+re);
//		
//		if( ErrorType.error500.toString().equals(re)){
//			fail(re);
//		}
//		//assertEquals( CommConstants.OK_MARK, re);
//		 ConferenceDocsDTO sfaf = PojoMapper.fromJsonAsObject(re,   ConferenceDocsDTO.class);
//		assertNotNull(sfaf);
//	}
	/***
	 *    添加会议成员
	 *    bug-1 - api
	 */
	@Test
	public void addConferenceMembers(){
		System.out.println("============ 添加会议成员==================");
		
		ConferenceMemberDTO con  = new ConferenceMemberDTO();
			con.setConferenceId(map.get(CONID));
			con.setInviteTime(new Date());
			con.setInviteUserId(1244L);
			con.setInviteUserName("001");
			con.setStatus("accepted");
		//	con.setUserIcon(userIcon);
			con.setUserId(userId3885);
			con.setUserName("asd");
		
		List<ConferenceMemberDTO> conList =new ArrayList<ConferenceMemberDTO>();
			conList.add(con);
		
		ConferenceMembersDTO cf = new ConferenceMembersDTO();
			cf.setMembers(conList);
		
		String re  = postData(RESTurl.addConferenceMembers,cf);
		System.out.println("结果 = "+re);
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		assertEquals( CommConstants.OK_MARK, re);
//		ConferenceDocDTO sfaf = PojoMapper.fromJsonAsObject(re,  ConferenceDocDTO.class);
//		assertNotNull(sfaf);
	}
	
	
	/***
	 *   修改会议成员状态
	 *   pass
	 */
	@Test
	public void updateConferenceMember(){
		System.out.println("============修改会议成员状态==================");
		
		 ConferenceMemberDTO cf = new  ConferenceMemberDTO();
			cf.setConferenceId(map.get(CONID));
			cf.setInviteTime(new Date());
			cf.setInviteUserId(1244L);
			cf.setInviteUserName("001");
			cf.setStatus("refused");
		//	con.setUserIcon(userIcon);
			cf.setUserId(userId3885);
			cf.setUserName("asd");
		String re  = postData(RESTurl.updateConferenceMember ,cf);
		System.out.println("结果 = "+re);
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		assertEquals( CommConstants.OK_MARK, re);
	}
	
	

	/***
	 *   判断会议文档文件是否存在,及网盘空间
	 */
	@Test
	public void checkConferenceDocUpload(){
		System.out.println("==========判断会议文档文件是否存在,及网盘空间============");
		
		ConferenceDocDTO cf = new ConferenceDocDTO();

		cf.setDiskFileId(308422L);
		cf.setEnterpriseId(ENT_ID);
		cf.setGuid("443db64a-4bfa-4bbc-98ba-0416ab8df4b5.html");
		cf.setName("文档11112.oatw");
		cf.setSize(1);
		cf.setUserId(249509L);
		cf.setType("oatw");
		cf.setConferenceId(308168L);
		cf.setFileId(map.get(CONDOC));
		
		String re  = postData(RESTurl.checkConferenceDocUpload ,cf);
		System.out.println("结果 = "+re);
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		
//		assertEquals( CommConstants.OK_MARK, re);
		assertEquals( ErrorType.errorSameFile.toString(), re);
		//ConferenceDocDTO sfaf = PojoMapper.fromJsonAsObject(re,  ConferenceDocDTO.class);
		//assertNotNull(sfaf);
	}
	/***
	 *    取会议信息包括参会者
	 */
	@Test
	public void getConferenceById (){
		System.out.println("============ 取会议信息包括参会者==================");
		
		//ConferenceDocDTO cf = new ConferenceDocDTO();
		String conferenceId = ""+map.get(CONID);
		String re  = postData(RESTurl.getConferenceById  ,conferenceId);
		System.out.println("结果 = "+re);
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		//assertEquals( CommConstants.OK_MARK, re);
		 ConferenceDTO sfaf = PojoMapper.fromJsonAsObject(re,   ConferenceDTO.class);
		assertNotNull(sfaf);
	}
	
	/***
	 *   取会议列表包括参会者
	 */
	@Test
	public void getConferenceByUserId (){
		System.out.println("============取会议列表包括参会者==================");
		
	//	ConferenceDocDTO cf = new ConferenceDocDTO();
		String userId =""+userId3885;
		
		String re  = postData(RESTurl.getConferenceByUserId  ,userId);
		System.out.println("结果 = "+re);
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		//assertEquals( CommConstants.OK_MARK, re);
		ConferencesDTO sfaf = PojoMapper.fromJsonAsObject(re,  ConferencesDTO.class);
		assertNotNull(sfaf);
	}
	/***
	 *   按文件id取单个视频会议文档信息
	 */
	@Test
	public void getConferenceDocById(){
		System.out.println("============按文件id取单个视频会议文档信息==================");
		
		//ConferenceDocDTO cf = new ConferenceDocDTO();
		String fileId = ""+map.get(CONDOC);
		String re  = postData(RESTurl.getConferenceDocById ,fileId);
		System.out.println("结果 = "+re);
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		//assertEquals( CommConstants.OK_MARK, re);
		ConferenceDocDTO sfaf = PojoMapper.fromJsonAsObject(re,  ConferenceDocDTO.class);
		assertNotNull(sfaf);
	}
	
	/***
	 *   获取会议文档中心文件
	 *   bug
	 */
	@Test
	public void getConferenceDocsByConId (){
		System.out.println("============添加会议文档中心文件==================");
		
		//ConferenceDocDTO cf = new getConferenceDocsByConId ();
		String conId =map.get(CONID)+"";
		
		String re  = postData(RESTurl.addConferenceDoc ,conId);
		System.out.println("结果 = "+re);
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		//assertEquals( CommConstants.OK_MARK, re);
		ConferenceDocsDTO sfaf = PojoMapper.fromJsonAsObject(re,  ConferenceDocsDTO.class);
		assertNotNull(sfaf);
	}
	
	
	
	@Test
	public void deleteConferenceDocs (){
		
		ConferenceDocDTO cf = new ConferenceDocDTO();
		cf.setDiskFileId(308422L);
		cf.setEnterpriseId(ENT_ID);
		cf.setGuid("443db64a-4bfa-4bbc-98ba-0416ab8df4b5.html");
		cf.setName("文档11112.oatw");
		cf.setSize(1);
		cf.setUserId(249509L);
		cf.setType("oatw");
		cf.setConferenceId(map.get(CONID));
		
		List<ConferenceDocDTO> cdd = new ArrayList<ConferenceDocDTO>();
		cdd.add(cf);
		ConferenceDocsDTO cfs = new  ConferenceDocsDTO();
		cfs.setDocList(cdd);
		
		System.out.println("============批量  删除视频会议文档==================");
		String re2  = postData(RESTurl.deleteConferenceDocs,cfs);
		
		System.out.println("结果 = "+re2);
		
		if( ErrorType.error500.toString().equals(re2)){
			fail(re2);
		}
	}
	
	
	
	
	@Test
	public void deleteConference(){
		
		ConferenceDTO cf = new  ConferenceDTO();
		
		cf.setCreaterName("开个会啊");
		cf.setCreaterId(userId3885);
		cf.setEnterpriseId(ENT_ID);
		cf.setTheme("kaihui");
		cf.setType(0);
		cf.setStartTime(new Date());
		cf.setStatus("ended");
		//cf.setId(map.get(CONID));
		cf.setId(373691L);
		
		List<ConferenceDTO> cfList =new ArrayList<ConferenceDTO>();
			cfList.add(cf);
		ConferencesDTO  cs =new ConferencesDTO();
			cs.setConferenceList(cfList);
		
		System.out.println("============删除会议==================");
		String re2  = postData(RESTurl.deleteConference,cfList);
		System.out.println(re2);
		if( ErrorType.error500.toString().equals(re2)){
			fail(re2);
		}
		assertEquals(CommConstants.OK_MARK, re2);
		
	}
	
}
