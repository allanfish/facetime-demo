package com.qycloud.oatos.server.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.admin.AdminDTO;
import com.conlect.oatos.dto.client.admin.AdminsDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.logic.AdminLogic;
import com.qycloud.oatos.server.util.BllLogger;

/**
 * 二级管理员服务
 * @author yang
 *
 */
@Controller("AdminService")
public class AdminService {
	
	private final static Logger logger = BllLogger.getLogger();

	@Autowired
	private AdminLogic adminLogic;

	/**
	 * 获取企业的二级管理员
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.getAdminsByEntId, method = RequestMethod.POST)
	@ResponseBody
	public String getAdminsByEntId(@RequestBody String postData) {
		String reBody = "";
		try {
			AdminsDTO adminsDTO = adminLogic.getAdminsByEntId(Long.parseLong(postData));
			reBody = PojoMapper.toJson(adminsDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 添加二级管理员
	 * @param postData
	 * @return CommConstants.OK_MARK
	 * {@link}
	 */
	@RequestMapping(value = RESTurl.addAdmins, method = RequestMethod.POST)
	@ResponseBody
	public String addAdmins(@RequestBody String postData) {
		String reBody = "";
		try {
			AdminsDTO adminsDTO = PojoMapper.fromJsonAsObject(postData, AdminsDTO.class);
			adminLogic.addAdmins(adminsDTO.getAdmins());
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 更新二级管理员权限
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateAdmins, method = RequestMethod.POST)
	@ResponseBody
	public String updateAdmins(@RequestBody String postData) {
		String reBody = "";
		try {
			AdminsDTO adminsDTO = PojoMapper.fromJsonAsObject(postData, AdminsDTO.class);
			adminLogic.updateAdmins(adminsDTO.getAdmins());
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 删除二级管理员
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.deleteAdmin, method = RequestMethod.POST)
	@ResponseBody
	public String deleteAdmin(@RequestBody String postData) {
		String reBody = "";
		try {
			AdminDTO adminDTO = PojoMapper.fromJsonAsObject(postData, AdminDTO.class);
			adminLogic.deleteAdmin(adminDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {

			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

}
