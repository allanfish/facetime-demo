package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.conlect.oatos.dto.client.UserInfoDTO;

/**
 * 用户
 * @author yang
 */
public class User implements Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 用户登录帐号
	 */
	private String userName;
	/**
	 * oatos邮箱
	 * @deprecated
	 */
	private String conlectMail;
	/**
	 * 邮箱地址
	 */
	private String registerMail;
	/**
	 * 电话
	 */
	private String userPhone;
	/**
	 * 签名
	 */
	private String userLable;
	/**
	 * 头像
	 */
	private String userHeaderPhoto;
	/**
	 * 性别
	 */
	private String userSex;
	/**
	 * 年龄
	 */
	private Integer userAge;
	/**
	 * 生日
	 */
	private Date userBithday;
	/**
	 * 国籍
	 */
	private String userCountry;
	/**
	 * 省份
	 */
	private String userProvince;
	/**
	 * 城市
	 */
	private String userCity;
	/**
	 * 毕业学校
	 */
	private String userSchool;
	/**
	 * 血型
	 */
	private String userBloodType;
	/**
	 * 手机
	 */
	private String userMobile;
	/**
	 * 职业
	 */
	private String userOccupation;
	/**
	 * 个人网站
	 */
	private String userPage;
	/**
	 * 用户类型<br>
	 * {@link com.conlect.oatos.dto.status.UserType}
	 */
	private Integer userType;
	/**
	 * 用户添加时间
	 */
	private Date registTime;
	/**
	 * 个人网盘空间
	 */
	private long diskSize;
	/**
	 * 个人网盘剩余容量
	 * @deprecated
	 */
	private long diskSpace;

	/**
	 * 地区
	 */
	private String district;
	/**
	 * 学位
	 */
	private String degree;
	/**
	 * 生肖
	 */
	private String animal;
	/**
	 * 星座
	 */
	private String star;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 英文名
	 */
	private String engishName;
	/**
	 * 专业
	 */
	private String major;

	/**
	 * 启用邮箱登陆
	 * @deprecated
	 */
	private boolean emailEnabled = false;
	/**
	 * 启用手机号登录
	 * @deprecated
	 */
	private boolean phoneEnabled = false;

	/**
	 * 是否锁定
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
	 * 是否可以下载文件
	 * @deprecated
	 */
	private boolean download;
	/**
	 * 是否可以与外部建立联系
	 * @deprecated
	 */
	private boolean accessExternal;
	/**
	 * 是否为客服
	 */
	private boolean customerService;
	/**
	 * 状态<br>
	 * {@link com.conlect.oatos.dto.status.EmployeeStatus}
	 */
	private String status;
	/**
	 * 职位
	 */
	private String jobTitle;

	/**
	 * 爱好
	 */
	private String hobby;
	/**
	 * 支付状态
	 */
	private String payStatus;
	
	/**
	 * 设备的token(对IOS专用)
	 */
	private String deviceToken;
	
	/**
	 * 常联系人显示设置 0:为同事和联系人都显示，1：为显示同事，2：为显示常用联系人
	 */
	private int contactDisplay;

	public User() {
	}

	/**
	 * Constructs ...
	 * 
	 * 
	 * @param userId
	 */
	public User(long userId) {
		this.userId = userId;
	}

	/**
	 * 
	 * @param userInfoDTO
	 */
	public User(UserInfoDTO userInfoDTO) {
		userId = userInfoDTO.getUserId();
		userName = userInfoDTO.getUserName();
		registerMail = userInfoDTO.getRegisterMailAccount();
		conlectMail = userInfoDTO.getMailing7MailAccount();
		userSex = userInfoDTO.getGender();
		userBithday = userInfoDTO.getBirthday();
		userAge = userInfoDTO.getAge();
		registTime = userInfoDTO.getRegisterDate();
		userHeaderPhoto = userInfoDTO.getIcon();
		userLable = userInfoDTO.getSignature();
		userCountry = userInfoDTO.getNation();
		userProvince = userInfoDTO.getProvince();
		userCity = userInfoDTO.getCity();
		district = userInfoDTO.getDistrict();
		userOccupation = userInfoDTO.getOccupation();
		userSchool = userInfoDTO.getCollege();
		degree = userInfoDTO.getDegree();
		animal = userInfoDTO.getAnimal();
		star = userInfoDTO.getStar();
		realName = userInfoDTO.getRealName();
		engishName = userInfoDTO.getEngishName();
		userPage = userInfoDTO.getHomePage();
		userBloodType = userInfoDTO.getBlood();
		major = userInfoDTO.getMajor();
		userPhone = userInfoDTO.getPhone();
		userMobile = userInfoDTO.getMobile();
		emailEnabled = userInfoDTO.isEmailEnabled();
		phoneEnabled = userInfoDTO.isPhoneEnabled();
		locked = userInfoDTO.isLocked();
		enterpriseId = userInfoDTO.getEnterpriseId() != null
				&& userInfoDTO.getEnterpriseId() > 0 ? userInfoDTO
				.getEnterpriseId() : null;
		departmentId = userInfoDTO.getDepartmentId() != null
				&& userInfoDTO.getDepartmentId() > 0 ? userInfoDTO
				.getDepartmentId() : null;
		employeeNumber = userInfoDTO.getEmployeeNumber();
		download = userInfoDTO.isDownload();
		accessExternal = userInfoDTO.isAccessExternal();
		customerService = userInfoDTO.isCustomerService();
		status = userInfoDTO.getStatus();
		userType = userInfoDTO.getUserType();
		diskSize = userInfoDTO.getDiskSize();
		jobTitle = userInfoDTO.getJobTitle();
		hobby = userInfoDTO.getHobby();
		payStatus = userInfoDTO.getPayStatus();
		contactDisplay = userInfoDTO.getContactDisplay();
	}

	public UserInfoDTO toUserInfoDTO() {
		UserInfoDTO user = new UserInfoDTO();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setRegisterMailAccount(registerMail);
		user.setMailing7MailAccount(conlectMail);
		user.setGender(userSex);
		user.setBirthday(userBithday);
		if (userAge != null) {
			user.setAge(userAge);
		} else {
			user.setAge(-1);
		}
		user.setRegisterDate(registTime);
		user.setIcon(userHeaderPhoto);
		if (userLable != null) {
			user.setSignature(userLable);
		} else {
			user.setSignature("");
		}
		user.setNation(userCountry);
		user.setProvince(userProvince);
		user.setCity(userCity);
		user.setDistrict(district);
		user.setOccupation(userOccupation);
		user.setCollege(userSchool);
		user.setDegree(degree);
		user.setAnimal(animal);
		user.setStar(star);
		user.setRealName(realName);
		user.setEngishName(engishName);
		user.setHomePage(userPage);
		user.setBlood(userBloodType);
		user.setMajor(major);
		user.setPhone(userPhone);
		user.setMobile(userMobile);
		user.setEmailEnabled(emailEnabled);
		user.setPhoneEnabled(phoneEnabled);
		user.setLocked(locked);
		user.setEnterpriseId(enterpriseId);
		user.setDepartmentId(departmentId);
		user.setEmployeeNumber(employeeNumber);
		user.setDownload(download);
		user.setAccessExternal(accessExternal);
		user.setCustomerService(customerService);
		user.setStatus(status);
		if (userType != null) {
			user.setUserType(userType);
		}
		user.setDiskSize(diskSize);
		user.setJobTitle(jobTitle);
		user.setHobby(hobby);
		user.setPayStatus(payStatus);
		user.setContactDisplay(contactDisplay);
		return user;
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

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getConlectMail() {
		return conlectMail;
	}

	public void setConlectMail(String conlectMail) {
		this.conlectMail = conlectMail;
	}

	public String getRegisterMail() {
		return registerMail;
	}

	public void setRegisterMail(String registerMail) {
		this.registerMail = registerMail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserLable() {
		return userLable;
	}

	public void setUserLable(String userLable) {
		this.userLable = userLable;
	}

	public String getUserHeaderPhoto() {
		return userHeaderPhoto;
	}

	public void setUserHeaderPhoto(String userHeaderPhoto) {
		this.userHeaderPhoto = userHeaderPhoto;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public Date getUserBithday() {
		return userBithday;
	}

	public void setUserBithday(Date userBithday) {
		this.userBithday = userBithday;
	}

	public String getUserCountry() {
		return userCountry;
	}

	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}

	public String getUserProvince() {
		return userProvince;
	}

	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserSchool() {
		return userSchool;
	}

	public void setUserSchool(String userSchool) {
		this.userSchool = userSchool;
	}

	public String getUserBloodType() {
		return userBloodType;
	}

	public void setUserBloodType(String userBloodType) {
		this.userBloodType = userBloodType;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserOccupation() {
		return userOccupation;
	}

	public void setUserOccupation(String userOccupation) {
		this.userOccupation = userOccupation;
	}

	public String getUserPage() {
		return userPage;
	}

	public void setUserPage(String userPage) {
		this.userPage = userPage;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public long getDiskSize() {
		return diskSize;
	}

	public void setDiskSize(long diskSize) {
		this.diskSize = diskSize;
	}

	public long getDiskSpace() {
		return diskSpace;
	}

	public void setDiskSpace(long diskSpace) {
		this.diskSpace = diskSpace;
	}

	public boolean isEmailEnabled() {
		return emailEnabled;
	}

	public void setEmailEnabled(boolean emailEnabled) {
		this.emailEnabled = emailEnabled;
	}

	public boolean isPhoneEnabled() {
		return phoneEnabled;
	}

	public void setPhoneEnabled(boolean phoneEnabled) {
		this.phoneEnabled = phoneEnabled;
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

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * @return the deviceToken
	 */
	public String getDeviceToken() {
		return deviceToken;
	}

	/**
	 * @param deviceToken the deviceToken to set
	 */
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the contactDisplay
	 */
	public int getContactDisplay() {
		return contactDisplay;
	}

	/**
	 * @param contactDisplay the contactDisplay to set
	 */
	public void setContactDisplay(int contactDisplay) {
		this.contactDisplay = contactDisplay;
	}



}
