package com.qycloud.oatos.server.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.FolderAndFileDTO;
import com.conlect.oatos.dto.client.FolderAndFileParamDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.NetworkFilesDTO;
import com.conlect.oatos.dto.client.NetworkFolderDTO;
import com.conlect.oatos.dto.client.NetworkFoldersDTO;
import com.conlect.oatos.dto.client.SaveFileDTO;
import com.conlect.oatos.dto.client.user.PersonDiskAllocListDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.ServerInnerUrl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.entity.PersonalFile;
import com.qycloud.oatos.server.domain.entity.PersonalFolder;
import com.qycloud.oatos.server.domain.logic.PersonalDiskLogic;
import com.qycloud.oatos.server.util.BllLogger;
import com.qycloud.oatos.server.util.LogicException;

/**
 * 个人网盘服务
 * @author yang
 *
 */
@Controller("PersonalDiskService")
public class PersonalDiskService {
	private final static Logger logger = BllLogger.getLogger();

	@Autowired
	PersonalDiskLogic personalDiskLogic;

	/**
	 * 增加文件夹
	 * @param folder
	 */
	@RequestMapping(value = RESTurl.addDiskFolder, method = RequestMethod.POST)
	@ResponseBody
	public String addFolder(@RequestBody String requestBody) {
		String reBody = "";
		try {
			NetworkFolderDTO folderDto = PojoMapper.fromJsonAsObject(requestBody, NetworkFolderDTO.class);
			if (folderDto == null) {
				return ErrorType.errorRequestData.name();
			}
			else {
				PersonalFolder folder = new PersonalFolder(folderDto);
				reBody = personalDiskLogic.addNetworkFolder(folder);
			}
		}
		catch (LogicException ex) // 逻辑层错误
		{
			reBody = ex.getError().name();
		}
		catch (Exception ex) // 其他异常
		{
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}

		return reBody;
	}

	/**
	 * 修改文件夹
	 * @param folder
	 */
	@RequestMapping(value = RESTurl.updateDiskFolder, method = RequestMethod.POST)
	@ResponseBody
	public String updateFoler(@RequestBody String requestBody) {
		String reBody = "";
		try {
			NetworkFolderDTO folderDto = PojoMapper.fromJsonAsObject(requestBody, NetworkFolderDTO.class);
			if (folderDto == null) {
				return ErrorType.errorRequestData.name();
			}
			else {
				PersonalFolder folder = new PersonalFolder(folderDto);
				personalDiskLogic.updateNetworkFoler(folder);
				reBody = CommConstants.OK_MARK;
			}
		}
		catch (LogicException ex) // 逻辑层错误
		{
			reBody = ex.getError().name();
		}
		catch (Exception ex) // 其他异常
		{
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}

		return reBody;
	}

