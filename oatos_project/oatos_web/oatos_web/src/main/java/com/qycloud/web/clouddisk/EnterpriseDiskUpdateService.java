package com.qycloud.web.clouddisk;

import java.util.Date;

import org.apache.log4j.Logger;

import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.client.ShareFileDTO;
import com.conlect.oatos.dto.client.ShareFileNewDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;
import com.conlect.oatos.dto.client.ShareFileUpdateDTO;
import com.conlect.oatos.dto.client.ShareFolderAndFileUpdateDTO;
import com.conlect.oatos.dto.client.ShareFolderDTO;
import com.conlect.oatos.dto.client.ShareFolderNewDTO;
import com.conlect.oatos.dto.client.ShareFolderUpdateDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.FileStatus;
import com.conlect.oatos.dto.status.MessageType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.MQMessageService;
import com.qycloud.web.utils.ConfigUtil;
import com.qycloud.web.utils.Logs;

/**
 * 企业网盘更新服务，发送更新消息
 * @author yang
 *
 */
public class EnterpriseDiskUpdateService {

	private final static Logger logger = Logs.getLogger();

	private static XhrProxy proxy = XhrProxy.getInstance();

	public static String service(String method, String postData, String token) {
		String result = "";
		if (RESTurl.newShareFolder.equals(method)) {
			result = instance.newShareFolder(postData, token);
		} else if (RESTurl.updateShareFolder.equals(method)) {
			result = instance.updateShareFolder(postData, token);
		} else if (RESTurl.deleteShareFolder.equals(method)) {
			result = instance.deleteShareFolder(postData, token);
		} else if (RESTurl.updateShareFile.equals(method)) {
			result = instance.updateShareFile(postData, token);
		} else if (RESTurl.updateShareFolderAndFiles.equals(method)) {
			result = instance.updateShareFolderAndFiles(postData, token);
		} else if (RESTurl.moveShareFolderAndFiles.equals(method)) {
			result = instance.moveShareFolderAndFiles(postData, token);
		} else if (RESTurl.deleteShareFolderAndFiles.equals(method)) {
			result = instance.deleteShareFolderAndFiles(postData, token);
		} else if (RESTurl.restoreShareFolderAndFiles.equals(method)) {
			result = instance.restoreShareFolderAndFiles(postData, token);
		} else if (RESTurl.restoreShareFolderVersion.equals(method)) {
			result = instance.restoreShareFolderVersion(postData, token);
		} else if (RESTurl.restoreShareFileVersion.equals(method)) {
			result = instance.restoreShareFileVersion(postData, token);
		} else {
			result = proxy.post(ConfigUtil.getServiceUrl(method), token, postData);
		}
		return result;
	}

	private static EnterpriseDiskUpdateService instance = new EnterpriseDiskUpdateService();

	private EnterpriseDiskUpdateService() {
	}

