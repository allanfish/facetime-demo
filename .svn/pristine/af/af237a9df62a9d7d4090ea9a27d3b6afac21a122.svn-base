package com.qycloud.oatos.server.domain.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conlect.oatos.dto.autobean.INetworkFileDTO;
import com.conlect.oatos.dto.autobean.IShareFileRecordDTO;
import com.conlect.oatos.dto.client.FileDTO;
import com.conlect.oatos.dto.client.FolderAndFileParamDTO;
import com.conlect.oatos.dto.client.SaveFileDTO;
import com.conlect.oatos.dto.client.ShareFileDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;
import com.conlect.oatos.dto.client.ShareFileRecordDTO;
import com.conlect.oatos.dto.client.ShareFileRecordsDTO;
import com.conlect.oatos.dto.client.ShareFileUpdateDTO;
import com.conlect.oatos.dto.client.ShareFilesDTO;
import com.conlect.oatos.dto.client.ShareFolderAndFileDTO;
import com.conlect.oatos.dto.client.ShareFolderAndFileUpdateDTO;
import com.conlect.oatos.dto.client.ShareFolderDTO;
import com.conlect.oatos.dto.client.ShareFolderUpdateDTO;
import com.conlect.oatos.dto.client.ShareHistoryDTO;
import com.conlect.oatos.dto.client.ShareLinkFilesDTO;
import com.conlect.oatos.dto.client.SharePersonalFileDTO;
import com.conlect.oatos.dto.client.ViewFileDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.FilePermission;
import com.conlect.oatos.dto.status.FileStatus;
import com.conlect.oatos.dto.status.UserType;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.dao.AdminFolderMapper;
import com.qycloud.oatos.server.dao.AdminMapper;
import com.qycloud.oatos.server.dao.EnterpriseMapper;
import com.qycloud.oatos.server.dao.FavoriteFileMapper;
import com.qycloud.oatos.server.dao.RolePermissionMapper;
import com.qycloud.oatos.server.dao.ShareFileHistoryMapper;
import com.qycloud.oatos.server.dao.ShareFileMapper;
import com.qycloud.oatos.server.dao.ShareFileRecordMapper;
import com.qycloud.oatos.server.dao.ShareFolderHistoryMapper;
import com.qycloud.oatos.server.dao.ShareFolderMapper;
import com.qycloud.oatos.server.dao.ShareLinkMapper;
import com.qycloud.oatos.server.dao.UserMapper;
import com.qycloud.oatos.server.domain.entity.Admin;
import com.qycloud.oatos.server.domain.entity.AdminFolder;
import com.qycloud.oatos.server.domain.entity.Enterprise;
import com.qycloud.oatos.server.domain.entity.RolePermission;
import com.qycloud.oatos.server.domain.entity.ShareFile;
import com.qycloud.oatos.server.domain.entity.ShareFileHistory;
import com.qycloud.oatos.server.domain.entity.ShareFileRecord;
import com.qycloud.oatos.server.domain.entity.ShareFolder;
import com.qycloud.oatos.server.domain.entity.ShareFolderHistory;
import com.qycloud.oatos.server.domain.entity.ShareLink;
import com.qycloud.oatos.server.domain.entity.User;
import com.qycloud.oatos.server.domain.model.ShareFolderAndFileModel;
import com.qycloud.oatos.server.domain.model.SimpleUserModel;
import com.qycloud.oatos.server.util.CacheClient;
import com.qycloud.oatos.server.util.CacheKey;
import com.qycloud.oatos.server.util.FileLockUtil;
import com.qycloud.oatos.server.util.FilePermissionUtil;
import com.qycloud.oatos.server.util.LogicException;

/**
 * share disk logic
 * 
 * @author yang
 * 
 */
@Service("ShareDiskLogic")
public class ShareDiskLogic {

	@Autowired
	private SequenceLogic sequence;

	@Autowired
	private ShareFolderMapper shareFolderMapper;

	@Autowired
	private ShareFileMapper shareFileMapper;

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private EnterpriseMapper enterpriseMapper;

	@Autowired
	private ShareLinkMapper shareLinkMapper;

	@Autowired
	private ShareFileHistoryMapper shareFileHistoryMapper;
	@Autowired
	private ShareFolderHistoryMapper shareFolderHistoryMapper;

	@Autowired
	private ShareFileRecordMapper shareFileRecordMapper;

	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private AdminFolderMapper adminFolderMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Autowired
	private FavoriteFileMapper favoriteFileMapper;

	/**
	 * 取文件夹列表，不包括已删除和回收站中文件夹
	 * @param enterpriseId
	 * @return
	 */
	public List<ShareFolderDTO> getShareFoldersByEntId(long enterpriseId) {
		List<ShareFolder> folderModels = shareFolderMapper.getShareFoldersByEntId(enterpriseId);
		List<ShareFolderDTO> folderDTOs = new ArrayList<ShareFolderDTO>();
		if (folderModels != null) {
			for (ShareFolder f : folderModels) {
				folderDTOs.add(f.toShareFolderDTO());
			}
		}
		return folderDTOs;
	}
	
	/**
	 * 取所有文件，不包括已删除和回收站中文件
	 * @param enterpriseId
	 * @return
	 */
	public ShareFilesDTO getShareFilesByEntId(long enterpriseId) {
		List<ShareFile> fileModels = shareFileMapper.getShareFilesByEntId(enterpriseId);
		List<ShareFileDTO> fileDTOs = new ArrayList<ShareFileDTO>();
		if (fileModels != null) {
			for (ShareFile f : fileModels) {
				fileDTOs.add(f.toShareFileDTO());
			}
		}
		ShareFilesDTO filesDTO = new ShareFilesDTO();
		filesDTO.setShareFileList(fileDTOs);
		return filesDTO;
	}
	
	/**
	 * 增加文件夹
	 * @param folderDTO
	 * @return
	 */
	@Transactional
	public String newShareFolder(ShareFolderUpdateDTO folderDTO) {
		// check permission
		if (folderDTO.getUserId() != folderDTO.getEnterpriseId()) {
			checkPermissionAndThrowException(folderDTO.getUserId(), folderDTO.getParentId(), FilePermission.Write);
        }

		ShareFolder folder = new ShareFolder(folderDTO);
		// check same folder
		checkSameFolder(folderDTO);
		
		folder.setFolderId(sequence.getNextId());
		folder.setVersion(getNextVersion(folderDTO.getEnterpriseId()));
		folder.setCreateUserId(folderDTO.getUserId());
		// add folder
		shareFolderMapper.addShareFolder(folder);
		// insert history
		ShareFolderHistory historyModel = new ShareFolderHistory(folder);
		historyModel.setOperation(Operation.NewFolder.name());
		historyModel.setComment(folderDTO.getComment());
		historyModel.setUserId(folderDTO.getUserId());
		historyModel.setUserName(folderDTO.getUserName());
		historyModel.setUpdateTime(new Date());
		shareFolderHistoryMapper.addShareFolderHistory(historyModel);

        if (folder.getParentId() != null) {
    	    // update parent folder version
    	    ShareFolder parent = shareFolderMapper.getShareFolderById(folder.getParentId());
    	    if (parent.getDeleted() == FileStatus.active) {
        	    parent.setVersion(folder.getVersion());
        	    shareFolderMapper.updateShareFolder(parent);
			} else {
				// 父文件夹已经被删除
				throw new LogicException(ErrorType.errorFolderDeleted);
			}

        }
		
		// copy permissions
		if (folderDTO.getParentId() != null) {
			rolePermissionMapper.copyParentRolePermisssionToChildFolder(folderDTO.getParentId(), folder.getFolderId());
        }
		
		if (folder.getCreateUserId() != null) {
	        Admin a = adminMapper.getAdminByUserId(folder.getCreateUserId());
	        if (a != null) {
	            AdminFolder f = new AdminFolder();
	            f.setUserId(folder.getCreateUserId());
	            f.setFolderId(folder.getFolderId());
	            adminFolderMapper.addAdminFolder(f);
            }
        }
		return PojoMapper.toJson(folder.toShareFolderDTO());
	}
	
	private void checkSameFolder(ShareFolderDTO folderDTO) {
		ShareFolder folder = new ShareFolder(folderDTO);
		if (shareFolderMapper.getSameShareFolder(folder) != null) {
			throw new LogicException(ErrorType.errorSameFolder);
		}
	}
	
	/**
	 * 修改共享文件夹
	 * @param folderDTO
	 * @return
	 */
	@Transactional
	public String updateShareFolder(ShareFolderUpdateDTO folderDTO) {
		// 检查权限
		if (folderDTO.getUserId() != folderDTO.getEnterpriseId()) {
			checkPermissionAndThrowException(folderDTO.getUserId(), folderDTO.getFolderId(), FilePermission.Write);
        }
		// 检查版本
		checkFolderVersion(folderDTO.getFolderId(), folderDTO.getVersion());
		// 检查同名文件夹
		checkSameFolder(folderDTO);

		ShareFolder folder = new ShareFolder(folderDTO);
		folder.setVersion(getNextVersion(folder.getEnterpriseId()));
		// update folder
		shareFolderMapper.updateShareFolder(folder);

		// insert history
		ShareFolderHistory historyModel = new ShareFolderHistory(folderDTO);
		historyModel.setVersion(folder.getVersion());
		historyModel.setUpdateTime(new Date());
		shareFolderHistoryMapper.addShareFolderHistory(historyModel);

        if (folder.getParentId() != null) {
    	    // update parent folder version
    	    ShareFolder parent = shareFolderMapper.getShareFolderById(folder.getParentId());
    	    parent.setVersion(folder.getVersion());
    	    shareFolderMapper.updateShareFolder(parent);
        }
        return PojoMapper.toJson(folder.toShareFolderDTO());
	}
	
