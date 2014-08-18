package com.qycloud.oatos.server.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.FolderAndFileParamDTO;
import com.conlect.oatos.dto.client.SaveFileDTO;
import com.conlect.oatos.dto.client.ShareFileDTO;
import com.conlect.oatos.dto.client.ShareFileRecordDTO;
import com.conlect.oatos.dto.client.ShareFileUpdateDTO;
import com.conlect.oatos.dto.client.ShareFilesDTO;
import com.conlect.oatos.dto.client.ShareFolderAndFileDTO;
import com.conlect.oatos.dto.client.ShareFolderAndFileUpdateDTO;
import com.conlect.oatos.dto.client.ShareFolderDTO;
import com.conlect.oatos.dto.client.ShareFolderUpdateDTO;
import com.conlect.oatos.dto.client.ShareFoldersDTO;
import com.conlect.oatos.dto.client.ShareHistoryDTO;
import com.conlect.oatos.dto.client.ShareLinkFilesDTO;
import com.conlect.oatos.dto.client.SharePersonalFileDTO;
import com.conlect.oatos.dto.client.ViewFileDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.dto.status.url.FileUrl;
import com.conlect.oatos.dto.status.url.ShareDiskUrl;
import com.conlect.oatos.file.ServerInnerUrl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.logic.ShareDiskLogic;
import com.qycloud.oatos.server.util.BllLogger;
import com.qycloud.oatos.server.util.LogicException;

/**
 * 企业网盘服务
 * @author yang
 * 
 */
@Controller("ShareDiskService")
public class ShareDiskService {

	private final static Logger logger = BllLogger.getLogger();

	@Autowired
	private ShareDiskLogic shareDiskLogic;

