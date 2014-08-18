package com.qycloud.oatos.server.domain.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conlect.oatos.dto.autobean.INetworkFileDTO;
import com.conlect.oatos.dto.client.FileDTO;
import com.conlect.oatos.dto.client.FolderAndFileDTO;
import com.conlect.oatos.dto.client.FolderAndFileParamDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.NetworkFolderDTO;
import com.conlect.oatos.dto.client.SaveFileDTO;
import com.conlect.oatos.dto.client.user.PersonDiskAllocDTO;
import com.conlect.oatos.dto.client.user.PersonDiskAllocListDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.FileStatus;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.dao.EnterpriseMapper;
import com.qycloud.oatos.server.dao.PersonalFileMapper;
import com.qycloud.oatos.server.dao.PersonalFolderMapper;
import com.qycloud.oatos.server.dao.UserMapper;
import com.qycloud.oatos.server.domain.entity.Enterprise;
import com.qycloud.oatos.server.domain.entity.PersonalFile;
import com.qycloud.oatos.server.domain.entity.PersonalFolder;
import com.qycloud.oatos.server.domain.entity.User;
import com.qycloud.oatos.server.util.LogicException;

/**
 * 对象名称：网盘逻辑实现层
 * 
 * 作者： 秦利军
 * 
 * 完成日期：
 * 
 * 对象内容：
 */
@Service("PersonalDiskLogic")
public class PersonalDiskLogic {
	// 生成主键ID对象
	@Autowired
	private SequenceLogic sequence;
	// 文件管理对象
	@Autowired
	private PersonalFileMapper personalFileMapper;
	// 文件夹管理对象
	@Autowired
	private PersonalFolderMapper personalFolderMapper;
	// 用户管理对象
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private EnterpriseMapper enterpriseMapper;

	/**
	 * 增加文件夹
	 * @param folder
	 */
	public String addNetworkFolder(PersonalFolder folder) throws LogicException {
		String result = null;
		PersonalFolder temp = personalFolderMapper.getSameFolder(folder);
		if (temp == null) {
			// 给文件夹分配ID
			long folderId = sequence.getNextId();
			folder.setFolderId(folderId);
			personalFolderMapper.addPersonalFolder(folder);
			result = PojoMapper.toJson(folder.toFolderDTO());
		}
		else
		// 如果用户同一文件夹下存在同名文件夹，则抛出异常
		{
			result = ErrorType.errorSameFolder.name();
		}
		return result;
	}

	/**
	 * 修改文件夹
	 * @param folder
	 */
	public void updateNetworkFoler(PersonalFolder folder) throws LogicException {
		PersonalFolder temp = null;
		temp = personalFolderMapper.getSameFolder(folder);
		if (temp == null || temp.getFolderId() == folder.getFolderId()) {
			personalFolderMapper.updatePersonalFolder(folder);
		}
		else
		// 如果修改后的文件夹，在用户同一文件夹下存在同名文件夹，则抛出异常
		{
			throw new LogicException(ErrorType.errorSameFolder);
		}
	}

	/**
	 * 获取用户下的文件夹
	 * @param userId
	 * @return
	 */
	public List<NetworkFolderDTO> getFoldersByUserId(long userId) {
		List<PersonalFolder> folderModels = personalFolderMapper.getFoldersByUserId(userId);
		List<NetworkFolderDTO> folderDTOs = new ArrayList<NetworkFolderDTO>();
		if (folderModels != null) {
			for (PersonalFolder f : folderModels) {
				folderDTOs.add(f.toFolderDTO());
			}
		}
		return folderDTOs;
	}
	
