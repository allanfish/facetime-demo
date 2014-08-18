package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.FolderAndFileParamDTO;
import com.conlect.oatos.dto.client.ShareFileDTO;
import com.conlect.oatos.dto.client.ShareFileRecordDTO;
import com.conlect.oatos.dto.client.ShareFileRecordsDTO;
import com.conlect.oatos.dto.client.ShareFileUpdateDTO;
import com.conlect.oatos.dto.client.ShareFilesDTO;
import com.conlect.oatos.dto.client.ShareFolderAndFileDTO;
import com.conlect.oatos.dto.client.ShareFolderAndFileUpdateDTO;
import com.conlect.oatos.dto.client.ShareFolderDTO;
import com.conlect.oatos.dto.client.ShareFolderUpdateDTO;
import com.conlect.oatos.dto.client.ShareFoldersDTO;
import com.conlect.oatos.dto.client.ShareHistoryDTO;
import com.conlect.oatos.dto.client.ShareLinkFilesDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 企业网盘REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>按企业ID取企业网盘下文件夹列表，不包括回收站中文件夹
 * <li>按企业取ID企业网盘下取文件列表，不包括回收站中文件
 * <li>增加文件夹
 * <li>修改共享文件夹
 * <li>修改共享文件夹版本
 * <li>修改共享文件
 * <li>添加共享文件
 * <li>取企业下所有共享文件夹和共享文件
 * <li>修改共享文件夹和共享文件
 * <li>移动共享文件夹和共享文件
 * <li>删除共享文件夹和共享文件
 * <li>取企业下文件夹
 * <li>取企业下文件
 * <li>取企业下共享文件夹和共享文件
 * <li>移动个人网盘到企业网盘
 * <li>把企业网盘文件删掉到回收站
 * <li>共享文件是存在同名的
 * <li>删掉共享文件夹到回收站
 * <li>得到单个共享文件
 * <li>取单个共享文件夹信息
 * <li>还原回收站共享文件夹和共享文件
 * <li>取企业下所有文件夹
 * <li>取企业下所有文件
 * <li>取企业下所有共享文件夹和共享文件
 * <li>取企业下文件夹
 * <li>取企业下文件
 * <li>取企业下共享文件夹和共享文件
 * <li>获取共享的文件信息
 * <li>同步企业网盘文件
 * <li>取共享文件历史记录
 * <li>取共享文件夹历史记录
 * <li>取共享文件夹历史记录详细
 * <li>恢复共享文件版本
 * <li>恢复共享文件夹和文件版本
 * <li>取企业下用户本地同步的共享文件夹,不包括回收站
 * <li>取企业下用户本地同步的共享文件,不包括回收站
 * <li>取企业下用户本地同步可读的共享文件夹和共享文件,不包括回收站
 * <li>取同名文件
 * <li>获取企业网盘版本号
 * <li>插入文件访问记录
 * <li>获取文件访问记录
 * <li>获取子文件夹和文件，当folderId为空时，获取顶级文件夹
 * </ul>
 * 
 * @author yang
 * 
 */
public interface ShareDiskUrl {

