package com.conlect.oatos.dto.client.user;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.autobean.IUserInfoDTO;
import com.conlect.oatos.dto.autobean.user.IPersonDiskAllocDTO;

/**
 * 个人网盘分配DTO
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PersonDiskAllocDTO implements IPersonDiskAllocDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long userId;
	private String username;
	private String realname;
	private String departmentName;
	private long originalDiskSize;
	private int originalAddSize;
	private int addSize;

	public PersonDiskAllocDTO() {
		super();
	}

	public PersonDiskAllocDTO(IUserInfoDTO u) {
		this.userId = u.getUserId();
		this.username = u.getUserName();
		this.realname = u.getRealName();
		this.originalDiskSize = u.getDiskSize();
	}

	@Override
	public int getOriginalAddSize() {
		return originalAddSize;
	}

	@Override
	public void setOriginalAddSize(int originalAddSize) {
		this.originalAddSize = originalAddSize;
	}

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getRealname() {
		return realname;
	}

	@Override
	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Override
	public String getDepartmentName() {
		return departmentName;
	}

	@Override
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public long getOriginalDiskSize() {
		return originalDiskSize;
	}

	@Override
	public void setOriginalDiskSize(long originalDiskSize) {
		this.originalDiskSize = originalDiskSize;
	}

	@Override
	public int getAddSize() {
		return addSize;
	}

	@Override
	public void setAddSize(int addSize) {
		this.addSize = addSize;
	}

}
