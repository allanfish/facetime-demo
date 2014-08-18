package com.qycloud.oatos.filecache.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

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
import com.qycloud.oatos.filecache.domain.logic.FileDownloadLogic;
import com.qycloud.oatos.filecache.util.Logs;

/**
 * 文件下载服务
 * @author yang
 *
 */
@Controller
public class FileDownloadService {

	private static final Logger logger = Logs.getLogger();

	@Autowired
	private FileDownloadLogic fileDownloadLogic;

	/**
	 * 获取文件的流
	 * @param response
	 * @param filePath
	 * @throws IOException
	 */
	@RequestMapping(value = RESTurl.getFileStream, method = RequestMethod.POST)
	public void getFileStream(HttpServletResponse response, @RequestBody String filePath, @RequestHeader(RESTurl.UserTokenkey) String token) throws IOException {
		fileDownloadLogic.getFileStream(response, filePath, token);
	}

	/**
	 * 下载文件
	 * @param response
	 * @param postData
	 * @param token
	 * @throws IOException
	 */
	@RequestMapping(value = RESTurl.fileDownload, method = RequestMethod.POST)
	public void fileDownload(HttpServletResponse response, @RequestBody String postData, @RequestHeader(RESTurl.UserTokenkey) String token) throws Exception {
		fileDownloadLogic.fileDownload(response, postData, token);
	}

	/**
	 * 检查文件
	 * 
	 * 返回带有三个参数的字符串: 状态&大小byte&版本, 其中状态: 正常:0 , 删除:1 , 锁住:2 , 其他错误: 3
	 */
	@RequestMapping(value = RESTurl.checkDiskFile, method = RequestMethod.POST)
	@ResponseBody
	public String checkDiskFile(@RequestHeader(RESTurl.fileId) long fileId, @RequestHeader(RESTurl.type) String type, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String reBody = "";
		try {
			reBody = fileDownloadLogic.checkFile(fileId, type, token);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(fileId, ex);
		}
		return reBody;
	}

	/**
	 * 分段下载文件
	 * @param response
	 * @param postData
	 * @param token
	 * @throws IOException
	 */
	@RequestMapping(value = RESTurl.sectionFileDownload, method = RequestMethod.POST)
	public void sectionFileDownload(HttpServletResponse response, @RequestBody String postData, @RequestHeader(RESTurl.UserTokenkey) String token) throws Exception {
		fileDownloadLogic.sectionFileDownload(response, postData, token);
	}

	/**
	 * 获取媒体文件流
	 * @param response
	 * @param postData
	 * @throws IOException
	 */
	@RequestMapping(value = RESTurl.getMediaStream, method = RequestMethod.POST)
	public void getMediaStream(HttpServletResponse response, @RequestBody String postData, @RequestHeader(RESTurl.UserTokenkey) String token) throws Exception {
		fileDownloadLogic.getMediaStream(response, postData, token);
	}

	/**
	 * 压缩下载文件
	 * @param postData
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.downZipFile, method = RequestMethod.POST)
	public @ResponseBody String downZipFile(@RequestBody  String postData, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileDownloadLogic.downZipFile(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

}
