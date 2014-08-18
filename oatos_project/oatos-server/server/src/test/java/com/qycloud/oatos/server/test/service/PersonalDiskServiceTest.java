/**
 * 
 */
package com.qycloud.oatos.server.test.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import static org.junit.Assert.*;

import com.conlect.oatos.dto.client.FolderAndFileDTO;
import com.conlect.oatos.dto.client.FolderAndFileParamDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.NetworkFilesDTO;
import com.conlect.oatos.dto.client.NetworkFolderDTO;
import com.conlect.oatos.dto.client.NetworkFoldersDTO;
import com.conlect.oatos.dto.client.user.PersonDiskAllocDTO;
import com.conlect.oatos.dto.client.user.PersonDiskAllocListDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;

import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.dao.PersonalFileMapper;
import com.qycloud.oatos.server.dao.PersonalFolderMapper;
import com.qycloud.oatos.server.domain.entity.PersonalFile;
import com.qycloud.oatos.server.domain.entity.PersonalFolder;

import com.qycloud.oatos.server.domain.logic.PersonalDiskLogic;

/**
 * @author xiao.min
 *
 */
public class PersonalDiskServiceTest extends BaseServiceTest {

	
	
	
//	@Before
//	public void before(){
//		super.before();
//		 personalFolder = initPersonalFolder();
//		 personalFile = initPersonalFile();
//		 personalFolderMapper.addPersonalFolder(personalFolder);
//		 personalFileMapper.addPersonalFile(personalFile);
//		 System.out.println("insert succeed!");
//		 
//	}
//	
//	@After
//	public void after(){
//		super.after();
//		personalFileMapper.deletePersonalFile(personalFile.getFileId());
//		personalFolderMapper.deletePersonalFolder(personalFolder.getFolderId());
//	}
	
	
	
	/***
	 * 新建网盘文件夹
	 * pass
	 */
	@Test
	public void addDiskFolder(){
		System.out.println("================新建网盘文件夹=====================");
		//参数
		NetworkFolderDTO netWork = new NetworkFolderDTO();
		netWork.setUserId(117187);
		netWork.setDeleted(0);
		netWork.setFolderName("jiuyuehe");
		netWork.setVersion(0);
		
		//请求
		String re = postData(RESTurl.addDiskFolder, netWork);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		 NetworkFolderDTO net = PojoMapper.fromJsonAsObject(re,  NetworkFolderDTO.class);
		//assertEquals(net.getFolderName(), "xiao.min");
		// assertEquals(ErrorType.errorSameFolder.toString(), re);
	}
	
	/***
	 * 添加网盘文件
	 * pass - 
	 */
	@Test
	public void addPersonalFile (){
		System.out.println("================添加网盘文件=====================");
		//参数
		 NetworkFileDTO netWork = new  NetworkFileDTO();
		 netWork.setDeleted(0);
		 netWork.setEnterpriseId(1234);
		 netWork.setFolderId(249211L);
		 netWork.setFolderName("xiao.min");
		 netWork.setVersion(0);
		 netWork.setType("ppt");
		 netWork.setName("doodoole_googole.pptx");
		 netWork.setGuid("13543-4144-5645-6545-54asdf");
		 netWork.setPageCount(10);
		 netWork.setRemark("ok");
		 netWork.setSize(2000);
		 netWork.setUserId(117187);
		// personalDiskLogic.addPersonalFile(file);
		 
		//请求
		String re = postData(RESTurl.addPersonalFile, netWork);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		//PojoMapper.fromJsonAsObject(re, class);
		//assertEquals(CommConstants.OK_MARK, re);
		assertNotNull(re);
	}
	
