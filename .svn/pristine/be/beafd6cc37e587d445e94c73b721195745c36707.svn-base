package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.AcceptFileDTO;
import com.conlect.oatos.dto.client.DownFileDTO;
import com.conlect.oatos.dto.client.FileDTO;
import com.conlect.oatos.dto.client.FileDownloadDTO;
import com.conlect.oatos.dto.client.FileThumbsDTO;
import com.conlect.oatos.dto.client.ImageDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.NetworkFilesDTO;
import com.conlect.oatos.dto.client.SaveFileDTO;
import com.conlect.oatos.dto.client.SharePersonalFileDTO;
import com.conlect.oatos.dto.client.UserIconDTO;
import com.conlect.oatos.dto.client.UserIconsDTO;
import com.conlect.oatos.dto.client.ViewFileDTO;
import com.conlect.oatos.dto.client.ViewFileResultDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;

/**
 * <p>
 * 文件REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>文件转换浏览
 * <li>文件编辑，文件保存
 * <li>用户头像剪切，制作
 * <li>企业LOGO剪切，制作
 * <li>复制个人网盘文件至企业网盘
 * <li>文件下载
 * </ul>
 * 
 * @author yang
 * 
 */
public interface FileUrl extends FileUploadUrl, ConferenceDocUrl {

