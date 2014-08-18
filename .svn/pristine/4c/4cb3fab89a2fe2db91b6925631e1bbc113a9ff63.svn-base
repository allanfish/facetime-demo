package com.conlect.oatos.file;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.conlect.oatos.dto.client.FileDownloadDTO;
import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.utils.StringUtils;

/**
 * 文件上传，下载参数解析
 * 
 * @author yang
 * 
 */
public class FileBeanUtils {

	/**
	 * 解析文件下载参数
	 * 
	 * @param request
	 * @return
	 */
	public static FileDownloadDTO parseDownloadBean(HttpServletRequest request) {
		FileDownloadDTO bean = new FileDownloadDTO();
		// parameters
		try {
			String userId = request.getParameter(RESTurl.userId);
			bean.setUserId(Long.parseLong(userId));
		} catch (Exception ex) {
		}
		try {
			String entId = request.getParameter(RESTurl.enterpriseId);
			bean.setEntId(Long.parseLong(entId));
		} catch (Exception ex) {
		}
		try {
			String fid = request.getParameter(RESTurl.fileId);
			Long fileId = Long.parseLong(fid);
			if (fileId <= 0) {
				fileId = null;
			}
			bean.setFileId(fileId);
		} catch (Exception ex) {
		}
		try {
			String fi = request.getParameter(RESTurl.folderId);
			Long folderId = Long.parseLong(fi);
			if (folderId <= 0) {
				folderId = null;
			}
			bean.setFolderId(folderId);
		} catch (Exception ex) {
		}
		String fileNameASCII = request.getParameter(RESTurl.fileName);
		bean.setFileNameASCII(fileNameASCII);
		if (StringUtils.isValid(fileNameASCII)) {
			bean.setFileName(CommonUtil.ASCII2String(fileNameASCII));
		}

		bean.setGuidName(request.getParameter(RESTurl.guidName));
		bean.setType(request.getParameter(RESTurl.type));
		try {
			String start = request.getParameter(RESTurl.START_POSITION);
			bean.setStart(Integer.parseInt(start));
		} catch (Exception ex) {
		}
		try {
			String end = request.getParameter(RESTurl.END_POSITION);
			bean.setEnd(Integer.parseInt(end));
		} catch (Exception ex) {
		}

		if (CommConstants.FILE_TYPE_SHAREDISK.equals(bean.getType())) {
			String chk = request.getParameter(RESTurl.checkDownPermission);
			if (RESTurl.FALSE.equals(chk)) {
				bean.setCheckDownPermission(false);
			} else {
				bean.setCheckDownPermission(true);
			}
		}
		return bean;
	}

	/**
	 * 解析文件上传参数
	 * 
	 * @param request
	 * @return
	 */
	public static FileUploadDTO parseUploadBean(HttpServletRequest request) {
		FileUploadDTO bean = new FileUploadDTO();
		// parameters
		try {
			String fid = request.getParameter(RESTurl.fileId);
			Long fileId = Long.parseLong(fid);
			if (fileId <= 0) {
				fileId = null;
			}
			bean.setFileId(fileId);
		} catch (Exception ex) {
		}
		try {
			String userId = request.getParameter(RESTurl.userId);
			bean.setUserId(Long.parseLong(userId));
		} catch (Exception ex) {
		}
		try {
			String entId = request.getParameter(RESTurl.enterpriseId);
			bean.setEntId(Long.parseLong(entId));
		} catch (Exception ex) {
		}
		try {
			String fi = request.getParameter(RESTurl.folderId);
			Long folderId = Long.parseLong(fi);
			if (folderId <= 0) {
				folderId = null;
			}
			bean.setFolderId(folderId);
		} catch (Exception ex) {
		}
		String fileNameASCII = request.getParameter(RESTurl.fileName);
		bean.setFileNameASCII(fileNameASCII);
		bean.setGuidName(request.getParameter(RESTurl.guidName));
		bean.setOldGuid(request.getParameter(RESTurl.guidName));
		try {
			String fileSize = request.getParameter(RESTurl.fileSize);
			bean.setFileSize(Long.parseLong(fileSize));
		} catch (Exception ex) {
		}
		bean.setType(request.getParameter(RESTurl.type));
		try {
			String receiverId = request.getParameter(RESTurl.receiverId);
			bean.setReceiverId(Long.parseLong(receiverId));
		} catch (Exception ex) {
		}

		try {
			String blockSize = request.getParameter(RESTurl.blockSize);
			bean.setBlockSize(Long.parseLong(blockSize));
		} catch (Exception ex) {
		}
		try {
			String blockCount = request.getParameter(RESTurl.blockCount);
			bean.setBlockCount(Integer.parseInt(blockCount));
		} catch (Exception ex) {
		}
		try {
			String blockIndex = request.getParameter(RESTurl.blockIndex);
			bean.setBlockIndex(Integer.parseInt(blockIndex));
		} catch (Exception ex) {
		}
		bean.setMd5(request.getParameter(RESTurl.guidName));

		bean.setMethod(request.getParameter(RESTurl.serviceMethod));
		return bean;
	}

	/**
	 * 获取下载名
	 * 
	 * @param request
	 * @param bean
	 * @return
	 */
	public static String getDownName(HttpServletRequest request,
			FileDownloadDTO bean) {
		String downName = CommonUtil.getFilePrefixName(bean.getFileName())
				+ "." + CommonUtil.getFileSuffixName(bean.getGuidName());
		if (CommConstants.FILE_TYPE_ZIP_DOWN.equals(bean.getType())) {
			downName = bean.getFileName().substring(
					bean.getFileName().lastIndexOf("/") + 1);
		} else {
			if (CommonUtil.isOatw(bean.getFileName())) {
				downName = CommonUtil.getFilePrefixName(bean.getFileName())
						+ "." + CommConstants.FILE_FORMAT_DOC;
			}

		}
		try {
			String agent = request.getHeader("User-Agent").toLowerCase();
			if (agent.indexOf("msie") > 0) {
				downName = URLEncoder.encode(downName,
						CommConstants.CHARSET_UTF_8);
			} else {
				downName = new String(
						downName.getBytes(CommConstants.CHARSET_UTF_8),
						"ISO8859-1");
			}
		} catch (Exception ex) {
		}

		downName = downName.replace(" ", "+");
		downName = downName.replace(",", "");
		downName = downName.replace("，", "");
		return downName;
	}

	/**
	 * get token
	 * 
	 * @param request
	 * @return
	 */
	public static String getToken(HttpServletRequest request) {
		String token = request.getHeader(RESTurl.UserTokenkey);
		if (token == null || "".equals(token) || "null".equals(token)) {
			token = request.getParameter(RESTurl.UserTokenkey);
			if (token == null || "".equals(token) || "null".equals(token)) {
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (Cookie c : cookies) {
						if (MyConst.COOKIE_USER_TOKEN.equals(c.getName())) {
							token = c.getValue();
							break;
						}
					}
				}
			}
		}
		return token;
	}
}
