/**
 * 
 */
package com.qycloud.oatos.server.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.conlect.oatos.dto.client.FolderAndFileParamDTO;
import com.conlect.oatos.dto.client.ShareFileDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;
import com.conlect.oatos.dto.client.ShareFileRecordDTO;
import com.conlect.oatos.dto.client.ShareFileRecordsDTO;
import com.conlect.oatos.dto.client.ShareFileUpdateDTO;
import com.conlect.oatos.dto.client.ShareFilesDTO;
import com.conlect.oatos.dto.client.ShareFolderAndFileDTO;
import com.conlect.oatos.dto.client.ShareFolderAndFileUpdateDTO;
import com.conlect.oatos.dto.client.ShareFolderDTO;
import com.conlect.oatos.dto.client.ShareFolderUpdateDTO;
import com.conlect.oatos.dto.client.ShareFoldersDTO;
import com.conlect.oatos.dto.client.ShareHistoryDTO;
import com.conlect.oatos.dto.client.ShareLinkFilesDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;

/**
 * @author xiao.min
 *
 */
public class ShareDiskServiceTest extends BaseServiceTest {
	
	private static Long userId3885 =249509L;
	private static Long entId = 1234L;
	private static Long New_folder =1346L;
	private static Long New_folder1 =1347L;
	private static Long ligao =263081L;
	private static Long saas =262812L;

	/***
	 *  添加企业网盘文件至数据库
	 *  pass
	 */
	@Test
	public void addShareFile(){
		
		System.out.println("============== 添加企业网盘文件至数据库=============");
		//参数
		//String linkId = "";
		 ShareFileUpdateDTO sfu = new  ShareFileUpdateDTO();
		 sfu.setEnterpriseId(1234);
		 sfu.setFolderId(1348L);
		 sfu.setGuid("b887964d-d5a4-49a8-ba13-6605ad6bb320.xls");
		// sfu.setLockByUser(lockByUser);
		// sfu.setLockByUserId(lockByUserId);
		 sfu.setName("ak47.p2p.com");
		 sfu.setVersion(3);
		 sfu.setUserName("testemp3885");
		 sfu.setUserId(249509);
		 sfu.setType("pdf");
		 sfu.setThumb("xxxx");
		 sfu.setSize(20);
		// sfu.setShareLinkId(shareLinkId);
		// sfu.setRestoreUserId(restoreUserId);
		 //sfu.setRemark();
		 sfu.setPageCount(10);
		//发送
		String re = postData(RESTurl.addShareFile,sfu);
		System.out.println("返回值："+re);
		//验证
		if (ErrorType.error500.name().equals(re)) { fail(); }
	} 
	/***
	 *  检查当前企业网盘使用空间是否超出免费空间 
	 *  pass
	 */
	@Test
	public void checkEntDiskSizeExceed(){
		System.out.println("============ 检查当前企业网盘使用空间是否超出免费空间 ==================");
		String entId = "1234";
		String re  = postData(RESTurl.checkEntDiskSizeExceed,entId);
		System.out.println("结果 = "+re);
		
		if (ErrorType.error500.name().equals(re)) { fail(); }
	}
	
	/***
	 *  企业网盘文件同步上传之前，检查文件版本，网盘空间，权限，同名文件  
	 *  pass
	 */
	@Test
	public void checkShareFileSyncUpload (){
		System.out.println("============ 企业网盘文件同步上传之前，检查文件版本，网盘空间，权限，同名文件 ==================");
		//String entId = "";
		
		ShareFileUpdateDTO sfu  = new  ShareFileUpdateDTO();
		 sfu.setEnterpriseId(1234);
		 sfu.setFolderId(1348L);
		 sfu.setGuid("b887964d-d5a4-49a8-ba13-6605ad6bb320.xls");
		 sfu.setName("ak47.p2p.com");
		 sfu.setVersion(3);
		 sfu.setUserName("testemp3885");
		 sfu.setUserId(249509);
		 sfu.setType("pdf");
		 sfu.setThumb("xxxx");
		 sfu.setSize(20);
		 sfu.setPageCount(10);
		
		String re  = postData(RESTurl.checkShareFileSyncUpload ,sfu);
		System.out.println("结果 = "+re);
		
		if (ErrorType.error500.name().equals(re)) { fail(); }
	}
	
