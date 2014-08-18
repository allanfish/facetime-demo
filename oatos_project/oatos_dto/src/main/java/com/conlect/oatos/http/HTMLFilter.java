package com.conlect.oatos.http;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Filter source text file like "*.cache.html".
 * 
 * @author jinkerjiang
 * 
 */
public class HTMLFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public HTMLFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/**
	 * Filter request, if request uri ends with '.cache.html', forward request
	 * to {@link HTMLServlet}
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String uri = ((HttpServletRequest) request).getRequestURI();

		boolean isCacheHtmlFile = uri
				.endsWith(TextFileSource.TARGET_TEXT_File_SUFFIX);

		if (!isCacheHtmlFile) {
			// goes to default servlet.
			chain.doFilter(request, response);
		} else {
			if (uri.indexOf("/ChatClientWindow") != -1) {
				/**
				 * forward to {@link HTMLAsJSServlet}
				 */
				request.getRequestDispatcher(
						"/"
								+ TextFileSource.HTML_AS_JS_SERVLET_PREFIX
								+ "/"
								+ uri.replaceFirst(
										((HttpServletRequest) request)
												.getContextPath(), ""))
						.forward(request, response);
			} else {
				/**
				 * forward to {@link HTMLServlet}
				 */
				request.getRequestDispatcher(
						"/"
								+ TextFileSource.HTML_SERVLET_PREFIX
								+ "/"
								+ uri.replaceFirst(
										((HttpServletRequest) request)
												.getContextPath(), ""))
						.forward(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
