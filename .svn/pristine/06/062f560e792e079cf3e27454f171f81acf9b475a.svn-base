package com.conlect.oatos.http;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Transform html file like "ChatClientWindow/*.cache.html". to js
 * 
 * @author jinkerjiang
 * 
 */
public class HTMLAsJSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, String> pathToJsMap = new HashMap<String, String>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/javascript");

		PrintWriter writer = response.getWriter();

		String servletPath = request.getServletPath();

		servletPath = servletPath.substring(servletPath
				.indexOf(TextFileSource.HTML_AS_JS_SERVLET_PREFIX)
				+ TextFileSource.HTML_AS_JS_SERVLET_PREFIX.length());

		String filePath = getServletContext().getRealPath(servletPath)
				+ request.getPathInfo();

		String content = pathToJsMap.get(filePath);

		if (content == null) {
			synchronized (pathToJsMap) {
				try {
					content = TextFileReader.buildJSString(filePath);
					pathToJsMap.put(filePath, content);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		writer.write(content);
		writer.flush();
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