	/***
	 *  企业网盘文件上传前，判断企业网盘文件是否重名，上传权限，网盘空间及文件夹空间
	 *  pass
	 */
	@Test
	public void checkShareFileUpload(){
		System.out.println("============ 企业网盘文件上传前，判断企业网盘文件是否重名，上传权限，网盘空间及文件夹空间 ==================");
		//String entId = "";
		ShareFileUpdateDTO sfu  = new  ShareFileUpdateDTO();
		 sfu.setEnterpriseId(1234);
		 sfu.setFolderId(1348L);
		 sfu.setGuid("b887964d-d5a4-49a8-ba13-6605ad6bb320.xls");
		 sfu.setName("ak47.p2p.com");
		 sfu.setVersion(3);
		 sfu.setUserName("testemp3885");
		 sfu.setUserId(249509);
		 sfu.setType("pdf");
		 sfu.setThumb("xxxx");
		 sfu.setSize(20);
		 sfu.setPageCount(10);
		
		String re  = postData(RESTurl.checkShareFileUpload ,sfu);
		System.out.println("结果 = "+re);
		
		if (ErrorType.error500.name().equals(re)) { fail(); }
	}
	
	/***
	 *  删除企业网盘文件至回收站 
	 *  bug --可能是哪个参数为必须，没设置
	 */
	@Test
	public void deleteShareFile(){
		System.out.println("============  删除企业网盘文件至回收站 ==================");
	//	String entId = "";
		ShareFileUpdateDTO sfu = new ShareFileUpdateDTO();
		 sfu.setFileId(262937L);
		 sfu.setEnterpriseId(1234);
		 sfu.setFolderId(1347L);
		 sfu.setGuid("a37d95d5-901e-484f-99cf-7bf8a9dae74a.jpg");
		 sfu.setName("花2.jpg");
		 sfu.setUserName("testemp3885");
		 sfu.setUserId(249509);
		 sfu.setVersion(11L);
		 sfu.setSize(1220);
		
		String re  = postData(RESTurl.deleteShareFile,sfu);
		System.out.println("结果 = "+re);
		
		if (ErrorType.error500.name().equals(re)) { fail(); }
	}
	
	/***
	 *  删除企业网盘目录至回收站 
	 *   bug --可能是哪个参数为必须，没设置
	 */
	@Test
	public void deleteShareFolder (){
		System.out.println("============ 删除企业网盘目录至回收站 ==================");
		//String entId = "";
		ShareFolderUpdateDTO  sfu = new ShareFolderUpdateDTO();
		sfu.setEnterpriseId(1234);
		sfu.setName("人生最美");
		sfu.setParentId(1346L);
		sfu.setFolderId(263038);
		sfu.setVersion(14L);
		sfu.setUserId(249509);
		
		
		String re  = postData(RESTurl.deleteShareFolder ,sfu);
		System.out.println("结果 = "+re);
		
		if (ErrorType.error500.name().equals(re)) { fail(); }
	}
	
	/***
	 *   删除企业网盘回收站文件夹和文件 
	 *    pass
	 */
	@Test
	public void deleteShareFolderAndFiles(){
		System.out.println("============删除企业网盘回收站文件夹和文件 ==================");
		//String entId = "";
		 ShareFolderAndFileUpdateDTO sffu = new  ShareFolderAndFileUpdateDTO();
		 ShareFileDTO sfd = new ShareFileDTO();
		 sfd.setFileId(263073L);
		 sfd.setFolderId(263038L);
		 sfd.setName("lihua.jpg");
		 sfd.setGuid("dcb14607-e1d3-41e5-98e9-89877d2c680c.jpg");
		 sfd.setVersion(17L);
		 sfd.setUserId(249509);
		 sfd.setCreateTime(new Date());
		 sfd.setUpdateTime(new Date());
		 List<ShareFileDTO> sflist = new ArrayList<ShareFileDTO>();
		 sflist.add(sfd);
		sffu.setFileList(sflist);
		
		String re  = postData(RESTurl.deleteShareFolderAndFiles,sffu);
		System.out.println("结果 = "+re);
		
		if (ErrorType.error500.name().equals(re)) { fail(); }
	}
	
	/***
	 *   按企业取企业网盘下文件列表，包括回收站中文件
	 *   pass
	 */
	@Test
	public void getAllShareFilesByEntId(){
		System.out.println("============  按企业取企业网盘下文件列表，包括回收站中文件 ==================");
		String entId = "1234";
		String re  = postData(RESTurl.getAllShareFilesByEntId,entId);
		System.out.println("结果 = "+re);
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		//assertEquals( CommConstants.OK_MARK, re);
		ShareFilesDTO sfdto = PojoMapper.fromJsonAsObject(re, ShareFilesDTO.class);
		assertNotNull(sfdto);
	}
	
