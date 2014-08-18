package com.qycloud.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conlect.oatos.http.TextFileReader;

/**
 * Chat client window nocache.js servlet
 * 
 * @author jinkerjiang
 * 
 */
public class ChatClientWindowNocacheJsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, String> pathToJsMap = new HashMap<String, String>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/javascript");

		PrintWriter writer = response.getWriter();

		String servletPath = request.getServletPath();
		String filePath = getServletContext().getRealPath(servletPath);

		String content = pathToJsMap.get(filePath);

		if (content == null) {
			synchronized (pathToJsMap) {
				try {
					content = TextFileReader.renderQuickLoadNocacheJs(filePath);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
