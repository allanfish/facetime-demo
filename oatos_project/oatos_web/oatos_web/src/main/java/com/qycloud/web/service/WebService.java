package com.qycloud.web.service;

import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.ShareFileDTO;
import com.conlect.oatos.dto.status.ErrorType;

public interface WebService {

	String FLEX_SERVICE = "/flexService";
	/**
	 * 照相服务
	 */
	String TAKE_PICTURE = "/swf/takePicture";
	/**
	 * 录音
	 */
	String VOICE_RECORD = "/swf/recordVoiceMail";

	String SYNC_FILE_DOWNLOAD = "/file/sectionOnlineFileDownload";
	String SECTION_FILE_UPLOAD = "/sectionFileUpload";
	String SECTION_ONLINE_FILE_UPLOAD = "/sectionOnlineFileUpload";
	String GET_VERSION = "/getVersion";
	String FILE_ROUTING = "/onlinedisk/*";
	/**
	 * <p>
	 * 文件上传服务. 包括企业文件和个人文件上传
	 * </p>
	 * <b>参数： </b>{@link FileUploadDTO} 文件上传DTO<br>
	 * <p>
	 * <b>正常返回： </b>企业文件{@link ShareFileDTO} or 个人文件{@link NetworkFileDTO}
	 * </p>
	 * <b>异常返回： </b>
	 * <p> 个人文件异常返回: <p>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorSameFile} 文件重名
	 * <li>{@link ErrorType#errorNoSpace} 个人网盘空间不足
	 * </ul>
	 * 
	 * <p>企业文件异常返回: </p>
	 * <ul>
	 * <li>{@link ErrorType#errorNoPermission}：用户没有当前文件夹下的上传权限
	 * <li>{@link ErrorType#errorFolderSpaceOver}：当前文件夹空间已满
	 * <li>{@link ErrorType#errorNoSpace}：企业网盘空间已满
	 * <li>{@link ErrorType#errorSameFile}：同一文件夹下存在同名文件
	 * <li>{@link ErrorType#errorFolderDeleted}：父文件夹已经删除
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String FILE_ROUTING_UPLOAD = "/fileRoutingUpload";

	String FILE_DOWNLOAD = "/file/fileDownload";

	String SEND_MESSAGE = "/sendmessage";
	String ZIP_FILE_DOWNLOAD = "/zipfile/download/";
}
