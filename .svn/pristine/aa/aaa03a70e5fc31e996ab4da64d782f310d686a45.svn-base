package com.qycloud.oatos.filecache.logic;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.conlect.oatos.dto.autobean.IUserIconDTO;
import com.conlect.oatos.dto.client.FileDTO;
import com.conlect.oatos.dto.client.ImageDTO;
import com.conlect.oatos.dto.client.SaveFileDTO;
import com.conlect.oatos.dto.client.ShareFileNewDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO;
import com.conlect.oatos.dto.client.UserIconDTO;
import com.conlect.oatos.dto.client.UserInfoDTO;
import com.conlect.oatos.dto.client.UserInfosDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.file.ImageUtil;
import com.conlect.oatos.file.ServerInnerUrl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.oatos.filecache.util.ConfigUtil;
import com.qycloud.oatos.filecache.util.FileProxy;
import com.qycloud.oatos.filecache.util.Logs;

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
	 * 调用文件服务器的服务
	 * @param restUrl
	 * @param postData
	 * @param token
	 * @return
	 */
	public String service(String restUrl, String postData, String token) {
		StringBuilder url = new StringBuilder(ConfigUtil.getValue(MyConst.FileManagerService));
		url.append(restUrl);

		return proxy.post(url.toString(), token, postData);
	}

	/**
	 * 保存文件修改内容
	 * @param postData
	 * @param token
	 * @return
	 */
	public String saveFile(String postData, String token) {
		SaveFileDTO fileDTO = PojoMapper.fromJsonAsObject(postData, SaveFileDTO.class);
		String result = null;
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getToType())) {
			result = saveShareFile(fileDTO, token);
		} else if (CommConstants.FILE_TYPE_ONLINEDISK.equals(fileDTO.getToType())) {
			result = savePersonalFile(fileDTO, token);
		}
		return result;
	}

	/**
	 * 图片尺寸缩放
	 * @param postData
	 * @return
	 * @throws Exception 
	 */
	public String resizeUserPicture(String postData, String token) throws Exception {
		ImageDTO imageDTO = PojoMapper.fromJsonAsObject(postData, ImageDTO.class);
		
		String path = DiskUtil.DISK_TEMP_DIR + imageDTO.getName();
		if (CommConstants.FILE_TYPE_ONLINEDISK.equals(imageDTO.getType())) {
			path = DiskUtil.getPersonalDiskDir(imageDTO.getUserId()) + imageDTO.getName();
			FileDTO fileDTO = new FileDTO();
			fileDTO.setUserId(imageDTO.getUserId());
			fileDTO.setGuid(imageDTO.getName());
			fileDTO.setType(CommConstants.FILE_TYPE_ONLINEDISK);
			path = FileProxy.getFilePath(fileDTO, token);
		}
		// 源文件
		File srcFile = new File(DiskUtil.ONLINEDISK_PATH, path);
		// 最大长宽尺寸
		float imageMaxSize = imageDTO.getWidth() * 0.75F;
		float resizeRatio = 1.0F;
		if (imageDTO.getWidth() > imageDTO.getHeight()) {
			imageMaxSize = imageDTO.getHeight() * 0.75F;
		}
		// 实际尺寸
		BufferedImage src = ImageIO.read(srcFile);
		int realWidth = src.getWidth();
		int realHeight = src.getHeight();

		// 缩放率
		if (realWidth > realHeight) {
			resizeRatio = imageMaxSize / realWidth;
		} else {
			resizeRatio = imageMaxSize / realHeight;
		}
		// 缩放后尺寸
		int resizeWidth = (int) (realWidth * resizeRatio);
		int resizeHeight = (int) (realHeight * resizeRatio);
		// 缩放后文件
		File dir = new File(DiskUtil.ONLINEDISK_PATH,
				DiskUtil.DISK_TEMP_DIR);
		if (!(dir.mkdir())) {
			dir.mkdirs();
		}
		String newName = UUID.randomUUID().toString().toLowerCase() + "."
				+ CommConstants.FILE_FORMAT_PNG;
		String newPath = DiskUtil.DISK_TEMP_DIR + newName;
		File resizeFile = new File(dir, newName);
		
		// 缩放图片
		ImageUtil.resizeImage(srcFile, resizeFile, resizeWidth, resizeHeight);

		imageDTO.name = newName;
		imageDTO.type = CommConstants.FILE_TYPE_TEMP;
		imageDTO.url = newPath;
		imageDTO.width = resizeWidth;
		imageDTO.height = resizeHeight;
		
		return PojoMapper.toJson(imageDTO);
	}

	/**
	 * 剪切图片
	 * @param postData
	 * @return
	 * @throws IOException 
	 */
	public String cropUserPicture(String postData, String token) throws IOException {
		ImageDTO imageDTO = PojoMapper.fromJsonAsObject(postData, ImageDTO.class);
		// 源文件
		String path = DiskUtil.DISK_TEMP_DIR + imageDTO.getName();
		File srcFile = new File(DiskUtil.ONLINEDISK_PATH, path);

		BufferedImage src = ImageIO.read(srcFile);

		float imageResizeRatio = ((float) ImageUtil.IMAGE_SIZE_96) / imageDTO.getWidth();

		// 缩放图片
		int resizeWidth = (int) (src.getWidth() * imageResizeRatio);
		int resizeHeight = (int) (src.getHeight() * imageResizeRatio);

		BufferedImage resizeImage = new BufferedImage(resizeWidth,
				resizeHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizeImage.createGraphics();

		// 透明背景
		resizeImage = g.getDeviceConfiguration().createCompatibleImage(
				resizeWidth, resizeHeight, Transparency.TRANSLUCENT);
		g.dispose();
		g = resizeImage.createGraphics();

		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(src, 0, 0, resizeWidth, resizeHeight, null);
		g.dispose();

		// 剪切图片
		int x = (int) (imageDTO.getCropStartX() * imageResizeRatio);
		int y = (int) (imageDTO.getCropStartY() * imageResizeRatio);
		BufferedImage subImage = resizeImage.getSubimage(x, y,
				ImageUtil.IMAGE_SIZE_96, ImageUtil.IMAGE_SIZE_96);

		// 剪切后文件
		File dir = new File(DiskUtil.ONLINEDISK_PATH, DiskUtil.getUserIconDir(imageDTO.getUserId()));
		if (!(dir.mkdir())) {
			dir.mkdirs();
		}
		String newName = UUID.randomUUID() + "_96." + CommConstants.FILE_FORMAT_PNG;
		File cropedFile = new File(dir, newName);
		ImageIO.write(subImage, CommConstants.FILE_FORMAT_PNG, cropedFile);

		String p = DiskUtil.getUserIconDir(imageDTO.getUserId()) + newName;
		
		FileUploadUtil.upload(p, CommConstants.FILE_TYPE_ICON, token);

		IUserIconDTO icon = new UserIconDTO();
		icon.setPath(p);
		icon.setUrl(p);
		return PojoMapper.toJson(icon);
	}

	/**
	 * 删除用户使用过的头像
	 * @param postData
	 * @param token
	 * @return
	 */
	public String deleteUserIcon(String postData, String token) {
		UserIconDTO iconDTO = PojoMapper.fromJsonAsObject(postData, UserIconDTO.class);
		String file = DiskUtil.ONLINEDISK_PATH + iconDTO.getPath();
		FileUtil.delete(file, 0);
		FileProxy.delete(iconDTO.getPath(), token);
		return CommConstants.OK_MARK;
	}

	/**
	 * 删除用户
	 * @param postData
	 * @param token
	 * @return
	 */
	public String deleteEnterpriseUsers(String postData, String token) {
		UserInfosDTO usersDTO = PojoMapper.fromJsonAsObject(postData, UserInfosDTO.class);
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.deleteEnterpriseUsers), token,
				PojoMapper.toJson(usersDTO));
		if (CommConstants.OK_MARK.equals(result)) {
			StringBuilder sb = new StringBuilder();
			for (UserInfoDTO u : usersDTO.getUserInfoDTOList()) {
				String userDir = DiskUtil.getPersonalDiskDir(u.getUserId());
				FileUtil.delete(new File(DiskUtil.ONLINEDISK_PATH, userDir), 0);
				
				sb.append(userDir);
				sb.append(",");
			}
			FileProxy.delete(sb.toString(), token);
		}
		return result;
	}
	
	/**
	 *  个人网盘保存html
	 * @param postData
	 * @param token
	 * @return
	 */
	private String savePersonalFile(SaveFileDTO fileDTO, String token) {
		String result = null;
		boolean isNew = false;
		try {
			String postData = PojoMapper.toJson(fileDTO);
			result = proxy.post(ConfigUtil.getServiceUrl(ServerInnerUrl.checkSavePersonalFile), token, postData);
			if (result != null && !result.startsWith(CommConstants.ERROR_MARK)) {
				FileDTO fDTO = PojoMapper.fromJsonAsObject(result, FileDTO.class);
				fileDTO.setName(CommonUtil.getHTMLEditNewName(fileDTO.getName()));
				fileDTO.setNewGuid(FileUtil.createFileGuidName(fileDTO.getName()));
				fileDTO.setFileId(fDTO.getFileId());
				if (CommConstants.FILE_NEW.equals(fDTO.getType())) {
					isNew = true;
				}
				// save to disk
				long size = saveFile(fileDTO, isNew, token);
				fileDTO.setSize(size);
				if (CommConstants.FILE_NEW.equals(fDTO.getType())) {
					fileDTO.setType(CommConstants.FILE_NEW);
				}
				// update file to db
				String postJson = PojoMapper.toJson(fileDTO);
				result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.savePersonalFile), token, postJson);
			}
		}
		catch (Exception ex) {
			logger.error("", ex);
			result = ErrorType.error500.name();
		}
		return result;
	}

	/**
	 * 企业网盘保存html
	 * @param fileDTO
	 * @param token
	 * @return
	 */
	private String saveShareFile(SaveFileDTO fileDTO, String token) {
		String result = null;
		boolean isNew = false;
		ShareFileNewMessageDTO.Operation operation = ShareFileNewMessageDTO.Operation.EditFile;
		try {
			String postData = PojoMapper.toJson(fileDTO);
			result = proxy.post(ConfigUtil.getServiceUrl(ServerInnerUrl.checkSaveShareFile), token, postData);
			if (result != null && !result.startsWith(CommConstants.ERROR_MARK)) {
				FileDTO fDTO = PojoMapper.fromJsonAsObject(result, FileDTO.class);
				fileDTO.setName(CommonUtil.getHTMLEditNewName(fileDTO.getName()));
				fileDTO.setNewGuid(FileUtil.createFileGuidName(fileDTO.getName()));
				fileDTO.setFileId(fDTO.getFileId());
				if (CommConstants.FILE_NEW.equals(fDTO.getType())) {
					isNew = true;
				}
				// save to disk
				long size = saveFile(fileDTO, isNew, token);
				fileDTO.setSize(size);
				if (CommConstants.FILE_NEW.equals(fDTO.getType())) {
					fileDTO.setType(CommConstants.FILE_NEW);
					operation = ShareFileNewMessageDTO.Operation.UploadFile;
				}
				// update file to db
				String postJson = PojoMapper.toJson(fileDTO);
				result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.saveShareFile), token, postJson);
			}
		}
		catch (Exception ex) {
			logger.error("", ex);
			result = ErrorType.error500.name();
		}
		if (result != null && !result.startsWith(CommConstants.ERROR_MARK)) {
			// send update version message
			ShareFileNewMessageDTO fileNewDTO = new ShareFileNewMessageDTO(fileDTO.getUserId(),
					fileDTO.getToId());
			ShareFileNewDTO newFileDTO = new ShareFileNewDTO(fileDTO.getFolderId(), fileDTO.getName(),
					fileDTO.getNewGuid(), operation);
			if (!ShareFileNewMessageDTO.Operation.UploadFile.equals(operation)) {
				newFileDTO.setFileId(fileDTO.getFileId());
			}
			fileNewDTO.getFiles().add(newFileDTO);
			FileProxy.sendShareFileNewMessage(token, fileNewDTO);
		}
		return result;
	}

	/**
	 * 保存编辑的文件
	 * @param fileDTO
	 * @param isNew
	 * @return
	 * @throws Exception
	 */
	private long saveFile(SaveFileDTO fileDTO, boolean isNew, String token) throws Exception {
		String htmlPath = DiskUtil.getHtmlFilePath(fileDTO.getToId(), fileDTO.getNewGuid(), fileDTO.getToType());
		if (CommonUtil.isHtml(fileDTO.getNewGuid())) {
			htmlPath = DiskUtil.getFilePath(fileDTO.getToId(), fileDTO.getNewGuid(), fileDTO.getToType());
		}
		File htmlFile = new File(DiskUtil.ONLINEDISK_PATH, htmlPath);

		String content = CommonUtil.buildHtml(fileDTO.getContent());
		
		// 新建文件
		if (!htmlFile.exists()) {
			htmlFile.createNewFile();
		}
		// 写入内容
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(htmlFile), CommConstants.CHARSET_UTF_8);
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(content);
		bw.close();

