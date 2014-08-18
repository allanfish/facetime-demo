package com.qycloud.oatos.filecache.jms.receiver;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.utils.SysLogger;
import com.qycloud.oatos.filecache.jms.support.FileUploadWorker;
import com.qycloud.oatos.filecache.util.ConfigUtil;

public class FileUploadJmsReceiver implements MessageListener {

	private static final String workerSize = ConfigUtil.getValue(ConfigUtil.FILE_UPLOAD_WORKER_MAX_SIZE);
	private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Integer
			.parseInt(workerSize));

	@Override
	public void onMessage(Message message) {
		try {
			// 等待线程池中有空闲线程
			while (true) {
				if (executor.getActiveCount() < 8) {
					break;
				} else {
					Thread.sleep(200);
				}
			}
			// 获取消息内容
			TextMessage txtMessage = (TextMessage) message;
			String uploadBeanJson = txtMessage.getText();

			FileUploadWorker worker = new FileUploadWorker(PojoMapper.fromJsonAsObject(uploadBeanJson,
					FileUploadDTO.class));
			executor.execute(worker);
		} catch (Exception e) {
			SysLogger.getServerLogger().error("parse fileUploadQueue message error!", e);
		}
	}
}