	/***
	 *  分配个人网盘空间
	 *  pass
	 */
	@Test
	public void allocPersonDisk(){
		System.out.println("=============== 分配个人网盘空间=====================");
		//参数
		 PersonDiskAllocDTO  allDTO =new PersonDiskAllocDTO();
		 
		 allDTO.setUserId(117187);
		 allDTO.setAddSize(1);
		 allDTO.setOriginalAddSize(100);
		 allDTO.setOriginalDiskSize(3000);
		 allDTO.setRealname("小小");
		 allDTO.setUsername("xiao.min");
		 
		 List<PersonDiskAllocDTO> personList = new ArrayList<PersonDiskAllocDTO>();
		 personList.add(allDTO);
		
		 PersonDiskAllocListDTO person = new  PersonDiskAllocListDTO();
		 person.setEntId(1234);
		 person.setPersonDiskList(personList);
		//请求
		String re = postData(RESTurl.allocPersonDisk, person);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		//PojoMapper.fromJsonAsObject(re, class);
		assertEquals(CommConstants.OK_MARK, re);
	}

	
	/***
	 *  个人网盘文件同步上传之前，检查文件版本，网盘空间，同名文件
	 *  pass
	 */
	@Test
	public void checkPersonalFileSyncUpload (){
		System.out.println("=========个人网盘文件同步上传之前，检查文件版本，网盘空间，同名文件=========");
		//参数
		 NetworkFileDTO netWork = new  NetworkFileDTO();
		 netWork.setDeleted(0);
		 netWork.setEnterpriseId(1234);
		 netWork.setFolderId(247863l);
		 netWork.setFolderName("xiao.min");
		 netWork.setVersion(0);
		 netWork.setType("ppt");
		 netWork.setName("doodoole_froum.pptx");
		// netWork.setGuid("1357-4144-5645-6545-54asf");
		  netWork.setGuid("135-4144-5645-6545-54asdf");
		 
		 netWork.setPageCount(10);
		 netWork.setRemark("ok");
		 netWork.setSize(2000);
		 netWork.setUserId(117187);
		//请求
		String re = postData(RESTurl.checkPersonalFileSyncUpload , netWork);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		//PojoMapper.fromJsonAsObject(re, class);
		assertEquals(CommConstants.OK_MARK, re);
		//assertEquals(, re);
	}
	
	/***
	 *  判断私有网盘文件是否存在,及网盘空间
	 *  pass
	 */
	@Test
	public void checkPersonalFileUpload  (){
		System.out.println("=========判断私有网盘文件是否存在,及网盘空间=========");
		//参数
		NetworkFileDTO netWork = new  NetworkFileDTO();
		 netWork.setDeleted(0);
		 netWork.setEnterpriseId(1234);
		 netWork.setFolderId(247863l);
		 netWork.setFolderName("xiao.min");
		 netWork.setVersion(0);
		 netWork.setType("ppt");
		 netWork.setName("doodoole_froum.pptx");
		// netWork.setGuid("1357-4144-5645-6545-54asf");
		  netWork.setGuid("135-4144-5645-6545-54asdf");
		 
		 netWork.setPageCount(10);
		 netWork.setRemark("ok");
		 netWork.setSize(2000);
		 netWork.setUserId(117187);
		//请求
		String re = postData(RESTurl.checkPersonalFileUpload  , netWork);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		//PojoMapper.fromJsonAsObject(re, class);
		assertEquals(CommConstants.OK_MARK, re);
	}
	/***
	 *  检查当前个人网盘使用空间是否超出了免费空间
	 *  pass
	 */
	@Test
	public void checkUserDiskSizeExceed(){
		System.out.println("=========检查当前个人网盘使用空间是否超出了免费空间=========");
		//参数
	//	NetworkFileDTO netWork = new  NetworkFileDTO();
		String userId ="117187";
		//请求
		String re = postData(RESTurl.checkUserDiskSizeExceed, userId);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		//PojoMapper.fromJsonAsObject(re, class);
		assertEquals(CommConstants.FALSE_MARK, re);
	}
	/***
	 *  删掉文件或文件夹 
	 *   pass         
	 */
	@Test
	public void deleteFolderAndFile(){
		System.out.println("========= 删掉文件或文件夹=========");
		
		//参数
		NetworkFolderDTO netfolder = new NetworkFolderDTO();
		netfolder.setUserId(117187);
		netfolder.setDeleted(0);
		netfolder.setFolderName("jiuyuehe");
		netfolder.setVersion(4);
		netfolder.setFolderId(248193);
		
		List<NetworkFolderDTO> ntList = new ArrayList<NetworkFolderDTO>();
		ntList.add(netfolder);
		
		NetworkFileDTO netFile = new  NetworkFileDTO();
			netFile.setDeleted(0);
			netFile.setEnterpriseId(1234);
			netFile.setFolderId(247863l);
			netFile.setFolderName("xiao.min");
			netFile.setVersion(0);
			netFile.setType("ppt");
			netFile.setName("doodoole_ggole.pptx");
			netFile.setGuid("13543-4144-5645-6545-54asdf");
			netFile.setPageCount(10);
			netFile.setRemark("ok");
			netFile.setSize(2000);
			netFile.setUserId(117187);
			netFile.setFileId(248194l);
			
		List<NetworkFileDTO> ntfList = new ArrayList<NetworkFileDTO>();	
		
		//参数
		FolderAndFileDTO netWork = new  FolderAndFileDTO();
		netWork.setFileList(ntfList);
		netWork.setForderList(ntList);
		//请求
		String re = postData(RESTurl.deleteFolderAndFile , netWork);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		//PojoMapper.fromJsonAsObject(re, class);
		assertEquals(CommConstants.OK_MARK, re);
	}
	
