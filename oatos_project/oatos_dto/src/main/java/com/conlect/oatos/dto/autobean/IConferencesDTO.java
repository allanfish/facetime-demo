package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.List;

public interface IConferencesDTO extends Serializable {
	List<IConferenceDTO> getConferenceList();

	void setConferenceList(List<IConferenceDTO> conferenceList);

	String getCloudDiskIp();

	void setCloudDiskIp(String cloudDiskIp);
}
