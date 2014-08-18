package com.qycloud.oatos.server.logic.mail;

import java.util.List;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qycloud.oatos.server.dao.MailMapper;
import com.qycloud.oatos.server.dao.MailFolderMapper;
import com.qycloud.oatos.server.domain.entity.MailAccount;
import com.qycloud.oatos.server.domain.entity.MailFolder;
import com.qycloud.oatos.server.domain.logic.SequenceLogic;

/**
 * 邮件文件夹同步，同步系统数据库与邮箱服务器文件夹
 * 
 * @author jinkerjiang
 * 
 */
@Service("MailFolderSynchronizer")
public class MailFolderSynchronizer {
	@Autowired
	private MailServerConnector mailServerConnector;
	@Autowired
	private SequenceLogic sequence;
	@Autowired
	private MailFolderMapper mailFolderDao;
	@Autowired
	private MailMapper mailDao;

	public static class FolderDiff {
		List<MailFolder> deletedFolders;
		List<String> addedFolderNames;

		public FolderDiff(List<MailFolder> deletedFolders, List<String> addedFolderNames) {
			super();
			this.deletedFolders = deletedFolders;
			this.addedFolderNames = addedFolderNames;
		}

		public List<MailFolder> getDeletedFolders() {
			return deletedFolders;
		}

		public void setDeletedFolders(List<MailFolder> deletedFolders) {
			this.deletedFolders = deletedFolders;
		}

		public List<String> getAddedFolderNames() {
			return addedFolderNames;
		}

		public void setAddedFolderNames(List<String> addedFolderNames) {
			this.addedFolderNames = addedFolderNames;
		}
	}

	public List<MailFolder> sync(MailAccount mailAccountModel, List<MailFolder> localFolderModels)
	        throws MessagingException {
		Store store = mailServerConnector.getStore(mailAccountModel);

		List<MailFolder> mailFolderModels = mailFolderDao.getMailFoldersByAccountId(mailAccountModel.getMailAccountId());

		// TODO
		return null;
	}

	private void getFolderInfo(Store store) throws MessagingException {
		getFolderInfo(store.getDefaultFolder(), "	");
	}

	private void getFolderInfo(Folder folder, String tab) throws MessagingException {
		Folder[] folders = folder.list();
		System.out.println(tab + "folders=" + folders.length);
		for (Folder f : folders) {
			System.out.println(tab + "folder=" + f.getName());
			getFolderInfo(f, tab + "	");
		}
	}
}
