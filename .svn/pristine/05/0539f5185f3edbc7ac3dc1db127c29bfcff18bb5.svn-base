package com.qycloud.web.clouddisk;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.MessageType;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.FileBeanUtils;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.utils.ConfigUtil;
import com.qycloud.web.utils.Logs;

/**
 * GWT文件上传组件使用的Servlet
 * 
 * @author Allan
 */
public class FileUploadServlet extends HttpServlet {

	// 日志
	private final static Logger logger = Logs.getLogger();

	private static final long serialVersionUID = 1L;
	HashMap<String, String> receivedContentTypes = new HashMap<String, String>();
	/**
	 * Maintain a list with received files and their content types.
	 */
	HashMap<String, File> receivedFiles = new HashMap<String, File>();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		super.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
			ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		super.doPost(request, response);
	}

	/**
	 * Override executeAction to save the received files in a custom place and
	 * delete this items from session.
	 */
	public String executeAction(HttpServletRequest request, List<FileItem> sessionFiles) {
		String path = getServletContext().getRealPath("/");
		File dir = null;

		String type = request.getParameter(RESTurl.type);
		if (isUploadToCloudDisk(type)) {
			return uploadToCloudDisk(request, sessionFiles);
		} else if (MyConst.BUDDIES_CSV.equals(type) || MyConst.FEEDBACK_FILE.equals(type)) {
			String filePath = request.getParameter("path");
			dir = new File(path, filePath);
			boolean result;
			if (!(result = dir.mkdir())) {
				result = dir.mkdirs();
			}
			logger.info("make directory " + dir + " : " + result);
		}

		String response = "";
		int cont = 0;
		for (FileItem item : sessionFiles) {
			if (false == item.isFormField()) {
				cont++;
				try {
					String fileName = FileUtil.getFileName(item.getName());
					File file = new File(dir, fileName);
					item.write(file);
					// / Save a list with the received files
					receivedFiles.put(item.getFieldName(), file);
					receivedContentTypes.put(item.getFieldName(), item.getContentType());

					// / Compose a xml message with the full file information
					// which can be parsed in client side
					response += "<file-" + cont + "-field>" + item.getFieldName() + "</file-" + cont + "-field>\n";
					response += "<file-" + cont + "-name>" + item.getName() + "</file-" + cont + "-name>\n";
					response += "<file-" + cont + "-size>" + item.getSize() + "</file-" + cont + "-size>\n";
					response += "<file-" + cont + "-type>" + item.getContentType() + "</file-" + cont + "type>\n";
				} catch (Exception e) {
					throw new AssertionError(e);
				}
			}
		}
		// / Send information of the received files to the client.
		return "<response>\n" + response + "</response>\n";
	}

	/**
	 * Check if upload to cloud disk
	 * 
	 * @param type
	 *            file type
	 * @return
	 */
	private boolean isUploadToCloudDisk(String type) {
		return CommConstants.FILE_TYPE_TEMP.equals(type) || CommConstants.FILE_TYPE_ONLINEDISK.equals(type)
				|| CommConstants.FILE_TYPE_ICON.equals(type) || CommConstants.FILE_TYPE_ENTLOGO.equals(type)
				|| CommConstants.FILE_TYPE_SHAREDISK.equals(type) || MessageType.InstantFile.equals(type)
				|| MessageType.ShareInstantFile.equals(type) || MessageType.OfflineFile.equals(type)
				|| CommConstants.FILE_TYPE_CONFERENCE_DOC.equals(type)
				|| CommConstants.FILE_TYPE_SHAREFILE_THUMB.equals(type)
				|| CommConstants.FILE_TYPE_PRIVATEFILE_THUMB.equals(type);
	}

	public void removeItem(HttpServletRequest request, String fieldName) {
		File file = receivedFiles.get(fieldName);
		receivedFiles.remove(fieldName);
		receivedContentTypes.remove(fieldName);
		if (file != null) {
			file.delete();
		}
	}

	/**
	 * upload file to cloud disk
	 * 
	 * @param request
	 * @param sessionFiles
	 * @param type
	 *            type
	 * @return
	 * @throws IOException 
	 */
	private String uploadToCloudDisk(HttpServletRequest request, List<FileItem> sessionFiles) {
		String result = "";
		try {
			FileUploadDTO uploadBean = FileBeanUtils.parseUploadBean(request);

			MultipartEntity multipartEntity = new MultipartEntity();
			for (FileItem fi : sessionFiles) {
				if (!fi.isFormField()) {
					String fileName = FileUtil.getFileName(fi.getName());
					ContentBody body = new InputStreamBody(fi.getInputStream(), CommonUtil.string2ASCII(fileName));
					multipartEntity.addPart(fileName, body);
				}
			}
			StringBuilder postURL = new StringBuilder(ConfigUtil.getValue(MyConst.FileCacheService));
			postURL.append(RESTurl.fileUpload);

			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, FileBeanUtils.getToken(request));
			headers.put(RESTurl.postJsonData, PojoMapper.toJson(uploadBean));

			HttpEntity httpEntity = XhrProxy.getInstance().postForEntity(postURL.toString(), multipartEntity, headers);
			result = EntityUtils.toString(httpEntity);
		} catch (Exception ex) {
			Logs.getLogger().error("", ex);
			result = ErrorType.error500.name();
		}
		return result;
	}

}
