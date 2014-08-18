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

import com.conlect.oatos.dto.client.FileDownloadDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.FileBeanUtils;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.utils.ConfigUtil;
import com.qycloud.web.utils.Logs;

/**
 * file download servlet
 * @author yang
 *
 */
public class FileDownloadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		response.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		
		FileDownloadDTO downloadBean = FileBeanUtils.parseDownloadBean(request);
		try {
			// url
			StringBuilder urlPath = new StringBuilder(ConfigUtil.getValue(MyConst.FileCacheService));
			urlPath.append(RESTurl.fileDownload);

			String postData = PojoMapper.toJson(downloadBean);
			HttpEntity postEntity = new StringEntity(postData, CommConstants.CHARSET_UTF_8);
			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, FileBeanUtils.getToken(request));
			HttpEntity entity = XhrProxy.getInstance().postForEntity(urlPath.toString(), postEntity, headers);
			if (entity != null && entity.getContent() != null) {
				String downName = FileBeanUtils.getDownName(request, downloadBean);
				response.setContentType("application/x-download; charset=UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=" + downName);
				entity.writeTo(response.getOutputStream());
			}

		} catch (Exception ex) {
			Logs.getLogger().error(downloadBean.toString(), ex);
		};
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