	/***
	 *  按用户取企业网盘下有可见权限的文件夹列表和文件列表，包括回收站中文件夹和文件 
	 */
	@Test
	public void getAllShareFolderAndFileByUserId (){
		System.out.println("============按用户取企业网盘下有可见权限的文件夹列表和文件列表，包括回收站中文件夹和文件==================");
		String userId = "249509";
		String re  = postData(RESTurl.getAllShareFolderAndFileByUserId ,userId);
		System.out.println("结果 = "+re);
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		//assertEquals( CommConstants.OK_MARK, re);
		ShareFolderAndFileDTO sfaf = PojoMapper.fromJsonAsObject(re, ShareFolderAndFileDTO.class);
		assertNotNull(sfaf);
	}
	
	/***
	 *  按企业取企业网盘下文件夹列表，包括回收站中文件夹
	 */
	@Test
	public void getAllShareFoldersByEntId(){
		System.out.println("============ 按企业取企业网盘下文件夹列表，包括回收站中文件夹 ==================");
		String entId = "1234";
		String re  = postData(RESTurl.getAllShareFoldersByEntId,entId);
		System.out.println("结果 = "+re);
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		ShareFoldersDTO sfs = PojoMapper.fromJsonAsObject(re, ShareFoldersDTO.class);
		assertNotNull(sfs);
		assertEquals(sfs.getShareFolderList().get(0).getFolderId()+"", "1346");
	}
	
	/***
	 *  按用户取企业网盘下有可见权限的文件列表，包括回收站中文件 
	 */
	@Test
	public void getAllShareFilesByUserId  (){
		System.out.println("============ 按用户取企业网盘下有可见权限的文件列表，包括回收站中文件==================");
		String userId = "249509";
		String re  = postData(RESTurl.getAllShareFilesByUserId  ,userId);
		System.out.println("结果 = "+re);
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		ShareFilesDTO sharef = PojoMapper.fromJsonAsObject(re, ShareFilesDTO.class);
		assertNotNull(sharef);
		assertEquals(sharef.getShareFileList().get(0).getFileId()+"", "262812");
		
		//System.out.println(sharef.getUserId()+"--"+sharef.getUsedSize()+"****"+sharef.getShareFileList().size());
	}
	/***
	 *   按文件夹取企业网盘子文件夹和文件，取顶层文件夹时，文件夹id传null
	 */
	@Test
	public void getEntFolderAndFileByFolderId (){
		System.out.println("============按文件夹取企业网盘子文件夹和文件，取顶层文件夹时，文件夹id传null==================");
		//String folderId = "1346";
		List<Long> llist = new ArrayList<Long>();
		llist.add(1347L);
		llist.add(1348L);
		
		FolderAndFileParamDTO ffp = new FolderAndFileParamDTO();
		ffp.setUserId(249509L);
		ffp.setFolderIds(llist);
	
		
		String re  = postData(RESTurl.getEntFolderAndFileByFolderId  ,ffp);
		System.out.println("结果 = "+re);
		ShareFolderAndFileDTO folderAndFileDTO = PojoMapper.fromJsonAsObject(re, ShareFolderAndFileDTO.class);
		assertNotNull(folderAndFileDTO);
	}
	
	/***
	 *   取企业网盘回收站中文件夹和文件
	 */
	@Test
	public void getEntFolderAndFileInRecycle (){
		System.out.println("============取企业网盘回收站中文件夹和文件==================");
		String entId = "1234";
		String re  = postData(RESTurl.getEntFolderAndFileInRecycle  ,entId);
		System.out.println("结果 = "+re);

		ShareFolderAndFileDTO sfaf = PojoMapper.fromJsonAsObject(re, ShareFolderAndFileDTO.class);
		assertNotNull(sfaf);
	}
	//---------------------------------------------pass-------------------------------------------------
	//-===----------------------------------------------------------------------------------------------
	/***
	 *    取企业网盘文件访问记录，包括下载和浏览记录
	 *    bug 转化错误
	 */
	@Test
	public void getFileAccessRecord(){
		System.out.println("============ 取企业网盘文件访问记录，包括下载和浏览记录==================");
		//String entId = "1234";
		 ShareFileRecordDTO sfr = new  ShareFileRecordDTO();
		 sfr.setUserId(userId3885);
		 sfr.setRecordType("fa");
		String re  = postData(RESTurl.getFileAccessRecord,sfr);
		System.out.println("结果 = "+re);

		 ShareFileRecordsDTO  sfaf = PojoMapper.fromJsonAsObject(re,   ShareFileRecordsDTO.class);
		 assertNotNull(sfaf);
	}
	/***
	 *    取企网盘已使用空业间
	 *    pass
	 */
	@Test
	public void getShareDiskUsedSizeByEntId (){
		System.out.println("============ 取企业网盘已使用空间==================");
		//String entId = entId;
		String re  = postData(RESTurl.getShareDiskUsedSizeByEntId  ,entId+"");
		System.out.println("结果 = "+Long.parseLong(re));
		
		if (ErrorType.error500.name().equals(re)) { fail(); }
	}
	/***
	 *    取企业网盘当前版本号
	 *    pass
	 */
	@Test
	public void getShareDiskVersion  (){
		System.out.println("============ 取企业网盘当前版本号==================");
		String entId = "1234";
		String re  = postData(RESTurl.getShareDiskVersion   ,entId);
		System.out.println("结果 = "+re);
		
		if (ErrorType.error500.name().equals(re)) { fail(); }
	}
	/***
	 *   按文件id取单个企业网盘文件信息
	 */
	@Test
	public void getShareFileById(){
		System.out.println("============按文件id取单个企业网盘文件信息=================");
		String fileId = ligao+"";
		String re  = postData(RESTurl.getShareFileById   ,fileId);
		System.out.println("结果 = "+re);

		 ShareFileDTO sfaf = PojoMapper.fromJsonAsObject(re,  ShareFileDTO.class);
		 assertNotNull(sfaf);
	}
	
