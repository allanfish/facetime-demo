package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.ShareFileUpdateDTO;
import com.conlect.oatos.dto.client.TakePictureDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;

/**
 * <p>
 * 文件上传REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>保存截图
 * <li>拍照并保存
 * <li>拍照
 * <li>录音上传
 * <li>文件上传
 * <li>文件分段上传
 * <li>网盘文件同步上传
 * </ul>
 * 
 * @author yang
 * 
 */
public interface FileUploadUrl {

	/**
	 * <p>
	 * 保存截图至个人网盘
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>request文件流
	 * <li>{@link RESTurl#userId}：用户id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>OK$ + 文件地址
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorFolderSpaceOver}：文件夹空间已满
	 * <li>{@link ErrorType#errorNoSpace}：企业网盘空间已满
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String screenshot = "/sc/file/screenshot";

	/**
	 * <p>
	 * 拍照并保存
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>request文件流
	 * <li>{@link RESTurl#postJsonData}：{@link TakePictureDTO}的JSON，保存文件信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>OK$ + 文件地址
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorNoPermission}：没有权限
	 * <li>{@link ErrorType#errorFolderSpaceOver}：文件夹空间已满
	 * <li>{@link ErrorType#errorNoSpace}：企业网盘空间已满
	 * <li>{@link ErrorType#errorVersionConflict}：父文件夹已经删除
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String tackPictureAndSave = "/sc/file/tackPictureAndSave";

	/**
	 * <p>
	 * 制作头像时拍照上传
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>request文件流
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>OK$ + 文件地址
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String tackPicture = "/swf/takePicture";

	/**
	 * <p>
	 * 录音上传
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>request文件流
	 * <li>{@link RESTurl#userId}：用户id，接收者id，用户名，接收者名
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：上传成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String recordVoiceMail = "/swf/recordVoiceMail";

	/**
	 * <p>
	 * 文件上传
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>request文件流
	 * <li>{@link RESTurl#postJsonData}：{@link FileUploadDTO}的JSON，文件上传信息
	 * </ul>
	 * <b>1.文件上传之前，检查网盘空间，同名文件，权限</b><br> {@link FileUploadDTO#method} =
	 * {@link RESTurl#getStartPoint}<br>
	 * <b>返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：正常，可以开始上传文件
	 * <li>{@link ErrorType#errorNoPermission}：没有上传权限
	 * <li>{@link ErrorType#errorFolderSpaceOver}：文件夹空间已满
	 * <li>{@link ErrorType#errorNoSpace}：企业网盘空间已满
	 * <li>{@link ErrorType#errorSameFile}：同一文件夹下存在同名文件
	 * <li>{@link ErrorType#errorVersionConflict}：文件夹已经删除
	 * <li>其他返回值：系统错误
	 * </ul>
	 * <b>2.文件上传</b><br> {@link FileUploadDTO#method}不传<br>
	 * <b>返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：文件上传完成
	 * <li>{@link ErrorType#errorNoPermission}：没有上传权限
	 * <li>{@link ErrorType#errorFolderSpaceOver}：文件夹空间已满
	 * <li>{@link ErrorType#errorNoSpace}：企业网盘空间已满
	 * <li>{@link ErrorType#errorSameFile}：同一文件夹下存在同名文件
	 * <li>{@link ErrorType#errorVersionConflict}：文件夹已经删除
	 * <li>其他返回值：系统错误
	 * </ul>
	 */
	String fileUpload = "/file/fileUpload";