	/**
	 * <p>
	 * 浏览图片，取图片尺寸及地址
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ViewFileDTO}的JSON：文件转换浏览信息
	 * </ul>
	 * <b>正常返回：</b>{@link ViewFileResultDTO}的JSON
	 * <ul>
	 * <li>{@link ViewFileResultDTO#message} = {@link CommConstants#OK_MARK}：<br>
	 * 转换成功，{@link ViewFileResultDTO#url}为图片地址，{@link ViewFileResultDTO#width}
	 * 为图片宽度px，{@link ViewFileResultDTO#height}为图片高度px
	 * <li>{@link ViewFileResultDTO#message} =
	 * {@link ErrorType#errorNoPermission}：没有浏览权限
	 * <li>{@link ViewFileResultDTO#message} =
	 * {@link ErrorType#errorNotSupported}：该文件不支持图片预览
	 * <li>{@link ViewFileResultDTO#message} =
	 * {@link ErrorType#errorFileNotFound}：文件不存在
	 * <li>{@link ViewFileResultDTO#message} = {@link ErrorType#error500}：系统错误
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String viewFileAsImage = "/sc/file/viewFileAsImage";

	/**
	 * <p>
	 * 将文件转成html，浏览文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ViewFileDTO}的JSON：文件转换浏览信息
	 * </ul>
	 * <b>正常返回：</b>{@link ViewFileResultDTO}的JSON
	 * <ul>
	 * <li>{@link ViewFileResultDTO#message} = {@link CommConstants#OK_MARK}：<br>
	 * 转换成功，{@link ViewFileResultDTO#url}为html地址
	 * <li>{@link ViewFileResultDTO#message} = {@link CommConstants#QUEUED}
	 * ：文档已加入等待队列，等待转换
	 * <li>{@link ViewFileResultDTO#message} = {@link CommConstants#ERROR_MARK}
	 * ：文档转换失败
	 * <li>{@link ViewFileResultDTO#message} =
	 * {@link ErrorType#errorNoPermission}：没有浏览权限
	 * <li>{@link ViewFileResultDTO#message} =
	 * {@link ErrorType#errorNotSupported}：该文件不支持转成html
	 * <li>{@link ViewFileResultDTO#message} =
	 * {@link ErrorType#errorFileNotFound}：文件不存在
	 * <li>{@link ViewFileResultDTO#message} = {@link ErrorType#error500}：系统错误
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String viewFileAsHtml = "/sc/file/viewFileAsHtml";

	/**
	 * <p>
	 * 将文件转成pdf，浏览文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ViewFileDTO}的JSON：文件转换浏览信息
	 * </ul>
	 * <b>正常返回：</b>{@link ViewFileResultDTO}的JSON
	 * <ul>
	 * <li>{@link ViewFileResultDTO#message} = {@link CommConstants#OK_MARK}：<br>
	 * 转换成功，{@link ViewFileResultDTO#url}为pdf地址
	 * <li>{@link ViewFileResultDTO#message} = {@link CommConstants#QUEUED}
	 * ：文档已加入等待队列，等待转换
	 * <li>{@link ViewFileResultDTO#message} = {@link CommConstants#ERROR_MARK}
	 * ：文档转换失败
	 * <li>{@link ViewFileResultDTO#message} =
	 * {@link ErrorType#errorNoPermission}：没有浏览权限
	 * <li>{@link ViewFileResultDTO#message} =
	 * {@link ErrorType#errorNotSupported}：该文件不支持转成pdf
	 * <li>{@link ViewFileResultDTO#message} =
	 * {@link ErrorType#errorFileNotFound}：文件不存在
	 * <li>{@link ViewFileResultDTO#message} = {@link ErrorType#error500}：系统错误
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String viewFileAsPdf = "/sc/file/viewFileAsPdf";

	/**
	 * <p>
	 * 将文件转成swf，浏览文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ViewFileDTO}的JSON：文件转换浏览信息
	 * </ul>
	 * <b>正常返回：</b>{@link ViewFileResultDTO}的JSON
	 * <ul>
	 * <li>{@link ViewFileResultDTO#message} = {@link CommConstants#OK_MARK}：<br>
	 * 转换成功，{@link ViewFileResultDTO#url}为分段swf地址，
	 * {@link ViewFileResultDTO#pageCount}为文件页数
	 * <li>{@link ViewFileResultDTO#message} = {@link CommConstants#QUEUED}
	 * ：文档已加入等待队列，等待转换
	 * <li>{@link ViewFileResultDTO#message} = {@link CommConstants#ERROR_MARK}
	 * ：文档转换失败
	 * <li>{@link ViewFileResultDTO#message} =
	 * {@link ErrorType#errorNoPermission}：没有浏览权限
	 * <li>{@link ViewFileResultDTO#message} =
	 * {@link ErrorType#errorNotSupported}：该文件不支持转成swf
	 * <li>{@link ViewFileResultDTO#message} =
	 * {@link ErrorType#errorFileNotFound}：文件不存在
	 * <li>{@link ViewFileResultDTO#message} = {@link ErrorType#error500}：系统错误
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String viewFileAsSwf = "/sc/file/viewFileAsSwf";

	/**
	 * <p>
	 * 将文件转成html，在线编辑文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ViewFileDTO}的JSON：文件转换浏览信息
	 * </ul>
	 * <b>正常返回：</b>{@link ViewFileResultDTO}的JSON
	 * <ul>
	 * <li>{@link ViewFileResultDTO#message} = {@link ErrorType#errorFileLocked}
	 * +:用户名：文件被他人锁定
	 * <li>{@link ViewFileResultDTO#message} = {@link CommConstants#OK_MARK}：<br>
	 * 转换成功，{@link ViewFileResultDTO#content}为文件html内容
	 * <li>{@link ViewFileResultDTO#message} = {@link CommConstants#QUEUED}
	 * ：文档已加入等待队列，等待转换
	 * <li>{@link ViewFileResultDTO#message} = {@link CommConstants#ERROR_MARK}
	 * ：文档转换失败
	 * <li>{@link ViewFileResultDTO#message} =
	 * {@link ErrorType#errorNoPermission}：没有编辑权限
	 * <li>{@link ViewFileResultDTO#message} =
	 * {@link ErrorType#errorNotSupported}：该文件不支持转成html
	 * <li>{@link ViewFileResultDTO#message} =
	 * {@link ErrorType#errorFileNotFound}：文件不存在
	 * <li>{@link ViewFileResultDTO#message} = {@link ErrorType#error500}：系统错误
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String editFileAsHtml = "/sc/file/editFileAsHtml";

	/**
	 * <p>
	 * 保存文件到网盘
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link SaveFileDTO}的JSON：保存文件信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link NetworkFileDTO}的JSON：保存成功的文件信息
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String saveFileToDisk = "/sc/file/saveFileToDisk";

	/**
	 * <p>
	 * 新建文档
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link SaveFileDTO}的JSON：保存文件信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link FileDTO}的JSON：保存成功的文件信息
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * <li>{@link ErrorType#errorSameFile}文件重复
	 * <li>{@link ErrorType#errorFolderDeleted}：父文件已被删除
	 * </ul>
	 */
	String saveFile = "/sc/file/saveHtml";