	/***
	 *    按文件取企业网盘文件历史修改记录
	 *    bug -必须参数没设置？？
	 */
	@Test
	public void getShareFileHistory  (){
		System.out.println("============ 按文件取企业网盘文件历史修改记录==================");
		 ShareFileUpdateDTO sfu = new  ShareFileUpdateDTO();
		 sfu.setFileId(saas);
		 sfu.setFolderId(New_folder);
		 sfu.setEnterpriseId(entId);
		 sfu.setGuid("be8d686f-42a1-4c1d-a309-16bdff553de0.png");
		
		//String entId = "1234";
		String re  = postData(RESTurl.getShareFileHistory  ,sfu);
		System.out.println("结果 = "+re);

		 ShareHistoryDTO sfaf = PojoMapper.fromJsonAsObject(re,  ShareHistoryDTO.class);
		 assertNotNull(sfaf);
	}
	/***
	 *    按企业ID取企业网盘下取文件列表，不包括回收站中文件
	 *    pass
	 */
	@Test
	public void getShareFilesByEntId(){
		System.out.println("============ 按企业ID取企业网盘下取文件列表，不包括回收站中文件==================");
		String entId = "1234";
		String re  = postData(RESTurl.getShareFilesByEntId   ,entId);
		System.out.println("结果 = "+re);

		 ShareFilesDTO sfaf = PojoMapper.fromJsonAsObject(re,  ShareFilesDTO.class);
		 assertNotNull(sfaf);
		 
	}
	/***
	 * 按用户取取企业网盘下有可见权限的文件列表，不包括回收站的文件
	 */
	@Test
	public void getShareFilesByUserId(){
		System.out.println("============按用户取取企业网盘下有可见权限的文件列表，不包括回收站的文件==================");
		String userId = userId3885+"";
		String re  = postData(RESTurl.getShareFilesByUserId   ,userId);
		System.out.println("结果 = "+re);

		 ShareFilesDTO sfaf = PojoMapper.fromJsonAsObject(re,  ShareFilesDTO.class);
		 assertNotNull(sfaf);
	}
	