	/**
	 * 修改共享文件夹空间
	 * @param folderDTO
	 * @return
	 */
	@Transactional
	public String updateShareFolderSize(ShareFolderUpdateDTO folderDTO) {
		// 检查版本
		checkFolderVersion(folderDTO.getFolderId(), folderDTO.getVersion());
		// 检查同名文件夹
		checkSameFolder(folderDTO);

		ShareFolder folder = new ShareFolder(folderDTO);
		folder.setVersion(getNextVersion(folder.getEnterpriseId()));
		// update folder
		shareFolderMapper.updateShareFolder(folder);

		// insert history
		ShareFolderHistory historyModel = new ShareFolderHistory(folderDTO);
		historyModel.setVersion(folder.getVersion());
		historyModel.setUpdateTime(new Date());
		historyModel.setOperation(Operation.SetFolderSize.name());
		shareFolderHistoryMapper.addShareFolderHistory(historyModel);

        if (folder.getParentId() != null) {
    	    // update parent folder version
    	    ShareFolder parent = shareFolderMapper.getShareFolderById(folder.getParentId());
    	    parent.setVersion(folder.getVersion());
    	    shareFolderMapper.updateShareFolder(parent);
        }
        return PojoMapper.toJson(folder.toShareFolderDTO());
	}
	
	/**
	 * 修改共享文件
	 * @param fileDTO
	 * @return
	 */
	@Transactional
	public String updateShareFile(ShareFileUpdateDTO fileDTO) {
		// 检查权限
		checkPermissionAndThrowException(fileDTO.getUserId(), fileDTO.getFolderId(), FilePermission.Write);
		// 检查文件锁定
		checkFileLock(fileDTO.getFileId(), fileDTO.getUserId());
		// 检查文件版本
		checkFileVersion(fileDTO.getFileId(), fileDTO.getVersion());
		// 检查同名文件
		checkSameFile(fileDTO);
		ShareFile file = updateFile(fileDTO);
		return PojoMapper.toJson(file.toShareFileDTO());
	}
	
	private void checkSameFile(ShareFileDTO fileDTO) {
		ShareFile file = new ShareFile(fileDTO);
		if (shareFileMapper.getSameShareFile(file) != null) {
			throw new LogicException(ErrorType.errorSameFile);
		}
	}
	
	/**
	 * 取企业下所有共享文件夹和共享文件
	 * @param entId
	 * @return
	 */
	public ShareFolderAndFileDTO getShareFolderAndFileByEntId(long entId) {
		ShareFolderAndFileDTO folderAndFileDTO = new ShareFolderAndFileDTO();
		folderAndFileDTO.setForderList(getShareFoldersByEntId(entId));
		ShareFilesDTO filesDTO = getShareFilesByEntId(entId);
		folderAndFileDTO.setFileList(filesDTO.getShareFileList());
		return folderAndFileDTO;
	}
	
	/**
	 * 删除回收站中共享文件夹和共享文件
	 * @param folderAndFileDTO
	 * @return
	 */
	@Transactional
	public String deleteShareFolderAndFile(ShareFolderAndFileUpdateDTO folderAndFileDTO) {
		ShareFolderAndFileModel folderAndFileModel = new ShareFolderAndFileModel(folderAndFileDTO);
		List<ShareFolder> folderList = folderAndFileModel.getFolderList();
		List<ShareFile> fileList = folderAndFileModel.getFileList();

		long entId = -1;

        long version = getNextVersion(entId);

		if (fileList.size() > 0) {
			entId = fileList.get(0).getEnterpriseId();
			// 刪除db中文件
			for (ShareFile f : fileList) {
				// 检查文件锁定
				checkFileLock(f.getFileId(), folderAndFileDTO.getUserId());
				// 检查文件版本
				checkFileVersion(f.getFileId(), f.getVersion());
				f.setVersion(version);
			}
			shareFileMapper.deleteShareFiles(fileList);
		}

		if (folderList.size() > 0) {
			entId = folderList.get(0).getEnterpriseId();
			// 删除文件夹
			for (ShareFolder f : folderList) {
				// 检查文件夹版本
				checkFolderVersion(f.getFolderId(), f.getVersion());
				f.setVersion(version);
			}
			if (folderList.size() > 0) {
				shareFolderMapper.deleteShareFolders(folderList);
			}
		}

		if (entId > 0) {
			// insert history
	        folderAndFileDTO.setOperation(Operation.DeletePermanently);
	        insertHistory(version, folderList, fileList, folderAndFileDTO);

	        // update parent folder version
	        updateParentFolderVersion(folderAndFileModel.getParentFolderIds(), version);
        }

		return CommConstants.OK_MARK;
	}

	/**
	 * 取所有子文件夹，不包括回收站和已删除的文件夹
	 * @param folder
	 * @return
	 */
	private List<ShareFolder> getSubFolders(ShareFolder folder) {
		List<ShareFolder> allFolders = shareFolderMapper.getShareFoldersByEntId(folder.getEnterpriseId());
		return getSubFolders(allFolders, folder.getFolderId());
	}

	/**
	 * 从指定文件夹列表中递归找到文件夹的子文件夹
	 * @param folders
	 * @param folderId
	 * @return
	 */
	private List<ShareFolder> getSubFolders(List<ShareFolder> folders, long folderId) {
		List<ShareFolder> childs = new ArrayList<ShareFolder>();
		for (ShareFolder f : folders) {
			if (f.getParentId() != null && f.getParentId().longValue() == folderId) {
				childs.add(f);
				childs.addAll(getSubFolders(folders, f.getFolderId()));
			}
		}
		return childs;
	}

	/**
	 * 从指定文件列表中取子文件
	 * @param folders
	 * @param allFiles
	 * @return
	 */
	private List<ShareFile> getSubFiles(List<ShareFolder> folders, List<ShareFile> allFiles) {
		List<ShareFile> subFiles = new ArrayList<ShareFile>();
		for (ShareFolder folder : folders) {
			for (ShareFile file : allFiles) {
				if (file.getFileId() != null && file.getFolderId().equals(folder.getFolderId())) {
					subFiles.add(file);
				}
			}
		}
		return subFiles;
	}
	
	/**
	 * 修改共享文件夹和共享文件
	 * @param folderAndFileDTO
	 */
	@Transactional
	public void updateShareFolderAndFile(ShareFolderAndFileUpdateDTO folderAndFileDTO) {
		ShareFolderAndFileModel folderAndFileModel = new ShareFolderAndFileModel(folderAndFileDTO);
		List<ShareFolder> folderList = folderAndFileModel.getFolderList();
		List<ShareFile> fileList = folderAndFileModel.getFileList();

		long entId = -1;
		for (ShareFile f : fileList) {
			// 检查文件版本
			checkFileVersion(f.getFileId(), f.getVersion());

			entId = f.getEnterpriseId();
		}
		for (ShareFolder f : folderList) {
			// 检查文件夹版本
			checkFolderVersion(f.getFolderId(), f.getVersion());

			entId = f.getEnterpriseId();
		}
		if (entId > 0) {
			if (Operation.Delete.equals(folderAndFileDTO.getOperation())) {
				// 取子文件夹
				List<ShareFolder> allFolders = shareFolderMapper.getShareFoldersByEntId(entId);
				List<ShareFolder> subFolders = new ArrayList<ShareFolder>();
				for (ShareFolder f : folderList) {
					subFolders.addAll(getSubFolders(allFolders, f.getFolderId()));
				}
				// 设置子文件夹状态为删除
				for (ShareFolder f : subFolders) {
					f.setDeleted(FileStatus.recycle);
				}
				folderList.addAll(subFolders);

				// 取子文件
				List<ShareFile> allFiles = shareFileMapper.getShareFilesByEntId(entId);
				List<ShareFile> subFiles = getSubFiles(folderList, allFiles);
				// 设置文件状态为删除
				for (ShareFile f : subFiles) {
					f.setDeleted(FileStatus.recycle);
				}
				fileList.addAll(subFiles);
			}
			
			// get next version
			long version = getNextVersion(entId);
			for (ShareFile f : fileList) {
				// 检查文件锁定
				checkFileLock(f.getFileId(), folderAndFileDTO.getUserId());

				f.setVersion(version);
			}
			if (fileList.size() > 0) {
				shareFileMapper.updateShareFiles(fileList);
			}
			for (ShareFolder f : folderList) {
				f.setVersion(version);
			}
			if (folderList.size() > 0) {
				shareFolderMapper.updateShareFolders(folderList);
			}

			// insert history
			insertHistory(version, folderList, fileList, folderAndFileDTO);

	        // update parent folder version
	        updateParentFolderVersion(folderAndFileModel.getParentFolderIds(), version);
        }
	}

	/**
	 * update parent folder version
	 * @param parentFolderIds
	 * @param version
	 */
	private void updateParentFolderVersion(Set<Long> parentFolderIds, long version) {
        // update parent folder version
        if (parentFolderIds.size() > 0) {
            for (Long folderId : parentFolderIds) {
                ShareFolder f = shareFolderMapper.getShareFolderById(folderId);
                if (f != null) {
                    f.setVersion(version);
                    shareFolderMapper.updateShareFolder(f);
                }
            }
        }
	}

