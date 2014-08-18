package com.qycloud.web.activemq;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.conlect.oatos.dto.status.MyConst;

/**
 * 
 * @author jinkerjiang
 * 
 */
public class AmqContainer {
	private static HashMap<String, AjaxWebClient> webClientsMap = new HashMap<String, AjaxWebClient>();

	public static AjaxWebClient getAjaxWebClient(HttpServletRequest request) {
		HttpSession session = request.getSession(true);

		String clientId = getClientId(request);
		if (clientId == null) {
			return null;
		}

		String sessionKey = session.getId() + '-' + clientId;

		AjaxWebClient client = webClientsMap.get(sessionKey);
		// create a new AjaxWebClient if one does not already exist for this
		// sessionKey.
		if (client == null) {
			client = new AjaxWebClient(request, MessageListenerServlet.maximumReadTimeout);
			synchronized (webClientsMap) {
				webClientsMap.put(sessionKey, client);
			}
		}
		client.updateLastAccessed();
		return client;
	}

	private static String getClientId(HttpServletRequest request) {
		String clientId = request.getParameter(MyConst.QUER_KEY_CLIENT_ID);

		if (clientId == null) {
			clientId = request.getHeader(MyConst.QUER_KEY_CLIENT_ID);
		}

		if (clientId == null) {
			Object object = request.getAttribute(MyConst.QUER_KEY_CLIENT_ID);
			if (object != null) {
				clientId = (String) object;
			}
		}
		return clientId;
	}

	public static HashMap<String, AjaxWebClient> getAjaxWebClients() {
		return webClientsMap;
	}

}
