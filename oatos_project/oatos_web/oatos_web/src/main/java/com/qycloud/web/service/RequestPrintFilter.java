package com.qycloud.web.service;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestPrintFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		int logIndex = uri.indexOf("/pages/logmgr/writeOperLogMgr");
		if (logIndex != -1)
			chain.doFilter(req, resp);
		else {
			String httpInfo = getJSONString(req);
			System.out.println("REQUEST HTTP HEAD:" + httpInfo);
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	private String getJSONString(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Map<String, String[]> paramMap = request.getParameterMap();
		StringBuilder param = new StringBuilder();
		param.append("\r\n{\r\n");
		param.append("  URI: ").append(request.getRequestURI()).append(",\n");
		for (Entry<String, String[]> entry : paramMap.entrySet()) {
			param.append("  ").append(entry.getKey()).append(": ");
			String[] values = entry.getValue();
			if (values != null && values.length > 1) {
				param.append("[");
				for (String value : values)
					param.append(value).append(",");
				param.delete(param.length() - 1, param.length());
				param.append("]").append(",\n");
			} else
				param.append(values == null ? null : values[0]).append(",\n");
		}
		if (param.lastIndexOf(",") != -1)
			param.replace(param.lastIndexOf(","), param.lastIndexOf(",") + 1, "");
		param.append("}\r\n");
		return param.toString();
	}

}
