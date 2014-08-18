package com.qycloud.oatos.filemanager.domain.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.conlect.oatos.dto.autobean.IFileThumbDTO;
import com.conlect.oatos.dto.autobean.INetworkFileDTO;
import com.conlect.oatos.dto.autobean.IUserIconDTO;
import com.conlect.oatos.dto.client.AcceptFileDTO;
import com.conlect.oatos.dto.client.CopyFileDTO;
import com.conlect.oatos.dto.client.CopyFilesDTO;
import com.conlect.oatos.dto.client.FileDTO;
import com.conlect.oatos.dto.client.FileThumbDTO;
import com.conlect.oatos.dto.client.FileThumbsDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.NetworkFilesDTO;
import com.conlect.oatos.dto.client.SaveFileDTO;
import com.conlect.oatos.dto.client.ShareFileNewDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO;
import com.conlect.oatos.dto.client.SharePersonalFileDTO;
import com.conlect.oatos.dto.client.UserIconDTO;
import com.conlect.oatos.dto.client.UserIconsDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.file.ImageUtil;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.oatos.filemanager.util.ConfigUtil;
import com.qycloud.oatos.filemanager.util.FileDESCComparator;
import com.qycloud.oatos.filemanager.util.FileProxy;
import com.qycloud.oatos.filemanager.util.ImageNameFilter;
import com.qycloud.oatos.filemanager.util.Logs;
/**
 * 文件逻辑服务
 * @author yang
 *
 */
@Service
public class FileLogic {
	
	private final static Logger logger = Logs.getLogger();

	/**
	 * 远程服务请求代理
	 */
	private static XhrProxy proxy = XhrProxy.getInstance();

	/**
	 * 获取文件流
	 * 
	 * @param response
	 * @param filePath
	 * @throws IOException
	 */
	public void getFileStream(HttpServletResponse response, String filePath)
			throws IOException {
		File file = new File(DiskUtil.ONLINEDISK_PATH, filePath);
		if (file.exists()) {
			FileUtil.copyStream(new FileInputStream(file),
					response.getOutputStream());
		}
	}

	/**
	 * 获取文件
	 * @param response
	 * @param postData
	 * @throws Exception
	 */
	public void getFile(HttpServletResponse response, FileDTO fileDTO) throws Exception {

		String filePath = DiskUtil.getFilePath(fileDTO);
		File f = new File(DiskUtil.ONLINEDISK_PATH, filePath);
		if (f.exists()) {
			// 当前版本
			FileUtil.copyStream(new FileInputStream(f), response.getOutputStream());
			response.setHeader(RESTurl.filePath, filePath);
		} else if (fileDTO.getFileId() != null) {
			// 历史版本
			filePath = DiskUtil.getHistoryFilePath(fileDTO);
			f = new File(DiskUtil.ONLINEDISK_PATH, filePath);
			FileUtil.copyStream(new FileInputStream(f), response.getOutputStream());
			response.setHeader(RESTurl.filePath, filePath);
		}
	}

	/**
	 * 获取文件路径
	 * @param postData
	 * @return
	 */
	public String getFilePath(FileDTO fileDTO) {

		String filePath = DiskUtil.getFilePath(fileDTO);
		File f = new File(DiskUtil.ONLINEDISK_PATH, filePath);
		if (f.exists()) {
			// 当前版本
		} else if (fileDTO.getFileId() != null) {
			// 历史版本
			filePath = DiskUtil.getHistoryFilePath(fileDTO);
		}
		return filePath;
	}

	/**
	 * 复制文件
	 * @param postData
	 * @return
	 */
	public String copyFiles(String postData) {
		CopyFilesDTO filesDTO = PojoMapper.fromJsonAsObject(postData, CopyFilesDTO.class);
		for (CopyFileDTO f : filesDTO.getFiles()) {
			File s = new File(DiskUtil.ONLINEDISK_PATH, f.getFromPath());
			File t = new File(DiskUtil.ONLINEDISK_PATH, f.getToPath());
			try {
				FileUtil.copyFile(s, t);
			} catch (Exception ex) {
				// TODO: handle exception
			}
		}
		return CommConstants.OK_MARK;
	}

	/**
	 * 移动文件
	 * @param postData
	 * @return
	 */
	public String moveFile(String postData) {
		CopyFileDTO fileDTO = PojoMapper.fromJsonAsObject(postData, CopyFileDTO.class);
		File s = new File(DiskUtil.ONLINEDISK_PATH, fileDTO.getFromPath());
		if (s.exists()) {
			File t = new File(DiskUtil.ONLINEDISK_PATH, fileDTO.getToPath());
			s.renameTo(t);
		}
		return CommConstants.OK_MARK;
	}

	/**
	 * 删除文件或目录
	 * @param files
	 * @return
	 */
	public String deleteFiles(String files) {
		String[] paths = files.split(",");
		for (String p : paths) {
			FileUtil.delete(DiskUtil.ONLINEDISK_PATH + p, 0);
		}
		return CommConstants.OK_MARK;
	}

