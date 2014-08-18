/**
 * 
 */
package com.qycloud.oatos.server.service;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.FavoriteFilesDTO;
import com.conlect.oatos.dto.client.ShareFilesDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.logic.FavoriteFileLogic;
import com.qycloud.oatos.server.util.BllLogger;

/**
 * 个人收藏夹服务
 * @author huhao
 *
 */
@Controller("FavoriteFileServiceTest")
public class FavoriteFileService {
	
	private final static  Logger logger  = BllLogger.getLogger();
	
	@Autowired
	private FavoriteFileLogic favoriteFileLogic;
	
	/**
	 * 增加个人收藏文件
	 * @param postData
	 * @return
	 */
	@RequestMapping(value=RESTurl.addFavoriteFile,method=RequestMethod.POST)
	@ResponseBody
	public String addFavoriteFile(@RequestBody String postData) {
		String resBody = "";
		try {
			FavoriteFilesDTO favoriteFilesDTO = PojoMapper.fromJsonAsObject(postData, FavoriteFilesDTO.class);
			resBody = favoriteFileLogic.addFavoriteFile(favoriteFilesDTO);
		} catch (Exception e) {
			resBody = ErrorType.error500.name();
			logger.error(postData, e);
		}
		return resBody;
		
	}
	
	/**
	 * 取消个人收藏文件
	 * @param postData
	 * @return
	 */
	@RequestMapping(value=RESTurl.delFavoriteFile,method=RequestMethod.POST)
	@ResponseBody
	public String delFavoriteFile(@RequestBody String postData){
		String resBody = "";
		try {
			FavoriteFilesDTO favoriteFilesDTO = PojoMapper.fromJsonAsObject(postData, FavoriteFilesDTO.class);
			resBody = favoriteFileLogic.delFavoriteFile(favoriteFilesDTO);
		} catch (Exception e) {
			resBody = ErrorType.error500.name();
			logger.error(postData, e);
		}
		return resBody;
	}
	
	/**
	 * 获得个人收藏文件
	 * @param userId
	 * @return
	 */
	@RequestMapping(value=RESTurl.getFavoriteFile,method=RequestMethod.POST)
	@ResponseBody
	public String getFavoriteFile(@RequestBody String userId){
		String resBody = "";
		try {
			ShareFilesDTO shareFilesDTO = favoriteFileLogic.getFavoriteFile(Long.parseLong(userId));
			resBody = PojoMapper.toJson(shareFilesDTO);
		} catch (Exception e) {
			resBody = ErrorType.error500.name();
			logger.error(userId, e);
		}
		return resBody;
	}
	
	
}