	/**
	 * <p>
	 * 文件分段上传
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>request分段文件流
	 * <li>{@link RESTurl#postJsonData}：{@link FileUploadDTO}的JSON，文件上传信息
	 * </ul>
	 * <b>1.文件上传之前，检查网盘空间，权限，同名文件，取上传开始位置</b><br> {@link FileUploadDTO#method} =
	 * {@link RESTurl#getStartPoint}<br>
	 * <b>返回：</b>
	 * <ul>
	 * <li>已上传分块数（整数）：正常，可以开始上传文件
	 * <li>{@link CommConstants#UPLOAD_SUCCESS}：文件上传完成
	 * <li>{@link ErrorType#errorNoPermission}：没有上传权限
	 * <li>{@link ErrorType#errorFolderSpaceOver}：文件夹空间已满
	 * <li>{@link ErrorType#errorNoSpace}：企业网盘空间已满
	 * <li>{@link ErrorType#errorSameFile}：同一文件夹下存在同名文件
	 * <li>{@link ErrorType#errorVersionConflict}：文件夹已经删除
	 * <li>其他返回值：系统错误
	 * </ul>
	 * <b>2.上传分块</b><br> {@link FileUploadDTO#method}不传<br>
	 * <b>返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：分块上传完成
	 * <li>{@link CommConstants#ERROR_MARK}：分块上传失败
	 * <li>{@link CommConstants#UPLOAD_SUCCESS}：文件上传完成
	 * <li>{@link ErrorType#errorNoPermission}：没有上传权限
	 * <li>{@link ErrorType#errorFolderSpaceOver}：文件夹空间已满
	 * <li>{@link ErrorType#errorNoSpace}：企业网盘空间已满
	 * <li>{@link ErrorType#errorSameFile}：同一文件夹下存在同名文件
	 * <li>{@link ErrorType#errorVersionConflict}：文件夹已经删除
	 * <li>其他返回值：系统错误
	 * </ul>
	 */
	String sectionFileUpload = "/file/sectionFileUpload";

	/**
	 * <p>
	 * 网盘文件同步分段上传
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>request分段文件流
	 * <li>{@link RESTurl#postJsonData}：{@link FileUploadDTO}的JSON，文件上传信息
	 * </ul>
	 * <b>1.文件上传之前，检查网盘空间，权限，同名文件，取上传开始位置</b><br> {@link FileUploadDTO#method} =
	 * {@link RESTurl#getStartPoint}<br>
	 * <b>返回：</b>
	 * <ul>
	 * <li>已上传分块数（整数）：正常，可以开始上传文件
	 * <li>{@link NetworkFileDTO}的JSON：个人网盘文件同步上传完成
	 * <li>{@link ShareFileUpdateDTO}的JSON：企业网盘文件同步上传完成
	 * <li>{@link ErrorType#errorVersionConflict}：版本冲突
	 * <li>{@link ErrorType#errorNoPermission}：没有本地同步权限
	 * <li>{@link ErrorType#errorFolderSpaceOver}：文件夹空间已满
	 * <li>{@link ErrorType#errorNoSpace}：企业网盘空间已满
	 * <li>{@link ErrorType#errorSameFile}：同一文件夹下存在同名文件
	 * <li>{@link ErrorType#errorFileLocked}：文件被人他人锁定
	 * <li>其他返回值：系统错误
	 * </ul>
	 * <b>2.上传分块</b><br> {@link FileUploadDTO#method}不传<br>
	 * <b>返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：分块上传完成
	 * <li>{@link CommConstants#ERROR_MARK}：分块上传失败
	 * <li>{@link NetworkFileDTO}的JSON：个人网盘文件同步上传完成
	 * <li>{@link ShareFileUpdateDTO}的JSON：企业网盘文件同步上传完成
	 * <li>{@link ErrorType#errorNoPermission}：没有本地同步权限
	 * <li>{@link ErrorType#errorFolderSpaceOver}：文件夹空间已满
	 * <li>{@link ErrorType#errorNoSpace}：企业网盘空间已满
	 * <li>{@link ErrorType#errorSameFile}：同一文件夹下存在同名文件
	 * <li>{@link ErrorType#errorFileLocked}：文件被人他人锁定
	 * <li>其他返回值：系统错误
	 * </ul>
	 */
	String syncFileUpload = "/file/syncFileUpload";
}
