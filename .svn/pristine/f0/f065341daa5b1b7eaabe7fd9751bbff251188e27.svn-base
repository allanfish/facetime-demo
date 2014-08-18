package com.qycloud.oatos.filemanager.service;

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

import com.conlect.oatos.dto.client.FileDTO;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.ServerInnerUrl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.filemanager.domain.logic.FileLogic;
import com.qycloud.oatos.filemanager.util.Logs;

/**
 * 文件服务
 * @author yang
 *
 */
@Controller
public class FileService {
	
	private static final Logger logger = Logs.getLogger();
	
	@Autowired
	private FileLogic fileLogic;

	/**
	 * 获取文件的流
	 * 
	 * @param response
	 * @param filePath
	 * @throws IOException
	 */
	@RequestMapping(value = RESTurl.getFileStream, method = RequestMethod.POST)
	public void getFileStream(HttpServletResponse response,
			@RequestBody String filePath) {
		try {
			fileLogic.getFileStream(response, filePath);
		} catch (Exception ex) {
			logger.error(filePath, ex);
		}
	}

	@RequestMapping(value = ServerInnerUrl.getFile, method = RequestMethod.POST)
	public void getFile(HttpServletResponse response,
			@RequestBody String postData) {
		try {
			FileDTO fileDTO = PojoMapper.fromJsonAsObject(postData, FileDTO.class);
			fileLogic.getFile(response, fileDTO);
		} catch (Exception ex) {
			logger.error(postData, ex);
		}
	}

	@RequestMapping(value = ServerInnerUrl.getFilePath, method = RequestMethod.POST)
	public @ResponseBody String getFilePath(@RequestBody String postData) {
		String r = "";
		try {
			FileDTO fileDTO = PojoMapper.fromJsonAsObject(postData, FileDTO.class);
			r = fileLogic.getFilePath(fileDTO);
		} catch (Exception ex) {
			logger.error(postData, ex);
			r = ErrorType.error500.name();
		}
		return r;
	}

	@RequestMapping(value = ServerInnerUrl.copyFiles, method = RequestMethod.POST)
	public void copyFiles(@RequestBody String postData) {
		try {
			fileLogic.copyFiles(postData);
		} catch (Exception ex) {
			logger.error(postData, ex);
		}
	}

	@RequestMapping(value = ServerInnerUrl.moveFile, method = RequestMethod.POST)
	public void moveFile(@RequestBody String postData) {
		try {
			fileLogic.moveFile(postData);
		} catch (Exception ex) {
			logger.error(postData, ex);
		}
	}

	/**
	 * 删除文件
	 * @param request
	 * @param files
	 * @return
	 */
	@RequestMapping(value = ServerInnerUrl.deleteFiles, method = RequestMethod.POST)
	public @ResponseBody String deleteFiles(@RequestBody String files) {
		String r = "";
		try {
			r = fileLogic.deleteFiles(files);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(files, ex);
		}
		return r;
	}

	/**
	 * 另存文件
	 * @param postData
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.saveFileToDisk, method = RequestMethod.POST)
	public @ResponseBody String saveFileToDisk(@RequestBody  String postData, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileLogic.saveFileToDisk(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * 接收聊天文件，保存文件至个人网盘
	 * @param postData
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.acceptFile, method = RequestMethod.POST)
	public @ResponseBody String acceptFile(@RequestBody  String postData, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileLogic.acceptFile(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * 获取用户使用过的图像
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getUserIcons, method = RequestMethod.POST)
	public @ResponseBody String getUserIcons(@RequestBody  String userId) {
		String r = "";
		try {
			r = fileLogic.getUserIcons(userId);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(userId, ex);
		}
		return r;
	}

	/**
	 * 获取企业使用过的logo
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.getEnterpriseLogos, method = RequestMethod.POST)
	public @ResponseBody String getEnterpriseLogos(@RequestBody  String entId) {
		String r = "";
		try {
			r = fileLogic.getEnterpriseLogos(entId);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(entId, ex);
		}
		return r;
	}

	/**
	 * 获取个人网盘图片的缩略图
	 * @param postData
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.getDiskImageThumbs, method = RequestMethod.POST)
	public @ResponseBody String getDiskImageThumbs(@RequestBody  String userId, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileLogic.getDiskImageThumbs(userId, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(userId, ex);
		}
		return r;
	}

	/**
	 * 将文件从个人网盘复制到企业网盘
	 * @param postData
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.sharePersonalFile, method = RequestMethod.POST)
	public @ResponseBody String sharePersonalFile(@RequestBody  String postData, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileLogic.sharePersonalFile(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}
}
