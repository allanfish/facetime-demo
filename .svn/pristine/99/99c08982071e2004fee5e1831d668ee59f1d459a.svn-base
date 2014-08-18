package com.qycloud.web.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.bean.jms.JmsSender;
import com.qycloud.web.utils.ConfigUtil;

/**
 * JMS消息发送服务
 * 
 * @author Allan
 *
 */
@Controller
public class SendMessageService {

	private XhrProxy http = XhrProxy.getInstance();

	@Autowired
	private JmsSender jmsSender;

	@RequestMapping(WebService.SEND_MESSAGE)
	@ResponseBody
	public String execute(@RequestHeader(RESTurl.UserTokenkey) String token, HttpServletRequest request,
			@RequestBody String postData) throws ServletException, IOException {
		String result = CommConstants.OK_MARK;
		MessageDTO message = PojoMapper.fromJsonAsObject(postData, MessageDTO.class);
		result = saveMessage(token, message.getMessageType(), postData);
		if (CommConstants.OK_MARK.equals(result)) {
			jmsSender.sendMessage(String.valueOf(message.getReceiver()), postData);
		}
		return result;
	}

	private String saveMessage(String token, String type, String messageJson) {
		if (token != null) {
			if (CommonUtil.isSaveMessage(type)) {
				return http.post(ConfigUtil.getServiceUrl(RESTurl.sendMessage), token, messageJson);
			}
		}
		return CommConstants.OK_MARK;
	}

}