	/**
	 * <p>
	 * 按企业ID取企业网盘下文件夹列表，不包括回收站中文件夹
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>企业id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFoldersDTO}的JSON：企业网盘文件夹list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getShareFoldersByEntId = "/sc/shareDisk/getShareFoldersByEntId";

	/**
	 * <p>
	 * 按企业ID取企业网盘下取文件列表，不包括回收站中文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>企业id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFilesDTO}的JSON：企业网盘文件list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getShareFilesByEntId = "/sc/shareDisk/getShareFilesByEntId";

	/**
	 * <p>
	 * 修改企业网盘文件夹信息及文件夹容量
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFolderUpdateDTO}的JSON：企业网盘文件夹修改信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFolderDTO}的JSON：修改后的企业网盘文件夹信息
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorSameFolder}：父目录下已经有与此同名的目录存在
	 * <li>{@link ErrorType#errorVersionConflict}：文件夹版本冲突
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String updateShareFolderSize = "/sc/shareDisk/updateShareFolderSize";

	/**
	 * <p>
	 * 添加企业网盘文件至数据库
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFileUpdateDTO}的JSON：企业网盘文件信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>文件id：新添加的文件id
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorNoPermission}：用户没有当前文件夹下的上传权限
	 * <li>{@link ErrorType#errorFolderSpaceOver}：当前文件夹空间已满
	 * <li>{@link ErrorType#errorNoSpace}：企业网盘空间已满
	 * <li>{@link ErrorType#errorSameFile}：同一文件夹下存在同名文件
	 * <li>{@link ErrorType#errorFolderDeleted}：父文件夹已经删除
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String addShareFile = "/sc/shareDisk/addShareFile";

	/**
	 * <p>
	 * 按企业取取企业网盘下文件夹列表和文件列表，不包括回收站的文件夹和文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>企业id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFolderAndFileDTO}的JSON：企业网盘文件夹list和文件list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getShareFolderAndFileByEntId = "/sc/shareDisk/getShareFolderAndFileByEntId";

	/**
	 * <p>
	 * 按用户取取企业网盘下有可见权限的文件夹列表，不包括回收站的文件夹
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>用户id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFoldersDTO}的JSON：企业网盘文件夹list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getShareFoldersByUserId = "/sc/shareDisk/getShareFoldersByUserId";

	/**
	 * <p>
	 * 按用户取取企业网盘下有可见权限的文件列表，不包括回收站的文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>用户id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFilesDTO}的JSON：企业网盘文件list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getShareFilesByUserId = "/sc/shareDisk/getShareFilesByUserId";

	/**
	 * <p>
	 * 按用户取取企业网盘下有可见权限的文件夹列表和文件列表，不包括回收站的文件夹和文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>用户id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFolderAndFileDTO}的JSON：企业网盘文件夹list和文件list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getShareFolderAndFileByUserId = "/sc/shareDisk/getShareFolderAndFileByUserId";

	/**
	 * <p>
	 * 删除企业网盘文件至回收站
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFileUpdateDTO}的JSON：企业网盘文件修改信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：删除成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorNoPermission}：用户没有此文件的删除权限
	 * <li>{@link ErrorType#errorFileLocked}：文件被人他人锁定
	 * <li>{@link ErrorType#errorVersionConflict}：版本冲突
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String deleteShareFile = "/sc/shareDisk/deleteShareFile";

	/**
	 * <p>
	 * 企业网盘文件上传前，判断企业网盘文件是否重名，上传权限，网盘空间及文件夹空间
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFileUpdateDTO}的JSON：企业网盘文件修改信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：正常，可以上传
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorNoPermission}：用户没有当前文件夹下的上传权限
	 * <li>{@link ErrorType#errorFolderSpaceOver}：当前文件夹空间已满
	 * <li>{@link ErrorType#errorNoSpace}：企业网盘空间已满
	 * <li>{@link ErrorType#errorSameFile}：同一文件夹下存在同名文件
	 * <li>{@link ErrorType#errorFolderDeleted}：父文件已被删除
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String checkShareFileUpload = "/sc/shareDisk/checkShareFileUpload";

	/**
	 * <p>
	 * 按文件id取单个企业网盘文件信息
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>文件id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFileDTO}的JSON：企业网盘文件信息
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getShareFileById = "/sc/shareDisk/getShareFileById";

	/**
	 * <p>
	 * 按文件夹id取单个企业网盘文件夹信息
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>文件夹id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFolderDTO}的JSON：企业网盘文件夹信息
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getShareFolderById = "/sc/shareDisk/getShareFolderById";

	/**
	 * <p>
	 * 按企业取企业网盘下文件夹列表，包括回收站中文件夹
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>企业id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFoldersDTO}的JSON：：企业网盘文件夹list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getAllShareFoldersByEntId = "/sc/shareDisk/getAllShareFoldersByEntId";

	/**
	 * <p>
	 * 按企业取企业网盘下文件列表，包括回收站中文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>企业id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFilesDTO}的JSON：企业网盘文件list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getAllShareFilesByEntId = "/sc/shareDisk/getAllShareFilesByEntId";

	/**
	 * <p>
	 * 按企业取企业网盘下文件夹列表和文件列表，包括回收站中文件夹和文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>企业id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFolderAndFileDTO}的JSON：企业网盘文件夹list和文件list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getAllShareFolderAndFileByEntId = "/sc/shareDisk/getAllShareFolderAndFileByEntId";

	/**
	 * <p>
	 * 按用户取企业网盘下有可见权限的文件夹列表，包括回收站中文件夹
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>用户id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFoldersDTO}的JSON：企业网盘文件夹list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getAllShareFoldersByUserId = "/sc/shareDisk/getAllShareFoldersByUserId";

	/**
	 * <p>
	 * 按用户取企业网盘下有可见权限的文件列表，包括回收站中文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>用户id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFilesDTO}的JSON：企业网盘文件list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getAllShareFilesByUserId = "/sc/shareDisk/getAllShareFilesByUserId";

	/**
	 * <p>
	 * 按用户取企业网盘下有可见权限的文件夹列表和文件列表，包括回收站中文件夹和文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>用户id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFolderAndFileDTO}的JSON：企业网盘文件夹list和文件list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getAllShareFolderAndFileByUserId = "/sc/shareDisk/getAllShareFolderAndFileByUserId";

	/**
	 * <p>
	 * 按外链id取共享的文件夹列表和文件列表
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>外链id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareLinkFilesDTO}的JSON：共享的文件夹list和文件list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getShareLinkFiles = "/sc/shareDisk/getShareLinkFiles";

	/**
	 * <p>
	 * 同步企业网盘文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFileUpdateDTO}的JSON：企业网盘文件修改信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFileUpdateDTO}的JSON：同步后的文件信息
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorNoPermission}：用户没有当前文件夹下的本地同步权限
	 * <li>{@link ErrorType#errorFolderSpaceOver}：当前文件夹空间已满
	 * <li>{@link ErrorType#errorNoSpace}：企业网盘空间已满
	 * <li>{@link ErrorType#errorSameFile}：同一文件夹下存在同名文件
	 * <li>{@link ErrorType#errorVersionConflict}：版本冲突
	 * <li>{@link ErrorType#errorFileLocked}：文件被人他人锁定
	 * <li>{@link ErrorType#errorFolderDeleted}：父文件已被删除
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String syncEnterpriseFile = "/sc/shareDisk/syncEnterpriseFile";

	/**
	 * <p>
	 * 按文件取企业网盘文件历史修改记录
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFileUpdateDTO}的JSON：企业网盘文件信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareHistoryDTO}的JSON：企业网盘文件历史修改记录
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getShareFileHistory = "/sc/shareDisk/getShareFileHistory";

	/**
	 * <p>
	 * 按文件夹取企业网盘文件夹历史修改记录
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFolderUpdateDTO}的JSON：企业网盘文件夹信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareHistoryDTO}的JSON：企业网盘文件夹和文件历史修改记录
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getShareFolderHistory = "/sc/shareDisk/getShareFolderHistory";

	/**
	 * <p>
	 * 按文件夹和版本取企业网盘文件夹历史版本详细修改记录
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFolderUpdateDTO}的JSON：企业网盘文件夹信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareHistoryDTO}的JSON：企业网盘文件夹和文件历史修改记录
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getShareFolderVersionDetail = "/sc/shareDisk/getShareFolderVersionDetail";

	/**
	 * <p>
	 * 按用户取企业网盘下有本地交互权限的文件夹列表，,不包括回收站中文件夹
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>用户id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFoldersDTO}的JSON：企业网盘文件夹list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getSyncShareFoldersByUserId = "/sc/shareDisk/getSyncShareFoldersByUserId";

	/**
	 * <p>
	 * 按用户取企业网盘下有本地交互权限的文件列表，,不包括回收站中文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>用户id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFilesDTO}的JSON：企业网盘文件list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getSyncShareFilesByUserId = "/sc/shareDisk/getSyncShareFilesByUserId";

	/**
	 * <p>
	 * 按用户取企业网盘下有本地交互权限的文件夹列表和文件列表，,不包括回收站中文件夹和文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>用户id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFolderAndFileDTO}的JSON：企业网盘文件夹list和文件list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getSyncShareFolderAndFileByUserId = "/sc/shareDisk/getSyncShareFolderAndFileByUserId";

	/**
	 * <p>
	 * 企业网盘文件同步上传之前，检查文件版本，网盘空间，权限，同名文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFileUpdateDTO}的JSON：文件上传信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：正常，可以开始上传文件
	 * </ul>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#errorFileLocked}+:用户名：文件被他人锁定
	 * <li>{@link ErrorType#errorVersionConflict}：版本冲突
	 * <li>{@link ErrorType#errorFolderDeleted}：父文件已被删除
	 * <li>{@link ErrorType#errorNoPermission}：没有本地同步权限
	 * <li>{@link ErrorType#errorFolderSpaceOver}：文件夹空间已满
	 * <li>{@link ErrorType#errorNoSpace}：企业网盘空间已满
	 * <li>{@link ErrorType#errorSameFile}：同一文件夹下存在同名文件
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String checkShareFileSyncUpload = "/sc/shareDisk/checkShareFileSyncUpload";

	/**
	 * <p>
	 * 取企业网盘当前版本号
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>企业id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>企业网盘当前版本号
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getShareDiskVersion = "/sc/ent/getShareFileVersion";

	/**
	 * <p>
	 * 插入企业网盘文件访问记录，包括下载和浏览记录
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFileRecordDTO}的JSON：企业网盘文件访问记录
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：插入成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String insertFileAccessRecord = "/sc/shareDisk/insertFileAccessRecord";

	/**
	 * <p>
	 * 取企业网盘文件访问记录，包括下载和浏览记录
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFileRecordDTO}的JSON：企业网盘文件访问记录
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFileRecordsDTO}的JSON：企业网盘文件访问记录list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getFileAccessRecord = "/sc/shareDisk/getFileAccessRecord";

	/**
	 * <p>
	 * 按文件夹取企业网盘子文件夹和文件，取顶层文件夹时，文件夹id传null
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link FolderAndFileParamDTO}的JSON：企业网盘文件夹和文件获取参数
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFolderAndFileDTO}的JSON：企业网盘文件夹list和文件list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getEntFolderAndFileByFolderId = "/sc/shareDisk/getEntFolderAndFileByFolderId";

	/**
	 * <p>
	 * 新建企业网盘目录
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFolderUpdateDTO}的JSON：新建企业网盘目录信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFolderDTO}的JSON：新建的企业网盘目录信息
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorNoPermission}：没有父目录下的编辑权限
	 * <li>{@link ErrorType#errorSameFolder}：父目录下已经有与此同名的目录存在
	 * <li>{@link ErrorType#errorFolderDeleted}：父文件夹已经被删除
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String newShareFolder = "/sc/shareDisk/newShareFolder";

	/**
	 * <p>
	 * 修改企业网盘目录信息
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFolderUpdateDTO}的JSON：企业网盘目录修改信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFolderDTO}的JSON：修改后的企业网盘目录信息
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorNoPermission}：没有此目录的编辑权限
	 * <li>{@link ErrorType#errorSameFolder}：父目录下已经有与此同名的目录存在
	 * <li>{@link ErrorType#errorVersionConflict}：文件夹版本冲突
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String updateShareFolder = "/sc/shareDisk/updateShareFolder";

	/**
	 * <p>
	 * 删除企业网盘目录至回收站
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFolderUpdateDTO}的JSON：企业网盘目录修改信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：删除成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorNoPermission}：没有此目录的删除权限
	 * <li>{@link ErrorType#errorVersionConflict}：版本冲突
	 * <li>{@link ErrorType#errorFileLocked}：文件被人他人锁定
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String deleteShareFolder = "/sc/shareDisk/deleteShareFolder";

	/**
	 * <p>
	 * 修改企业网盘文件信息
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFileUpdateDTO}的JSON：企业网盘文件修改信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFileDTO}的JSON：修改后的文件信息
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorNoPermission}：没有此文件的编辑权限
	 * <li>{@link ErrorType#errorSameFile}：目录下已经有与此同名的文件存在
	 * <li>{@link ErrorType#errorFileLocked}：文件被人他人锁定
	 * <li>{@link ErrorType#errorVersionConflict}：文件版本冲突
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String updateShareFile = "/sc/shareDisk/updateShareFile";

	/**
	 * <p>
	 * 修改企业网盘文件夹和文件信息
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFolderAndFileUpdateDTO}的JSON：企业网盘文件夹和文件修改信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：修改成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorNoPermission}：没有权限
	 * <li>{@link ErrorType#errorSameFile}：目录下已经有与此同名的文件存在
	 * <li>{@link ErrorType#errorSameFolder}：父目录下已经有与此同名的目录存在
	 * <li>{@link ErrorType#errorFileLocked}：文件被人他人锁定
	 * <li>{@link ErrorType#errorVersionConflict}：版本冲突
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String updateShareFolderAndFiles = "/sc/shareDisk/updateShareFolderAndFiles";

	/**
	 * <p>
	 * 移动企业网盘文件夹和文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFolderAndFileUpdateDTO}的JSON：企业网盘文件夹和文件修改信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：移动成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorNoPermission}：没有权限
	 * <li>{@link ErrorType#errorSameFile}：目录下已经有与此同名的文件存在
	 * <li>{@link ErrorType#errorSameFolder}：父目录下已经有与此同名的目录存在
	 * <li>{@link ErrorType#errorFolderSpaceOver}：文件夹空间已满
	 * <li>{@link ErrorType#errorFileLocked}：文件被人他人锁定
	 * <li>{@link ErrorType#errorVersionConflict}：版本冲突
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String moveShareFolderAndFiles = "/sc/shareDisk/moveShareFolderAndFiles";

	/**
	 * <p>
	 * 删除企业网盘回收站文件夹和文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFolderAndFileUpdateDTO}的JSON：企业网盘文件夹和文件修改信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：删除成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorNoPermission}：没有权限
	 * <li>{@link ErrorType#errorVersionConflict}：版本冲突
	 * <li>{@link ErrorType#errorFileLocked}：文件被人他人锁定
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String deleteShareFolderAndFiles = "/sc/shareDisk/deleteShareFolderAndFiles";

	/**
	 * <p>
	 * 从回收站还原删除的企业网盘文件夹和文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFolderAndFileUpdateDTO}的JSON：企业网盘文件夹和文件修改信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：还原成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorNoPermission}：没有权限
	 * <li>{@link ErrorType#errorSameFile}：目录下已经有与此同名的文件存在
	 * <li>{@link ErrorType#errorSameFolder}：父目录下已经有与此同名的目录存在
	 * <li>{@link ErrorType#errorFolderSpaceOver}：文件夹空间已满
	 * <li>{@link ErrorType#errorNoSpace}：企业网盘空间已满
	 * <li>{@link ErrorType#errorVersionConflict}：版本冲突
	 * <li>{@link ErrorType#errorFileLocked}：文件被人他人锁定
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String restoreShareFolderAndFiles = "/sc/shareDisk/restoreShareFolderAndFiles";

	/**
	 * <p>
	 * 恢复企业网盘文件夹历史版本
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFolderUpdateDTO}的JSON：企业网盘文件夹修改信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：恢复成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorNoPermission}：没有权限
	 * <li>{@link ErrorType#errorSameFile}：目录下已经有与此同名的文件存在
	 * <li>{@link ErrorType#errorSameFolder}：父目录下已经有与此同名的目录存在
	 * <li>{@link ErrorType#errorFolderSpaceOver}：文件夹空间已满
	 * <li>{@link ErrorType#errorNoSpace}：企业网盘空间已满
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String restoreShareFolderVersion = "/sc/shareDisk/restoreShareFolderVersion";

	/**
	 * <p>
	 * 恢复企业网盘文件历史版本
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFileUpdateDTO}的JSON：企业网盘文件修改信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：恢复成功
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
	String restoreShareFileVersion = "/sc/shareDisk/restoreShareFileVersion";

	/**
	 * <p>
	 * 取企业网盘回收站中文件夹和文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>企业Id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFolderAndFileDTO}的JSON：企业网盘文件夹list和文件list
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getEntFolderAndFileInRecycle = "/sc/shareDisk/getEntFolderAndFileInRecycle";

	/**
	 * <p>
	 * 锁定企业网盘文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFileDTO}的JSON：文件信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：锁定成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorFileLocked}:锁定文件的用户名：文件已被他人锁定
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String lockShareFile = "/sc/file/lockShareFile";

	/**
	 * <p>
	 * 解锁锁定企业网盘文件
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ShareFilesDTO}的JSON：文件信息list
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：解锁成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String unlockShareFiles = "/sc/file/unlockShareFiles";

	/**
	 * <p>
	 * 检查当前企业网盘使用空间是否超出免费空间
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>企业Id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#TRUE_MARK}：企业网盘使用空间超出免费空间
	 * <li>{@link CommConstants#FALSE_MARK}：企业网盘使用空间未超出免费空间
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String checkEntDiskSizeExceed = "/sc/disk/checkEntDiskSize";

	/**
	 * <p>
	 * 取企业网盘已使用空间
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>企业Id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>企业网盘已使用空间，long型，单位KB
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getShareDiskUsedSizeByEntId = "/sc/shareDisk/getShareDiskUsedSizeByEntId";
}
