package com.qycloud.oatos.server.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.conlect.oatos.dto.client.CheckPermissionDTO;
import com.conlect.oatos.dto.client.CheckPermissionsDTO;
import com.conlect.oatos.dto.client.RoleDTO;
import com.conlect.oatos.dto.client.RolePermissionDTO;
import com.conlect.oatos.dto.client.RolePermissionsDTO;
import com.conlect.oatos.dto.client.RoleUsersDTO;
import com.conlect.oatos.dto.client.RolesDTO;
import com.conlect.oatos.dto.client.UserRoleDTO;
import com.conlect.oatos.dto.client.UserRolesDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.FilePermission;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;

import static org.junit.Assert.*;

/**
 * roleURL接口单元测试用例
 * @author xiao.min
 *
 */
public class RoleServiceTest extends BaseServiceTest{
	
	/**
	 * 增加角色,删除角色
	 * pass
	 */
	@Test	
	public void addRole(){
		System.out.println("================增加角色=====================");
		
		RoleDTO rdto = new  RoleDTO();	
		rdto.setName("HR"+new Random().nextInt(1000));
		rdto.setEnterpriseId(entId);
		rdto.setDescription("this is test add role description and enterprise name is :jenny");
		
		String json = PojoMapper.toJson(rdto);
		String re = post(RESTurl.addRole, json);
		System.out.println(re);
		if(re.equals("error500")){
			fail(re);
		}
		RoleDTO redto = PojoMapper.fromJsonAsObject(re, RoleDTO.class);
		assertNotNull(redto);
		
		redto.setDescription("12312");
		String re1 = postData(RESTurl.updateRole, redto);
		if(re1.equals("error500")){
			fail(re1);
		}
		assertEquals(CommConstants.OK_MARK, re1);
		
		String re2 = postData(RESTurl.deleteRole, redto);
		if(re2.equals("error500")){
			fail(re2);
		}
		assertEquals(CommConstants.OK_MARK, re2);
		
	}
	/***
	 * 增加角色列表
	 * pass + bug1
	 */
	@Test
	public void addRoleList(){
		System.out.println("================增加角色列表=====================");
		RolesDTO rdtos = new  RolesDTO();
		RoleDTO rdto = new  RoleDTO();	
		rdto.setName("HRList");
		rdto.setEnterpriseId(1528);
		rdto.setDescription("this is the first test");
		
		RoleDTO rdto1 = new  RoleDTO();	
		rdto1.setName("HRList1");
		rdto1.setEnterpriseId(1528);
		rdto1.setDescription("this is the second test");
		
		List<RoleDTO> rlList = new ArrayList<RoleDTO>();
		rlList.add(rdto);
		rlList.add(rdto1);
		rdtos.setRoleList(rlList);
		String json = PojoMapper.toJson(rdtos);
		System.out.println(json);
		
		String re = post(RESTurl.addRoleList, json);
		System.out.println("re:"+re);
		if(re.equals("error500")){
			fail(re);
		}
		//RolesDTO redtos = PojoMapper.fromJsonAsObject(re, RolesDTO.class);
		//assertEquals(redtos.getRoleList().get(0).getName(), "HRList");
		
	}
	
	/***
	 *  检查文件操作权限
	 *  pass
	 */
	@Test
	public void checkPermission (){
		System.out.println("======================checkPermission======================");
		 CheckPermissionDTO checkDTO = new  CheckPermissionDTO();
		 checkDTO.setFolderId(1095l);
		 checkDTO.setOperate(FilePermission.Share);
		 checkDTO.setUserId(227);
		 
		 String json = PojoMapper.toJson(checkDTO);
		 String re = post(RESTurl.checkPermission, json);
			System.out.println("re:"+re);
			if(re.equals("error500")){
				fail(re);
			}
		assertEquals( ErrorType.errorNoPermission.toString(),re);	
	}
	
	
	/***
	 *  批量检查文件操作权限
	 *  pass
	 */
	@Test
	public void checkPermissions(){
		System.out.println("======================checkPermissions======================");
		 CheckPermissionDTO checkDTO = new  CheckPermissionDTO();
		 checkDTO.setFolderId(New_folder);
		 checkDTO.setOperate(FilePermission.Share);
		 checkDTO.setUserId(userId3885);
		 
		 List<CheckPermissionDTO> checks = new ArrayList<CheckPermissionDTO>();
		 
		 checks.add(checkDTO);
		 
		 CheckPermissionsDTO chesDTO  = new CheckPermissionsDTO();
		 
		 chesDTO.setCheckPermissionDTOs(checks);
		 
		 chesDTO.setUserId(userId3885);
		 
		 String json = PojoMapper.toJson(chesDTO);
		 String re = post(RESTurl.checkPermissions, json);
			System.out.println("re:"+re);
			if(re.equals("error500")){
				fail(re);
			}
		assertEquals(CommConstants.OK_MARK,re);	
	}
	/***
	 *  指定企业ID， 获取企业中所有角色和文件夹之间的权限关系 
	 *  pass
	 */
	@Test
	public void getRolePermissionsByEnterprise (){
		System.out.println("=========== 指定企业ID， 获取企业中所有角色和文件夹之间的权限关系 ===========");
		
		String re = post(RESTurl.getRolePermissionsByEnterprise, "1158");
		System.out.println(re);
		if(re.equals("error500")){
			fail(re);
		}
		
		RolePermissionsDTO redto = PojoMapper.fromJsonAsObject(re,  RolePermissionsDTO.class );
		assertNotNull(redto);
		assertEquals(redto.getPermissionList().get(0).getFolderId(), 1165);
	}
	/***
	 *  指定企业ID，获取企业所有角色数据
	 *  pass
	 */
	@Test
	public void  getRolesByEnterprise(){
		System.out.println("==========指定企业ID，获取企业所有角色数据==========");
		String re = post(RESTurl.getRolesByEnterprise, "1234");
		System.out.println(re);
		if(re.equals("error500")){
			fail(re);
		}
		
		RolesDTO role = PojoMapper.fromJsonAsObject(re,  RolesDTO.class );
		assertEquals(role.getRoleList().get(0).getRoleId(), 1242);
	}
	/**
	 * 取角色及用户
	 * pass
	 */
	@Test
	public void getRoleUsersByEntId (){
		System.out.println("==========取角色及用户==========");
		String re = post(RESTurl.getRoleUsersByEntId, "1234");
		System.out.println(re);
		if(re.equals("error500")){
			fail(re);
		}
		 RoleUsersDTO roleUser = PojoMapper.fromJsonAsObject(re,   RoleUsersDTO.class);
		 assertEquals(roleUser.getRoleUserDTOs().get(0).getRoleId(), 1242);
		// assertNotNull(roleUser);
	}
	
