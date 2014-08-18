package com.qycloud.oatos.server.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.ConferenceDTO;
import com.conlect.oatos.dto.client.ConferenceDocDTO;
import com.conlect.oatos.dto.client.ConferenceDocsDTO;
import com.conlect.oatos.dto.client.ConferenceMemberDTO;
import com.conlect.oatos.dto.client.ConferenceMembersDTO;
import com.conlect.oatos.dto.client.ConferencesDTO;
import com.conlect.oatos.dto.client.CreateConferenceDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.ServerInnerUrl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.logic.ConferenceLogic;
import com.qycloud.oatos.server.util.BllLogger;
import com.qycloud.oatos.server.util.LogicException;

/**
 * 视频会议服务
 * @author yang
 *
 */
@Controller("ConferenceService")
public class ConferenceService {

	private final static Logger logger = BllLogger.getLogger();

	@Autowired
	private ConferenceLogic conferenceLogic;

	/**
	 * 创建会议
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.createConference, method = RequestMethod.POST)
	@ResponseBody
	public String createConference(@RequestBody String postData) {
		String reBody = "";
		try {
			CreateConferenceDTO conferenceDTO = PojoMapper.fromJsonAsObject(postData, CreateConferenceDTO.class);
			long conferenceId = conferenceLogic.createConference(conferenceDTO);
			reBody = String.valueOf(conferenceId);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 修改会议
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateConferenceStatus, method = RequestMethod.POST)
	@ResponseBody
	public String updateConferenceStatus(@RequestBody String postData) {
		String reBody = "";
		try {
			ConferenceDTO conferenceDTO = PojoMapper.fromJsonAsObject(postData, ConferenceDTO.class);
			conferenceLogic.updateConferenceStatus(conferenceDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 删除会议
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.deleteConference, method = RequestMethod.POST)
	@ResponseBody
	public String deleteConference(@RequestBody String postData) {
		String reBody = "";
		try {
			ConferencesDTO conferencesDTO = PojoMapper.fromJsonAsObject(postData, ConferencesDTO.class);
			conferenceLogic.deleteConference(conferencesDTO.getConferenceList());
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 取会议信息
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.getConferenceById, method = RequestMethod.POST)
	@ResponseBody
	public String getConferenceById(@RequestBody String postData) {
		String reBody = "";
		try {
			ConferenceDTO conferenceDTO = conferenceLogic.getConferenceById(Long.parseLong(postData));
			reBody = PojoMapper.toJson(conferenceDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 取会议列表
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.getConferenceByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getConferenceByUserId(@RequestBody String postData) {
		String reBody = "";
		try {
			ConferencesDTO conferencesDTO = conferenceLogic.getConferenceByUserId(Long.parseLong(postData));
			reBody = PojoMapper.toJson(conferencesDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 改会议成员状态
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateConferenceMember, method = RequestMethod.POST)
	@ResponseBody
	public String updateConferenceMember(@RequestBody String postData) {
		String reBody = "";
		try {
			ConferenceMemberDTO memberDTO = PojoMapper.fromJsonAsObject(postData, ConferenceMemberDTO.class);
			conferenceLogic.updateConferenceMember(memberDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 添加会议成员
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.addConferenceMembers, method = RequestMethod.POST)
	@ResponseBody
	public String addConferenceMembers(@RequestBody String postData) {
		String reBody = "";
		try {
			ConferenceMembersDTO membersDTO = PojoMapper.fromJsonAsObject(postData, ConferenceMembersDTO.class);
			conferenceLogic.addConferenceMembers(membersDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 添加会议文档中心文件
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.addConferenceDoc, method = RequestMethod.POST)
	@ResponseBody
	public String addConferenceDoc(@RequestBody String postData) {
		String reBody = "";
		try {
			ConferenceDocDTO docDTO = PojoMapper.fromJsonAsObject(postData, ConferenceDocDTO.class);
			reBody = conferenceLogic.addConferenceDoc(docDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 添加会议文档中心文件
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.addConferenceDocs, method = RequestMethod.POST)
	@ResponseBody
	public String addConferenceDocs(@RequestBody String postData) {
		String reBody = "";
		try {
			ConferenceDocsDTO docsDTO = PojoMapper.fromJsonAsObject(postData, ConferenceDocsDTO.class);
			reBody = conferenceLogic.addConferenceDoc(docsDTO.getDocList());
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 获取会议文档中心文件
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.getConferenceDocsByConId, method = RequestMethod.POST)
	@ResponseBody
	public String getConferenceDocsByConId(@RequestBody String postData) {
		String reBody = "";
		try {
			List<ConferenceDocDTO> docDTOs = conferenceLogic.getConferenceDocsByConId(Long.parseLong(postData));
			ConferenceDocsDTO docsDTO = new ConferenceDocsDTO();
			docsDTO.setDocList(docDTOs);
			reBody = PojoMapper.toJson(docsDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 删除会议文档中心文件
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.deleteConferenceDocs, method = RequestMethod.POST)
	@ResponseBody
	public String deleteConferenceDocs(@RequestBody String postData) {
		String reBody = "";
		try {
			ConferenceDocsDTO docsDTO = PojoMapper.fromJsonAsObject(postData, ConferenceDocsDTO.class);
			conferenceLogic.deleteConferenceDocs(docsDTO.getDocList());
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 判断会议文档文件是否存在,及网盘空间
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.checkConferenceDocUpload, method = RequestMethod.POST)
	@ResponseBody
	public String checkConferenceDocUpload(@RequestBody String postData) {
		String reBody = "";
		try {
			ConferenceDocDTO docDTO = PojoMapper.fromJsonAsObject(postData, ConferenceDocDTO.class);
			reBody = conferenceLogic.checkConferenceDocUpload(docDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 修改会议
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateConference, method = RequestMethod.POST)
	@ResponseBody
	public String updateConference(@RequestBody String postData) {
		String reBody = "";
		try {
			CreateConferenceDTO conferenceDTO = PojoMapper.fromJsonAsObject(postData, CreateConferenceDTO.class);
			conferenceLogic.updateConference(conferenceDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 更新会议文档总页数
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = ServerInnerUrl.updateConferenceDocPageCount, method = RequestMethod.POST)
	@ResponseBody
	public String updateConferenceDocPageCount(@RequestBody String postData) {
		String reBody = "";
		try {
			ConferenceDocDTO docDTO = PojoMapper.fromJsonAsObject(postData, ConferenceDocDTO.class);
			conferenceLogic.updateDocPageCount(docDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * @see RESTurl#getConferenceDocById
	 */
	@RequestMapping(value = RESTurl.getConferenceDocById, method = RequestMethod.POST)
	@ResponseBody
	public String getConferenceDocById(@RequestBody String fileId) {
		String reBody = "";
		try {
			ConferenceDocDTO docDTO = conferenceLogic.getConferenceDocById(Long.parseLong(fileId));
			reBody = PojoMapper.toJson(docDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(fileId, ex);
		}
		return reBody;
	}
}
