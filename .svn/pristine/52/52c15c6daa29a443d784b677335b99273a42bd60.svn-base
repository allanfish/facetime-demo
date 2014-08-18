package com.qycloud.oatos.server.domain.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conlect.oatos.dto.autobean.mail.IMailAccountDTO;
import com.conlect.oatos.dto.autobean.mail.IMailAttachDTO;
import com.conlect.oatos.dto.autobean.mail.IMailQueryDTO;
import com.conlect.oatos.dto.client.mail.MailAccountDTO;
import com.conlect.oatos.dto.client.mail.MailAttachDTO;
import com.conlect.oatos.dto.client.mail.MailContactDTO;
import com.conlect.oatos.dto.client.mail.MailContactGroupDTO;
import com.conlect.oatos.dto.client.mail.MailDTO;
import com.conlect.oatos.dto.client.mail.MailFileDTO;
import com.conlect.oatos.dto.client.mail.MailFolderDTO;
import com.conlect.oatos.dto.client.mail.MailFolderListDTO;
import com.conlect.oatos.dto.client.mail.MailQueryDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.mail.MailHelper;
import com.qycloud.oatos.server.dao.MailAccountMapper;
import com.qycloud.oatos.server.dao.MailAttachMapper;
import com.qycloud.oatos.server.dao.MailContactMapper;
import com.qycloud.oatos.server.dao.MailContactGroupMapper;
import com.qycloud.oatos.server.dao.MailMapper;
import com.qycloud.oatos.server.dao.MailFolderMapper;
import com.qycloud.oatos.server.dao.PersonalFileMapper;
import com.qycloud.oatos.server.domain.entity.MailAccount;
import com.qycloud.oatos.server.domain.entity.MailAttach;
import com.qycloud.oatos.server.domain.entity.MailContactGroup;
import com.qycloud.oatos.server.domain.entity.MailContact;
import com.qycloud.oatos.server.domain.entity.MailFolder;
import com.qycloud.oatos.server.domain.entity.Mail;
import com.qycloud.oatos.server.domain.entity.PersonalFile;

@Service("MailLogic")
public class MailLogic {
	private static final int TAKE_MAIL_SIZE = 40;

	@Autowired
	private SequenceLogic sequence;

	@Autowired
	private MailAccountMapper mailAccountMapper;

	@Autowired
	private MailMapper mailMapper;
	@Autowired
	private MailAttachMapper mailAttachMapper;
	@Autowired
	private PersonalFileMapper personalFileMapper;
	@Autowired
	private MailContactMapper mailContactMapper;
	@Autowired
	private MailContactGroupMapper mailContactGroupMapper;
	@Autowired
	private MailFolderMapper mailFolderMapper;
	@Autowired
	private PersonalDiskLogic networkDiskLogic;
	
	/**
	 * 增加邮件账号
	 * @param dto
	 * @return
	 */
	public IMailAccountDTO addMailAccount(IMailAccountDTO dto) {
		MailAccount model = new MailAccount(dto);
		model.setMailAccountId(sequence.getNextId());
		mailAccountMapper.addMailAccount(model);
		return model.toDTO();
	}
	
	/**
	 * 增加联系人
	 * @param mailContactDTO
	 * @return
	 */
	@Transactional
	public MailContactDTO addMailContact(MailContactDTO mailContactDTO) {
		// 未分组时
		if (mailContactDTO.getMailContactGroupId() != null
		        && CommConstants.DEFAULT_CONTACT_GROUP_ID == mailContactDTO.getMailContactGroupId().longValue()) {
			mailContactDTO.setMailContactGroupId(null);
		}
		MailContact model = new MailContact(mailContactDTO);
		long mailContactId = sequence.getNextId();
		model.setMailContactId(mailContactId);
		mailContactDTO.setMailContactId(mailContactId);
		mailContactMapper.addMailContact(model);
		return mailContactDTO;
	}
	
