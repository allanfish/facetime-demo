package com.qycloud.web.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.EnterpriseLoginDTO;
import com.conlect.oatos.dto.client.ReLoginDTO;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.utils.SysLogger;
import com.qycloud.web.ServiceUtil;
import com.qycloud.web.activemq.AjaxWebClient;
import com.qycloud.web.activemq.AmqContainer;

@Controller
public class FlexService {

	@RequestMapping(WebService.FLEX_SERVICE)
	@ResponseBody
	public String execute(@RequestHeader("serviceType") String serviceType,
			@RequestHeader(required = false, value = RESTurl.UserTokenkey) String token, @RequestBody String postJson,
			HttpServletRequest req) throws Exception {

		String result = "error";

		if (RESTurl.enterpriseUserLogin.equals(serviceType)) {
			SysLogger.getOsLogger().debug("User Login Record: " + postJson);
			EnterpriseLoginDTO loginDTO = PojoMapper.fromJsonAsObject(postJson, EnterpriseLoginDTO.class);
			loginDTO.setIp(req.getRemoteAddr());
			String postData = PojoMapper.toJson(loginDTO);
			result = ServiceUtil.invoke(token, serviceType, postData);

			AjaxWebClient client = AmqContainer.getAjaxWebClient(req);
			if (client != null) {
				synchronized (client) {
					client.setToken(result);
				}
			}
		} else if (RESTurl.reLogin.equals(serviceType)) {
			result = ServiceUtil.invoke(token, serviceType, postJson);

			ReLoginDTO reLoginDTO = PojoMapper.fromJsonAsObject(postJson, ReLoginDTO.class);
			if (reLoginDTO.getClientId() != null)
				req.setAttribute(MyConst.QUER_KEY_CLIENT_ID, reLoginDTO.getClientId());
			AjaxWebClient client = AmqContainer.getAjaxWebClient(req);
			if (client != null) {
				synchronized (client) {
					client.setToken(result);
				}
			}
		} else {
			result = ServiceUtil.invoke(token, serviceType, postJson);
		}
		return result;
	}
}
