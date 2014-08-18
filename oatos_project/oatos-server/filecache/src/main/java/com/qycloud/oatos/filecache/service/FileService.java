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
import com.qycloud.oatos.filecache.domain.logic.FileLogic;
import com.qycloud.oatos.filecache.util.Logs;

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
	 * 另存文件
	 * @param postData
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.saveFileToDisk, method = RequestMethod.POST)
	public @ResponseBody String saveFileToDisk(@RequestBody  String postData, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileLogic.service(RESTurl.saveFileToDisk, postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * html方式编辑后，保存文件内容
	 * @param postData
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.saveFile, method = RequestMethod.POST)
	public @ResponseBody String saveFile(@RequestBody  String postData, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileLogic.saveFile(postData, token);
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
			r = fileLogic.service(RESTurl.acceptFile, postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * 图片尺寸缩放
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.resizeUserPicture, method = RequestMethod.POST)
	public @ResponseBody String resizeUserPicture(@RequestBody  String postData, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileLogic.resizeUserPicture(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * 剪切图片，制作图像
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.cropUserPicture, method = RequestMethod.POST)
	public @ResponseBody String cropUserPicture(@RequestBody  String postData, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileLogic.cropUserPicture(postData, token);
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
	public @ResponseBody String getUserIcons(@RequestBody  String userId, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileLogic.service(RESTurl.getUserIcons, userId, token);
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
	public @ResponseBody String getEnterpriseLogos(@RequestBody  String entId, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileLogic.service(RESTurl.getEnterpriseLogos, entId, token);
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
			r = fileLogic.service(RESTurl.getDiskImageThumbs, userId, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(userId, ex);
		}
		return r;
	}

	/**
	 * 删除用户使用过的头像
	 * @param postData
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.deleteUserIcon, method = RequestMethod.POST)
	public @ResponseBody String deleteUserIcon(@RequestBody  String postData, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileLogic.deleteUserIcon(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * 删除用户，删除用户文件
	 * @param postData
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.deleteEnterpriseUsers, method = RequestMethod.POST)
	public @ResponseBody String deleteEnterpriseUsers(@RequestBody  String postData, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = fileLogic.deleteEnterpriseUsers(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
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
			r = fileLogic.service(RESTurl.sharePersonalFile, postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}
}
