package com.qycloud.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.web.utils.Logs;

public class SendMessageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		response.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		response.setContentType("text/plain; charset=UTF-8");

		String result = CommConstants.OK_MARK;
		try {
			// token
			String token = request.getHeader(RESTurl.UserTokenkey);

			// post data
			InputStream is = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			String postData = sb.toString();
			
			MessageDTO messageDTO = PojoMapper.fromJsonAsObject(postData, MessageDTO.class);
			result = MQMessageService.getInstance().sendMessage(token, messageDTO);
		} catch (Exception ex) {
			result = ErrorType.error500.name();
			Logs.getLogger().error("send message", ex);
		}
		response.getWriter().write(result);
	}

}
