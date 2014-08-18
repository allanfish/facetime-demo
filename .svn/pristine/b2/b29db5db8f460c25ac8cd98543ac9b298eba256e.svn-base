package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.ChatGroupDTO;
import com.conlect.oatos.dto.client.ChatGroupMemberDTO;
import com.conlect.oatos.dto.client.ChatGroupMembersDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 群组聊天REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>新建临时聊天群
 * <li>添加群成员
 * <li>取群众成员
 * <li>退出群
 * <li>删除聊天群
 * </ul>
 * 
 * @author huhao
 * 
 */
public interface ChatGroupUrl {
	/**
	 * <p>
	 * 新建临时聊天群
	 * </p>
	 * <b>参数： </b>{@link ChatGroupDTO}群聊DTO <br>
	 * <p>
	 * <b>正常返回： </b>groupId 群组ID<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String addGroup = "/sc/group/addGroup";
	/**
	 * <p>
	 * 添加群成员
	 * </p>
	 * <b>参数： </b>{@link ChatGroupMemberDTO}群聊成员DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorExitMemeber} 成员已经存在
	 * <li>{@link ErrorType#errorNullOfGroup} 群不存在
	 * </ul>
	 */
	String addGroupMember = "/sc/group/addGroupMember";
	/**
	 * <p>
	 * 取群众成员
	 * </p>
	 * <b>参数： </b>groupId 群组ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link ChatGroupMembersDTO} 群聊成员DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorNullOfGroup}没有此群组
	 * </ul>
	 */
	String getGroupMembers = "/sc/group/getGroupMembers";
	/**
	 * <p>
	 * 退出群
	 * </p>
	 * <b>参数： </b>{@link ChatGroupMembersDTO} 群聊成员DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String removeGroupMember = "/sc/group/removeGroupMember";
	/**
	 * <p>
	 * 删除聊天群
	 * </p>
	 * <b>参数： </b>{@link ChatGroupDTO} 群聊组信息DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String deleteGroup = "/sc/group/deleteGroup";

}
