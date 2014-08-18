package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

/**
 * message list
 * 
 * @author yang
 */
public class MessagesDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 消息的个数
	 */
	private long count;

	/**
	 * 消息DTO列表
	 */

	private List<MessageDTO> messageDTOList = new ArrayList<MessageDTO>();

	public MessagesDTO() {
		super();
	}

	public MessagesDTO(List<MessageDTO> messageDTOList) {
		super();
		this.messageDTOList = messageDTOList;
		count = messageDTOList.size();
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public void setMessageDTOList(List<MessageDTO> messagesDTOs) {
		this.messageDTOList = messagesDTOs;
	}

	public List<MessageDTO> getMessageDTOList() {
		return messageDTOList;
	}

}
