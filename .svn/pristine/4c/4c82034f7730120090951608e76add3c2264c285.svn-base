package com.qycloud.oatos.filecache.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.conlect.oatos.dto.client.ComplexFolderDTO;
import com.conlect.oatos.dto.client.DownFileDTO;
import com.conlect.oatos.dto.client.FileDTO;
import com.conlect.oatos.dto.client.FileDownloadDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.ShareFileDTO;
import com.conlect.oatos.dto.client.ViewFileDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.FilePermission;
import com.conlect.oatos.dto.status.MessageType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.conlect.oatos.utils.DateUtils;
import com.qycloud.oatos.filecache.util.ConfigUtil;
import com.qycloud.oatos.filecache.util.FileProxy;
import com.qycloud.oatos.filecache.util.FileRecordThread;

@Service
public class FileDownloadLogic {

	/**
	 * 远程服务请求代理
	 */
	private static XhrProxy proxy = XhrProxy.getInstance();

	/**
	 * 获取文件流
	 * @param response
	 * @param filePath
	 * @throws IOException
	 */
	public void getFileStream(HttpServletResponse response, String filePath, String token) throws IOException {
		File file = FileProxy.getFile(filePath, token);
		if (file.exists()) {
			FileUtil.copyStream(new FileInputStream(file), response.getOutputStream());
		}
	}
	
	/**
	 * 下载文件
	 * @param response
	 * @param postData
	 * @param token
	 * @throws IOException
	 */
	public void fileDownload(HttpServletResponse response, String postData, String token) throws Exception {
		FileDownloadDTO bean = PojoMapper.fromJsonAsObject(postData, FileDownloadDTO.class);

		if (CommConstants.FILE_TYPE_SHAREDISK.equals(bean.getType()) && bean.isCheckDownPermission()) {
			String check = FileProxy.checkPermission(bean.getUserId(), bean.getFolderId(), FilePermission.Download, token);
			if (!CommConstants.OK_MARK.equals(check)) {
				// no permission
				return;
			}
		}
		
		File file = null;
		if (CommConstants.FILE_TYPE_ZIP_DOWN.equals(bean.getType())) {
			file = new File(DiskUtil.ONLINEDISK_PATH, bean.getFileName());
		} else {
			FileDTO fileDTO = toFileDTO(bean);
			String filePath = FileProxy.getFilePath(fileDTO, token);
			file = new File(DiskUtil.ONLINEDISK_PATH, filePath);

			if (CommonUtil.isOatw(bean.getFileName())) {
				long fromId;
				if (CommConstants.FILE_TYPE_SHAREDISK.equals(bean.getType())
						|| CommConstants.FILE_TYPE_CONFERENCE_DOC.equals(bean.getType())) {
					fromId = bean.getEntId();
				} else {
					fromId = bean.getUserId();
				}
				String docGuid = CommonUtil.getFilePrefixName(fileDTO.getName()) + "." + CommConstants.FILE_FORMAT_DOC;
				String docPath = DiskUtil.getFilePath(fromId, docGuid, fileDTO.getType());
				File docFile = new File(DiskUtil.ONLINEDISK_PATH, docPath);
				if (docFile.exists()) {
					file = new File(DiskUtil.ONLINEDISK_PATH, docPath);
				}
			}
		}
		
		FileUtil.copyStream(new FileInputStream(file), response.getOutputStream());
		
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(bean.getType())) {
			new FileRecordThread(bean.getFileId(), bean.getUserId(), MessageType.FileDown, token);
		}
	}

	/**
	 * 
	 * @param bean
	 * @return
	 */
	private FileDTO toFileDTO(FileDownloadDTO bean) {
		FileDTO fileDTO = new FileDTO();
		fileDTO.setFileId(bean.getFileId());
		if (bean.getUserId() != null) {
			fileDTO.setUserId(bean.getUserId());
		}
		if (bean.getEntId() != null) {
			fileDTO.setEnterpriseId(bean.getEntId());
		}
		fileDTO.setFolderId(bean.getFolderId());
		fileDTO.setGuid(bean.getGuidName());
		fileDTO.setType(bean.getType());
		return fileDTO;
	}

	/**
	 * 检查文件
	 * @param fileId
	 * @param type
	 * @return
	 * @throws IOException 
	 */
	public String checkFile(long fileId, String type, String token) throws Exception {
		String result = "";
		if (CommConstants.FILE_TYPE_ONLINEDISK.equals(type)) {
			String r = proxy.post(ConfigUtil.getServiceUrl(RESTurl.getFileById), token, String.valueOf(fileId));
			NetworkFileDTO fileDTO = PojoMapper.fromJsonAsObject(r, NetworkFileDTO.class);
			String filePath = FileProxy.getFilePath(fileDTO, token);
			result = fileDTO.getDeleted() == 1 ? "1" : "0";
			File f = new File(DiskUtil.ONLINEDISK_PATH, filePath);
			result = result + "&" + f.length() + "&"+ DateUtils.date2str(fileDTO.getUpdateTime(), DateUtils.DATE_TIME_FORMAT_STR2);
		}
		else if (CommConstants.FILE_TYPE_SHAREDISK.equals(type)) {
			String r = proxy.post(ConfigUtil.getServiceUrl(RESTurl.getShareFileById), token, String.valueOf(fileId));
			ShareFileDTO fileDTO = PojoMapper.fromJsonAsObject(r, ShareFileDTO.class);
			String filePath = FileProxy.getFilePath(fileDTO, token);
			result = fileDTO.getDeleted() == 1 ? "1" : "0";
			File f = new File(DiskUtil.ONLINEDISK_PATH, filePath);
			result = result + "&" + f.length() + "&"+ DateUtils.date2str(fileDTO.getUpdateTime(), DateUtils.DATE_TIME_FORMAT_STR2);
		}
		return result;
	}

	/**
	 * 文件分段下载
	 * @param response
	 * @param postData
	 * @param token
	 * @throws IOException
	 */
	public void sectionFileDownload(HttpServletResponse response, String postData, String token) throws Exception {
		FileDownloadDTO bean = PojoMapper.fromJsonAsObject(postData, FileDownloadDTO.class);
		FileDTO fileDTO = toFileDTO(bean);
		String filePath = FileProxy.getFilePath(fileDTO, token);
		File f = new File(DiskUtil.ONLINEDISK_PATH, filePath);
		FileUtil.copyStream(new FileInputStream(f), response.getOutputStream(), bean.getStart(), bean.getEnd() - bean.getStart());
	}
	
	/**
	 * 获取媒体文件流
	 * @param response
	 * @param postData
	 * @throws IOException
	 */
	public void getMediaStream(HttpServletResponse response, String postData, String token) throws Exception {
		ViewFileDTO fileDTO = PojoMapper.fromJsonAsObject(postData, ViewFileDTO.class);
		String filePath = FileProxy.getFilePath(fileDTO, token);
		File f = new File(DiskUtil.ONLINEDISK_PATH, filePath);
		FileInputStream in = new FileInputStream(f);
		FileUtil.copyStream(in, response.getOutputStream());
	}

	/**
	 * 压缩下载文件
	 * @param postData
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public String downZipFile(String postData, String token) throws Exception {
		DownFileDTO downDTO = PojoMapper.fromJsonAsObject(postData, DownFileDTO.class);
		String result = downZipFile(downDTO, token);
		return result;
	}

	/**
	 * 压缩下载文件
	 * @param downDTO
	 * @param token
	 * @return
	 * @throws Exception
	 */
	private String downZipFile(DownFileDTO downDTO, String token) throws Exception {
		long fromId = downDTO.getUserId();
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(downDTO.getType())) {
			fromId = downDTO.getEnterpriseId();
		}
		// make top dir
		String folderName = UUID.randomUUID().toString();
		if (downDTO.getFolder() != null) {
			folderName = downDTO.getFolder().getName();
		}
		folderName = folderName.replace(" ", "-");
		String tempDir = DiskUtil.DISK_TEMP_DIR + DiskUtil.FILE_SEPARATOR + UUID.randomUUID().toString().toLowerCase()
				+ DiskUtil.FILE_SEPARATOR + folderName;
		String tempPath = DiskUtil.ONLINEDISK_PATH + tempDir;
		File dir = new File(tempPath);
		if (!dir.mkdir()) {
			dir.mkdirs();
		}

		for (FileDTO f : downDTO.getFileList()) {
			String filePath = downFile(fromId, f, token);
			String fn = CommonUtil.getFilePrefixName(f.getName()) + "." + CommonUtil.getFileSuffixName(f.getName());
			File sourceFile = new File(DiskUtil.ONLINEDISK_PATH, filePath);
			File targetFile = new File(tempPath, fn);
			FileUtil.copyFile(sourceFile, targetFile);
		}
		for (ComplexFolderDTO f : downDTO.getFolderList()) {
			copyDownFile(fromId, tempPath, f, token);
		}
		
		String zipPath = tempDir + ".zip";
