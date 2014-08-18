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
import com.conlect.oatos.dto.status.url.ConferenceDocUrl;
import com.qycloud.oatos.filecache.domain.logic.ConferenceDocLogic;
import com.qycloud.oatos.filecache.util.Logs;

/**
 * 视频会议文档服务
 * 
 * @author yang
 * 
 */
@Controller
public class ConferenceDocService {

	private static final Logger logger = Logs.getLogger();

	@Autowired
	private ConferenceDocLogic docLogic;

	/**
	 * @see ConferenceDocUrl#viewConferenceDoc
	 */
	@RequestMapping(value = ConferenceDocUrl.viewConferenceDoc, method = RequestMethod.POST)
	public @ResponseBody
	String viewConferenceDoc(@RequestBody String postData,
			@RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = docLogic.viewConferenceDoc(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * @see ConferenceDocUrl#addConferenceDocFromPersonalDisk
	 */
	@RequestMapping(value = ConferenceDocUrl.addConferenceDocFromPersonalDisk, method = RequestMethod.POST)
	public @ResponseBody
	String addConferenceDocFromPersonalDisk(@RequestBody String postData,
			@RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = docLogic.addConferenceDocFromPersonalDisk(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * @see ConferenceDocUrl#addConferenceDocFromEnterpriseDisk
	 */
	@RequestMapping(value = ConferenceDocUrl.addConferenceDocFromEnterpriseDisk, method = RequestMethod.POST)
	public @ResponseBody
	String addConferenceDocFromEnterpriseDisk(@RequestBody String postData,
			@RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = docLogic.addConferenceDocFromEnterpriseDisk(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * @see ConferenceDocUrl#deleteConferenceDocs
	 */
	@RequestMapping(value = ConferenceDocUrl.deleteConferenceDocs, method = RequestMethod.POST)
	public @ResponseBody
	String deleteConferenceDocs(@RequestBody String postData,
			@RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = docLogic.deleteConferenceDocs(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * @see ConferenceDocUrl#deleteConference
	 */
	@RequestMapping(value = ConferenceDocUrl.deleteConference, method = RequestMethod.POST)
	public @ResponseBody
	String deleteConference(@RequestBody String postData,
			@RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = docLogic.deleteConference(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}

	/**
	 * @see ConferenceDocUrl#createConference
	 */
	@RequestMapping(value = ConferenceDocUrl.createConference, method = RequestMethod.POST)
	public @ResponseBody
	String createConference(@RequestBody String postData,
			@RequestHeader(RESTurl.UserTokenkey) String token) {
		String r = "";
		try {
			r = docLogic.createConference(postData, token);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}
}
