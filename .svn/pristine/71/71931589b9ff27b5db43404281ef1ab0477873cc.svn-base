package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.NetworkFilesDTO;
import com.conlect.oatos.dto.client.mail.MailAccountDTO;
import com.conlect.oatos.dto.client.mail.MailAccountsDTO;
import com.conlect.oatos.dto.client.mail.MailAttachDTO;
import com.conlect.oatos.dto.client.mail.MailContactDTO;
import com.conlect.oatos.dto.client.mail.MailContactGroupDTO;
import com.conlect.oatos.dto.client.mail.MailContactGroupsDTO;
import com.conlect.oatos.dto.client.mail.MailContactsDTO;
import com.conlect.oatos.dto.client.mail.MailDTO;
import com.conlect.oatos.dto.client.mail.MailFileDTO;
import com.conlect.oatos.dto.client.mail.MailFolderDTO;
import com.conlect.oatos.dto.client.mail.MailFolderListDTO;
import com.conlect.oatos.dto.client.mail.MailListDTO;
import com.conlect.oatos.dto.client.mail.MailQueryDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 邮件REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>获取最新的邮件文件夹
 * <li>设置邮件已读
 * <li>按文件名搜索个人网盘信息
 * <li>获取邮件文件夹
 * <li>保存邮件文件夹
 * <li>获得邮件列表
 * <li>增加邮件账号
 * <li>删除邮件账号
 * <li>更新邮件帐户
 * <li>查找用户帐号
 * <li>从数据库中获取邮件信息, 包括附件信息
 * <li>已存在邮件附件
 * <li>返回邮箱账号信息
 * <li>邮箱帐户增加文件和附件
 * <li>删除邮件
 * <li>增加联系人
 * <li>获取联系人列表
 * <li>修改联系人信息
 * <li>删除联系人
 * <li>新建联系人分组
 * <li>获取联系人分组列表
 * <li>修改联系人分组信息
 * <li>删除联系人分组信息
 * <li>保存邮件到草稿箱
 * <li>获取附件信息
 * </ul>
 * 
 * @author huhao
 * 
 */
