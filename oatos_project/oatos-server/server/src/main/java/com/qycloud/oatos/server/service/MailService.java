package com.qycloud.oatos.server.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.autobean.mail.IMailAccountDTO;
import com.conlect.oatos.dto.autobean.mail.IMailQueryDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.NetworkFilesDTO;
import com.conlect.oatos.dto.client.mail.MailAccountDTO;
import com.conlect.oatos.dto.client.mail.MailAccountsDTO;
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
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.utils.StringUtils;
import com.qycloud.oatos.server.domain.logic.MailLogic;
import com.qycloud.oatos.server.domain.logic.PersonalDiskLogic;
import com.qycloud.oatos.server.util.BllLogger;
import com.qycloud.oatos.server.util.LogicException;

@Controller("MailService")
public class MailService {
	private final static Logger logger = BllLogger.getLogger();

	@Autowired
	private MailLogic mailLogic;

	@Autowired
	private PersonalDiskLogic personalDiskLogic;
	/**
	 * 获取最新的邮件文件夹
	 * @param mailQuery
	 * @return
	 */
	@RequestMapping(value = RESTurl.receiveLatestMailFolder, method = RequestMethod.POST)
	@ResponseBody
	public String receiveLatestMailFolder(@RequestBody String mailQuery) {
		String responseBody = "";
		try {
			MailQueryDTO mailQueryDTO = PojoMapper.fromJsonAsObject(mailQuery, MailQueryDTO.class);
			MailFolderDTO folderDTO = mailLogic.receiveLatestMailFolder(mailQueryDTO);
			responseBody = PojoMapper.toJson(folderDTO);
		}
		catch (Exception e) {
			responseBody = ErrorType.error500.name();
			logger.error(e);
		}
		return responseBody;
	}
	
	/**
	 * 设置邮件为已读
	 * @param mailId
	 * @return
	 */
	@RequestMapping(value = RESTurl.setMailRead, method = RequestMethod.POST)
	@ResponseBody
	public String setMailRead(@RequestBody String mailId) {
		String responseBody = null;
		
		try {
			responseBody = mailLogic.setMailRead(mailId);
		}
		catch (Exception e) {
			responseBody = ErrorType.error500.name();
			logger.error(e);
		}
		return responseBody;
	}
	
	/**
	 * 通过文件名和用户token得到个人网盘中邮件附件
	 * @param userToken
	 * @param fileNames
	 * @return
	 */
	@RequestMapping(value = RESTurl.getAttachFiles, method = RequestMethod.POST)
	@ResponseBody
	public String getAttachFiles(@RequestHeader(RESTurl.UserTokenkey) String userToken, @RequestBody String fileNames) {
		String responseBody = null;
		try {
			fileNames = CommonUtil.ASCII2String(fileNames);
			String userId = StringUtils.split(userToken, '@')[0];
			List<NetworkFileDTO> fileList = personalDiskLogic.getAttachFiles(userId, fileNames);
			NetworkFilesDTO filesDTO = new NetworkFilesDTO(fileList);
			responseBody = PojoMapper.toJson(filesDTO);
		}
		catch (Exception e) {
			responseBody = ErrorType.error500.name();
			logger.error(e);
		}
		return responseBody;
	}

	/**
	 * 获取邮件文件夹 列表
	 * param mailAccountId
	 * param token
	 */

	@RequestMapping(value = RESTurl.getMailFolders, method = RequestMethod.POST)
	public @ResponseBody
	String getMailFolders(@RequestBody String mailAccountId, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String responseBody = "";
		try {
			MailFolderListDTO folderListDTO = mailLogic.getMailFolders(Long.parseLong(mailAccountId));
			responseBody = PojoMapper.toJson(folderListDTO);
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			responseBody = ErrorType.error500.name();
			logger.error(mailAccountId, ex);
		}

		return responseBody;
	}

	/**
	 * 保存邮件文件夹
	 * @param strFolderListDTO
	 * @return
	 */
	@RequestMapping(value = RESTurl.saveMailFolders, method = RequestMethod.POST)
	public @ResponseBody
	String saveMailFolders(@RequestBody String strFolderListDTO) {
		String responseBody = "";
		try {
			MailFolderListDTO folderListDTO = PojoMapper.fromJsonAsObject(strFolderListDTO, MailFolderListDTO.class);
			folderListDTO = mailLogic.takeMailFolders(folderListDTO);
			responseBody = PojoMapper.toJson(folderListDTO);
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			responseBody = ErrorType.error500.name();
			logger.error(strFolderListDTO, ex);
		}

		return responseBody;
	}

