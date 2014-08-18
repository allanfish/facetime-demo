package com.qycloud.oatos.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.ConferenceMember;
import com.qycloud.oatos.server.domain.entity.Conference;

/**
 * 视频会议实体层
 * @author yang
 *
 */
public interface ConferenceMapper {

	/**
	 * 添加视频会议
	 * @param conference
	 */
	void addConference(Conference conference);

	/**
	 * 更新视频会议
	 * @param conference
	 */
	void updateConference(Conference conference);

	/**
	 * 根据用户获取视频会议
	 * @param userId
	 * @return
	 */
	List<Conference> getConferenceByUserId(long userId);

	/**
	 * 添加视频会议成员
	 * @param members
	 */
	void addConferenceMembers(List<ConferenceMember> members);

	/**
	 * 修改视频会议成员
	 * @param member
	 */
	void updateConferenceMember(ConferenceMember member);

	/**
	 * 获取视频会议成员
	 * @param conferenceId
	 * @return
	 */
	List<ConferenceMember> getConferenceMembers(long conferenceId);

	/**
	 * 删除视频会议
	 * @param conferenceId
	 */
	void deleteConferenceById(long conferenceId);

	/**
	 * 获取视频会议信息
	 * @param conferenceId
	 * @return
	 */
	Conference getConferenceById(long conferenceId);

	/**
	 * 获取视频会议成员状态
	 * @param conferenceId
	 * @param userId
	 * @return
	 */
	String getConferenceMemberStatus(@Param("conferenceId") long conferenceId, @Param("userId") long userId);

	/**
	 * 获取会议成员总数
	 * @param conferenceId
	 * @return
	 */
	long getConferenceMemberCount(long conferenceId);

	/**
	 * 获取未确认的视频会议
	 * @param userId
	 * @return
	 */
	List<ConferenceMember> getUnconfirmedConferenceByUserId(long userId);

	/**
	 * 删除视频会成员
	 * @param conferenceId
	 * @param members
	 */
	void deleteConferenceMembers(@Param("conferenceId") long conferenceId, @Param("members") List<ConferenceMember> members);
}
