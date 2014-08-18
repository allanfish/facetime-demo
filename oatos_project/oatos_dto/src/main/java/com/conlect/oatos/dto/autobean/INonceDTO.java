package com.conlect.oatos.dto.autobean;

import java.io.Serializable;

public interface INonceDTO extends Serializable {

	String getNonce();

	void setNonce(String nonce);

	String getHashKey();

	void setHashKey(String hashKey);

	String getAgent();

	void setAgent(String agent);
}
