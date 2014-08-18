package com.qycloud.web.mail;

import static com.conlect.oatos.utils.SysLogger.osLogger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conlect.oatos.dto.autobean.INetworkFileDTO;
import com.conlect.oatos.dto.autobean.mail.IMailAttachDTO;
import com.conlect.oatos.dto.autobean.mail.IMailQueryDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.NetworkFilesDTO;
import com.conlect.oatos.dto.client.mail.MailAccountDTO;
import com.conlect.oatos.dto.client.mail.MailDTO;
import com.conlect.oatos.dto.client.mail.MailFolderDTO;
import com.conlect.oatos.dto.client.mail.MailFolderListDTO;
import com.conlect.oatos.dto.client.mail.MailListDTO;
import com.conlect.oatos.dto.client.mail.MailQueryDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.mail.MailHelper;
import com.conlect.oatos.utils.SysLogger;
import com.qycloud.web.utils.ConfigUtil;

public class MailAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getHeader(RESTurl.action);
		String strAccount = req.getHeader(RESTurl.mailAccountDTO);
		String userToken = req.getHeader(RESTurl.UserTokenkey);
		String strMailQuery = req.getHeader(RESTurl.mailQueryDTO);
		String strMail = req.getParameter(RESTurl.mailDTO);
		MailAccountDTO account = null;
		IMailQueryDTO mailQuery = null;
		SysLogger.osLogger.debug("doPost, action=" + action);
		if (strAccount != null) {
			account = PojoMapper.fromJsonAsObject(strAccount,
					MailAccountDTO.class);
			decodeMailAccount(account);
		}
		if (strMailQuery != null) {
			mailQuery = PojoMapper.fromJsonAsObject(strMailQuery,
					MailQueryDTO.class);
			decodeMailQueryDTO(mailQuery);
		}
		if (strMail != null) {
			SysLogger.osLogger.debug("strMail=" + strMail);
			strMail = strMail.replaceAll(CommonUtil.string2ASCII("&"), "&");
		}
		String strResponse = "";
		if (RESTurl.getMailFolders.equals(action)) {
			strResponse = getMailFolders(account, userToken);
		} else if (RESTurl.getMailList.equals(action)) {
			strResponse = getMailList(strMailQuery, userToken);
		} else if (RESTurl.sendReplyMail.equals(action)) {
			MailDTO mail = PojoMapper.fromJsonAsObject(strMail, MailDTO.class);
			strResponse = sendReplyMail(account, mail);
		} else if (RESTurl.sendNewMail.equals(action)) {
			MailDTO mail = PojoMapper.fromJsonAsObject(strMail, MailDTO.class);
			strResponse = sendNewMail(account, mail, userToken);
		} else if (RESTurl.deleteMails.equals(action)) {
			strResponse = deleteMails(account, mailQuery, userToken);
		} else if (RESTurl.getMailFolder.equals(action)) {
			strResponse = getMailFolder(account, mailQuery);
		} else if (RESTurl.addMailAccount.equals(action)) {
			strResponse = addMailAccount(account, userToken);
		} else if (RESTurl.updateMailAccount.equals(action)) {
			strResponse = updateMailAccount(account, userToken);
		} else if (RESTurl.setMailRead.equals(action)) {
			strResponse = setMailRead(account, mailQuery, userToken);
		} else if (RESTurl.receiveLatestMailFolder.equals(action)) {
			strResponse = receiveNewestFolder(account, mailQuery, userToken);
		}
		resp.setContentType("text/html; charset=UTF-8");
		resp.getWriter().print(strResponse);
		resp.getWriter().flush();
	}

	private void decodeMailAccount(MailAccountDTO account) {
		if(account == null )
			return;
		if(account.getSenderName() != null)
			account.setSenderName(urlDecode(account.getSenderName()));
	}

	private void decodeMailQueryDTO(IMailQueryDTO mailQuery) {
		if (mailQuery.getFolderurl() != null)
			mailQuery.setFolderurl(urlDecode(mailQuery.getFolderurl()));
		if (mailQuery.getSubject() != null)
			mailQuery.setSubject(urlDecode(mailQuery.getSubject()));
		if (mailQuery.getSubjects() != null
				&& mailQuery.getSubjects().size() > 0)
			for (int i = 0; i < mailQuery.getSubjects().size(); i++)
				mailQuery.getSubjects().set(i,
						urlDecode(mailQuery.getSubjects().get(i)));
	}

	public static String urlDecode(String raw) {
		if (raw != null) {
			try {
				return URLDecoder.decode(raw, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return raw;
	}

	private String receiveNewestFolder(MailAccountDTO account,
			IMailQueryDTO mailQuery, String userToken) {
		String responseBody = ErrorType.error500.name();
		try {
			String result = MailUtils.receiveLatestMailFolder(mailQuery,
					userToken);
			responseBody = result;
		} catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
		}
		return responseBody;
	}

	private String setMailRead(MailAccountDTO account, IMailQueryDTO mailQuery,
			String userToken) {
		String strResponse = null;
		try {
			MailHelper.setMailRead(account, mailQuery.getFolderurl(),
					mailQuery.getMessageId(), mailQuery.getSubject());
			strResponse = MailUtils.setMailRead(mailQuery.getMailId(),
					userToken);
		} catch (Exception e) {
			e.printStackTrace();
			strResponse = ErrorType.error500.name();
		}
		return strResponse;
	}

	private String updateMailAccount(MailAccountDTO account, String userToken) {
		String responseBody = "";
		try {
			boolean connectResult = MailHelper.canConnect(account);
			if (!connectResult) {
				responseBody = CommConstants.ERROR_MARK;
			} else {
				responseBody = MailUtils.updateMailAccount(account, userToken);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			SysLogger.osLogger.error(ex);
		}
		return responseBody;
	}

	private String addMailAccount(MailAccountDTO account, String token) {
		String responseBody = "";
		try {
			boolean connectResult = MailHelper.canConnect(account);
			if (!connectResult) {
				return null;
			}
			responseBody = MailUtils.addMailAccount(account, token);
		} catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			SysLogger.osLogger.error(ex);
		}
		return responseBody;
	}

	/**
	 * 获取邮箱文件夹信息
	 */
	private String getMailFolder(MailAccountDTO account, IMailQueryDTO mailQuery) {
		String responseBody = "";
		try {
			MailFolderDTO mailFolder = MailHelper.getMailFolder(account,
					mailQuery.getFolderurl());
			responseBody = PojoMapper.toJson(mailFolder);
		} catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
		}
		return responseBody;
	}

	private String deleteMails(MailAccountDTO account, IMailQueryDTO mailQuery,
			String userToken) {
		String responseBody = "";
		try {
			MailHelper.deleteMails(account, mailQuery.getFolderurl(),
					mailQuery.getMessageIds(), mailQuery.getSubjects());
			MailUtils.deleteMail(mailQuery, userToken);
			responseBody = CommConstants.OK_MARK;
		} catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			SysLogger.osLogger.error(ex);
		}
		return responseBody;
	}

	private String sendNewMail(MailAccountDTO account, MailDTO mail,
			String userToken) {
		String responseBody = "";
		try {
			if (mail.isHaveAttach() && mail.getAttachs() != null
					&& mail.getAttachs().size() > 0) {
				sendAttachMail(account, mail, userToken);
			} else {
				MailHelper.sendMail(account, mail);
			}
			responseBody = CommConstants.OK_MARK;
		} catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			SysLogger.osLogger.error(ex);
		}
		return responseBody;
	}

	private MimeMessage sendAttachMail(MailAccountDTO account, MailDTO mail,
			String userToken) throws Exception {
		NetworkFilesDTO fileListDTO = MailUtils.getNetworkFilePath(
				getAttachFileNames(mail), userToken);
		if (fileListDTO == null) {
			osLogger.error("attach file is null");
		}

		List<NetworkFileDTO> fileList = fileListDTO.getNetworkFileDTOList();
		for (INetworkFileDTO file : fileList) {
			IMailAttachDTO attachDTO = getAttachDTO(mail, file.getName());
			if (attachDTO != null) {
				attachDTO.setFileId(file.getFileId());
				attachDTO.setSize(file.getSize());
				attachDTO.setFilePath(getFileDownloadUrl(file.getUserId(),
						file.getGuid(), file.getName(), file.getFolderId(),
						userToken, ConfigUtil.getValue("CloudDiskIP")));
				attachDTO.setMimeType(file.getType());
				attachDTO.setFileName(file.getName());
				SysLogger.osLogger.debug(String.format(
						"attach file name:%s, attach file url:%s",
						attachDTO.getFileName(), attachDTO.getFilePath()));
			}
		}
		SysLogger.osLogger.debug("sendAttachMail, OK!!!!");
		return MailHelper.sendMail(account, mail);
	}

	public static String getFileDownloadUrl(long userId, String guid,
			String name, Long folderId, String token, String diskIp) {
		StringBuilder url = new StringBuilder("http://" + diskIp
				+ ConfigUtil.getAppName() + RESTurl.fileDownload);
		// user id
		url.append("?").append(RESTurl.userId).append("=").append(userId);
		// type
		url.append("&").append(RESTurl.type).append("=")
				.append(CommConstants.FILE_TYPE_ONLINEDISK);
		// guid
		url.append("&").append(RESTurl.guidName).append("=").append(guid);
		// token
		url.append("&").append(RESTurl.UserTokenkey).append("=").append(token);
		// file name
		url.append("&").append(RESTurl.fileName).append("=")
				.append(CommonUtil.string2ASCII(name));
		// cloud disk ip
		url.append("&").append(RESTurl.diskIP).append("=").append(diskIp);
		// folderId
		if (folderId != null) {
			url.append("&").append(RESTurl.folderId).append("=")
					.append(folderId.longValue());
		}
		return url.toString();
	}

	private IMailAttachDTO getAttachDTO(MailDTO mail, String fileName) {
		for (IMailAttachDTO attachDTO : mail.getAttachs()) {
			if (fileName.equals(attachDTO.getFileName()))
				return attachDTO;
		}
		return null;
	}

	private String[] getAttachFileNames(MailDTO mail) {
		List<String> attachNames = new ArrayList<String>(mail.getAttachs()
				.size());
		for (IMailAttachDTO attachDTO : mail.getAttachs()) {
			attachNames.add(attachDTO.getFileName());
		}
		return attachNames.toArray(new String[] {});
	}

	private String sendReplyMail(MailAccountDTO account, MailDTO mail) {
		String responseBody = "";
		try {
			MailHelper.sendReplyMail(account, mail);
			responseBody = CommConstants.OK_MARK;
		} catch (Exception ex) {
			ex.printStackTrace();
			responseBody = ErrorType.error500.name();
			SysLogger.osLogger.error(ex);
		}
		return responseBody;
	}

	/**
	 * 获取邮件的文件夹信息
	 * 
	 * @param userToken
	 */
	private String getMailFolders(MailAccountDTO account, String userToken) {
		String responseBody = "";
		try {
			String result = MailUtils.getMailFolders(account, userToken);
			MailFolderListDTO folderListDTO = PojoMapper.fromJsonAsObject(
					result, MailFolderListDTO.class);
			if (result != null && folderListDTO != null
					&& folderListDTO.getFolders().size() > 0) {
				responseBody = result;
			} else {
				List<MailFolderDTO> folders = MailHelper.takeMailFolders(
						account, new MailQueryDTO());
				responseBody = MailUtils.saveMailFolders(account, userToken,
						new MailFolderListDTO(folders));
			}
		} catch (Exception ex) {
			responseBody = ErrorType.error500.name();
			SysLogger.osLogger.error(ex);
		}
		return responseBody;
	}

	/**
	 * 获取邮件信息列表
	 * 
	 * @param userToken
	 *            TODO
	 */
	private String getMailList(String query, String userToken) {
		String responseBody = "";
		try {
			String result = MailUtils.getMailList(query, userToken);
			MailListDTO mailListDTO = PojoMapper.fromJsonAsObject(result,
					MailListDTO.class);
			// 只有查询最新的邮件才到邮件服务器中取
			if (mailListDTO != null) {
				responseBody = result;
			} else {
				responseBody = CommConstants.ERROR_MARK;
			}
		} catch (Exception ex) {
			responseBody = ErrorType.error500.name();
			SysLogger.osLogger.error(ex);
		}
		return responseBody;
	}
}
