package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.List;

public interface IBlockDTO extends Serializable {

	long getUserId();

	void setUserId(long userId);

	List<Long> getBlockUserList();

	void setBlockUserList(List<Long> blackUserList);

}