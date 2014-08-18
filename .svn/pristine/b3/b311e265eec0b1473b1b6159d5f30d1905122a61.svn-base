package com.qycloud.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conlect.oatos.dto.client.EnterpriseLoginDTO;
import com.conlect.oatos.dto.client.ReLoginDTO;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.utils.SysLogger;
import com.qycloud.web.activemq.AjaxWebClient;
import com.qycloud.web.activemq.AmqContainer;

/**
 * 
 * @author jinkerjiang
 * 
 */
public class FlexServlet extends HttpServlet {
	private static final long serialVersionUID = -1418258913166048204L;

	public static final String SERVICE_TYPE = "serviceType";

	public static final String SERVICE_POST = "servicePost";

	public static final String USER_ID = "userId";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");

		String result = "error";
		// server type
		String serviceType = req.getHeader(SERVICE_TYPE);
		// get token
		String token = req.getHeader(RESTurl.UserTokenkey);
		// get post data
		String postJson = ServletUtils.readContent(req, req.getContentType(), "utf-8");
		
		if (RESTurl.enterpriseUserLogin.equals(serviceType)) {
			SysLogger.getOsLogger().debug("User Login Record: " + postJson);
			EnterpriseLoginDTO loginDTO = PojoMapper.fromJsonAsObject(postJson, EnterpriseLoginDTO.class);
			loginDTO.setIp(req.getRemoteAddr());
			String postData = PojoMapper.toJson(loginDTO);
			result = ServiceUtil.invoke(token, serviceType, postData);

			AjaxWebClient client = AmqContainer.getAjaxWebClient(req);
			if (client != null) {
				synchronized (client) {
					client.setToken(result);
				}
			}
		} else if (RESTurl.reLogin.equals(serviceType)) {
			result = ServiceUtil.invoke(token, serviceType, postJson);

			ReLoginDTO reLoginDTO = PojoMapper.fromJsonAsObject(postJson, ReLoginDTO.class);
			if (reLoginDTO.getClientId() != null)
				req.setAttribute(MyConst.QUER_KEY_CLIENT_ID, reLoginDTO.getClientId());
			AjaxWebClient client = AmqContainer.getAjaxWebClient(req);
			if (client != null) {
				synchronized (client) {
					client.setToken(result);
				}
			}
		} else {
			result = ServiceUtil.invoke(token, serviceType, postJson);
		}

		PrintWriter out = resp.getWriter();
		out.print(result);
		out.close();
	}
}