	/***
	 * 按企业取取企业网盘下文件夹列表和文件列表，不包括回收站的文件夹和文件
	 * pass
	 */
	@Test
	public void getShareFolderAndFileByEntId(){
		System.out.println("============按企业取取企业网盘下文件夹列表和文件列表，不包括回收站的文件夹和文件==================");
		String entId = "1234";
		String re  = postData(RESTurl.getShareFolderAndFileByEntId,entId);
		System.out.println("结果 = "+re);

		ShareFolderAndFileDTO sfaf = PojoMapper.fromJsonAsObject(re, ShareFolderAndFileDTO.class);
		assertNotNull(sfaf);
	}
	/***
	 *  按用户取取企业网盘下有可见权限的文件夹列表和文件列表，不包括回收站的文件夹和文件
	 *  pass
	 */
	@Test
	public void getShareFolderAndFileByUserId (){
		System.out.println("============ 按用户取取企业网盘下有可见权限的文件夹列表和文件列表，不包括回收站的文件夹和文件==================");
		String userId = ""+userId3885;
		String re  = postData(RESTurl.getShareFolderAndFileByUserId  ,userId);
		System.out.println("结果 = "+re);

		ShareFolderAndFileDTO sfaf = PojoMapper.fromJsonAsObject(re, ShareFolderAndFileDTO.class);
		assertNotNull(sfaf);
	}
	/***
	 *   按文件夹id取单个企业网盘文件夹信息
	 *   pass
	 */
	@Test
	public void getShareFolderById(){
		System.out.println("============按文件夹id取单个企业网盘文件夹信息==================");
		String folderId = ""+New_folder;
		String re  = postData(RESTurl.getShareFolderById,folderId);
		System.out.println("结果 = "+re);

		 ShareFolderDTO sfaf = PojoMapper.fromJsonAsObject(re,  ShareFolderDTO.class);
		 assertNotNull(sfaf);
		 
	}
	/***
	 *  按文件夹取企业网盘文件夹历史修改记录
	 *  pass
	 *  
	 */
	@Test
	public void getShareFolderHistory(){
		System.out.println("============按文件夹取企业网盘文件夹历史修改记录=================");
	//	String folderId = ""+New_folder;
		
		ShareFolderUpdateDTO sfu = new ShareFolderUpdateDTO();
		sfu.setFolderId(New_folder);
		sfu.setEnterpriseId(entId);
		
		String re  = postData(RESTurl.getShareFolderHistory,sfu);
		System.out.println("结果 = "+re);

		ShareHistoryDTO sfaf = PojoMapper.fromJsonAsObject(re, ShareHistoryDTO.class);
		assertNotNull(sfaf);
		
	}
	
	/***
	 *    按企业ID取企业网盘下文件夹列表，不包括回收站中文件夹
	 *    pass
	 */
	@Test
	public void getShareFoldersByEntId(){
		System.out.println("============ 按企业ID取企业网盘下文件夹列表，不包括回收站中文件夹==================");
		String entId = "1234";
		String re  = postData(RESTurl.getShareFoldersByEntId,entId);
		System.out.println("结果 = "+re);

		 ShareFoldersDTO sfaf = PojoMapper.fromJsonAsObject(re,  ShareFoldersDTO.class);
		 assertNotNull(sfaf);
	}
	
	/***
	 *    按用户取取企业网盘下有可见权限的文件夹列表，不包括回收站的文件夹
	 *    pass
	 */
	@Test
	public void getShareFoldersByUserId(){
		System.out.println("============ 按用户取取企业网盘下有可见权限的文件夹列表，不包括回收站的文件夹==================");
		String userId = ""+userId3885;
		String re  = postData(RESTurl.getShareFoldersByUserId,userId);
		System.out.println("结果 = "+re);

		ShareFoldersDTO sfaf = PojoMapper.fromJsonAsObject(re, ShareFoldersDTO.class);
		assertNotNull(sfaf);
	}
	
