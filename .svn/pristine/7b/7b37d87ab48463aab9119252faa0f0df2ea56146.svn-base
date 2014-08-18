package com.qycloud.oatos.server.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.UsualContactDTO;
import com.conlect.oatos.dto.client.UsualContactGroupDTO;
import com.conlect.oatos.dto.client.UsualContactGroupAndUserDTO;
import com.conlect.oatos.dto.client.UsualContactGroupsDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.logic.UsualContactLogic;
import com.qycloud.oatos.server.util.BllLogger;

/**
 * 常用联系人服务
 * 
 * @author huhao
 * 
 */
@Controller("UsualContactService")
public class UsualContactService {

	private final static Logger logger = BllLogger.getLogger();

	@Autowired
	private UsualContactLogic usualContactLogic;

	/**
	 * 设置常用联系人
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = RESTurl.setUsualContact, method = RequestMethod.POST)
	@ResponseBody
	public String setUsualContactListByUserId(@RequestBody String userId) {
		String resBody = "";
		try {
			UsualContactDTO usualContactDTO = PojoMapper.fromJsonAsObject(
					userId, UsualContactDTO.class);
			usualContactLogic.setUsualContactListByUserId(usualContactDTO);
			resBody = CommConstants.OK_MARK;
		} catch (Exception e) {
			resBody = ErrorType.error500.name();
			logger.error(userId, e);
		}
		return resBody;
	}

	/**
	 * 取消常用联系人
	 * 
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.deleteUsualContact, method = RequestMethod.POST)
	@ResponseBody
	public String deleteUsualContact(@RequestBody String postData) {
		String resBody = "";
		try {
			UsualContactDTO usualContactDTO = PojoMapper.fromJsonAsObject(
					postData, UsualContactDTO.class);
			usualContactLogic.deleteUsualContact(usualContactDTO);
			resBody = CommConstants.OK_MARK;
		} catch (Exception e) {
			resBody = ErrorType.error500.name();
			logger.error(postData, e);
		}
		return resBody;
	}

	/**
	 * 创建常用联系人分组
	 * 
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.addUsualContactGroup, method = RequestMethod.POST)
	@ResponseBody
	public String addUsualContactGroup(@RequestBody String postData) {
		String resBody = "";
		try {
			UsualContactGroupDTO usualContactDTO = PojoMapper.fromJsonAsObject(
					postData, UsualContactGroupDTO.class);
			usualContactLogic.addUsualContactGroup(usualContactDTO);
			resBody = CommConstants.OK_MARK;
		} catch (Exception e) {
			resBody = ErrorType.error500.name();
			logger.error(postData, e);
		}
		return resBody;
	}

	/**
	 * 修改常用联系人分组
	 */

	@RequestMapping(value = RESTurl.updateUsualContactGroup, method = RequestMethod.POST)
	@ResponseBody
	public String updateUsualContactGroup(@RequestBody String postData) {
		String responseBody = "";
		try {
			UsualContactGroupDTO usualContactGroupDTO = PojoMapper
					.fromJsonAsObject(postData, UsualContactGroupDTO.class);
			usualContactLogic.updateUsualContactGroup(usualContactGroupDTO);
			responseBody = CommConstants.OK_MARK;
		} catch (Exception e) {
			responseBody = ErrorType.error500.name();
			logger.error(responseBody, e);

		}
		return responseBody;

	}

	/**
	 * 删除常用联系人
	 * 
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.deleteUsualContactGroup, method = RequestMethod.POST)
	@ResponseBody
	public String deleteUsualContactGroup(@RequestBody String postData) {
		String responseBody = "";
		try {
			UsualContactGroupDTO usualContactGroupDTO = PojoMapper
					.fromJsonAsObject(postData, UsualContactGroupDTO.class);
			usualContactLogic.deleteUsualContactGroup(usualContactGroupDTO);
			responseBody = CommConstants.OK_MARK;
		} catch (Exception e) {
			responseBody = ErrorType.error500.name();
			logger.error(responseBody, e);
		}
		return responseBody;
	}

	/**
	 * 通过用户ID获得常用联系人分组列表和联系人详细信息
	 * 
	 * @param userId
	 */
	@RequestMapping(value = RESTurl.getUsualContactGroupAndUserByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getUsualContactGroupAndUserByUserId(@RequestBody String userId) {
		String responseBody = "";
		try {
			UsualContactGroupAndUserDTO usualContactGroupsDTO = usualContactLogic
					.getUsualContactGroupAndUserByUserId(Long.parseLong(userId));
			responseBody = PojoMapper.toJson(usualContactGroupsDTO);
		} catch (Exception e) {
			responseBody = ErrorType.error500.name();
			logger.error(userId, e);
		}
		return responseBody;
	}

	/**
	 * 更新用户联系人的分组ID
	 * 
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateUsualContact, method = RequestMethod.POST)
	@ResponseBody
	public String updateUsualContact(@RequestBody String postData) {
		String responseBody = "";
		try {
			UsualContactDTO usualContactDTO = PojoMapper.fromJsonAsObject(
					postData, UsualContactDTO.class);
			usualContactLogic.updateUsualContact(usualContactDTO);
			responseBody = CommConstants.OK_MARK;
		} catch (Exception e) {
			responseBody = ErrorType.error500.name();
			logger.error(postData, e);
		}
		return responseBody;
	}

	@RequestMapping(value = RESTurl.getUsualContactGroupsByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getUsualContactGroupsByUserId(@RequestBody String userId) {
		String responseBody = "";
		try {
			UsualContactGroupsDTO groupsDTO = usualContactLogic.getUsualContactGroupsByUserId(Long.parseLong(userId));
			responseBody = PojoMapper.toJson(groupsDTO);
		} catch (Exception e) {
			responseBody = ErrorType.error500.name();
			logger.error(userId, e);
		}
		return responseBody;
	}

}
