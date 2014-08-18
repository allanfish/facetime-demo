package com.qycloud.oatos.server.test.service;

import org.junit.Test;

import static org.junit.Assert.*;

import com.conlect.oatos.dto.client.ShareLinkDTO;
import com.conlect.oatos.dto.client.ShareLinkInfoDTO;
import com.conlect.oatos.dto.client.ShareLinkMailDTO;
import com.conlect.oatos.dto.client.ShareLinksDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;

public class ShareLinkServiceTest extends BaseServiceTest{
	
	
	@Test
	public void createShareLink(){
		System.out.println("==============创建共享链接=============");
		//参数
		//String linkId = "";
		ShareLinkDTO share = new ShareLinkDTO();
		
		share.setFileId(2517L);
		share.setEnterpriseId(1234L);
		share.setMaxDownload(10000L);
		share.setCreateUserId(249524L);
		share.setCreateUserName("testemp3306");
		
		//发送
		String re = postData(RESTurl.createShareLink,share);
		System.out.println("返回值："+re);
		//验证
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		//assertEquals( CommConstants.OK_MARK, re);
		ShareLinkDTO  sl = PojoMapper.fromJsonAsObject(re, ShareLinkDTO.class);
		
		System.out.println("==============更新共享链接=============");
		//参数
		//String entId = "";
		//sl.setLinkId(261855);
		//sl.setLinkCode("e5357528c93d481ca839055a9b9f0d60");
		sl.setMaxDownload(5000L);
		sl.setDownloadCount(5L);
		//发送
		String re1 = postData(RESTurl.updateShareLink  ,sl);
		System.out.println("返回值："+re1);
		//验证
		
		if( ErrorType.error500.toString().equals(re1)){
			fail(re1);
		}
		assertEquals( CommConstants.OK_MARK, re1);
		
		
		System.out.println("==============删除共享链接=============");
		//参数
		String linkId =sl.getLinkId()+"";
		System.out.println("linkId:"+linkId);
		//发送
		String re2 = postData(RESTurl.deleteShareLink,linkId);
		System.out.println("返回值："+re2);
		//验证
		
		if( ErrorType.error500.toString().equals(re2)){
			fail(re2);
		}
		assertEquals( CommConstants.OK_MARK, re2);
	}
	
	
	
	/***
	 * 验证共享链接是否可以下载
	 */
	@Test
	public void checkShareLinkDownload(){
		
		System.out.println("==============验证共享链接是否可以下载=============");
		//参数
		String linkId = "62002";
		//发送
		String re3 = postData(RESTurl.checkShareLinkDownload,linkId);
		System.out.println("返回值re3："+re3);
		//验证
		
		if( ErrorType.error500.toString().equals(re3)){
			fail(re3);
		}
		assertEquals( CommConstants.OK_MARK, re3);
		
		
	} 
	/***
	 * 删除共享链接
	 */
	@Test
	public void deleteShareLink(){
		
		
	} 
	
	/***
	 * 更新共享链接
	 */
	@Test
	public void updateShareLink  (){
		
		
//		ShareLinkInfoDTO  sl = PojoMapper.fromJsonAsObject(re, ShareLinkInfoDTO.class);
		
	} 
	/***
	 *  更新共享链接下载次数
	 *  bug
	 */
	@Test
	public void updateShareLinkDownCount (){
		System.out.println("============== 更新共享链接下载次数=============");
		//参数
		String linkId ="248513";
		long size = 120;
		String param = linkId + ":" + size;
		//发送
		String re2 = postData(RESTurl.updateShareLinkDownCount ,param);
		System.out.println("返回值re2："+re2);
		//验证
		
		if( ErrorType.error500.toString().equals(re2)){
			fail(re2);
		}
		assertEquals( CommConstants.OK_MARK, re2);
		
		//ShareLinkInfoDTO  sl = PojoMapper.fromJsonAsObject(re, ShareLinkInfoDTO.class);
		
	} 
	
	
	
