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

import com.conlect.oatos.dto.client.RecordVoiceDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.FileBeanUtils;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.utils.ConfigUtil;
import com.qycloud.web.utils.Logs;

/**
 * voice record servlet
 * 
 * @author yang
 * 
 */
public class VoiceRecordServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		response.setCharacterEncoding(CommConstants.CHARSET_UTF_8);

		String p = request.getParameter(RESTurl.userId);
		String result = null;
		try {
			String token = FileBeanUtils.getToken(request);

			int i = p.indexOf("R");
			int j = p.indexOf("S");
			int k = p.indexOf("T");
			
			long userId = Long.parseLong(p.substring(0, i));
			long receiverId = Long.parseLong(p.substring(i + 1, j));
			String userName = CommonUtil.ASCII2String(p.substring(j + 1, k));
			String receiverName = CommonUtil.ASCII2String(p.substring(k + 1));

			RecordVoiceDTO recordDTO = new RecordVoiceDTO();
			recordDTO.setUserId(userId);
			recordDTO.setUserName(userName);
			recordDTO.setReceiverId(receiverId);
			recordDTO.setReceiverName(receiverName);

			StringBuilder url = new StringBuilder(ConfigUtil.getValue(MyConst.FileCacheService));
			url.append(RESTurl.recordVoiceMail);

			HttpEntity postData = new InputStreamEntity(request.getInputStream(), -1);
			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, token);
			headers.put(RESTurl.postJsonData, PojoMapper.toJson(recordDTO));
			result = XhrProxy.getInstance().post(url.toString(), postData, headers);
		} catch (Exception ex) {
			result = ErrorType.error500.name();
			Logs.getLogger().error(p, ex);
		}
		response.getWriter().write(result);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
