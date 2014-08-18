package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

/**
 * 会议参会人list
 * 
 * @author yang
 * 
 */
public class ConferenceMembersDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 会议参会人list
	 */
	private List<ConferenceMemberDTO> members = new ArrayList<ConferenceMemberDTO>();

	public ConferenceMembersDTO() {
	}

	public List<ConferenceMemberDTO> getMembers() {
		return members;
	}

	public void setMembers(List<ConferenceMemberDTO> members) {
		this.members = members;
	}

}