	/***
	 *  按文件夹和版本取企业网盘文件夹历史版本详细修改记录
	 *  pass
	 */
	@Test
	public void getShareFolderVersionDetail(){
		System.out.println("============按文件夹和版本取企业网盘文件夹历史版本详细修改记录==================");
		//String entId = "1234";
		ShareFolderUpdateDTO sfu = new  ShareFolderUpdateDTO();
		sfu.setFolderId(New_folder);
		sfu.setVersion(19L);
		
		String re  = postData(RESTurl.getShareFolderVersionDetail,sfu);
		System.out.println("结果 = "+re);

		 ShareHistoryDTO sfaf = PojoMapper.fromJsonAsObject(re,  ShareHistoryDTO.class);
		 assertNotNull(sfaf);
	}
	/***
	 *   按外链id取共享的文件夹列表和文件列表
	 *   pass
	 */
	@Test
	public void getShareLinkFiles(){
		System.out.println("============按外链id取共享的文件夹列表和文件列表==================");
		String linkId = "263923";
		String re  = postData(RESTurl.getShareLinkFiles,linkId);
		System.out.println("结果 = "+re);

		 ShareLinkFilesDTO sfaf = PojoMapper.fromJsonAsObject(re,  ShareLinkFilesDTO.class);
		 assertNotNull(sfaf);
	}
	/***
	 *   按用户取企业网盘下有本地交互权限的文件列表，,不包括回收站中文件
	 *   pass
	 */
	@Test
	public void getSyncShareFilesByUserId(){
		System.out.println("============按用户取企业网盘下有本地交互权限的文件列表，,不包括回收站中文件==================");
		String userId = ""+userId3885;
		String re  = postData(RESTurl.getSyncShareFilesByUserId,userId);
		System.out.println("结果 = "+re);

		ShareFilesDTO sfaf = PojoMapper.fromJsonAsObject(re, ShareFilesDTO.class);
		assertNotNull(sfaf);
	}
	/***
	 *  按用户取企业网盘下有本地交互权限的文件夹列表和文件列表，,不包括回收站中文件夹和文件
	 *  pass
	 */
	@Test
	public void getSyncShareFolderAndFileByUserId(){
		System.out.println("============ 按用户取企业网盘下有本地交互权限的文件夹列表和文件列表，,不包括回收站中文件夹和文件==================");
		String userId = ""+userId3885;
		String re  = postData(RESTurl.getSyncShareFolderAndFileByUserId,userId);
		System.out.println("结果 = "+re);

		ShareFolderAndFileDTO sfaf = PojoMapper.fromJsonAsObject(re, ShareFolderAndFileDTO.class);
		assertNotNull(sfaf);
	}
	/***
	 *   按用户取企业网盘下有本地交互权限的文件夹列表，,不包括回收站中文件夹
	 */
	@Test
	public void getSyncShareFoldersByUserId(){
		System.out.println("============ 按用户取企业网盘下有本地交互权限的文件夹列表，,不包括回收站中文件夹==================");
		String userId = ""+userId3885;
		String re  = postData(RESTurl.getSyncShareFoldersByUserId ,userId);
		System.out.println("结果 = "+re);

		ShareFoldersDTO sfaf = PojoMapper.fromJsonAsObject(re, ShareFoldersDTO.class);
		assertNotNull(sfaf);
	}
	/***
	 *    插入企业网盘文件访问记录，包括下载和浏览记录
	 */
	@Test
	public void insertFileAccessRecord (){
		System.out.println("============ 插入企业网盘文件访问记录，包括下载和浏览记录==================");
		//String entId = "1234";
		ShareFileRecordDTO sfr = new  ShareFileRecordDTO();
		 sfr.setFileId(ligao);
		 sfr.setRecordType("fa");
		 sfr.setUserId(userId3885);
		 
		String re  = postData(RESTurl.insertFileAccessRecord,sfr);
		System.out.println("结果 = "+re);

		assertEquals( CommConstants.OK_MARK, re);
	}
	
	/***
	 *     锁定企业网盘文件 
	 *     bug- 1
	 */
	@Test
	public void lockShareFile(){
		System.out.println("============  锁定企业网盘文件 ==================");
		//String entId = "1234";
		 ShareFileDTO sf = new  ShareFileDTO();
		 sf.setFileId(263081L);
		 sf.setLockByUser("testemp3885");
		 sf.setLockByUserId(userId3885);
		 
		String re  = postData(RESTurl.lockShareFile,sf);
		System.out.println("结果 = "+re);
		assertEquals(CommConstants.OK_MARK, re);
	}
	
	/***
	 *     移动企业网盘文件夹和文件 
	 *     bug-2 移动文件和文件夹于目标文件文件夹参数定义模糊。
	 */
	@Test
	public void moveShareFolderAndFiles(){
		System.out.println("============  移动企业网盘文件夹和文件 ==================");
		
		ShareFileDTO sf = new ShareFileDTO();
		sf.setEnterpriseId(entId);
		sf.setFileId(ligao);
		sf.setFolderId(New_folder);
		sf.setVersion(16);
		
		List<ShareFileDTO> sfs = new ArrayList<ShareFileDTO>();
		sfs.add(sf);
 		
		ShareFolderAndFileUpdateDTO sffu = new ShareFolderAndFileUpdateDTO();
		sffu.setFileList(sfs);
		
		String re  = postData(RESTurl.moveShareFolderAndFiles,sffu);
		System.out.println("结果 = "+re);
		if (ErrorType.error500.name().equals(re)) { fail(); }
	}
	/***
	 *      新建企业网盘目录 
	 *      pass
	 */
	@Test
	public void newShareFolder(){
		System.out.println("============新建企业网盘目录 ==================");
		ShareFolderUpdateDTO sfu = new ShareFolderUpdateDTO();
		sfu.setEnterpriseId(entId);
//		sfu.setFolderId(folderId);
		sfu.setName("老照片");
		//sfu.setParentId(263038L);
		sfu.setUserId(userId3885);
		sfu.setUserName("风儿轻轻");
		
		String re  = postData(RESTurl.newShareFolder,sfu);
		System.out.println("结果 = "+re);
		if (ErrorType.error500.name().equals(re)) { fail(); }
		
	}
	/***
	 *     恢复企业网盘文件历史版本 
	 *
	 *     bug-3 后台异常为： Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Column 'file_guid' cannot be null
	 *		实际上已经设置
	 */
	
