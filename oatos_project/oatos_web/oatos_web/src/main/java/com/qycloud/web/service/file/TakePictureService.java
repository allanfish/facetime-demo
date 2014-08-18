package com.qycloud.web.service.file;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.entity.InputStreamEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.service.WebService;
import com.qycloud.web.utils.ConfigUtil;
import com.qycloud.web.utils.Logs;

@Controller
public class TakePictureService {

	@RequestMapping(WebService.TAKE_PICTURE)
	@ResponseBody
	public String execute(@RequestHeader(RESTurl.UserTokenkey) String token, HttpServletRequest request) {
		String result = null;
		try {
			StringBuilder url = new StringBuilder(ConfigUtil.getValue(MyConst.FileCacheService));
			url.append(RESTurl.tackPicture);

			HttpEntity postData = new InputStreamEntity(request.getInputStream(), -1);
			result = XhrProxy.getInstance().post(url.toString(), token, postData);
		} catch (Exception ex) {
			result = ErrorType.error500.name();
			Logs.getLogger().error("", ex);
		}

		return result;
	}
}