	/**
	 * 取邮件附件文件夹
	 * @param userId
	 * @return
	 */
	public NetworkFolderDTO getEmailFolder(long userId) {
		PersonalFolder model = new PersonalFolder();
		model.setUserId(userId);
		model.setFolderName(CommConstants.EmailAttachFolderName);
		long emailFolderId = -1;
		try {
			// 数据库中已有邮件文件夹
			emailFolderId = personalFolderMapper.getDefaultFolder(model).getFolderId();
			model.setFolderId(emailFolderId);
		}
		catch (Exception e) {
			// 数据库中还未有邮件文件夹
			emailFolderId = sequence.getNextId();
			model.setFolderId(emailFolderId);
			personalFolderMapper.addPersonalFolder(model);
		}
		return model.toFolderDTO();
	}

	/**
	 * 修改文件
	 * @param file
	 */
	public void updateNetworkFile(PersonalFile file) throws LogicException {
		PersonalFile temp = null;
		temp = personalFileMapper.getSameNameFile(file);
		if (temp == null || temp.getFileId() == file.getFileId()) {
			personalFileMapper.updatePersonalFile(file);
		}
		else
		// 如果修改后的文件，在用户同一文件夹下存在同名文件，则抛出异常
		{
			throw new LogicException(ErrorType.errorSameFile);
		}
	}

	/**
	 * 获取用户文件
	 * @param UserId
	 * @return
	 */
	public List<NetworkFileDTO> getFilesByUserId(long userId) {
		List<PersonalFile> fileModels = personalFileMapper.getFilesByUserId(userId);
		List<NetworkFileDTO> fileDTOs = new ArrayList<NetworkFileDTO>();
		if (fileModels != null) {
			for (PersonalFile f : fileModels) {
				fileDTOs.add(f.toFileDTO());
			}
		}
		return fileDTOs;
	}

	/**删掉文件或文件夹
	 */
	@Transactional
	public String deleteFolderAndFile(FolderAndFileDTO folderAndFileDTO) {
		List<PersonalFolder> folderList = toFolderModels(folderAndFileDTO.getForderList());
		List<PersonalFile> fileList = toFileModels(folderAndFileDTO.getFileList());

		if (fileList.size() > 0) {
			// 刪除db中文件
			for (PersonalFile f : fileList) {
				personalFileMapper.deletePersonalFile(f.getFileId());
			}
		}

		if (folderList.size() > 0) {
			// 删除文件夹
			for (PersonalFolder f : folderList) {
				personalFolderMapper.deletePersonalFolder(f.getFolderId());
			}
		}
		return CommConstants.OK_MARK;
	}
	
	/**
	 * 更新文件或文件夹
	 * @param folderAndFileDTO
	 */
	@Transactional
	public void updateFolderAndFile(FolderAndFileDTO folderAndFileDTO) {
		List<PersonalFolder> folderList = toFolderModels(folderAndFileDTO.getForderList());
		List<PersonalFile> fileList = toFileModels(folderAndFileDTO.getFileList());
		if (folderList.size() > 0) {
			for (PersonalFolder f : folderList) {
				personalFolderMapper.updatePersonalFolder(f);
			}
		}
		if (fileList.size() > 0) {
			for (PersonalFile f : fileList) {
				personalFileMapper.updatePersonalFile(f);
			}
		}
	}
	
	/**
	 * 取所有网盘文件夹和文件
	 * @param userId
	 * @return
	 */
	public FolderAndFileDTO getFolderAndFileByUserId(long userId) {
		FolderAndFileDTO folderAndFileDTO = new FolderAndFileDTO();
		folderAndFileDTO.setForderList(getFoldersByUserId(userId));
		folderAndFileDTO.setFileList(getFilesByUserId(userId));
		return folderAndFileDTO;
	}

	private List<PersonalFolder> toFolderModels(List<NetworkFolderDTO> folderDTOs) {
		List<PersonalFolder> folderList = new ArrayList<PersonalFolder>();
		if (folderDTOs != null) {
			for (NetworkFolderDTO folderDTO : folderDTOs) {
				PersonalFolder folder = new PersonalFolder(folderDTO);
				folderList.add(folder);
			}
		}
		return folderList;
	}

