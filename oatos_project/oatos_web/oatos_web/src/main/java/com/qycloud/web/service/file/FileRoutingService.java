package com.qycloud.web.service.file;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.service.WebService;
import com.qycloud.web.utils.ConfigUtil;

@Controller
@RequestMapping(WebService.FILE_ROUTING)
public class FileRoutingService {

	public void execute(@RequestHeader(RESTurl.UserTokenkey) String token, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String uri = request.getRequestURI();
		String path = uri;
		if (uri.startsWith(ConfigUtil.getAppName())) {
			path = uri.substring(ConfigUtil.getAppName().length());
		}

		StringBuilder urlPath = new StringBuilder(ConfigUtil.getValue(MyConst.FileCacheService));
		urlPath.append(RESTurl.getFileStream);

		Map<String, String> headers = new HashMap<String, String>();
		headers.put(RESTurl.UserTokenkey, token);
		HttpEntity postData = new StringEntity(path, CommConstants.CHARSET_UTF_8);
		HttpEntity entity = XhrProxy.getInstance().postForEntity(urlPath.toString(), postData, headers);
		entity.writeTo(response.getOutputStream());
	}

}
