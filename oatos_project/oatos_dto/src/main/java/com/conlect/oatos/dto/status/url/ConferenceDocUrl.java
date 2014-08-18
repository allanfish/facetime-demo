package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.ConferenceDocDTO;
import com.conlect.oatos.dto.client.ConferenceDocsDTO;
import com.conlect.oatos.dto.client.ConferencesDTO;
import com.conlect.oatos.dto.client.CreateConferenceDTO;
import com.conlect.oatos.dto.client.PrepareFileAsConferenceDocDTO;
import com.conlect.oatos.dto.client.PrepareShareFileAsConferenceDocDTO;
import com.conlect.oatos.dto.client.ViewFileResultDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 视频会议文档REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>浏览会议文档，将文档转成swf
 * <li>从企业网盘或个人网盘选择添加会议文档
 * <li>删除会议文档
 * <li>创建视频会议
 * </ul>
 * 
 * @author yang
 * 
 */
public interface ConferenceDocUrl {

	/**
	 * <p>
	 * 浏览会议文档，将文档转成swf
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ConferenceDocDTO}的JSON：视频会议文档信息
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
	String viewConferenceDoc = "/sc/file/viewConferenceDoc";

	/**
	 * <p>
	 * 从个人网盘选择添加会议文档
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link PrepareFileAsConferenceDocDTO}的JSON：选择个人网盘文件作为会议文档dto
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：添加成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String addConferenceDocFromPersonalDisk = "/sc/file/addConferenceDocFromPersonalDisk";

	/**
	 * <p>
	 * 从企业网盘选择添加会议文档
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link PrepareShareFileAsConferenceDocDTO}的JSON：选择企业网盘文件作为会议文档dto
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：添加成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String addConferenceDocFromEnterpriseDisk = "/sc/file/addConferenceDocFromEnterpriseDisk";

	/**
	 * <p>
	 * 删除视频会议文档
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ConferenceDocsDTO}的JSON：视频会议文档list
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
	String deleteConferenceDocs = "/sc/conference/deleteConferenceDocs";

	/**
	 * <p>
	 * 删除视频会议
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link ConferencesDTO}的JSON：视频会议list
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
	String deleteConference = "/sc/conference/deleteConference";

	/**
	 * <p>
	 * 创建视频会议
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link CreateConferenceDTO}的JSON：创建视频会议信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>新创建的会议id
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String createConference = "/sc/conference/createConference";
}
