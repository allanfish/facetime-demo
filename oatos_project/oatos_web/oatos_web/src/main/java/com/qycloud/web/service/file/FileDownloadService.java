package com.qycloud.web.service.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.conlect.oatos.dto.client.FileDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.ShareFileDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.file.FileBeanUtils;
import com.conlect.oatos.file.ServerInnerUrl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.service.WebService;
import com.qycloud.web.utils.ConfigUtil;

@Controller
public class FileDownloadService {

	private static final XhrProxy HTTP = XhrProxy.getInstance();

	@RequestMapping(WebService.FILE_DOWNLOAD)
	public void execute(@RequestParam(RESTurl.UserTokenkey) String token, @RequestParam(RESTurl.fileId) long fileId,
			@RequestParam(RESTurl.type) String type, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FileDTO fileDTO = this.getFileDTO(token, type, fileId);
		File file = new File(DiskUtil.ONLINEDISK_PATH, DiskUtil.getFilePath(fileDTO));
		if (!file.exists()) {
			downloadFileFromFM(file, token, fileDTO);
		}

		response.setContentType("application/x-download; charset=UTF-8");
		response.setHeader("Content-Disposition",
				"attachment; filename=" + FileBeanUtils.getDownName(request, fileDTO.getName(), type));
		response.setHeader("Content-Length", file.length() + "");
		FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());

		//		if (CommConstants.FILE_TYPE_SHAREDISK.equals(bean.getType())) {
		//			new FileRecordThread(bean.getFileId(), bean.getUserId(), MessageType.FileDown, token);
		//		}
	}

	private FileDTO getFileDTO(String token, String type, long fileId) {
		if (this.isEntDisk(type)) {
			String strShareFile = HTTP.post(ConfigUtil.getServiceUrl(RESTurl.getShareFileById), token, fileId + "");
			ShareFileDTO shareFile = PojoMapper.fromJsonAsObject(strShareFile, ShareFileDTO.class);
			return new FileDTO(shareFile);
		} else {
			String strFile = HTTP.post(ConfigUtil.getServiceUrl(RESTurl.getFileById), token, fileId + "");
			return new FileDTO(PojoMapper.fromJsonAsObject(strFile, NetworkFileDTO.class));
		}

	}

	private boolean isEntDisk(String type) {
		return CommConstants.FILE_TYPE_SHAREDISK.equals(type);
	}

	/**
	 * 远程去取文件在fileManager中的文件
	 * @param cacheFile
	 * @param token
	 * @param shareFile
	 * @throws Exception
	 */
	private void downloadFileFromFM(File cacheFile, String token, FileDTO fileDTO) throws Exception {
		StringBuilder postURL1 = new StringBuilder(ConfigUtil.getValue(MyConst.FileManagerService));
		postURL1.append(ServerInnerUrl.getFile);

		Map<String, String> headers = new HashMap<String, String>();
		headers.put(RESTurl.UserTokenkey, token);
		HttpEntity postEntity = new StringEntity(PojoMapper.toJson(fileDTO), CommConstants.CHARSET_UTF_8);
		HttpResponse response = HTTP.postForResponse(postURL1.toString(), postEntity, headers);

		//在fileCache中拷贝一份文件
		cacheFile.createNewFile();
		FileCopyUtils.copy(response.getEntity().getContent(), new FileOutputStream(cacheFile));
	}
}
