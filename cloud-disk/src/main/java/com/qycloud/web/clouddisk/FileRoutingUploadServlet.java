package com.qycloud.web.clouddisk;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.InputStreamBody;

import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.FileBeanUtils;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.utils.ConfigUtil;
import com.qycloud.web.utils.Logs;

/**
 * Flash文件上传组件使用的Servlet
 * 
 * @author YUFEI
 *
 */
public class FileRoutingUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		response.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		
		FileUploadDTO uploadBean = FileBeanUtils.parseUploadBean(request);

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(5 * 1024 * 1024); // 5M缓冲,文件大于5M则保存到临时目录
		factory.setRepository(new File(request.getSession().getServletContext().getRealPath("temp"))); // 临时目录
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding(CommConstants.CHARSET_UTF_8);
		String result = CommConstants.OK_MARK;

		try {
			HttpEntity postEntity = null;
			if (RESTurl.getStartPoint.equals(uploadBean.getMethod()) || RESTurl.isUploadOver.equals(uploadBean.getMethod())) {
				postEntity = new StringEntity("", CommConstants.CHARSET_UTF_8);
			} else {
				FileItemIterator iterator = upload.getItemIterator(request);
				MultipartEntity multipartEntity = new MultipartEntity();
				while (iterator.hasNext()) {
					FileItemStream fis = iterator.next();
					if (!fis.isFormField()) {
						String fileName = FileUtil.getFileName(fis.getName());
						File dir = new File(request.getSession().getServletContext().getRealPath("temp"));
						if (!dir.mkdir()) {
							dir.mkdirs();
						}
						File tempFile = new File(dir, FileUtil.createFileGuidName(fileName));
						FileUtil.copyInputStreamToFile(fis.openStream(), tempFile);

						ContentBody body = new InputStreamBody(new FileInputStream(tempFile), CommonUtil.string2ASCII(fileName));
						multipartEntity.addPart(fileName, body);
					}
				}
				postEntity = multipartEntity;
			}
			StringBuilder postURL = new StringBuilder(ConfigUtil.getValue(MyConst.FileCacheService));
			postURL.append(RESTurl.fileUpload);

			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, FileBeanUtils.getToken(request));
			headers.put(RESTurl.postJsonData, PojoMapper.toJson(uploadBean));

			result = XhrProxy.getInstance().post(postURL.toString(), postEntity, headers);
		} catch (Exception ex) {
			Logs.getLogger().error(uploadBean.toString(), ex);
			result = ErrorType.error500.name();
		}
		response.getWriter().write(result);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
