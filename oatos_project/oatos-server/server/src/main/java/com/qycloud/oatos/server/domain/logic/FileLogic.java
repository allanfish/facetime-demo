package com.qycloud.oatos.server.domain.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conlect.oatos.dto.client.FileDTO;
import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.NetworkFilesDTO;
import com.conlect.oatos.dto.client.SaveFileDTO;
import com.conlect.oatos.dto.client.SendFileDTO;
import com.conlect.oatos.dto.client.SendFileResultDTO;
import com.conlect.oatos.dto.client.ShareFileUpdateDTO;
import com.conlect.oatos.dto.client.TakePictureDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.FilePermission;
import com.conlect.oatos.dto.status.MessageStatus;
import com.conlect.oatos.dto.status.MessageType;
import com.conlect.oatos.dto.status.UserType;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.dao.CustomerMapper;
import com.qycloud.oatos.server.dao.PersonalFileMapper;
import com.qycloud.oatos.server.dao.PersonalFolderMapper;
import com.qycloud.oatos.server.dao.RolePermissionMapper;
import com.qycloud.oatos.server.dao.ShareFileMapper;
import com.qycloud.oatos.server.dao.UserMapper;
import com.qycloud.oatos.server.domain.entity.Customer;
import com.qycloud.oatos.server.domain.entity.PersonalFile;
import com.qycloud.oatos.server.domain.entity.PersonalFolder;
import com.qycloud.oatos.server.domain.entity.RolePermission;
import com.qycloud.oatos.server.domain.entity.ShareFile;
import com.qycloud.oatos.server.domain.entity.User;
import com.qycloud.oatos.server.util.FilePermissionUtil;
import com.qycloud.oatos.server.util.LogicException;

/**
 * file logic
 * 
 * @author yang
 * 
 */
@Service("FileLogic")
public class FileLogic {

	@Autowired
	private SequenceLogic sequence;
	@Autowired
	private PersonalFileMapper personalFileMapper;
	@Autowired
	private PersonalFolderMapper personalFolderMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ShareFileMapper shareFileMapper;

	// 客户 DAO
	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	@Autowired
	private ShareDiskLogic shareDiskLogic;
	
	@Autowired
	private PersonalDiskLogic personalDiskLogic;

	public String copySystemFile(SaveFileDTO saveFileDTO) {
		PersonalFile fileModel = new PersonalFile();
		fileModel.setUserId(saveFileDTO.getToId());
		fileModel.setFileName(saveFileDTO.getName());
		fileModel.setFolderId(saveFileDTO.getFolderId());
		fileModel.setFileGuid(saveFileDTO.getNewGuid());
		fileModel.setFileSize(saveFileDTO.getSize());
		fileModel.setThumb(saveFileDTO.getThumb());
		fileModel.setRemark(saveFileDTO.getRemark());
		
		PersonalFolder param = new PersonalFolder();
		param.setUserId(saveFileDTO.getToId());
		param.setFolderName(CommConstants.ReceivedFileFolderName);
		PersonalFolder folder = personalFolderMapper.getDefaultFolder(param);
		fileModel.setFolderId(folder.getFolderId());
		
		personalDiskLogic.addPersonalFile(fileModel);
		return CommConstants.OK_MARK;
	}

