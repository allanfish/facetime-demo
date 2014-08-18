package com.qycloud.oatos.server.test.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.conlect.oatos.dto.client.admin.AdminDTO;
import com.conlect.oatos.dto.client.admin.AdminsDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.EmployeeStatus;
import com.conlect.oatos.dto.status.UserType;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.entity.Login;
import com.qycloud.oatos.server.domain.entity.User;
import com.qycloud.oatos.server.domain.logic.AdminLogic;
import com.qycloud.oatos.server.security.Security;
import com.qycloud.oatos.server.test.BaseTest;

/**
 * AdminLogic test
 * @author yang
 *
 */
public class AdminLogicTest extends BaseTest {

	@SpringBeanByType
	private AdminLogic adminLogic;
	
	private User tmpUser;

	@Before
	public void before() {
		super.before();
		
		// initialize user
		tmpUser = new User();
		tmpUser.setUserId(sequence.getNextId());
		tmpUser.setEnterpriseId(enterprise.getEnterpriseId());
		tmpUser.setUserType(UserType.BusinessUser);
		tmpUser.setUserName("qycloud-test-1");
		tmpUser.setDepartmentId(null);
		tmpUser.setDiskSize(CommConstants.PERSONAL_DISK_SIZE);
		tmpUser.setRegisterMail("admin@qycloud.com");
		tmpUser.setUserMobile("12345678900");
		tmpUser.setUserPhone("0755-25468209");
		tmpUser.setUserLable("hello");
		tmpUser.setUserAge(18);
		tmpUser.setRegistTime(new Date());
		tmpUser.setCustomerService(true);
		tmpUser.setJobTitle("test-1");
		tmpUser.setStatus(EmployeeStatus.Active);
		userMapper.addUser(tmpUser);

		// login
		Login login = new Login();
		login.setLoginId(sequence.getNextId());
		login.setUserId(tmpUser.getUserId());
		login.setEntName(enterprise.getEnterpriseName());
		login.setAccount(tmpUser.getUserName());
		login.setPassword(Security.CreatePassword(Security.SHA256("123456")));
		loginMapper.addLogin(login);
	}
	
	@After
	public void after() {
		super.after();
		userMapper.deleteUser(tmpUser.getUserId());
	}
	
	@Test
	public void getAdminsByEntId() {
		AdminsDTO adminsDTO = adminLogic.getAdminsByEntId(enterprise.getEnterpriseId());
		System.out.println(PojoMapper.toJson(adminsDTO));
	}
	
	/**
	 * 添加二级管理员 
	 */
	@Test
	public void addAdmins(){
		AdminDTO admin = new AdminDTO();
		admin.setUserId(tmpUser.getUserId());
		admin.setEnterpriseId(enterprise.getEnterpriseId());
		admin.setSetRole(true);
		
		List<AdminDTO> adminDTOs = new ArrayList<AdminDTO>();
		adminDTOs.add(admin);
		adminLogic.addAdmins(adminDTOs);
	}
	
	
	/**
	 * 修改二级管理员权限
	 */
	@Test
	public void updateAdmins(){
		AdminDTO admin = new AdminDTO();
		admin.setUserId(tmpUser.getUserId());
		admin.setEnterpriseId(enterprise.getEnterpriseId());
		admin.setSetRole(true);
		admin.setCreateDept(true);

		List<AdminDTO> adminDTOs  = new ArrayList<AdminDTO>();
		adminDTOs.add(admin);
		adminLogic.updateAdmins(adminDTOs);
	}
	
	/**
	 * 删除二级管理员
	 */
	@Test
	public void deleteAdmins(){
		AdminDTO admins = new AdminDTO();
		admins.setUserId(tmpUser.getUserId());
		admins.setEnterpriseId(enterprise.getEnterpriseId());
		admins.setCreateDept(true);
		adminLogic.deleteAdmin(admins);
	}
}