	/***
	 *   删除个人网盘文件
	 *   pass
	 */
	@Test
	public void deletePersonalFile(){
		System.out.println("========= 删除个人网盘文件=========");
		//参数
		String fileId ="248194";
		//请求
		String re = postData(RESTurl.deletePersonalFile , fileId);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		//PojoMapper.fromJsonAsObject(re, class);
		assertEquals(CommConstants.OK_MARK, re);
	}
	/***
	 *    取所有网盘文件夹和文件,包括回收站
	 *    pass
	 */
	@Test
	public void getAllDiskFolderAndFileByUserId (){
		System.out.println("========= 取所有网盘文件夹和文件,包括回收站=========");
		//参数
		//NetworkFileDTO netWork = new  NetworkFileDTO();
		String userId ="117187";
		//请求
		String re = postData(RESTurl.getAllDiskFolderAndFileByUserId , userId);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		 FolderAndFileDTO ff = PojoMapper.fromJsonAsObject(re,  FolderAndFileDTO.class);
		assertEquals(ff.getForderList().get(0).getFolderId(), 117179);
	}
	/***
	 *     获取网盘邮件文件夹
	 *     pass
	 */
	@Test
	public void getEmailFolder(){
		System.out.println("=========  获取网盘邮件文件夹=========");
		//参数
		//NetworkFileDTO netWork = new  NetworkFileDTO();
		String userId ="117187";
		//请求
		String re = postData(RESTurl.getEmailFolder , userId);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		NetworkFolderDTO nf = PojoMapper.fromJsonAsObject(re, NetworkFolderDTO.class);
		assertEquals(nf.getFolderId(), 117183);
	}
	/***
	 *     取单个文件信息
	 *     pass
	 *    修改文件信息-  pass
	 */
	@Test
	public void getFileById(){
		System.out.println("=========  取单个文件信息=========");
		//参数
		//NetworkFileDTO netWork = new  NetworkFileDTO();
		String userId ="247967";
		//请求
		String re = postData(RESTurl.getFileById  , userId);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		 NetworkFileDTO nf = PojoMapper.fromJsonAsObject(re,  NetworkFileDTO.class);
		assertEquals(nf.getName(), "doodoole.pptx");
		
		System.out.println("=========修改文件========");
		//参数
		//NetworkFileDTO netWork = new  NetworkFileDTO();
		//String userId ="";
		
		nf.setSize(2500);
		
		//请求
		String res = postData(RESTurl.updateDiskFile, nf);
		System.out.println(res);
		//验证
		if("error500".equals(res)){
			fail(re);
		}
		NetworkFileDTO nfd = PojoMapper.fromJsonAsObject(re, NetworkFileDTO.class);
		//assertEquals(CommConstants.OK_MARK, re);
	}
	
