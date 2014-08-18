/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package com.qycloud.oatos.server.domain.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conlect.oatos.dto.status.ErrorType;
import com.qycloud.oatos.server.dao.UserMapper;
import com.qycloud.oatos.server.domain.entity.User;
import com.qycloud.oatos.server.domain.model.ChatGroupModel;
import com.qycloud.oatos.server.domain.model.GroupMemberModel;
import com.qycloud.oatos.server.util.CacheClient;
import com.qycloud.oatos.server.util.CacheKey;
import com.qycloud.oatos.server.util.LogicException;

/**
 * 群聊服务
 * @author yang
 *
 */
@Service("ChatGroupLogic")
public class ChatGroupLogic {
	
	@Autowired
	private SequenceLogic sequence;

	@Autowired
	private UserMapper userMapper;

	/**
	 * 新建聊天群
	 * 
	 * @param chatGroup
	 * @return 群ID
	 */
	public long createChatGroup(ChatGroupModel chatGroup) {
		String userName = "";
		String userHeaderPhoto = "";
		User user = userMapper.getUser(chatGroup.getCreater());
		if (user != null) {
			userName = user.getUserName();
			userHeaderPhoto = user.getUserHeaderPhoto();
        }
		// 创建新的群
		long groupId = sequence.getNextId();
		chatGroup.setGroupId(groupId);

		// 群成员
		GroupMemberModel member = new GroupMemberModel();
		member.setGroupId(groupId);
		member.setUserId(chatGroup.getCreater());
		member.setUserName(userName);
		member.setUserHeaderPhoto(userHeaderPhoto);

		chatGroup.getMembers().add(member);
		String key = CacheKey.Group + groupId;
		CacheClient.getInstance().set(key, chatGroup);
		return groupId;
	}

	/**
	 * 加入群
	 * 
	 * @param params
	 */
	public void joinChatGroup(GroupMemberModel groupMemberModel, long inviter) {
		
		// 从缓存中获取群
		String key = CacheKey.Group + groupMemberModel.getGroupId();

		ChatGroupModel chatModel = (ChatGroupModel) CacheClient.getInstance().get(key);

		if (chatModel != null) {
			List<GroupMemberModel> members = chatModel.getMembers();
			for (GroupMemberModel member : members) {
				if (groupMemberModel.getUserId() == member.getUserId()) {
					throw new LogicException(ErrorType.errorExitMemeber);
				}
			}
			members.add(groupMemberModel);

			CacheClient.getInstance().set(key, chatModel);

		}
		else {
			// 否则群不存在
			throw new LogicException(ErrorType.errorNullOfGroup);
		}
	}

	/**
	 * 取群组成员
	 * @param groupId
	 * @return
	 */
	public List<GroupMemberModel> getGroupMembers(long groupId) {
		
		List<GroupMemberModel> reMembers = null;

		// 从缓存中获取群
		String key = CacheKey.Group + groupId;
		ChatGroupModel chatModel = (ChatGroupModel) CacheClient.getInstance().get(key);
		if (chatModel != null) {
			reMembers = chatModel.getMembers();
		}
		else {
			// 否则群不存在
			throw new LogicException(ErrorType.errorNullOfGroup);
		}
		return reMembers;
	}

	/**
	 * 退出群
	 * 
	 * @param params
	 */
	public void exitChatGroup(GroupMemberModel groupMemberModel) {
		
		// 从缓存中获取群
		String key = CacheKey.Group + groupMemberModel.getGroupId();
		ChatGroupModel chatModel = (ChatGroupModel) CacheClient.getInstance().get(key);
		
		if (chatModel != null) {
			List<GroupMemberModel> members = chatModel.getMembers();
			for (GroupMemberModel member : members) {
				if (groupMemberModel.getUserId() == member.getUserId()) {
					members.remove(member);
					break;
				}
			}
			chatModel.setMembers(members);
			CacheClient.getInstance().set(key, chatModel);
		}
	}

	/**
	 * 删除聊天群
	 * 
	 * @param groupId
	 */
	public void deleteChatGroup(ChatGroupModel chatGroup) {
		
		// 从缓存中获取群
		String key = CacheKey.Group + chatGroup.getGroupId();
		ChatGroupModel chatModel = (ChatGroupModel) CacheClient.getInstance().get(key);
		if (chatModel != null) {
			CacheClient.getInstance().delete(key);
		}
	}


}
