package com.qycloud.oatos.filecache.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.utils.SysLogger;

/**
 * 初始化配置
 * @author yang
 */
@Component
public class ConfigListener implements InitializingBean {

	private boolean init = false;

	@Override
	public void afterPropertiesSet() throws Exception {
		if (!init) {
			SysLogger.getServerLogger().info(
					"FileCache Spring context initial. Now settting ONLINEDISK_PATH to: "
							+ ConfigUtil.getValue(DiskUtil.NetworkDiskPath));
			DiskUtil.ONLINEDISK_PATH = ConfigUtil.getValue(DiskUtil.NetworkDiskPath);
			this.init = true;
		}
	}

}