	private void insertHistory(long version, List<ShareFolder> folderList, List<ShareFile> fileList, ShareFolderAndFileUpdateDTO folderAndFileDTO) {
		if (fileList.size() > 0) {
			List<ShareFileHistory> hs = new ArrayList<ShareFileHistory>();
			for (ShareFile f : fileList) {
				ShareFileHistory h = new ShareFileHistory(f);
				h.setVersion(version);
				h.setOperation(folderAndFileDTO.getOperation().name());
				h.setComment(folderAndFileDTO.getComment());
				h.setUserId(folderAndFileDTO.getUserId());
				h.setUserName(folderAndFileDTO.getUserName());
				hs.add(h);
            }
			shareFileHistoryMapper.addShareFileHistoryList(hs);
		}
		if (folderList.size() > 0) {
			List<ShareFolderHistory> hs = new ArrayList<ShareFolderHistory>();
			for (ShareFolder f : folderList) {
				ShareFolderHistory h = new ShareFolderHistory(f);
				h.setVersion(version);
				h.setUpdateTime(new Date());
				h.setOperation(folderAndFileDTO.getOperation().name());
				h.setComment(folderAndFileDTO.getComment());
				h.setUserId(folderAndFileDTO.getUserId());
				h.setUserName(folderAndFileDTO.getUserName());
				hs.add(h);
            }
			shareFolderHistoryMapper.addShareFolderHistoryList(hs);
		}
	}
	
	/**
	 * 移动共享文件夹和共享文件
	 * @param folderAndFileDTO
	 */
	@Transactional
	public void moveShareFolderAndFile(ShareFolderAndFileUpdateDTO folderAndFileDTO) {
		ShareFolderAndFileModel folderAndFileModel = new ShareFolderAndFileModel(folderAndFileDTO);
		List<ShareFolder> folderList = folderAndFileModel.getFolderList();
		List<ShareFile> fileList = folderAndFileModel.getFileList();
		long entId = -1;

		long moveFileSize = 0;
		Long toFolderId = null;
		if (folderList.size() > 0) {
			List<ShareFolder> allFolders = shareFolderMapper.getShareFoldersByEntId(entId);
			List<ShareFile> allFiles = shareFileMapper.getShareFilesByEntId(entId);
			
			List<ShareFolder> moveFolders = new ArrayList<ShareFolder>();
			for (ShareFolderDTO f : folderAndFileDTO.getForderList()) {
				entId = f.getEnterpriseId();
				toFolderId = f.getParentId();

				ShareFolder folder = new ShareFolder(f);
				moveFolders.add(folder);
				moveFolders.addAll(getSubFolders(allFolders, folder.getFolderId()));
            }
			List<ShareFile> subFiles = getSubFiles(moveFolders, allFiles);
	        for (ShareFile file : subFiles) {
	        	moveFileSize = moveFileSize + file.getSize();
            }
		}
		if (fileList.size() > 0) {
			for (ShareFileDTO f : folderAndFileDTO.getFileList()) {
				entId = f.getEnterpriseId();
				toFolderId = f.getFolderId();
				moveFileSize = moveFileSize + f.getSize();
            }
		}

		// check folder space
		if (moveFileSize > 0) {
			ShareFolder folder = shareFolderMapper.getShareFolderById(toFolderId);
			if (folder.getMaxSize() != null && folder.getMaxSize().longValue() > 0) {
                long us = shareFileMapper.getFileSizeSumByFolderId(toFolderId);
                if (folder.getMaxSize().longValue() < (moveFileSize + us)) {
                	throw new LogicException(ErrorType.errorFolderSpaceOver);
                }
            }
        }

		// check same name folder
		if (folderList.size() > 0) {
			for (ShareFolderDTO f : folderAndFileDTO.getForderList()) {
	            checkSameFolder(f);
            }
		}
		// check same name file
		if (fileList.size() > 0) {
			for (ShareFileDTO f : folderAndFileDTO.getFileList()) {
				checkSameFile(f);
            }
		}

		if (entId > 0) {
			// get next version
			long version = getNextVersion(entId);
			for (ShareFile f : fileList) {
				// 检查文件锁定
				checkFileLock(f.getFileId(), folderAndFileDTO.getUserId());
				// 检查文件版本
				checkFileVersion(f.getFileId(), f.getVersion());

				f.setVersion(version);
			}
			if (fileList.size() > 0) {
				shareFileMapper.updateShareFiles(fileList);
			}
			for (ShareFolder f : folderList) {
				// 检查文件夹版本
				checkFolderVersion(f.getFolderId(), f.getVersion());

				f.setVersion(version);
			}
			if (folderList.size() > 0) {
				shareFolderMapper.updateShareFolders(folderList);
			}

			// insert history
			folderAndFileDTO.setOperation(Operation.Move);
			insertHistory(version, folderList, fileList, folderAndFileDTO);

	        // update parent folder version
	        updateParentFolderVersion(folderAndFileModel.getParentFolderIds(), version);
        }
	}
	
	/**
	 * 取企业下文件夹
	 * @param userId
	 * @return
	 */
    public List<ShareFolderDTO> getShareFoldersByUserId(long userId) {
		User user = userMapper.getUser(userId);
		List<RolePermission> permissions = null;
		if (UserType.Administrator != user.getUserType().longValue()) {
			permissions = rolePermissionMapper.getRolePermisssionsByUserId(user.getUserId());
        }
	    return getShareFoldersByUser(user, permissions);
    }
    
    /**
     * 取企业下文件
     * @param userId
     * @return
     */
    public ShareFilesDTO getShareFilesByUserId(long userId) {
		User user = userMapper.getUser(userId);
		List<RolePermission> permissions = null;
		if (UserType.Administrator != user.getUserType().longValue()) {
			permissions = rolePermissionMapper.getRolePermisssionsByUserId(user.getUserId());
        }
	    return getShareFilesByUser(user, getShareFoldersByUser(user, permissions));
    }
    
    /**
     * 取企业下共享文件夹和共享文件
     * @param userId
     * @return
     */
    public ShareFolderAndFileDTO getShareFolderAndFileByUserId(long userId) {
		User user = userMapper.getUser(userId);
		List<RolePermission> permissions = null;
		if (UserType.Administrator != user.getUserType().longValue()) {
			permissions = rolePermissionMapper.getRolePermisssionsByUserId(user.getUserId());
        }
		ShareFolderAndFileDTO folderAndFileDTO = new ShareFolderAndFileDTO();
		folderAndFileDTO.setForderList(getShareFoldersByUser(user, permissions));
		ShareFilesDTO filesDTO = getShareFilesByUser(user, folderAndFileDTO.getForderList());
		folderAndFileDTO.setFileList(filesDTO.getShareFileList());
	    return folderAndFileDTO;
    }
	
	private List<ShareFolderDTO> getShareFoldersByUser(User user, List<RolePermission> permissions) {
		List<ShareFolderDTO> allFolders = getShareFoldersByEntId(user.getEnterpriseId());
		List<ShareFolderDTO> folderDTOs = new ArrayList<ShareFolderDTO>();
		if (UserType.Administrator == user.getUserType().longValue()) {
	        folderDTOs = allFolders;
        } else {
    		for (ShareFolderDTO folder : allFolders) {
    	        long folderId = folder.getFolderId();
    	        boolean check = FilePermissionUtil.checkPermission(folderId, FilePermission.List, permissions);
    	        if (check) {
    	        	folderDTOs.add(folder);
                }
            }
		}
		return folderDTOs;
	}
    
    private ShareFilesDTO getShareFilesByUser(User user, List<ShareFolderDTO> folderDTOs) {
    	ShareFilesDTO filesDTO = getShareFilesByEntId(user.getEnterpriseId());
		List<ShareFileDTO> allFiles = filesDTO.getShareFileList();
		List<ShareFileDTO> files = new ArrayList<ShareFileDTO>();
		if (UserType.Administrator == user.getUserType().longValue()) {
			files = allFiles;
            for (ShareFileDTO file : files) {
            	SimpleUserModel lock = FileLockUtil.getFileLockStatus(file.getFileId());
    			if (lock != null) {
    	            file.setLockByUserId(lock.getUserId());
    	            file.setLockByUser(lock.getName());
                }
            }
		} else {
			for (ShareFolderDTO folder : folderDTOs) {
	            for (ShareFileDTO file : allFiles) {
	                if (file.getFolderId() == folder.getFolderId()) {
	                	SimpleUserModel lock = FileLockUtil.getFileLockStatus(file.getFileId());
	        			if (lock != null) {
	        	            file.setLockByUserId(lock.getUserId());
	        	            file.setLockByUser(lock.getName());
	                    }
	                    files.add(file);
	                }
	            }
	        }
		}
		attachFavoriteFiles(user.getUserId(), files);
		filesDTO.setShareFileList(files);
	    return filesDTO;
    }

    /**
     * 设置收藏属性
     * @param userId
     * @param files
     */
    private void attachFavoriteFiles(long userId, List<ShareFileDTO> files) {
		List<Long> favoriteList = favoriteFileMapper.getFavoriteFileIdByUserId(userId);
		if (favoriteList != null) {
            for (ShareFileDTO f : files) {
            	if (favoriteList.contains(f.getFileId())) {
					f.setFavorite(true);
				} else {
					f.setFavorite(false);
				}
            }
		}
    }
    
    /**
     * 添加共享文件
     * @param fileUpdateDTO
     * @return
     */
	@Transactional
    public long addShareFile(ShareFileUpdateDTO fileUpdateDTO) {
		ShareFileUpdateDTO f = addFile(fileUpdateDTO);
		return f.getFileId();
    }
	
