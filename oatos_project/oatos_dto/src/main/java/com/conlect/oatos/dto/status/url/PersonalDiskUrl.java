package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.FolderAndFileDTO;
import com.conlect.oatos.dto.client.FolderAndFileParamDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.NetworkFilesDTO;
import com.conlect.oatos.dto.client.NetworkFolderDTO;
import com.conlect.oatos.dto.client.NetworkFoldersDTO;
import com.conlect.oatos.dto.client.user.PersonDiskAllocListDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 个人网盘REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>新建网盘文件夹
 * <li>修改文件夹
 * <li>获取用户下的文件夹
 * <li>修改文件
 * <li>获取用户文件
 * <li>删掉文件或文件夹
 * <li>更新文件或文件夹
 * <li>取所有网盘文件夹和文件
 * <li>增加个人用户文件
 * <li>删除个人用户文件
 * <li>个人网盘检查是否存在同名文件
 * <li>个人网盘获取单个文件
 * <li>修改个人网盘的文件夹和文件
 * <li>个人网盘获取用户文件夹，包括回收站中文件
 * <li>获取用户文件包括回收站中文件
 * <li>取所有网盘文件夹和文件
 * <li>同步个人网盘文件
 * <li>取邮件附件文件夹
 * <li>个人网盘按文件名搜索文件
 * <li>个人网盘通过文件夹名和用户ID获得文件
 * <li>获取个人网盘文件夹信息
 * <li>个人网盘取同名文件
 * <li>按文件夹取个人网盘子文件夹和文件，取顶层文件夹时，文件夹ID传null
 * </ul>
 * 
 * @author huhao
 * 
 */
public interface PersonalDiskUrl {
	/**
	 * <p>
	 * 新建网盘文件夹
	 * </p>
	 * <b>参数： </b>{@link NetworkFolderDTO} 个人网盘文件夹DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link NetworkFolderDTO}个人网盘文件夹DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorSameFolder} 相同文件夹
	 * <li>{@link ErrorType#errorRequestData}请求的数据有问题
	 * </ul>
	 */
	String addDiskFolder = "/sc/disk/addFolder/";
	/**
	 * <p>
	 * 【重命名】、【修改】个人网盘文件夹的信息
	 * </p>
	 * <b>参数： </b>{@link NetworkFolderDTO} 个人网盘文件夹DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorRequestData} 请求的数据错误
	 * <li>{@link ErrorType#errorSameFolder} 存在相同文件夹
	 * </ul>
	 */
	String updateDiskFolder = "/sc/disk/updateFolder/";

	/**
	 * <p>
	 * 取所有网盘文件夹,不包括回收站
	 * </p>
	 * <b>参数： </b>userId 用户ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link NetworkFoldersDTO}个人网盘文件夹listDTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorSameFile} 文件重名
	 * </ul>
	 */
	String getFoldersByUserId = "/sc/disk/getFolders";
	/**
	 * <p>
	 * 【重命名】、【修改】 文件
	 * </p>
	 * <b>参数： </b>{@link NetworkFileDTO}个人网盘文件DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorSameFile} 名字重复
	 * </ul>
	 */
	String updateDiskFile = "/sc/disk/updateFile";

	/**
	 * <p>
	 * 获取用户文件
	 * </p>
	 * <b>参数： </b>userId用户ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link NetworkFilesDTO}个人网盘文件list <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getFilesByUserId = "/sc/disk/getFiles";

	/**
	 * <p>
	 * 删掉文件或文件夹
	 * </p>
	 * <b>参数： </b>{@link FolderAndFileDTO}个人网盘文件和文件夹DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorRequestData} 请求的数据有问题
	 * </ul>
	 */
	String deleteFolderAndFile = "/sc/disk/deleteFolderAndFile";
	/**
	 * <p>
	 * 批量修改网盘文件夹和文件
	 * </p>
	 * <b>参数： </b>{@link FolderAndFileDTO}文件夹和文件DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String updateFolderAndFile = "/sc/disk/updateFolderAndFile";
	/**
	 * <p>
	 * 取所有网盘文件夹和文件,不包括回收站
	 * </p>
	 * <b>参数： </b>userId用户ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link FolderAndFileDTO}文件夹和文件DTO <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getDiskFolderAndFileByUserId = "/sc/disk/getDiskFolderAndFileByUserId";
	/**
	 * <p>
	 * 添加网盘文件
	 * </p>
	 * <b>参数： </b>{@link NetworkFileDTO}个人网盘文件DTO<br>
	 * <p>
	 * <b>正常返回： </b>fileId 文件ID
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorSameFile} 文件重名
	 * <li>{@link ErrorType#errorNoSpace} 个人网盘空间不足
	 * </ul>
	 */
	String addPersonalFile = "/sc/disk/addPersonalFile";

