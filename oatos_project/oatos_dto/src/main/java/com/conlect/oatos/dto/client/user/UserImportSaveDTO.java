package com.conlect.oatos.dto.client.user;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.client.BaseDTO;
import com.conlect.oatos.dto.client.EnterpriseUserDTO;

/**
 * 用户导入DTO
 * 
 * @author huhao
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserImportSaveDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 企业ID
	 */
	private long entId;
	/**
	 * 企业用户列表
	 */
	private List<EnterpriseUserDTO> userList = new ArrayList<EnterpriseUserDTO>();

	public long getEntId() {
		return entId;
	}

	public void setEntId(long entId) {
		this.entId = entId;
	}

	public List<EnterpriseUserDTO> getUserList() {
		return userList;
	}

	public void setUserList(List<EnterpriseUserDTO> userList) {
		this.userList = userList;
	}

}
