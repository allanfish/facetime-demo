package com.qycloud.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.status.MessageType;
import com.conlect.oatos.dto.status.MyConst;

/**
 * @author StephenWang
 * 
 */
public class VerifierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String from = request.getParameter(MyConst.NOTICE_KEY);
		long userId = Long.parseLong(request.getParameter(MyConst.USER_ID));
		String verifier = request.getParameter(MyConst.VERIFIER_KEY);

		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setMessageBody(verifier + "&from=" + from);
		messageDTO.setSender(userId);
		messageDTO.setReceiver(userId);
		messageDTO.setMessageType(MessageType.BuddyImportFromWeb);

		MQMessageService.getInstance().sendMessage(messageDTO);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<script>");
		out.println("window.close();");
		out.println("</script>");
		out.println("</html>");

		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		this.doGet(request, response);
	}

}
