package com.qycloud.oatos.filemanager.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.qycloud.oatos.filemanager.domain.logic.FileUploadLogic;
import com.qycloud.oatos.filemanager.util.Logs;

/**
 * 文件上传服务
 * @author yang
 *
 */
@Controller
public class FileUploadService {

	private static final Logger logger = Logs.getLogger();

	@Autowired
	private FileUploadLogic uploadLogic;

	/**
	 * 文件上传
	 * @param request
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.fileUpload, method = RequestMethod.POST)
	public @ResponseBody String fileUpload(HttpServletRequest request, @RequestHeader(RESTurl.filePath) String filePath) {
		String r = "";
		try {
			r = uploadLogic.fileUpload(request, filePath);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(filePath, ex);
		}
		return r;
	}

	/**
	 * 网盘文件同步上传
	 * 
	 * @param request
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.syncFileUpload, method = RequestMethod.POST)
	public @ResponseBody
	String syncFileUpload(HttpServletRequest request,
			@RequestHeader(RESTurl.postJsonData) String postData, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = uploadLogic.syncFileUpload(request, postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

}
