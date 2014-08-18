package com.qycloud.oatos.convert.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.doc.DocConvertDTO;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.DocConvertUrl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.convert.domain.logic.ConvertLogic;
import com.qycloud.oatos.convert.util.Logs;

/**
 * 文档转换对外服务接口
 * @author yang
 *
 */
@Controller
@RequestMapping(DocConvertUrl.ConvertService)
public class ConvertService {

	private final static Logger logger = Logs.getLogger();
	
	@Autowired
	private ConvertLogic converter;

	/**
	 * @see DocConvertUrl#docConvert
	 */
	@RequestMapping(value = DocConvertUrl.docConvert, method = RequestMethod.POST)
	public @ResponseBody String docConvert(@RequestBody  String postData, @RequestHeader(RESTurl.UserTokenkey) String token) {
		String reBody = "";
		try {
			DocConvertDTO docDTO = PojoMapper.fromJsonAsObject(postData, DocConvertDTO.class);
			docDTO.setToken(token);
			reBody = converter.convert(docDTO);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * @see DocConvertUrl#checkTextCharset
	 */
	@RequestMapping(value = DocConvertUrl.checkTextCharset, method = RequestMethod.POST)
	public void checkTextCharset(@RequestBody  String postData) {
		try {
			converter.checkTextCharset(postData);
		}
		catch (Exception ex) {
			logger.error(postData, ex);
		}
	}

}