	@Test
	public void restoreShareFileVersion(){
		System.out.println("============恢复企业网盘文件历史版本==================");
		//String entId = "1234";
		ShareFileUpdateDTO sf = new ShareFileUpdateDTO();
		sf.setEnterpriseId(entId);
		sf.setFileId(265561L);
		sf.setFolderId(265485L);
		sf.setVersion(33);
		sf.setGuid("fdabbcbf-cee2-4449-8370-89b488f796a8.html");
		sf.setName("1234.oatw");
		sf.setUserId(249509L);

		
		String re  = postData(RESTurl.restoreShareFileVersion,sf);
		System.out.println("结果 = "+re);
		if (ErrorType.error500.name().equals(re)) { fail(); }
	}
	/***
	 *   从回收站还原删除的企业网盘文件夹和文件 
	 *   Bug -4 参数不对？
	 */
	@Test
	public void restoreShareFolderAndFiles(){
		System.out.println("============ 从回收站还原删除的企业网盘文件夹和文件 ==================");
		//String entId = "1234";
		ShareFolderAndFileUpdateDTO  sfaf = new ShareFolderAndFileUpdateDTO();
		sfaf.setOperation(Operation.RestoreFromRecycle);
		sfaf.setUserId(userId3885);
		sfaf.setUserName("testemp3885");
		
		ShareFileDTO fileDTO = new ShareFileDTO();
		fileDTO.setFileId(265561L);
		fileDTO.setEnterpriseId(entId);
		fileDTO.setFolderId(265485L);
		fileDTO.setName("1234.oatw");
		fileDTO.setGuid("fdabbcbf-cee2-4449-8370-89b488f796a8.html");
		fileDTO.setVersion(33);

		fileDTO.setCreateTime(new Date());
		fileDTO.setUpdateTime(new Date());
		
		List<ShareFileDTO> sfs = new ArrayList<ShareFileDTO>();
		sfs.add(fileDTO);
		
		//sfaf.setForderList(forderList)
		sfaf.setFileList(sfs);
		String re  = postData(RESTurl.restoreShareFolderAndFiles,sfaf);
		System.out.println("结果 = "+re);
		if (ErrorType.error500.name().equals(re)) { fail(); }
	}
	/***
	 * 恢复企业网盘文件夹历史版本
	 * pass
	 * 
	 */
	@Test
	public void restoreShareFolderVersion(){
		System.out.println("============恢复企业网盘文件夹历史版本==================");
		
		ShareFolderUpdateDTO sfu = new ShareFolderUpdateDTO();
		sfu.setFolderId(265485L);
		sfu.setVersion(36);
		sfu.setOperation(Operation.RestoreVersion);
		
		
		String re  = postData(RESTurl.restoreShareFolderVersion,sfu);
		System.out.println("结果 = "+re);
		if (ErrorType.error500.name().equals(re)) { fail(); }
	}
	
