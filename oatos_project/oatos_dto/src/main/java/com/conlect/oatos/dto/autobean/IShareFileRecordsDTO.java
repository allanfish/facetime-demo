package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.List;

public interface IShareFileRecordsDTO extends Serializable {

	List<IShareFileRecordDTO> getRecordList();

	void setRecordList(List<IShareFileRecordDTO> recordList);
}
