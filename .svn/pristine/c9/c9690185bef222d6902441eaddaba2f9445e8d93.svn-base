package com.qycloud.web.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.InputStreamBody;

import com.conlect.oatos.dto.autobean.INetworkFileDTO;
import com.conlect.oatos.dto.autobean.mail.IMailAttachDTO;
import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.mail.MailAccountDTO;
import com.conlect.oatos.dto.client.mail.MailAttachDTO;
import com.conlect.oatos.dto.client.mail.MailFileDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.conlect.oatos.mail.MailHelper;
import com.conlect.oatos.utils.SysLogger;
import com.qycloud.web.utils.ConfigUtil;

/**
 * 下载邮件附件的Servlet
 * 
 */
public class DownloadMailAttachServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private XhrProxy proxy = XhrProxy.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		request.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		response.setCharacterEncoding(CommConstants.CHARSET_UTF_8);

		try {
			String mailAccountId = request.getParameter(RESTurl.mailAccountId);
			String diskIp = request.getParameter(RESTurl.diskIP);
			String mailAttachId = request.getParameter(RESTurl.mailAttachId);
			String folderurl = request.getParameter(RESTurl.folderurl);
			String messageId = request.getParameter(RESTurl.messageId);
			String userToken = request.getParameter(RESTurl.UserTokenkey);
			String userId = userToken != null ? userToken.split("@")[0] : "";
			String enterpriseId = request.getParameter(RESTurl.enterpriseId);

			IMailAttachDTO mailAttachDTO = MailUtils.getMailAttach(Long.parseLong(mailAttachId), userToken);

			response.setContentType("application/x-download; charset=UTF-8");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + encodStr(request, mailAttachDTO.getFileName()));
			// 检查邮件附件是否已存入网盘
			Long fileId = mailAttachDTO.getFileId();
			if (fileId != null) {
				// 邮件附件已存入网盘, 从网盘下载
				try {
					INetworkFileDTO networkFile = MailUtils.getNetworkFile(fileId, userToken);

					String fileDownloadUrl = getFileDownloadUrl(Long.parseLong(userId), Long.parseLong(enterpriseId),
							CommConstants.FILE_TYPE_ONLINEDISK, networkFile.getGuid(), networkFile.getName(),
							networkFile.getFolderId(), userToken, diskIp);
					SysLogger.osLogger.debug("fileDownloadUrl=" + fileDownloadUrl);
					HttpEntity post = proxy.postForEntity(fileDownloadUrl, null, null);
					FileUtil.copyStream(post.getContent(), response.getOutputStream());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}

			MailAccountDTO mailAcc = MailUtils.getMailAccount(mailAccountId, userToken);
			// 附件还未存入网盘，
			String guidName = UUID.randomUUID().toString().replace("-", "").toLowerCase() + "."
					+ CommonUtil.getFileSuffixName(mailAttachDTO.getFileName());
			// 下载至本地临时文件
			
			String tempPath = request.getSession().getServletContext().getRealPath("") + "/temp/" + guidName;
			FileUtil.copyStream(
					MailHelper.getAttachInputStream(mailAcc, folderurl, messageId, mailAttachDTO.getFileName()),
					new FileOutputStream(new File(tempPath)));

			// 开启线程上传附件至网盘，并将记录保存至DB
			new SaveToNetWorkThread(new FileInputStream(new File(tempPath)), mailAttachDTO, guidName,
					Long.parseLong(userId), userToken, diskIp).run();

			// 将文件传输至用户
			FileUtil.copyStream(new FileInputStream(tempPath), response.getOutputStream());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 下载失败消息
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(CommConstants.ERROR_MARK);
		response.getWriter().flush();
	}

	/**
	 * 保存至网盘
	 */
	private class SaveToNetWorkThread extends Thread {
		private InputStream fileInputStream;
		private IMailAttachDTO mailAttachDTO;
		private String diskIp;
		private String guidName;
		private long userId;
		private String userToken;
		private long folderId = -1;

		public SaveToNetWorkThread(InputStream fileInputStream, IMailAttachDTO mailAttachDTO, String guidName,
				long userId, String userToken, String diskIp) {
			super();
			this.fileInputStream = fileInputStream;
			this.mailAttachDTO = mailAttachDTO;
			this.guidName = guidName;
			this.userId = userId;
			this.userToken = userToken;
			this.diskIp = diskIp;
			folderId = MailUtils.getMailFolder(userId, userToken).getFolderId();
		}

		@Override
		public void run() {
			super.run();
			// 上传至网盘
			MultipartEntity multipartEntity = new MultipartEntity();
			ContentBody body = new InputStreamBody(fileInputStream,
					CommonUtil.string2ASCII(mailAttachDTO.getFileName()));
			multipartEntity.addPart(mailAttachDTO.getFileName(), body);

			StringBuilder postURL = new StringBuilder("http://");
			postURL.append(diskIp);
			postURL.append(ConfigUtil.getAppName());
			postURL.append("/fileRoutingUpload");
			
			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, userToken);
			headers.put(RESTurl.postJsonData, PojoMapper.toJson(toParameters()));
			
			String result = XhrProxy.getInstance().post(postURL.toString(), multipartEntity, headers);
			System.out.println("upload to network, result=" + result);
			if (CommConstants.OK_MARK.equals(result)) {
				//更新数据至DB
				addFile(userId, mailAttachDTO.getFileName(), guidName, mailAttachDTO.getSize(), userToken,
						mailAttachDTO.getMailId(), mailAttachDTO.getAttachId());
			}
		}

		public FileUploadDTO toParameters() {
			FileUploadDTO bean = new FileUploadDTO();
			bean.setUserId(userId);
			bean.setGuidName(guidName);
			bean.setFolderId(folderId);
			bean.setType(CommConstants.FILE_TYPE_ONLINEDISK);
			return bean;
		}

		/**
		 * 写入数据库
		 * 
		 * @param userId
		 * @param fileName
		 * @param guidName
		 * @param fileSize
		 * @param token
		 * @param mailId
		 * @param attachId
		 * @return
		 */
		private String addFile(long userId, String fileName, String guidName, long fileSize, String token, long mailId,
				long attachId) {

			INetworkFileDTO file = new NetworkFileDTO();
			file.setUserId(userId);
			file.setName(fileName);
			file.setGuid(guidName);
			file.setSize(Math.round(fileSize / 1024F));
			file.setType(CommonUtil.getFileSuffixName(fileName));

			MailAttachDTO attach = new MailAttachDTO();
			attach.setAttachId(attachId);
			attach.setMailId(mailId);
			attach.setFileName(fileName);

			MailFileDTO mailFile = new MailFileDTO();
			mailFile.setMailAttach(attach);
			mailFile.setNetworkFile(file);
			SysLogger.osLogger.debug("addFile, mailFile=" + PojoMapper.toJson(mailFile));
			return proxy.post(ConfigUtil.getServiceUrl(RESTurl.addMailFile), token, PojoMapper.toJson(mailFile));
		}
	}

	public static String getFileDownloadUrl(long userId, long enterpriseId, String type, String guid, String name,
			Long folderId, String token, String diskIp) {
		StringBuilder url = new StringBuilder("http://" + diskIp + ConfigUtil.getAppName() + RESTurl.fileDownload);
		// user id
		url.append("?").append(RESTurl.userId).append("=").append(userId);
		// file from id
		url.append("&").append(RESTurl.enterpriseId).append("=").append(enterpriseId);
		// type
		url.append("&").append(RESTurl.type).append("=").append(type);
		// guid
		url.append("&").append(RESTurl.guidName).append("=").append(guid);
		// token
		url.append("&").append(RESTurl.UserTokenkey).append("=").append(token);
		// file name
		url.append("&").append(RESTurl.fileName).append("=").append(CommonUtil.string2ASCII(name));
		// cloud disk ip
		url.append("&").append(RESTurl.diskIP).append("=").append(diskIp);
		// folderId
		if (folderId != null) {
			url.append("&").append(RESTurl.folderId).append("=").append(folderId.longValue());
		}
		return url.toString();
	}

	private String encodStr(HttpServletRequest request, String raw) throws UnsupportedEncodingException {
		String agent = request.getHeader("User-Agent").toLowerCase();
		if (agent.indexOf("msie") > 0) {
			raw = URLEncoder.encode(raw, CommConstants.CHARSET_UTF_8);
		} else {
			raw = new String(raw.getBytes(CommConstants.CHARSET_UTF_8), "ISO8859-1");
		}
		raw = raw.replaceAll("\\s", "+");
		return raw;
	}
}