	/**
	 * 获得邮件列表
	 * @param requestBody
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.getMailList, method = RequestMethod.POST)
	public @ResponseBody
	String getMailList(@RequestBody String requestBody, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String responseBody = "";
		try {
			MailQueryDTO mailQuery = PojoMapper.fromJsonAsObject(requestBody, MailQueryDTO.class);
			List<MailDTO> mailList = mailLogic.getMailList(mailQuery);
			responseBody = PojoMapper.toJson(new MailListDTO(mailList));
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}

		return responseBody;
	}
	
	/**
	 * 增加邮件账号
	 * @param requestBody
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.addMailAccount, method = RequestMethod.POST)
	public @ResponseBody
	String addMailAccount(@RequestBody String requestBody, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String responseBody = "";
		try {
			IMailAccountDTO mailAccountDTO = PojoMapper.fromJsonAsObject(requestBody, MailAccountDTO.class);
			mailAccountDTO = mailLogic.addMailAccount(mailAccountDTO);
			if (mailAccountDTO == null) {
				logger.info("addMailAccount, mailAccountDTO is null");
				throw new LogicException(ErrorType.error500);
			}
			else {
				responseBody = PojoMapper.toJson(mailAccountDTO);
				logger.info("addMailAccount, mailAccountDTO=" + responseBody);
			}
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}

		return responseBody;
	}
	
	
	/**
	 * 删除邮件账号
	 * @param requestBody
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.deleteMailAccount, method = RequestMethod.POST)
	public @ResponseBody
	String deleteMailAccount(@RequestBody String requestBody, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String responseBody = "";
		try {
			long mailAccountId = Long.valueOf(requestBody);
			responseBody = mailLogic.deleteMailAccountByMailAccountId(mailAccountId);
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}

		return responseBody;
	}
	
	/**
	 * 更新邮件帐户
	 * @param requestBody
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateMailAccount, method = RequestMethod.POST)
	public @ResponseBody
	String updateMailAccount(@RequestBody String requestBody, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String responseBody = "";
		try {
			IMailAccountDTO mailAccountDTO = PojoMapper.fromJsonAsObject(requestBody, MailAccountDTO.class);
			responseBody = mailLogic.updateMailAccount(mailAccountDTO);
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}

		return responseBody;
	}
	
	/**
	 * 查找用户帐号
	 * @param requestBody
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.getMailAccountsByUserId, method = RequestMethod.POST)
	public @ResponseBody
	String getMailAccountsByUserId(@RequestBody String requestBody, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String responseBody = "";
		try {
			long userId = Long.valueOf(requestBody);
			List<MailAccountDTO> mailAccountDTOs = mailLogic.getMailAccountsByUserId(userId);
			responseBody = PojoMapper.toJson(new MailAccountsDTO(mailAccountDTOs));
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return responseBody;
	}

	/**
	 * 从数据库中获取邮件信息, 包括附件信息
	 * @param strMailQuery
	 * @return
	 */
	@RequestMapping(value = RESTurl.getMailInfo, method = RequestMethod.POST)
	public @ResponseBody
	String getMailInfo(@RequestBody String strMailQuery) {
		String responseBody = "";
		try {
			MailQueryDTO mailQuery = PojoMapper.fromJsonAsObject(strMailQuery, MailQueryDTO.class);
			MailDTO mail = mailLogic.getMailInfo(mailQuery);
			responseBody = PojoMapper.toJson(mail);
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			logger.error(strMailQuery, ex);
		}
		return responseBody;
	}
	
