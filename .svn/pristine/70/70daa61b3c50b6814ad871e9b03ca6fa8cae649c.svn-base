package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.ConferenceDTO;
import com.conlect.oatos.dto.client.ConferenceDocDTO;
import com.conlect.oatos.dto.client.ConferenceDocsDTO;
import com.conlect.oatos.dto.client.ConferenceMemberDTO;
import com.conlect.oatos.dto.client.ConferenceMembersDTO;
import com.conlect.oatos.dto.client.ConferencesDTO;
import com.conlect.oatos.dto.client.CreateConferenceDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 视频会议REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>修改会议
 * <li>取会议信息包括参会者
 * <li>取会议列表包括参会者
 * <li>修改会议成员状态
 * <li>添加会议成员
 * <li>添加会议文档中心文件
 * <li>批量添加会议文档中心文件
 * <li>判断会议文档文件是否存在,及网盘空间
 * <li>修改会议
 * <li>更新会议文档总页数
 * </ul>
 * 
 * @author yang
 * 
 */
public interface ConferenceUrl {
	/**
	 * <p>
	 * 修改会议
	 * </p>
	 * <b>参数： </b>{@link ConferenceDTO}会议DTO <br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String updateConferenceStatus = "/sc/conference/updateConferenceStatus";

	/**
	 * <p>
	 * 取会议信息包括参会者
	 * </p>
	 * <b>参数： </b>conferenceId会议Id <br>
	 * <p>
	 * <b>正常返回： </b>{@link ConferenceDTO}视频会议DTO <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getConferenceById = "/sc/conference/getConferenceById";
	/**
	 * <p>
	 * 取会议列表
	 * </p>
	 * <b>参数： </b>userId用户ID <br>
	 * <p>
	 * <b>正常返回： </b>{@link ConferencesDTO}视频会议列表 <br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getConferenceByUserId = "/sc/conference/getConferenceByUserId";
	/**
	 * <p>
	 * 修改会议成员状态
	 * </p>
	 * <b>参数： </b>{@link ConferenceMemberDTO} 会议参与人员DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String updateConferenceMember = "/sc/conference/updateConferenceMember";
	/**
	 * <p>
	 * 添加会议成员
	 * </p>
	 * <b>参数： </b>{@link ConferenceMembersDTO} 会议参与人员DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String addConferenceMembers = "/sc/conference/addConferenceMembers";
	/**
	 * <p>
	 * 添加会议文档中心文件
	 * </p>
	 * <b>参数： </b>{@link ConferenceDocDTO} 视频会议文档DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link ConferenceDocDTO}视频会议文档DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String addConferenceDoc = "/sc/conference/addConferenceDoc";

	/**
	 * <p>
	 * 批量添加会议文档中心文件
	 * </p>
	 * <b>参数： </b>{@link ConferenceDocsDTO} 视频会议文档DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link ConferenceDocsDTO}视频会议文档DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String addConferenceDocs = "/sc/conference/addConferenceDocs";
	/**
	 * <p>
	 * 获取会议文档中心文件
	 * </p>
	 * <b>参数： </b>conferenceId 视频会议ID<br>
	 * <p>
	 * <b>正常返回： </b>{@link ConferenceDocsDTO}视频会议文档DTO<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getConferenceDocsByConId = "/sc/conference/getConferenceDocsByConId";

	/**
	 * <p>
	 * 判断会议文档文件是否存在,及网盘空间
	 * </p>
	 * <b>参数： </b>{@link ConferenceDocDTO} 视频会议文档DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部 错误
	 * <li>{@link ErrorType#errorSameFile} 重复文件
	 * </ul>
	 */
	String checkConferenceDocUpload = "/sc/shareDisk/checkConferenceDocUpload";
	/**
	 * <p>
	 * 修改会议
	 * </p>
	 * <b>参数： </b>{@link CreateConferenceDTO} 创建会议DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部 错误
	 * <li>{@link ErrorType#errorConferenceMemberOver} 会议人数超出
	 * </ul>
	 */
	String updateConference = "/sc/conference/updateConference";

	/**
	 * <p>
	 * 按文件id取单个视频会议文档信息
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>文件id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ConferenceDocDTO}的JSON：视频会议文档信息
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getConferenceDocById = "/sc/conference/getConferenceDocById";
}
