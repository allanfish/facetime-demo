package com.qycloud.oatos.server.test;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.EmployeeStatus;
import com.conlect.oatos.dto.status.EnterpriseStatus;
import com.conlect.oatos.dto.status.UserType;
import com.conlect.oatos.utils.DateUtils;
import com.qycloud.oatos.server.dao.EnterpriseMapper;
import com.qycloud.oatos.server.dao.LoginMapper;
import com.qycloud.oatos.server.dao.UserMapper;
import com.qycloud.oatos.server.domain.entity.Enterprise;
import com.qycloud.oatos.server.domain.entity.Login;
import com.qycloud.oatos.server.domain.entity.User;
import com.qycloud.oatos.server.domain.logic.SequenceLogic;
import com.qycloud.oatos.server.security.Security;

/**
 * base test class
 * 
 * @author yang
 * 
 */
@SpringApplicationContext({ "oatos-servlet.xml" })
public class BaseTest extends UnitilsJUnit4 {

	@SpringBeanByType
	protected SequenceLogic sequence;

	@SpringBeanByType
	protected EnterpriseMapper enterpriseMapper;

	@SpringBeanByType
	protected UserMapper userMapper;

	@SpringBeanByType
	protected LoginMapper loginMapper;

	protected Enterprise enterprise;

	protected User user;

	@Before
	public void before() {

		// initialize enterprise
		enterprise = new Enterprise();
		enterprise.setEnterpriseId(sequence.getNextId());
		enterprise.setEnterpriseName("qycloud-test");
		enterprise.setPhone("400-030-1108");
		enterprise.setFax("0755-88888888");
		enterprise.setAddress("深圳市宝安区留仙一路高新奇战略新兴产业园1号楼2层");
		enterprise.setPostcode("518100");
		enterprise.setWebsite("http://www.oatos.com");
		enterprise.setAdminPassword(Security.CreatePassword(Security
				.SHA256("123456")));
		enterprise.setMaxEmployees(1000);
		enterprise.setEmployeePassword(Security.SHA256("123456"));
		enterprise.setDiskSize(CommConstants.ENTERPRISE_DISK_SIZE);
		enterprise.setRegisterTime(new Date());
		enterprise.setExpirationTime(DateUtils.addMonths(
				enterprise.getRegisterTime(), 3));
		enterprise.setMobile("12345678900");
		enterprise.setMail("oatos@qycloud.com");
		enterprise.setContact("yang");
		enterprise.setPersonalDiskSize(enterprise.getMaxEmployees()
				* CommConstants.PERSONAL_DISK_SIZE);
		enterprise.setMaxShareLinkDownload(CommConstants.SHARE_LINK_DOWN_SIZE);
		enterprise.setStatus(EnterpriseStatus.Free);

		enterpriseMapper.addEnterprise(enterprise);

		// initialize user
		user = new User();
		user.setUserId(sequence.getNextId());
		user.setEnterpriseId(enterprise.getEnterpriseId());
		user.setUserType(UserType.Administrator);
		user.setUserName(CommConstants.Administrator);
		user.setDepartmentId(null);
		user.setDiskSize(CommConstants.PERSONAL_DISK_SIZE);
		user.setRegisterMail("admin@qycloud.com");
		user.setUserMobile("12345678900");
		user.setUserPhone("0755-25468209");
		user.setUserLable("hello");
		user.setUserAge(18);
		user.setRegistTime(new Date());
		user.setCustomerService(true);
		user.setJobTitle(CommConstants.Administrator);
		user.setStatus(EmployeeStatus.Active);
		userMapper.addUser(user);

		// login
		Login login = new Login();
		login.setLoginId(sequence.getNextId());
		login.setUserId(user.getUserId());
		login.setEntName(enterprise.getEnterpriseName());
		login.setAccount(CommConstants.Administrator);
		login.setPassword(enterprise.getAdminPassword());
		loginMapper.addLogin(login);
	}

	@After
	public void after() {
		// delete enterprise
		enterpriseMapper.deleteEnterprise(enterprise.getEnterpriseId());
		// delete user
		userMapper.deleteUser(user.getUserId());
	}

	@Test
	public void testSequence() {
		assertNotNull(sequence.getNextId());
	}
}