	/**
	 *  新建联系人分组
	 * @param mailContactGroupDTO
	 * @return
	 */
	@Transactional
	public MailContactGroupDTO addMailContactGroup(MailContactGroupDTO mailContactGroupDTO) {
		MailContactGroup model = new MailContactGroup(mailContactGroupDTO);
		long contactGroupId = sequence.getNextId();
		model.setMailContactGroupId(contactGroupId);
		mailContactGroupDTO.setMailContactGroupId(contactGroupId);
		mailContactGroupMapper.addMailContactGroup(model);
		return mailContactGroupDTO;
	}
	
	/**
	 * 增加个人网盘文件和附件
	 * @param mailFile
	 * @return
	 */
	@Transactional
	public String addMailFile(MailFileDTO mailFile) {
		long fileId = networkDiskLogic.addEmailFile(mailFile.getNetworkFile());

		mailFile.getMailAttach().setFileId(fileId);
		updateMailAttach(mailFile.getMailAttach());

		return CommConstants.OK_MARK;
	}

	private MailFolderDTO asFolderDTO(MailFolder folderModel) {
		MailFolderDTO folderDTO = folderModel.toDTO();

		// load sub folders
		List<MailFolder> subFolderModels = mailFolderMapper.getSubFolders(folderDTO.getMailAccountId(),
		        folderDTO.getFolderId());
		if (subFolderModels != null && subFolderModels.size() > 0) {
			for (MailFolder subFolderModel : subFolderModels) {
				folderDTO.getChildren().add(asFolderDTO(subFolderModel));
			}
		}
		if (folderDTO.getMsgCount() > 0) {
			loadMailInfos(folderDTO);
		}
		return folderDTO;
	}

	private void createAttachs(List<MailAttach> attachModels, MailDTO mailDTO) {
		for (MailAttachDTO attach : mailDTO.getAttachs()) {
			attach.setMailId(mailDTO.getMailId());
			attach.setAttachId(sequence.getNextId());
			attachModels.add(new MailAttach(attach));
		}
	}

	private MailFolder createMailFolder(MailFolderDTO folder, Map<String, MailFolder> folderUrlToFolderId) {
		MailFolder model = new MailFolder(folder);
		model.setMailFolderId(sequence.getNextId());
		folder.setFolderId(model.getMailFolderId());

		folderUrlToFolderId.put(folder.getUrlName(), model);
		if (folder.getParentFolderUrl() != null) {
			Long parentFolderId = folderUrlToFolderId.get(folder.getParentFolderUrl()).getMailFolderId();
			if (parentFolderId != null && parentFolderId != 0) {
				folder.setParentFolderId(parentFolderId);
			}
		}
		if (folder.getChildren().size() > 0) {
			for (MailFolderDTO subFolder : folder.getChildren()) {
				createMailFolder(subFolder, folderUrlToFolderId);
			}
		}
		return model;
	}

	private void createMails(List<Mail> mailModels, MailFolderDTO folderDTO, List<MailAttach> attachModels) {
		List<MailDTO> mailList = folderDTO.getMailList();
		for (MailDTO mailDTO : mailList) {
			mailDTO.setFolderId(folderDTO.getFolderId());
			mailDTO.setFolderUrl(folderDTO.getUrlName());
			mailDTO.setMailAccountId(folderDTO.getMailAccountId());

			Mail mailModel = new Mail(mailDTO);
			mailModel.setMailId(sequence.getNextId());
			mailDTO.setMailId(mailModel.getMailId());
			mailModels.add(mailModel);

			if (mailDTO.getAttachs() != null && mailDTO.getAttachs().size() > 0) {
				createAttachs(attachModels, mailDTO);
			}
		}
	}
	
	/**
	 * 删除邮件账号
	 * @param mailAccountId
	 * @return
	 */
	@Transactional
	public String deleteMailAccountByMailAccountId(long mailAccountId) {
		mailAccountMapper.deleteMailAccount(mailAccountId);
		return CommConstants.OK_MARK;
	}
	
