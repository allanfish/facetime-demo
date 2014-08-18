package com.conlect.oatos.dto.client;

import java.util.List;

/**
 * 企业网盘文件操作记录list
 * 
 * @author yang
 * 
 */
public class ShareFileRecordsDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ShareFileRecordDTO> recordList;

	public List<ShareFileRecordDTO> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<ShareFileRecordDTO> recordList) {
		this.recordList = recordList;
	}
}
