package com.qycloud.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证码校验接口
 * 
 * @author PengfeiLiu
 * 
 */
public class WordVerificationServiceImpl extends HttpServlet {
	private static final long serialVersionUID = 5213666882498037892L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		String code = req.getParameter("code");
		HttpSession session = req.getSession(true);
		String savedCode = (String)session.getAttribute("validationCode");

		boolean result = savedCode != null ? savedCode.equalsIgnoreCase(code) : false;
		PrintWriter out = resp.getWriter();
		out.print(result);
	}
}
