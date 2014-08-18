package com.qycloud.oatos.server.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.BlockDTO;
import com.conlect.oatos.dto.client.BlocksDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.logic.BlockListLogic;
import com.qycloud.oatos.server.util.BllLogger;

/**
 * 阻止联系人服务
 * @author yang
 *
 */
@Controller("BlockListService")
public class BlockListService {

	private final static Logger logger = BllLogger.getLogger();

	@Autowired
	private BlockListLogic blockListLogic;
	
	/**
	 * 用户取阻止通信的用户id
	 * 
	 * 
	 * @param userId
	 */
	@RequestMapping(value = RESTurl.getBlockListByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getBlockListByUserId(@RequestBody String userId) {
		String reBody = "";
		try {
			BlockDTO blockDTO = blockListLogic.getBlockListByUserId(Long.parseLong(userId));
			reBody = PojoMapper.toJson(blockDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(userId, ex);
		}
		return reBody;
	}

	/**
	 * 用户设置阻止通信的用户id
	 * 
	 * @param response
	 * @param postData
	 */
	@RequestMapping(value = RESTurl.setBlockListByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String setBlockListByUserId(@RequestBody String postData) {
		String reBody = "";
		try {
			BlockDTO blockDTO = PojoMapper.fromJsonAsObject(postData, BlockDTO.class);
			blockListLogic.setBlockListByUserId(blockDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}
	
	/**
	 * 取企业阻止通信列表(不可见)
	 * 
	 * @param response
	 * @param userId
	 */
	@RequestMapping(value = RESTurl.getEnterpriseBlocksByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getEnterpriseBlocksByUserId(@RequestBody String userId) {
		String reBody = "";
		try {
			BlockDTO blockDTO = blockListLogic.getBlocksByUserId(Long.parseLong(userId));
			reBody = PojoMapper.toJson(blockDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(userId, ex);
		}
		return reBody;
	}

	/**
	 * 企业设置阻止通信列表(不可见)
	 * 
	 * @param response
	 * @param postData
	 */
	@RequestMapping(value = RESTurl.updateEnterpriseBlocks, method = RequestMethod.POST)
	@ResponseBody
	public String updateEnterpriseBlocks(@RequestBody String postData) {
		String reBody = "";
		try {
			BlocksDTO blocksDTO = PojoMapper.fromJsonAsObject(postData, BlocksDTO.class);
			blockListLogic.updateBlocks(blocksDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}
	
	/**
	 * 用户解除阻止通信的用户id(黑名单)
	 * 
	 * @param response
	 * @param postData
	 */
	@RequestMapping(value = RESTurl.deleteBlock, method = RequestMethod.POST)
	@ResponseBody
	public String deleteBlock(@RequestBody String postData) {
		String reBody = "";
		try {
			BlockDTO blockDTO = PojoMapper.fromJsonAsObject(postData, BlockDTO.class);
			blockListLogic.deleteBlock(blockDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}
	
	/**
	 * 删除企业通信阻止(不可见)
	 * 
	 * @param response
	 * @param postData
	 */
	@RequestMapping(value = RESTurl.deleteEnterpriseBlock, method = RequestMethod.POST)
	@ResponseBody
	public String deleteEnterpriseBlock(@RequestBody String postData) {
		String reBody = "";
		try {
			BlockDTO blockDTO = PojoMapper.fromJsonAsObject(postData, BlockDTO.class);
			blockListLogic.deleteEnterpriseBlock(blockDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}
	
	/**
	 * 用户取被阻止通信的用户id
	 * 
	 * @param response
	 * @param userId
	 */
	@RequestMapping(value = RESTurl.getBlockedByListByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getBlockedByListByUserId(@RequestBody String userId) {
		String reBody = "";
		try {
			BlockDTO blockDTO = blockListLogic.getBlockedByListByUserId(Long.parseLong(userId));
			reBody = PojoMapper.toJson(blockDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(userId, ex);
		}
		return reBody;
	}
}
