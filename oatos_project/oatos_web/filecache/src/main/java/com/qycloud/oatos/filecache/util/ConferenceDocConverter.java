package com.qycloud.oatos.filecache.util;

import java.util.ArrayList;
import java.util.List;

import com.conlect.oatos.dto.client.ConferenceDocDTO;

public class ConferenceDocConverter extends Thread {

	private List<ConferenceDocDTO> docList;

	private String token;

	public ConferenceDocConverter(List<ConferenceDocDTO> docList, String token) {
		this.docList = docList;
		this.token = token;
	}

	public ConferenceDocConverter(ConferenceDocDTO docDTO, String token) {
		this.token = token;
		docList = new ArrayList<ConferenceDocDTO>();
		docList.add(docDTO);
	}

	@Override
	public void run() {
		for (ConferenceDocDTO c : docList) {
			DocConverter.convertDocToSwf(c, token);
		}
	}

}
