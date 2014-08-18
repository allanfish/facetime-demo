package com.conlect.oatos.dto.autobean;

public interface IEnterpriseUserDTO extends IRegisterDTO {

	long getEnterpriseId();

	void setEnterpriseId(long enterpriseId);

	long getDepartmentId();

	void setDepartmentId(long departmentId);

	String getEmployeeNumber();

	void setEmployeeNumber(String employeeNumber);

	String getGender();

	void setGender(String gender);

	String getEnterpriseName();

	void setEnterpriseName(String enterpriseName);

	String getUserIcon();

	void setUserIcon(String userIcon);

	String getJobTitle();

	void setJobTitle(String jobTitle);

	String getLocale();

	void setLocale(String locale);

	boolean isNet();

	void setNet(boolean net);

	public abstract void setDepartmentName(String departmentName);

	public abstract String getDepartmentName();

	public abstract void setUserId(Long userId);

	public abstract Long getUserId();

	public abstract void setRoleName(String roleName);

	public abstract String getRoleName();

	public abstract void setCustomerService(boolean isCustomerService);

	public abstract boolean isCustomerService();

	public abstract void setRoleId(long roleId);

	public abstract long getRoleId();

	public abstract void setRealName(String realName);

	public abstract String getRealName();
}