//		Zip.zip(DiskUtil.ONLINEDISK_PATH + zipPath, tempPath);
		return zipPath;
	}

	/**
	 * 获取文件
	 * @param fromId
	 * @param fileDTO
	 * @param token
	 * @return
	 * @throws Exception
	 */
	private String downFile(long fromId, FileDTO fileDTO, String token) throws Exception {
		String filePath = FileProxy.getFilePath(fileDTO, token);
		if (CommonUtil.isOatw(fileDTO.getName())) {
			String docGuid = CommonUtil.getFilePrefixName(fileDTO.getName()) + "." + CommConstants.FILE_FORMAT_DOC;
			String docPath = DiskUtil.getFilePath(fromId, docGuid, fileDTO.getType());
			File docFile = new File(DiskUtil.ONLINEDISK_PATH, docPath);
			if (docFile.exists()) {
				filePath = docPath;
			}
		}
		return filePath;
	}

	/**
	 * 复制文件及文件夹到临时文件夹
	 * @param fromId
	 * @param pdir
	 * @param folderDTO
	 * @param token
	 * @throws Exception
	 */
	private void copyDownFile(long fromId, String pdir, ComplexFolderDTO folderDTO, String token) throws Exception {
		String myDir = pdir + DiskUtil.FILE_SEPARATOR + new String(folderDTO.getName().getBytes(), "UTF-8");
		File dir = new File(myDir);
		if (!dir.mkdir()) {
			dir.mkdirs();
		}
		// copy files
		for (FileDTO f : folderDTO.getFiles()) {
			String fp = downFile(fromId, f, token);
			String fn = new String(CommonUtil.getFilePrefixName(f.getName()).getBytes(), "UTF-8") + "."
					+ CommonUtil.getFileSuffixName(fp);
			File sourceFile = new File(DiskUtil.ONLINEDISK_PATH, fp);
			File targetFile = new File(myDir, fn);
			FileUtil.copyFile(sourceFile, targetFile);
		}
		// copy folders
		for (ComplexFolderDTO f : folderDTO.getFolders()) {
			copyDownFile(fromId, myDir, f, token);
		}
	}

}