	/***
	 * 获取共享链接
	 */
	@Test
	public void getExistShareLink(){
		
		System.out.println("==============获取共享链接=============");
		//参数
		//String linkId = "";
		
		ShareLinkDTO sldto = new ShareLinkDTO();
		sldto.setCreateUserId(3318L);
		sldto.setCreateUserName("Admin");
		sldto.setFileId(61871L);
		//sldto.setFolderId(folderId)
		sldto.setEnterpriseId(3317L);
		sldto.setLinkId(62010L);
		sldto.setLinkCode("7d758bf972ba4db88e1cd4c060a98ef5");
		//发送
		String re = postData(RESTurl.getExistShareLink,sldto);
		System.out.println("返回值："+re);
		//验证
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		//assertEquals( CommConstants.OK_MARK, re);
		
		ShareLinkInfoDTO  sl = PojoMapper.fromJsonAsObject(re, ShareLinkInfoDTO.class);
		
	} 
	/***
	 * 获取共享链接通过linkCode
	 */
	@Test
	public void getShareLinkByCode (){
		
		System.out.println("==============获取共享链接通过linkCode=============");
		//参数
		String linkCode = "8a8f76cfb9c447708ebac6ffd48b1c86";
		//发送
		String re4 = postData(RESTurl.getShareLinkByCode ,linkCode);
		System.out.println("返回值re4："+re4);
		//验证
		
		if( ErrorType.error500.toString().equals(re4)){
			fail(re4);
		}
	//	assertEquals( CommConstants.OK_MARK, re);
		ShareLinkInfoDTO  sli = PojoMapper.fromJsonAsObject(re4, ShareLinkInfoDTO.class);
		
	} 
	
	/***
	 * 获取共享链接通过企业Id
	 */
	@Test
	public void getShareLinkByEntId (){
		
		System.out.println("==============获取共享链接通过企业Id=============");
		//参数
		//String entId = "";
		//发送
		String re5 = postData(RESTurl.getShareLinkByEntId ,1808+"");
		System.out.println("返回值re5："+re5);
		//验证
		
		if( ErrorType.error500.toString().equals(re5)){
			fail(re5);
		}
		//assertEquals( CommConstants.OK_MARK, re);
		ShareLinksDTO  sli2 = PojoMapper.fromJsonAsObject(re5, ShareLinksDTO.class);
		
	} 
	/***
	 * 获取共享链接通过linkId   
	 * 
	 */
	@Test
	public void getShareLinkByLinkId (){
		
		System.out.println("==============获取共享链接通过linkId=============");
		//参数
		String linkId = "62002";
		//发送
		String re = postData(RESTurl.getShareLinkByLinkId ,linkId);
		System.out.println("返回值："+re);
		//验证
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		//assertEquals( CommConstants.OK_MARK, re);
		ShareLinkDTO  sl = PojoMapper.fromJsonAsObject(re, ShareLinkDTO.class);
		
	} 
	/***
	 *  发送共享链接
	 *  pass
	 */
	@Test
	public void sendShareLinkMail  (){
		
		System.out.println("============== 发送共享链接=============");
		
		//参数
		//String entId = "";
		 ShareLinkMailDTO slm = new  ShareLinkMailDTO();
		 slm.setLocale("zh_cn");
		 slm.setNet(true);
		 slm.setEnterpriseName("");
		 slm.setSender("");
		 slm.setLinkUrl("http://baidu.com");
		 slm.setContent("来自oatos的一封邮件");
		 
		 
		//发送
		String re = postData(RESTurl.sendShareLinkMail  ,slm);
		System.out.println("返回值："+re);
		//验证
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		assertEquals( CommConstants.OK_MARK, re);
		//ShareLinkInfoDTO  sl = PojoMapper.fromJsonAsObject(re, ShareLinkInfoDTO.class);
		
	} 
	
	
}