	/**
	 * 已存在邮件附件
	 * @param requestBody
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.existMailAttach, method = RequestMethod.POST)
	public @ResponseBody
	String existMailAttach(@RequestBody String requestBody, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String responseBody = "";
		try {
			IMailQueryDTO mailQuery = PojoMapper.fromJsonAsObject(requestBody, MailQueryDTO.class);
			responseBody = mailLogic.existMailAttach(mailQuery);
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return responseBody;
	}

	/**
	 * 返回邮箱账号信息
	 * @param requestBody
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.getMailAccount, method = RequestMethod.POST)
	public @ResponseBody
	String getMailAccount(@RequestBody String requestBody, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String responseBody = "";
		try {
			IMailAccountDTO mailAcc = mailLogic.getMailAccount(Long.parseLong(requestBody));
			responseBody = PojoMapper.toJson(mailAcc);
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return responseBody;
	}
	
	/**
	 * 邮箱帐户增加文件和附件
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.addMailFile, method = RequestMethod.POST)
	public @ResponseBody
	String addMailFile(@RequestBody String requestBody) {
		String responseBody = "";
		try {
			MailFileDTO mailFile = PojoMapper.fromJsonAsObject(requestBody, MailFileDTO.class);
			responseBody = mailLogic.addMailFile(mailFile);
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return responseBody;
	}
	
	
	/**
	 * 删掉邮件
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.deleteMails, method = RequestMethod.POST)
	public @ResponseBody
	String deleteMails(@RequestBody String requestBody) {
		String responseBody = "";
		try {
			IMailQueryDTO mailQuery = PojoMapper.fromJsonAsObject(requestBody, MailQueryDTO.class);
			mailLogic.deleteMails(mailQuery);
			responseBody = CommConstants.OK_MARK;
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return responseBody;

	}
	
	/**
	 * 增加联系人
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.addMailContact, method = RequestMethod.POST)
	public @ResponseBody
	String addMailContact(@RequestBody String requestBody) {
		String responseBody = "";
		try {
			MailContactDTO mailContactDTO = PojoMapper.fromJsonAsObject(requestBody, MailContactDTO.class);
			responseBody = PojoMapper.toJson(mailLogic.addMailContact(mailContactDTO));
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return responseBody;
	}
	
	/**
	 * 获取联系人列表
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getMailContacts, method = RequestMethod.POST)
	public @ResponseBody
	String getMailContacts(@RequestBody String userId) {
		String responseBody = "";
		try {
			responseBody = PojoMapper.toJson(new MailContactsDTO(mailLogic.getMailContactsByUserId(Long.parseLong(userId))));
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			logger.error(userId, ex);
		}
		return responseBody;
	}
	
	
	/**
	 * 修改联系人信息
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateMailContact, method = RequestMethod.POST)
	public @ResponseBody
	String updateMailContact(@RequestBody String requestBody) {
		String responseBody = "";
		try {
			MailContactDTO mailContactDTO = PojoMapper.fromJsonAsObject(requestBody, MailContactDTO.class);
			responseBody = mailLogic.updateMailContact(mailContactDTO);
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return responseBody;
	}
	
	
	/**
	 * 删除联系人
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.deleteMailContact, method = RequestMethod.POST)
	public @ResponseBody
	String deleteMailContact(@RequestBody String requestBody) {
		String responseBody = "";
		try {
			String[] contactIds = StringUtils.split(requestBody, ",");
			for (String contactId : contactIds) {
				long mailContactId = Long.parseLong(contactId);
				mailLogic.deleteMailContactById(mailContactId);
			}
			return CommConstants.OK_MARK;
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return responseBody;
	}
	
	
	
	/**
	 * 新建联系人分组
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.addMailContactGroup, method = RequestMethod.POST)
	public @ResponseBody
	String addMailContactGroup(@RequestBody String requestBody) {
		String responseBody = "";
		try {
			MailContactGroupDTO mailContactGroupDTO = PojoMapper.fromJsonAsObject(requestBody,
			        MailContactGroupDTO.class);
			return PojoMapper.toJson(mailLogic.addMailContactGroup(mailContactGroupDTO));
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return responseBody;
	}
	
	
	/**
	 * 获取联系人分组列表
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.getMailContactGroups, method = RequestMethod.POST)
	public @ResponseBody
	String getMailContactGroups(@RequestBody String requestBody) {
		String responseBody = "";
		try {
			long userId = Long.parseLong(requestBody);
			return PojoMapper.toJson(new MailContactGroupsDTO(mailLogic.getMailContactGroupsByUserId(userId)));
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return responseBody;
	}
	
	/**
	 * 修改联系人分组信息
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateMailContactGroup, method = RequestMethod.POST)
	public @ResponseBody
	String updateMailContactGroup(@RequestBody String requestBody) {
		String responseBody = "";
		try {
			MailContactGroupDTO mailContactGroupDTO = PojoMapper.fromJsonAsObject(requestBody,
			        MailContactGroupDTO.class);
			responseBody = mailLogic.updateMailContactGroup(mailContactGroupDTO);
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return responseBody;
	}
	
	/**
	 * 删除联系人分组信息
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.deleteMailContactGroup, method = RequestMethod.POST)
	public @ResponseBody
	String deleteMailContactGroup(@RequestBody String requestBody) {
		String responseBody = "";
		try {
			String[] params = StringUtils.split(requestBody, "&");
			long mailContactGroupId = Long.parseLong(params[0]);
			long userId = Long.parseLong(params[1]);
			responseBody = mailLogic.deleteMailContactGroupById(mailContactGroupId, userId);
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return responseBody;
	}

	/**
	 * 保存邮件到草稿箱
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.saveMailDraft, method = RequestMethod.POST)
	public @ResponseBody
	String saveMailDraft(@RequestBody String requestBody) {
		String responseBody = "";
		try {
			MailDTO mail = PojoMapper.fromJsonAsObject(requestBody, MailDTO.class);
			responseBody = mailLogic.saveMailDraft(mail);
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return responseBody;
	}
	
	
	/**
	 * 获取附件信息
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.getMailAttachById, method = RequestMethod.POST)
	public @ResponseBody
	String getMailAttachById(@RequestBody String requestBody) {
		String responseBody = "";
		try {
			responseBody = PojoMapper.toJson(mailLogic.getMailAttachById(Long.parseLong(requestBody)));
		}
		catch (LogicException ex) {
			responseBody = ex.getError().name();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return responseBody;
	}
}
