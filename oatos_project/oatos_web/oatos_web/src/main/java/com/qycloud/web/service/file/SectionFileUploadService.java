package com.qycloud.web.service.file;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.entity.InputStreamEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.FileBeanUtils;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.service.WebService;
import com.qycloud.web.utils.ConfigUtil;
import com.qycloud.web.utils.Logs;

/**
 * 文件分段上传
 * @author yang
 *
 */
@Controller
public class SectionFileUploadService {

	@RequestMapping(WebService.SECTION_FILE_UPLOAD)
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		response.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		response.setContentType("text/plain; charset=UTF-8");

		// parameters
		FileUploadDTO uploadBean = FileBeanUtils.parseUploadBean(request);

		// result
		String result = "";
		try {
			StringBuilder url = new StringBuilder(ConfigUtil.getValue(MyConst.FileCacheService));
			url.append(RESTurl.sectionFileUpload);

			HttpEntity postData = new InputStreamEntity(request.getInputStream(), -1);

			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, FileBeanUtils.getToken(request));
			headers.put(RESTurl.postJsonData, PojoMapper.toJson(uploadBean));

			result = XhrProxy.getInstance().post(url.toString(), postData, headers);
		} catch (Exception ex) {
			ex.printStackTrace();
			result = ErrorType.error500.name();
			Logs.getLogger().error(uploadBean.toString(), ex);
		}
		response.getWriter().write(result);
	}
}
