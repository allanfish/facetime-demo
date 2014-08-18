package com.qycloud.oatos.server.test.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.conlect.oatos.dto.client.FavoriteFilesDTO;
import com.conlect.oatos.dto.client.admin.AdminDTO;
import com.conlect.oatos.dto.client.admin.AdminDepartmentDTO;
import com.conlect.oatos.dto.client.admin.AdminsDTO;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.dao.FavoriteFileMapper;
import com.qycloud.oatos.server.domain.logic.AdminLogic;
import com.qycloud.oatos.server.domain.logic.FavoriteFileLogic;
import com.qycloud.oatos.server.test.BaseTest;

/**
 * FavoriteFileLogicTest test
 * @author huhao
 *
 */
public class FavoriteFileLogicTest extends BaseTest {

	@SpringBeanByType
	private FavoriteFileLogic favoriteFileLogic;
	
	@Before
	public void before(){
		
	}
	
	@Test
	public void addFavoriteFile() {
		FavoriteFilesDTO favoriteFilesDTO = new FavoriteFilesDTO();
		favoriteFilesDTO.setUserId(0L);
		List<Long> aList  = new ArrayList<Long>();
		aList.add(89L);
		aList.add(90L);
		favoriteFilesDTO.setFavoriteFileIdList(aList);
		favoriteFileLogic.addFavoriteFile(favoriteFilesDTO);
	}
	
	@Test
	public void delFavoriteFile() {
		FavoriteFilesDTO favoriteFilesDTO = new FavoriteFilesDTO();
		favoriteFilesDTO.setUserId(0L);
		List<Long> aList  = new ArrayList<Long>();
		aList.add(89L);
		aList.add(90L);
		favoriteFilesDTO.setFavoriteFileIdList(aList);
		favoriteFileLogic.delFavoriteFile(favoriteFilesDTO);
	}	
	
	@After
	public void after(){
		
	}
}
