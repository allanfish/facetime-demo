package com.conlect.oatos.dto.client.user;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.autobean.user.IUserImportDTO;

/**
 * 用户导入DTO
 * 
 * @author YUFEI
 * @created 2012-10-31
 * @IDE eclipse
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserImportDTO implements IUserImportDTO {

	private static final long serialVersionUID = 8539791076487455382L;
	private String name;
	private String realName;
	private String email;
	private String password;
	private String sex;
	private String departmentName;
	private long departmentId;
	private String jobNum;
	private String jobTitle;
	private String joinDate;
	/**
	 * 是否是客服
	 */
	private String isCS;
	private String roleName;
	private long roleId;
	private boolean checkFail;
	private String failReason;

	@Override
	public String getRealName() {
		return realName;
	}

	@Override
	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Override
	public long getDepartmentId() {
		return departmentId;
	}

	@Override
	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public long getRoleId() {
		return roleId;
	}

	@Override
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Override
	public boolean isCheckFail() {
		return checkFail;
	}

	@Override
	public void setCheckFail(boolean checkFail) {
		this.checkFail = checkFail;
	}

	@Override
	public String getFailReason() {
		return failReason;
	}

	@Override
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getJobTitle() {
		return jobTitle;
	}

	@Override
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@Override
	public String getIsCS() {
		return isCS;
	}

	@Override
	public void setIsCS(String isCS) {
		this.isCS = isCS;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getSex() {
		return sex;
	}

	@Override
	public void setSex(String sex) {
		this.sex = sex;
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
	public String getJobNum() {
		return jobNum;
	}

	@Override
	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}

	@Override
	public String getJoinDate() {
		return joinDate;
	}

	@Override
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String getRoleName() {
		return roleName;
	}

	@Override
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
