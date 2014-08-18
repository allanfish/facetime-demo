package com.qycloud.oatos.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.mail.ExtendMailDTO;
import com.conlect.oatos.dto.client.mail.MailDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.logic.mail.MailSender;
import com.qycloud.oatos.server.util.BllLogger;

/**
 * 发送邮件服务
 * @author yang
 *
 */
@Controller("SendMailService")
public class SendMailService {

	@Autowired
	private MailSender mailSender;

	/**
	 * 邮件发送服务
	 * 
	 * @param response
	 * @param requestBody
	 */
	@RequestMapping(value = RESTurl.sendMail, method = RequestMethod.POST)
	@ResponseBody
	public String sendMail(@RequestBody String requestBody) {
		String reBody = "";
		try {
			MailDTO dto = PojoMapper.fromJsonAsObject(requestBody, MailDTO.class);
			ExtendMailDTO extendDto = new ExtendMailDTO();
			extendDto.setMailDTO(dto);
			extendDto.setSendForSystemAccount(false);

			mailSender.sendMail(extendDto);

			reBody = CommConstants.OK_MARK;
		}
		catch (Exception ex)
		{
			reBody = ErrorType.error500.name();
			BllLogger.getLogger().error(requestBody, ex);
		}
		return reBody;

	}

}