	@Transactional
	public String sendFile(SendFileDTO sendFileDTO) {
		// add message
		MessageDTO message = new MessageDTO();
		message.setMessageType(sendFileDTO.getType());
		message.setSender(sendFileDTO.getSendId());
		message.setReceiver(sendFileDTO.getReceiverId());
		message.setStatus(MessageStatus.New);
		message.setSendDate(new Date());

		PersonalFile fileModel = new PersonalFile(sendFileDTO.getFileDTO());
		NetworkFileDTO fileDTO = fileModel.toFileDTO();
		fileDTO.setType(CommConstants.FILE_TYPE_TEMP);
		User sendUser = userMapper.getUser(sendFileDTO.getSendId());
		if (sendUser != null) {
			message.setFromUser(sendUser.getUserName());
			// add file to sender's disk
			PersonalFolder sendFolder = new PersonalFolder();
			sendFolder.setFolderName(CommConstants.SendFileFolderName);
			sendFolder.setUserId(sendFileDTO.getSendId());
			long sendFolderId;
			PersonalFolder folder = personalFolderMapper.getDefaultFolder(sendFolder);
			if (folder == null) {
				sendFolderId = sequence.getNextId();
				sendFolder.setFolderId(sendFolderId);
				personalFolderMapper.addPersonalFolder(sendFolder);
			}
			else {
				sendFolderId = folder.getFolderId();
			}

			List<PersonalFile> fileModels = personalFileMapper.getFilesByFolderId(sendFolderId);
			fileModel.setUserId(sendFileDTO.getSendId());
			fileModel.setFolderId(sendFolderId);
			fileModel.setFileName(generatedDiskFileName(fileModels, fileDTO.getName()));

			// add file in db
			personalDiskLogic.addPersonalFile(fileModel);
			fileDTO = fileModel.toFileDTO();
			fileDTO.setType(CommConstants.FILE_TYPE_ONLINEDISK);
		}
		else {
			Customer sendCustomer = customerMapper.getCustomerById(sendFileDTO.getSendId());
			if (sendCustomer != null) {
				message.setFromUser(sendCustomer.getCustomerIP());
			}
			else {
				// TODO no sender name
			}
		}
		User receiveUser = userMapper.getUser(sendFileDTO.getReceiverId());
		if (receiveUser != null) {
			message.setReceiverName(receiveUser.getUserName());
			// add file to receiver's disk
			if (MessageType.InstantFile.equals(sendFileDTO.getType())) {
				PersonalFolder receiveFolder = new PersonalFolder();
				receiveFolder.setFolderName(CommConstants.ReceivedFileFolderName);
				receiveFolder.setUserId(sendFileDTO.getReceiverId());
				long receiveFolderId;
				PersonalFolder folder = personalFolderMapper.getDefaultFolder(receiveFolder);
				if (folder == null) {
					receiveFolderId = sequence.getNextId();
					receiveFolder.setFolderId(receiveFolderId);
					personalFolderMapper.addPersonalFolder(receiveFolder);
				}
				else {
					receiveFolderId = folder.getFolderId();
				}

				List<PersonalFile> fileModels = personalFileMapper.getFilesByFolderId(receiveFolderId);
				fileModel.setFileName(sendFileDTO.getFileDTO().getName());
				fileModel.setUserId(sendFileDTO.getReceiverId());
				fileModel.setFolderId(receiveFolderId);
				fileModel.setFileName(generatedDiskFileName(fileModels, fileDTO.getName()));

				// add file in db
				personalDiskLogic.addPersonalFile(fileModel);
				fileDTO = fileModel.toFileDTO();
				fileDTO.setType(CommConstants.FILE_TYPE_ONLINEDISK);
			}
		}
		else {
			Customer receiveCustomer = customerMapper.getCustomerById(sendFileDTO.getReceiverId());
			if (receiveCustomer != null) {
				message.setReceiverName(receiveCustomer.getCustomerIP());
			}
			else {
				// TODO no receiver name
			}
		}

		SendFileResultDTO resultDTO = new SendFileResultDTO();
		resultDTO.setSatus(CommConstants.OK_MARK);
		resultDTO.setFileDTO(fileDTO);
		message.setMessageBody(PojoMapper.toJson(resultDTO));
		return PojoMapper.toJson(message);
	}

