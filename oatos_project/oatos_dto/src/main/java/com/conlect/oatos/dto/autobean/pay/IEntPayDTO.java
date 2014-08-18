package com.conlect.oatos.dto.autobean.pay;

import java.util.List;

public interface IEntPayDTO extends BaseEntPayDTO {

	List<IServiceTypeDTO> getServiceTypes();

	void setServiceTypes(List<IServiceTypeDTO> serviceTypes);

	IServiceStatusDTO getServiceStatus();

	void setServiceStatus(IServiceStatusDTO serviceStatus);

}