	private List<PersonalFile> toFileModels(List<NetworkFileDTO> fileDTOs) {
		List<PersonalFile> fileList = new ArrayList<PersonalFile>();
		if (fileDTOs != null) {
			for (NetworkFileDTO fileDTO : fileDTOs) {
				PersonalFile file = new PersonalFile(fileDTO);
				fileList.add(file);
			}
		}
		return fileList;
	}
	
	/**
	 * 增加个人用户文件
	 * @param file
	 * @return
	 */
	@Transactional
	public long addPersonalFile(PersonalFile file) {
		PersonalFile temp = personalFileMapper.getSameNameFile(file);
		if (temp == null) {
			addFile(file);
		}
		else
		// 在用户同一文件夹下存在同名文件，则抛出异常
		{
			throw new LogicException(ErrorType.errorSameFile);
		}
		return file.getFileId();
	}
	
	/**
	 * 删除个人用户文件
	 * @param fileId
	 */
	public void deleteFile(long fileId) {
		personalFileMapper.deletePersonalFile(fileId);
	}
	
	/**
	 * 个人网盘检查是否存在同名文件
	 * @param fileModel
	 * @return
	 */
	public String checkPersonalFileUpload(NetworkFileDTO fileDTO) {
		String result = CommConstants.OK_MARK;
		PersonalFile fileModel = new PersonalFile(fileDTO);
		PersonalFile temp = personalFileMapper.getSameNameFile(fileModel);
		if (temp == null) {
			// 查询用户的信息，获得剩余的磁盘空间
			User mailUser = userMapper.getUser(fileModel.getUserId());
			long usedSize = personalFileMapper.getFileSizeSumByUserId(fileModel.getUserId());
			if (mailUser.getDiskSize() < (fileModel.getFileSize() + usedSize)) {
				// 用户空间不足
				result = ErrorType.errorNoSpace.name();
			}
		}
		else {
			// 在用户同一文件夹下存在同名文件
			result = ErrorType.errorSameFile.name();
		}
		return result;
	}
	
	/**
	 * 个人网盘获取单个文件
	 * @param fileId
	 * @return
	 */
	public NetworkFileDTO getFileById(long fileId) {
		PersonalFile fileModel = personalFileMapper.getFileById(fileId);
		return fileModel.toFileDTO();
	}
	
	/**
	 * 修改个人网盘的文件夹和文件
	 * @param folderAndFileDTO
	 */
	@Transactional
	public void restoreFolderAndFile(FolderAndFileDTO folderAndFileDTO) {
		List<PersonalFolder> folderList = toFolderModels(folderAndFileDTO.getForderList());
		List<PersonalFile> fileList = toFileModels(folderAndFileDTO.getFileList());
		long size = 0;
		Long userId = null;
		for (PersonalFile f : fileList) {
			userId = f.getUserId();
			size = size + f.getFileSize();
		}
		if (userId != null) {
			// 查询用户的信息，获得剩余的磁盘空间
			User mailUser = userMapper.getUser(userId);
			long usedSize = personalFileMapper.getFileSizeSumByUserId(userId);
			if (mailUser.getDiskSize() < (size + usedSize)) {
				// 用户空间不足
				throw new LogicException(ErrorType.errorNoSpace);
			}
		}

		for (PersonalFolder f : folderList) {
			f.setDeleted(FileStatus.active);
		}
		for (PersonalFile f : fileList) {
			f.setDeleted(FileStatus.active);
		}
		if (folderList.size() > 0) {
			for (PersonalFolder f : folderList) {
				personalFolderMapper.updatePersonalFolder(f);
			}
		}
		if (fileList.size() > 0) {
			for (PersonalFile f : fileList) {
				personalFileMapper.updatePersonalFile(f);
			}
		}
	}
	
