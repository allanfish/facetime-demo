package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.FavoriteFilesDTO;
import com.conlect.oatos.dto.client.ShareFilesDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 个人收藏夹REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>添加个人收藏夹文件列表
 * <li>取消个人收藏夹文件列表
 * <li>获得个人收藏夹文件列表
 * </ul>
 * 
 * @author huhao
 * 
 */
public interface FavoriteFileUrl {

	/**
	 * <p>
	 * 添加个人收藏夹文件列表
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link FavoriteFilesDTO} 个人收藏文件夹DTO
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK} OK
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String addFavoriteFile = "/sc/favoriteFile/addFavoriteFile";
	/**
	 * <p>
	 * 取消个人收藏夹文件列表
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link FavoriteFilesDTO} 个人收藏文件夹DTO
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK} OK
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String delFavoriteFile = "/sc/favoriteFile/delFavoriteFile";
	/**
	 * <p>
	 * 获得个人收藏夹文件列表
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>userId用户ID
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link ShareFilesDTO} 企业网盘文件列表DTO
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getFavoriteFile = "/sc/favoriteFile/getFavoriteFile";

}