package com.qycloud.web.clouddisk;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.entity.InputStreamEntity;

import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.FileBeanUtils;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.utils.ConfigUtil;
import com.qycloud.web.utils.Logs;

/**
 * screen shot
 * @author yang
 * @path {@link com.conlect.oatos.dto.status.RESTurl.screenshot}
 */
public class ScreenshotServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		String result = CommConstants.ERROR_MARK;
		String userId = request.getParameter(RESTurl.userId);
		try {
			StringBuilder url = new StringBuilder(ConfigUtil.getValue(MyConst.FileCacheService));
			url.append(RESTurl.screenshot);

			String token = FileBeanUtils.getToken(request);

			HttpEntity postData = new InputStreamEntity(request.getInputStream(), -1);
			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, token);
			headers.put(RESTurl.userId, userId);
			result = XhrProxy.getInstance().post(url.toString(), postData, headers);
		} catch (Exception ex) {
			result = ErrorType.error500.name();
			Logs.getLogger().error(userId, ex);
		}
		response.getWriter().write(result);
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
