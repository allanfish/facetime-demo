package com.qycloud.web.activemq;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.jms.MessageConsumer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StatisticsServlet
 */
public class StatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StatisticsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		writer.println("<style>");
		writer.println("table td{border:1px solid gray;}");
		writer.println("</style>");

		writer.println("<table>");
		writer.println("<tbody>");

		writer.println("<tr>");
		writer.println("<td>clientId</td><td>token</td><td>token is the last effective</td><td>subscribe destination(s)</td><td>message consumer(s)</td>");
		writer.println("</tr>");

		Map<String, AjaxWebClient> clientsMap = AmqContainer.getAjaxWebClients();
		synchronized (clientsMap) {
			for (Entry<String, AjaxWebClient> entry : clientsMap.entrySet()) {
				writer.println("<tr>");

				// clientId
				writer.println("<td>");
				writer.print(entry.getKey());
				writer.println("</td>");

				AjaxWebClient client = entry.getValue();
				synchronized (client) {
					// token
					writer.println("<td>");
					writer.print(client.getToken());
					writer.println("</td>");

					// token is the last effective
					writer.println("<td>");
					writer.print(AjaxWebClient.checkToken(client.getToken()));
					writer.println("</td>");

					// destinations
					writer.println("<td>");
					for (String destination : client.getDestinationNameMap().values()) {
						writer.print(destination);
						writer.print("</br>");
					}
					writer.println("</td>");

					// consumers
					writer.println("<td>");
					for (MessageConsumer consumer : client.getConsumers()) {
						writer.print(consumer);
						writer.print("</br>");
					}
					writer.println("</td>");

					writer.println("</tr>");
				}
			}
		}

		writer.println("</tbody>");
		writer.println("</table>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
