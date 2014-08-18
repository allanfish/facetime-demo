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
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.utils.ConfigUtil;
import com.qycloud.web.utils.Logs;

/**
 * take picture
 * @author yang
 * @path {@link com.conlect.oatos.dto.status.RESTurl.tackPictureAndSave}
 */
public class TakePictureAndSaveServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		// get token
		String token = request.getHeader(RESTurl.UserTokenkey);
		// TakePictureDTO json
		String postJsonData = request.getHeader(RESTurl.postJsonData);

		String result = CommConstants.ERROR_MARK;
		try {
			StringBuilder url = new StringBuilder(ConfigUtil.getValue(MyConst.FileCacheService));
			url.append(RESTurl.tackPictureAndSave);
			
			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, token);
			headers.put(RESTurl.postJsonData, postJsonData);
			HttpEntity postData = new InputStreamEntity(request.getInputStream(), -1);
			result = XhrProxy.getInstance().post(url.toString(), postData, headers);
		} catch (Exception ex) {
			result = ErrorType.error500.name();
			Logs.getLogger().error(postJsonData, ex);
		}
		response.getWriter().write(result);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
