package com.qycloud.web.service.file;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.conlect.oatos.dto.autobean.IFileDTO;
import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.FileStatus;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.file.FileBeanUtils;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.file.ImageUtil;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.bean.jms.FileUploadJmsSender;
import com.qycloud.web.service.WebService;
import com.qycloud.web.service.support.FileHelper;
import com.qycloud.web.utils.ConfigUtil;

@Controller
public class FileRoutingUploadService {

	private static final XhrProxy HTTP = XhrProxy.getInstance();

	@Autowired
	@Qualifier("fileUploadJmsSender")
	private FileUploadJmsSender fileUploadJmsSender;

	@RequestMapping(WebService.FILE_ROUTING_UPLOAD)
	@ResponseBody
	public String execute(@RequestHeader(value = RESTurl.UserTokenkey, required = false) String token,
			@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws Exception {
		if (token == null)
			token = request.getParameter(RESTurl.UserTokenkey);
		FileUploadDTO uploadDTO = FileBeanUtils.parseUploadBean(request);
		uploadDTO.setToken(token);
		uploadDTO.setFileName(file.getOriginalFilename());
		uploadDTO.setGuidName(FileUtil.createFileGuidName(uploadDTO.getFileName()));
		uploadDTO.setFileSize(file.getFileItem().getSize());

		// 保存文件
		File targetFile = DiskUtil.getTargetFile(uploadDTO);
		if (!targetFile.exists()) {
			targetFile.createNewFile();
		}
		FileUtil.copyInputStreamToFile(file.getInputStream(), targetFile);

		IFileDTO fileDTO = isEntDisk(uploadDTO) ? uploadDTO.toEnterpriseFileDTO() : uploadDTO.toPrivateFileDTO();

		// 生成文件缩略图
		if (CommonUtil.isImage(uploadDTO.getFileName())) {
			createAndUploadThumb(uploadDTO, targetFile, fileDTO);
		}

		// 添加文件记录到数据库中
		String postResult = addFileRecord(uploadDTO, fileDTO);
		if (postResult.startsWith(CommConstants.ERROR_MARK)) {
			FileUtil.delete(targetFile, 0);
			return postResult;
		}
		long fileId = Long.parseLong(postResult);
		uploadDTO.setFileId(fileId);
		fileUploadJmsSender.send(PojoMapper.toJson(uploadDTO));
		return postResult;
	}

	/**
	 * 添加文件记录到数据库中
	 * 
	 * @param uploadDTO
	 * @param fileDTO
	 * @return
	 */
	private String addFileRecord(FileUploadDTO uploadDTO, IFileDTO fileDTO) {
		fileDTO.setFileStatus(FileStatus.UPLOAD_TO_CACHE);
		if (isEntDisk(uploadDTO)) {
			return HTTP.post(ConfigUtil.getServiceUrl(RESTurl.addShareFile), uploadDTO.getToken(),
					PojoMapper.toJson(fileDTO));
		} else {
			return HTTP.post(ConfigUtil.getServiceUrl(RESTurl.addPersonalFile), uploadDTO.getToken(),
					PojoMapper.toJson(fileDTO));
		}
	}

	public static boolean isEntDisk(FileUploadDTO uploadDTO) {
		return CommConstants.FILE_TYPE_SHAREDISK.equals(uploadDTO.getType());
	}

	/**
	 * 生成文件缩略图, 并上传到FileManager中
	 * 
	 * @param uploadDTO
	 * @param targetFile
	 * @param fileDTO
	 * @throws Exception
	 */
	private void createAndUploadThumb(FileUploadDTO uploadDTO, File targetFile, IFileDTO fileDTO) throws Exception {
		String thumbPath = DiskUtil.getThumbPath(fileDTO);
		fileDTO.setThumb(thumbPath);
		FileHelper.get().uploadFile(uploadDTO.getToken(), thumbPath, ImageUtil.createImageThumb(targetFile, thumbPath));
	}
}
