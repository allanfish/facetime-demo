package com.conlect.oatos.dto.status;

public interface ConferenceMemberStatus {

	/**
	 * 会议邀请中
	 */
	String Invited = "invited";

	/**
	 * 接受会议邀请
	 */
	String Accepted = "accepted";

	/**
	 * 拒绝会议邀请
	 */
	String Refused = "refused";

	/**
	 * 出席会议
	 */
	String Attended = "attended";

}