	/***
	 *  获取用户文件
	 *   pass
	 */
	@Test
	public void getFilesByUserId(){
		System.out.println("========= 获取用户文件========");
		//参数
		//NetworkFileDTO netWork = new  NetworkFileDTO();
		//netWork.set
		String userId ="117187";
		//请求
		String re = postData(RESTurl.getFilesByUserId  , userId);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		NetworkFilesDTO nft = PojoMapper.fromJsonAsObject(re, NetworkFilesDTO.class);
		assertEquals(nft.getNetworkFileDTOList().get(0).getFileId()+"", "247967");
	}
	/***
	 *   取所有网盘文件夹,不包括回收站
	 *   pass
	 */
	@Test
	public void getFoldersByUserId(){
		System.out.println("========= 取所有网盘文件夹,不包括回收站=========");
		//参数
		//NetworkFileDTO netWork = new  NetworkFileDTO();
		String userId ="117187";
		//请求
		String re = postData(RESTurl.getFoldersByUserId   , userId);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		 NetworkFoldersDTO nft = PojoMapper.fromJsonAsObject(re,  NetworkFoldersDTO.class);
		assertEquals(nft.getNetworkFolderDTOList().get(0).getFolderId()+"", "117179");
	}
	/***
	 *   取个人网盘已使用空间站
	 *   pass
	 */
	@Test
	public void getPersonalDiskUsedSizeByUserId (){
		System.out.println("========= 取个人网盘已使用空间=========");
		//参数
		//NetworkFileDTO netWork = new  NetworkFileDTO();
		String userId ="117187";
		//请求
		String re = postData(RESTurl.getPersonalDiskUsedSizeByUserId    , userId);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		//PojoMapper.fromJsonAsObject(re, class);
		assertNotNull(re);
	}
	/***
	 *   按文件夹取个人网盘子文件夹和文件，取顶层文件夹时，文件夹ID传null
	 *   pass
	 */
	@Test
	public void getPersonalFolderAndFileByFolderId (){
		System.out.println("=========按文件夹取个人网盘子文件夹和文件，取顶层文件夹时，文件夹ID传null=========");
		//参数
		//NetworkFileDTO netWork = new  NetworkFileDTO();
		//String userId ="";
		 FolderAndFileParamDTO faf = new  FolderAndFileParamDTO();
		 faf.setUserId(117187);
		 List<Long> list = new ArrayList<Long>();
		 list.add(247863l);
		 faf.setFolderIds(list);
		//请求
		String re = postData(RESTurl.getPersonalFolderAndFileByFolderId, faf);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		 FolderAndFileDTO fafs = PojoMapper.fromJsonAsObject(re,  FolderAndFileDTO.class);
		//assertEquals(CommConstants.OK_MARK, re);
	}
	/***
	 *   取单个文件夹信息 
	 *   pass
	 *   修改文件夹 
	 *   pass
	 */
	@Test
	public void getPrivateFolderById (){
		System.out.println("=========  取单个文件夹信息 =========");
		//参数
		//NetworkFileDTO netWork = new  NetworkFileDTO();
		String userId ="247863";
		//请求
		String re = postData(RESTurl.getPrivateFolderById    ,userId);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		NetworkFolderDTO nf = PojoMapper.fromJsonAsObject(re,  NetworkFolderDTO.class);
		assertEquals(nf.getFolderName(), "xiao.min");
		
		
		System.out.println("=========修改网盘文件夹的信息 ========");
		
		nf.setVersion(75);
		//参数
		//请求
		String res = postData(RESTurl.updateDiskFolder , nf);
		System.out.println(res);
		//验证
		if("error500".equals(res)){
			fail(re);
		}
		NetworkFolderDTO nfd = PojoMapper.fromJsonAsObject(re, NetworkFolderDTO.class);
		//assertEquals(CommConstants.OK_MARK, re);
		
		
		
	}
	/***
	 *   个人网盘通过文件夹名和用户ID获得文件
	 *   pass
	 */
	@Test
	public void getSystemFileByFileName (){
		System.out.println("========= 个人网盘通过文件夹名和用户ID获得文件=========");
		//参数
		NetworkFileDTO netWork = new  NetworkFileDTO();
		netWork.setUserId(117187);
		netWork.setFolderName("xiao.min");
//		String userId ="";
		//请求
		String re = postData(RESTurl.getSystemFileByFileName , netWork);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		//PojoMapper.fromJsonAsObject(re, class);
		//assertEquals(CommConstants.OK_MARK, re);
	}
	/***
	 *    还原个人网盘回收站文件夹和文件
	 *    pass
	 */
	@Test
	public void restoreFolderAndFile (){
		System.out.println("=========  还原个人网盘回收站文件夹和文件=========");
		//参数
		NetworkFolderDTO netfolder = new NetworkFolderDTO();
		netfolder.setUserId(117187);
		netfolder.setDeleted(0);
		netfolder.setFolderName("jiuyuehe");
		netfolder.setVersion(4);
		netfolder.setFolderId(248193);
		
		List<NetworkFolderDTO> ntList = new ArrayList<NetworkFolderDTO>();
		ntList.add(netfolder);
		
		NetworkFileDTO netFile = new  NetworkFileDTO();
			netFile.setDeleted(0);
			netFile.setEnterpriseId(1234);
			netFile.setFolderId(247863l);
			netFile.setFolderName("xiao.min");
			netFile.setVersion(0);
			netFile.setType("ppt");
			netFile.setName("doodoole_ggole.pptx");
			netFile.setGuid("13543-4144-5645-6545-54asdf");
			netFile.setPageCount(10);
			netFile.setRemark("ok");
			netFile.setSize(2000);
			netFile.setUserId(117187);
			netFile.setFileId(248194l);
			
		List<NetworkFileDTO> ntfList = new ArrayList<NetworkFileDTO>();	
		
		//参数
		FolderAndFileDTO netWork = new  FolderAndFileDTO();
		netWork.setFileList(ntfList);
		netWork.setForderList(ntList);
		//请求
		String re = postData(RESTurl.restoreFolderAndFile, netWork);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		//PojoMapper.fromJsonAsObject(re, class);
		assertEquals(CommConstants.OK_MARK, re);
	}
	/***
	 *   同步个人网盘文件
	 *   pass
	 */
	@Test
	public void syncPersonalFile (){
		System.out.println("========= 同步个人网盘文件=========");
		//参数
		NetworkFileDTO netWork = new  NetworkFileDTO();
		 netWork.setDeleted(0);
		 netWork.setEnterpriseId(1234);
		 netWork.setFolderId(247863l);
		 netWork.setFolderName("xiao.min");
		 netWork.setVersion(0);
		 netWork.setType("ppt");
		 netWork.setName("doodoole_googole.pptx");
		 netWork.setGuid("13543-4144-5645-6545-54asdf");
		 netWork.setPageCount(10);
		 netWork.setRemark("ok");
		 netWork.setSize(2000);
		 netWork.setUserId(117187);
		 netWork.setFileId(249034l);
		
		//请求
		String re = postData(RESTurl.syncPersonalFile, netWork);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		//PojoMapper.fromJsonAsObject(re, class);
		assertEquals("249034", re);
	}
	
