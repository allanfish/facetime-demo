package com.qycloud.oatos.server.util;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.qycloud.oatos.server.security.Security;

/**
 * 用户token验证
 * @author yang
 *
 */
public class UserTokenFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
	        ServletException {
		request.setCharacterEncoding(CommConstants.CHARSET_UTF_8);
		response.setContentType(MyConstants.ServiceContentType);

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String token = null;
		String ut = httpRequest.getHeader(RESTurl.UserTokenkey);
		if (ut != null) {
			token = URLDecoder.decode(ut, CommConstants.CHARSET_UTF_8);
		}

		// 检查结果，为 true 则放行请求，为 false 则阻止请求。
		boolean checkResults = false;

		String uri = httpRequest.getRequestURI();
		try {
			if (uri.contains("Test") || uri.contains(RESTurl.sendMessage)) {
				checkResults = true;
			}
			else if (token == null || token.length() == 0) {
				checkResults = false;
			}
			else {
				// 检查用户的 token 是否合法
				checkResults = Security.CheckToken(token);
			}
		}
		catch (Exception ex) {
			checkResults = false;
			BllLogger.getLogger().error(uri + ": " + token, ex);
		}

		if (checkResults) {
			chain.doFilter(request, response);
		}
		else {
			response.getWriter().write(ErrorType.errorCheckToken.name());
			BllLogger.getLogger().error("check token fail, " + uri + ":" + token);
		}
	}

	public void destroy() {
	}
}
