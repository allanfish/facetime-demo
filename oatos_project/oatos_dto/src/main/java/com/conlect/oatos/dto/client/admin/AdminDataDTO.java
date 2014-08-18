package com.conlect.oatos.dto.client.admin;

import com.conlect.oatos.dto.client.BaseDTO;
import com.conlect.oatos.dto.client.EnterpriseDTO;
import com.conlect.oatos.dto.client.UserInfoDTO;

/**
 * 企业管理员登录后，取初始化数据DTO
 * 
 * @author yang
 * 
 */
public class AdminDataDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 企业信息
	 */
	private EnterpriseDTO enterpriseDTO;

	/**
	 * 用户信息
	 */
	private UserInfoDTO userInfoDTO;

	/**
	 * 管理员权限信息
	 */
	private AdminDTO adminDTO;

	public EnterpriseDTO getEnterpriseDTO() {
		return enterpriseDTO;
	}

	public void setEnterpriseDTO(EnterpriseDTO enterpriseDTO) {
		this.enterpriseDTO = enterpriseDTO;
	}

	public UserInfoDTO getUserInfoDTO() {
		return userInfoDTO;
	}

	public void setUserInfoDTO(UserInfoDTO userInfoDTO) {
		this.userInfoDTO = userInfoDTO;
	}

	public AdminDTO getAdminDTO() {
		return adminDTO;
	}

	public void setAdminDTO(AdminDTO adminDTO) {
		this.adminDTO = adminDTO;
	}

}
