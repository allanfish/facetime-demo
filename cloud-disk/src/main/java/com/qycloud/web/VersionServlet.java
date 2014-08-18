package com.qycloud.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.file.FileUtil;

/**
 * mobile app version
 * @author yang
 *@mapping /getVersion
 */
public class VersionServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String PC_APP = "pc";
	
	private static final String MOBILE_APP = "mobile";
	
	private static final String PARAM_PLATFORM = "platform";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
			InputStream is = new FileInputStream(getServletContext().getRealPath(versionFile));
			FileUtil.copyStream(is, response.getOutputStream());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