	/**
	 * 移动个人网盘到企业网盘
	 * @param shareDTO
	 */
	@Transactional
    public void sharePersonalFile(SharePersonalFileDTO shareDTO) {
	    for (INetworkFileDTO f : shareDTO.getFileList()) {
	    	ShareFileUpdateDTO file = new ShareFileUpdateDTO();
	    	file.setUserId(shareDTO.getUserId());
	    	file.setEnterpriseId(shareDTO.getEnterpriseId());
	    	file.setFolderId(shareDTO.getFolderId());
	    	file.setGuid(f.getGuid());
	    	file.setName(f.getName());
	    	file.setSize(f.getSize());
	    	file.setRemark(f.getRemark());
	    	file.setThumb(f.getThumb());
	    	// add
	    	addShareFile(file);
        }
    }
	
	/**
	 * 把企业网盘文件删掉到回收站
	 * @param fileDTO
	 */
	@Transactional
    public void deleteShareFile(ShareFileUpdateDTO fileDTO) {
		// 检查权限
		checkPermissionAndThrowException(fileDTO.getUserId(), fileDTO.getFolderId(), FilePermission.Delete);
		// 检查文件锁定
		checkFileLock(fileDTO.getFileId(), fileDTO.getUserId());
		// 检查文件版本
		checkFileVersion(fileDTO.getFileId(), fileDTO.getVersion());

		ShareFile f = shareFileMapper.getShareFileById(fileDTO.getFileId());
		fileDTO.setFolderId(f.getFolderId());
		fileDTO.setEnterpriseId(f.getEnterpriseId());
		fileDTO.setName(f.getName());
		fileDTO.setSize(f.getSize());
		fileDTO.setType(f.getType());
		fileDTO.setGuid(f.getGuid());
		fileDTO.setCreateTime(f.getCreateTime());
		fileDTO.setShareLinkId(f.getShareLinkId());
		fileDTO.setVersion(f.getVersion());
		fileDTO.setThumb(f.getThumb());
		fileDTO.setRemark(f.getRemark());
		
		fileDTO.setDeleted(FileStatus.recycle);
		fileDTO.setOperation(Operation.Delete);
		updateFile(fileDTO);
    }
	
	/**
	 * 共享文件是存在同名的
	 * @param file
	 * @return
	 */
    public String checkShareFileUpload(ShareFileUpdateDTO fileDTO) {
		String result = CommConstants.OK_MARK;
		ShareFile file = new ShareFile(fileDTO);
		ShareFile temp = shareFileMapper.getSameShareFile(file);
		if (temp == null) {
			long usedSize = shareFileMapper.getFileSizeSumByEntId(file.getEnterpriseId());
			Enterprise ent = enterpriseMapper.getEnterpriseById(file.getEnterpriseId());
			// check enterprise disk space
			if (ent.getDiskSize() >= (file.getSize() + usedSize)) {
				// check folder space
				ShareFolder folder = shareFolderMapper.getShareFolderById(file.getFolderId());
				if (folder.getDeleted() != FileStatus.active) {
					// 文件夹已被删除
					result = ErrorType.errorFolderDeleted.name();
				} else if (folder.getMaxSize() != null && folder.getMaxSize().longValue() > 0) {
	                long us = shareFileMapper.getFileSizeSumByFolderId(file.getFolderId());
	                if (folder.getMaxSize().longValue() < (file.getSize() + us)) {
	                	result = ErrorType.errorFolderSpaceOver.name();
                    }
                }
			}
			else
			{
				result = ErrorType.errorNoSpace.name();
			}
		}
		else
		{
			// 在用户同一文件夹下存在同名文件
			result = ErrorType.errorSameFile.name();
		}
		return result;
    }
    
    /**
     * 删掉共享文件夹到回收站
     */
    @Transactional
    public String deleteShareFolder(ShareFolderUpdateDTO folderDTO) {
		// check permission
		if (folderDTO.getUserId() != folderDTO.getEnterpriseId()) {
			checkPermissionAndThrowException(folderDTO.getUserId(), folderDTO.getFolderId(), FilePermission.Delete);
        }
		// 检查文件夹版本
		checkFolderVersion(folderDTO.getFolderId(), folderDTO.getVersion());

		// 获取下一个版本号
        long version = getNextVersion(folderDTO.getEnterpriseId());

		List<ShareFolder> deleteFolders = new ArrayList<ShareFolder>();

		// 取所有要删除的子文件夹
		ShareFolder folder = new ShareFolder(folderDTO);
		deleteFolders.add(folder);

		// 此文件夹下的子文件夹
		deleteFolders.addAll(getSubFolders(folder));

		// 删除文件夹的权限设置
		for (ShareFolder f : deleteFolders) {
			f.setVersion(version);
			f.setDeleted(FileStatus.recycle);
		}
		if (deleteFolders.size() > 0) {
			// 修改文件夹
			shareFolderMapper.updateShareFolders(deleteFolders);
		}
		
		List<ShareFile> allFiles = shareFileMapper.getShareFilesByEntId(folderDTO.getEnterpriseId());
		List<ShareFile> deleteFiles = getSubFiles(deleteFolders, allFiles);

		for (ShareFile f : deleteFiles) {
			// 检查文件锁定
			checkFileLock(f.getFileId(), folderDTO.getUserId());
			// 将文件删除到回收站
			f.setDeleted(FileStatus.recycle);
			f.setVersion(version);
		}
		if (deleteFiles.size() > 0) {
			// 修改文件
			shareFileMapper.updateShareFiles(deleteFiles);
		}

		// insert history
		ShareFolderAndFileUpdateDTO updateDTO = new ShareFolderAndFileUpdateDTO();
		updateDTO.setOperation(Operation.Delete);
		updateDTO.setUserId(folderDTO.getUserId());
		updateDTO.setUserName(folderDTO.getUserName());

        insertHistory(version, deleteFolders, deleteFiles, updateDTO);

        if (folder.getParentId() != null) {
    	    // update parent folder version
    	    ShareFolder parent = shareFolderMapper.getShareFolderById(folder.getParentId());
    	    parent.setVersion(version);
    	    shareFolderMapper.updateShareFolder(parent);
        }
		return CommConstants.OK_MARK;
    }
    
    
    /**
     * 得到单个共享文件
     * @param fileId
     * @return
     */
    public ShareFileDTO getShareFileById(long fileId) {
	    ShareFile fileModel = shareFileMapper.getShareFileById(fileId);
	    return fileModel.toShareFileDTO();
    }
    
    /**
     * 还原回收站共享文件夹和共享文件
     * @param folderAndFileDTO
     */
	@Transactional
    public void restoreShareFolderAndFile(ShareFolderAndFileUpdateDTO folderAndFileDTO) {
		ShareFolderAndFileModel folderAndFileModel = new ShareFolderAndFileModel(folderAndFileDTO);
		List<ShareFolder> folderList = folderAndFileModel.getFolderList();
		List<ShareFile> fileList = folderAndFileModel.getFileList();

		long entId = -1;
		for (ShareFolder f : folderList) {
	        f.setDeleted(FileStatus.active);
        }
		for (ShareFile f : fileList) {
	        f.setDeleted(FileStatus.active);
        }
		if (fileList.size() > 0) {
			entId = fileList.get(0).getEnterpriseId();
			long size = 0;
			Map<Long, Long> sizeMap = new HashMap<Long, Long>();
			for (ShareFileDTO f : folderAndFileDTO.getFileList()) {
				size = size + f.getSize();
				Long s = sizeMap.get(f.getFolderId());
				if (s == null) {
	                s = f.getSize();
                } else {
					s = s + f.getSize();
				}
				sizeMap.put(f.getFolderId(), s);
            }
			if (size > 0) {
				// check total disk space
				long usedSize = shareFileMapper.getFileSizeSumByEntId(entId);
				Enterprise ent = enterpriseMapper.getEnterpriseById(entId);
				if (ent.getDiskSize() < (size + usedSize)) {
					throw new LogicException(ErrorType.errorNoSpace);
				}
            }
			// check folder space
			for (Long folderId : sizeMap.keySet()) {
				ShareFolder folder = shareFolderMapper.getShareFolderById(folderId);
				if (folder.getMaxSize() != null && folder.getMaxSize().longValue() > 0) {
	                long us = shareFileMapper.getFileSizeSumByFolderId(folderId);
	                long s = sizeMap.get(folderId);
	                if (folder.getMaxSize().longValue() < (s + us)) {
	                	throw new LogicException(ErrorType.errorFolderSpaceOver);
                    }
                }
            }
			// check same name file
			for (ShareFileDTO f : folderAndFileDTO.getFileList()) {
				checkSameFile(f);
            }
		}
		if (folderList.size() > 0) {
			entId = folderList.get(0).getEnterpriseId();
			for (ShareFolderDTO f : folderAndFileDTO.getForderList()) {
	            checkSameFolder(f);
            }
		}
		if (fileList.size() > 0) {
			for (ShareFileDTO f : folderAndFileDTO.getFileList()) {
				checkSameFile(f);
            }
		}


		if (entId > 0) {
			// get next version
			long version = getNextVersion(entId);
			for (ShareFile f : fileList) {
				// 检查文件锁定
				checkFileLock(f.getFileId(), folderAndFileDTO.getUserId());
				// 检查文件版本
				checkFileVersion(f.getFileId(), f.getVersion());
				f.setVersion(version);
			}
			if (fileList.size() > 0) {
				shareFileMapper.updateShareFiles(fileList);
			}
			for (ShareFolder f : folderList) {
				// 检查文件夹版本
				checkFolderVersion(f.getFolderId(), f.getVersion());
				f.setVersion(version);
			}
			if (folderList.size() > 0) {
				shareFolderMapper.updateShareFolders(folderList);
			}

			// insert history
			folderAndFileDTO.setOperation(Operation.RestoreFromRecycle);
			insertHistory(version, folderList, fileList, folderAndFileDTO);

	        // update parent folder version
	        updateParentFolderVersion(folderAndFileModel.getParentFolderIds(), version);
        }
    }
	
