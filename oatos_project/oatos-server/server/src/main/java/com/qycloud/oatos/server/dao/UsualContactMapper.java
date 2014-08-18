package com.qycloud.oatos.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.conlect.oatos.dto.client.UserInfoDTO;
import com.conlect.oatos.dto.client.UsualContactDTO;
import com.qycloud.oatos.server.domain.entity.User;
import com.qycloud.oatos.server.domain.entity.UsualContact;
import com.qycloud.oatos.server.domain.entity.UsualContactGroup;

/**
 * 常用联系人实体层
 * @author yang
 *
 */
public interface UsualContactMapper {
	/**
	 * 增加常用联系人
	 * @param usualContact
	 */
	void addUsualContact(List<UsualContact> usualContactList);
	
	/**
	 * 删除常用联系人
	 * @param usualContact
	 */
	void delUsualContact(@Param("userId") long userId,@Param("contactUsualId") long contactUsualId);

	/**
	 * 取常用联系人ID
	 * @param userId
	 * @return
	 */
	List<Long> getUsualContacts(long userId);
	
	/**
	 * 新建常联系人分组
	 * @param usualContactGroup
	 */
	void addUsualContactGroup(UsualContactGroup usualContactGroup);
	
	/**
	 * 修改常联系人分组
	 * @param usualContactGroup
	 */
	void updateUsualContactGroup(UsualContactGroup usualContactGroup);
	
	/**
	 * 删除常用联系人分组
	 * @param usualContactGroup
	 */
	void deleteUsualContactGroup(UsualContactGroup usualContactGroup);

	/**
	 * 通过用户ID获得联系人分组
	 * @param userId
	 * @return
	 */
	List<UsualContactGroup> getUsualContactGroupByUserId(long userId);

	/**
	 * 更新常用联系人分组
	 * @param contactUserId
	 * @param groupId
	 * @param userId
	 */
	void updateUsualContact(@Param("contactUserId")long  contactUserId, @Param("groupId")long groupId, @Param("userId")long userId);

}
