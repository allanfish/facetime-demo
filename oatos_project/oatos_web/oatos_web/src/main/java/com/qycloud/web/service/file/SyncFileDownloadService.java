package com.qycloud.web.service.file;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.conlect.oatos.dto.client.FileDownloadDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.service.WebService;
import com.qycloud.web.utils.ConfigUtil;
import com.qycloud.web.utils.Logs;

@Controller
public class SyncFileDownloadService {

	private static final XhrProxy proxy = XhrProxy.getInstance();

	@RequestMapping(WebService.SYNC_FILE_DOWNLOAD)
	public void execute(FileDownloadDTO downloadBean, @RequestHeader(RESTurl.UserTokenkey) String token,
			HttpServletResponse response) throws Exception {
		try {
			StringBuilder urlPath = new StringBuilder(ConfigUtil.getValue(MyConst.FileCacheService));
			urlPath.append(RESTurl.sectionFileDownload);

			String postData = PojoMapper.toJson(downloadBean);
			HttpEntity postEntity = new StringEntity(postData, CommConstants.CHARSET_UTF_8);
			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, token);
			HttpEntity entity = proxy.postForEntity(urlPath.toString(), postEntity, headers);
			if (entity != null && entity.getContent() != null) {
				entity.writeTo(response.getOutputStream());
			}

		} catch (Exception ex) {
			Logs.getLogger().error(downloadBean.toString(), ex);
		}

	}
}