	/***  批量修改网盘文件夹和文件修改网盘文件夹的信息 
	 * 
	 *   pass
	 */
	@Test
	public void updateFolderAndFile(){
		System.out.println("=========   批量修改网盘文件夹和文件 ========");
		
		NetworkFolderDTO netfolder = new NetworkFolderDTO();
		netfolder.setUserId(117187);
		netfolder.setDeleted(0);
		netfolder.setFolderName("jiuyuehe");
		netfolder.setVersion(4);
		netfolder.setFolderId(248193);
		
		List<NetworkFolderDTO> ntList = new ArrayList<NetworkFolderDTO>();
		ntList.add(netfolder);
		
		NetworkFileDTO netFile = new  NetworkFileDTO();
			netFile.setDeleted(0);
			netFile.setEnterpriseId(1234);
			netFile.setFolderId(247863l);
			netFile.setFolderName("xiao.min");
			netFile.setVersion(0);
			netFile.setType("ppt");
			netFile.setName("doodoole_ggole.pptx");
			netFile.setGuid("13543-4144-5645-6545-54asdf");
			netFile.setPageCount(10);
			netFile.setRemark("ok");
			netFile.setSize(2000);
			netFile.setUserId(117187);
			netFile.setFileId(248194l);
			
		List<NetworkFileDTO> ntfList = new ArrayList<NetworkFileDTO>();	
		
		//参数
		FolderAndFileDTO netWork = new  FolderAndFileDTO();
		netWork.setFileList(ntfList);
		netWork.setForderList(ntList);
		//请求
		String re = postData(RESTurl.updateFolderAndFile  , netWork);
		System.out.println(re);
		//验证
		if("error500".equals(re)){
			fail(re);
		}
		//PojoMapper.fromJsonAsObject(re, class);
		assertEquals(CommConstants.OK_MARK, re);
	}
	/** 取个人网盘回收站中文件夹和文件 
	 * pass
	 */
	@Test
	public void getPersonalFolderAndFileInRecycle(){
		System.out.println("== 取个人网盘回收站中文件夹和文件 ==");
		String re = postData(RESTurl.getPersonalFolderAndFileInRecycle,"117187");
		if("error500".equals(re)){
			fail(re);
		}
		 FolderAndFileDTO fnf = PojoMapper.fromJsonAsObject(re,  FolderAndFileDTO.class);
		//assertEquals(CommConstants.OK_MARK, re);
	}
}