	/**
	 * 获取用户文件夹，包括回收站中文件
	 * @param userId
	 * @return
	 */
	public List<NetworkFolderDTO> getAllFoldersByUserId(long userId) {
		List<PersonalFolder> folderModels = personalFolderMapper.getAllFoldersByUserId(userId);
		List<NetworkFolderDTO> folderDTOs = new ArrayList<NetworkFolderDTO>();
		if (folderModels != null) {
			for (PersonalFolder f : folderModels) {
				folderDTOs.add(f.toFolderDTO());
			}
		}
		return folderDTOs;
	}
	
	/**
	 * 获取用户文件包括回收站中文件
	 * @param userId
	 * @return
	 */
	public List<NetworkFileDTO> getAllFilesByUserId(long userId) {
		List<PersonalFile> fileModels = personalFileMapper.getAllFilesByUserId(userId);
		List<NetworkFileDTO> fileDTOs = new ArrayList<NetworkFileDTO>();
		if (fileModels != null) {
			for (PersonalFile f : fileModels) {
				fileDTOs.add(f.toFileDTO());
			}
		}
		return fileDTOs;
	}
	
	/**
	 * 取所有网盘文件夹和文件
	 * @param userId
	 * @return
	 */
	public FolderAndFileDTO getAllFolderAndFileByUserId(long userId) {
		FolderAndFileDTO folderAndFileDTO = new FolderAndFileDTO();
		folderAndFileDTO.setForderList(getAllFoldersByUserId(userId));
		folderAndFileDTO.setFileList(getAllFilesByUserId(userId));
		return folderAndFileDTO;
	}

	public long addEmailFile(INetworkFileDTO file) {
		PersonalFile fileModel = new PersonalFile(file);
		long folderId = getEmailFolder(file.getUserId()).getFolderId();
		fileModel.setFolderId(folderId);
		fileModel.setFileId(sequence.getNextId());
		return addFile(fileModel);
	}
	
	/**
	 * 同步个人网盘文件
	 * @param file
	 * @return
	 */
	@Transactional
	public String syncPersonalFile(PersonalFile file) {
		PersonalFile resultFile = null;
		if (file.getFileId() == null) {
			addPersonalFile(file);
			resultFile = file;
		}
		else {
			PersonalFile f = personalFileMapper.getFileById(file.getFileId());
			f.setFileName(file.getFileName());
			f.setFileGuid(file.getFileGuid());
			f.setFileSize(file.getFileSize());
			f.setUpdateTime(new Date());
			personalFileMapper.updatePersonalFile(f);
			resultFile = f;
		}
		return PojoMapper.toJson(resultFile.toFileDTO());
	}

	/**
	 * 通过文件名和用户token得到个人网盘中邮件附件的文件列表
	 * @param userId
	 * @param fileNames
	 * @return
	 */
	public List<NetworkFileDTO> getAttachFiles(String userId, String fileNames) {
		NetworkFolderDTO emailFolder = getEmailFolder(Long.parseLong(userId));
		List<PersonalFile> fileList = personalFileMapper.getFileList(userId, emailFolder.getFolderId(), fileNames);
		List<NetworkFileDTO> dtoList = new ArrayList<NetworkFileDTO>(fileList.size());
		for (PersonalFile model : fileList)
			dtoList.add(model.toFileDTO());
		return dtoList;
	}
	
	/**
	 * 个人网盘通过文件夹名和用户ID获得文件
	 * @param userId
	 * @param folderName
	 * @param name
	 * @return
	 */
	public NetworkFileDTO getFile(long userId, String folderName, String name) {
		PersonalFolder param = new PersonalFolder();
		param.setUserId(userId);
		param.setFolderName(folderName);
		PersonalFolder folder = personalFolderMapper.getDefaultFolder(param);
		List<PersonalFile> fileList = personalFileMapper.getFileList(userId + "", folder.getFolderId(), name);
		if (fileList == null || fileList.size() == 0)
			return null;
		return fileList.get(0).toFileDTO();
	}
	
