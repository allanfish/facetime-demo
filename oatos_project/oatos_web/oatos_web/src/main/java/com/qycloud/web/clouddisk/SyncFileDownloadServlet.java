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
 * 网盘文件同步下载
 * @author yang
 *
 */
public class SyncFileDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final XhrProxy proxy = XhrProxy.getInstance();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		response.setCharacterEncoding(CommConstants.CHARSET_UTF_8);

		FileDownloadDTO downloadBean = FileBeanUtils.parseDownloadBean(request);
		try {
			StringBuilder urlPath = new StringBuilder(ConfigUtil.getValue(MyConst.FileCacheService));
			urlPath.append(RESTurl.sectionFileDownload);

			String postData = PojoMapper.toJson(downloadBean);
			HttpEntity postEntity = new StringEntity(postData, CommConstants.CHARSET_UTF_8);
			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, FileBeanUtils.getToken(request));
			HttpEntity entity = proxy.postForEntity(urlPath.toString(), postEntity, headers);
			if (entity != null && entity.getContent() != null) {
				entity.writeTo(response.getOutputStream());
			}

		} catch (Exception ex) {
			Logs.getLogger().error(downloadBean.toString(), ex);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
