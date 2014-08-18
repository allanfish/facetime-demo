package com.qycloud.web.service;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.file.FileUtil;

/**
 * mobile app version
 * @author yang
 *@mapping /getVersion
 */
@Controller
public class VersionService {
	private static final String PC_APP = "pc";
	private static final String MOBILE_APP = "mobile";
	private static final String PARAM_PLATFORM = "platform";

	@RequestMapping(WebService.GET_VERSION)
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		response.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		response.setContentType("text/plain; charset=UTF-8");

		String platform = request.getHeader(PARAM_PLATFORM);
		String versionFile = null;

		if (PC_APP.equals(platform)) {
			versionFile = "ext/version.xml";
		} else if (MOBILE_APP.equals(platform)) {
			versionFile = "mobile/version.xml";
		}
		if (versionFile != null) {
			InputStream is = new FileInputStream(request.getServletContext().getRealPath(versionFile));
			FileUtil.copyStream(is, response.getOutputStream());
		}
	}
}