	/**
	 * 新建文件夹
	 * @param postData
	 * @param token
	 * @return
	 */
	public String newShareFolder(String postData, String token) {
		// 新建文件夹
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.newShareFolder), token, postData);
		if (result != null && !result.startsWith(CommConstants.ERROR_MARK)) {
			try {
				// 发送更新消息
				ShareFolderUpdateDTO folderDTO = PojoMapper.fromJsonAsObject(postData, ShareFolderUpdateDTO.class);
				ShareFileNewMessageDTO fileNewDTO = new ShareFileNewMessageDTO(folderDTO.getUserId(),
						folderDTO.getEnterpriseId());
				ShareFolderNewDTO folderNew = new ShareFolderNewDTO(folderDTO.getParentId(), folderDTO.getName(),
						Operation.NewFolder);
				fileNewDTO.getFolders().add(folderNew);
				
				sendShareFileNewMessage(token, fileNewDTO);
			} catch (Exception ex) {
				logger.error(postData, ex);
			}
		}
		return result;
	}

	/**
	 * 修改文件夹信息
	 * @param postData
	 * @param token
	 * @return
	 */
	public String updateShareFolder(String postData, String token) {
		// 修改文件夹信息
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.updateShareFolder), token, postData);
		if (result != null && !result.startsWith(CommConstants.ERROR_MARK)) {
			try {
				// 发送更新消息
				ShareFolderUpdateDTO folderDTO = PojoMapper.fromJsonAsObject(postData, ShareFolderUpdateDTO.class);
				ShareFileNewMessageDTO fileNewDTO = new ShareFileNewMessageDTO(folderDTO.getUserId(),
						folderDTO.getEnterpriseId());
				ShareFolderNewDTO folderNew = new ShareFolderNewDTO(folderDTO.getFolderId(), folderDTO.getParentId(),
						folderDTO.getName(), Operation.RenameFolder);
				fileNewDTO.getFolders().add(folderNew);
				sendShareFileNewMessage(token, fileNewDTO);
			} catch (Exception ex) {
				logger.error(postData, ex);
			}
		}
		return result;
	}

	/**
	 * 删除文件夹
	 * @param postData
	 * @param token
	 * @return
	 */
	public String deleteShareFolder(String postData, String token) {
		// 删除文件夹
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.deleteShareFolder), token, postData);
		try {
			if (CommConstants.OK_MARK.equals(result)) {
				try {
					// 发送更新消息
					ShareFolderUpdateDTO folderDTO = PojoMapper.fromJsonAsObject(postData, ShareFolderUpdateDTO.class);
					ShareFileNewMessageDTO fileNewDTO = new ShareFileNewMessageDTO();
					fileNewDTO.setUserId(folderDTO.getUserId());
					fileNewDTO.setEnterpriseId(folderDTO.getEnterpriseId());
					ShareFolderNewDTO folderNew = new ShareFolderNewDTO(folderDTO.getFolderId(),
							folderDTO.getParentId(), folderDTO.getName(), Operation.Delete);
					fileNewDTO.getFolders().add(folderNew);
					sendShareFileNewMessage(token, fileNewDTO);
				}
				catch (Exception ex) {
					logger.error(postData, ex);
				}
			}
		}
		catch (Exception ex) {
			logger.error("", ex);
			result = ErrorType.error500.name();
		}
		return result;
	}

	/**
	 * 修改文件信息
	 * @param postData
	 * @param token
	 * @return
	 */
	public String updateShareFile(String postData, String token) {
		// 修改文件信息
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.updateShareFile), token, postData);
		if (result != null && !result.startsWith(CommConstants.ERROR_MARK)) {
			try {
				// 发送更新消息
				ShareFileUpdateDTO fileDTO = PojoMapper.fromJsonAsObject(postData, ShareFileUpdateDTO.class);
				ShareFileNewMessageDTO fileNewDTO = new ShareFileNewMessageDTO(fileDTO.getUserId(),
						fileDTO.getEnterpriseId());
				ShareFileNewDTO fileNew = new ShareFileNewDTO(fileDTO.getFileId(), fileDTO.getFolderId(),
						fileDTO.getName(), fileDTO.getGuid(), Operation.RenameFile);
				fileNewDTO.getFiles().add(fileNew);
				sendShareFileNewMessage(token, fileNewDTO);
			} catch (Exception ex) {
				logger.error(postData, ex);
			}
		}
		return result;
	}

	/**
	 * 修改文件夹和文件
	 * @param postData
	 * @param token
	 * @return
	 */
	public String updateShareFolderAndFiles(String postData, String token) {
		// 修改文件夹和文件
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.updateShareFolderAndFiles), token, postData);
		if (CommConstants.OK_MARK.equals(result)) {
			try {
				// 发送更新消息
				ShareFolderAndFileUpdateDTO folderAndFileDTO = PojoMapper.fromJsonAsObject(postData,
						ShareFolderAndFileUpdateDTO.class);
				ShareFileNewMessageDTO fileNewDTO = new ShareFileNewMessageDTO();
				fileNewDTO.setUserId(folderAndFileDTO.getUserId());
				if (folderAndFileDTO.getForderList() != null) {
					for (ShareFolderDTO folderDTO : folderAndFileDTO.getForderList()) {
						fileNewDTO.setEnterpriseId(folderDTO.getEnterpriseId());
						Operation operation = Operation.Update;
						if (folderDTO.getDeleted() == FileStatus.recycle) {
							operation = Operation.Delete;
						}
						ShareFolderNewDTO folderNew = new ShareFolderNewDTO(folderDTO.getFolderId(),
								folderDTO.getParentId(), folderDTO.getName(), operation);
						fileNewDTO.getFolders().add(folderNew);
					}
				}
				if (folderAndFileDTO.getFileList() != null) {
					for (ShareFileDTO fileDTO : folderAndFileDTO.getFileList()) {
						fileNewDTO.setEnterpriseId(fileDTO.getEnterpriseId());
						Operation operation = Operation.Update;
						if (fileDTO.getDeleted() == FileStatus.recycle) {
							operation = Operation.Delete;
						}
						ShareFileNewDTO fileNew = new ShareFileNewDTO(fileDTO.getFileId(), fileDTO.getFolderId(),
								fileDTO.getName(), fileDTO.getGuid(), operation);
						fileNewDTO.getFiles().add(fileNew);
					}
				}
				if (fileNewDTO.getFolders().size() > 0 || fileNewDTO.getFiles().size() > 0) {
					sendShareFileNewMessage(token, fileNewDTO);
				}
			} catch (Exception ex) {
				logger.error(postData, ex);
			}
		}
		return result;
	}

	/**
	 * 移动文件夹和文件
	 * @param postData
	 * @param token
	 * @return
	 */
	public String moveShareFolderAndFiles(String postData, String token) {
		// 移动文件夹和文件
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.moveShareFolderAndFiles), token, postData);
		if (CommConstants.OK_MARK.equals(result)) {
			try {
				// 发送更新消息
				ShareFolderAndFileUpdateDTO folderAndFileDTO = PojoMapper.fromJsonAsObject(postData,
						ShareFolderAndFileUpdateDTO.class);
				ShareFileNewMessageDTO fileNewDTO = new ShareFileNewMessageDTO();
				fileNewDTO.setUserId(folderAndFileDTO.getUserId());
				if (folderAndFileDTO.getForderList() != null) {
					for (ShareFolderDTO folderDTO : folderAndFileDTO.getForderList()) {
						fileNewDTO.setEnterpriseId(folderDTO.getEnterpriseId());
						ShareFolderNewDTO folderNew = new ShareFolderNewDTO(folderDTO.getFolderId(),
								folderDTO.getParentId(), folderDTO.getName(), Operation.Move);
						fileNewDTO.getFolders().add(folderNew);
					}
				}
				if (folderAndFileDTO.getFileList() != null) {
					for (ShareFileDTO fileDTO : folderAndFileDTO.getFileList()) {
						fileNewDTO.setEnterpriseId(fileDTO.getEnterpriseId());
						ShareFileNewDTO fileNew = new ShareFileNewDTO(fileDTO.getFileId(), fileDTO.getFolderId(),
								fileDTO.getName(), fileDTO.getGuid(), Operation.Move);
						fileNewDTO.getFiles().add(fileNew);
					}
				}
				if (fileNewDTO.getFolders().size() > 0 || fileNewDTO.getFiles().size() > 0) {
					sendShareFileNewMessage(token, fileNewDTO);
				}
			} catch (Exception ex) {
				logger.error(postData, ex);
			}
		}
		return result;
	}

	/**
	 * 删除文件夹和文件
	 * @param postData
	 * @param token
	 * @return
	 */
	public String deleteShareFolderAndFiles(String postData, String token) {
		// 删除文件夹和文件
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.deleteShareFolderAndFiles), token, postData);
		try {
			if (CommConstants.OK_MARK.equals(result)) {
				try {
					// 发送更新消息
					ShareFolderAndFileUpdateDTO folderAndFileDTO = PojoMapper.fromJsonAsObject(postData,
							ShareFolderAndFileUpdateDTO.class);
					ShareFileNewMessageDTO fileNewDTO = new ShareFileNewMessageDTO();
					fileNewDTO.setUserId(folderAndFileDTO.getUserId());
					if (folderAndFileDTO.getForderList() != null) {
						for (ShareFolderDTO folderDTO : folderAndFileDTO.getForderList()) {
							fileNewDTO.setEnterpriseId(folderDTO.getEnterpriseId());
							ShareFolderNewDTO folderNew = new ShareFolderNewDTO(folderDTO.getFolderId(),
									folderDTO.getParentId(), folderDTO.getName(), Operation.Delete);
							fileNewDTO.getFolders().add(folderNew);
						}
					}
					if (folderAndFileDTO.getFileList() != null) {
						for (ShareFileDTO fileDTO : folderAndFileDTO.getFileList()) {
							fileNewDTO.setEnterpriseId(fileDTO.getEnterpriseId());
							ShareFileNewDTO fileNew = new ShareFileNewDTO(fileDTO.getFileId(), fileDTO.getFolderId(),
									fileDTO.getName(), fileDTO.getGuid(), Operation.Delete);
							fileNewDTO.getFiles().add(fileNew);
						}
					}
					if (fileNewDTO.getFolders().size() > 0 || fileNewDTO.getFiles().size() > 0) {
						sendShareFileNewMessage(token, fileNewDTO);
					}
				}
				catch (Exception ex) {
					logger.error(postData, ex);
				}
			}
		}
		catch (Exception ex) {
			logger.error("", ex);
			result = ErrorType.error500.name();
		}
		return result;
	}

	/**
	 * 从回收站恢复文件夹和文件
	 * @param postData
	 * @param token
	 * @return
	 */
	public String restoreShareFolderAndFiles(String postData, String token) {
		// 从回收站恢复文件夹和文件
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.restoreShareFolderAndFiles), token, postData);
		if (CommConstants.OK_MARK.equals(result)) {
			try {
				// 发送更新消息
				ShareFolderAndFileUpdateDTO folderAndFileDTO = PojoMapper.fromJsonAsObject(postData,
						ShareFolderAndFileUpdateDTO.class);
				ShareFileNewMessageDTO fileNewDTO = new ShareFileNewMessageDTO();
				fileNewDTO.setUserId(folderAndFileDTO.getUserId());
				if (folderAndFileDTO.getForderList() != null) {
					for (ShareFolderDTO folderDTO : folderAndFileDTO.getForderList()) {
						fileNewDTO.setEnterpriseId(folderDTO.getEnterpriseId());
						ShareFolderNewDTO folderNew = new ShareFolderNewDTO(folderDTO.getFolderId(),
								folderDTO.getParentId(), folderDTO.getName(), Operation.RestoreFromRecycle);
						fileNewDTO.getFolders().add(folderNew);
					}
				}
				if (folderAndFileDTO.getFileList() != null) {
					for (ShareFileDTO fileDTO : folderAndFileDTO.getFileList()) {
						fileNewDTO.setEnterpriseId(fileDTO.getEnterpriseId());
						ShareFileNewDTO fileNew = new ShareFileNewDTO(fileDTO.getFileId(), fileDTO.getFolderId(),
								fileDTO.getName(), fileDTO.getGuid(), Operation.RestoreFromRecycle);
						fileNewDTO.getFiles().add(fileNew);
					}
				}
				if (fileNewDTO.getFolders().size() > 0 || fileNewDTO.getFiles().size() > 0) {
					sendShareFileNewMessage(token, fileNewDTO);
				}
			} catch (Exception ex) {
				logger.error(postData, ex);
			}
		}
		return result;
	}

	/**
	 * 恢复文件夹版本
	 * @param postData
	 * @param token
	 * @return
	 */
	public String restoreShareFolderVersion(String postData, String token) {
		// 恢复文件夹版本
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.restoreShareFolderVersion), token, postData);
		if (CommConstants.OK_MARK.equals(result)) {
			try {
				// 发送更新消息
				ShareFolderUpdateDTO folderUpdateDTO = PojoMapper
						.fromJsonAsObject(postData, ShareFolderUpdateDTO.class);
				ShareFileNewMessageDTO fileNewDTO = new ShareFileNewMessageDTO();
				fileNewDTO.setUserId(folderUpdateDTO.getUserId());
				fileNewDTO.setEnterpriseId(folderUpdateDTO.getEnterpriseId());
				ShareFolderNewDTO folderNew = new ShareFolderNewDTO(folderUpdateDTO.getFolderId(),
						folderUpdateDTO.getParentId(), folderUpdateDTO.getName(), Operation.RestoreVersion);
				fileNewDTO.getFolders().add(folderNew);
				sendShareFileNewMessage(token, fileNewDTO);
			} catch (Exception ex) {
				logger.error(postData, ex);
			}
		}
		return result;
	}

	/**
	 * 恢复文件版本
	 * @param postData
	 * @param token
	 * @return
	 */
	public String restoreShareFileVersion(String postData, String token) {
		// 恢复文件版本
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.restoreShareFileVersion), token, postData);
		if (CommConstants.OK_MARK.equals(result)) {
			try {
				// 发送更新消息
				ShareFileUpdateDTO fileDTO = PojoMapper.fromJsonAsObject(postData, ShareFileUpdateDTO.class);
				ShareFileNewMessageDTO fileNewDTO = new ShareFileNewMessageDTO(fileDTO.getRestoreUserId(),
						fileDTO.getEnterpriseId());
				ShareFileNewDTO fileNew = new ShareFileNewDTO(fileDTO.getFileId(), fileDTO.getFolderId(),
						fileDTO.getName(), fileDTO.getGuid(), Operation.RestoreVersion);
				fileNewDTO.getFiles().add(fileNew);
				sendShareFileNewMessage(token, fileNewDTO);
			} catch (Exception ex) {
				logger.error(postData, ex);
			}
		}
		return result;
	}
	
	private void sendShareFileNewMessage(String token, ShareFileNewMessageDTO fileNewDTO) {
		try {
			// send message
			MessageDTO message = new MessageDTO();
			message.setMessageType(MessageType.ShareFileNew);
			message.setMessageBody(PojoMapper.toJson(fileNewDTO));
			message.setSender(fileNewDTO.getUserId());
			message.setReceiver(fileNewDTO.getEnterpriseId());
			message.setSendDate(new Date());
			
			MQMessageService.getInstance().sendMessage(message);
		}
		catch (Exception ex) {
			Logs.getLogger().error(token, ex);
		}
	}

}
