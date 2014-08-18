package com.qycloud.web.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.conlect.oatos.utils.SysLogger;
import com.qycloud.web.apns.ApnsNotifyListener;

public class SystemInitListener implements ServletContextListener {

	private static final String APS_PATH = "mobile/aps_developer_identity.p12";

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// SysMsgListener.get().destory();
		ClientCleanListener.get().destory();
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		// SysMsgListener.get().execute();
		ClientCleanListener.get().execute();
		String realPath = context.getServletContext().getRealPath(APS_PATH);
		SysLogger.getOsLogger().debug("realPath: " + realPath);
		ApnsNotifyListener.get().execute(realPath);
	}

}
