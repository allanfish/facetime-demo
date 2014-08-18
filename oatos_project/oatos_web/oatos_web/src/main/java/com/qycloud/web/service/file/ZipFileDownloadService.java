package com.qycloud.web.service.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.conlect.oatos.dto.client.ComplexFolderDTO;
import com.conlect.oatos.dto.client.DownFileDTO;
import com.conlect.oatos.dto.client.FileDTO;
import com.conlect.oatos.dto.client.ShareFileDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.file.FileBeanUtils;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.service.WebService;
import com.qycloud.web.utils.ConfigUtil;
import com.qycloud.web.utils.FileProxy;
import com.qycloud.web.utils.Zip;

@Controller
public class ZipFileDownloadService {

	private static final XhrProxy HTTP = XhrProxy.getInstance();

	@RequestMapping(WebService.ZIP_FILE_DOWNLOAD)
	public void execute(@RequestParam(RESTurl.UserTokenkey) String token, @RequestParam(RESTurl.fileId) long fileId,
			@RequestParam(RESTurl.type) String type, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String strShareFile = HTTP.post(ConfigUtil.getServiceUrl(RESTurl.getShareFileById), token, fileId + "");
		ShareFileDTO shareFile = PojoMapper.fromJsonAsObject(strShareFile, ShareFileDTO.class);

		File file = new File(DiskUtil.ONLINEDISK_PATH, DiskUtil.getFilePath(shareFile));

		response.setContentType("application/x-download; charset=UTF-8");
		response.setHeader("Content-Disposition",
				"attachment; filename=" + FileBeanUtils.getDownName(request, shareFile.getName(), type));
		response.setHeader("Content-Length", file.length() + "");
		FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());

		//		if (CommConstants.FILE_TYPE_SHAREDISK.equals(bean.getType())) {
		//			new FileRecordThread(bean.getFileId(), bean.getUserId(), MessageType.FileDown, token);
		//		}
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
		Zip.zip(tempPath + ".zip", tempPath);
		return zipPath;
	}

	/**
	 * 复制文件及文件夹到临时文件夹
	 * @param fromId
	 * @param tempDir
	 * @param folderDTO
	 * @param token
	 * @throws Exception
	 */
	private void copyDownFile(long fromId, String tempDir, ComplexFolderDTO folderDTO, String token) throws Exception {
		String myDir = tempDir + DiskUtil.FILE_SEPARATOR + new String(folderDTO.getName().getBytes(), "UTF-8");
		File dir = new File(myDir);
		if (!dir.mkdir()) {
			dir.mkdirs();
		}
		// copy files
		for (FileDTO f : folderDTO.getFiles()) {
			String filePath = downFile(fromId, f, token);
			String fn = new String(CommonUtil.getFilePrefixName(f.getName()).getBytes(), "UTF-8") + "."
					+ CommonUtil.getFileSuffixName(f.getName());
			File sourceFile = new File(DiskUtil.ONLINEDISK_PATH, filePath);
			File targetFile = new File(myDir, fn);
			FileUtil.copyFile(sourceFile, targetFile);
		}
		// copy folders
		for (ComplexFolderDTO f : folderDTO.getFolders()) {
			copyDownFile(fromId, myDir, f, token);
		}
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
}