	/**
	 * 获取个人网盘文件夹信息
	 * @param folderId
	 * @return
	 */
    public NetworkFolderDTO getPrivateFolder(long folderId) {
		return personalFolderMapper.getFolderById(folderId).toFolderDTO();
    }
    
    /**
     * 个人网盘文件同步上传之前，检查文件版本，网盘空间，权限，同名文件
     * @param fileDTO
     * @return
     */
    public String checkPersonalFileSyncUpload(NetworkFileDTO fileDTO) {
    	String result = CommConstants.OK_MARK;
    	if (fileDTO.getFileId() != null) {
    		PersonalFile f = personalFileMapper.getFileById(fileDTO.getFileId());
    		if (!f.getFileGuid().equals(fileDTO.getGuid())) {
				result = ErrorType.errorVersionConflict.name();
			}
		} else {
			result = checkPersonalFileUpload(fileDTO);
		}
	    return result;
    }
    
    /**
     * 按文件夹取个人网盘子文件夹和文件，取顶层文件夹时，文件夹ID传null
     * @param param
     * @return
     */
    public FolderAndFileDTO getPersonalFolderAndFileByFolderId(FolderAndFileParamDTO param) {
    	List<PersonalFolder> folders = new ArrayList<PersonalFolder>();
    	List<PersonalFile> files = new ArrayList<PersonalFile>();
    	if (param.getFolderIds() != null && param.getFolderIds().size() > 0) {
			for (Long folderId : param.getFolderIds()) {
				List<PersonalFolder> subFolders = personalFolderMapper.getFoldersByUserIdAndFolderId(param.getUserId(), folderId);
				List<PersonalFile> subFiles = personalFileMapper.getFilesByUserIdAndFolderId(param.getUserId(), folderId);
				folders.addAll(subFolders);
				files.addAll(subFiles);
			}
		} else {
			folders = personalFolderMapper.getFoldersByUserIdAndFolderId(param.getUserId(), null);
			files = personalFileMapper.getFilesByUserIdAndFolderId(param.getUserId(), null);
		}
		FolderAndFileDTO folderAndFileDTO = new FolderAndFileDTO();
		List<NetworkFolderDTO> folderDTOs = new ArrayList<NetworkFolderDTO>();
		for (PersonalFolder f : folders) {
	        folderDTOs.add(f.toFolderDTO());
        }
		folderAndFileDTO.setForderList(folderDTOs);

		List<NetworkFileDTO> fileDTOs = new ArrayList<NetworkFileDTO>();
		for (PersonalFile f : files) {
	        fileDTOs.add(f.toFileDTO());
        }
		folderAndFileDTO.setFileList(fileDTOs);
	    return folderAndFileDTO;
    }
	
	private long addFile(PersonalFile file) {
		// 查询用户的信息，获得剩余的磁盘空间
		User mailUser = userMapper.getUser(file.getUserId());
		long usedSize = personalFileMapper.getFileSizeSumByUserId(file.getUserId());
		if (mailUser.getDiskSize() >= (file.getFileSize() + usedSize)) {
			file.setFileId(sequence.getNextId());
			file.setFileType("");
			file.setUpdateTime(new Date());
			personalFileMapper.addPersonalFile(file);
		}
		else
		// 用户空间不足时抛出异常
		{
			throw new LogicException(ErrorType.errorNoSpace);
		}
		return file.getFileId();
	}
	
	/**
	 * 得到个人网盘回收站中的文件和文件夹
	 * @param userId
	 * @return
	 */
	public FolderAndFileDTO getPersonalFolderAndFileInRecycle(long userId) {
		List<NetworkFolderDTO> networkFolderDTOs = getPersonalFolderInRecycleByUserId(userId);
		List<NetworkFileDTO> networkFileDTOs = getPersonalFileInRecycleByUserId(userId);
		
		FolderAndFileDTO folderAndFileDTO = getFolderAndFileDTO(networkFolderDTOs,networkFileDTOs);
		return folderAndFileDTO;
		
	}

