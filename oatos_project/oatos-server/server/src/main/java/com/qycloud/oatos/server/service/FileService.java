package com.qycloud.oatos.server.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.AcceptFileDTO;
import com.conlect.oatos.dto.client.FileDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.SaveFileDTO;
import com.conlect.oatos.dto.client.SendFileDTO;
import com.conlect.oatos.dto.client.TakePictureDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.ServerInnerUrl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.logic.FileLogic;
import com.qycloud.oatos.server.util.BllLogger;
import com.qycloud.oatos.server.util.LogicException;

/**
 * file service
 * 
 * @author yang
 * 
 */
@Controller("FileService")
public class FileService {
	private final static Logger logger = BllLogger.getLogger();

	@Autowired
	FileLogic fileLogic;

	/**
	 * save file to online disk
	 * 
	 * @param request
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.copySystemFile, method = RequestMethod.POST)
	@ResponseBody
	public String copySystemFile(@RequestBody String requestBody) {
		String reBody = "";
		try {
			// save file to online disk
			SaveFileDTO saveFileToDiskDTO = PojoMapper.fromJsonAsObject(requestBody, SaveFileDTO.class);
			fileLogic.copySystemFile(saveFileToDiskDTO);
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
	 * send file
	 * 
	 * @return
	 */
	@RequestMapping(value = ServerInnerUrl.sendFile, method = RequestMethod.POST)
	@ResponseBody
	public String sendFile(@RequestBody String requestBody) {
		
		String reBody = "";
		try {
			SendFileDTO sendFileDTO = PojoMapper.fromJsonAsObject(requestBody, SendFileDTO.class);
			reBody = fileLogic.sendFile(sendFileDTO);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
			logger.error(requestBody, ex);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * accept sending files
	 * 
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.acceptFile, method = RequestMethod.POST)
	@ResponseBody
	public String acceptFile(@RequestBody String requestBody) {
		if (requestBody == null || requestBody.isEmpty()) {
			return ErrorType.errorRequestData.name();
		}

		String reBody = "";

		try {
			AcceptFileDTO acceptDTO = PojoMapper.fromJsonAsObject(requestBody, AcceptFileDTO.class);

			if (acceptDTO == null) {
				return ErrorType.errorRequestData.name();
			}
			else {

				reBody = fileLogic.acceptFile(acceptDTO.getUserId(), acceptDTO.getFileList());
			}

		}
		catch (LogicException ex) // 逻辑层错误
		{
			reBody = ex.getError().name();
			logger.error(requestBody, ex);
		}
		catch (Exception ex) // 其他异常
		{
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 截图
	 * 
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.screenshot, method = RequestMethod.POST)
	@ResponseBody
	public String screenshot(@RequestBody String requestBody) {
		String reBody = "";
		try {
			NetworkFileDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, NetworkFileDTO.class);
			reBody = fileLogic.screenshot(fileDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	/**
	 * 拍照并保存
	 * 
	 * @param requestBody
	 * @return
	 */
	@RequestMapping(value = RESTurl.tackPictureAndSave, method = RequestMethod.POST)
	@ResponseBody
	public String tackPictureAndSave(@RequestBody String requestBody) {
		String reBody = "";
		try {
			logger.info("take picture json: " + requestBody);
			TakePictureDTO takePictureDTO = PojoMapper.fromJsonAsObject(requestBody, TakePictureDTO.class);
			reBody = fileLogic.tackPictureAndSave(takePictureDTO);
			logger.info("take picture result: " + reBody);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

	@RequestMapping(value = ServerInnerUrl.updateFilePageCount, method = RequestMethod.POST)
	@ResponseBody
	public String updateFilePageCount(@RequestBody String requestBody) {
		String reBody = "";
		try {
			FileDTO fileDTO = PojoMapper.fromJsonAsObject(requestBody, FileDTO.class);
			fileLogic.updateFilePageCount(fileDTO);
			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(requestBody, ex);
		}
		return reBody;
	}

}
