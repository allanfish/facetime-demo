/**
 * 
 */
package com.qycloud.oatos.server.domain.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conlect.oatos.dto.client.FavoriteFilesDTO;
import com.conlect.oatos.dto.client.ShareFileDTO;
import com.conlect.oatos.dto.client.ShareFilesDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.qycloud.oatos.server.dao.FavoriteFileMapper;
import com.qycloud.oatos.server.domain.entity.ShareFile;

/**
 * 个人收藏夹逻辑层
 * @author huhao
 *
 */
@Service("FavoriteFileLogic")
public class FavoriteFileLogic {

	@Autowired
	private FavoriteFileMapper favoriteFileMapper;
	/**
	 * 增加个人收藏夹
	 * @param favoriteFilesDTO
	 * @return
	 */
	public String addFavoriteFile(FavoriteFilesDTO favoriteFilesDTO) {
		favoriteFileMapper.addFavoriteFile(favoriteFilesDTO.getUserId(),favoriteFilesDTO.getFavoriteFileIdList());
		return CommConstants.OK_MARK;
	}
	/**
	 * 删除个人收藏夹
	 * @param favoriteFilesDTO
	 */
	@Transactional
	public String delFavoriteFile(FavoriteFilesDTO favoriteFilesDTO) {
		List<Long> favoriteFileList = favoriteFilesDTO.getFavoriteFileIdList();
		for (Long fileId : favoriteFileList) {
			favoriteFileMapper.delFavoriteFile(favoriteFilesDTO.getUserId(),fileId);
		}
		return CommConstants.OK_MARK;
		
	}
	/**
	 * 获得个人收藏夹
	 * @param userId
	 * @return
	 */
	public ShareFilesDTO getFavoriteFile(long userId) {
		List<ShareFileDTO> shareFileDTOs = new ArrayList<ShareFileDTO>();
		ShareFilesDTO shareFilesDTO = new ShareFilesDTO();
		List<ShareFile> shareFiles = favoriteFileMapper.getFavoriteFile(userId);
		
		shareFilesDTO.setUserId(userId);
		
		if (shareFiles!=null && shareFiles.size()>0) {
			for (ShareFile shareFile : shareFiles) {
				shareFileDTOs.add(shareFile.toShareFileDTO());
			}
		}
		shareFilesDTO.setShareFileList(shareFileDTOs);
		return shareFilesDTO;
	}
	
}