	/**
	 * @param networkFolderDTOs
	 * @param networkFileDTOs
	 * @return
	 */
	private FolderAndFileDTO getFolderAndFileDTO(
			List<NetworkFolderDTO> networkFolderDTOs,
			List<NetworkFileDTO> networkFileDTOs) {
		FolderAndFileDTO folderAndFileDTO = new FolderAndFileDTO();
		folderAndFileDTO.setFileList(networkFileDTOs);
		folderAndFileDTO.setForderList(networkFolderDTOs);
		return folderAndFileDTO;
	}

	/**
	 * @param userId
	 * @return
	 */
	private List<NetworkFileDTO> getPersonalFileInRecycleByUserId(long userId) {
		List<NetworkFileDTO> networkFileDTOs = new ArrayList<NetworkFileDTO>();
		List<PersonalFile> personalFiles = personalFileMapper.getPersonalFileInRecycleByUserId(userId);
		if (personalFiles!=null) {
			for (PersonalFile personalFile : personalFiles) {
				networkFileDTOs.add(personalFile.toFileDTO());
			}
		}
		
		return networkFileDTOs;
	}

	/**
	 * @param userId
	 * @return
	 */
	private List<NetworkFolderDTO> getPersonalFolderInRecycleByUserId(
			long userId) {
		List<NetworkFolderDTO> networkFolderDTOs = new ArrayList<NetworkFolderDTO>();
		List<PersonalFolder> personalFolders = personalFolderMapper.getPersonalFolderListInRecycleByUserId(userId);
		if(personalFolders!=null){
			for (PersonalFolder personalFolder : personalFolders) {
				networkFolderDTOs.add(personalFolder.toFolderDTO());
			}
		}
		
		return networkFolderDTOs;
	}

    public String checkUserDiskSizeExceed(long userId) {
	    return personalFileMapper.getFileSizeSumByUserId(userId) >= CommConstants.PERSONAL_DISK_SIZE ? CommConstants.TRUE_MARK : CommConstants.FALSE_MARK;
	    
    }

    @Transactional
	public String allocPersonDisk(PersonDiskAllocListDTO personDiskList) {
		Enterprise enterprise = enterpriseMapper.getEnterpriseById(personDiskList.getEntId());

		checkPersonDiskAlloc(enterprise, personDiskList);

		for (PersonDiskAllocDTO personDisk : personDiskList.getPersonDiskList()) {
			long diskSize = CommConstants.PERSONAL_DISK_SIZE + personDisk.getAddSize() * 1024 * 1024;
			userMapper.updateDiskSize(personDisk.getUserId(), diskSize);
		}
		return CommConstants.OK_MARK;
	}

	private int  checkPersonDiskAlloc(Enterprise enterprise, PersonDiskAllocListDTO personDiskList) {
		long remainSize = enterprise.getPersonalDiskSize() - userMapper.getUserCountByEntId(enterprise.getEnterpriseId() * CommConstants.PERSONAL_DISK_SIZE) ;
		int totalSizeGB = 0;
		for (PersonDiskAllocDTO personDisk : personDiskList.getPersonDiskList()) {
			if (personDisk.getOriginalAddSize() > 0 && personDisk.getOriginalAddSize() >= personDisk.getAddSize()) {
				totalSizeGB -= personDisk.getOriginalAddSize() - personDisk.getAddSize();
			}
			else {
				totalSizeGB += (personDisk.getAddSize() - personDisk.getOriginalAddSize());
			}

			// 减小了该用户的空间
			if (personDisk.getOriginalAddSize() > 0 && personDisk.getOriginalAddSize() > personDisk.getAddSize()) {
				long sizeUsed = personalFileMapper.getFileSizeSumByUserId(personDisk.getUserId());
				if (sizeUsed > (CommConstants.PERSONAL_DISK_SIZE + personDisk.getAddSize() * 1024 * 1024)) {
					throw new LogicException(ErrorType.errorPersonDiskUsedExceedAllocSize);
				}
			}
		}
		if (remainSize < totalSizeGB * 1024 * 1024) {
			throw new LogicException(ErrorType.errorPersonDiskOverflow);
		}
		return totalSizeGB;
	}