public interface MailUrl {
	/**
	 * <p>
	 * 获取最新的邮件文件夹
	 * </p>
	 * <b>参数： </b>{@link MailQueryDTO} 邮件查询DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link MailFolderDTO}邮件文件夹DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String receiveLatestMailFolder = "/sc/mail/receiveNewestFolder";
	/**
	 * <p>
	 * 设置邮件已读
	 * </p>
	 * <b>参数： </b>邮件ID <br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String setMailRead = "/sc/mail/setMailRead";
	/**
	 * <p>
	 * 通过文件名和用户token得到个人网盘中邮件附件的文件列表
	 * </p>
	 * <b>参数： </b>CommonUtil.string2ASCII(fileName) 文件名的ASCII 用户名通过token传入 <br>
	 * <p>
	 * <b>正常返回： </b>{@link NetworkFilesDTO}个人网盘文件列表DTO（邮件附件）<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getAttachFiles = "/sc/mail/getAttachFiles";
	/**
	 * <p>
	 * 获取邮件文件夹
	 * </p>
	 * <b>参数： </b>mailAccountId 邮件账户ID<br>
	 * <p>
	 * <b>正常返回： </b>@link MailFolderListDTO}邮件文件夹列表DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getMailFolders = "/sc/mail/getMailFolders";
	/**
	 * <p>
	 * 保存邮件文件夹
	 * </p>
	 * <b>参数： </b>{@link MailFolderListDTO} 邮件文件夹列表DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link MailFolderListDTO}邮件文件夹列表DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String saveMailFolders = "/sc/mail/saveMailFolders";
	/**
	 * <p>
	 * 获得邮件列表
	 * </p>
	 * <b>参数： </b>{@link MailQueryDTO} 邮件查询DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link MailListDTO}邮件列表DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getMailList = "/sc/mail/getMailList";
	/**
	 * <p>
	 * 增加邮件账号
	 * </p>
	 * <b>参数： </b>{@link MailAccountDTO} 邮件账号DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link MailAccountDTO}邮件账号DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String addMailAccount = "/sc/mail/addMailAccount";

	/**
	 * <p>
	 * 删除邮件账号
	 * </p>
	 * <b>参数： </b>mailAccountId 邮件账号ID <br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String deleteMailAccount = "/sc/mail/deleteMailAccount";

	/**
	 * <p>
	 * 更新邮件帐户
	 * </p>
	 * <b>参数： </b>{@link MailAccountDTO} 邮件账户DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link MailFolderDTO}邮件文件夹DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String updateMailAccount = "/sc/mail/updateMailAccount";

	/**
	 * <p>
	 * 查找用户帐号
	 * </p>
	 * <b>参数： </b>userId 用户ID <br>
	 * <p>
	 * <b>正常返回： </b>{@link MailAccountsDTO}邮箱帐户列表<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getMailAccountsByUserId = "/sc/mail/getMailAccountsByUserId";

	/**
	 * <p>
	 * 从数据库中获取邮件信息, 包括附件信息
	 * </p>
	 * <b>参数： </b>{@link MailQueryDTO}邮件查询DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link MailDTO}邮件DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getMailInfo = "/sc/mail/getMailInfo";

	/**
	 * <p>
	 * 已存在邮件附件
	 * </p>
	 * <b>参数： </b>{@link MailQueryDTO} 邮件查询DTO <br>
	 * <p>
	 * <b>正常返回： </b>文件guid<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String existMailAttach = "/sc/mail/existMailAttach";

	/**
	 * <p>
	 * 返回邮箱账号信息
	 * </p>
	 * <b>参数： </b>mailAccountId <br>
	 * <p>
	 * <b>正常返回： </b>{@link MailAccountDTO}邮件账户DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getMailAccount = "/sc/mail/getMailAccount";
	/**
	 * <p>
	 * 邮箱帐户增加文件和附件
	 * </p>
	 * <b>参数： </b>{@link MailFileDTO} 邮件文件DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String addMailFile = "/sc/mail/addMailFile";

	/**
	 * <p>
	 * 删除邮件
	 * </p>
	 * <b>参数： </b>{@link MailQueryDTO}查询邮箱账户DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>error
	 * </ul>
	 */
	String deleteMails = "/sc/mail/deleteMails";
	/**
	 * <p>
	 * 增加联系人
	 * </p>
	 * <b>参数： </b>{@link MailContactDTO} 邮件联系DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link MailFolderDTO}邮件文件夹DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String addMailContact = "/sc/mail/addMailContact";
	/**
	 * <p>
	 * 获取联系人列表
	 * </p>
	 * <b>参数： </b>userId <br>
	 * <p>
	 * <b>正常返回： </b>{@link MailContactsDTO}邮件联系DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getMailContacts = "/sc/mail/getMailContacts";
	/**
	 * <p>
	 * 修改联系人信息
	 * </p>
	 * <b>参数： </b>{@link MailAccountDTO} 邮件账号DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OKO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String updateMailContact = "/sc/mail/updateMailContact";

	/**
	 * <p>
	 * 删除联系人
	 * </p>
	 * <b>参数： </b>contactIds 形如：id1,id2,id3<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String deleteMailContact = "/sc/mail/deleteMailContact";
	/**
	 * <p>
	 * 新建联系人分组
	 * </p>
	 * <b>参数： </b>{@link MailContactGroupDTO} 邮件联系人分组DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link MailContactGroupDTO} 邮件联系人分组DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String addMailContactGroup = "/sc/mail/addMailContactGroup";
	/**
	 * <p>
	 * 获取联系人分组列表
	 * </p>
	 * <b>参数： </b>userId 用户的ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link MailContactGroupsDTO}邮件联系人分组夹DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getMailContactGroups = "/sc/mail/getMailContactGroups";

	/**
	 * <p>
	 * 修改联系人分组信息
	 * </p>
	 * <b>参数： </b>{@link MailContactGroupDTO} 邮件联系人分组DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String updateMailContactGroup = "/sc/mail/updateMailContactGroup";

	/**
	 * <p>
	 * 删除联系人分组信息
	 * </p>
	 * <b>参数： </b>格式为：mailContactGroupId&userId <br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String deleteMailContactGroup = "/sc/mail/deleteMailContactGroup";
	/**
	 * <p>
	 * 保存邮件到草稿箱
	 * </p>
	 * <b>参数： </b>{@link MailDTO} 邮件DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link CommConstants#ERROR_MARK} error
	 * </ul>
	 */
	String saveMailDraft = "/sc/mail/saveMailDraft";
	/**
	 * <p>
	 * 获取附件信息
	 * </p>
	 * <b>参数： </b>attachId <br>
	 * <p>
	 * <b>正常返回： </b>{@link MailAttachDTO}邮件账号DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getMailAttachById = "/sc/mail/getMailAttachById";

}
