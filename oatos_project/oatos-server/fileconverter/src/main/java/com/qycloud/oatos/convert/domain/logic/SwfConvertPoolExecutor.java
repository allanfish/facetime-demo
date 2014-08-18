package com.qycloud.oatos.convert.domain.logic;

import com.conlect.oatos.dto.client.doc.DocConvertDTO;
import com.qycloud.oatos.convert.util.ConfigUtil;

/**
 * swf转换线程池
 * @author yang
 *
 */
public class SwfConvertPoolExecutor extends ConvertPoolExecutor {

	/**
	 * 最大同时执行任务数
	 */
	private static final int MAX_RUNNING_TASKS = ConfigUtil.getIntValue(ConfigUtil.MAX_SWF_TASK);

	private static SwfConvertPoolExecutor instance = new SwfConvertPoolExecutor();

	public static SwfConvertPoolExecutor getInstance() {
		return instance;
	}

	private SwfConvertPoolExecutor() {
		super(MAX_RUNNING_TASKS);
	}

	@Override
	protected ConvertTask initConvertTask(DocConvertDTO doc) {
		return new SwfConvertTask(doc);
	}

}