	/**
	 *  保存用户的角色
	 *  pass 必须是已经存在的roleId 和已经存在的userId
	 */
	@Test
	public void saveUserRoles(){
		System.out.println("========== 保存用户的角色==========");
		
		List<RoleDTO> roleDto = new ArrayList<RoleDTO>();
		RoleDTO rDto = new RoleDTO();
		rDto.setRoleId(101078);
		//rDto.setEnterpriseId(1528);
		//rDto.setName("测试接口");
		//rDto.setDescription("专门测试接口");
		roleDto.add(rDto);
		
		List<UserRoleDTO> urList = new ArrayList<UserRoleDTO>();
		
		UserRoleDTO dto =new UserRoleDTO();
		dto.setUserId(34);
		dto.setRoles(roleDto);
		urList.add(dto);
		
		
		UserRolesDTO urDTO = new  UserRolesDTO();
		urDTO.setUserRoleList(urList);
		 
		String json = PojoMapper.toJson(urDTO);
		System.out.println("json:"+json);
		UserRolesDTO ss = PojoMapper.fromJsonAsObject(json, UserRolesDTO.class);
		System.out.println(ss);
		
		String re = post(RESTurl.saveUserRoles , json);
		System.out.println(re);
		if(re.equals("error500")){
			fail(re);
		}
		 //RoleUsersDTO roleUser = PojoMapper.fromJsonAsObject(re,   RoleUsersDTO.class);
		 assertEquals(CommConstants.OK_MARK, re);
		 //assertNotNull(roleUser);
		 
	}
	
	/**
	 *  指定企业ID， 获取企业所有用户和角色数据
	 *  bug 
	 */
	@Test
	public void getUserRolesByEnterprise(){
		System.out.println("========== 指定企业ID， 获取企业所有用户和角色数据==========");
		String re = post(RESTurl.getUserRolesByEnterprise, "1234");
		System.out.println(re);
		if(re.equals("error500")){
			fail(re);
		}
		//bug 此行无法转换
		 UserRolesDTO roleUser = PojoMapper.fromJsonAsObject(re,  UserRolesDTO.class);
		 //assertEquals(expected, actual);
		 assertNotNull(roleUser);
	}
	/**
	 *  保存角色的文件夹权限信息
	 *  备注：此表设计为：联合主键，由folder,role一起组成
	 *  pass
	 */
	@Test
	public void savePermissions(){
		System.out.println("========== 保存角色的文件夹权限信息==========");
		RolePermissionDTO pdto = new RolePermissionDTO();
		pdto.setFolderId(100212);
		pdto.setRoleId(101082);
		pdto.setShare(true);
		pdto.setDelete(true);
		pdto.setDownload(true);
		pdto.setList(true);
		pdto.setLocal(true);
		pdto.setRead(true);
		pdto.setUpload(true);
		pdto.setWrite(true);
		
		List<RolePermissionDTO> rdtoList = new ArrayList<RolePermissionDTO>();
		rdtoList.add(pdto);
		
		RolePermissionsDTO rDTO = new RolePermissionsDTO();
		
		rDTO.setPermissionList(rdtoList);
		
		String json = PojoMapper.toJson(rDTO);
		System.out.println(json);
		String re = post(RESTurl.savePermissions, json);
		System.out.println(re);
		if(re.equals("error500")){
			fail(re);
		}
		
		//RoleUsersDTO roleUser = PojoMapper.fromJsonAsObject(re, RoleUsersDTO.class);
		 assertEquals(CommConstants.OK_MARK, re);
		// assertNotNull(roleUser);
	}
	
	
}
