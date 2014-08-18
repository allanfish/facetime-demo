package com.conlect.oatos.file;

import com.conlect.oatos.dto.client.ConferenceDocDTO;
import com.conlect.oatos.dto.client.ViewFileDTO;
import com.conlect.oatos.dto.client.ViewFileResultDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * 后台内部rest服务请求地址
 * 
 * @author yang
 * 
 */
public interface ServerInnerUrl {

	String getFile = "file/getFile";

	String getFilePath = "file/getFilePath";

	String copyFiles = "file/copyFiles";

	String moveFile = "file/moveFile";

	String deleteFiles = "file/deleteFiles";

	/**
	 * 发送聊天文件
	 * 
	 * @param HttpServletRequest
	 * @return MessageDTO
	 */
	String sendFile = "/sc/file/sendFile";

	/**
	 * 检查保存编辑后的个人网盘文件
	 * 
	 * @param SaveFileDTO
	 * @return FileDTO
	 */
	String checkSavePersonalFile = "/sc/file/checkSavePersonalFile";

	String savePersonalFile = "/sc/file/savePersonalFile";

	/**
	 * 检查保存编辑后的企业网盘文件
	 * 
	 * @param SaveFileDTO
	 * @return FileDTO
	 */
	String checkSaveShareFile = "/sc/file/checkSaveShareFile";

	String saveShareFile = "/sc/file/saveShareFile";

	/**
	 * 更新文档总页数
	 * 
	 * @param FileDTO
	 * @return CommConstants.OK_MARK
	 */
	String updateFilePageCount = "/sc/file/updateFilePageCount";

	/**
	 * <p>
	 * 更新会议文档总页数
	 * </p>
	 * <b>参数： </b>{@link ConferenceDocDTO}视频会议文档DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部 错误
	 * </ul>
	 */
	String updateConferenceDocPageCount = "/sc/conference/updateConferenceDocPageCount";

	/**
	 * <p>
	 * 编辑企业网盘文件前，检查权限，是否被他人锁定，并锁定文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ViewFileDTO}的JSON：文件信息
	 * </ul>
	 * <b>正常返回：</b>{@link ViewFileResultDTO}的JSON
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：正常,可以编辑
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorFileLocked}+:用户名：文件被他人锁定
	 * <li>{@link ErrorType#errorNoPermission}：没有编辑权限
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String checkEditShareFile = "/sc/shareDisk/checkEditShareFile";

	String updatePersonalFileStatus = "/sc/disk/updatePersonalFileStatus";
	String updateShareFileStatus = "/sc/shareDisk/updateShareFileStatus";

}
