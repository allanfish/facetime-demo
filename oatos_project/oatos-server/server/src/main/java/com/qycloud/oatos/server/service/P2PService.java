package com.qycloud.oatos.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.P2pKeyDTO;
import com.conlect.oatos.dto.client.UserStatusDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.logic.P2PLogic;
import com.qycloud.oatos.server.util.BllLogger;
import com.qycloud.oatos.server.util.LogicException;

/**
 * p2p视频通话，语音通话后台数据服务
 * @author yang
 *
 */
@Controller("P2PService")
public class P2PService {
	@Autowired
	private P2PLogic p2PLogic;

	/**
	 * 取用户的标志key
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getP2pKey, method = RequestMethod.POST)
	@ResponseBody
	public String getUserIdentityKey(@RequestBody String userId) {
		String reBody = "";
		try {
			reBody = p2PLogic.getFriendIdentityKey(Long.parseLong(userId));
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			BllLogger.getLogger().error(userId, ex);
		}
		return reBody;
	}

	/**
	 * 保存用户的标志key
	 * 
	 * @param article
	 */
	@RequestMapping(value = RESTurl.saveP2pKey, method = RequestMethod.POST)
	@ResponseBody
	public String setIdentityKey(@RequestBody String keyDTOJson)
	{
		String reBody = "";
		try {
			P2pKeyDTO keyDTO = PojoMapper.fromJsonAsObject(keyDTOJson, P2pKeyDTO.class);
			p2PLogic.setIdentityKey(keyDTO.getKey(), keyDTO.getUserId());

			reBody = CommConstants.OK_MARK;
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			BllLogger.getLogger().error(keyDTOJson, ex);
		}
		return reBody;

	}

	/**
	 * 取用户的通话状态
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getCallStatus, method = RequestMethod.POST)
	@ResponseBody
	public String getCallStatus(@RequestBody String userId) {
		String reBody = "";
		try {
			reBody = p2PLogic.getCallStatus(Long.parseLong(userId));
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			BllLogger.getLogger().error(userId, ex);
		}
		return reBody;
	}

	/**
	 * 修改用户的通话状态
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.setCallStatus, method = RequestMethod.POST)
	@ResponseBody
	public String setCallStatus(@RequestBody String statusDTOJson) {
		String reBody = "";
		try {
			UserStatusDTO dto = PojoMapper.fromJsonAsObject(statusDTOJson, UserStatusDTO.class);
			p2PLogic.setCallStatus(dto.getUserId(), dto.getUserStatus());
			reBody = CommConstants.OK_MARK;
		}
		catch (LogicException ex)
		{
			reBody = ex.getError().name();
		}
		catch (Exception ex)
		{
			reBody = ErrorType.error500.name();
			BllLogger.getLogger().error(statusDTOJson, ex);
		}
		return reBody;
	}

}
