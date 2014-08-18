package com.conlect.oatos.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Manage html file like "*.cache.html".
 * 
 * @author jinkerjiang
 * 
 */
public class HTMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private long lastModified = 1000 * (System.currentTimeMillis() / 1000);

	private TextFileSource textFileSource = TextFileSource.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setDateHeader("Last-Modified", lastModified);

		PrintWriter writer = response.getWriter();

		String servletPath = request.getServletPath();
		// remove prefix to get real resource file path
		servletPath = servletPath.substring(servletPath
				.indexOf(TextFileSource.HTML_SERVLET_PREFIX)
				+ TextFileSource.HTML_SERVLET_PREFIX.length());

		String filePath = getServletContext().getRealPath(servletPath)
				+ request.getPathInfo();

		String[] frags = textFileSource.get(filePath);

		if (frags == null) {
			synchronized (textFileSource) {
				try {
					frags = TextFileReader.renderWithProgressInvoker(new File(
							filePath));
					textFileSource.put(filePath, frags);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// set response's buffer size
		int fragMaxSize = textFileSource.getFragMaxSize(filePath);
		fragMaxSize = (fragMaxSize == -1) ? 10 * 1024 : fragMaxSize;
		response.setBufferSize(fragMaxSize);

		if (frags != null) {
			// flush each fragment to client as soon as possible
			for (String frag : frags) {
				writer.write(frag);
				writer.flush();
				response.flushBuffer();
			}
		}

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
