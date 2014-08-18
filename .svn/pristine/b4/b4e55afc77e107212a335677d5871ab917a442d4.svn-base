package com.qycloud.web.clouddisk;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.log4j.Logger;

import com.conlect.oatos.dto.autobean.IViewFileDTO;
import com.conlect.oatos.dto.client.CheckPermissionDTO;
import com.conlect.oatos.dto.client.ViewFileResultDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.FilePermission;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.utils.ConfigUtil;
import com.qycloud.web.utils.Logs;

/**
 * file logic
 * 
 * @author yang
 */
public class FileService {

	private final static Logger logger = Logs.getLogger();

	/**
	 * 远程服务请求代理
	 */
	private static XhrProxy proxy = XhrProxy.getInstance();

	/**
	 * service
	 * 
	 * @param token
	 * @param method
	 * @param postData
	 * @return
	 */
	public static String service(String token, String method, String postData) {
		String result = "";
		try {
			if (RESTurl.copySystemFile.equals(method)) {
				String userId = postData;
				// TODO
			} else {
				StringBuilder postURL = new StringBuilder(
						ConfigUtil.getValue(MyConst.FileCacheService));
				postURL.append(method);
				result = proxy.post(postURL.toString(), token, postData);
			}
		} catch (Exception ex) {
			logger.error(postData, ex);
			result = ErrorType.error500.name();
		}
		return result;
	}

	public static ViewFileResultDTO getMediaUrl(String contextPath, String token, IViewFileDTO fileDTO) {
		ViewFileResultDTO resultDTO = new ViewFileResultDTO(fileDTO);

		try {
			// check permission
			String check = CommConstants.OK_MARK;
			if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getType()) && fileDTO.isCheckPermission()) {
				CheckPermissionDTO checkDTO = new CheckPermissionDTO(fileDTO.getUserId(), fileDTO.getFolderId(), FilePermission.Read);
				String postData = PojoMapper.toJson(checkDTO);
				check = XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.checkPermission), token, postData);
			}
			if (CommConstants.OK_MARK.equals(check)) {
				String tempPath = "temp/" + fileDTO.getGuid();
				File temp = new File(contextPath, tempPath);
				if (!temp.exists()) {
					File dir = new File(contextPath, "temp");
					if (!dir.mkdir()) {
						dir.mkdirs();
					}
					StringBuilder urlPath = new StringBuilder(ConfigUtil.getValue(MyConst.FileCacheService));
					urlPath.append(RESTurl.getMediaStream);

					String postData = PojoMapper.toJson(fileDTO);
					HttpEntity postEntity = new StringEntity(postData, CommConstants.CHARSET_UTF_8);
					Map<String, String> headers = new HashMap<String, String>();
					headers.put(RESTurl.UserTokenkey, token);
					HttpEntity entity = proxy.postForEntity(urlPath.toString(), postEntity, headers);
					FileUtil.copyInputStreamToFile(entity.getContent(), temp);
				}
				resultDTO.setUrl(tempPath);
			} else {
				resultDTO.setMessage(check);
			}
		} catch (Exception ex) {
			resultDTO.setMessage(ErrorType.error500.name());
		}

		return resultDTO;
	}

}
