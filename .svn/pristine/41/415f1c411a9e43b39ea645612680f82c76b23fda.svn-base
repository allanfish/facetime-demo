package com.qycloud.oatos.filecache.logic;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.conlect.oatos.dto.client.ConferenceDocDTO;
import com.conlect.oatos.dto.client.FileDTO;
import com.conlect.oatos.dto.client.doc.DocConvertDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.DocConvertType;
import com.conlect.oatos.file.ServerInnerUrl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.conlect.oatos.utils.SysRuntime;
import com.qycloud.oatos.filecache.util.ConfigUtil;
import com.qycloud.oatos.filecache.util.Logs;

@Service("FileConverterMonitorLogic")
public class FileConverterMonitorLogic {

	private static final Logger logger = Logs.getLogger();
	
	private static final String FileConverterHome = "FileConverterHome";
	
	private static final long delay = 10;
	private static final long period = 60;

	private static final int restartCount = 10;
	
	private AtomicInteger atomicInteger;

	@PostConstruct
	public void init() {
		// 初始计数器
		atomicInteger = new AtomicInteger(0);
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

		// 开始文件转换服务监控
		MonitorTask task = new MonitorTask();
		 executor.scheduleAtFixedRate(task, delay, period, TimeUnit.SECONDS);
		logger.debug("start monitor task");

		// 启动文件转换服务
		executor.schedule(new Runnable() {
			
			@Override
			public void run() {
				restartConverterService();
			}
		}, delay, TimeUnit.SECONDS);
	}

	/**
	 * 重置文件转换服务监控
	 * @param status
	 */
	public void monitor(String status) {
		// 重置计数器
		atomicInteger = new AtomicInteger(0);
	}
	
	/**
	 * 重启文件转换服务
	 */
	private void restartConverterService() {
		try {
			logger.debug("restart converter service");
			String cmd = ConfigUtil.getValue(FileConverterHome) + "restart.sh";
			SysRuntime.execute(cmd);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// 重置计数器
		monitor(CommConstants.OK_MARK);
	}
	
	public void afterFileConvert(DocConvertDTO docDTO) {
		// 更新文件总页数到数据库
		if (DocConvertType.DOC_TO_SWF.equals(docDTO.getConvertType())
				&& docDTO.getStartPage() <= 1) {
			updatePageCount(docDTO);
		}

		if (docDTO.isSaveTarget()) {
			// 保存目标文件至文件服务器
			File targetFile = new File(docDTO.getDiskRootPath(), docDTO.getTargetPath());
			if (targetFile.exists()) {
				FileUploadUtil.upload(docDTO.getTargetPath(), docDTO.getFileId(), docDTO.getFileType(), docDTO.getToken());
			}
		}
	}
	
	/**
	 * 更新文件总页数到数据库
	 * @param doc
	 */
	private void updatePageCount(DocConvertDTO doc) {
		if (doc.getFileId() != null) {
			if (CommConstants.FILE_TYPE_SHAREDISK.equals(doc.getFileType())
					|| CommConstants.FILE_TYPE_ONLINEDISK.equals(doc.getFileType())) {
				// 企业网盘或个人网盘文件
				FileDTO fileDTO = new FileDTO();
				fileDTO.setFileId(doc.getFileId());
				fileDTO.setPageCount(doc.getPageCount());
				fileDTO.setType(doc.getFileType());
				String postData = PojoMapper.toJson(fileDTO);
				XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(ServerInnerUrl.updateFilePageCount), "", postData);
			} else if (CommConstants.FILE_TYPE_CONFERENCE_DOC.equals(doc.getFileType())) {
				// 视频会议文档
				ConferenceDocDTO docDTO = new ConferenceDocDTO();
				docDTO.setFileId(doc.getFileId());
				docDTO.setPageCount(doc.getPageCount());
				String postData = PojoMapper.toJson(docDTO);
				XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(ServerInnerUrl.updateConferenceDocPageCount), "", postData);
			}
		}
	}

	private class MonitorTask implements Runnable {

		@Override
		public void run() {
			try {
				logger.debug("execute monitor task:" + atomicInteger.get());
				
				if (atomicInteger.get() > restartCount) {
					// 重启文件转换服务
					restartConverterService();
				} else {
					// 计数器加1
					atomicInteger.incrementAndGet();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
	}
}
