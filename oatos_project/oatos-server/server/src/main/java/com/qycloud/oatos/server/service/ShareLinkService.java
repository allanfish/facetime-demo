package com.qycloud.oatos.server.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.ShareLinkDTO;
import com.conlect.oatos.dto.client.ShareLinkInfoDTO;
import com.conlect.oatos.dto.client.ShareLinkMailDTO;
import com.conlect.oatos.dto.client.ShareLinksDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.logic.ShareLinkLogic;
import com.qycloud.oatos.server.util.BllLogger;
import com.qycloud.oatos.server.util.LogicException;

/**
 * 外链服务
 * @author yang
 *
 */
@Controller("ShareLinkService")
public class ShareLinkService {

	private final static Logger logger = BllLogger.getLogger();

	@Autowired
	private ShareLinkLogic shareLinkLogic;

	/**
	 * 创建共享链接
	 * 
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.createShareLink, method = RequestMethod.POST)
	@ResponseBody
	public String createShareLink(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareLinkDTO linkDTO = PojoMapper.fromJsonAsObject(requestBody, ShareLinkDTO.class);
			reBody = shareLinkLogic.createShareLink(linkDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 更新共享链接
	 * 
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateShareLink, method = RequestMethod.POST)
	@ResponseBody
	public String updateShareLink(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareLinkDTO linkDTO = PojoMapper.fromJsonAsObject(requestBody, ShareLinkDTO.class);
			shareLinkLogic.updateShareLink(linkDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 删除共享链接
	 * 
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.deleteShareLink, method = RequestMethod.POST)
	@ResponseBody
	public String deleteShareLink(@RequestBody String requestBody) {
		String reBody = "";
		try {
			shareLinkLogic.deleteShareLink(Long.parseLong(requestBody));
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 获取共享链接
	 * 
	 * @param linkCode
	 * @return
	 */
	@RequestMapping(value = RESTurl.getShareLinkByCode, method = RequestMethod.POST)
	@ResponseBody
	public String getShareLinkByCode(@RequestBody String linkCode) {
		String reBody = "";
		try {
			ShareLinkInfoDTO linkDTO = shareLinkLogic.getShareLinkByCode(linkCode);
			reBody = PojoMapper.toJson(linkDTO);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(linkCode, ex);
		}
		return reBody;
	}

	/**
	 * 获取共享链接
	 * 
	 * @param entId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getShareLinkByEntId, method = RequestMethod.POST)
	@ResponseBody
	public String getShareLinkByEntId(@RequestBody String entId) {
		String reBody = "";
		try {
			List<ShareLinkDTO> linkDTOs = shareLinkLogic.getShareLinkByEntId(Long.parseLong(entId));
			ShareLinksDTO linksDTO = new ShareLinksDTO();
			linksDTO.setLinkDTOs(linkDTOs);
			reBody = PojoMapper.toJson(linksDTO);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(entId, ex);
		}
		return reBody;
	}

	/**
	 * 获取共享链接
	 * 
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.getExistShareLink, method = RequestMethod.POST)
	@ResponseBody
	public String getExistShareLink(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareLinkDTO link = PojoMapper.fromJsonAsObject(requestBody, ShareLinkDTO.class);
			ShareLinkDTO linkDTO = shareLinkLogic.getExistShareLink(link);
			reBody = PojoMapper.toJson(linkDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 获取共享链接
	 * 
	 * @param linkId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getShareLinkByLinkId, method = RequestMethod.POST)
	@ResponseBody
	public String getShareLinkByLinkId(@RequestBody String linkId) {
		String reBody = "";
		try {
			ShareLinkDTO linkDTO = shareLinkLogic.getShareLinkByLinkId(Long.parseLong(linkId));
			reBody = PojoMapper.toJson(linkDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(linkId, ex);
		}
		return reBody;
	}

	/**
	 * 验证共享链接是否可以下载
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = RESTurl.checkShareLinkDownload, method = RequestMethod.POST)
	@ResponseBody
	public String checkShareLinkDownload(@RequestBody String linkId) {
		String reBody = "";
		try {
			reBody = shareLinkLogic.checkDownload(Long.parseLong(linkId));
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(linkId, ex);
		}
		return reBody;
	}

	/**
	 * 更新共享链接下载次数
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateShareLinkDownCount, method = RequestMethod.POST)
	@ResponseBody
	public String updateShareLinkDownCount(@RequestBody String param) {
		String reBody = "";
		try {
			shareLinkLogic.updateDownloadCount(param);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(param, ex);
		}
		return reBody;
	}

	/**
	 * 发送共享链接
	 * 
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.sendShareLinkMail, method = RequestMethod.POST)
	@ResponseBody
	public String sendShareLinkMail(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareLinkMailDTO mailDTO = PojoMapper.fromJsonAsObject(requestBody, ShareLinkMailDTO.class);
			shareLinkLogic.sendShareLinkMail(mailDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}
}
