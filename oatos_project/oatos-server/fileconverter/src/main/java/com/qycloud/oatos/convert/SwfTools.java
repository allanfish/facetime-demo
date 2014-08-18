package com.qycloud.oatos.convert;

import java.io.File;

import org.apache.log4j.Logger;

import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.utils.SysRuntime;
import com.qycloud.oatos.convert.util.ConfigUtil;
import com.qycloud.oatos.convert.util.Logs;

/**
 * SWFTools工具类
 * @author yang
 *
 */
public class SwfTools {
	
	private final static Logger logger = Logs.getLogger();
	
	private static final String SwfToolsPath = "SwfToolsPath";

	/**
	 * pdf转成swf
	 * @param pdfPath
	 * @param swfPath
	 * @param startPage
	 * @param endPage
	 * @throws Exception
	 */
	public static String pdfToSwf(String pdfPath, String swfPath, int startPage, int endPage) {
		String result = CommConstants.OK_MARK;

		try {
			File pdf = new File(pdfPath);
			File swf = new File(swfPath);

			if (!pdf.exists()) {
				logger.error(pdfPath + " is not exist");
				result = ErrorType.errorFileNotFound.name();
			}

			if (swf.exists() && swf.lastModified() > pdf.lastModified()) {
				// swf已经存在且比pdf版本新
				result = CommConstants.OK_MARK;
			} else {
				StringBuilder command = new StringBuilder();
				command.append("pdf2swf");
				command.append(" -f");
				command.append(" -T ").append("9");
				command.append(" ");
				// pdf路径
				command.append(pdfPath.replace(" ", "\" \""));
				// 开始页码，结束页码
				command.append(" -p ").append(startPage).append("-").append(endPage);
				// swf路径
				command.append(" -o ").append(swfPath.replace(" ", "\" \""));

				logger.debug("start convert pdf [" + pdfPath + "] to swf [" + swfPath + "]" + ", page [" + startPage + " - "
				        + endPage + "]");
				long start = System.currentTimeMillis();
				// 开始转换
				executeSwfToolsCommand(command.toString());
				long cost = System.currentTimeMillis() - start;

				logger.debug("convert pdf [" + pdfPath + "] to swf [" + swfPath + "]" + ", page [" + startPage + " - "
				        + endPage + "]" + " finish, time: " + cost);
			}
		}
		catch (Exception ex) {
			result = ErrorType.error500.name();
			logger.error("convert pdf [" + pdfPath + "] to swf [" + swfPath + "]" + " error, page [" + startPage
			        + " - " + endPage + "]", ex);
		}
		return result;
	}

	/**
	 * 执行命令行
	 * 
	 * @param command
	 * @return
	 * @throws Exception
	 */
	private static int executeSwfToolsCommand(String command) throws Exception {
		logger.debug(command);
		String cmd = ConfigUtil.getValue(SwfToolsPath) + command;
		return SysRuntime.execute(cmd);
	}

}
