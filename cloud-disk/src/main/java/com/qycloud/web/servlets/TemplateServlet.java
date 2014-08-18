package com.qycloud.web.servlets;

import static com.conlect.oatos.utils.SysLogger.osLogger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.client.MessagesDTO;
import com.conlect.oatos.dto.client.SystemMsgDTO;
import com.conlect.oatos.dto.status.MessageStatus;
import com.conlect.oatos.dto.status.MessageType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.utils.FileUtils;
import com.conlect.oatos.utils.SysLogger;
import com.qycloud.web.clouddisk.FileService;
import com.qycloud.web.utils.ConfigUtil;

/**
 * 读取模版文件的Servlet服务
 * 
 * @author yufei
 * @created 2012-10-22
 * @design eclipse
 */
public class TemplateServlet extends HttpServlet {

	private static final String GET_FIRST_LOGIN_MSG = "getFirstLoginMsg";
	private static final String COPY_SYSTEM_FILES = "copySystemFiles";
	private static final long serialVersionUID = 1L;
	private static final MessagesDTO msgsDTO = new MessagesDTO();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try {
			String[] msgs = FileUtils.readLines(
					ConfigUtil.class.getClassLoader().getResourceAsStream(
							"templates/system_msg.txt"), "UTF-8");
			for (String msg : msgs) {
				try {
					SystemMsgDTO sysMsg = PojoMapper.fromJsonAsObject(msg,
							SystemMsgDTO.class);
					MessageDTO messageDTO = new MessageDTO();
					messageDTO.setSender(0L);
					messageDTO.setSendDate(new Date());
					messageDTO
							.setMessageType(MessageType.SystemVideoMsg
									.equals(sysMsg.getType()) ? MessageType.SystemVideoMsg
									: MessageType.SystemMsg);
					messageDTO.setMessageBody(sysMsg.getContent());
					messageDTO.setStatus(MessageStatus.Read);
					osLogger.info("sys msg:" + messageDTO);
					msgsDTO.getMessageDTOList().add(messageDTO);
				} catch (Exception e) {
					SysLogger.osLogger.error(e);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String urlDecode(String raw) {
		if (raw != null) {
			try {
				return URLDecoder.decode(raw, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				SysLogger.osLogger.error(e);
			}
		}
		return raw;
	}

	public static String urlEncode(Object raw) {
		if (raw != null) {
			try {
				return URLEncoder.encode(raw.toString(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				SysLogger.osLogger.error(e);
			}
		}
		return null;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter(RESTurl.action);
		String result = "";
		if (GET_FIRST_LOGIN_MSG.equals(action)) {
			result = getFirstLoginMsg();
		} else if (COPY_SYSTEM_FILES.equals(action)) {
			String userId = req.getParameter(RESTurl.userId);
			result = FileService.service("", RESTurl.copySystemFile, userId);
		}

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.write(result);
		writer.flush();
		writer.close();
	}

	private String getFirstLoginMsg() {
		return PojoMapper.toJson(msgsDTO);
	}

}
