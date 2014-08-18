package com.qycloud.oatos.server.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import com.conlect.oatos.dto.autobean.ILimitDTO;
import com.conlect.oatos.dto.client.DepartmentDTO;
import com.conlect.oatos.dto.client.DepartmentsDTO;
import com.conlect.oatos.dto.client.EnterpriseDTO;
import com.conlect.oatos.dto.client.LimitDTO;
import com.conlect.oatos.dto.client.UserInfoDTO;
import com.conlect.oatos.dto.client.admin.AdminDataDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.security.Security;
import com.qycloud.oatos.server.test.EncryptModel;
import com.qycloud.oatos.server.test.EncryptUtil;

/**
 * enterpriseUrl 接口测试用例
 * @author xiao.min
 *
 */
public class EnterpriseServiceTest extends BaseServiceTest{
	
	private static String DEPT_ID="dept_id";
	
	/***
	 * 获取企业信息
	 * pass
	 */
	@Test
	public void getEnterpriseInfo(){
		String s = post(RESTurl.getEnterprise,entId+"");
		System.out.print("------------------------------"+s);
		//assertEquals(ErrorType.error500.toString(), "error500");
		EnterpriseDTO enterDTO = PojoMapper.fromJsonAsObject(s, EnterpriseDTO.class);
		assertEquals(enterDTO.getEnterpriseId(),1234);
		
	}
	/***
	 * 获取企业信息
	 * 修改企业信息
	 * pass
	 */
	@Test
	public void updateEnterpriseInfo(){
		String s = post(RESTurl.getEnterprise,entId+"");
		System.out.print("------------------------------"+s);
		EnterpriseDTO enterDTO = PojoMapper.fromJsonAsObject(s, EnterpriseDTO.class);
		assertEquals(enterDTO.getEnterpriseId(), 1234);
		
		enterDTO.setEnterpriseName("中华神风集团");
		
		String postData = PojoMapper.toJson(enterDTO);
		String r = post(RESTurl.updateEnterprise, postData);
		assertEquals(CommConstants.OK_MARK , r);
	}
	
	/***
	 * 获取企业所有部门的信息 
	 * pass
	 */
	@Test
	public void getDepartmentsList(){
		System.out.println("-------------------获取企业所有部门的信息-------------------");
		String s = post(RESTurl.getDepartments,entId+"");
		DepartmentsDTO departDTO = PojoMapper.fromJsonAsObject(s, DepartmentsDTO.class);
		//assertEquals(1259, departDTO.getDepartmentList());
	//	assertEquals("总裁办", departDTO.getName());
		System.out.println(departDTO.getDepartmentList());
		
	}
	/***
	 * 新建，添加部门
	 * pass
	 */
	@Test
	public void addDept(){
		
	}
	
	
	/***
	 * 获取部门详细信息
	 * 修改部门信息
	 * pass
	 */
	@Test
	public void getDepartmentInfo(){
		
		DepartmentDTO deptDTO = new DepartmentDTO();
		deptDTO.setName("性能测试部门"+new Random().nextInt(1000));
		deptDTO.setEnterpriseId(entId);
		String postData = PojoMapper.toJson(deptDTO);
		System.out.println(postData);
		String r = post(RESTurl.addDepartment, postData);
		//assertEquals(CommConstants.OK_MARK , r);
		System.out.println(r);
		
		DepartmentDTO testDTO = PojoMapper.fromJsonAsObject(r, DepartmentDTO.class);
		//assertEquals("性能测试部门" , testDTO.getName());
		assertNotNull(testDTO);
		
		idmap.put(DEPT_ID, testDTO.getDepartmentId());
		
		
		//获取
		String s = post(RESTurl.getDepartment,""+idmap.get(DEPT_ID));
		System.out.println("get=="+s);
		DepartmentDTO departDTO = PojoMapper.fromJsonAsObject(s, DepartmentDTO.class);
		//assertEquals(1259, departDTO.getDepartmentId());
		//assertEquals("性能测试部门", departDTO.getName());
		assertNotNull(testDTO);
		//修改
		departDTO.setName("性能测试部门niu");
		//String re = PojoMapper.toJson(departDTO);
		String r2= postData(RESTurl.updateDepartment, departDTO);
		assertEquals(CommConstants.OK_MARK , r2);
		
		//删除
		String d = postData(RESTurl.deleteDepartment, departDTO);
		assertEquals(CommConstants.OK_MARK , d);
		
	}
	
	
	
	/***
	 * 企业注册，判断重复注册
	 * pass by huhao
	 */
	@Test
	public void registeEnterprise(){
		System.out.println("------------------获取免费的key---------------");
		
		String freeKey = post(RESTurl.getFreeProductKey);
		System.out.println(freeKey);
		
		System.out.println("------------------企业注册，判断重复注册---------------");
		EnterpriseDTO enterDTO = new EnterpriseDTO();
		//enterDTO.setWebsite(website);
		enterDTO.setEnterpriseName("企业天虹112133");
		enterDTO.setContact("天虹大师");
		enterDTO.setMobile("15898552597");
		enterDTO.setAddress("深圳市南山区科技园");
		enterDTO.setProductKey(freeKey);
		enterDTO.setPhone("075588888888");
		enterDTO.setStatus("1");
		enterDTO.setPersonalDiskSize(10485760);
		enterDTO.setMaxShareLinkDownload(5242880);
		enterDTO.setShareLinkDownCount(100);
		
		enterDTO.setAdminName("Admin");
		
		EncryptModel encrypt = EncryptUtil.encrypt(enterDTO.getEnterpriseName(), "111111");
		enterDTO.setAdminPassword(encrypt.getPassword());
		enterDTO.setHashKey(encrypt.getHashKey());
		enterDTO.setNonce(encrypt.getNonce());
		
		
		String postData = PojoMapper.toJson(enterDTO);
		String r = post(RESTurl.registerEnterprise, postData);
		System.out.println(r);
		if("error500".equals(r)){
			fail(r);
		}
		
		
	}
	/***
	 * 获取免费的产品号
	 * pass
	 */
	@Test
	public void getFreeProductKey (){
		String r = post(RESTurl.getFreeProductKey);
		assertNotNull(r);
	}
	
	/***
	 * 获取下载速度限制
	 * pass
	 */
	@Test
	public void getUploadLimit  (){
		System.out.println("----------------获取下载速度限制--------------");
		String r = post(RESTurl.getUploadLimit, "961");
		//assertEquals(CommConstants.OK_MARK, r);
		System.out.println(r);
		LimitDTO limit = PojoMapper.fromJsonAsObject(r, LimitDTO.class);
		assertNotNull(limit);
	}
	/***
	 * 获取企业管理员登录后初始信息
	 */
	@Test
	public void getAdminData(){
		System.out.println("------------------获取企业管理员登录后初始信息------------------");
		String token2 = Security.CreateToken("1235");
		String r = post(RESTurl.getAdminData, token2);
		//String r = post(RESTurl.getAdminData, token);
		System.out.println("-------------------"+r);
		AdminDataDTO limit = PojoMapper.fromJsonAsObject(r,  AdminDataDTO.class);
		System.out.println(limit.getUserInfoDTO().getUserName());
	}
	
	/****
	 * 获取客服的信息
	 */
	@Test
	public void getCustomerSerivceInfo(){
		String r = post(RESTurl.getCustomerSerivceInfo, "21");
		System.out.println(r);
		UserInfoDTO u = PojoMapper.fromJsonAsObject(r, UserInfoDTO.class);
	}
	
	
	

}
