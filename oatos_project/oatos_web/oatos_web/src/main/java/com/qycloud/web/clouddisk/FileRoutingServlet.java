package com.qycloud.web.clouddisk;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.FileBeanUtils;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.utils.ConfigUtil;
import com.qycloud.web.utils.Logs;

/**
 * file routing servlet
 * @author yang
 *
 */
public class FileRoutingServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		response.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		try {
			String token = FileBeanUtils.getToken(request);
			
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
		} catch (Exception ex) {
			Logs.getLogger().error(request.getRequestURI(), ex);
		}
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