	/***
	 *    同步企业网盘文件
	 *    pass
	 */
	@Test
	public void syncEnterpriseFile(){
		System.out.println("============同步企业网盘文件 ==================");
		//String entId = "1234";
		ShareFileUpdateDTO sfu = new ShareFileUpdateDTO();
		sfu.setFileId(265561L);
		sfu.setFolderId(265485L);
		sfu.setGuid("fdabbcbf-cee2-4449-8370-89b488f796a8.html");
		sfu.setEnterpriseId(entId);
		sfu.setName("一二三四.oatw");
		sfu.setUserId(249509L);
		
		
		String re  = postData(RESTurl.syncEnterpriseFile ,sfu);
		System.out.println("结果 = "+re);
		if (ErrorType.error500.name().equals(re)) { fail(); }
	}
	/***
	 *   解锁锁定企业网盘文件 
	 */
	@Test
	public void unlockShareFiles(){
		System.out.println("============  解锁锁定企业网盘文件  ==================");
		ShareFileDTO  sfdto = new ShareFileDTO();
		//sfdto.setGuid("");
		sfdto.setFileId(265561L);
		
		List<ShareFileDTO> sflist =new ArrayList<ShareFileDTO>();
		sflist.add(sfdto);
		
		ShareFilesDTO sf = new ShareFilesDTO();
		sf.setShareFileList(sflist);
		
		String re  = postData(RESTurl.unlockShareFiles ,sf);
		System.out.println("结果 = "+re);

		assertEquals( CommConstants.OK_MARK, re);
	}
	/***
	 *     修改企业网盘文件信息
	 *     bug -6 
	 */
	@Test
	public void updateShareFile(){
		System.out.println("============ 修改企业网盘文件信息 ==================");
		//String entId = "1234";
		
		String re  = postData(RESTurl.getShareFileById   ,"264075");
		System.out.println("结果 = "+re);
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		 ShareFileDTO f = PojoMapper.fromJsonAsObject(re,  ShareFileDTO.class);

		ShareFileUpdateDTO fileDTO = new ShareFileUpdateDTO();
		fileDTO.setFileId(f.getFileId());
		fileDTO.setEnterpriseId(f.getEnterpriseId());
		fileDTO.setFolderId(f.getFolderId());
		fileDTO.setName(f.getName());
		fileDTO.setGuid(f.getGuid());
		fileDTO.setVersion(f.getVersion());
		fileDTO.setThumb(f.getThumb());
		fileDTO.setRemark(f.getRemark());
		fileDTO.setCreateTime(f.getCreateTime());
		fileDTO.setUpdateTime(new Date());
		
		fileDTO.setOperation(Operation.RenameFile);
		fileDTO.setName("花好月圆.jpg");
		fileDTO.setUserId(userId3885);
		fileDTO.setUserName("testemp3885");
		
		String re1  = postData(RESTurl.updateShareFile,fileDTO);
		System.out.println("结果 = "+re1);
		if (ErrorType.error500.name().equals(re1)) { fail(); }
	}
	/***
	 *   修改企业网盘目录信息 
	 *   bug-5参数问题？
	 */
	@Test
	public void updateShareFolder(){
		System.out.println("============  修改企业网盘目录信息 ==================");
	//	String entId = "1234";
		String re  = postData(RESTurl.getShareFolderById,"265485");
		System.out.println("结果 = "+re);
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		//assertEquals( CommConstants.OK_MARK, re);
		 ShareFolderDTO f = PojoMapper.fromJsonAsObject(re,  ShareFolderDTO.class);

		ShareFolderUpdateDTO folderDTO = new ShareFolderUpdateDTO();
		folderDTO.setFolderId(f.getFolderId());
		folderDTO.setEnterpriseId(f.getEnterpriseId());
		folderDTO.setParentId(f.getParentId());
		folderDTO.setVersion(f.getVersion());
		folderDTO.setRemark(f.getRemark());
		folderDTO.setUpdateTime(new Date());
		
		folderDTO.setOperation(Operation.RenameFolder);
		folderDTO.setName("旧照片");
		folderDTO.setUserId(userId3885);
		folderDTO.setUserName("testemp3885");
		
		String re1  = postData(RESTurl.updateShareFolder,folderDTO);
		System.out.println("结果 = "+re1);
		if (ErrorType.error500.name().equals(re1)) { fail(); }
	}
	/***
	 *    修改企业网盘文件夹和文件信息
	 *    bug
	 */
	@Test
	public void updateShareFolderAndFiles(){
		System.out.println("============修改企业网盘文件夹和文件信息 ==================");
		//String entId = "1234";
		String re  = postData(RESTurl.getShareFileById   ,"264075");
		System.out.println("结果 = "+re);
		
		if( ErrorType.error500.toString().equals(re)){
			fail(re);
		}
		ShareFileDTO sfaf = PojoMapper.fromJsonAsObject(re,  ShareFileDTO.class);
		List<ShareFileDTO>  sList =new ArrayList<ShareFileDTO>();
		sList.add(sfaf);
		
		
		ShareFolderAndFileUpdateDTO sfu = new ShareFolderAndFileUpdateDTO();
		sfu.setFileList(sList);
		sfu.setOperation(Operation.Update);
		sfu.setUserId(userId3885);
		sfu.setUserName("testemp3885");
		
		
		String re1  = postData(RESTurl.updateShareFolderAndFiles,sfu);
		System.out.println("结果 = "+re1);
		if (ErrorType.error500.name().equals(re1)) { fail(); }
	}
	/***
	 *    修改企业网盘文件夹信息及文件夹容量
	 *     
	 *    	 */
	@Test
	public void updateShareFolderSize(){
		System.out.println("============  修改企业网盘文件夹信息及文件夹容量 ==================");
		//String entId = "1234";
		
		ShareFolderUpdateDTO sfu = new ShareFolderUpdateDTO();
		sfu.setFolderId(263686L);
		sfu.setName("人生最美1");
		sfu.setMaxSize(5000L);
		sfu.setUserId(userId3885);
		sfu.setUserName("testemp3885");
		sfu.setEnterpriseId(entId);
		sfu.setVersion(34);
		sfu.setOperation(Operation.SetFolderSize);
		
		String re  = postData(RESTurl.updateShareFolderSize,sfu);
		System.out.println("结果 = "+re);
		if (ErrorType.error500.name().equals(re)) { fail(); }
	}
	
	
}