	/**
	 * @see ShareDiskUrl#getShareFoldersByEntId
	 */
	@RequestMapping(value = RESTurl.getShareFoldersByEntId, method = RequestMethod.POST)
	@ResponseBody
	public String getShareFoldersByEntId(@RequestBody String entId) {
		String reBody = "";
		try {
			List<ShareFolderDTO> folderDTOs = shareDiskLogic.getShareFoldersByEntId(Long.parseLong(entId));
			ShareFoldersDTO foldersDTO = new ShareFoldersDTO();
			foldersDTO.setShareFolderList(folderDTOs);
			reBody = PojoMapper.toJson(foldersDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(entId, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getShareFilesByEntId
	 */
	@RequestMapping(value = RESTurl.getShareFilesByEntId, method = RequestMethod.POST)
	@ResponseBody
	public String getShareFilesByEntId(@RequestBody String entId) {
		String reBody = "";
		try {
			ShareFilesDTO filesDTO = shareDiskLogic.getShareFilesByEntId(Long.parseLong(entId));
			reBody = PojoMapper.toJson(filesDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(entId, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#newShareFolder
	 */
	@RequestMapping(value = RESTurl.newShareFolder, method = RequestMethod.POST)
	@ResponseBody
	public String newShareFolder(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFolderUpdateDTO folderDto = PojoMapper.fromJsonAsObject(requestBody, ShareFolderUpdateDTO.class);
			reBody = shareDiskLogic.newShareFolder(folderDto);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#updateShareFolder
	 */
	@RequestMapping(value = RESTurl.updateShareFolder, method = RequestMethod.POST)
	@ResponseBody
	public String updateShareFolder(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFolderUpdateDTO folderDto = PojoMapper.fromJsonAsObject(requestBody, ShareFolderUpdateDTO.class);
			reBody = shareDiskLogic.updateShareFolder(folderDto);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#updateShareFolderSize
	 */
	@RequestMapping(value = RESTurl.updateShareFolderSize, method = RequestMethod.POST)
	@ResponseBody
	public String updateShareFolderSize(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFolderUpdateDTO folderDto = PojoMapper.fromJsonAsObject(requestBody, ShareFolderUpdateDTO.class);
			reBody = shareDiskLogic.updateShareFolderSize(folderDto);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#updateShareFile
	 */
	@RequestMapping(value = RESTurl.updateShareFile, method = RequestMethod.POST)
	@ResponseBody
	public String updateShareFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFileUpdateDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, ShareFileUpdateDTO.class);
			reBody = shareDiskLogic.updateShareFile(fileDTO);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#addShareFile
	 */
	@RequestMapping(value = RESTurl.addShareFile, method = RequestMethod.POST)
	@ResponseBody
	public String addShareFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFileUpdateDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, ShareFileUpdateDTO.class);
			long fileId = shareDiskLogic.addShareFile(fileDTO);
			reBody = String.valueOf(fileId);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getShareFolderAndFileByEntId
	 */
	@RequestMapping(value = RESTurl.getShareFolderAndFileByEntId, method = RequestMethod.POST)
	@ResponseBody
	public String getShareFolderAndFileByEntId(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFolderAndFileDTO folderAndFileDTO = shareDiskLogic.getShareFolderAndFileByEntId(Long
			        .parseLong(requestBody));
			reBody = PojoMapper.toJson(folderAndFileDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#updateShareFolderAndFiles
	 */
	@RequestMapping(value = RESTurl.updateShareFolderAndFiles, method = RequestMethod.POST)
	@ResponseBody
	public String updateShareFolderAndFiles(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFolderAndFileUpdateDTO folderAndFileDTO = PojoMapper.fromJsonAsObject(requestBody,
			        ShareFolderAndFileUpdateDTO.class);
			shareDiskLogic.updateShareFolderAndFile(folderAndFileDTO);

			reBody = CommConstants.OK_MARK;
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#moveShareFolderAndFiles
	 */
	@RequestMapping(value = RESTurl.moveShareFolderAndFiles, method = RequestMethod.POST)
	@ResponseBody
	public String moveShareFolderAndFiles(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFolderAndFileUpdateDTO folderAndFileDTO = PojoMapper.fromJsonAsObject(requestBody,
			        ShareFolderAndFileUpdateDTO.class);
			shareDiskLogic.moveShareFolderAndFile(folderAndFileDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#deleteShareFolderAndFiles
	 */
	@RequestMapping(value = RESTurl.deleteShareFolderAndFiles, method = RequestMethod.POST)
	@ResponseBody
	public String deleteShareFolderAndFiles(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFolderAndFileUpdateDTO folderAndFileDTO = PojoMapper.fromJsonAsObject(requestBody,
			        ShareFolderAndFileUpdateDTO.class);
			reBody = shareDiskLogic.deleteShareFolderAndFile(folderAndFileDTO);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}
	
	/**
	 * @see ShareDiskUrl#getShareFoldersByUserId
	 */
	@RequestMapping(value = RESTurl.getShareFoldersByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getShareFoldersByUserId(@RequestBody String requestBody) {
		String reBody = "";
		try {
			List<ShareFolderDTO> folderDTOs = shareDiskLogic.getShareFoldersByUserId(Long.parseLong(requestBody));
			ShareFoldersDTO foldersDTO = new ShareFoldersDTO();
			foldersDTO.setShareFolderList(folderDTOs);
			reBody = PojoMapper.toJson(foldersDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getShareFilesByUserId
	 */
	@RequestMapping(value = RESTurl.getShareFilesByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getShareFilesByUserId(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFilesDTO filesDTO = shareDiskLogic.getShareFilesByUserId(Long.parseLong(requestBody));
			reBody = PojoMapper.toJson(filesDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getShareFolderAndFileByUserId
	 */
	@RequestMapping(value = RESTurl.getShareFolderAndFileByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getShareFolderAndFileByUserId(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFolderAndFileDTO folderAndFileDTO = shareDiskLogic.getShareFolderAndFileByUserId(Long
			        .parseLong(requestBody));
			reBody = PojoMapper.toJson(folderAndFileDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see FileUrl#sharePersonalFile
	 */
	@RequestMapping(value = RESTurl.sharePersonalFile, method = RequestMethod.POST)
	@ResponseBody
	public String sharePersonalFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			SharePersonalFileDTO shareDTO = PojoMapper.fromJsonAsObject(requestBody, SharePersonalFileDTO.class);
			shareDiskLogic.sharePersonalFile(shareDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#deleteShareFile
	 */
	@RequestMapping(value = RESTurl.deleteShareFile, method = RequestMethod.POST)
	@ResponseBody
	public String deleteShareFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFileUpdateDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, ShareFileUpdateDTO.class);
			shareDiskLogic.deleteShareFile(fileDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#checkShareFileUpload
	 */
	@RequestMapping(value = RESTurl.checkShareFileUpload, method = RequestMethod.POST)
	@ResponseBody
	public String checkShareFileUpload(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFileUpdateDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, ShareFileUpdateDTO.class);
			reBody = shareDiskLogic.checkShareFileUpload(fileDTO);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#deleteShareFolder
	 */
	@RequestMapping(value = RESTurl.deleteShareFolder, method = RequestMethod.POST)
	@ResponseBody
	public String deleteShareFolder(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFolderUpdateDTO folderDTO = PojoMapper.fromJsonAsObject(requestBody, ShareFolderUpdateDTO.class);
			reBody = shareDiskLogic.deleteShareFolder(folderDTO);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getShareFileById
	 */
	@RequestMapping(value = RESTurl.getShareFileById, method = RequestMethod.POST)
	@ResponseBody
	public String getShareFileById(@RequestBody String fileId) {
		String reBody = "";
		try {
			ShareFileDTO fileDTO = shareDiskLogic.getShareFileById(Long.parseLong(fileId));
			reBody = PojoMapper.toJson(fileDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(fileId, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getShareFolderById
	 */
	@RequestMapping(value = RESTurl.getShareFolderById, method = RequestMethod.POST)
	@ResponseBody
	public String getShareFolderById(@RequestBody String folderId) {
		String reBody = "";
		try {
			ShareFolderDTO folderDTO = shareDiskLogic.getShareFolderById(Long.parseLong(folderId));
			reBody = PojoMapper.toJson(folderDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(folderId, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#restoreShareFolderAndFiles
	 */
	@RequestMapping(value = RESTurl.restoreShareFolderAndFiles, method = RequestMethod.POST)
	@ResponseBody
	public String restoreShareFolderAndFiles(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFolderAndFileUpdateDTO folderAndFileDTO = PojoMapper.fromJsonAsObject(requestBody,
			        ShareFolderAndFileUpdateDTO.class);
			shareDiskLogic.restoreShareFolderAndFile(folderAndFileDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getAllShareFoldersByEntId
	 */
	@RequestMapping(value = RESTurl.getAllShareFoldersByEntId, method = RequestMethod.POST)
	@ResponseBody
	public String getAllShareFoldersByEntId(@RequestBody String requestBody) {
		String reBody = "";
		try {
			List<ShareFolderDTO> folderDTOs = shareDiskLogic.getAllShareFoldersByEntId(Long.parseLong(requestBody));
			ShareFoldersDTO foldersDTO = new ShareFoldersDTO();
			foldersDTO.setShareFolderList(folderDTOs);
			reBody = PojoMapper.toJson(foldersDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getAllShareFilesByEntId
	 */
	@RequestMapping(value = RESTurl.getAllShareFilesByEntId, method = RequestMethod.POST)
	@ResponseBody
	public String getAllShareFilesByEntId(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFilesDTO filesDTO = shareDiskLogic.getAllShareFilesByEntId(Long.parseLong(requestBody));
			reBody = PojoMapper.toJson(filesDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getAllShareFolderAndFileByEntId
	 */
	@RequestMapping(value = RESTurl.getAllShareFolderAndFileByEntId, method = RequestMethod.POST)
	@ResponseBody
	public String getAllShareFolderAndFileByEntId(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFolderAndFileDTO folderAndFileDTO = shareDiskLogic.getAllShareFolderAndFileByEntId(Long
			        .parseLong(requestBody));
			reBody = PojoMapper.toJson(folderAndFileDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getAllShareFoldersByUserId
	 */
	@RequestMapping(value = RESTurl.getAllShareFoldersByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getAllShareFoldersByUserId(@RequestBody String requestBody) {
		String reBody = "";
		try {
			List<ShareFolderDTO> folderDTOs = shareDiskLogic.getAllShareFoldersByUserId(Long.parseLong(requestBody));
			ShareFoldersDTO foldersDTO = new ShareFoldersDTO();
			foldersDTO.setShareFolderList(folderDTOs);
			reBody = PojoMapper.toJson(foldersDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getAllShareFilesByUserId
	 */
	@RequestMapping(value = RESTurl.getAllShareFilesByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getAllShareFilesByUserId(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFilesDTO filesDTO = shareDiskLogic.getAllShareFilesByUserId(Long.parseLong(requestBody));
			reBody = PojoMapper.toJson(filesDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getAllShareFolderAndFileByUserId
	 */
	@RequestMapping(value = RESTurl.getAllShareFolderAndFileByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getAllShareFolderAndFileByUserId(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFolderAndFileDTO folderAndFileDTO = shareDiskLogic.getAllShareFolderAndFileByUserId(Long
			        .parseLong(requestBody));
			reBody = PojoMapper.toJson(folderAndFileDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getShareLinkFiles
	 */
	@RequestMapping(value = RESTurl.getShareLinkFiles, method = RequestMethod.POST)
	@ResponseBody
	public String getShareLinkFiles(@RequestBody String linkId) {
		String reBody = "";
		try {
			ShareLinkFilesDTO filesDTO = shareDiskLogic.getShareLinkFiles(Long.parseLong(linkId));
			reBody = PojoMapper.toJson(filesDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(linkId, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#syncEnterpriseFile
	 */
	@RequestMapping(value = RESTurl.syncEnterpriseFile, method = RequestMethod.POST)
	@ResponseBody
	public String syncEnterpriseFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFileUpdateDTO shareFileDTO = PojoMapper.fromJsonAsObject(requestBody, ShareFileUpdateDTO.class);
			reBody = shareDiskLogic.syncEnterpriseFile(shareFileDTO);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getShareFileHistory
	 */
	@RequestMapping(value = RESTurl.getShareFileHistory, method = RequestMethod.POST)
	@ResponseBody
	public String getShareFileHistory(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFileUpdateDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, ShareFileUpdateDTO.class);
			ShareHistoryDTO historyDTO = shareDiskLogic.getShareFileHistory(fileDTO);
			reBody = PojoMapper.toJson(historyDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getShareFolderHistory
	 */
	@RequestMapping(value = RESTurl.getShareFolderHistory, method = RequestMethod.POST)
	@ResponseBody
	public String getShareFolderHistory(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFolderUpdateDTO folderDTO = PojoMapper.fromJsonAsObject(requestBody, ShareFolderUpdateDTO.class);
			ShareHistoryDTO historyDTO = shareDiskLogic.getShareFolderHistory(folderDTO);
			reBody = PojoMapper.toJson(historyDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getShareFolderVersionDetail
	 */
	@RequestMapping(value = RESTurl.getShareFolderVersionDetail, method = RequestMethod.POST)
	@ResponseBody
	public String getShareFolderVersionDetail(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFolderUpdateDTO folderDTO = PojoMapper.fromJsonAsObject(requestBody, ShareFolderUpdateDTO.class);
			ShareHistoryDTO historyDTO = shareDiskLogic.getShareFolderVersionDetail(folderDTO);
			reBody = PojoMapper.toJson(historyDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#restoreShareFileVersion
	 */
	@RequestMapping(value = RESTurl.restoreShareFileVersion, method = RequestMethod.POST)
	@ResponseBody
	public String restoreShareFileVersion(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFileUpdateDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, ShareFileUpdateDTO.class);
			shareDiskLogic.restoreShareFileVersion(fileDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#restoreShareFolderVersion
	 */
	@RequestMapping(value = RESTurl.restoreShareFolderVersion, method = RequestMethod.POST)
	@ResponseBody
	public String restoreShareFolderVersion(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFolderUpdateDTO folderDTO = PojoMapper.fromJsonAsObject(requestBody, ShareFolderUpdateDTO.class);
			shareDiskLogic.restoreShareFolderVersion(folderDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getSyncShareFoldersByUserId
	 */
	@RequestMapping(value = RESTurl.getSyncShareFoldersByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getSyncShareFoldersByUserId(@RequestBody String requestBody) {
		String reBody = "";
		try {
			List<ShareFolderDTO> folderDTOs = shareDiskLogic.getSyncShareFoldersByUserId(Long.parseLong(requestBody));
			ShareFoldersDTO foldersDTO = new ShareFoldersDTO();
			foldersDTO.setShareFolderList(folderDTOs);
			reBody = PojoMapper.toJson(foldersDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getSyncShareFilesByUserId
	 */
	@RequestMapping(value = RESTurl.getSyncShareFilesByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getSyncShareFilesByUserId(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFilesDTO filesDTO = shareDiskLogic.getSyncShareFilesByUserId(Long.parseLong(requestBody));
			reBody = PojoMapper.toJson(filesDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getSyncShareFolderAndFileByUserId
	 */
	@RequestMapping(value = RESTurl.getSyncShareFolderAndFileByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getSyncShareFolderAndFileByUserId(@RequestBody String userId) {
		String reBody = "";
		try {
			ShareFolderAndFileDTO folderAndFileDTO = shareDiskLogic.getSyncShareFolderAndFileByUserId(Long
			        .parseLong(userId));
			reBody = PojoMapper.toJson(folderAndFileDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(userId, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#checkShareFileSyncUpload
	 */
	@RequestMapping(value = RESTurl.checkShareFileSyncUpload, method = RequestMethod.POST)
	@ResponseBody
	public String checkShareFileSyncUpload(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFileUpdateDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, ShareFileUpdateDTO.class);
			reBody = shareDiskLogic.checkShareFileSyncUpload(fileDTO);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getShareDiskVersion
	 */
	@RequestMapping(value = RESTurl.getShareDiskVersion, method = RequestMethod.POST)
	@ResponseBody
	public String getShareDiskVersion(@RequestBody String entId) {
		String reBody = "";
		try {
			reBody = shareDiskLogic.getShareDiskMaxVersion(Long.parseLong(entId));
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(entId, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#insertFileAccessRecord
	 */
	@RequestMapping(value = RESTurl.insertFileAccessRecord, method = RequestMethod.POST)
	@ResponseBody
	public String insertFileAccessRecord(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFileRecordDTO recordDTO = PojoMapper.fromJsonAsObject(requestBody, ShareFileRecordDTO.class);
			shareDiskLogic.insertRecord(recordDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getFileAccessRecord
	 */
	@RequestMapping(value = RESTurl.getFileAccessRecord, method = RequestMethod.POST)
	@ResponseBody
	public String getFileAccessRecord(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFileRecordDTO recordDTO = PojoMapper.fromJsonAsObject(requestBody, ShareFileRecordDTO.class);
			reBody = shareDiskLogic.getRecordByUserIdAndType(recordDTO.getUserId(), recordDTO.getRecordType());
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see ShareDiskUrl#getEntFolderAndFileByFolderId
	 */
	@RequestMapping(value = RESTurl.getEntFolderAndFileByFolderId, method = RequestMethod.POST)
	@ResponseBody
	public String getEntFolderAndFileByFolderId(@RequestBody String requestBody) {
		String reBody = "";
		try {
			FolderAndFileParamDTO paramDTO = PojoMapper.fromJsonAsObject(requestBody, FolderAndFileParamDTO.class);
			ShareFolderAndFileDTO folderAndFileDTO = shareDiskLogic.getEntFolderAndFileByFolderId(paramDTO);
			reBody = PojoMapper.toJson(folderAndFileDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}
	
	/**
	 * 取企业网盘回收站中文件夹和文件
	 * @param enterpriseId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getEntFolderAndFileInRecycle,method = RequestMethod.POST)
	@ResponseBody
	public String getEntFolderAndFileInRecycle(@RequestBody String enterpriseId){
		String responseBody = "";
		try {
			ShareFolderAndFileDTO shareFolderAndFileDTO = shareDiskLogic.getFolderAndFileInRecycle(Long.parseLong(enterpriseId));
			responseBody = PojoMapper.toJson(shareFolderAndFileDTO);
		} catch (Exception e) {
			responseBody = ErrorType.error500.toString();
			logger.error(responseBody, e);
		}
		return responseBody;
		
	}

	/**
	 * 锁定文件
	 * 
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.lockShareFile, method = RequestMethod.POST)
	@ResponseBody
	public String lockShareFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFileDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, ShareFileDTO.class);
			reBody = shareDiskLogic.lockShareFile(fileDTO);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 释放锁定的文件
	 * 
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.unlockShareFiles, method = RequestMethod.POST)
	@ResponseBody
	public String unlockShareFiles(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ShareFilesDTO filesDTO = PojoMapper.fromJsonAsObject(requestBody, ShareFilesDTO.class);
			shareDiskLogic.unlockShareFiles(filesDTO.getShareFileList(), filesDTO.getUserId());
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	@RequestMapping(value = RESTurl.checkEntDiskSizeExceed, method = RequestMethod.POST)
	@ResponseBody
	public String checkEntDiskSize(@RequestBody String strEntId) {
		String reBody = "";
		try {
			reBody = shareDiskLogic.checkEntDiskSizeExceed(Long.parseLong(strEntId));
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(strEntId, ex);
		}
		return reBody;
	}

	/**
	 * 编辑企业网盘文件前，检查权限，是否被他人锁定，并锁定文件
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = ServerInnerUrl.checkEditShareFile, method = RequestMethod.POST)
	@ResponseBody
	public String checkEditShareFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			ViewFileDTO viewFileDTO = PojoMapper.fromJsonAsObject(requestBody, ViewFileDTO.class);
			reBody = shareDiskLogic.checkEditShareFile(viewFileDTO);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * @see RESTurl#getShareDiskUsedSizeByEntId
	 */
	@RequestMapping(value = RESTurl.getShareDiskUsedSizeByEntId, method = RequestMethod.POST)
	@ResponseBody
	public String getShareDiskUsedSizeByEntId(@RequestBody String entId) {
		String reBody = "";
		try {
			long usedSize = shareDiskLogic.getShareDiskUsedSizeByEntId(Long.parseLong(entId));
			reBody = String.valueOf(usedSize);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(entId, ex);
		}
		return reBody;
	}

	@RequestMapping(value = ServerInnerUrl.checkSaveShareFile, method = RequestMethod.POST)
	@ResponseBody
	public String checkSaveShareFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			SaveFileDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, SaveFileDTO.class);
			reBody = shareDiskLogic.checkSaveShareFile(fileDTO);
		}
		catch (LogicException ex)
		{
			reBody = ex.getError().name();
		}
		catch (Exception ex)
		{
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	@RequestMapping(value = ServerInnerUrl.saveShareFile, method = RequestMethod.POST)
	@ResponseBody
	public String saveShareFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			SaveFileDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, SaveFileDTO.class);
			reBody = shareDiskLogic.saveShareFile(fileDTO);
		}
		catch (LogicException ex)
		{
			reBody = ex.getError().name();
		}
		catch (Exception ex)
		{
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}
}