	/**
	 * 获取用户下的文件夹
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getFoldersByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getFolderList(@RequestBody String requestBody) {
		String reBody = "";
		try {
			long userId = Long.parseLong(requestBody);
			List<NetworkFolderDTO> folderDtos = personalDiskLogic.getFoldersByUserId(userId);
			NetworkFoldersDTO networkFoldersDTO = new NetworkFoldersDTO();
			networkFoldersDTO.setNetworkFolderDTOList(folderDtos);
			reBody = PojoMapper.toJson(networkFoldersDTO);
		}
		catch (LogicException ex) // 逻辑层错误
		{
			reBody = ex.getError().name();
		}
		catch (Exception ex) // 其他异常
		{
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 修改文件
	 * @param file
	 */
	@RequestMapping(value = RESTurl.updateDiskFile, method = RequestMethod.POST)
	@ResponseBody
	public String updateFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			NetworkFileDTO dto = PojoMapper.fromJsonAsObject(requestBody, NetworkFileDTO.class);
			PersonalFile model = new PersonalFile(dto);
			personalDiskLogic.updateNetworkFile(model);
			reBody = CommConstants.OK_MARK;
		}
		catch (LogicException ex) // 逻辑层错误
		{
			reBody = ex.getError().name();
		}
		catch (Exception ex) // 其他异常
		{
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 获取用户文件
	 * @param UserId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getFilesByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getFileList(@RequestBody String requestBody) {
		String reBody = "";
		try {
			long userId = Long.parseLong(requestBody);
			List<NetworkFileDTO> dtos = personalDiskLogic.getFilesByUserId(userId);

			NetworkFilesDTO filesDTO = new NetworkFilesDTO();
			filesDTO.setNetworkFileDTOList(dtos);
			reBody = PojoMapper.toJson(filesDTO);
		}
		catch (LogicException ex) // 逻辑层错误
		{
			reBody = ex.getError().name();
		}
		catch (Exception ex) // 其他异常
		{
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}

		return reBody;
	}

	/**
	 * 删掉文件或文件夹
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.deleteFolderAndFile, method = RequestMethod.POST)
	@ResponseBody
	public String deleteFolderAndFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			FolderAndFileDTO folderAndFileDTO = PojoMapper.fromJsonAsObject(requestBody, FolderAndFileDTO.class);
			reBody = personalDiskLogic.deleteFolderAndFile(folderAndFileDTO);
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

	/**
	 * 更新文件或文件夹
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateFolderAndFile, method = RequestMethod.POST)
	@ResponseBody
	public String updateFolderAndFile(@RequestBody String requestBody) {
		String reBody = "";

		try {
			FolderAndFileDTO folderAndFileDTO = PojoMapper.fromJsonAsObject(requestBody, FolderAndFileDTO.class);
			if (folderAndFileDTO == null) {
				return ErrorType.errorRequestData.name();
			}
			else {
				personalDiskLogic.updateFolderAndFile(folderAndFileDTO);
				reBody = CommConstants.OK_MARK;
			}

		}
		catch (LogicException ex) // 逻辑层错误
		{
			reBody = ex.getError().name();
		}
		catch (Exception ex) // 其他异常
		{
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 取所有网盘文件夹和文件
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getDiskFolderAndFileByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getDiskFolderAndFileByUserId(@RequestBody String userId) {
		String reBody = "";
		try {
			FolderAndFileDTO folderAndFileDTO = personalDiskLogic.getFolderAndFileByUserId(Long.parseLong(userId));
			reBody = PojoMapper.toJson(folderAndFileDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(userId, ex);
		}
		return reBody;
	}

	/**
	 * 增加个人用户文件
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.addPersonalFile, method = RequestMethod.POST)
	@ResponseBody
	public String addPersonalFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			NetworkFileDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, NetworkFileDTO.class);
			long fileId = personalDiskLogic.addPersonalFile(new PersonalFile(fileDTO));
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
	 * 删除个人用户文件
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.deletePersonalFile, method = RequestMethod.POST)
	@ResponseBody
	public String deletePersonalFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			personalDiskLogic.deleteFile(Long.parseLong(requestBody));
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
	 * 个人网盘检查是否存在同名文件
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.checkPersonalFileUpload, method = RequestMethod.POST)
	@ResponseBody
	public String checkPersonalFileUpload(@RequestBody String requestBody) {
		String reBody = "";
		try {
			NetworkFileDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, NetworkFileDTO.class);
			reBody = personalDiskLogic.checkPersonalFileUpload(fileDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 个人网盘获取单个文件
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.getFileById, method = RequestMethod.POST)
	@ResponseBody
	public String getFileById(@RequestBody String fileId) {
		String reBody = "";
		try {
			NetworkFileDTO fileDTO = personalDiskLogic.getFileById(Long.parseLong(fileId));
			reBody = PojoMapper.toJson(fileDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(fileId, ex);
		}
		return reBody;
	}

	/**
	 * 修改个人网盘的文件夹和文件
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.restoreFolderAndFile, method = RequestMethod.POST)
	@ResponseBody
	public String restoreFolderAndFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			FolderAndFileDTO folderAndFileDTO = PojoMapper.fromJsonAsObject(requestBody, FolderAndFileDTO.class);
			personalDiskLogic.restoreFolderAndFile(folderAndFileDTO);
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
	 * 个人网盘获取用户文件夹，包括回收站中文件
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getAllFoldersByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getAllFoldersByUserId(@RequestBody String requestBody) {
		String reBody = "";
		try {
			long userId = Long.parseLong(requestBody);
			List<NetworkFolderDTO> folderDtos = personalDiskLogic.getAllFoldersByUserId(userId);
			NetworkFoldersDTO networkFoldersDTO = new NetworkFoldersDTO();
			networkFoldersDTO.setNetworkFolderDTOList(folderDtos);
			reBody = PojoMapper.toJson(networkFoldersDTO);
		}
		catch (Exception ex) // 其他异常
		{
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 获取用户文件包括回收站中文件
	 * @param UserId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getAllFilesByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getAllFilesByUserId(@RequestBody String requestBody) {
		String reBody = "";
		try {
			long userId = Long.parseLong(requestBody);
			List<NetworkFileDTO> dtos = personalDiskLogic.getAllFilesByUserId(userId);
			NetworkFilesDTO filesDTO = new NetworkFilesDTO();
			filesDTO.setNetworkFileDTOList(dtos);
			reBody = PojoMapper.toJson(filesDTO);
		}
		catch (Exception ex) // 其他异常
		{
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 取所有网盘文件夹和文件
	 * @param enterpriseId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getAllDiskFolderAndFileByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getAllDiskFolderAndFileByUserId(@RequestBody String userId) {
		String reBody = "";
		try {
			FolderAndFileDTO folderAndFileDTO = personalDiskLogic.getAllFolderAndFileByUserId(Long.parseLong(userId));
			reBody = PojoMapper.toJson(folderAndFileDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(userId, ex);
		}
		return reBody;
	}

	/**
	 * 同步个人网盘文件
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.syncPersonalFile, method = RequestMethod.POST)
	@ResponseBody
	public String syncPersonalFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			NetworkFileDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, NetworkFileDTO.class);
			reBody = personalDiskLogic.syncPersonalFile(new PersonalFile(fileDTO));
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
	 * 取邮件附件文件夹
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.getEmailFolder, method = RequestMethod.POST)
	@ResponseBody
	public String getEmailFolder(@RequestBody String requestBody) {
		String reBody = "";
		try {
			reBody = PojoMapper.toJson(personalDiskLogic.getEmailFolder(Long.parseLong(requestBody)));
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

	@RequestMapping(value = RESTurl.getSystemFileByFileName, method = RequestMethod.POST)
	@ResponseBody
	public String getSystemFileByFileName(@RequestBody String  strNetworkFile) {
		String responseBody = null;
		try {
			NetworkFileDTO networkFile = PojoMapper.fromJsonAsObject(strNetworkFile, NetworkFileDTO.class);
//			networkFile.setName(CommonUtil.ASCII2String(networkFile.getName()));
			NetworkFileDTO file = personalDiskLogic.getFile(networkFile.getUserId(), networkFile.getFolderName(), networkFile.getName());
			responseBody = PojoMapper.toJson(file);
		}
		catch (Exception e) {
			responseBody = ErrorType.error500.name();
			logger.error(e);
		}
		return responseBody;
	}

	/**
	 * 获取个人网盘文件夹信息
	 * @param folderId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getPrivateFolderById, method = RequestMethod.POST)
	@ResponseBody
	public String getPrivateFolderById(@RequestBody String folderId) {
		String reBody = "";
		try {
			NetworkFolderDTO folderDTO = personalDiskLogic.getPrivateFolder(Long.parseLong(folderId));
			reBody = PojoMapper.toJson(folderDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(folderId, ex);
		}
		return reBody;
	}

	/**
	 * 个人网盘文件同步上传之前，检查文件版本，网盘空间，权限，同名文件
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.checkPersonalFileSyncUpload, method = RequestMethod.POST)
	@ResponseBody
	public String checkPersonalFileSyncUpload(@RequestBody String requestBody) {
		String reBody = "";
		try {
			NetworkFileDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, NetworkFileDTO.class);
			reBody = personalDiskLogic.checkPersonalFileSyncUpload(fileDTO);
		}
		catch (LogicException ex)
		{
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 按文件夹取个人网盘子文件夹和文件，取顶层文件夹时，文件夹ID传null
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.getPersonalFolderAndFileByFolderId, method = RequestMethod.POST)
	@ResponseBody
	public String getPersonalFolderAndFileByFolderId(@RequestBody String requestBody) {
		String reBody = "";
		try {
			FolderAndFileParamDTO paramDTO = PojoMapper.fromJsonAsObject(requestBody, FolderAndFileParamDTO.class);
			FolderAndFileDTO folderAndFileDTO = personalDiskLogic.getPersonalFolderAndFileByFolderId(paramDTO);
			reBody = PojoMapper.toJson(folderAndFileDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}
	
	/**
	 * 通过用户ID获得个人网盘回收站中的文件和文件夹
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getPersonalFolderAndFileInRecycle,method = RequestMethod.POST)
	@ResponseBody
	public String getPersonalFolderAndFileInRecycle(@RequestBody String userId){
		String responseBody = "";
		try {
			FolderAndFileDTO folderAndFileDTO = personalDiskLogic.getPersonalFolderAndFileInRecycle(Long.parseLong(userId));
			responseBody = PojoMapper.toJson(folderAndFileDTO);
		} catch (Exception e) {
			responseBody = ErrorType.error500.name();
			logger.error(responseBody, e);
		}
		return responseBody;
	}

	@RequestMapping(value = RESTurl.checkUserDiskSizeExceed, method = RequestMethod.POST)
	@ResponseBody
	public String checkUserDiskSizeExceed(@RequestBody String strUserId) {
		String reBody = "";
		try {
			reBody = personalDiskLogic.checkUserDiskSizeExceed(Long.parseLong(strUserId));
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(strUserId, ex);
		}
		return reBody;
	}

	@RequestMapping(value = RESTurl.allocPersonDisk, method = RequestMethod.POST)
	@ResponseBody
	public String allocPersonDiskExceed(@RequestBody String strPersonDiskList) {
		String result = "";
		try {
			PersonDiskAllocListDTO personDiskList = PojoMapper.fromJsonAsObject(strPersonDiskList,
			        PersonDiskAllocListDTO.class);
			result = personalDiskLogic.allocPersonDisk(personDiskList);
		}
		catch (LogicException ex) {
			result = ex.getError().name();
		}
		catch (Exception ex) {
			result = ErrorType.error500.name();
			logger.error(strPersonDiskList, ex);
		}
		return result;
	}

	/**
	 * @see RESTurl#getPersonalDiskUsedSizeByUserId
	 */
	@RequestMapping(value = RESTurl.getPersonalDiskUsedSizeByUserId, method = RequestMethod.POST)
	@ResponseBody
	public String getPersonalDiskUsedSizeByUserId(@RequestBody String userId) {
		String result = "";
		try {
			long usedSize = personalDiskLogic.getPersonalDiskUsedSizeByUserId(Long.parseLong(userId));
			result = String.valueOf(usedSize);
		}
		catch (LogicException ex) {
			result = ex.getError().name();
		}
		catch (Exception ex) {
			result = ErrorType.error500.name();
			logger.error(userId, ex);
		}
		return result;
	}

	@RequestMapping(value = ServerInnerUrl.checkSavePersonalFile, method = RequestMethod.POST)
	@ResponseBody
	public String checkSavePersonalFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			SaveFileDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, SaveFileDTO.class);
			reBody = personalDiskLogic.checkSavePersonalFile(fileDTO);
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

	@RequestMapping(value = ServerInnerUrl.savePersonalFile, method = RequestMethod.POST)
	@ResponseBody
	public String savePersonalFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			SaveFileDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, SaveFileDTO.class);
			reBody = personalDiskLogic.savePersonalFile(fileDTO);
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