	/**
	 * 取企业下所有文件夹
	 * @param enterpriseId
	 * @return
	 */
    public List<ShareFolderDTO> getAllShareFoldersByEntId(long enterpriseId) {
		List<ShareFolder> folderModels = shareFolderMapper.getAllShareFoldersByEntId(enterpriseId);
		List<ShareFolderDTO> folderDTOs = new ArrayList<ShareFolderDTO>();
		if (folderModels != null) {
			for (ShareFolder f : folderModels) {
				folderDTOs.add(f.toShareFolderDTO());
			}
		}
		return folderDTOs;
    }
    
    /**
     * 取企业下所有文件
     * @param enterpriseId
     * @return
     */
    public ShareFilesDTO getAllShareFilesByEntId(long enterpriseId) {
		List<ShareFile> fileModels = shareFileMapper.getAllShareFilesByEntId(enterpriseId);
		List<ShareFileDTO> fileDTOs = new ArrayList<ShareFileDTO>();
		if (fileModels != null) {
			for (ShareFile f : fileModels) {
				fileDTOs.add(f.toShareFileDTO());
			}
		}
		ShareFilesDTO filesDTO = new ShareFilesDTO();
		filesDTO.setShareFileList(fileDTOs);
		return filesDTO;
    }
    
    /**
     * 取企业下所有共享文件夹和共享文件
     * @param entId
     * @return
     */
    public ShareFolderAndFileDTO getAllShareFolderAndFileByEntId(long entId) {
		ShareFolderAndFileDTO folderAndFileDTO = new ShareFolderAndFileDTO();
		folderAndFileDTO.setForderList(getAllShareFoldersByEntId(entId));
		ShareFilesDTO filesDTO = getAllShareFilesByEntId(entId);
		folderAndFileDTO.setFileList(filesDTO.getShareFileList());
		return folderAndFileDTO;
    }
    
    /**
     * 取企业下文件夹
     * @param userId
     * @return
     */
    public List<ShareFolderDTO> getAllShareFoldersByUserId(long userId) {
		User user = userMapper.getUser(userId);
		List<RolePermission> permissions = null;
		if (UserType.Administrator != user.getUserType().longValue()) {
			permissions = rolePermissionMapper.getRolePermisssionsByUserId(user.getUserId());
        }
	    return getAllShareFoldersByUser(user, permissions);
    }
    
    /**
     * 取企业下文件
     * @param userId
     * @return
     */
    public ShareFilesDTO getAllShareFilesByUserId(long userId) {
		User user = userMapper.getUser(userId);
		List<RolePermission> permissions = null;
		if (UserType.Administrator != user.getUserType().longValue()) {
			permissions = rolePermissionMapper.getRolePermisssionsByUserId(user.getUserId());
        }
	    return getAllShareFilesByUser(user, getAllShareFoldersByUser(user, permissions));
    }
    
    /**
     * 取企业下共享文件夹和共享文件
     * @param userId
     * @return
     */
    public ShareFolderAndFileDTO getAllShareFolderAndFileByUserId(long userId) {
		User user = userMapper.getUser(userId);
		List<RolePermission> permissions = null;
		if (UserType.Administrator != user.getUserType().longValue()) {
			permissions = rolePermissionMapper.getRolePermisssionsByUserId(user.getUserId());
        }
		ShareFolderAndFileDTO folderAndFileDTO = new ShareFolderAndFileDTO();
		folderAndFileDTO.setForderList(getAllShareFoldersByUser(user, permissions));
		ShareFilesDTO filesDTO = getAllShareFilesByUser(user, folderAndFileDTO.getForderList());
		folderAndFileDTO.setFileList(filesDTO.getShareFileList());
	    return folderAndFileDTO;
    }

	private List<ShareFolderDTO> getAllShareFoldersByUser(User user, List<RolePermission> permissions) {
		List<ShareFolderDTO> allFolders = getAllShareFoldersByEntId(user.getEnterpriseId());
		List<ShareFolderDTO> folderDTOs = new ArrayList<ShareFolderDTO>();
		if (UserType.Administrator == user.getUserType().longValue()) {
	        folderDTOs = allFolders;
        } else {
    		for (ShareFolderDTO folder : allFolders) {
    	        long folderId = folder.getFolderId();
    	        boolean check = FilePermissionUtil.checkPermission(folderId, FilePermission.List, permissions);
    	        if (check) {
    	        	folderDTOs.add(folder);
                }
            }
		}
		return folderDTOs;
	}

    private ShareFilesDTO getAllShareFilesByUser(User user, List<ShareFolderDTO> folderDTOs) {
    	ShareFilesDTO filesDTO = getAllShareFilesByEntId(user.getEnterpriseId());
		List<ShareFileDTO> allFiles = filesDTO.getShareFileList();
		List<ShareFileDTO> files = new ArrayList<ShareFileDTO>();
		if (UserType.Administrator == user.getUserType().longValue()) {
			files = allFiles;
            for (ShareFileDTO file : files) {
            	SimpleUserModel lock = FileLockUtil.getFileLockStatus(file.getFileId());
    			if (lock != null) {
    	            file.setLockByUserId(lock.getUserId());
    	            file.setLockByUser(lock.getName());
                }
            }
		} else {
			for (ShareFolderDTO folder : folderDTOs) {
	            for (ShareFileDTO file : allFiles) {
	                if (file.getFolderId() == folder.getFolderId()) {
	                	SimpleUserModel lock = FileLockUtil.getFileLockStatus(file.getFileId());
	        			if (lock != null) {
	        	            file.setLockByUserId(lock.getUserId());
	        	            file.setLockByUser(lock.getName());
	                    }
	                    files.add(file);
	                }
	            }
	        }
		}
		attachFavoriteFiles(user.getUserId(), files);
		filesDTO.setShareFileList(files);
	    return filesDTO;
    }
    
    /**
     * 获取共享的文件信息
     * @param linkId
     * @return
     */
    public ShareLinkFilesDTO getShareLinkFiles(long linkId) {
		ShareLink linkModel = shareLinkMapper.getShareLinkByLinkId(linkId);
		ShareLinkFilesDTO filesDTO = new ShareLinkFilesDTO();
		if (linkModel.getFolderId() != null) {
			// get top folder
	        ShareFolder folder = shareFolderMapper.getShareFolderById(linkModel.getFolderId());
	        if (FileStatus.active == folder.getDeleted()) {
		        filesDTO.setFolderDTO(folder.toShareFolderDTO());
		        // get child folders
		        List<ShareFolder> sharedFolders = getSubFolders(folder);
		        List<ShareFolderDTO> folderDTOs = new ArrayList<ShareFolderDTO>();
		        for (ShareFolder f : sharedFolders) {
		        	folderDTOs.add(f.toShareFolderDTO());
	            }
		        filesDTO.setForderList(folderDTOs);
		        
		        // get shared files
		        List<ShareFile> allFiles = shareFileMapper.getShareFilesByEntId(folder.getEnterpriseId());
		        List<ShareFileDTO> sharedFiles = new ArrayList<ShareFileDTO>();
		        for (ShareFile f : allFiles) {
		            boolean shared = false;
		            if (f.getFolderId().equals(linkModel.getFolderId())) {
		            	shared = true;
	                } else {
	        	        for (ShareFolder fol : sharedFolders) {
	        	        	if (f.getFolderId().equals(fol.getFolderId())) {
	        	        		shared = true;
	        	        		break;
	                        }
	                    }
					}
		            if (shared) {
		            	sharedFiles.add(f.toShareFileDTO());
	                }
	            }
		        filesDTO.setFileList(sharedFiles);
            }
        } else if (linkModel.getFileId() != null) {
			ShareFile fileModel = shareFileMapper.getShareFileById(linkModel.getFileId());
			if (FileStatus.active == fileModel.getDeleted()) {
				filesDTO.setFileDTO(fileModel.toShareFileDTO());
            }
		}
	    return filesDTO;
    }
    
    /**
     * 同步企业网盘文件
     * @param file
     * @return
     */
	@Transactional
    public String syncEnterpriseFile(ShareFileUpdateDTO file) {
		ShareFileUpdateDTO resultDto = null;

		// check permission
		checkPermissionAndThrowException(file.getUserId(), file.getFolderId(), FilePermission.Local);
		if (file.getUserName() == null) {
			User u = userMapper.getUser(file.getUserId());
			String str = u.getUserName();
			if (u.getRealName() != null && !"".equals(u.getRealName().trim())) {
	            str = u.getRealName();
            }
			file.setUserName(str);
        }
		if (file.getFileId() != null) {
			// 检查文件锁定状态
			String lock = FileLockUtil.checkLockFile(file.getUserId(), file.getFileId());
			if (CommConstants.OK_MARK.equals(lock)) {
				ShareFile f = shareFileMapper.getShareFileById(file.getFileId());
				f.setName(file.getName());
				f.setGuid(file.getGuid());
				f.setUpdateTime(new Date());
				f.setSize(file.getSize());
				f.setVersion(getNextVersion(f.getEnterpriseId()));
				shareFileMapper.updateShareFile(f);

			    // update parent folder version
			    ShareFolder folder = shareFolderMapper.getShareFolderById(file.getFolderId());
			    folder.setVersion(f.getVersion());
			    shareFolderMapper.updateShareFolder(folder);
				
				// insert history
				ShareFileHistory historyModel = new ShareFileHistory(f);
				historyModel.setOperation(Operation.EditFile.name());
				historyModel.setUserId(file.getUserId());
				historyModel.setUserName(file.getUserName());
				shareFileHistoryMapper.addShareFileHistory(historyModel);

				file.setVersion(f.getVersion());
				file.setUpdateTime(f.getUpdateTime());
			    resultDto = file;
			}
        } else {
        	resultDto = addFile(file);
		}
		return PojoMapper.toJson(resultDto);
    }
	