	/**
	 * 另存文件
	 * @param postData
	 * @param token
	 * @return
	 */
	public String saveFileToDisk(String postData, String token) {
		SaveFileDTO fileDTO = PojoMapper.fromJsonAsObject(postData, SaveFileDTO.class);
		String result = null;
		String guidName = FileUtil.createFileGuidName(fileDTO.getGuid());
		fileDTO.setNewGuid(guidName);
		try {
			// 复制文件
			saveFileToDisk(fileDTO);
			
			// 添加数据库记录
			NetworkFileDTO file = new NetworkFileDTO();
			file.setUserId(fileDTO.getToId());
			file.setFolderId(fileDTO.getFolderId());
			file.setName(fileDTO.getName());
			file.setGuid(fileDTO.getNewGuid());
			file.setSize(fileDTO.getSize());
			file.setThumb(fileDTO.getThumb());
			file.setRemark(fileDTO.getRemark());
			
			String postJson = PojoMapper.toJson(file);
			result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.addPersonalFile), token, postJson);
			
			long fileId = Long.parseLong(result);
			FileDTO f = new FileDTO(file);
			f.setFileId(fileId);
			result = PojoMapper.toJson(f);
		}
		catch (Exception ex) {
			logger.error("", ex);
			result = ErrorType.error500.name();
		}
		return result;
	}

	/**
	 * 接收聊天文件
	 * @param postData
	 * @param token
	 * @return
	 */
	public String acceptFile(String postData, String token) {
		AcceptFileDTO fileDTO = PojoMapper.fromJsonAsObject(postData, AcceptFileDTO.class);
		String result = null;
		try {
			for (INetworkFileDTO f : fileDTO.getFileList()) {
				SaveFileDTO saveFileDTO = new SaveFileDTO(f);
				saveFileDTO.setFromId(f.getUserId());
				saveFileDTO.setToId(fileDTO.getUserId());
				saveFileDTO.setType(CommConstants.FILE_TYPE_ONLINEDISK);
				saveFileDTO.setToType(CommConstants.FILE_TYPE_ONLINEDISK);
				
				// 生成新的guid
				String saveName = FileUtil.createFileGuidName(f.getGuid());
				f.setGuid(saveName);
				saveFileDTO.setNewGuid(saveName);
				// 复制文件
				saveFileToDisk(saveFileDTO);
			}
			// 将文件记录插入数据库
			String postJson = PojoMapper.toJson(fileDTO);
			result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.acceptFile), token, postJson);
		}
		catch (Exception ex) {
			logger.error("", ex);
			result = ErrorType.error500.name();
		}

		return result;
	}

	/**
	 * 获取用户使用过的图像
	 * @param userId
	 * @return
	 */
	public String getUserIcons(String userId) {
		String result = null;
		List<String> pictures = new ArrayList<String>();
		
		// user icons
		String userIconPath = DiskUtil.ONLINEDISK_PATH + DiskUtil.getUserIconDir(Long.parseLong(userId));
		File userIconFolder = new File(userIconPath);
		File[] userIcons = userIconFolder.listFiles(new ImageNameFilter());
		
		String iconPath = DiskUtil.getUserIconDir(Long.parseLong(userId)).replace(DiskUtil.FILE_SEPARATOR, "/");
		if (userIcons != null) {
			
			Arrays.sort(userIcons, new FileDESCComparator());
			
			for (File userIcon : userIcons) {
				String filePath = iconPath + userIcon.getName();
				pictures.add(filePath);
			}
		}
		
		List<IUserIconDTO> iconList = new ArrayList<IUserIconDTO>();
		for (String path : pictures) {
			IUserIconDTO icon = new UserIconDTO();
			icon.setPath(path);
			icon.setUrl(path);
			
			iconList.add(icon);
		}
		
		UserIconsDTO userIconsDTO = new UserIconsDTO();
		userIconsDTO.setIconList(iconList);
		result = PojoMapper.toJson(userIconsDTO);
		return result;
	}

	/**
	 * 获取企业使用过的logo
	 * @param entId
	 * @return
	 */
	public String getEnterpriseLogos(String entId) {
		String result = null;
		List<String> pictures = new ArrayList<String>();
		
		// user icons
		String userIconPath = DiskUtil.getLogoDir(Long.parseLong(entId));
		File userIconFolder = new File(DiskUtil.ONLINEDISK_PATH + userIconPath);
		File[] userIcons = userIconFolder.listFiles(new ImageNameFilter());
		
		String iconPath = userIconPath.replace(DiskUtil.FILE_SEPARATOR, "/");
		if (userIcons != null) {
			
			Arrays.sort(userIcons, new FileDESCComparator());
			
			for (File userIcon : userIcons) {
				String filePath = iconPath + userIcon.getName();
				pictures.add(filePath);
			}
		}
		
		List<IUserIconDTO> iconList = new ArrayList<IUserIconDTO>();
		for (String path : pictures) {
			IUserIconDTO icon = new UserIconDTO();
			icon.setPath(path);
			icon.setUrl(path);
			
			iconList.add(icon);
		}
		
		UserIconsDTO userIconsDTO = new UserIconsDTO();
		userIconsDTO.setIconList(iconList);
		result = PojoMapper.toJson(userIconsDTO);
		return result;
	}

	/**
	 * 获取个人网盘图片的缩略图
	 * @param userId
	 * @param token
	 * @return
	 */
	public String getDiskImageThumbs(String userId, String token) {
		String result = null;
		String response = proxy.post(ConfigUtil.getServiceUrl(RESTurl.getFilesByUserId), token,
				String.valueOf(userId));
		NetworkFilesDTO filesDTO = PojoMapper.fromJsonAsObject(response, NetworkFilesDTO.class);
		List<NetworkFileDTO> imageList = new ArrayList<NetworkFileDTO>();
		if (filesDTO != null) {
			for (NetworkFileDTO fileModel : filesDTO.getNetworkFileDTOList()) {
				if (CommonUtil.isImage(fileModel.getGuid())) {
					imageList.add(fileModel);
				}
			}
		}
		
		List<IFileThumbDTO> thumbList = new ArrayList<IFileThumbDTO>();
		if (imageList.size() > 0) {
			
			for (INetworkFileDTO file : imageList) {
				String url = ImageUtil.createThumb(file.getUserId(), file.getGuid(), CommConstants.FILE_TYPE_ONLINEDISK);
				if (url != null) {
					IFileThumbDTO thumb = new FileThumbDTO();
					thumb.setUserId(file.getUserId());
					thumb.setFileName(file.getName());
					thumb.setGuidName(file.getGuid());
					thumb.setUrl(url);
					
					thumbList.add(thumb);
				}
			}
		}
		
		FileThumbsDTO thumbsDTO = new FileThumbsDTO();
		thumbsDTO.setThumbList(thumbList);
		result = PojoMapper.toJson(thumbsDTO);
		return result;
	}

	/**
	 * 将文件从个人网盘复制到企业网盘
	 * @param postData
	 * @param token
	 * @return
	 */
	public String sharePersonalFile(String postData, String token) {
		// 将文件从个人网盘复制到企业网盘
		SharePersonalFileDTO shareDTO = PojoMapper.fromJsonAsObject(postData, SharePersonalFileDTO.class);
		String result = null;
		List<String> savedFiles = new ArrayList<String>();
		ShareFileNewMessageDTO fileNewDTO = new ShareFileNewMessageDTO(shareDTO.getUserId(), shareDTO.getEnterpriseId());
		try {
			for (INetworkFileDTO f : shareDTO.getFileList()) {
				SaveFileDTO saveFileDTO = new SaveFileDTO(f);
				saveFileDTO.setFromId(f.getUserId());
				saveFileDTO.setToId(shareDTO.getEnterpriseId());
				saveFileDTO.setType(CommConstants.FILE_TYPE_ONLINEDISK);
				saveFileDTO.setToType(CommConstants.FILE_TYPE_SHAREDISK);
				
				// 生成新的guid
				String saveName = FileUtil.createFileGuidName(f.getGuid());
				f.setGuid(saveName);
				saveFileDTO.setNewGuid(saveName);
				// 保存文件
				saveFileToDisk(saveFileDTO);
				savedFiles.add(saveName);
				ShareFileNewDTO fileDTO = new ShareFileNewDTO(shareDTO.getFolderId(), f.getName(), saveName,
						ShareFileNewMessageDTO.Operation.ShareFile);
				fileNewDTO.getFiles().add(fileDTO);
			}
			// 插入数据库记录
			String postJson = PojoMapper.toJson(shareDTO);
			result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.sharePersonalFile), token, postJson);
		}
		catch (Exception ex) {
			logger.error("", ex);
			result = ErrorType.error500.name();
		}
		if (CommConstants.OK_MARK.equals(result)) {
			// send update version message
			FileProxy.sendShareFileNewMessage(token, fileNewDTO);
		}
		return result;
	}

	/**
	 * 另存文件
	 * @param fileDTO
	 * @throws Exception 
	 */
	private void saveFileToDisk(SaveFileDTO fileDTO) throws Exception {
		String fromPath = DiskUtil.getFilePath(fileDTO.getFromId(), fileDTO.getGuid(), fileDTO.getType());
		File f = new File(DiskUtil.ONLINEDISK_PATH, fromPath);
		if (!f.exists()) {
			fromPath = DiskUtil.getHistoryFilePath(fileDTO.getFromId(), fileDTO.getFileId(), fileDTO.getGuid(), fileDTO.getType());
			f = new File(DiskUtil.ONLINEDISK_PATH, fromPath);
		}
		
		String toPath = DiskUtil.getFilePath(fileDTO.getToId(), fileDTO.getNewGuid(), fileDTO.getToType());
		
		File saveFile = new File(DiskUtil.ONLINEDISK_PATH, toPath);
		FileUtil.copyFile(f, saveFile);
	}

}