	/**
	 * 删除联系人信息
	 * @param mailContactId
	 * @return
	 */
	@Transactional
	public String deleteMailContactById(long mailContactId) {
		mailContactMapper.deleteMailContact(mailContactId);
		return CommConstants.OK_MARK;
	}
	
	/**
	 * 删除联系人分组信息
	 * @param mailContactGroupId
	 * @param userId
	 * @return
	 */
	@Transactional
	public String deleteMailContactGroupById(long mailContactGroupId, long userId) {
		if (CommConstants.DEFAULT_CONTACT_GROUP_ID == mailContactGroupId) {
			mailContactMapper.deleteUngroupMailContactByUserId(userId);
			return CommConstants.OK_MARK;
		}
		mailContactMapper.deleteMailContactByGroupId(mailContactGroupId);
		mailContactGroupMapper.deleteMailContactGroup(mailContactGroupId);
		return CommConstants.OK_MARK;
	}

	@Transactional
	public void deleteMails(IMailQueryDTO mailQuery) throws Exception {
		List<Mail> mailList = new ArrayList<Mail>();
		if (mailQuery.getMailIds().size() > 0) {
			mailList = mailMapper.getMailListById(mailQuery.getMailIds());
		}
		// update mail folder
		MailFolder folder = mailFolderMapper.getMailFolderById(mailQuery.getFolderId());
		int deleteUnreadMsgCount = 0;
		for (Mail mail : mailList) {
			if (!mail.isHaveRead()) {
				deleteUnreadMsgCount++;
			}
		}
		folder.setMsgCount(folder.getMsgCount() - mailList.size());
		folder.setUnreadMsgCount(folder.getUnreadMsgCount() - deleteUnreadMsgCount);
		mailFolderMapper.updateMailFolder(folder);

		if (mailQuery.getMailIds().size() > 0) {
			mailMapper.deleteMails(mailQuery.getMailIds());
		}
	}

	public String existMailAttach(IMailQueryDTO mailQuery) {
		List<MailAttach> attachList = mailAttachMapper.getAttachByMailId(mailQuery.getMailId());
		if (attachList == null || attachList.size() == 0) {
			return null;
		}
		for (MailAttach model : attachList) {
			if (model.getFileId() == null) {
				return null;
			}
			PersonalFile file = personalFileMapper.getFileById(model.getFileId());
			if (file.getFileName().equals(mailQuery.getAttachFileName())) {
				return file.getFileGuid();
			}
		}
		return null;
	}
	
	/**
	 * 返回邮箱帐号信息
	 * @param mailAccountId
	 * @return
	 */
	public IMailAccountDTO getMailAccount(long mailAccountId) {
		MailAccount model = mailAccountMapper.getMailAccount(mailAccountId);
		return model.toDTO();
	}

	public List<MailAccountDTO> getMailAccountsByUserId(long userId) {
		List<MailAccount> models = mailAccountMapper.getMailAccountsByUserId(userId);
		List<MailAccountDTO> dtos = new ArrayList<MailAccountDTO>();
		for (MailAccount model : models) {
			dtos.add(model.toDTO());
		}
		return dtos;
	}
	
	/**
	 * 获取附件信息
	 * @param attachId
	 * @return
	 */
	public IMailAttachDTO getMailAttachById(long attachId) {
		return mailAttachMapper.getMailAttachById(attachId).toDTO();
	}
	
