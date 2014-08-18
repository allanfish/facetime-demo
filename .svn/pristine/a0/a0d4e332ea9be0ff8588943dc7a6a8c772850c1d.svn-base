package com.qycloud.web.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.entity.InputStreamEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.RecordVoiceDTO;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.utils.ConfigUtil;
import com.qycloud.web.utils.Logs;

@Controller
public class VoiceRecordService {

	@RequestMapping(WebService.VOICE_RECORD)
	@ResponseBody
	public String execute(@RequestParam(RESTurl.userId) String p, @RequestHeader(RESTurl.UserTokenkey) String token,
			HttpServletRequest request) throws Exception {
		String result = null;
		try {
			int i = p.indexOf("R");
			int j = p.indexOf("S");
			int k = p.indexOf("T");

			long userId = Long.parseLong(p.substring(0, i));
			long receiverId = Long.parseLong(p.substring(i + 1, j));
			String userName = CommonUtil.ASCII2String(p.substring(j + 1, k));
			String receiverName = CommonUtil.ASCII2String(p.substring(k + 1));

			RecordVoiceDTO recordDTO = new RecordVoiceDTO();
			recordDTO.setUserId(userId);
			recordDTO.setUserName(userName);
			recordDTO.setReceiverId(receiverId);
			recordDTO.setReceiverName(receiverName);

			StringBuilder url = new StringBuilder(ConfigUtil.getValue(MyConst.FileCacheService));
			url.append(RESTurl.recordVoiceMail);

			HttpEntity postData = new InputStreamEntity(request.getInputStream(), -1);
			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, token);
			headers.put(RESTurl.postJsonData, PojoMapper.toJson(recordDTO));
			result = XhrProxy.getInstance().post(url.toString(), postData, headers);
		} catch (Exception ex) {
			result = ErrorType.error500.name();
			Logs.getLogger().error(p, ex);
		}
		return result;
	}

}
