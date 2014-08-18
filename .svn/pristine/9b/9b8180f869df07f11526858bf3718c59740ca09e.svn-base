package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.conlect.oatos.dto.client.EnterpriseDTO;

/**
 * 企业
 * @author yang
 *
 */
public class Enterprise implements Serializable {

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
	 * 邮政编码
	 */
	private String postcode;
	/**
	 * 网站
	 */
	private String website;
	/**
	 * 企业管理员密码
	 */
	private String adminPassword;
	/**
	 * 最大用户数
	 */
	private int maxEmployees;
	/**
	 * 企业logo
	 */
	private String logo;
	/**
	 * 皮肤
	 */
	private String skin;
	/**
	 * 默认用户密码
	 */
	private String employeePassword;
	/**
	 * 产品key
	 */
	private String productKey;
	/**
	 * 网盘地址
	 */
	private String cloudDiskIp;

	/**
	 * 企业网盘空间，kb
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
	 * 手机
	 */
	private String mobile;
	/**
	 * 邮箱
	 */
	private String mail;
	/**
	 * 联系人
	 */
	private String contact;

	/**
	 * 企业网盘版本
	 * @deprecated
	 */
	private long shareDiskVersion;

	/**
	 * 企业个人网盘总大小,KB
	 */
	private long personalDiskSize;
	/**
	 * 外链最大下载流量，kb
	 */
	private long maxShareLinkDownload;
	/**
	 * 当前外链下载流量，kb
	 */
	private long shareLinkDownCount;

	/**
	 * 状态<br>
	 * {@link com.conlect.oatos.dto.status.EnterpriseStatus}
	 */
	private String status;
	/**
	 * 购买支付状态
	 */
	private String payStatus;
	/**
	 * 部门排序是否已激活
	 */
	private boolean orderEnabled = false;

	public Enterprise() {
	}

	public Enterprise(EnterpriseDTO entDTO) {
		this.enterpriseId = entDTO.getEnterpriseId();
		this.enterpriseName = entDTO.getEnterpriseName();
		this.phone = entDTO.getPhone();
		this.fax = entDTO.getFax();
		this.address = entDTO.getAddress();
		this.postcode = entDTO.getPostcode();
		this.website = entDTO.getWebsite();
		this.adminPassword = entDTO.getAdminPassword();
		this.maxEmployees = entDTO.getMaxEmployees();
		this.logo = entDTO.getLogo();
		this.skin = entDTO.getSkin();
		this.employeePassword = entDTO.getEmployeePassword();
		this.productKey = entDTO.getProductKey();
		this.cloudDiskIp = entDTO.getCloudDiskIp();
		this.registerTime = entDTO.getRegisterTime();
		this.expirationTime = entDTO.getExpirationTime();
		this.diskSize = entDTO.getDiskSize();
		this.mobile = entDTO.getMobile();
		this.mail = entDTO.getMail();
		this.contact = entDTO.getContact();
		this.shareDiskVersion = entDTO.getShareDiskVersion();
		this.personalDiskSize = entDTO.getPersonalDiskSize();
		this.maxShareLinkDownload = entDTO.getMaxShareLinkDownload();
		this.shareLinkDownCount = entDTO.getShareLinkDownCount();
		this.status = entDTO.getStatus();
		this.payStatus = entDTO.getPayStatus();
		this.orderEnabled = entDTO.isOrderEnabled();
	}

	public EnterpriseDTO toEnterpriseDTO() {
		EnterpriseDTO dto = new EnterpriseDTO();
		dto.setEnterpriseId(this.enterpriseId);
		dto.setEnterpriseName(this.enterpriseName);
		dto.setPhone(this.phone);
		dto.setFax(this.fax);
		dto.setAddress(this.address);
		dto.setPostcode(this.postcode);
		dto.setWebsite(this.website);
		dto.setAdminPassword(this.adminPassword);
		dto.setMaxEmployees(this.maxEmployees);
		dto.setLogo(this.logo);
		dto.setSkin(this.skin);
		dto.setEmployeePassword(this.employeePassword);
		dto.setProductKey(this.productKey);
		dto.setCloudDiskIp(this.cloudDiskIp);
		dto.setRegisterTime(registerTime);
		dto.setExpirationTime(expirationTime);
		dto.setDiskSize(diskSize);
		dto.setMobile(mobile);
		dto.setMail(mail);
		dto.setContact(contact);
		dto.setShareDiskVersion(shareDiskVersion);
		dto.setPersonalDiskSize(personalDiskSize);
		dto.setMaxShareLinkDownload(maxShareLinkDownload);
		dto.setShareLinkDownCount(shareLinkDownCount);
		dto.setStatus(status);
		dto.setPayStatus(payStatus);
		dto.setOrderEnabled(isOrderEnabled());
		return dto;
	}

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
	
	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
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

	public int getMaxEmployees() {
		return maxEmployees;
	}

	public void setMaxEmployees(int maxEmployees) {
		this.maxEmployees = maxEmployees;
	}

	public long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getCloudDiskIp() {
		return cloudDiskIp;
	}

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

	public void setShareDiskVersion(long shareDiskVersion) {
		this.shareDiskVersion = shareDiskVersion;
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

}