	/**
	 * 获取联系人分组列表
	 * @param userId
	 * @return
	 */
	public List<MailContactGroupDTO> getMailContactGroupsByUserId(long userId) {
		List<MailContactGroupDTO> dtos = new ArrayList<MailContactGroupDTO>();
		List<MailContactGroup> models = mailContactGroupMapper.getMailContactGroupsByUserId(userId);
		if (models != null) {
			List<MailContactDTO> mailContacts = getMailContactsByUserId(userId);

			// 如有未分组联系人,添加“未分组”联系人组
			List<MailContactDTO> ungrpContactDTOs = new ArrayList<MailContactDTO>();
			for (MailContactDTO mailContactDTO : mailContacts) {
				if (mailContactDTO.getMailContactGroupId() == null
				        || mailContactDTO.getMailContactGroupId() == CommConstants.DEFAULT_CONTACT_GROUP_ID) {
					ungrpContactDTOs.add(mailContactDTO);
				}
			}
			if (ungrpContactDTOs.size() > 0) {
				MailContactGroup ungroup = new MailContactGroup(CommConstants.DEFAULT_CONTACT_GROUP_ID,
				        "未分组", userId);
				MailContactGroupDTO groupDTO = ungroup.toDTO();
				groupDTO.setMailContacts(ungrpContactDTOs);
				dtos.add(groupDTO);
			}

			for (MailContactGroup model : models) {
				MailContactGroupDTO dto = model.toDTO();
				List<MailContactDTO> contactDTOs = new ArrayList<MailContactDTO>();
				for (MailContactDTO mailContactDTO : mailContacts) {
					if (mailContactDTO.getMailContactGroupId() == null
					        || mailContactDTO.getMailContactGroupId() == CommConstants.DEFAULT_CONTACT_GROUP_ID) {

					}
					if (mailContactDTO.getMailContactGroupId() != null
					        && mailContactDTO.getMailContactGroupId() == dto.getMailContactGroupId()) {
						contactDTOs.add(mailContactDTO);
					}
				}
				dto.setMailContacts(contactDTOs);
				dtos.add(dto);
			}
		}
		return dtos;
	}
	
	/**
	 * 获取联系人列表
	 * @param userId
	 * @return
	 */
	public List<MailContactDTO> getMailContactsByUserId(long userId) {
		List<MailContact> models = mailContactMapper.getMailContactsByUserId(userId);
		List<MailContactDTO> dtos = new ArrayList<MailContactDTO>();

		if (models != null) {
			for (MailContact model : models) {
				dtos.add(model.toDTO());
			}
		}

		return dtos;
	}
	
