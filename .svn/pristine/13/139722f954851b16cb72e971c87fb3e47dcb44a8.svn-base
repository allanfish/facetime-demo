package com.qycloud.oatos.server.domain.entity;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.conlect.oatos.dto.autobean.user.IEntModuleDTO;
import com.conlect.oatos.dto.client.user.EntModuleDTO;
import com.conlect.oatos.dto.status.ModuleStatus;

/**
 * 企业模块实体
 * 
 * @author yufei
 * 
 */
public class EntModule {

	private Long id;
	private Long entId;
	private Date createDate;

	/**
	 * 同事模块的状态
	 */
	private String colleagueStatus;
	/**
	 * 个人网盘的状态
	 */
	private String personalDiskStatus;
	/**
	 * 企业网盘的状态
	 */
	private String enterpriseDiskStatus;

	/**
	 * 视频会议模块的状态
	 */
	private String conferenceStatus;
	/**
	 * 云办公的状态
	 */
	private String officeStatus;
	/**
	 * 邮件模块的状态
	 */
	private String mailStatus;

	/**
	 * 客服功能的状态
	 */
	private String csStatus;
	/**
	 * 付费添加用户的状态
	 */
	private String userAddStatus = ModuleStatus.UNUSED;
	/**
	 * 本地交互功能的状态
	 */
	private String enhanceStatus;

	public String getUserAddStatus() {
		return userAddStatus;
	}

	public void setUserAddStatus(String userAddStatus) {
		this.userAddStatus = userAddStatus;
	}

	public EntModule() {
		super();
	}

	public String getCsStatus() {
		return csStatus;
	}

	public void setCsStatus(String csStatus) {
		this.csStatus = csStatus;
	}

	public EntModule(IEntModuleDTO entModule) {
		BeanUtils.copyProperties(entModule, this);
	}

	public EntModuleDTO asDTO() {
		EntModuleDTO entModule = new EntModuleDTO();
		BeanUtils.copyProperties(this, entModule);
		return entModule;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEntId() {
		return entId;
	}

	public void setEntId(Long entId) {
		this.entId = entId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getColleagueStatus() {
		return colleagueStatus;
	}

	public void setColleagueStatus(String colleagueStatus) {
		this.colleagueStatus = colleagueStatus;
	}

	public String getPersonalDiskStatus() {
		return personalDiskStatus;
	}

	public void setPersonalDiskStatus(String personalDiskStatus) {
		this.personalDiskStatus = personalDiskStatus;
	}

	public String getEnterpriseDiskStatus() {
		return enterpriseDiskStatus;
	}

	public void setEnterpriseDiskStatus(String enterpriseDiskStatus) {
		this.enterpriseDiskStatus = enterpriseDiskStatus;
	}

	public String getConferenceStatus() {
		return conferenceStatus;
	}

	public void setConferenceStatus(String conferenceStatus) {
		this.conferenceStatus = conferenceStatus;
	}

	public String getOfficeStatus() {
		return officeStatus;
	}

	public void setOfficeStatus(String officeStatus) {
		this.officeStatus = officeStatus;
	}

	public String getMailStatus() {
		return mailStatus;
	}

	public void setMailStatus(String mailStatus) {
		this.mailStatus = mailStatus;
	}

	public String getEnhanceStatus() {
		return enhanceStatus;
	}

	public void setEnhanceStatus(String enhanceStatus) {
		this.enhanceStatus = enhanceStatus;
	}

}
