/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conlect.oatos.dto.status;

/**
 * 消息类型<br>
 * 字符串长度不超过10
 * 
 * @author PeterGuo
 */
public interface MessageType {

	/**
	 * chat message
	 */
	String ChatMessage = "Chat";
	/**
	 * 新邮件到达消息
	 */
	String NewMailNotify = "NewMail";
	/**
	 * 邮件接收完成消息
	 */
	String MailNotify = "MailN";
	/**
	 * invite buddy
	 */
	@Deprecated
	String InviteBuddy = "AddBud";
	/**
	 * 邀请好友回复， 代表被邀请方接受邀请，邀请方处理
	 */
	String Reply = "Reply";
	/**
	 * 强行下线
	 */
	String ForceOffline = "ForOffl";
	/**
	 * 即时文件传输
	 */
	String InstantFile = "InsFile";
	/**
	 * 私聊时共享文件
	 */
	String ShareFileInPrivacy = "SFP";
	/**
	 * 群聊时共享文件
	 */
	String ShareFileInGroup = "SFG";
	/**
	 * 群聊共享即时文件，文件为客户端上传
	 */
	String ShareInstantFile = "SIF";
	/**
	 * 群聊天记录
	 */
	String GroupChat = "GroupChat";
	/**
	 * 删除好友时， 通知对方
	 */
	@Deprecated
	String InformDeleteBuddy = "DelBud";
	/**
	 * 邀请好友加入聊天组
	 */
	@Deprecated
	String InviteBuddyToChatGroup = "IBToCG";
	/**
	 * 好友回复是否加入聊天组
	 */
	@Deprecated
	String ReplyForInvitedToChatGroup = "RICG";
	/**
	 * 某聊天组成员通知组内所有成员获取最新的成员列表
	 */
	String InformChatGroupToGetMemberList = "ICGGM";
	/**
	 * 某聊天组成员通知组内所有成员获取最新的成员列表
	 */
	String InformJoinChatGroup = "InfJCG";
	/**
	 * 聊天组成员退出通知
	 */
	String InformRemoveChatGroupMember = "InfRMem";
	/**
	 * 通知编辑状态
	 */
	String InformEditStatus = "InfEditSta";
	/**
	 * 视频邀请
	 */
	String VideoInvite = "VidInv";
	/**
	 * 视频开始
	 */
	String VideoStart = "VidSta";
	/**
	 * 拒绝视频
	 */
	String VideoRefuse = "VidRef";
	/**
	 * 视频结束
	 */
	String VideoEnd = "VidEnd";
	/**
	 * 音频邀请
	 */
	String AudioInvite = "AudInv";
	/**
	 * 音频开始
	 */
	String AudioStart = "AudSta";
	/**
	 * 拒绝音频
	 */
	String AudioRefuse = "AudRef";
	/**
	 * 音频结束
	 */
	String AudioEnd = "AudEnd";

	/**
	 * 用户登入
	 */
	String UserJoin = "UserJoin";
	/**
	 * 用户改变状态，其新状态值由<br> {@link com.conlect.oatos.dto.client.MessageDTO#messageBody}
	 * 获取
	 */
	String UserStatusChange = "UserSCh";
	/**
	 * 用户离开
	 */
	String UserLeave = "UserLea";
	/**
	 * 用户信息更新
	 */
	String UserInfoUpdate = "UserUpd";

	/**
	 * after user agreed import contacts from YAHOO! or GOOGLE, it returns a
	 * verifier.
	 */
	@Deprecated
	String BuddyImportFromWeb = "BudImpWeb";

	/**
	 * offline file
	 */
	String OfflineFile = "OffFile";

	/**
	 * voice mail
	 */
	String VoiceMail = "VoMail";
	/**
	 * 客户请求服务，客服处理
	 */
	String CustomerRequest = "CusReq";
	/**
	 * 客服响应，客户处理
	 */
	String ServiceResponse = "SerRes";
	/**
	 * 客户退出，客服处理
	 */
	String CustomerLeave = "CusLea";
	/**
	 * 客服退出，客户处理
	 */
	String ServiceLeave = "SerLea";
	/**
	 * 企业用户登录记录
	 */
	String Login = "Login";
	/**
	 * 文件进入记录
	 */
	String FileDown = "fd";
	/**
	 * 文件输出记录
	 */
	String fileAccessRecord = "fa";
	/**
	 * 客户连接通知，客户发出，客服处理
	 */
	String CustomerConnect = "CusCon";
	/**
	 * 客服连接响应，客服发出，客户处理
	 */
	String ServiceConnectRes = "SerCRes";
	/**
	 * 客服移除客户通知，客服发出，客户处理
	 */
	String RemoveCustomer = "ReCus";
	/**
	 * 共享网盘文件更新
	 */
	String ShareFileNew = "SFNew";
	/**
	 * 同事更新
	 */
	String ColleagueNew = "ColNew";
	/**
	 * 视频会议邀请参会
	 */
	String ConferenceInvite = "ConInv";
	/**
	 * 视频会议开始，通知出席
	 */
	String ConferenceStart = "ConSta";
	/**
	 * 视频会议结束
	 */
	String ConferenceEnd = "ConEnd";
	/**
	 * 视频会议更新
	 */
	String ConferenceUpdate = "ConUpd";
	/**
	 * 视频会议文档更新
	 */
	String ConferenceDocNew = "ConDocNew";
	/**
	 * 通知阻止状态更新，message body true 标识 block，false 标识 unblock
	 */
	String InformBlock = "IB";
	/**
	 * 系统消息
	 */
	String SystemMsg = "SystemMsg";
	/**
	 * 系统视频消息
	 */
	String SystemVideoMsg = "SystemVideoMsg";

	/**
	 * 文件上传完成通知
	 */
	String FileUploadDone = "FileUploadDone";

	/**
	 * 文件转换完成通知
	 */
	String FileConvertDone = "FileConvertDone";
}
