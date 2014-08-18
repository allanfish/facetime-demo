package com.qycloud.oatos.filecache.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.entity.InputStreamEntity;
import org.springframework.stereotype.Service;

import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.ShareFileUpdateDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.file.ImageUtil;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.oatos.filecache.util.ConfigUtil;
import com.qycloud.oatos.filecache.util.Logs;

/**
 * 网盘文件同步上传
 * 
 * @author yang
 * 
 */
@Service
public class SyncFileUploadLogic {

	/**
	 * 远程服务请求代理
	 */
	private static XhrProxy proxy = XhrProxy.getInstance();

	/**
	 * 网盘文件同步上传
	 * @param request
	 * @param postData
	 * @return
	 * @throws Exception
	 */
	public String syncFileUpload(HttpServletRequest request, String postData, String token) throws Exception {
		FileUploadDTO bean = PojoMapper.fromJsonAsObject(postData, FileUploadDTO.class);
		String fileName = CommonUtil.ASCII2String(bean.getFileNameASCII());
		bean.setFileName(fileName);
		// 重新生成guid
		bean.setGuidName(FileUtil.createFileGuidName(bean.getFileName()));

		String result = "";
		if (RESTurl.getStartPoint.equals(bean.getMethod())) {
			result = getStart(bean, token);
		} else {
			result = sectionUpload(bean, request.getInputStream(), token);
		}
		return result;
	}

