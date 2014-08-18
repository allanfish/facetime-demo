package com.qycloud.oatos.server.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.client.CustomerDTO;
import com.conlect.oatos.dto.client.DepartmentDTO;
import com.conlect.oatos.dto.client.DepartmentsDTO;
import com.conlect.oatos.dto.client.EnterpriseDTO;
import com.conlect.oatos.dto.client.LimitDTO;
import com.conlect.oatos.dto.client.UserInfoDTO;
import com.conlect.oatos.dto.status.AdminToken;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.logic.EnterpriseLogic;
import com.qycloud.oatos.server.util.BllLogger;
import com.qycloud.oatos.server.util.LogicException;

/**
 * 企业服务
 * @author yang
 *
 */
@Controller("EnterpriseService")
public class EnterpriseService {

	private final static Logger logger = BllLogger.getLogger();

	@Autowired
	private EnterpriseLogic entLogic;

	/**
	 * 企业注册服务
	 * @param postData
	 *            企业注册数据传输对象
	 * @return OK
	 */
	@RequestMapping(value = RESTurl.registerEnterprise, method = RequestMethod.POST)
	public @ResponseBody
	String registerEnterprise(@RequestBody String postData) {
		String reBody = "";
		try {
			EnterpriseDTO entDTO = PojoMapper.fromJsonAsObject(postData, EnterpriseDTO.class);
			reBody = entLogic.registerEnterprise(entDTO);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;

	}

	/**
	 * 获取一个客户ID
	 * @param postData
	 * @return 客户临时ID
	 */
	@RequestMapping(value = RESTurl.createCustomerId, method = RequestMethod.POST)
	public @ResponseBody
	String createCustomeId(@RequestBody String postData) {
		String reBody = "";
		try {
			CustomerDTO customerDTO = PojoMapper.fromJsonAsObject(postData, CustomerDTO.class);
			reBody = entLogic.createCustomer(customerDTO.getIp());
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 获取客服的信息(客服是UserInfoDTO）
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.getCustomerSerivceInfo, method = RequestMethod.POST)
	public @ResponseBody
	String getCustomerSerivceInfo(@RequestBody String postData) {
		String reBody = "";
		try {
			UserInfoDTO customerService = entLogic.getEnterpriseCustomerService(Long.parseLong(postData));
			if (customerService != null) {
				reBody = PojoMapper.toJson(customerService);
			}
			else {
				reBody = CommConstants.NONE_MARK;
			}
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 判断企业名称是否已经注册过，注册过的返回 true。
	 * @param enterpriseName
	 * @return
	 */
	@RequestMapping(value = RESTurl.existEnterprise, method = RequestMethod.POST)
	public @ResponseBody
	String existEnterprise(@RequestBody String entName) {
		String reBody = "";
		try {
			 reBody = entLogic.existEnterprise(entName);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(entName, ex);
		}
		return reBody;
	}

	/**
	 * 批量新建部门
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.addDepartments, method = RequestMethod.POST)
	public @ResponseBody
	String addDepartments(@RequestBody String postData) {
		String reBody = "";
		try {
			DepartmentsDTO deptDtoList = PojoMapper.fromJsonAsObject(postData, DepartmentsDTO.class);
			reBody = entLogic.addDepartments(deptDtoList);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 获取企业所有部门的信息带有部门排序
	 * @param entpriseId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getDepartments, method = RequestMethod.POST)
	public @ResponseBody
	String getDepartments(@RequestBody String postData) {
		String reBody = "";
		try {
			DepartmentsDTO deptList = new DepartmentsDTO();
			deptList.setDepartmentList(entLogic.getDepartmentsByEntId(Long.parseLong(postData)));
			reBody = PojoMapper.toJson(deptList);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 获取企业信息
	 * @param entId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getEnterprise, method = RequestMethod.POST)
	public @ResponseBody
	String getEnterprise(@RequestBody String entId) {
		String reBody = "";
		try {
			EnterpriseDTO ent = entLogic.getEnterpriseById(Long.parseLong(entId));
			reBody = PojoMapper.toJson(ent);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(entId, ex);
		}
		return reBody;
	}

	/**
	 * 获取部门信息
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getDepartment, method = RequestMethod.POST)
	public @ResponseBody
	String getDepartment(@RequestBody String departmentId) {
		String reBody = "";
		try {
			DepartmentDTO dept = entLogic.getDepartmentById(departmentId);
			reBody = PojoMapper.toJson(dept);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(departmentId, ex);
		}
		return reBody;
	}

	/**
	 * 修改部门信息
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateDepartment, method = RequestMethod.POST)
	public @ResponseBody
	String updateDepartment(@RequestBody String postData) {
		String reBody = "";
		try {
			DepartmentDTO dept = PojoMapper.fromJsonAsObject(postData, DepartmentDTO.class);
			reBody = entLogic.updateDepartment(dept);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 删除部门
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.deleteDepartment, method = RequestMethod.POST)
	public @ResponseBody
	String deleteDepartment(@RequestBody String postData) {
		String reBody = "";
		try {
			DepartmentDTO dept = PojoMapper.fromJsonAsObject(postData, DepartmentDTO.class);
			reBody = entLogic.deleteDepartment(dept);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 修改企业信息
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateEnterprise, method = RequestMethod.POST)
	public @ResponseBody
	String updateEnterprise(@RequestBody String postData) {
		String response = "";
		try {
			EnterpriseDTO entDTO = PojoMapper.fromJsonAsObject(postData, EnterpriseDTO.class);
			response = entLogic.updateEnterprise(entDTO);
		}
		catch (Exception ex) {
			response = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return response;
	}

	/**
	 * 插入 ProductKey
	 * @param requestBody
	 * @return 客户临时ID
	 */
	@RequestMapping(value = RESTurl.generatorProductKey, method = RequestMethod.POST)
	public @ResponseBody
	String generatorProductKey(@RequestBody String sales) {
		String reBody = "";
		try {
			String[] arr = sales.split(",");
			reBody = entLogic.generatorProductKey(arr[0], Integer.parseInt(arr[1]));
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(sales, ex);
		}
		return reBody;
	}

	/**
	 * 获取免费的产品号
	 * @return
	 */
	@RequestMapping(value = RESTurl.getFreeProductKey, method = RequestMethod.POST)
	public @ResponseBody
	String getFreeProductKey() {
		String reBody = "";
		try {
			reBody = entLogic.getFreeProductKey();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error("", ex);
		}
		return reBody;
	}

	/**
	 * 修改企业产品序列号
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.registerProductKey, method = RequestMethod.POST)
	public @ResponseBody
	String registerProductKey(@RequestBody String postData) {
		String reBody = "";
		try {
			EnterpriseDTO enterpriseDTO = PojoMapper.fromJsonAsObject(postData, EnterpriseDTO.class);
			reBody = entLogic.registerProductKey(enterpriseDTO);
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error("", ex);
		}
		return reBody;
	}

	/**
	 * 判断企业产品序列号激活状态
	 * @param entId
	 * @return
	 */
	@RequestMapping(value = RESTurl.checkProductKey, method = RequestMethod.POST)
	public @ResponseBody
	String checkProductKey(@RequestBody String entId) {
		String reBody = "";
		try {
			reBody = entLogic.checkProductKey(Long.parseLong(entId));
		}
		catch (LogicException ex) {
			reBody = ex.getError().name();
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error("", ex);
		}
		return reBody;
	}

	/**
	 * 获取下载速度限制
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = RESTurl.getUploadLimit, method = RequestMethod.POST)
	public @ResponseBody
	String getUploadLimit(@RequestBody String userId) {
		String reBody = "";
		try {
			LimitDTO limitDTO = entLogic.getUploadLimit(Long.parseLong(userId));
			reBody = PojoMapper.toJson(limitDTO);
		}
		catch (Exception ex) {
			logger.error(userId, ex);
			reBody = ErrorType.error500.name();
		}
		return reBody;
	}

	/**
	 * 新建部门
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = RESTurl.addDepartment, method = RequestMethod.POST)
	public @ResponseBody
	String addDepartment(@RequestBody String postData) {
		String reBody = "";
		try {
			DepartmentDTO deptDto = PojoMapper.fromJsonAsObject(postData, DepartmentDTO.class);
			reBody = entLogic.addDepartment(deptDto);
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(postData, ex);
		}
		return reBody;
	}

	/**
	 * 获取企业管理员登录后初始信息（企业信息，管理员信息）
	 * @param token
	 * @return
	 */
	@RequestMapping(value = RESTurl.getAdminData, method = RequestMethod.POST)
	public @ResponseBody String getAdminData(@RequestBody String token) {
		String reBody = "";
		try {
			reBody = entLogic.getAdminData(new AdminToken(token));
		}
		catch (Exception ex) {
			reBody = ErrorType.error500.name();
			logger.error(token, ex);
		}
		return reBody;
	}

	/**
	 * 更新部门排序
	 * 
	 * @param enterpriseName
	 * @return
	 */
	@RequestMapping(value = RESTurl.updateDepartmentsLevel, method = RequestMethod.POST)
	public @ResponseBody
	String updateDepartmentsLevel(@RequestBody String strDepartmentsDTO) {
		String responseBody = "";
		try {
			DepartmentsDTO depts = PojoMapper.fromJsonAsObject(strDepartmentsDTO, DepartmentsDTO.class);
			responseBody = entLogic.updateDepartmentLevels(depts);
		}
		catch (Exception ex) {
			responseBody = ErrorType.error500.name();
			logger.error(strDepartmentsDTO, ex);
		}

		return responseBody;
	}
	
}
