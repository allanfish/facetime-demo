package com.qycloud.oatos.filecache.domain.logic;

import java.io.File;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.filecache.util.Logs;

/**
 * 文件分段上传
 * @author yang
 *
 */
@Service
public class SectionFileUploadLogic {

	/**
	 * 文件分段上传
	 * @param request
	 * @param postData
	 * @return
	 * @throws Exception
	 */
	public String sectionFileUpload(HttpServletRequest request, String postData, String token) throws Exception {
		FileUploadDTO bean = PojoMapper.fromJsonAsObject(postData, FileUploadDTO.class);
		String fileName = CommonUtil.ASCII2String(bean.getFileNameASCII());
		bean.setFileName(fileName);
		// 生成guid
		bean.setGuidName(FileUtil.createFileGuidName(bean.getFileName()));

		String result = "";
		if (RESTurl.getStartPoint.equals(bean.getMethod())) {
			result = getStart(bean, token);
		} else if (RESTurl.isUploadOver.equals(bean.getMethod())) {
			// TODO not supported
			result = CommConstants.UPLOAD_SUCCESS;
		} else {
			result = sectionUpload(bean, request.getInputStream(), token);
		}
		return result;
	}
	
	/**
	 * 检查网盘空间，权限，同名文件，取上传开始位置
	 * @param bean
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	private String getStart(FileUploadDTO bean, String token) throws Exception {
		// 检查网盘空间，权限，同名文件
		String result = FileUploadUtil.checkFileUpload(bean, token);
		if (CommConstants.OK_MARK.equals(result)) {
			String sdir = DiskUtil.getShareDiskDir(bean.getEntId());
			if (CommConstants.FILE_TYPE_ONLINEDISK.equals(bean.getType())) {
				sdir = DiskUtil.getPersonalDiskDir(bean.getUserId());
			}
			// 根据MD5码搜索现有文件，有同样文件，则快速复制
			File f = FileUtil.searchFile(CommonUtil.getFileSuffixName(bean.getFileName()), bean.getFileSize(), bean.getMd5(), new File(DiskUtil.ONLINEDISK_PATH, sdir));
			
			if (f != null) {
				// 有同样文件，则快速复制
				File targetFile = DiskUtil.getTargetFile(bean);
				FileUtil.copyFile(f, targetFile);
				result = FileUploadUtil.handleUpload(targetFile, bean, token);
				if (CommConstants.OK_MARK.equals(result)) {
					result = CommConstants.UPLOAD_SUCCESS;
				}
			} else {
				File tempdir = DiskUtil.getSectionTempDir(bean);
				// 获取已经上传完成的块数
				int receiveCount = DiskUtil.getUploadedBlockCount(tempdir, bean);
				if (receiveCount == bean.getBlockCount()) {
					// 所有块已上传完，合并文件，上传完成
					MergeFileThread mergeFileThread = new MergeFileThread(bean, token);
					mergeFileThread.start();
					result = CommConstants.UPLOAD_SUCCESS;
				} else {
					// 返回已成功上传的块数
					result = String.valueOf(receiveCount);
				}
			}
		}
		return result;
	}
	
	/**
	 * 上传分块
	 * @param bean
	 * @param in
	 * @param token
	 * @return
	 * @throws Exception
	 */
	private String sectionUpload(FileUploadDTO bean, InputStream in, String token) throws Exception {
		String result = "";
		File tempdir = DiskUtil.getSectionTempDir(bean);
		String bname = String.valueOf(bean.getBlockIndex());
		File file = new File(tempdir, bname);
		// 保存分块
		long size = FileUtil.copyInputStreamToFile(in, file);
		if (size == bean.getBlockSize()) {
			// 分块上传成功
			result = CommConstants.OK_MARK;
			if (bean.getBlockIndex().equals(bean.getBlockCount())) {
				// 所有分块都上传完成，合并分块
				MergeFileThread mergeFileThread = new MergeFileThread(bean, token);
				mergeFileThread.start();
				result = CommConstants.UPLOAD_SUCCESS;
			}
		} else {
			//  分块上传失败
			FileUtil.delete(file, 0);
			result = CommConstants.ERROR_MARK;
		}
		return result;
	}

	/**
	 * 合并分块线程
	 * @author yang
	 *
	 */
	private class MergeFileThread extends Thread {
		
		private FileUploadDTO bean;

		private String token;

		public MergeFileThread(FileUploadDTO bean, String token) {
			this.bean = bean;
			this.token = token;
		}

		@Override
		public void run() {
			try {
				File tempdir = DiskUtil.getSectionTempDir(bean);
				File targetFile = DiskUtil.getTargetFile(bean);
				// 合并分块
				DiskUtil.mergeFile(tempdir, targetFile);
				// 插入数据库记录
				String result = FileUploadUtil.handleUpload(targetFile, bean, token);
				if (!CommConstants.OK_MARK.equals(result)) {
					Logs.getLogger().error("upload file error:" + bean.toString());
				}
			} catch (Exception ex) {
				Logs.getLogger().error(bean.toString(), ex);
			}
		}
	}
}
