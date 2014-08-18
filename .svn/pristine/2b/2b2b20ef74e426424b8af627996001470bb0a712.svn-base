package com.conlect.oatos.dto.status;

import java.util.ArrayList;
import java.util.List;

/**
 * util
 * 
 * @author yang
 * 
 */
public class CommonUtil {

	/**
	 * 字符串转换为ASCII码
	 * 
	 * @param orgString
	 * @return
	 */
	public static String string2ASCII(String orgString) {
		String outString = "";
		for (int i = 0; i < orgString.length(); i++) {// 输出结果
			outString = outString + orgString.codePointAt(i) + "-";
		}
		return outString.trim();
	}

	/**
	 * 字符串转换为ASCII码
	 * 
	 * @param orgString
	 * @return
	 */
	public static String[] string2ASCII(String[] orgStrings) {
		List<String> result = new ArrayList<String>(orgStrings.length);
		for (String orgString : orgStrings) {
			String outString = "";
			for (int i = 0; i < orgString.length(); i++) {// 输出结果
				outString = outString + orgString.codePointAt(i) + "-";
			}
			result.add(outString.trim());
		}
		return result.toArray(new String[0]);
	}

	/**
	 * ASCII转换为字符串
	 * 
	 * @param charList
	 * @return
	 */
	public static String ASCII2String(String charList) {
		// String s = "22307-35806-24555-20048";// ASCII码
		String outString = "";
		String[] chars = charList.split("-");

		for (int i = 0; i < chars.length; i++) {
			outString = outString + (char) Integer.parseInt(chars[i]);
		}

		return outString;
	}

	/**
	 * ASCII转换为字符串
	 * 
	 * @param charList
	 * @return
	 */
	public static String[] ASCII2String(String[] strList) {
		// String s = "22307-35806-24555-20048";// ASCII码
		List<String> result = new ArrayList<String>(strList.length);
		for (String charList : strList) {
			String outString = "";
			String[] chars = charList.split("-");
			for (int i = 0; i < chars.length; i++) {
				outString = outString + (char) Integer.parseInt(chars[i]);
			}
			result.add(outString);
		}
		return result.toArray(new String[0]);
	}

