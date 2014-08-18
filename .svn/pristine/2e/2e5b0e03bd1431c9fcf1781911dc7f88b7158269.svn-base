package com.qycloud.oatos.convert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.ErrorType;
import com.qycloud.oatos.convert.domain.logic.OfficeConnectionPool;
import com.qycloud.oatos.convert.util.Logs;

/**
 * LibreOffice文档转换
 * 
 * @author yang
 * 
 */
public class OfficeConverter {

	private final static Logger logger = Logs.getLogger();

	/**
	 * 文档转成pdf
	 * @param sourcePath
	 * @param targetPath
	 * @throws Exception
	 */
	public static String docToPdf(String sourcePath, String targetPath) {
		String result = CommConstants.OK_MARK;
		if (CommonUtil.isPdf(sourcePath)) {
			result = CommConstants.OK_MARK;
		} else if (CommonUtil.isConvertToPdfSupported(sourcePath)) {
			File target = new File(targetPath);
			if (target.exists()) {
				// 目标文件已经存在
				result = CommConstants.OK_MARK;
			} else {
				// 检查文本编码
				checkTextCharset(sourcePath);
				// 开始转换
				result = convert(sourcePath, targetPath);
			}
		} else {
			result = ErrorType.errorNotSupported.name();
		}

		return result;
	}

	/**
	 * 文档转成html
	 * @param inputFile
	 * @param targetPath
	 * @throws Exception
	 */
	public static String docToHtml(String sourcePath, String targetPath) {
		String result = CommConstants.OK_MARK;
		String htmlPath = targetPath;
		if (CommonUtil.isHtml(sourcePath)) {
			htmlPath = sourcePath;
			result = CommConstants.OK_MARK;
		} else if (CommonUtil.isConvertToHtmlSupported(sourcePath)) {
			File target = new File(targetPath);
			if (target.exists()) {
				// 目标文件已经存在
				result = CommConstants.OK_MARK;
			} else {
				// 检查文本编码
				checkTextCharset(sourcePath);
				// 开始转换
				result = convert(sourcePath, targetPath);
			}
		} else {
			result = ErrorType.errorNotSupported.name();
		}
		if (CommConstants.OK_MARK.equals(result)) {
			// 重写html的内容
			buildHtml(htmlPath);
		}
		return result;
	}

	/**
	 * office文档相互转换
	 * 
	 * @param sourcePath
	 * @param targetPath
	 * @throws Exception
	 */
	public static String convert(String sourcePath, String targetPath) {
		String result = CommConstants.OK_MARK;
		SocketOpenOfficeConnection connection = null;
		try {
			File source = new File(sourcePath);
			File target = new File(targetPath);

			if (target.exists()) {
				// 目标文件已经存在
				result = CommConstants.OK_MARK;
			} else {
				logger.debug("start convert file [" + sourcePath + "] to [" + targetPath
						+ "]");
				long start = System.currentTimeMillis();

				// 获取一个连接
				connection = OfficeConnectionPool.getInstance().getConnection();
				
				MyDocumentFormatRegistry formatRegistry = new MyDocumentFormatRegistry();
				DocumentConverter converter = new OpenOfficeDocumentConverter(connection, formatRegistry);

				converter.convert(source, target);

				result = CommConstants.OK_MARK;

				long cost = System.currentTimeMillis() - start;
				logger.debug("convert file finish [" + sourcePath + "] to [" + targetPath
						+ "]" + ", time: " + cost);
			}
		} catch (Exception ex) {
			try {
				// 销毁连接
				OfficeConnectionPool.getInstance().destroyObject(connection);
			} catch (Exception exp) {
			}
			result = ErrorType.error500.name();
			Logs.getLogger().error(sourcePath + "==convert to==" + targetPath, ex);
		} finally {
			// 释放连接
			OfficeConnectionPool.getInstance().release(connection);
		}
		return result;
	}

	/**
	 * 重新构造html内容
	 * @param htmlPath
	 */
	private static void buildHtml(String htmlPath) {
		FileInputStream is = null;
		BufferedReader reader = null;
		BufferedWriter bw = null;
		try {
			// 获取文件编码
			String charset = new FileCharsetDetector().guestFileEncoding(htmlPath);

			// 读取html内容
			is = new FileInputStream(htmlPath);
			reader = new BufferedReader(new InputStreamReader(is, charset));
			StringBuilder buffer = new StringBuilder();
			char[] chars = new char[1024];
			while (reader.read(chars) != -1) {
				buffer.append(new String(chars));
				chars = new char[1024];
			}
			// 构造新的html内容
			String content = buildHtmlText(buffer.toString().trim());

			// 将新内容写入文件
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(htmlPath), CommConstants.CHARSET_UTF_8);
			bw = new BufferedWriter(osw);
			bw.write(content);
		} catch (Exception ex) {
			// 不做处理，忽视
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (reader != null) {
					reader.close();
				}
				if (bw != null) {
					bw.close();
				}
			} catch (Exception ex) {
			}
		}
	}
	
	/**
	 * 构造html头
	 * @param html
	 * @return
	 */
	private static String buildHtmlText(String html) {
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

	/**
	 * 检查文本编码
	 * @param filePath
	 * @throws Exception
	 */
	public static void checkTextCharset(String filePath) {
		FileInputStream is = null;
		BufferedReader reader = null;
		BufferedWriter bw = null;
		try {
			if (CommonUtil.isText(filePath)) {
				String charset = new FileCharsetDetector().guestFileEncoding(filePath);
				if (!CommConstants.CHARSET_UTF_8.equalsIgnoreCase(charset)) {
					is = new FileInputStream(filePath);
					reader = new BufferedReader(new InputStreamReader(is, charset));
					StringBuilder buffer = new StringBuilder();
					char[] chars = new char[1024];
					while (reader.read(chars) != -1) {
						buffer.append(new String(chars));
						chars = new char[1024];
					}
					
					OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath), CommConstants.CHARSET_UTF_8);
					bw = new BufferedWriter(osw);
					bw.write(buffer.toString().trim());
				}
			}
		} catch (Exception ex) {
			// 不做处理，忽视
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (reader != null) {
					reader.close();
				}
				if (bw != null) {
					bw.close();
				}
			} catch (Exception ex) {
			}
		}
	}

}
