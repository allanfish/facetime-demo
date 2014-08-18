package com.qycloud.oatos.filecache.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.doc.DocConvertDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.file.ServerInnerUrl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.filecache.logic.FileConverterMonitorLogic;
import com.qycloud.oatos.filecache.util.Logs;

@Controller("FileConverterMonitorService")
public class FileConverterMonitorService {

	private Logger logger = Logs.getLogger();

	@Autowired
	private FileConverterMonitorLogic monitorLogic;
	
	@RequestMapping(value = ServerInnerUrl.fileConvertMonitor, method = RequestMethod.POST)
	public @ResponseBody
	String fileConvertMonitor(@RequestBody String postData) {
		String r = "";
		try {
			monitorLogic.monitor(postData);
		} catch (Exception ex) {
			r = ErrorType.error500.name();
		}
		return r;
	}
	
	
	@RequestMapping(value = ServerInnerUrl.afterFileConvert, method = RequestMethod.POST)
	public @ResponseBody
	String afterFileConvert(@RequestBody String postData) {
		String r = "";
		try {
			DocConvertDTO docDTO = PojoMapper.fromJsonAsObject(postData, DocConvertDTO.class);
			monitorLogic.afterFileConvert(docDTO);
			r = CommConstants.OK_MARK;
		} catch (Exception ex) {
			r = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return r;
	}
}