	/**
	 * get file suffix name (file type)
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffixName(String fileName) {
		String suffix = "";
		if (fileName != null) {
			int i = fileName.lastIndexOf("?");
			int j = fileName.lastIndexOf(".");
			String str = fileName;
			if (i > 0) {
				str = fileName.substring(0, i);
				j = str.lastIndexOf(".");

			}
			if (j > 0) {
				suffix = str.substring(j + 1);
			}
		}
		return suffix.trim();
	}

	/**
	 * get file prefix name
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFilePrefixName(String fileName) {
		String prefix = fileName;
		if (fileName != null) {
			int i = fileName.lastIndexOf("?");
			int j = fileName.lastIndexOf(".");
			String str = fileName;
			if (i > 0) {
				str = fileName.substring(0, i);
				j = str.lastIndexOf(".");

			}
			if (j > 0) {
				prefix = str.substring(0, j);
			}
		}
		return prefix;
	}

	/**
	 * is image
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isImage(String fileName) {
		String suffix = CommonUtil.getFileSuffixName(fileName);
		for (String type : CommConstants.IMAGE_FORMATS) {
			if (type.equalsIgnoreCase(suffix)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * is pdf
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isPdf(String fileName) {
		String suffix = CommonUtil.getFileSuffixName(fileName);
		if (CommConstants.FILE_FORMAT_PDF.equalsIgnoreCase(suffix)) {
			return true;
		}
		return false;
	}

	/**
	 * is html
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isHtml(String fileName) {
		String suffix = CommonUtil.getFileSuffixName(fileName);
		if (CommConstants.FILE_FORMAT_HTML.equalsIgnoreCase(suffix)) {
			return true;
		}
		return false;
	}

	/**
	 * is oatwri
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isOatw(String fileName) {
		String suffix = CommonUtil.getFileSuffixName(fileName);
		if (CommConstants.FILE_FORMAT_OATWRI.equalsIgnoreCase(suffix)) {
			return true;
		}
		return false;
	}

	/**
	 * is swf
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isSwf(String fileName) {
		String suffix = CommonUtil.getFileSuffixName(fileName);
		if (CommConstants.FILE_FORMAT_SWF.equalsIgnoreCase(suffix)) {
			return true;
		}
		return false;
	}

	/**
	 * is docx
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isDocx(String fileName) {
		String suffix = CommonUtil.getFileSuffixName(fileName);
		if (CommConstants.FILE_FORMAT_DOCX.equalsIgnoreCase(suffix)) {
			return true;
		}
		return false;
	}

	/**
	 * is xls
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isXls(String fileName) {
		String suffix = CommonUtil.getFileSuffixName(fileName);
		if (CommConstants.FILE_FORMAT_XLS.equalsIgnoreCase(suffix)) {
			return true;
		}
		return false;
	}

	/**
	 * is xlsx
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isXlsx(String fileName) {
		String suffix = CommonUtil.getFileSuffixName(fileName);
		if (CommConstants.FILE_FORMAT_XLSX.equalsIgnoreCase(suffix)) {
			return true;
		}
		return false;
	}

	/**
	 * is text file
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isText(String fileName) {
		String suffix = CommonUtil.getFileSuffixName(fileName);
		for (String format : CommConstants.VIEW_AS_TEXT_SUPPORTED_FORMAT) {
			if (format.equalsIgnoreCase(suffix)) {
				return true;
			}
		}
		if ("txt".equalsIgnoreCase(suffix) || "log".equalsIgnoreCase(suffix)
				|| "xml".equalsIgnoreCase(suffix)
				|| "sql".equalsIgnoreCase(suffix)
				|| "csv".equalsIgnoreCase(suffix)) {
			return true;
		}
		return false;
	}

	/**
	 * is convert to pdf supported
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isConvertToPdfSupported(String fileName) {
		String suffix = CommonUtil.getFileSuffixName(fileName);
		for (String format : CommConstants.CONVERT_TO_PDF_SUPPORTED_FORMAT) {
			if (format.equalsIgnoreCase(suffix)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * is view as swf supported
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isViewAsSwfSupported(String fileName) {
		if (isPdf(fileName) || isConvertToPdfSupported(fileName)) {
			return true;
		}
		return false;
	}

	/**
	 * is convert to html supported
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isConvertToHtmlSupported(String fileName) {
		String suffix = CommonUtil.getFileSuffixName(fileName);
		for (String format : CommConstants.CONVERT_TO_HTML_SUPPORTED_FORMAT) {
			if (format.equalsIgnoreCase(suffix)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * is view as html supported
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isViewAsHtmlSupported(String fileName) {
		String suffix = CommonUtil.getFileSuffixName(fileName);
		for (String format : CommConstants.VIEW_AS_HTML_SUPPORTED_FORMAT) {
			if (format.equalsIgnoreCase(suffix)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * is view as text supported
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isViewAsTEXTSupported(String fileName) {
		String suffix = CommonUtil.getFileSuffixName(fileName);
		for (String format : CommConstants.VIEW_AS_TEXT_SUPPORTED_FORMAT) {
			if (format.equalsIgnoreCase(suffix)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * is edit as html supported
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isEditAsHtmlSupported(String fileName) {
		if (isHtml(fileName)) {
			return true;
		} else {
			String suffix = CommonUtil.getFileSuffixName(fileName);
			for (String format : CommConstants.EDIT_AS_HTML_SUPPORTED_FORMAT) {
				if (format.equalsIgnoreCase(suffix)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * is view supported
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isViewSupported(String fileName) {
		if (isImage(fileName) || isViewAsSwfSupported(fileName)
				|| isViewAsHtmlSupported(fileName)
				|| isViewAsTEXTSupported(fileName)
				|| isVideoSupported(fileName)) {
			return true;
		}
		return false;
	}

	public static boolean isVideoSupported(String fileName) {
		String suffix = CommonUtil.getFileSuffixName(fileName);
		for (String format : CommConstants.JWPLAYER_SUPPORTED_FORMAT) {
			if (format.equalsIgnoreCase(suffix)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * is save message
	 * 
	 * @param messateType
	 * @return
	 */
	public static boolean isSaveMessage(String type) {
		boolean save = false;
		if (MessageType.ChatMessage.equals(type)
				|| MessageType.VideoInvite.equals(type)
				|| MessageType.AudioInvite.equals(type)
				|| MessageType.VoiceMail.equals(type)
				|| MessageType.InstantFile.equals(type)
				|| MessageType.ShareFileInPrivacy.equals(type)
				|| MessageType.OfflineFile.equals(type)
				|| CommonUtil.isSystemMsg(type)) {
			save = true;
		}
		return save;
	}

	public static String getHtmlBody(String html) {
		String t = html;
		String body = t;
		if (t.contains("<body")) {
			t = t.substring(t.indexOf("<body"));
			t = t.substring(t.indexOf(">") + 1);
			body = t.substring(0, t.indexOf("</body>"));
		} else if (t.contains("<BODY")) {
			t = t.substring(t.indexOf("<BODY"));
			t = t.substring(t.indexOf(">") + 1);
			body = t.substring(0, t.indexOf("</BODY>"));
		}
		return body.trim();
	}

	public static String buildHtml(String html) {
		String body = CommonUtil.getHtmlBody(html);
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html><html><head><meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
		sb.append("<style type='text/css'>body {");
		sb.append("-webkit-user-select: none;");
		sb.append("-khtml-user-select: none;");
		sb.append("-moz-user-select: none;");
		sb.append("-ms-user-select: none;");
		sb.append("-o-user-select: none;");
		sb.append("user-select: none;");
		sb.append("}</style>");
		sb.append("</head><body onselectstart='return false'>");
		sb.append(body);
		sb.append("</body></html>");
		return sb.toString();
	}

	public static final String[] sysMsgs = new String[] {
			MessageType.SystemMsg, MessageType.SystemVideoMsg };

	public static boolean isSystemMsg(String src) {
		return equalsAnyOf(sysMsgs, src);
	}

	public static boolean equalsAnyOf(String[] array, String src) {
		for (String str : array) {
			if (src.equals(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param size
	 *            byte
	 * @return size k byte
	 */
	public static long getFileSizeKb(long size) {
		long s = size / 1024;
		if (s < 1) {
			s = 1;
		}
		return s;
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getHTMLEditNewName(String fileName) {
		String p = fileName;
		if (CommonUtil.isDocx(fileName)) {
			p = CommonUtil.getFilePrefixName(fileName) + "."
					+ CommConstants.FILE_FORMAT_DOC;
		} else if (CommonUtil.isXlsx(fileName)) {
			p = CommonUtil.getFilePrefixName(fileName) + "."
					+ CommConstants.FILE_FORMAT_XLS;
		}
		return p;
	}
}
