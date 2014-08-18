package com.qycloud.oatos.server.test.service;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;


import org.junit.Test;
import static org.junit.Assert.*;

import com.conlect.oatos.dto.client.admin.AdminDTO;
import com.conlect.oatos.dto.client.admin.AdminsDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;

/**
 * AdminService test
 * 
 * @author yang
 * @update xiao.min 
 * pass
 */
public class AdminServiceTest extends BaseServiceTest {
	

	/**
	 * 测试添加一个二级管理员
	 */
	@Test
	public void addAdmins1() {
		System.out.println("=================测试添加一个二级管理员======================");
		AdminsDTO adminsDTO = new AdminsDTO();
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setUserId(new Long(12));
		adminDTO.setEnterpriseId(new Long(24));
		List<AdminDTO> adminDTOs = new ArrayList<AdminDTO>();
		adminDTOs.add(adminDTO);
		adminsDTO.setAdmins(adminDTOs);
		String postData = PojoMapper.toJson(adminsDTO);
		String re = post(RESTurl.addAdmins, postData);
		//System.out.println(r);
		System.out.println(re);
		if("error500".equals(re)){
				fail(re);
		}
		assertEquals(CommConstants.OK_MARK, re);
		//结果：测试通过，使用数据userId 12,enterpriseId 24
	}

	/**
	 * 多次添加同一个二级管理员
	 */
	@Test
	public void addAdmins2() {
		System.out.println("=================多次添加同一个二级管理员======================");
		AdminsDTO adminsDTO = new AdminsDTO();

		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setUserId(new Long(12));
		adminDTO.setEnterpriseId(new Long(24));

		List<AdminDTO> adminDTOs = new ArrayList<AdminDTO>();
		adminDTOs.add(adminDTO);

		adminsDTO.setAdmins(adminDTOs);

		String postData = PojoMapper.toJson(adminsDTO);
		String re = post(RESTurl.addAdmins, postData);
			//if("error500".equals(re)){
				//fail(re);
			//}
		assertEquals(ErrorType.error500.toString(), re);
		
		String me = post(RESTurl.addAdmins, postData);
			//if("error500".equals(me)){
			//	fail(me);
			//}
			assertEquals(ErrorType.error500.toString(), me);
		
	}



	/**
	 * 测试获得企业的二级管理员
	 */
	@Test
	public void getAdminsByEntId() {
		System.out.println("===================测试获得企业的二级管理员=================");
		String r = post(RESTurl.getAdminsByEntId, "21");
		//System.out.println(r);
		if("error500".equals(r)){
			fail(r);
		}
		System.out.println("re"+r);
		//AdminsDTO dto = PojoMapper.fromJsonAsObject(r, AdminsDTO.class);
		//assertEquals(dto.getAdmins().get(0).getEnterpriseId(), 21);

	}

	/**
	 * 测试更新企业二级管理员
	 */
	@Test
	public void updateAdmins() {
		System.out.println("===================测试更新企业二级管理员=================");
		AdminsDTO adminsDTO = new AdminsDTO();
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setUserId(new Long(0));
		adminDTO.setUserName("DDD");
		adminDTO.setEditEntInfo(true);
		adminDTO.setSetRole(true);
		adminDTO.setCreateDept(true);
		adminDTO.setCreateMember(true);
		adminDTO.setFolderPermission(true);
		adminDTO.setRecord(true);
		adminDTO.setFolderPermission(true);
		adminDTO.setCsPlugin(true);
		adminDTO.setSetBlock(true);
		adminDTO.setSetRole(true);
		adminDTO.setCreateFolder(true);
		adminDTO.setCreateDept(true);
		adminDTO.setAddAdmin(true);

		List<AdminDTO> adminDTOs = new ArrayList<AdminDTO>();
		adminDTOs.add(adminDTO);

		adminsDTO.setAdmins(adminDTOs);
		String postData = PojoMapper.toJson(adminsDTO);
		String re = post(RESTurl.updateAdmins, postData);
		if("error500".equals(re)){
			fail(re);
		}
		assertEquals(CommConstants.OK_MARK, re);
	}

	/**
	 * 测试删除企业二级管理员
	 */
	@Test
	public void delAdmins() {
		System.out.println("===================试删除企业二级管理员=================");
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setUserId(new Long(12));
		adminDTO.setUserName("DDD");
		adminDTO.setEditEntInfo(true);
		adminDTO.setSetRole(true);
		adminDTO.setCreateDept(true);
		adminDTO.setCreateMember(true);
		adminDTO.setFolderPermission(true);
		adminDTO.setRecord(true);
		adminDTO.setFolderPermission(true);
		adminDTO.setCsPlugin(true);
		adminDTO.setSetBlock(true);
		adminDTO.setSetRole(true);
		adminDTO.setCreateFolder(true);
		adminDTO.setCreateDept(true);
		adminDTO.setAddAdmin(true);

		String postData = PojoMapper.toJson(adminDTO);
		String re = post(RESTurl.deleteAdmin, postData);
		if("error500".equals(re)){
			fail(re);
		}
		assertEquals(CommConstants.OK_MARK, re);
	}
	
	

}
