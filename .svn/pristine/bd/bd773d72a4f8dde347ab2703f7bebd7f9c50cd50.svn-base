package com.qycloud.oatos.filecache.service;

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
import com.qycloud.oatos.filecache.domain.logic.FileUploadLogic;
import com.qycloud.oatos.filecache.domain.logic.SectionFileUploadLogic;
import com.qycloud.oatos.filecache.domain.logic.SyncFileUploadLogic;
import com.qycloud.oatos.filecache.util.Logs;

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

	@Autowired
	private SectionFileUploadLogic sectionFileUploadLogic;

	@Autowired
	private SyncFileUploadLogic syncFileUploadLogic;

	/**
	 * 保存截图至个人网盘
	 * @param request
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = RESTurl.screenshot, method = RequestMethod.POST)
	public @ResponseBody String screenshot(HttpServletRequest request, @RequestHeader(RESTurl.userId) long userId, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = uploadLogic.screenshot(request, userId, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(userId, ex);
		}
		return r;
	}

	/**
	 * 拍照并保存
	 * @param request
	 * @param postJsonData
	 * @return
	 */
	@RequestMapping(value = RESTurl.tackPictureAndSave, method = RequestMethod.POST)
	public @ResponseBody String tackPictureAndSave(HttpServletRequest request, @RequestHeader(RESTurl.postJsonData) String postData, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = uploadLogic.tackPictureAndSave(request, postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * 拍照
	 * @param request
	 * @return
	 */
	@RequestMapping(value = RESTurl.tackPicture, method = RequestMethod.POST)
	public @ResponseBody String tackPicture(HttpServletRequest request) {
		String r = "";
		try {
			r = uploadLogic.tackPicture(request);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error("", ex);
		}
		return r;
	}

	/**
	 * 录音上传
	 * @param request
	 * @param param
	 * @return
	 */
	@RequestMapping(value = RESTurl.recordVoiceMail, method = RequestMethod.POST)
	public @ResponseBody String recordVoiceMail(HttpServletRequest request, @RequestHeader(RESTurl.postJsonData) String postData, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = uploadLogic.recordVoiceMail(request, postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * 文件上传
	 * @param request
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.fileUpload, method = RequestMethod.POST)
	public @ResponseBody String fileUpload(HttpServletRequest request, @RequestHeader(RESTurl.postJsonData) String postData, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = uploadLogic.fileUpload(request, postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * 文件分段上传
	 * 
	 * @param request
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.sectionFileUpload, method = RequestMethod.POST)
	public @ResponseBody
	String sectionFileUpload(HttpServletRequest request,
			@RequestHeader(RESTurl.postJsonData) String postData, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = sectionFileUploadLogic.sectionFileUpload(request, postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
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
			r = syncFileUploadLogic.syncFileUpload(request, postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}
}