	/**
	 * <p>
	 * 删除个人网盘文件
	 * </p>
	 * <b>参数： </b>fileID文件ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String deletePersonalFile = "/sc/disk/deletePersonalFile";
	/**
	 * <p>
	 * 判断私有网盘文件是否存在,及网盘空间
	 * </p>
	 * <b>参数： </b>{@link NetworkFileDTO}个人网盘DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorSameFile} 文件重复
	 * <li>{@link ErrorType#errorNoSpace} 没有足够的空间
	 * </ul>
	 */
	String checkPersonalFileUpload = "/sc/disk/checkPersonalFileUpload";
	/**
	 * <p>
	 * 取单个文件信息
	 * </p>
	 * <b>参数： </b>fileId文件ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link NetworkFileDTO}个人网盘文件DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getFileById = "/sc/disk/getFileById";

	/**
	 * <p>
	 * 还原个人网盘回收站文件夹和文件
	 * </p>
	 * <b>参数： </b>{@link FolderAndFileDTO}个人网盘文件和文件夹DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String restoreFolderAndFile = "/sc/disk/restoreFolderAndFile";
	/**
	 * <p>
	 * 取所有网盘文件夹,包括回收站
	 * </p>
	 * <b>参数： </b>userId用户的ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link NetworkFoldersDTO}个人网盘文件夹list DTO <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getAllFoldersByUserId = "/sc/disk/getAllFoldersByUserId";

	/**
	 * <p>
	 * 取所有网盘文件,包括回收站
	 * </p>
	 * <b>参数： </b>userId用户的ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link NetworkFilesDTO}个人网盘文件listDTO <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getAllFilesByUserId = "/sc/disk/getAllFilesByUserId";

	/**
	 * <p>
	 * 取所有网盘文件夹和文件,包括回收站
	 * </p>
	 * <b>参数： </b>userId 用户ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link FolderAndFileDTO}个人网盘文件夹和文件DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getAllDiskFolderAndFileByUserId = "/sc/disk/getAllDiskFolderAndFileByUserId";

	/**
	 * <p>
	 * 同步个人网盘文件
	 * </p>
	 * <b>参数： </b>{@link NetworkFileDTO}个人网盘文件DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link NetworkFileDTO}的JSON：同步后的文件信息 <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#errorSameFile} 文件重名
	 * <li>{@link ErrorType#errorNoSpace} 个人网盘空间不足
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String syncPersonalFile = "/sc/disk/syncPersonalFile";
	/**
	 * <p>
	 * 获取网盘邮件文件夹
	 * </p>
	 * <b>参数： </b>userId用户ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link NetworkFolderDTO}个人网盘文件夹DTO <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getEmailFolder = "/sc/disk/getEmailFolder";

	/**
	 * <p>
	 * 个人网盘通过文件夹名和用户ID获得文件
	 * </p>
	 * <b>参数： </b>{@link NetworkFileDTO}个人网盘文件DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link NetworkFileDTO}个人网盘DTO <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getSystemFileByFileName = "/sc/network/getSystemFileByFileName";

	/**
	 * <p>
	 * 取单个文件夹信息
	 * </p>
	 * <b>参数： </b>folderId文件夹ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link NetworkFileDTO}个人网盘DTO <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getPrivateFolderById = "/sc/disk/getPrivateFolderById";

	/**
	 * <p>
	 * 个人网盘文件同步上传之前，检查文件版本，网盘空间，同名文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link NetworkFileDTO}的JSON：文件上传信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：正常，可以开始上传文件
	 * </ul>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#errorVersionConflict}：版本冲突
	 * <li>{@link ErrorType#errorNoSpace}：个人网盘空间已满
	 * <li>{@link ErrorType#errorSameFile}：同一文件夹下存在同名文件
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String checkPersonalFileSyncUpload = "/sc/disk/checkPersonalFileSyncUpload";

	/**
	 * <p>
	 * 按文件夹取个人网盘子文件夹和文件，取顶层文件夹时，文件夹ID传null
	 * </p>
	 * <b>参数： </b>{@link FolderAndFileParamDTO}查询网盘文件夹和文件的参数DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link FolderAndFileDTO}文件和文件夹DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getPersonalFolderAndFileByFolderId = "/sc/disk/getPersonalFolderAndFileByFolderId";

	/**
	 * <p>
	 * 取个人网盘回收站中文件夹和文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>用户Id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link FolderAndFileDTO}的JSON：个人网盘文件夹list和文件list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getPersonalFolderAndFileInRecycle = "/sc/disk/getPersonalFolderAndFileInRecycle";

	/**
	 * <p>
	 * 检查当前个人网盘使用空间是否超出了免费空间
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>用户Id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#TRUE_MARK}：个人网盘使用空间超出免费空间
	 * <li>{@link CommConstants#FALSE_MARK}：个人网盘使用空间未超出免费空间
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String checkUserDiskSizeExceed = "/sc/onlinedisk/checkUserDiskSize";

	/**
	 * <p>
	 * 分配个人网盘空间
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link PersonDiskAllocListDTO}：个人网盘空间分配信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：分配成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorPersonDiskOverflow}：企业的个人网盘空间不足
	 * <li>{@link ErrorType#errorPersonDiskUsedExceedAllocSize}：用户使用空间大于所分配的空间
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String allocPersonDisk = "/sc/onlinedisk/alloc";

	/**
	 * <p>
	 * 取个人网盘已使用空间
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>用户id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>个人网盘已使用空间，long型，单位KB
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getPersonalDiskUsedSizeByUserId = "/sc/disk/getPersonalDiskUsedSizeByUserId";
}
