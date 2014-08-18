package com.qycloud.web.bean.support;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.utils.SysLogger;
import com.qycloud.web.apns.ApnsNotifyListener;
import com.qycloud.web.servlets.ClientCleanListener;
import com.qycloud.web.utils.ConfigUtil;

/**
 * Spring context初始化后进行系统初始化的监听者
 * 
 * @author Allan
 *
 */
@Component
public class SystemInitListener implements InitializingBean, ServletContextAware {
	private static final String APS_PATH = "mobile/aps_developer_identity.p12";
	private boolean init = false;
	private ServletContext context;

	@Override
	public void afterPropertiesSet() throws Exception {
		if (!init) {
			ClientCleanListener.get().execute();
			String realPath = context.getRealPath(APS_PATH);
			ApnsNotifyListener.get().execute(realPath);

			// 设置缓存路径
			SysLogger.getOsLogger().info("Spring Context started! Setting CacheDiskPath to ONLINEDISK_PATH");
			DiskUtil.ONLINEDISK_PATH = ConfigUtil.getValue(DiskUtil.NetworkDiskPath);
			this.init = true;
		}
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}

}
