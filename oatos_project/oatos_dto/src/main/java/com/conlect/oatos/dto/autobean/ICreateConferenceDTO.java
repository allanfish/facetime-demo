package com.conlect.oatos.dto.autobean;

import java.util.List;

public interface ICreateConferenceDTO extends IConferenceDTO {

	List<IConferenceDocDTO> getDocList();

	void setDocList(List<IConferenceDocDTO> docList);

	String getCloudDiskIp();

	void setCloudDiskIp(String cloudDiskIp);
}