	/**
	 * 检查版本，取上传开始位置
	 * @param bean
	 * @param token
	 * @return
	 * @throws Exception
	 */
	private String getStart(FileUploadDTO bean, String token) throws Exception {
		String result = "0";
		// 检查版本
		String checkVersion = checkFileSync(bean, token);
		if (!CommConstants.OK_MARK.equals(checkVersion)) {
			result = checkVersion;
		} else {
			File tempdir = DiskUtil.getSectionTempDir(bean);
			// 获取已经上传完成的块数
			int receiveCount = DiskUtil.getUploadedBlockCount(tempdir, bean);
			if (bean.getBlockCount().equals(receiveCount)) {
				// 所有块已上传完，合并文件，上传完成
				File targetFile = DiskUtil.getTargetFile(bean);
				DiskUtil.mergeFile(tempdir, targetFile);
				result = syncFile(bean, targetFile, token);
			} else {
				// 返回已成功上传的块数
				result = String.valueOf(receiveCount);
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
			if (bean.getBlockCount().equals(bean.getBlockIndex())) {
				// 所有分块都上传完成，合并分块
				File targetFile = DiskUtil.getTargetFile(bean);
				DiskUtil.mergeFile(tempdir, targetFile);
				result = syncFile(bean, targetFile, token);
			}
		} else {
			//  分块上传失败
			FileUtil.delete(file, 0);
			result = CommConstants.ERROR_MARK;
		}
		return result;
	}

	/**
	 * 更新数据库中文件记录
	 * @param bean
	 * @param targetFile
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	private String syncFile(FileUploadDTO bean, File targetFile, String token) throws Exception {
		DiskUtil.getTargetFilePath(bean);
		
		StringBuilder postURL = new StringBuilder(ConfigUtil.getValue(MyConst.FileManagerService));
		postURL.append(RESTurl.syncFileUpload);

		Map<String, String> headers = new HashMap<String, String>();
		headers.put(RESTurl.UserTokenkey, token);
		// 防止中文名乱码
		bean.setFileName(null);
		headers.put(RESTurl.postJsonData, PojoMapper.toJson(bean));

		HttpEntity postEntity = new InputStreamEntity(new FileInputStream(targetFile), -1);
		// 保存文件至文件服务器
		String result = XhrProxy.getInstance().post(postURL.toString(), postEntity, headers);
		String re = result;
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(bean.getType())) {
			re = syncShareFile(bean, targetFile, result);
		} else if (CommConstants.FILE_TYPE_ONLINEDISK.equals(bean.getType())) {
			re = syncPersonalFile(bean, targetFile, result);
		}
		return re;
	}
	
	/**
	 * 个人网盘文件同步
	 * @param bean
	 * @param targetFile
	 * @param result
	 */
	private String syncPersonalFile(FileUploadDTO bean, File targetFile, String result) {
		String re = result;
		if (result != null && !result.startsWith(CommConstants.ERROR_MARK)) {
			try {
				NetworkFileDTO resultDto = PojoMapper.fromJsonAsObject(result, NetworkFileDTO.class);
				long fileId = resultDto.getFileId();
				// 移动原文件至历史目录
				if (bean.getFileId() != null) {
					bean.setGuidName(bean.getOldGuid());
					File oldFile = DiskUtil.getTargetFile(bean);
					if (oldFile.exists()) {
						// 移动原文件至历史目录
						String historyFilePath = DiskUtil.getHistoryFilePath(bean.getUserId(), fileId, bean.getOldGuid(), CommConstants.FILE_TYPE_ONLINEDISK);
						File historyFile = new File(DiskUtil.ONLINEDISK_PATH, historyFilePath);
						oldFile.renameTo(historyFile);
					}
				}
				// 创建文件缩略图
				if (CommonUtil.isImage(bean.getFileName()) && bean.getFileId() == null) {
					String thumbPath = DiskUtil.getThumbPath(bean.getUserId(), bean.getGuidName(), CommConstants.FILE_TYPE_ONLINEDISK);
					ImageUtil.createThumb(targetFile, thumbPath);
				}
			} catch (Exception ex) {
				re = ErrorType.error500.name();
				Logs.getLogger().error(bean.toString(), ex);
			}
		}
		return re;
	}
	
	/**
	 * 企业网盘文件同步
	 * @param bean
	 * @param targetFile
	 * @param result
	 */
	private String syncShareFile(FileUploadDTO bean, File targetFile, String result) {
		String re = result;
		if (result != null && !result.startsWith(CommConstants.ERROR_MARK)) {
			try {
				ShareFileUpdateDTO resultDto = PojoMapper.fromJsonAsObject(result, ShareFileUpdateDTO.class);
				long fileId = resultDto.getFileId();
				// 移动原文件至历史目录
				if (bean.getFileId() != null) {
					bean.setGuidName(bean.getOldGuid());
					File oldFile = DiskUtil.getTargetFile(bean);
					if (oldFile.exists()) {
						// 移动原文件至历史目录
						String historyFilePath = DiskUtil.getHistoryFilePath(bean.getEntId(), fileId, bean.getOldGuid(), CommConstants.FILE_TYPE_SHAREDISK);
						File historyFile = new File(DiskUtil.ONLINEDISK_PATH, historyFilePath);
						oldFile.renameTo(historyFile);
					}
				}
				// 创建文件缩略图
				if (CommonUtil.isImage(bean.getFileName()) && bean.getFileId() == null) {
					String thumbPath = DiskUtil.getThumbPath(bean.getEntId(), bean.getGuidName(), CommConstants.FILE_TYPE_SHAREDISK);
					ImageUtil.createThumb(targetFile, thumbPath);
				}
			} catch (Exception ex) {
				re = ErrorType.error500.name();
				Logs.getLogger().error(bean.toString(), ex);
			}
		}
		return re;
	}

	/**
	 * 同步上传之前，检查文件版本，网盘空间，权限，同名文件
	 * @param bean
	 * @return
	 */
	private String checkFileSync(FileUploadDTO bean, String token) {
		String result = CommConstants.OK_MARK;
		if (CommConstants.FILE_TYPE_ONLINEDISK.equals(bean.getType())) {
			// 个人网盘文件
			NetworkFileDTO fileDTO = bean.toPrivateFileDTO();
			fileDTO.setGuid(bean.getOldGuid());
			String postData = PojoMapper.toJson(fileDTO);
			result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.checkPersonalFileSyncUpload), token, postData);
		} else if (CommConstants.FILE_TYPE_SHAREDISK.equals(bean.getType())) {
			// 企业网盘文件
			ShareFileUpdateDTO fileDTO = bean.toEnterpriseFileDTO();
			fileDTO.setGuid(bean.getOldGuid());
			String postData = PojoMapper.toJson(fileDTO);
			result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.checkShareFileSyncUpload), token, postData);
		}
		return result;
	}

}