	/**
	 * 取个人网盘已使用空间
	 * @param userId
	 * @return
	 */
	public long getPersonalDiskUsedSizeByUserId(long userId) {
		return personalFileMapper.getFileSizeSumByUserId(userId);
	}

	/**
	 * 检查保存编辑的个人网盘文件
	 * @param fileDTO
	 * @return
	 */
	public String checkSavePersonalFile(SaveFileDTO saveFileDTO) {
		FileDTO fileDTO = new FileDTO();
		fileDTO.setName(saveFileDTO.getName());
		fileDTO.setFolderId(saveFileDTO.getFolderId());
		fileDTO.setGuid(saveFileDTO.getNewGuid());
		fileDTO.setType(saveFileDTO.getToType());
		fileDTO.setSize(saveFileDTO.getSize());
		
		PersonalFile file = new PersonalFile();
		file.setUserId(saveFileDTO.getToId());
		file.setFileName(saveFileDTO.getName());
		file.setFolderId(saveFileDTO.getFolderId());
		// 取同名文件
		PersonalFile temp = personalFileMapper.getSameNameFile(file);
		if (temp == null) {
			// 没有同名文件，新建文件
			fileDTO.setType(CommConstants.FILE_NEW);
		}
		else {
			if (CommConstants.FILE_NEW.equals(saveFileDTO.getType())) {
				// 同名文件错误
				throw new LogicException(ErrorType.errorSameFile);
			}
			else {
				// 覆盖现有文件
				fileDTO.setType(CommConstants.FILE_TYPE_ONLINEDISK);
				fileDTO.setGuid(temp.getFileGuid());
				fileDTO.setFolderId(temp.getFolderId());
				fileDTO.setFileId(temp.getFileId());
				fileDTO.setSize(temp.getFileSize());
			}
		}
		return PojoMapper.toJson(fileDTO);
	}

	/**
	 * 保存编辑的个人网盘文件
	 * @param fileDTO
	 * @return
	 */
	@Transactional
	public String savePersonalFile(SaveFileDTO saveFileDTO) {
		FileDTO fileDTO = new FileDTO();
		fileDTO.setFileId(saveFileDTO.getFileId());
		fileDTO.setName(saveFileDTO.getName());
		fileDTO.setFolderId(saveFileDTO.getFolderId());
		fileDTO.setGuid(saveFileDTO.getNewGuid());
		fileDTO.setType(saveFileDTO.getToType());
		fileDTO.setSize(saveFileDTO.getSize());
		
		PersonalFile file = new PersonalFile();
		file.setFileId(saveFileDTO.getFileId());
		file.setUserId(saveFileDTO.getToId());
		file.setFileGuid(saveFileDTO.getNewGuid());
		file.setFileName(saveFileDTO.getName());
		file.setFolderId(saveFileDTO.getFolderId());
		file.setFileSize(fileDTO.getSize());
		file.setUpdateTime(new Date());
		file.setThumb(saveFileDTO.getThumb());
		file.setRemark(saveFileDTO.getRemark());

		if (CommConstants.FILE_NEW.equals(saveFileDTO.getType())) {
			file.setCreatTime(new Date());
			long fileId = addPersonalFile(file);
			fileDTO.setFileId(fileId);
		}
		else {
			file.setUpdateTime(new Date());
			personalFileMapper.updatePersonalFile(file);
		}
		return PojoMapper.toJson(fileDTO);
	}

}
