package com.qycloud.oatos.convert.domain.logic;

import com.conlect.oatos.dto.client.doc.DocConvertDTO;
import com.qycloud.oatos.convert.util.ConfigUtil;

/**
 * Office文档转换线程池
 * @author yang
 *
 */
public class OfficeConvertPoolExecutor extends ConvertPoolExecutor {

	/**
	 * 最大同时执行任务数
	 */
	private static final int MAX_RUNNING_TASKS = ConfigUtil.getIntValue(ConfigUtil.MAX_OFFICE_TASK);

	private static OfficeConvertPoolExecutor instance = new OfficeConvertPoolExecutor();

	public static OfficeConvertPoolExecutor getInstance() {
		return instance;
	}

	private OfficeConvertPoolExecutor() {
		super(MAX_RUNNING_TASKS);
	}

	@Override
	protected ConvertTask initConvertTask(DocConvertDTO doc) {
		return new OfficeConvertTask(doc);
	}

}