	/**
	 * 取共享文件历史记录
	 * @param fileDTO
	 * @return
	 */
    public ShareHistoryDTO getShareFileHistory(ShareFileUpdateDTO fileDTO) {
		List<ShareFileHistory> historyModels = shareFileHistoryMapper.getShareFileHistoryByFileId(fileDTO.getFileId());
		List<ShareFileUpdateDTO> hs = new ArrayList<ShareFileUpdateDTO>();
		if (historyModels != null) {
			for (ShareFileHistory h : historyModels) {
		        hs.add(h.toHistoryDTO());
	        } 
        }
		ShareHistoryDTO historyDTO = new ShareHistoryDTO();
		historyDTO.setFileUpdateList(hs);
	    return historyDTO;
    }
    
    /**
     * 取共享文件夹历史记录
     * @param folderDTO
     * @return
     */
    public ShareHistoryDTO getShareFolderHistory(ShareFolderUpdateDTO folderDTO) {
		List<ShareFileHistory> fileHistoryModels = shareFileHistoryMapper.getShareFileHistoryByFolderId(folderDTO.getFolderId());
		List<ShareFileUpdateDTO> fileUpdateList = new ArrayList<ShareFileUpdateDTO>();
		Map<Long, Long> versions = new HashMap<Long, Long>();
		if (fileHistoryModels != null) {
			for (ShareFileHistory h : fileHistoryModels) {
				if (versions.get(h.getVersion()) == null) {
					fileUpdateList.add(h.toHistoryDTO());
					versions.put(h.getVersion(), h.getVersion());
                }
	        }
        }

		List<ShareFolderHistory> folderHistoryModels = shareFolderHistoryMapper.getShareFolderHistoryByFolderId(folderDTO.getFolderId());
		List<ShareFolderUpdateDTO> folderUpdateList = new ArrayList<ShareFolderUpdateDTO>();
		if (folderHistoryModels != null) {
			for (ShareFolderHistory h : folderHistoryModels) {
				if (versions.get(h.getVersion()) == null) {
					folderUpdateList.add(h.toHistoryDTO());
					versions.put(h.getVersion(), h.getVersion());
                }
	        }
        }

		ShareHistoryDTO historyDTO = new ShareHistoryDTO();
		historyDTO.setFolderUpdateList(folderUpdateList);
		historyDTO.setFileUpdateList(fileUpdateList);
	    return historyDTO;
    }
    
    /**
     * 取共享文件夹历史记录详细
     * @param folderDTO
     * @return
     */
    public ShareHistoryDTO getShareFolderVersionDetail(ShareFolderUpdateDTO folderDTO) {
		List<ShareFileHistory> fileHistoryModels = shareFileHistoryMapper.getShareFileVersionDetailByFolderId(folderDTO.getFolderId(), folderDTO.getVersion());
		List<ShareFileUpdateDTO> fileUpdateList = new ArrayList<ShareFileUpdateDTO>();
		if (fileHistoryModels != null) {
			for (ShareFileHistory h : fileHistoryModels) {
				fileUpdateList.add(h.toHistoryDTO());
	        }
        }

		List<ShareFolderHistory> folderHistoryModels = shareFolderHistoryMapper.getShareFolderVersionDetailByFolderId(folderDTO.getFolderId(), folderDTO.getVersion());
		List<ShareFolderUpdateDTO> folderUpdateList = new ArrayList<ShareFolderUpdateDTO>();
		if (folderHistoryModels != null) {
			for (ShareFolderHistory h : folderHistoryModels) {
				folderUpdateList.add(h.toHistoryDTO());
	        }
        }

		ShareHistoryDTO historyDTO = new ShareHistoryDTO();
		historyDTO.setFolderUpdateList(folderUpdateList);
		historyDTO.setFileUpdateList(fileUpdateList);
	    return historyDTO;
    }
    
    
    /**
     * 取单个共享文件夹信息
     * @param folderId
     * @return
     */
    public ShareFolderDTO getShareFolderById(long folderId) {
	    return shareFolderMapper.getShareFolderById(folderId).toShareFolderDTO();
    }
    
    /**
     * 恢复共享文件版本
     * @param file
     */
	@Transactional
    public void restoreShareFileVersion(ShareFileUpdateDTO file) {
	    shareFileMapper.updateShareFile(new ShareFile(file));
    }
	
	/**
	 * 恢复共享文件夹和文件版本
	 * @param folderDTO
	 */
	@Transactional
    public void restoreShareFolderVersion(ShareFolderUpdateDTO folderDTO) {
		//restore files
		List<ShareFileHistory> fileHistoryModels = shareFileHistoryMapper.getShareFileStatusByFolderIdAndVersion(folderDTO.getFolderId(), folderDTO.getVersion());
		if (fileHistoryModels != null) {
			List<ShareFile> files = new ArrayList<ShareFile>();
			for (ShareFileHistory f : fileHistoryModels) {
				files.add(new ShareFile(f));
	        }
			if (files.size() > 0) {
				shareFileMapper.updateShareFiles(files);
			}
        }
		// delete new files
		List<ShareFileHistory> fileNewModels = shareFileHistoryMapper.getShareFileNewByFolderIdAndVersion(folderDTO.getFolderId(), folderDTO.getVersion());
		if (fileNewModels != null) {
			List<ShareFile> files = new ArrayList<ShareFile>();
			for (ShareFileHistory f : fileNewModels) {
				files.add(new ShareFile(f));
	        }
			if (files.size() > 0) {
				shareFileMapper.deleteShareFiles(files);
			}
        }

		// restore folders
		List<ShareFolderHistory> folderHistoryModels = shareFolderHistoryMapper.getShareFolderStatusByFolderIdAndVersion(folderDTO.getFolderId(), folderDTO.getVersion());
		if (folderHistoryModels != null) {
			List<ShareFolder> folders = new ArrayList<ShareFolder>();
			for (ShareFolderHistory f : folderHistoryModels) {
				folders.add(new ShareFolder(f));
	        }
			if (folders.size() > 0) {
				shareFolderMapper.updateShareFolders(folders);
			}
        }
		// delete new folders
		List<ShareFolderHistory> folderNewModels = shareFolderHistoryMapper.getShareFolderNewByFolderIdAndVersion(folderDTO.getFolderId(), folderDTO.getVersion());
		if (folderNewModels != null) {
			List<ShareFolder> folders = new ArrayList<ShareFolder>();
			for (ShareFolderHistory f : folderNewModels) {
				folders.add(new ShareFolder(f));
	        }
			if (folders.size() > 0) {
				shareFolderMapper.deleteShareFolders(folders);
			}
        }
		// set folder version
		ShareFolder folderModel = shareFolderMapper.getShareFolderById(folderDTO.getFolderId());
		folderModel.setVersion(folderDTO.getVersion());
		shareFolderMapper.updateShareFolder(folderModel);
    }
	
	/**
	 * 取企业下用户本地同步的共享文件夹,不包括回收站
	 * @param userId
	 * @return
	 */
    public List<ShareFolderDTO> getSyncShareFoldersByUserId(long userId) {
		User user = userMapper.getUser(userId);
		List<RolePermission> permissions = null;
		if (UserType.Administrator != user.getUserType().longValue()) {
			permissions = rolePermissionMapper.getRolePermisssionsByUserId(user.getUserId());
        }
	    return getSyncShareFoldersByUser(user, permissions);
    }
    
    /**
     * 取企业下用户本地同步的共享文件,不包括回收站
     * @param userId
     * @return
     */
    public ShareFilesDTO getSyncShareFilesByUserId(long userId) {
		User user = userMapper.getUser(userId);
		List<RolePermission> permissions = null;
		if (UserType.Administrator != user.getUserType().longValue()) {
			permissions = rolePermissionMapper.getRolePermisssionsByUserId(user.getUserId());
        }
	    return getSyncShareFilesByUser(user, getSyncShareFoldersByUser(user, permissions));
    }
    
    /**
     * 取企业下用户本地同步可读的共享文件夹和共享文件,不包括回收站
     * @param userId
     * @return
     */
    public ShareFolderAndFileDTO getSyncShareFolderAndFileByUserId(long userId) {
		User user = userMapper.getUser(userId);
		List<RolePermission> permissions = null;
		if (UserType.Administrator != user.getUserType().longValue()) {
			permissions = rolePermissionMapper.getRolePermisssionsByUserId(user.getUserId());
        }
		ShareFolderAndFileDTO folderAndFileDTO = new ShareFolderAndFileDTO();
		folderAndFileDTO.setForderList(getSyncShareFoldersByUser(user, permissions));
		ShareFilesDTO filesDTO = getSyncShareFilesByUser(user, folderAndFileDTO.getForderList());
		folderAndFileDTO.setFileList(filesDTO.getShareFileList());
	    return folderAndFileDTO;
    }
	
	
	private List<ShareFolderDTO> getSyncShareFoldersByUser(User user, List<RolePermission> permissions) {
		List<ShareFolderDTO> allFolders = getShareFoldersByEntId(user.getEnterpriseId());
		List<ShareFolderDTO> folderDTOs = new ArrayList<ShareFolderDTO>();
		if (UserType.Administrator == user.getUserType().longValue()) {
	        folderDTOs = allFolders;
        } else {
    		for (ShareFolderDTO folder : allFolders) {
    	        long folderId = folder.getFolderId();
    	        boolean check = FilePermissionUtil.checkPermission(folderId, FilePermission.Local, permissions);
    	        if (check) {
    	        	folderDTOs.add(folder); 
                }
            }
		}
		return folderDTOs;
	}

