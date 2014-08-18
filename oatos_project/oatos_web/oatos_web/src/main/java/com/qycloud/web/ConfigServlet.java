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
 * config
 * 
 * @author yang
 * @mapping /getConfig
 */
public class ConfigServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		response.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		response.setContentType("text/plain; charset=UTF-8");
		String config = "config/config.xml";
		InputStream is = new FileInputStream(getServletContext().getRealPath(config));
		FileUtil.copyStream(is, response.getOutputStream());
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