	/**
	 * <p>
	 * 接收聊天文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link AcceptFileDTO}的JSON：保存文件信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link NetworkFilesDTO}的JSON：保存成功的文件信息
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String acceptFile = "/sc/file/acceptFile";

	/**
	 * <p>
	 * 制作图像，修改图片大小
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ImageDTO}的JSON：图片信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ImageDTO}的JSON：修改后的图片信息
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String resizeUserPicture = "/sc/file/resizeUserPicture/";

	/**
	 * <p>
	 * 制作头像，剪切图像
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ImageDTO}的JSON：图片信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link UserIconDTO}的JSON：制作后的头像信息
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String cropUserPicture = "/sc/file/cropUserPicture/";

	/**
	 * <p>
	 * 取用户使用过的头像
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>用户id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link UserIconsDTO}的JSON：头像list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getUserIcons = "/sc/file/getUserIcons/";

	/**
	 * <p>
	 * 取企业使用过的logo
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>企业id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link UserIconsDTO}的JSON：logo list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getEnterpriseLogos = "/sc/file/getEnterpriseLogos";

	/**
	 * <p>
	 * 取个人网盘中图片的缩略图
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>用户id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link FileThumbsDTO}的JSON：缩略图list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getDiskImageThumbs = "/sc/file/getDiskImageThumbs";

	/**
	 * <p>
	 * 删除使用过的头像或logo
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link UserIconDTO}的JSON：头像或者logo
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：删除成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String deleteUserIcon = "/sc/file/deleteUserIcon";

	/**
	 * <p>
	 * 打包下载文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link DownFileDTO}的JSON：文件信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>压缩文件地址
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorNoPermission}：没有下载权限
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String downZipFile = "/sc/file/downZipFile";

	/**
	 * <p>
	 * 复制个人网盘文件至企业网盘，发送更新消息
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link SharePersonalFileDTO}的JSON：复制个人网盘文件至企业网盘DTO
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：复制成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorNoPermission}：没有权限
	 * <li>{@link ErrorType#errorSameFile}：目录下已经有与此同名的文件存在
	 * <li>{@link ErrorType#errorFolderSpaceOver}：文件夹空间已满
	 * <li>{@link ErrorType#errorNoSpace}：企业网盘空间已满
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String sharePersonalFile = "/sc/shareDisk/sharePersonalFile";

	/**
	 * <p>
	 * 获取文件的流
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>文件路径
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>文件流
	 * </ul>
	 * <b>异常返回：</b>
	 */
	String getFileStream = "/sc/file/getFileStream";

	/**
	 * <p>
	 * 下载文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link FileDownloadDTO}的JSON：文件下载信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>文件流
	 * </ul>
	 * <b>异常返回：</b>
	 */
	String fileDownload = "/file/fileDownload";

	/**
	 * <p>
	 * 检查文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link RESTurl#fileId}：文件id
	 * <li>{@link RESTurl#type}：文件类型
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>返回带有三个参数的字符串: 状态&大小byte&版本, 其中状态: 正常:0 , 删除:1 , 锁住:2 , 其他错误: 3
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String checkDiskFile = "/sc/disk/checkDiskFile";

	/**
	 * <p>
	 * 分段下载文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link FileDownloadDTO}的JSON：文件下载信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>分段的文件流
	 * </ul>
	 * <b>异常返回：</b>
	 */
	String sectionFileDownload = "/file/sectionFileDownload";

	/**
	 * <p>
	 * 获取媒体文件流
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ViewFileDTO}的JSON：文件信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>文件流
	 * </ul>
	 * <b>异常返回：</b>
	 */
	String getMediaStream = "/file/getMediaStream";
}