    private ShareFilesDTO getSyncShareFilesByUser(User user, List<ShareFolderDTO> folderDTOs) {
    	ShareFilesDTO filesDTO = getShareFilesByEntId(user.getEnterpriseId());
		List<ShareFileDTO> allFiles = filesDTO.getShareFileList();
		List<ShareFileDTO> files = new ArrayList<ShareFileDTO>();
		if (UserType.Administrator == user.getUserType().longValue()) {
			files = allFiles;
		} else {
			for (ShareFolderDTO folder : folderDTOs) {
	            for (ShareFileDTO file : allFiles) {
	                if (file.getFolderId() == folder.getFolderId()) {
	                    files.add(file);
	                }
	            }
	        }
		}
		filesDTO.setShareFileList(files);
	    return filesDTO;
    }
    
    /**
     * check permission
     * @param user
     * @param folderId
     * @param operation
     * @return
     */
	private boolean checkPermission(User user, Long folderId, FilePermission operation) {
		boolean check = false;
		if (UserType.Administrator == user.getUserType().longValue()) {
			check = true;
		} else if (folderId == null) {
	       check = false; 
        } else {
			List<RolePermission> permissions = rolePermissionMapper.getRolePermisssionsByUserIdAndFolderId(user.getUserId(),
			        folderId);
			check = FilePermissionUtil.checkPermission(folderId, operation, permissions);
			
			
		}
		return check;
	}

	/**
	 * check permission
	 * 
	 * @param userId
	 * @param folderId
	 * @param operation
	 * @return
	 */
	private boolean checkPermissionAndThrowException(long userId, Long folderId, FilePermission operation) {
		User user = userMapper.getUser(userId);
		boolean check = checkPermission(user, folderId, operation);
		if (!check) {
			throw new LogicException(ErrorType.errorNoPermission);
		}
		return check;
	}
	
	/**
	 * 取同名文件
	 * @param fileDTO
	 * @return
	 */
    public String checkShareFileSyncUpload(ShareFileUpdateDTO fileDTO) {
    	String result = CommConstants.OK_MARK;
    	if (fileDTO.getFileId() != null) {
    		// 检查文件锁定状态
    		result = FileLockUtil.checkLockFile(fileDTO.getUserId(), fileDTO.getFileId());
    		if (CommConstants.OK_MARK.equals(result)) {
    			// 检查文件版本
    			ShareFile f = shareFileMapper.getShareFileById(fileDTO.getFileId());
    			if (!fileDTO.getGuid().equals(f.getGuid())) {
    				result = ErrorType.errorVersionConflict.name();
    			}
			}
		} else {
			result = checkShareFileUpload(fileDTO);
		}
	    return result;
    }
    
    /**
     * 获取企业网盘版本号
     * @param entId
     * @return
     */
    public String getShareDiskMaxVersion(long entId) {
	    return String.valueOf(shareFileHistoryMapper.getShareMaxVersion(entId));
    }
    
    /**
     * 插入文件访问记录
     * @param recordDTO
     */
    public void insertRecord(IShareFileRecordDTO recordDTO) {
		recordDTO.setRecordId(sequence.getNextId());
	    shareFileRecordMapper.insertRecord(new ShareFileRecord(recordDTO));
    }
    
    /**
     * 获取文件访问记录
     * @param userId
     * @param type
     * @return
     */
    public String getRecordByUserIdAndType(long userId, String type) {
		List<ShareFileRecord> records = shareFileRecordMapper.getRecordByUserIdAndType(userId, type);
		List<ShareFileRecordDTO> recordList = new ArrayList<ShareFileRecordDTO>();
		for (ShareFileRecord r : records) {
	        recordList.add(r.toRecordDTO());
        }
		ShareFileRecordsDTO recordsDTO = new ShareFileRecordsDTO();
		recordsDTO.setRecordList(recordList);
	    return PojoMapper.toJson(recordsDTO);
    }
    
    /**
     * 获取子文件夹和文件，当folderId为空时，获取顶级文件夹
     * @param param
     * @return
     */
    public ShareFolderAndFileDTO getEntFolderAndFileByFolderId(FolderAndFileParamDTO param) {
		ShareFolderAndFileDTO folderAndFileDTO = new ShareFolderAndFileDTO();
		User user = userMapper.getUser(param.getUserId());

		List<ShareFolder> folders = new ArrayList<ShareFolder>();
		List<ShareFile> files = new ArrayList<ShareFile>();
		if (param.getFolderIds() != null && param.getFolderIds().size() > 0) {
			for (Long folderId : param.getFolderIds()) {
				if (folderId != null) {
					// 当前文件夹浏览权限判断
					boolean check = checkPermission(user, folderId, FilePermission.List);
					if (check) {
						List<ShareFolder> subFolders = shareFolderMapper.getFoldersByEntIdAndFolderId(user.getEnterpriseId(), folderId);
						List<ShareFile> subFiles = shareFileMapper.getShareFilesByFolderId(folderId);
						folders.addAll(subFolders);
						files.addAll(subFiles);
					}
				} else {
					List<ShareFolder> topFolders = shareFolderMapper.getFoldersByEntIdAndFolderId(user.getEnterpriseId(), null);
					folders.addAll(topFolders);
				}
			}
		} else {
			// 获取顶层文件夹
			folders = shareFolderMapper.getFoldersByEntIdAndFolderId(user.getEnterpriseId(), null);
		}

		List<ShareFolderDTO> folderDTOs = new ArrayList<ShareFolderDTO>();
		for (ShareFolder f : folders) {
			// 判断文件夹浏览权限
	        boolean c = checkPermission(user, f.getFolderId(), FilePermission.List);
	        if (c) {
	            folderDTOs.add(f.toShareFolderDTO());
            }
        }
		folderAndFileDTO.setForderList(folderDTOs);

    	List<ShareFileDTO> fileDTOs = new ArrayList<ShareFileDTO>();
        for (ShareFile f : files) {
            ShareFileDTO file = f.toShareFileDTO();
            // 获取文件锁定状态
        	SimpleUserModel lock = FileLockUtil.getFileLockStatus(file.getFileId());
			if (lock != null) {
	            file.setLockByUserId(lock.getUserId());
	            file.setLockByUser(lock.getName());
            }
			fileDTOs.add(file);
        }
        // 设置收藏属性
        attachFavoriteFiles(user.getUserId(), fileDTOs);

        folderAndFileDTO.setFileList(fileDTOs);
		
	    return folderAndFileDTO;
    }
    
    /**
     * 添加共享文件
     * @param fileUpdateDTO
     * @return
     */
    public ShareFileUpdateDTO addFile(ShareFileUpdateDTO fileUpdateDTO) {
		if (fileUpdateDTO.getUserName() == null) {
			User u = userMapper.getUser(fileUpdateDTO.getUserId());
			String str = u.getUserName();
			if (u.getRealName() != null && !"".equals(u.getRealName().trim())) {
	            str = u.getRealName();
            }
			fileUpdateDTO.setUserName(str);
        }
    	fileUpdateDTO.setOperation(Operation.UploadFile);
    	ShareFile file = new ShareFile(fileUpdateDTO);
		ShareFile temp = shareFileMapper.getSameShareFile(file);
		if (temp == null) {
			long usedSize = shareFileMapper.getFileSizeSumByEntId(file.getEnterpriseId());
			Enterprise ent = enterpriseMapper.getEnterpriseById(file.getEnterpriseId());
			// check enterprise disk space
			if (ent.getDiskSize() >= (file.getSize() + usedSize)) {
				// check folder space
				ShareFolder folder = shareFolderMapper.getShareFolderById(file.getFolderId());
				if (folder.getDeleted() != FileStatus.active) {
					// 文件夹已被删除
					throw new LogicException(ErrorType.errorFolderDeleted);
				}
				if (folder.getMaxSize() != null && folder.getMaxSize().longValue() > 0) {
	                long us = shareFileMapper.getFileSizeSumByFolderId(file.getFolderId());
	                if (folder.getMaxSize().longValue() < (file.getSize() + us)) {
	                	throw new LogicException(ErrorType.errorFolderSpaceOver);
                    }
                }
				// add file
				file.setFileId(sequence.getNextId());
				fileUpdateDTO.setFileId(file.getFileId());
				file.setVersion(getNextVersion(file.getEnterpriseId()));
				file.setType("");
				file.setUpdateTime(new Date());
				file.setCreateTime(new Date());
				file.setCreateUserId(fileUpdateDTO.getUserId());
				shareFileMapper.addShareFile(file);

				// update parent folder version
				folder.setVersion(file.getVersion());
				shareFolderMapper.updateShareFolder(folder);
				
				// insert history
				ShareFileHistory historyModel = new ShareFileHistory(file);
				historyModel.setOperation(fileUpdateDTO.getOperation().name());
				historyModel.setComment(fileUpdateDTO.getComment());
				historyModel.setUserId(fileUpdateDTO.getUserId());
				historyModel.setUserName(fileUpdateDTO.getUserName());
				shareFileHistoryMapper.addShareFileHistory(historyModel);
				
				return historyModel.toHistoryDTO();
			}
			else
			{
				throw new LogicException(ErrorType.errorNoSpace);
			}
		}
		else
		// 在用户同一文件夹下存在同名文件，则抛出异常
		{
			throw new LogicException(ErrorType.errorSameFile);
		}
    }
    