	@Transactional
	public String acceptFile(long userId, List<NetworkFileDTO> fileList) {
		PersonalFolder receiveFolder = new PersonalFolder();
		receiveFolder.setFolderName(CommConstants.ReceivedFileFolderName);
		receiveFolder.setUserId(userId);
		long receiveFolderId;
		PersonalFolder folder = personalFolderMapper.getDefaultFolder(receiveFolder);
		if (folder == null) {
			receiveFolderId = sequence.getNextId();
			receiveFolder.setFolderId(receiveFolderId);
			personalFolderMapper.addPersonalFolder(receiveFolder);
		}
		else {
			receiveFolderId = folder.getFolderId();
		}

		List<PersonalFile> fileModels = personalFileMapper.getFilesByFolderId(receiveFolderId);

		List<NetworkFileDTO> acceptList = new ArrayList<NetworkFileDTO>();
		for (NetworkFileDTO fileDTO : fileList) {
			PersonalFile fileModel = new PersonalFile(fileDTO);
			fileModel.setUserId(userId);
			fileModel.setFileName(generatedDiskFileName(fileModels, fileDTO.getName()));
			fileModel.setFolderId(receiveFolderId);

			personalDiskLogic.addPersonalFile(fileModel);
			acceptList.add(fileModel.toFileDTO());
		}

		NetworkFilesDTO filesDTO = new NetworkFilesDTO();
		filesDTO.setNetworkFileDTOList(acceptList);
		return PojoMapper.toJson(filesDTO);
	}

	@Transactional
	public String screenshot(NetworkFileDTO fileDTO) {
		PersonalFolder pictureFolder = new PersonalFolder();
		pictureFolder.setFolderName(CommConstants.PictureFolderName);
		pictureFolder.setUserId(fileDTO.getUserId());
		long receiveFolderId;
		// get folder id
		PersonalFolder folder = personalFolderMapper.getDefaultFolder(pictureFolder);
		if (folder == null) {
			receiveFolderId = sequence.getNextId();
			pictureFolder.setFolderId(receiveFolderId);
			personalFolderMapper.addPersonalFolder(pictureFolder);
		}
		else {
			receiveFolderId = folder.getFolderId();
		}

		List<PersonalFile> fileModels = personalFileMapper.getFilesByFolderId(receiveFolderId);
		PersonalFile fileModel = new PersonalFile(fileDTO);
		// generated file name
		fileModel.setFileName(generatedDiskFileName(fileModels, fileDTO.getName()));
		fileModel.setFolderId(receiveFolderId);

		// add to disk
		personalDiskLogic.addPersonalFile(fileModel);

		return CommConstants.OK_MARK;
	}

