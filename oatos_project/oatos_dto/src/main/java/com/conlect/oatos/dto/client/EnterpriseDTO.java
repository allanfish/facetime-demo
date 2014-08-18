package com.conlect.oatos.dto.client;

import java.util.Date;

import com.conlect.oatos.dto.client.user.EntModuleDTO;

/**
 * 企业信息 DTO
 * 
 * @author yang
 * 
 */
public class EnterpriseDTO extends NonceDTO {

	public static final long serialVersionUID = 1L;

	/**
	 * 企业ID
	 */
	private long enterpriseId;
	/**
	 * 企业名
	 */
	private String enterpriseName;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 传真
	 */
	private String fax;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 邮编
	 */
	private String postcode;
	/**
	 * 网址
	 */
	private String website;
	/**
	 * 超级管理员登录密码，企业注册时使用
	 */
	private String adminPassword;
	/**
	 * 最大用户数
	 */
	private int maxEmployees;
	/**
	 * 商标
	 */
	private String logo;
	/**
	 * 自定义皮肤
	 */
	private String skin;
	/**
	 * 添加默认员工密码,sha256密文
	 */
	private String employeePassword;
	/**
	 * 产品key
	 */
	private String productKey;
	/**
	 * @deprecated
	 */
	@Deprecated
	private String cloudDiskIp;

	/**
	 * 企业网盘大小，单位KB
	 */
	private long diskSize;

	/**
	 * 注册时间
	 */
	private Date registerTime;

	/**
	 * 到期时间
	 */
	private Date expirationTime;

	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 邮箱地址
	 */
	private String mail;
	/**
	 * 联系人
	 */
	private String contact;

	/**
	 * 企业网盘版本号
	 * 
	 * @deprecated
	 */
	@Deprecated
	private long shareDiskVersion;

	/**
	 * 个人网盘大下，单位KB
	 */
	private long personalDiskSize;
	/**
	 * 个人网盘已分配大小，单位KB
	 */
	private long personDiskUsed;

	/**
	 * 外链最大下载流量，单位KB
	 */
	private long maxShareLinkDownload;
	/**
	 * 当前外链下载已使用流量，单位KB
	 */
	private long shareLinkDownCount;
	/**
	 * 企业服务状态<br> {@link com.conlect.oatos.dto.status.EnterpriseStatus}
	 */
	private String status;

	/**
	 * 语言
	 */
	private String locale;
	/**
	 * @deprecated
	 */
	@Deprecated
	private boolean net;
	/**
	 * 支付状态
	 */
	private String payStatus;

	/**
	 * 企业模块DTO
	 */
	private EntModuleDTO entModule;

	/**
	 * 部门排序是否已激活
	 */
	private boolean orderEnabled = false;

	/**
	 * 超级管理员用户名，企业注册时使用
	 */
	private String adminName;

	public boolean isOrderEnabled() {
		return orderEnabled;
	}

	public void setOrderEnabled(boolean orderEnabled) {
		this.orderEnabled = orderEnabled;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public long getPersonDiskUsed() {
		return personDiskUsed;
	}

	public void setPersonDiskUsed(long personDiskUsed) {
		this.personDiskUsed = personDiskUsed;
	}

	public EntModuleDTO getEntModule() {
		return entModule;
	}

	public void setEntModule(EntModuleDTO entModule) {
		this.entModule = entModule;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public int getMaxEmployees() {
		return maxEmployees;
	}

	public void setMaxEmployees(int maxEmployees) {
		this.maxEmployees = maxEmployees;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conlect.oatos.dto.client.IEnterpriseDTO#getEnterpriseId()
	 */
	public long getEnterpriseId() {
		return enterpriseId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conlect.oatos.dto.client.IEnterpriseDTO#setEnterpriseId(long)
	 */
	public void setEnterpriseId(long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conlect.oatos.dto.client.IEnterpriseDTO#getEnterpriseName()
	 */
	public String getEnterpriseName() {
		return enterpriseName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.conlect.oatos.dto.client.IEnterpriseDTO#setEnterpriseName(java.lang
	 * .String)
	 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conlect.oatos.dto.client.IEnterpriseDTO#getPhone()
	 */
	public String getPhone() {
		return phone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.conlect.oatos.dto.client.IEnterpriseDTO#setPhone(java.lang.String)
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conlect.oatos.dto.client.IEnterpriseDTO#getFax()
	 */
	public String getFax() {
		return fax;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conlect.oatos.dto.client.IEnterpriseDTO#setFax(java.lang.String)
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conlect.oatos.dto.client.IEnterpriseDTO#getAddress()
	 */
	public String getAddress() {
		return address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.conlect.oatos.dto.client.IEnterpriseDTO#setAddress(java.lang.String)
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conlect.oatos.dto.client.IEnterpriseDTO#getPostcode()
	 */
	public String getPostcode() {
		return postcode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.conlect.oatos.dto.client.IEnterpriseDTO#setPostcode(java.lang.String)
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conlect.oatos.dto.client.IEnterpriseDTO#getWebsite()
	 */
	public String getWebsite() {
		return website;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.conlect.oatos.dto.client.IEnterpriseDTO#setWebsite(java.lang.String)
	 */
	public void setWebsite(String website) {
		this.website = website;
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

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public long getDiskSize() {
		return diskSize;
	}

	public void setDiskSize(long diskSize) {
		this.diskSize = diskSize;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public long getShareDiskVersion() {
		return shareDiskVersion;
	}

	public void setShareDiskVersion(long shareFileVersion) {
		this.shareDiskVersion = shareFileVersion;
	}

	public long getPersonalDiskSize() {
		return personalDiskSize;
	}

	public void setPersonalDiskSize(long personalDiskSize) {
		this.personalDiskSize = personalDiskSize;
	}

	public long getMaxShareLinkDownload() {
		return maxShareLinkDownload;
	}

	public void setMaxShareLinkDownload(long maxShareLinkDownload) {
		this.maxShareLinkDownload = maxShareLinkDownload;
	}

	public long getShareLinkDownCount() {
		return shareLinkDownCount;
	}

	public void setShareLinkDownCount(long shareLinkDownCount) {
		this.shareLinkDownCount = shareLinkDownCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isNet() {
		return net;
	}

	public void setNet(boolean net) {
		this.net = net;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

}