    /**
     * 更新文件
     * @param fileDTO
     * @return
     */
	public ShareFile updateFile(ShareFileUpdateDTO fileDTO) {
    	ShareFile file = new ShareFile(fileDTO);
    	file.setVersion(getNextVersion(file.getEnterpriseId()));
    	fileDTO.setVersion(file.getVersion());
		shareFileMapper.updateShareFile(file);

	    // update parent folder version
	    ShareFolder folder = shareFolderMapper.getShareFolderById(file.getFolderId());
	    folder.setVersion(file.getVersion());
	    shareFolderMapper.updateShareFolder(folder);
	    
		// insert history
		ShareFileHistory historyModel = new ShareFileHistory(fileDTO);
		historyModel.setVersion(file.getVersion());
		historyModel.setUpdateTime(file.getUpdateTime());
		shareFileHistoryMapper.addShareFileHistory(historyModel);
		return file;
	}

	/**
	 * 取企业网盘下一个版本号
	 * @param entId
	 * @return
	 */
	public long getNextVersion(long entId) {
		return shareFileHistoryMapper.getShareMaxVersion(entId) + 1;
	}
	
	/**
	 * 通过企业ID获得企业网盘文件夹
	 * @param entId
	 * @return
	 */
	public ShareFolderAndFileDTO getFolderAndFileInRecycle(long entId) {
		// 取回收站中文件夹
		List<ShareFolder> folders = shareFolderMapper.getShareFolderListInRecycleByEnterpriseId(entId);
		List<ShareFolderDTO> folderDTOs = new ArrayList<ShareFolderDTO>();
		if(folders != null){
			for (ShareFolder f : folders) {
				folderDTOs.add(f.toShareFolderDTO());
			}
		}
		// 取回收站中文件
		List<ShareFile> files = shareFileMapper.getShareFilesInRecycleByEnterpriseId(entId);
		List<ShareFileDTO> fileDTOs = new ArrayList<ShareFileDTO>();
		if (files != null) {
			for (ShareFile f : files) {
				fileDTOs.add(f.toShareFileDTO());
			}
		}

		ShareFolderAndFileDTO folderAndFileDTO = new ShareFolderAndFileDTO();
		folderAndFileDTO.setFileList(fileDTOs);
		folderAndFileDTO.setForderList(folderDTOs);
		return folderAndFileDTO;
		
	}

	/**
	 * 锁定企业网盘文件
	 * @param fileDTO
	 * @return
	 */
	public String lockShareFile(ShareFileDTO fileDTO) {
		return FileLockUtil.lockFile(fileDTO.getLockByUserId(), fileDTO.getFileId(), fileDTO.getLockByUser());
	}

	/**
	 * 解锁企业网盘文件
	 * @param fileDTOs
	 * @param userId
	 */
	public void unlockShareFiles(List<ShareFileDTO> fileDTOs, long userId) {
		for (ShareFileDTO fileDTO : fileDTOs) {
			// set file unlocked
			String key = CacheKey.FileLock + fileDTO.getFileId();
			Object object = CacheClient.getInstance().get(key);
			if (object != null) {
				SimpleUserModel lock = (SimpleUserModel) object;
				if (lock.getUserId() == userId) {
					CacheClient.getInstance().delete(key);
				}
			}
		}
	}

	/**
	 * 判断企业网盘已使用空间是否超过免费空间
	 * @param entId
	 * @return
	 */
	public String checkEntDiskSizeExceed(long entId) {
		long usedSize = shareFileMapper.getFileSizeSumByEntId(entId);
		return usedSize >= CommConstants.ENTERPRISE_DISK_SIZE ? CommConstants.TRUE_MARK : CommConstants.FALSE_MARK;
	}

	/**
	 * 编辑企业网盘文件前，检查权限，是否被他人锁定，并锁定文件
	 * @param viewFileDTO
	 * @return
	 */
	public String checkEditShareFile(ViewFileDTO viewFileDTO) {
		String result = CommConstants.OK_MARK;
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(viewFileDTO.getType())) {
			// 检查权限
			checkPermissionAndThrowException(viewFileDTO.getUserId(), viewFileDTO.getFolderId(), FilePermission.Write);
			// 锁定文家
			result = FileLockUtil.lockFile(viewFileDTO.getUserId(), viewFileDTO.getFileId(), viewFileDTO.getUserName());
		}
		return result;
	}

	/**
	 * 取企业网盘已使用空间
	 * @param entId
	 * @return
	 */
	public long getShareDiskUsedSizeByEntId(long entId) {
		return shareFileMapper.getFileSizeSumByEntId(entId);
	}

	/**
	 * 检查保存编辑的企业网盘文件
	 * @param fileDTO
	 * @return
	 */
	public String checkSaveShareFile(SaveFileDTO saveFileDTO) {
		String result = CommConstants.OK_MARK;
		FileDTO fileDTO = new FileDTO();
		fileDTO.setName(saveFileDTO.getName());
		fileDTO.setFolderId(saveFileDTO.getFolderId());
		fileDTO.setGuid(saveFileDTO.getNewGuid());
		fileDTO.setType(saveFileDTO.getToType());
		fileDTO.setSize(saveFileDTO.getSize());

		// 检查权限
		checkPermissionAndThrowException(saveFileDTO.getUserId(), saveFileDTO.getFolderId(), FilePermission.Write);

		// 取同名文件
		ShareFile file = new ShareFile();
		file.setEnterpriseId(saveFileDTO.getToId());
		file.setName(saveFileDTO.getName());
		file.setFolderId(saveFileDTO.getFolderId());
		ShareFile temp = shareFileMapper.getSameShareFile(file);
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
				// 检查文件锁定状态
				result = FileLockUtil.checkLockFile(saveFileDTO.getUserId(), temp.getFileId());
				if (CommConstants.OK_MARK.equals(result)) {
					// 覆盖现有文件
					fileDTO.setType(CommConstants.FILE_TYPE_SHAREDISK);
					fileDTO.setGuid(temp.getGuid());
					fileDTO.setFolderId(temp.getFolderId());
					fileDTO.setFileId(temp.getFileId());
					fileDTO.setSize(temp.getSize());
				}

			}
		}
		if (CommConstants.OK_MARK.equals(result)) {
			result = PojoMapper.toJson(fileDTO);
		}
		return result;
	}

	/**
	 * 保存编辑的企业网盘文件
	 * @param fileDTO
	 * @return
	 */
	@Transactional
	public String saveShareFile(SaveFileDTO saveFileDTO) {
		FileDTO fileDTO = new FileDTO();
		fileDTO.setFileId(saveFileDTO.getFileId());
		fileDTO.setName(saveFileDTO.getName());
		fileDTO.setFolderId(saveFileDTO.getFolderId());
		fileDTO.setGuid(saveFileDTO.getNewGuid());
		fileDTO.setType(saveFileDTO.getToType());
		fileDTO.setSize(saveFileDTO.getSize());
		
		if (CommConstants.FILE_NEW.equals(saveFileDTO.getType())) {
			ShareFileUpdateDTO file = new ShareFileUpdateDTO();
			file.setFileId(saveFileDTO.getFileId());
			file.setEnterpriseId(saveFileDTO.getToId());
			file.setGuid(saveFileDTO.getNewGuid());
			file.setName(saveFileDTO.getName());
			file.setFolderId(saveFileDTO.getFolderId());
			file.setSize(fileDTO.getSize());
			file.setUserId(saveFileDTO.getUserId());
			file.setUserName(saveFileDTO.getUserName());
			file.setThumb(saveFileDTO.getThumb());
			file.setRemark(saveFileDTO.getRemark());
			file.setCreateTime(new Date());
			long fileId = addFile(file).getFileId();
			fileDTO.setFileId(fileId);
		}
		else {
			ShareFile oldFile = shareFileMapper.getShareFileById(saveFileDTO.getFileId());
			ShareFileHistory file = new ShareFileHistory(oldFile);
			file.setGuid(saveFileDTO.getNewGuid());
			file.setName(saveFileDTO.getName());
			file.setFolderId(saveFileDTO.getFolderId());
			file.setSize(fileDTO.getSize());
			file.setOperation(Operation.EditFile.name());
			file.setUserId(saveFileDTO.getUserId());
			file.setUserName(saveFileDTO.getUserName());
			file.setUpdateTime(new Date());
			updateFile(file.toHistoryDTO());
		}
		return PojoMapper.toJson(fileDTO);
	}

	/**
	 * 检查文件夹版本<br>
	 * 版本冲突，抛出LogicException(ErrorType.errorVersionConflict)异常
	 * @param folderId
	 * @param version
	 */
	private void checkFolderVersion(long folderId, long version) {
		ShareFolder folder = shareFolderMapper.getShareFolderById(folderId);
		if (folder.getVersion() != version) {
			throw new LogicException(ErrorType.errorVersionConflict);
		}
	}

	/**
	 * 检查文件版本<br>
	 * 版本冲突，抛出LogicException(ErrorType.errorVersionConflict)异常
	 * @param fileId
	 * @param version
	 */
	private void checkFileVersion(long fileId, long version) {
		ShareFile file = shareFileMapper.getShareFileById(fileId);
		if (file.getVersion() != version) {
			throw new LogicException(ErrorType.errorVersionConflict);
		}
	}
	
	/**
	 * 检查文件锁定状态<br>
	 * 文件被他人锁定，抛出LogicException(ErrorType.errorFileLocked)异常
	 * @param fileId
	 * @param userId
	 */
	private void checkFileLock(long fileId, long userId) {
		SimpleUserModel lock = FileLockUtil.getFileLockStatus(fileId);
		if (lock != null && lock.getUserId() != userId) {
			throw new LogicException(ErrorType.errorFileLocked);
		}
	}

}