	@Transactional
	public String tackPictureAndSave(TakePictureDTO pictureDTO) {
		String fileName = "";
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(pictureDTO.getType())) {
			// check permission
			checkPermissionAndThrowException(pictureDTO.getUserId(), pictureDTO.getFolderId(), FilePermission.Upload);

			ShareFileUpdateDTO file = new ShareFileUpdateDTO();
			file.setEnterpriseId(pictureDTO.getEnterpriseId());
			file.setFolderId(pictureDTO.getFolderId());
			file.setGuid(pictureDTO.getGuid());
			file.setSize(pictureDTO.getSize());
			file.setThumb(pictureDTO.getThumb());

			file.setUserId(pictureDTO.getUserId());

			List<ShareFile> fileList = shareFileMapper.getShareFilesByFolderId(pictureDTO.getFolderId());
			// generated file name
			fileName = generatedShareFileName(fileList, pictureDTO.getName());
			file.setName(fileName);
			// add to share disk
			shareDiskLogic.addFile(file);
		}
		else {
			Long folderId = pictureDTO.getFolderId();
			if (folderId == null) {
				// get folder id
				PersonalFolder pictureFolder = new PersonalFolder();
				pictureFolder.setFolderName(CommConstants.PictureFolderName);
				pictureFolder.setUserId(pictureDTO.getUserId());

				PersonalFolder folder = personalFolderMapper.getDefaultFolder(pictureFolder);
				if (folder == null) {
					folderId = sequence.getNextId();
					pictureFolder.setFolderId(folderId);
					personalFolderMapper.addPersonalFolder(pictureFolder);
				}
				else {
					folderId = folder.getFolderId();
				}
			}
			List<PersonalFile> fileModels = personalFileMapper.getFilesByFolderId(folderId);
			PersonalFile fileModel = new PersonalFile();
			fileModel.setUserId(pictureDTO.getUserId());
			fileModel.setFileGuid(pictureDTO.getGuid());
			fileModel.setFileSize(pictureDTO.getSize());
			fileModel.setThumb(pictureDTO.getThumb());
			// generated file name
			fileName = generatedDiskFileName(fileModels, pictureDTO.getName());
			fileModel.setFileName(fileName);
			fileModel.setFolderId(folderId);

			// add to disk
			personalDiskLogic.addPersonalFile(fileModel);
		}
		return fileName;
	}

	/**
	 * check permission
	 * 
	 * @param userId
	 * @param folderId
	 * @param operation
	 * @return
	 */
	private boolean checkPermissionAndThrowException(long userId, long folderId, FilePermission operation) {
		User user = userMapper.getUser(userId);
		boolean check = false;
		if (UserType.Administrator == user.getUserType().longValue()) {
			check = true;
		}
		else {
			List<RolePermission> permissions = rolePermissionMapper.getRolePermisssionsByUserIdAndFolderId(userId,
			        folderId);
			check = FilePermissionUtil.checkPermission(folderId, operation, permissions);
		}
		if (!check) {
			throw new LogicException(ErrorType.errorNoPermission);
		}
		return check;
	}

	@Transactional
    public void updateFilePageCount(FileDTO fileDTO) {
	    if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getType())) {
	        shareFileMapper.updateShareFilePageCount(fileDTO.getFileId(), fileDTO.getPageCount());
        } else if (CommConstants.FILE_TYPE_ONLINEDISK.equals(fileDTO.getType())) {
	        personalFileMapper.updatePersonalFilePageCount(fileDTO.getFileId(), fileDTO.getPageCount());
		}
    }

	/**
	 * 自动重命名
	 * 
	 * @return
	 */
	private String generatedDiskFileName(List<PersonalFile> fileList, String name) {
		String newName = name;
		if (fileList != null && fileList.size() > 0) {
			List<String> names = new ArrayList<String>();
			for (PersonalFile file : fileList) {
				names.add(file.getFileName());
			}
			newName = generatedFileName(names, name);
        }
		return newName;

	}

	private String generatedShareFileName(List<ShareFile> fileList, String name) {
		String newName = name;
		if (fileList != null && fileList.size() > 0) {
			List<String> names = new ArrayList<String>();
			for (ShareFile file : fileList) {
				names.add(file.getName());
			}
			newName = generatedFileName(names, name);
        }
		return newName;

	}
	
	/**
	 * 自动重命名
	 * 
	 * @param folderId
	 * @param name
	 * @return
	 */
	private String generatedFileName(List<String> fileList, String name) {
		String newName = name;
		String prefix = CommonUtil.getFilePrefixName(name);
		String suffix = CommonUtil.getFileSuffixName(name);
		if (fileList != null && fileList.size() > 0) {
			int i = 0;
			for (i = 0; i < Integer.MAX_VALUE; i++) {
				String temp = prefix;
				if (i > 0) {
					temp = prefix + "(" + i + ")";
				}
				boolean exist = false;
				for (String file : fileList) {
					if (temp.equalsIgnoreCase(CommonUtil.getFilePrefixName(file))
					        && suffix.equalsIgnoreCase(CommonUtil.getFileSuffixName(file))) {
						exist = true;
						break;
					}
				}
				if (!exist) {
					prefix = temp;
					break;
				}
			}

		}
		if (suffix == null || suffix.equals("")) {
			newName = prefix;
		}
		else {
			newName = prefix + "." + suffix;
		}
		return newName;
	}

}
