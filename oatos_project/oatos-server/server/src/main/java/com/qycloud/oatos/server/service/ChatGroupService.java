/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package com.qycloud.oatos.server.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.autobean.IChatGroupMemberDTO;
import com.conlect.oatos.dto.client.ChatGroupDTO;
import com.conlect.oatos.dto.client.ChatGroupMemberDTO;
import com.conlect.oatos.dto.client.ChatGroupMembersDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.logic.ChatGroupLogic;
import com.qycloud.oatos.server.domain.model.ChatGroupModel;
import com.qycloud.oatos.server.domain.model.GroupMemberModel;
import com.qycloud.oatos.server.util.BllLogger;
import com.qycloud.oatos.server.util.LogicException;

/**
 * 群组聊天服务 
 * @author yang
 *
 */
@Controller("ChatGroupService")
public class ChatGroupService {
	
	private final static Logger logger = BllLogger.getLogger();

	@Autowired
	private ChatGroupLogic chatGroupLogic;

	/**
	 * 新建聊天群
	 * 
	 * @param response
	 * @param requestBody
	 */
	@RequestMapping(value = RESTurl.addGroup, method = RequestMethod.POST)
	@ResponseBody
	public String addGroup(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ChatGroupDTO dto = PojoMapper.fromJsonAsObject(requestBody, ChatGroupDTO.class);
			ChatGroupModel chatGroup = new ChatGroupModel();
			chatGroup.setCreater(dto.userId);
			chatGroup.setGroupName(dto.groupName);

			// 返回群ID
			reBody = String.valueOf(chatGroupLogic.createChatGroup(chatGroup));
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 加入群
	 * 
	 * @param response
	 * @param requestBody
	 */
	@RequestMapping(value = RESTurl.addGroupMember, method = RequestMethod.POST)
	@ResponseBody
	public String addGroupMember(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ChatGroupMemberDTO dto = PojoMapper.fromJsonAsObject(requestBody, ChatGroupMemberDTO.class);
			GroupMemberModel groupMemberModel = new GroupMemberModel();
			groupMemberModel.setGroupId(dto.groupId);
			groupMemberModel.setUserId(dto.memberUserId);
			groupMemberModel.setUserName(dto.memberName);
			groupMemberModel.setUserHeaderPhoto(dto.userHeaderPhoto);
			chatGroupLogic.joinChatGroup(groupMemberModel, dto.userId);
			reBody = CommConstants.OK_MARK;
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 获取所有群成员
	 * 
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getGroupMembers, method = RequestMethod.POST)
	@ResponseBody
	public String getGroupMembers(@RequestBody String requestBody) {
		String reBody = "";
		try {
			long groupId = Long.parseLong(requestBody);
			List<IChatGroupMemberDTO> dtos = new ArrayList<IChatGroupMemberDTO>();

			List<GroupMemberModel> members = chatGroupLogic.getGroupMembers(groupId);
			ChatGroupMemberDTO dto;
			for (GroupMemberModel member : members) {
				dto = new ChatGroupMemberDTO();
				dto.groupId = member.getGroupId();
				dto.memberUserId = member.getUserId();
				dto.memberName = member.getUserName();
				dto.userHeaderPhoto = member.getUserHeaderPhoto();
				dtos.add(dto);
			}

			ChatGroupMembersDTO chatGroupMembersDTO = new ChatGroupMembersDTO();
			chatGroupMembersDTO.setChatGroupMemberDTOs(dtos);

			reBody = PojoMapper.toJson(chatGroupMembersDTO);

		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 退出群
	 * 
	 * @param response
	 * @param requestBody
	 */
	@RequestMapping(value = RESTurl.removeGroupMember, method = RequestMethod.POST)
	@ResponseBody
	public String removeGroupMember(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ChatGroupMemberDTO dto = PojoMapper.fromJsonAsObject(requestBody, ChatGroupMemberDTO.class);
			GroupMemberModel groupMemberModel = new GroupMemberModel();
			groupMemberModel.setGroupId(dto.groupId);
			groupMemberModel.setUserId(dto.memberUserId);

			chatGroupLogic.exitChatGroup(groupMemberModel);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 删除聊天群
	 * 
	 * @param response
	 * @param requestBody
	 */
	@RequestMapping(value = RESTurl.deleteGroup, method = RequestMethod.POST)
	@ResponseBody
	public String deleteGroup(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ChatGroupDTO dto = PojoMapper.fromJsonAsObject(requestBody, ChatGroupDTO.class);
			ChatGroupModel group = new ChatGroupModel();
			group.setGroupId(dto.id);
			group.setCreater(dto.userId);

			chatGroupLogic.deleteChatGroup(group);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

}