	/**
	 * 获取邮件文件夹 列表
	 * @param mailAccountId
	 * @return
	 */
	public MailFolderListDTO getMailFolders(long mailAccountId) {
		List<MailFolder> folderModels = mailFolderMapper.getSubFolders(mailAccountId, null);
		List<MailFolderDTO> folderDTOs = new ArrayList<MailFolderDTO>(folderModels.size());
		for (MailFolder folderModel : folderModels) {
			MailFolderDTO folderDTO = asFolderDTO(folderModel);
			folderDTOs.add(folderDTO);
		}
		return new MailFolderListDTO(folderDTOs);
	}
	
	
	/**
	 * 获得邮件信息,包括附件
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public MailDTO getMailInfo(MailQueryDTO query) throws Exception {
		Mail mailModel = mailMapper.getMailById(query.getMailId());
		if (mailModel == null)
			return null;

		MailDTO mailInfo = mailModel.toDTO();
		if (mailModel.getContent() != null) {
			mailInfo = mailModel.toDTO();
			if (mailInfo.isHaveAttach()) {
				List<MailAttach> attachModels = mailAttachMapper.getAttachByMailId(mailModel.getMailId());
				if (attachModels != null)
					for (MailAttach mailAttachModel : attachModels)
						mailInfo.getAttachs().add(mailAttachModel.toDTO());
			}
		}
		else {
			MailDTO newMailInfo = takeMailInfoFromMailBox(query, mailModel);
			mailInfo.setContent(newMailInfo.getContent());
			mailInfo.setHaveRead(newMailInfo.isHaveRead());
			mailInfo.setHaveAttach(newMailInfo.isHaveAttach());
			mailInfo.setAttachs(newMailInfo.getAttachs());
		}
		return mailInfo;
	}

	private MailDTO takeMailInfoFromMailBox(MailQueryDTO query, Mail mailModel) throws Exception {
		IMailAccountDTO mailAcc = mailAccountMapper.getMailAccount(query.getMailAccountId()).toDTO();
		MailDTO mailInfo = MailHelper.getMailInfo(mailAcc, query.getFolderurl(), query.getMessageId(),
		        query.getSubject());
		mailInfo.setMailId(mailModel.getMailId());
		mailInfo.setMailAccountId(mailModel.getMailAccountId());

		// 将邮箱服务器中的邮件置为'已读'
		if (!mailInfo.isHaveRead()) {
			MailHelper.setMailRead(mailAcc, query.getFolderurl(), query.getMessageId(), query.getSubject());
			MailFolder folderModel = mailFolderMapper.getMailFolderById(query.getFolderId());
			folderModel.setMsgCount(folderModel.getMsgCount() > 0 ? folderModel.getMsgCount() - 1 : 0);
			mailFolderMapper.updateMailFolder(folderModel);
			mailInfo.setHaveRead(true);
		}

		mailModel.setHaveRead(true);
		mailModel.setHaveAttach(mailInfo.isHaveAttach());
		mailModel.setContent(mailInfo.getContent());
		if (mailInfo.isHaveAttach() && mailInfo.getAttachs().size() > 0) {
			List<MailAttach> attachModels = new ArrayList<MailAttach>(mailInfo.getAttachs().size());
			createAttachs(attachModels, mailInfo);
			mailAttachMapper.addAttaches(attachModels);
		}
		mailMapper.updateMail(mailModel);
		return mailInfo;
	}

	public List<MailDTO> getMailInfosByFolderId(long folderId, int startIndex, int maxResults) {
		List<Mail> mailModels = mailMapper.getMailsByFolderId(folderId, startIndex, maxResults);
		List<MailDTO> mailDTOs = new ArrayList<MailDTO>(mailModels.size());
		for (Mail mail : mailModels) {
			MailDTO mailInfo = mail.toDTO();
			mailDTOs.add(mailInfo);
			List<MailAttach> attachModels = mailAttachMapper.getAttachByMailId(mail.getMailId());
			if (attachModels != null) {
				for (MailAttach mailAttachModel : attachModels) {
					mailInfo.getAttachs().add(mailAttachModel.toDTO());
				}
			}
		}
		return mailDTOs;
	}
	
	/**
	 * 得到邮件列表
	 * @param query
	 * @return
	 */
	public List<MailDTO> getMailList(MailQueryDTO query) {
		List<MailDTO> mailDTOs = new ArrayList<MailDTO>();
		List<Mail> mailModels = mailMapper.getMailsByFolderId(query.getFolderId(), query.getBeginIndex(),
		        query.getMaxSize());
		for (Mail mailModel : mailModels) {
			MailDTO mailDTO = mailModel.toDTO();
			mailDTO.setFolderUrl(query.getFolderurl());
			mailDTOs.add(mailDTO);
		}
		return mailDTOs;
	}

	/**
	 * 加载邮件的简单信息, 不包括附件和内容部分
	 */
	private void loadMailInfos(MailFolderDTO folderDTO) {
		List<Mail> mailModels = mailMapper.getMailsByFolderId(folderDTO.getFolderId(), 0, TAKE_MAIL_SIZE);
		if (mailModels != null && mailModels.size() > 0) {
			for (Mail mailModel : mailModels) {
				MailDTO mailDTO = mailModel.toDTO();
				mailDTO.setFolderUrl(folderDTO.getUrlName());
				folderDTO.getMailList().add(mailDTO);

				if (mailModel.isHaveAttach()) {
					loadMailAttachs(mailModel, mailDTO);
				}
			}
		}
	}

	private void loadMailAttachs(Mail mailModel, MailDTO mailDTO) {
		List<MailAttach> attachModels = mailAttachMapper.getAttachByMailId(mailModel.getMailId());
		if (attachModels.size() > 0) {
			for (MailAttach attachModel : attachModels) {
				MailAttachDTO attachDTO = attachModel.toDTO();
				mailDTO.getAttachs().add(attachDTO);
			}
		}
	}
	
