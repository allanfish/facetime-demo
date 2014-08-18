package com.qycloud.oatos.filecache.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.qycloud.oatos.filecache.logic.FileViewLogic;
import com.qycloud.oatos.filecache.util.Logs;

/**
 * 文件浏览服务
 * @author yang
 *
 */
@Controller
public class FileViewService {

	private static final Logger logger = Logs.getLogger();

	@Autowired
	private FileViewLogic fileViewLogic;

	/**
	 * 浏览图片，取图片尺寸及地址
	 * @param postData
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.viewFileAsImage, method = RequestMethod.POST)
	public @ResponseBody
	String viewFileAsImage(@RequestBody String postData,
			@RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileViewLogic.viewFileAsImage(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * 将文件转成html，浏览文件
	 * @param postData
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.viewFileAsHtml, method = RequestMethod.POST)
	public @ResponseBody
	String viewFileAsHtml(@RequestBody String postData,
			@RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileViewLogic.viewFileAsHtml(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * 将文件转成pdf，浏览文件
	 * @param postData
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.viewFileAsPdf, method = RequestMethod.POST)
	public @ResponseBody
	String viewFileAsPdf(@RequestBody String postData,
			@RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileViewLogic.viewFileAsPdf(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * 将文档转成swf，浏览文件
	 * @param postData
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.viewFileAsSwf, method = RequestMethod.POST)
	public @ResponseBody
	String viewFileAsSwf(@RequestBody String postData,
			@RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileViewLogic.viewFileAsSwf(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * 编辑文件，将文件转成html
	 * @param postData
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.editFileAsHtml, method = RequestMethod.POST)
	public @ResponseBody
	String editFileAsHtml(@RequestBody String postData,
			@RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileViewLogic.editFileAsHtml(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

}
