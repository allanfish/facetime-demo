package com.qycloud.web.clouddisk;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.FileBeanUtils;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.utils.ConfigUtil;
import com.qycloud.web.utils.Logs;

/**
 * 文件检查Servlet
 * 
 */
public class CheckDiskFileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		try {
			String fileId = request.getParameter(RESTurl.fileId);
			String type = request.getParameter(RESTurl.type);
			String token = FileBeanUtils.getToken(request);

			String postURL = ConfigUtil.getValue(MyConst.FileCacheService) + RESTurl.checkDiskFile;
			
			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, token);
			headers.put(RESTurl.fileId, fileId);
			headers.put(RESTurl.type, type);
			String result = XhrProxy.getInstance().post(postURL, null, headers);

			response.getWriter().write(result);
		} catch (Exception e) {
			Logs.getLogger().error("", e);
			response.getWriter().write(ErrorType.error500.name());
		}
	}
}
