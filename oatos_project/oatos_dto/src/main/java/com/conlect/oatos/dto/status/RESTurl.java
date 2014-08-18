/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conlect.oatos.dto.status;

import com.conlect.oatos.dto.status.url.AdminUrl;
import com.conlect.oatos.dto.status.url.BlockListUrl;
import com.conlect.oatos.dto.status.url.ChatGroupUrl;
import com.conlect.oatos.dto.status.url.ConferenceUrl;
import com.conlect.oatos.dto.status.url.EnterpriseUrl;
import com.conlect.oatos.dto.status.url.FavoriteFileUrl;
import com.conlect.oatos.dto.status.url.FileUrl;
import com.conlect.oatos.dto.status.url.LoginUrl;
import com.conlect.oatos.dto.status.url.MailUrl;
import com.conlect.oatos.dto.status.url.MessageUrl;
import com.conlect.oatos.dto.status.url.OrderUrl;
import com.conlect.oatos.dto.status.url.P2PUrl;
import com.conlect.oatos.dto.status.url.PersonalDiskUrl;
import com.conlect.oatos.dto.status.url.RoleUrl;
import com.conlect.oatos.dto.status.url.SendMailUrl;
import com.conlect.oatos.dto.status.url.ShareDiskUrl;
import com.conlect.oatos.dto.status.url.ShareLinkUrl;
import com.conlect.oatos.dto.status.url.UserUrl;
import com.conlect.oatos.dto.status.url.UsualContactUrl;
import com.conlect.oatos.file.ServerInnerUrl;

/**
 * @author PeterGuo
 */
public interface RESTurl extends LoginUrl, FileUrl, UserUrl, EnterpriseUrl,
		ShareDiskUrl, BlockListUrl, RoleUrl, OrderUrl, AdminUrl, ChatGroupUrl,
		MailUrl, MessageUrl, P2PUrl, PersonalDiskUrl, SendMailUrl,
		ShareLinkUrl, ConferenceUrl, ServerInnerUrl, UsualContactUrl,
		FavoriteFileUrl {

	/**
	 * 客户端token
	 */
	String UserTokenkey = "UT";
	String NULL = "NULL";
	String True = "true";
	String FALSE = "false";

	/**
	 * 用户id
	 */
	String userId = "ui";
	/**
	 * 企业id
	 */
	String enterpriseId = "ei";

	/**
	 * 好友id
	 */
	String buddyUserId = "bui";

	/**
	 * 接收者id
	 */
	String receiverId = "ri";

	/**
	 * 文件id
	 */
	String fileId = "fid";
	/**
	 * 文件夹id
	 */
	String folderId = "fi";

	/**
	 * 文件名
	 */
	String fileName = "fn";
	/**
	 * 文件路径
	 */
	String filePath = "fp";
	/**
	 * 文件guid名
	 */
	String guidName = "gn";
	/**
	 * 文件类型
	 */
	String type = "tp";
	/**
	 * 文件大小
	 */
	String fileSize = "fs";
	/**
	 * 分块大小
	 */
	String blockSize = "bs";
	/**
	 * 分块编号
	 */
	String blockIndex = "bi";
	/**
	 * 分块数目
	 */
	String blockCount = "bc";
	/** 开始位置 */
	String START_POSITION = "sp";
	/** 结束位置 */
	String END_POSITION = "ep";
	String checkDownPermission = "cdp";

	/**
	 * share link code
	 */
	String linkCode = "lc";
	/**
	 * update time
	 */
	String updateTime = "ut";
	/**
	 * status
	 */
	String status = "status";

	/*
	 * Mail Module Constants
	 */
	String mailAccountId = "mailAccountId";
	String mailAttachId = "mailAttachId";
	String folderurl = "folderurl";
	String messageId = "messageId";
	String mail = "mail";
	String MAIL_ACTION = "servlet/mail.action";
	String TEMPLATE_ACTION = "servlet/template";
	/**
	 * 用户导入模版servlet
	 */
	String UPLOAD_USER_IMPORT_TEMPLATE = "servlet/template/upload";
	String action = "action";
	String mailAccountDTO = "mailAccountDTO";
	String mailQueryDTO = "mailQueryDTO";
	String mailDTO = "mailDTO";

	/**
	 * @deprecated
	 */
	@Deprecated
	String diskIP = "di";
	/**
	 * routing
	 * 
	 * @deprecated
	 */
	@Deprecated
	String Routing = "routing";

	String serviceMethod = "sm";

	String postJsonData = "postJsonData";

	String getStartPoint = "1";
	String upload = "0";
	String isUploadOver = "3";

	//
	// =========================================================================
	//

	/**
	 * 批量发送消息
	 * 
	 * <br>
	 * MessagesDTO
	 */
	String sendMessages = "/sc/message/sendMessages";

	/**
	 * 判断是否可以发送离线文件
	 * 
	 * @deprecated
	 */
	@Deprecated
	String canSendOfflineFile = "/sc/message/canSendOfflineFile";

	/**
	 * 将OATOS的系统文件拷贝到用户的目录下
	 */
	String copySystemFile = "/pub/copySystemFiles";

	/**
	 * 取企业部门和成员
	 * 
	 * @deprecated use {getDepartmentAndUserWithStatusByUserId} instead
	 */
	@Deprecated
	String getDepartmentAndUserWithStatusByEntId = "/sc/ent/getDepartmentAndUserWithStatusByEntId";

	/**
	 * 保存邮件到数据库
	 * 
	 */
	String saveMail = "/sc/mail/saveMail";
	/**
	 * 发送邮件快捷回复
	 * 
	 */
	String sendReplyMail = "/sc/mail/sendReplyMail";
	/**
	 * 发送新邮件
	 * 
	 */
	String sendNewMail = "/sc/mail/sendNewMail";

	/**
	 * 返回邮箱文件夹
	 * 
	 */
	String getMailFolder = "/sc/mail/getMailFolder";

}
