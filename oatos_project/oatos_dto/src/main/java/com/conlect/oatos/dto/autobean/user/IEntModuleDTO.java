package com.conlect.oatos.dto.autobean.user;

import java.io.Serializable;
import java.util.Date;

public interface IEntModuleDTO extends Serializable {

	public abstract void setEnhanceStatus(String enhanceStatus);

	public abstract String getEnhanceStatus();

	public abstract void setMailStatus(String mailStatus);

	public abstract String getMailStatus();

	public abstract void setOfficeStatus(String officeStatus);

	public abstract String getOfficeStatus();

	public abstract void setEnterpriseDiskStatus(String enterpriseDiskStatus);

	public abstract String getEnterpriseDiskStatus();

	public abstract void setPersonalDiskStatus(String personalDiskStatus);

	public abstract String getPersonalDiskStatus();

	public abstract void setColleagueStatus(String colleagueStatus);

	public abstract String getColleagueStatus();

	public abstract void setCreateDate(Date createDate);

	public abstract Date getCreateDate();

	public abstract void setEntId(Long entId);

	public abstract Long getEntId();

	public abstract void setId(Long id);

	public abstract Long getId();

	public abstract void setConferenceStatus(String conferenceStatus);

	public abstract String getConferenceStatus();

	public abstract void setCsStatus(String csStatus);

	public abstract String getCsStatus();

}
