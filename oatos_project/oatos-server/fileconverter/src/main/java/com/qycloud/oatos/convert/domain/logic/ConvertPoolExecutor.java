package com.qycloud.oatos.convert.domain.logic;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.conlect.oatos.dto.client.doc.DocConvertDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.qycloud.oatos.convert.util.FileUploadUtil;

/**
 * 文档转换线程池
 * @author yang
 *
 */
public abstract class ConvertPoolExecutor extends ThreadPoolExecutor {

	/**
	 * 状态
	 */
	private static Map<String, String> statusMap = new HashMap<String, String>();

	public ConvertPoolExecutor(int corePoolSize) {
		super(corePoolSize, corePoolSize, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<Runnable>());
	}

	/**
	 * 将转换任务加入队列
	 * @param doc
	 */
	public void convert(DocConvertDTO doc) {
		ConvertTask task = initConvertTask(doc);
		// 等待状态写入缓存
		statusMap.put(doc.getTargetPath(), CommConstants.QUEUED);
		// 加入转换队列
		execute(task);
	}

	/**
	 * 实例化任务
	 * @param doc
	 * @return
	 */
	protected abstract ConvertTask initConvertTask(DocConvertDTO docDTO);

	/**
	 * 任务执行完成后，移除缓存的状态
	 */
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		ConvertTask task = (ConvertTask) r;
		DocConvertDTO docDTO = task.getDocDTO();
		// 删除缓存状态
		statusMap.remove(docDTO.getTargetPath());

		if (docDTO.isSaveTarget()) {
			// 保存目标文件至文件服务器
			File targetFile = new File(docDTO.getDiskRootPath(), docDTO.getTargetPath());
			if (targetFile.exists()) {
				FileUploadUtil.upload(targetFile, docDTO.getTargetPath(), docDTO.getToken());
			}
		}
	}

	/**
	 * 获取缓存的任务状态
	 * @param doc
	 * @return
	 */
	public static String getStatus(DocConvertDTO doc) {
		return statusMap.get(doc.getTargetPath());
	}

}