	@Transactional
	public MailFolderDTO receiveLatestMailFolder(MailQueryDTO mailQueryDTO) throws Exception {
			MailAccount accountModel = mailAccountMapper.getMailAccount(mailQueryDTO.getMailAccountId());
			MailFolderDTO folderDTO = MailHelper.receiveNewestFolder(accountModel.toDTO(), mailQueryDTO);
			folderDTO.setFolderId(mailQueryDTO.getFolderId());
			Map<String, Long> folderUrlToFolderId = new HashMap<String, Long>();
			updateMailFolder(folderDTO, folderUrlToFolderId);
			return folderDTO;
	}

	@Transactional
	public List<MailDTO> saveMails(List<MailDTO> list) {
		List<MailDTO> result = new ArrayList<MailDTO>(list.size());
		for (MailDTO mailDTO : list) {
			mailDTO.setMailId(sequence.getNextId());
			Mail mailModel = new Mail(mailDTO);
			mailMapper.addMail(mailModel);

			if (mailDTO.getAttachs() != null) {
				for (IMailAttachDTO attach : mailDTO.getAttachs()) {
					attach.setMailId(mailModel.getMailId());
					attach.setAttachId(sequence.getNextId());
					mailAttachMapper.addAttach(new MailAttach(attach));
				}
			}
			result.add(mailDTO);
		}
		if (result.size() > 40)
			return result.subList(0, 40);
		else
			return result;
	}
	
	/**
	 * 保存邮件到草稿箱
	 * @param mail
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public String saveMailDraft(MailDTO mail) throws Exception {
		MailAccount mailAcc = mailAccountMapper.getMailAccount(mail.getMailAccountId());
		if (mailAcc == null) {
			return CommConstants.ERROR_MARK;
		}
		MailHelper.saveDraftMail(mailAcc.toDTO(), mail);
		// TODO @YUFEI 等待测试
		return CommConstants.OK_MARK;
	}
	/**
	 * 设置邮件为已读
	 * @param mailId
	 * @return
	 */
	@Transactional
	public String setMailRead(String mailId) {
		Mail mailModel = mailMapper.getMailById(Long.parseLong(mailId));
		mailMapper.setMailRead(mailId);

		MailFolder folderModel = mailFolderMapper.getMailFolderById(mailModel.getMailFolderId());
		if (folderModel.getUnreadMsgCount() > 0) {
			folderModel.setUnreadMsgCount(folderModel.getUnreadMsgCount() - 1);
			mailFolderMapper.updateMailFolder(folderModel);
		}
		return CommConstants.OK_MARK;
	}
	
	/**
	 * 得到邮件文件夹
	 * @param folderListDTO
	 * @return
	 */
	@Transactional
	public MailFolderListDTO takeMailFolders(MailFolderListDTO folderListDTO) {
		List<MailFolderDTO> folderList = folderListDTO.getFolders();

		// 为性能考虑, 统一一次提交到数据库中
		Map<String, MailFolder> folderUrlToFolderId = new HashMap<String, MailFolder>();
		List<MailFolder> folderModels = new ArrayList<MailFolder>();
		List<Mail> mailModels = new ArrayList<Mail>();
		List<MailAttach> attachModels = new ArrayList<MailAttach>();
		for (MailFolderDTO folder : folderList) {
			folderModels.add(createMailFolder(folder, folderUrlToFolderId));
			if (folder.getMailList() != null && folder.getMailList().size() > 0) {
				createMails(mailModels, folder, attachModels);
			}
		}
		if (folderModels.size() > 0) {
			mailFolderMapper.addMailFolders(folderModels);
		}
		if (mailModels.size() > 0)
			mailMapper.addMails(mailModels);
		if (attachModels.size() > 0)
			mailAttachMapper.addAttaches(attachModels);

		// 限制只将40条邮件传递到
		for (MailFolderDTO folder : folderList) {
			if (folder.getMailList().size() > 40) {
				folder.setMailList(folder.getMailList().subList(0, 40));
			}
		}
		return new MailFolderListDTO(folderList);
	}
	
