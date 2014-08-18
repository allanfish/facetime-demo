package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.P2pKeyDTO;
import com.conlect.oatos.dto.client.UserStatusDTO;
import com.conlect.oatos.dto.status.CallStatus;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * P2PREST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>取p2p的key
 * <li>保存p2p的key
 * <li>得到用户的通话状态
 * <li>修改用户的通话状态
 * </ul>
 * 
 * @author huhao
 * 
 */
public interface P2PUrl {
	/**
	 * <p>
	 * 取p2p的key
	 * </p>
	 * <b>参数： </b>entId企业ID<br>
	 * <p>
	 * <b>正常返回： </b>p2p key<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorUserIsOffline} 用户离线
	 * </ul>
	 */
	String getP2pKey = "/sc/flash/GetFriendIdentityKey";
	/**
	 * <p>
	 * 保存p2p的key
	 * </p>
	 * <b>参数： </b>{@link P2pKeyDTO} P2PKEYDTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK} OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorUserIsOffline} 用户离线
	 * </ul>
	 */
	String saveP2pKey = "/sc/flash/SetIdentityKey";

	/**
	 * <p>
	 * 得到用户的通话状态
	 * </p>
	 * <b>参数： </b>userId 用户的ID<br>
	 * <p>
	 * <b>正常返回： </b>
	 * <ul>
	 * <li>{@link CallStatus#Offline}离线
	 * <li>{@link CallStatus#Busy} 忙碌
	 * <li>{@link CallStatus#Free} 空闲
	 * </ul>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * </ul>
	 */
	String getCallStatus = "/sc/flash/GetCallStatus";
	/**
	 * <p>
	 * 修改用户的通话状态
	 * </p>
	 * <b>参数： </b>{@link UserStatusDTO} 用户状态DTO<br>
	 * <p>
	 * <b>正常返回： </b>{@link CommConstants#OK_MARK}OK<br>
	 * </p>
	 * <b>异常返回： </b>
	 * <ul>
	 * <li>{@link ErrorType#error500} 服务器内部错误
	 * <li>{@link ErrorType#errorUserIsOffline} 用户离线
	 * </ul>
	 */
	String setCallStatus = "/sc/flash/SetCallStatus/";

}
