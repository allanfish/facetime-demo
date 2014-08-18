package com.qycloud.oatos.filemanager.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.conlect.oatos.file.DiskUtil;

/**
 * 初始化配置
 * @author yang
 *
 */
public class ConfigListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 网盘根路径
		DiskUtil.ONLINEDISK_PATH = ConfigUtil.getValue(DiskUtil.NetworkDiskPath);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
