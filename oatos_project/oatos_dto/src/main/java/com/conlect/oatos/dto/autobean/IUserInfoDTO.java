package com.conlect.oatos.dto.autobean;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author jinkerjiang
 * 
 */
public interface IUserInfoDTO extends INonceDTO {

	long getUserId();

	void setUserId(long userId);

	String getUserName();

	void setUserName(String userName);

	String getRegisterMailAccount();

	void setRegisterMailAccount(String registerMailAccount);

	String getMailing7MailAccount();

	void setMailing7MailAccount(String mailing7MailAccount);

	String getGender();

	void setGender(String gender);

	Date getBirthday();

	void setBirthday(Date birthday);

	int getAge();

	void setAge(int age);

	Date getRegisterDate();

	void setRegisterDate(Date registerDate);

	String getIcon();

	void setIcon(String icon);

	String getSignature();

	void setSignature(String signature);

	String getPhone();

	void setPhone(String phone);

	boolean isEmailEnabled();

	void setEmailEnabled(boolean emailEnabled);

	String getNation();

	void setNation(String nation);

	String getProvince();

	void setProvince(String province);

	String getCity();

	void setCity(String city);

	String getOccupation();

	void setOccupation(String occupation);

	String getCollege();

	void setCollege(String college);

	String getHomePage();

	void setHomePage(String homePage);

	String getDistrict();

	void setDistrict(String district);

	String getDegree();

	void setDegree(String degree);

	String getAnimal();

	void setAnimal(String animal);

	String getStar();

	void setStar(String star);

	String getRealName();

	void setRealName(String realName);

	String getEngishName();

	void setEngishName(String engishName);

	String getBlood();

	void setBlood(String blood);

	boolean isPhoneEnabled();

	void setPhoneEnabled(boolean phoneEnabled);

	String getMobile();

	void setMobile(String mobile);

	String getMajor();

	void setMajor(String major);

	boolean isLocked();

	void setLocked(boolean locked);

	Long getEnterpriseId();

	void setEnterpriseId(Long enterpriseId);

	Long getDepartmentId();

	void setDepartmentId(Long departmentId);

	String getEmployeeNumber();

	void setEmployeeNumber(String employeeNumber);

	boolean isDownload();

	public void setDownload(boolean download);

	boolean isAccessExternal();

	void setAccessExternal(boolean accessExternal);

	boolean isCustomerService();

	void setCustomerService(boolean customerService);

	String getStatus();

	void setStatus(String status);

	int getUserType();

	void setUserType(int userType);

	long getDiskSize();

	void setDiskSize(long diskSize);

	String getOnlineStatus();

	void setOnlineStatus(String onlineStatus);

	@Override
	String getAgent();

	@Override
	void setAgent(String agent);

	String getPassword();

	void setPassword(String password);

	String getJobTitle();

	void setJobTitle(String jobTitle);

	/**
	 * @deprecated
	 */
	@Deprecated
	String getCloudDiskIp();

	/**
	 * @deprecated
	 */
	@Deprecated
	void setCloudDiskIp(String cloudDiskIp);

	List<IRoleDTO> getRoles();

	void setRoles(List<IRoleDTO> roles);

	boolean isBlocked();

	void setBlocked(boolean blocked);

	boolean isUsualContact();

	void setUsualContact(boolean usualContact);

	String getDepartmentName();

	void setDepartmentName(String departmentName);

	public String getHobby();

	public void setHobby(String hobby);

	public String getPayStatus();

	public void setPayStatus(String payStatus);

	public String getDeviceToken();

	public void setDeviceToken(String deviceToken);
}