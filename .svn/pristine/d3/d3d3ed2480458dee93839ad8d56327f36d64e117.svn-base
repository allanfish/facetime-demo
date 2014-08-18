package com.qycloud.oatos.server.test.file;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.conlect.oatos.dto.client.AcceptFileDTO;
import com.conlect.oatos.dto.client.DownFileDTO;
import com.conlect.oatos.dto.client.ImageDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.SaveFileDTO;
import com.conlect.oatos.dto.client.UserIconDTO;
import com.conlect.oatos.dto.client.ViewFileDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.RESTurl;

public class FileServiceTest  extends BaseFileServiceTest{
	
	
	private static Long userId3885 =249509L;
	private static Long entId = 1234L;
	private static Long New_folder =1346L;
	private static Long New_folder1 =1347L;
	
	/**
	 * 接收聊天文件，保存文件至个人网盘
	 */
	@Test
	public void acceptFile(){
		NetworkFileDTO net = new NetworkFileDTO();
		net.setEnterpriseId(entId);
		net.setFolderId(249513L);
		net.setUserId(userId3885);
		net.setGuid("4aae685f-0b5c-432e-afe7-c5d29abdf506.jpg");
		net.setName("test.jpg");
		
		List<NetworkFileDTO> ntList = new ArrayList<NetworkFileDTO>();
		ntList.add(net);
		AcceptFileDTO af = new AcceptFileDTO();
		af.setUserId(userId3885);
		af.setFileList(ntList);
		
		String re = postData(RESTurl.acceptFile,af);
		System.out.println(re);
	}
	
	/**
	 * 检查文件
	 */
	@Test
	public void checkDiskFile(){
		Map<String,String> map =new HashMap<String,String>();
		headers.add("fileId", "373143");
		headers.add("type", "1");
		String re = getData(RESTurl.checkDiskFile, map);
		
	}
	
	/**
	 *  制作头像，剪切图像
	 */
	@Test
	public void cropUserPicture(){
		
		ImageDTO  img =new  ImageDTO();
		img.setHeight(128);
		img.setWidth(128);
		img.setCropStartX(100);
		img.setCropStartY(3);
		img.setUserId(userId3885);
		img.setName("6a49463c-6e62-498c-8109-b0df45983878.jpg");
		img.setUrl("temp/6a49463c-6e62-498c-8109-b0df45983878.jpg");
		 
		String re = postData(RESTurl.cropUserPicture,img);
		System.out.println(re);
	} 
	
	/**
	 * 删除使用过的头像
	 */
	@Test
	public void deleteUserIcon(){
		UserIconDTO ui =new UserIconDTO();
		ui.setPath("onlinedisk/personal/1235/icon/8f04ad0c-d05b-439a-8740-9e12ada1626b_96.png");
		
		String re = postData(RESTurl.deleteUserIcon,ui);
		System.out.println(re);
	}
	
	/**
	 *  打包下载文件
	 */
	@Test
	public void downZipFile (){
		
		 DownFileDTO df = new  DownFileDTO();
		 df.setEnterpriseId(entId);
		 df.setUserId(userId3885);
		 df.setType("yas");
		 //df.setFolder(1346L);
	//	 df.s
		 
		 String re = postData(RESTurl.downZipFile ,df);
		
	}
	
	/**
	 *  将文件转成html，在线编辑文件
	 */
	@Test
	public void editFileAsHtml (){
		ViewFileDTO df = new  ViewFileDTO();
		
		String re = postData(RESTurl.editFileAsHtml ,df);
		System.out.println("-----------re-----------"+re);
		
	}
	
	/**
	 * 下载文件
	 */
	@Test
	public void fileDownload(){
		
	}
	
	/**
	 * 取个人网盘中图片的缩略图 
	 */
	@Test
	public void getDiskImageThumbs (){
		
	}
	
	/**
	 * 取企业使用过的logo
	 */
	@Test
	public void getEnterpriseLogos (){
		
	}
	
	/**
	 *  获取文件的流
	 */
	@Test
	public void getFileStream (){
		
	}
	
	/**
	 *  获取媒体文件流
	 */
	@Test
	public void getMediaStream (){
		
	}
	/**
	 *   取用户使用过的头像
	 */
	@Test
	public void getUserIcons  (){
		
	}
	/**
	 * 制作图像，修改图片大小
	 */
	@Test
	public void resizeUserPicture  (){
		
	}
	/**
	 * 新建文档
	 */
	@Test
	public void saveFile  (){
		SaveFileDTO sf =new SaveFileDTO();
		sf.setEnterpriseId(entId);
		//sf.setFileId(fileId);
		sf.setFolderId(New_folder);
		sf.setName("真爱.oatw");
		sf.setUserId(userId3885);
		sf.setVersion(1);
		sf.setToType(CommConstants.FILE_TYPE_SHAREDISK);
		
		String re = postData(RESTurl.saveFile,sf);
		
		System.out.println(re);
		
	}
	/**
	 *   保存文件到网盘
	 */
	@Test
	public void saveFileToDisk (){
		 SaveFileDTO sf = new  SaveFileDTO();
		
		
		
		
	}
	/**
	 * 分段下载文件
	 */
	@Test
	public void sectionFileDownload (){
		
	}
	/**
	 * 复制个人网盘文件至企业网盘，发送更新消息
	 */
	@Test
	public void sharePersonalFile  (){
		
	}
	/**
	 *  将文件转成html，浏览文件
	 */
	@Test
	public void viewFileAsHtml (){
		
	}
	/**
	 *  将文件转成pdf，浏览文件
	 */
	@Test
	public void viewFileAsPdf  (){
		
	}
	/**
	 *  将文件转成swf，浏览文件
	 */
	@Test
	public void viewFileAsSwf  (){
		
	}
	
	
	

}