//		if (CommonUtil.isEditAsHtmlSupported(fileDTO.getNewGuid())) {
//			// 将html转成原文件
//			String filePath = DiskUtil.getFilePath(fileDTO.getToId(), fileDTO.getNewGuid(), fileDTO.getToType());
//			if (CommonUtil.isOatw(fileDTO.getName())) {
//				String docGuid = CommonUtil.getFilePrefixName(fileDTO.getNewGuid()) + "." + CommConstants.FILE_FORMAT_DOC;
//				filePath = DiskUtil.getFilePath(fileDTO.getToId(), docGuid, fileDTO.getToType());
//			}
//			DocConvertDTO docDTO = new DocConvertDTO(htmlPath, filePath);
//			docDTO.setSaveTarget(true);
//			docDTO.setPriorty(DocConvertPriority.SAVE_HTML);
//			DocConverter.convert(docDTO, token);
//		}
		// 保存html至文件服务器
		FileUploadUtil.upload(htmlPath, fileDTO.getToType(), token);

		if (!isNew) {
			// 移动原文件到历史目录
			File targetFile = new File(DiskUtil.ONLINEDISK_PATH, DiskUtil.getFilePath(fileDTO.getFromId(), fileDTO.getGuid(), fileDTO.getToType()));
			if (targetFile.exists()) {
				String historyFilePath = DiskUtil.getHistoryFilePath(fileDTO.getFromId(), fileDTO.getFileId(), fileDTO.getGuid(), fileDTO.getToType());
				File historyFile = new File(DiskUtil.ONLINEDISK_PATH, historyFilePath);
				targetFile.renameTo(historyFile);
			}
		}
		return CommonUtil.getFileSizeKb(htmlFile.length());
	}

}
