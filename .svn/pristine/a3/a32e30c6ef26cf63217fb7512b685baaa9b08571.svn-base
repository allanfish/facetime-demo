/**
 * 
 */
package com.qycloud.oatos.server.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import junitx.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.AssertionErrors;

import com.conlect.oatos.dto.client.FavoriteFilesDTO;
import com.conlect.oatos.dto.client.ShareFilesDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;

/**
 * @author huhao
 *
 */
public class FavoriteFileServiceTest extends BaseServiceTest{
	
	@Before
	public void before(){
		
	}
	
	@After
	public void after(){
		
		
	}
	
	@Test
	public void addFavoriteFile(){
		FavoriteFilesDTO favoriteFilesDTO = new FavoriteFilesDTO();
		favoriteFilesDTO.setUserId(userId3885);
		List<Long> fileIdList = new ArrayList<Long>();
		fileIdList.add(308422L);
		fileIdList.add(373065L);
		favoriteFilesDTO.setFavoriteFileIdList(fileIdList);
		String s = post(RESTurl.addFavoriteFile,PojoMapper.toJson(favoriteFilesDTO)); 
		System.out.println(s);
		assertEquals(CommConstants.OK_MARK, s);
	}
	
	/**
	 * c
	 */
	@Test
	public void getFavoriteFile(){
		String s = post(RESTurl.getFavoriteFile,userId3885+""); 
		System.out.println(s);
		if(ErrorType.error500.toString().equals(s)){
			fail(s);
		}
		
		ShareFilesDTO shareFilesDTO = PojoMapper.fromJsonAsObject(s, ShareFilesDTO.class);
		assertNotNull(shareFilesDTO);
		//assertEquals(0L, shareFilesDTO.getUserId());
		assertEquals(userId3885, shareFilesDTO.getUserId());
	}
	
	@Test
	public void delFavoriteFile(){
		FavoriteFilesDTO favoriteFilesDTO = new FavoriteFilesDTO();
		favoriteFilesDTO.setUserId(userId3885);
		List<Long> fileIdList = new ArrayList<Long>();
		fileIdList.add(308422L);
		fileIdList.add(373065L);
		favoriteFilesDTO.setFavoriteFileIdList(fileIdList);
		String s = post(RESTurl.delFavoriteFile,PojoMapper.toJson(favoriteFilesDTO)); 
		System.out.println(s);
		assertEquals(CommConstants.OK_MARK, s);
	}
	
}