	/**
	 * 更新邮件帐户信息
	 * @param mailAccountDTO
	 * @return
	 */
	@Transactional
	public String updateMailAccount(IMailAccountDTO mailAccountDTO) {
		MailAccount mailAccountModel = new MailAccount(mailAccountDTO);
		mailAccountMapper.updateMailAccount(mailAccountModel);
		return CommConstants.OK_MARK;
	}

	@Transactional
	public void updateMailAttach(IMailAttachDTO mailAttach) {
		mailAttachMapper.updateFileId(mailAttach.getAttachId(), mailAttach.getFileId());
	}
	
	/**
	 * 修改联系人信息
	 * @param mailContactDTO
	 * @return
	 */
	@Transactional
	public String updateMailContact(MailContactDTO mailContactDTO) {
		MailContact model = new MailContact();
		BeanUtils.copyProperties(mailContactDTO, model);
		mailContactMapper.updateMailContact(model);
		return CommConstants.OK_MARK;
	}
	
	/**
	 * 修改联系人分组信息
	 * @param mailContactGroupDTO
	 * @return
	 */
	@Transactional
	public String updateMailContactGroup(MailContactGroupDTO mailContactGroupDTO) {
		mailContactGroupMapper.updateMailContactGroup(new MailContactGroup(mailContactGroupDTO));
		return CommConstants.OK_MARK;
	}

	/**
	 * 增量添加文件夹的邮件 TODO @yufei: 该方法非常复杂, 要考虑到有文件夹的新增和删除的情况
	 * 
	 * @param newFolder
	 * @param folderUrlToFolderId
	 */
	private void updateMailFolder(MailFolderDTO newFolder, Map<String, Long> folderUrlToFolderId) {
		MailFolder folderModel = mailFolderMapper.getMailFolderById(newFolder.getFolderId());
		folderModel.setLatestReceiveDate(newFolder.getLatestReceiveDate());
		folderModel.setMsgCount(newFolder.getMsgCount());
		folderModel.setUnreadMsgCount(newFolder.getUnreadMsgCount());

		mailFolderMapper.updateMailFolder(folderModel);
		List<MailFolder> childFolderModels = mailFolderMapper.getSubFolders(folderModel.getMailAccountId(),
		        folderModel.getMailFolderId());
		folderUrlToFolderId.put(newFolder.getUrlName(), newFolder.getFolderId());
		if (childFolderModels != null && newFolder != null && childFolderModels.size() > 0
		        && newFolder.getChildren().size() > 0) {
			for (MailFolder childFolder : childFolderModels) {
				for (MailFolderDTO newChildFolder : newFolder.getChildren()) {
					if (childFolder.getFolderUrl().equals(newChildFolder.getUrlName())) {
						updateMailFolder(newChildFolder, folderUrlToFolderId);
					}
				}
			}
		}
		List<Mail> mailModels = new ArrayList<Mail>();
		List<MailAttach> attachModels = new ArrayList<MailAttach>();
		if (newFolder.getMailList() != null && newFolder.getMailList().size() > 0) {
			createMails(mailModels, newFolder, attachModels);
		}
		if (mailModels.size() > 0) {
			mailMapper.addMails(mailModels);
		}
		if (attachModels.size() > 0) {
			mailAttachMapper.addAttaches(attachModels);
		}

		List<MailDTO> mailInfos = getMailInfosByFolderId(folderModel.getMailFolderId(), 0, TAKE_MAIL_SIZE);
		for (MailDTO mailInfo : mailInfos) {
			mailInfo.setFolderUrl(newFolder.getUrlName());
		}
		newFolder.setMailList(mailInfos);
	}
}
