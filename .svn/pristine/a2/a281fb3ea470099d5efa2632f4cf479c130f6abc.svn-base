package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.conlect.oatos.dto.status.UserStatus;

/**
 * 用户信息DTO
 * 
 * @author PeterGuo
 */
public class UserInfoDTO extends NonceDTO implements BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	public long userId;
	/**
	 * 用户名
	 */
	public String userName;
	/**
	 * 邮箱
	 */
	public String registerMailAccount;
	/**
	 * @deprecated
	 */
	@Deprecated
	public String mailing7MailAccount;
	/**
	 * 性别
	 */
	public String gender;
	/**
	 * 生日
	 */
	public Date birthday;
	/**
	 * 年龄
	 */
	public int age;
	/**
	 * 加入时间
	 */
	public Date registerDate;
	/**
	 * 头像
	 */
	public String icon;
	/**
	 * 签名
	 */
	public String signature;

	/**
	 * 国家
	 */
	public String nation;
	/**
	 * 省市
	 */
	public String province;
	/**
	 * 城市
	 */
	public String city;
	/**
	 * 区县
	 */
	public String district;
	/**
	 * 职业
	 */
	public String occupation;
	/**
	 * 毕业院校
	 */
	public String college;
	/**
	 * 学位
	 */
	public String degree;
	/**
	 * 生肖
	 */
	public String animal;
	/**
	 * 星座
	 */
	public String star;
	/**
	 * 真是姓名
	 */
	public String realName;
	/**
	 * 英文名
	 */
	public String engishName;
	/**
	 * 个人网站
	 */
	public String homePage;
	/**
	 * 血型
	 */
	public String blood;
	/**
	 * 专业
	 */
	public String major;

	/**
	 * 电话号码
	 */
	public String phone;
	/**
	 * 手机号码
	 */
	public String mobile;
	/**
	 * 邮箱通过验证
	 * 
	 * @deprecated
	 */
	@Deprecated
	public boolean emailEnabled;
	/**
	 * 手机通过验证
	 * 
	 * @deprecated
	 */
	@Deprecated
	public boolean phoneEnabled;
	/**
	 * 爱好
	 */
	private String hobby;

	/**
	 * 锁定
	 */
	private boolean locked = false;
	/**
	 * 企业ID
	 */
	private Long enterpriseId;
	/**
	 * 部门ID
	 */
	private Long departmentId;
	/**
	 * 工号
	 */
	private String employeeNumber;
	/**
	 * 下载文档
	 * 
	 * @deprecated
	 */
	@Deprecated
	private boolean download;
	/**
	 * 对外联系
	 * 
	 * @deprecated
	 */
	@Deprecated
	private boolean accessExternal;
	/**
	 * 客服
	 */
	private boolean customerService;
	/**
	 * 用户状态<br> {@link com.conlect.oatos.dto.status.EmployeeStatus}
	 */
	private String status;
	/**
	 * 职位
	 */
	private String jobTitle;

	/**
	 * 用户角色
	 */
	private List<RoleDTO> roles = new ArrayList<RoleDTO>();

	/**
	 * 用户类型
	 * 
	 * @see com.conlect.oatos.dto.status.UserType
	 */
	private int userType;
	/**
	 * 网盘大小
	 */
	private long diskSize;
	/**
	 * @deprecated
	 */
	@Deprecated
	private String cloudDiskIp;

	/**
	 * 用户在线状态
	 * 
	 * @see com.conlect.oatos.dto.status.UserStatus
	 */
	private String onlineStatus = UserStatus.Offline;

	/**
	 * 用户是否被加入阻止联系人
	 */
	private boolean blocked = false;

	/**
	 * 用户是否被加入常用联系人
	 */
	private boolean usualContact = false;

	/**
	 * 用户登录设备<br>
	 * 
	 * @see com.conlect.oatos.dto.status.UserAgent
	 */
	private String agent;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 部门名
	 */
	private String departmentName;
	/**
	 * 
	 */
	private String payStatus;

	/**
	 * 设备toekn （针对IOS）
	 */
	private String deviceToken;
	/**
	 * 
	 */
	private int contactDisplay;

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public UserInfoDTO() {
	}

	public UserInfoDTO(long userId, String userName,
			String registerMailAccount, String mailing7MailAccount,
			String gender, Date birthday, int age, Date registerDate,
			String icon, String signature, int contactDisplay) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.registerMailAccount = registerMailAccount;
		this.mailing7MailAccount = mailing7MailAccount;
		this.gender = gender;
		this.birthday = birthday;
		this.age = age;
		this.registerDate = registerDate;
		this.icon = icon;
		this.signature = signature;
		this.contactDisplay = contactDisplay;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"class\":\"").append(getClass().getName())
				.append("\",\"userId\":\"").append(userId)
				.append("\", \"userName\":\"").append(userName)
				.append("\", \"registerMailAccount\":\"")
				.append(registerMailAccount)
				.append("\", \"mailing7MailAccount\":\"")
				.append(mailing7MailAccount).append("\", \"gender\":\"")
				.append(gender).append("\", \"birthday\":\"").append(birthday)
				.append("\", \"age\":\"").append(age)
				.append("\", \"registerDate\":\"").append(registerDate)
				.append("\", \"icon\":\"").append(icon)
				.append("\", \"signature\":\"").append(signature).append("\"}");
		return builder.toString();
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRegisterMailAccount() {
		return registerMailAccount;
	}

	public void setRegisterMailAccount(String registerMailAccount) {
		this.registerMailAccount = registerMailAccount;
	}

	public String getMailing7MailAccount() {
		return mailing7MailAccount;
	}

	public void setMailing7MailAccount(String mailing7MailAccount) {
		this.mailing7MailAccount = mailing7MailAccount;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isEmailEnabled() {
		return emailEnabled;
	}

	public void setEmailEnabled(boolean emailEnabled) {
		this.emailEnabled = emailEnabled;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEngishName() {
		return engishName;
	}

	public void setEngishName(String engishName) {
		this.engishName = engishName;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public boolean isPhoneEnabled() {
		return phoneEnabled;
	}

	public void setPhoneEnabled(boolean phoneEnabled) {
		this.phoneEnabled = phoneEnabled;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public boolean isDownload() {
		return download;
	}

	public void setDownload(boolean download) {
		this.download = download;
	}

	public boolean isAccessExternal() {
		return accessExternal;
	}

	public void setAccessExternal(boolean accessExternal) {
		this.accessExternal = accessExternal;
	}

	public boolean isCustomerService() {
		return customerService;
	}

	public void setCustomerService(boolean customerService) {
		this.customerService = customerService;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public long getDiskSize() {
		return diskSize;
	}

	public void setDiskSize(long diskSize) {
		this.diskSize = diskSize;
	}

	public String getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	public String getCloudDiskIp() {
		return cloudDiskIp;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	public void setCloudDiskIp(String cloudDiskIp) {
		this.cloudDiskIp = cloudDiskIp;
	}

	@Override
	public String getAgent() {
		return agent;
	}

	@Override
	public void setAgent(String agent) {
		this.agent = agent;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public boolean isUsualContact() {
		return usualContact;
	}

	public void setUsualContact(boolean usualContact) {
		this.usualContact = usualContact;
	}

	public int getContactDisplay() {
		return contactDisplay;
	}

	public void setContactDisplay(int contactDisplay) {
		this.contactDisplay = contactDisplay;
	}

}
