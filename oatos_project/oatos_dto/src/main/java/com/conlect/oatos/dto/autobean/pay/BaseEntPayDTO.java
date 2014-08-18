package com.conlect.oatos.dto.autobean.pay;

import java.io.Serializable;

public interface BaseEntPayDTO extends Serializable {

	void setEntId(long entId);

	long getEntId();

	void setEntName(String entName);

	String getEntName();

	void setRemainEntDiskSize(long remainEntDiskSize);

	long getRemainEntDiskSize();

	void setRemainPersonDiskSize(long remainPersonDiskSize);

	long getRemainPersonDiskSize();

	void setRemainShareLinkSize(long remainShareLinkSize);

	long getRemainShareLinkSize();

	void setRemainUserSize(long remainUserSize);

	long getRemainUserSize();

}
