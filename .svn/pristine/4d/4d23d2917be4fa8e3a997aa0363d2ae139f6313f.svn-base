package com.qycloud.oatos.server.domain.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conlect.oatos.dto.client.UserInfoDTO;
import com.conlect.oatos.dto.client.UsualContactDTO;
import com.conlect.oatos.dto.client.UsualContactGroupAndUserDTO;
import com.conlect.oatos.dto.client.UsualContactGroupDTO;
import com.conlect.oatos.dto.client.UsualContactGroupsDTO;
import com.qycloud.oatos.server.dao.UserMapper;
import com.qycloud.oatos.server.dao.UsualContactMapper;
import com.qycloud.oatos.server.domain.entity.User;
import com.qycloud.oatos.server.domain.entity.UsualContact;
import com.qycloud.oatos.server.domain.entity.UsualContactGroup;

/**
 * 常用联系人服务
 * @author yang
 *
 */
@Service("UsualContactLogic")
public class UsualContactLogic {

	@Autowired
	private UsualContactMapper usualContactMapper;
	@Autowired
	private UserMapper userMapper;

	/**
	 * 添加常用联系人
	 * @param usualContactDto
	 */
	@Transactional
	public void setUsualContactListByUserId(UsualContactDTO usualContactDto){
		if(usualContactDto.getUsualContactUserList() != null && usualContactDto.getUsualContactUserList().size() > 0){
				List<UsualContact> usualContacList = new ArrayList<UsualContact>();
				for (Long usualContactId : usualContactDto.getUsualContactUserList()) {
					UsualContact usualContact = new UsualContact();
					usualContact.setUserId(usualContactDto.getUserId());
					usualContact.setContactUserId(usualContactId);
					usualContacList.add(usualContact);
				}
				usualContactMapper.addUsualContact(usualContacList);
		}
	}
	
	/**
	 * 取消常用联系人
	 * @param usualContactDto
	 */
	@Transactional
	public void deleteUsualContact(UsualContactDTO usualContactDto){
		List<Long> usualContactIdList = usualContactDto.getUsualContactUserList();
		if(usualContactDto.getUsualContactUserList()!=null&&usualContactDto.getUsualContactUserList().size()>0){
			for (Long contactUsualId : usualContactIdList) {
				usualContactMapper.delUsualContact(usualContactDto.getUserId(),contactUsualId);
			}
			
		}
	}
	
	/**
	 * 创建常用联系人分组
	 * @param usualContactDto
	 */
	@Transactional
	public void addUsualContactGroup(UsualContactGroupDTO usualContactGroupDTO){
		usualContactMapper.addUsualContactGroup(new UsualContactGroup(usualContactGroupDTO));
	}
	
	/**
	 * 修改常用联系人分组
	 * @param usualContactGroupDTO
	 */
	@Transactional
	public void updateUsualContactGroup(UsualContactGroupDTO usualContactGroupDTO){
		usualContactMapper.updateUsualContactGroup(new UsualContactGroup(usualContactGroupDTO));
	}
	
	/**
	 * 删除常用联系人分组
	 * @param usualContactGroupDTO
	 */
	@Transactional
	public void deleteUsualContactGroup(UsualContactGroupDTO usualContactGroupDTO){
		usualContactMapper.deleteUsualContactGroup(new UsualContactGroup(usualContactGroupDTO));
	}

	/**
	 * 得到常用联系人分组和详细信息
	 * @param userId
	 * @return
	 */
	public UsualContactGroupAndUserDTO getUsualContactGroupAndUserByUserId(long userId) {
		UsualContactGroupAndUserDTO usualContactGroupsDTO = new UsualContactGroupAndUserDTO();
		List<UsualContactGroupDTO> usualContactList = getUsualContactGroupByUserId(userId);
		List<UserInfoDTO> userInfoDTOs = getUserInfoListByUserId(userId);
		usualContactGroupsDTO.setGroupList(usualContactList);
		usualContactGroupsDTO.setUserList(userInfoDTOs);
		return usualContactGroupsDTO;
	}

	/**
	 * 通过用户ID获得用户
	 * @param userId
	 * @return
	 */
	private List<UserInfoDTO> getUserInfoListByUserId(long userId) {
		List<User> userList = userMapper.getUserInfoListByUserId(userId);
		List<UserInfoDTO> userInfoDTOs = new ArrayList<UserInfoDTO>();
		for (User user : userList) {
			userInfoDTOs.add(user.toUserInfoDTO());
		}
		return userInfoDTOs;
	}

	/**
	 * 通过用户ID获得常联系人分组
	 * @param userId
	 * @return 
	 */
	private List<UsualContactGroupDTO> getUsualContactGroupByUserId(long userId) {
		List<UsualContactGroupDTO> usualContactDTOs = new ArrayList<UsualContactGroupDTO>();
		List<UsualContactGroup> usualContactList = usualContactMapper.getUsualContactGroupByUserId(userId);
		for (UsualContactGroup usualContactGroup : usualContactList) {
			usualContactDTOs.add(usualContactGroup.toUsualContactGroupDTO());
		}
		return usualContactDTOs;
		
	}
	
	/**
	 * 更新常用联系人的分组ID
	 * @param usualContactDTO
	 */
	public void updateUsualContact(UsualContactDTO usualContactDTO) {
		List<Long> usualContactUserList =  usualContactDTO.getUsualContactUserList();
		for (Long contactUserId : usualContactUserList) {
			usualContactMapper.updateUsualContact(contactUserId,usualContactDTO.getGroupId(),usualContactDTO.getUserId());
		}
	}

	/**
	 * 取常用联系人分组
	 * @param userId
	 * @return
	 */
	public UsualContactGroupsDTO getUsualContactGroupsByUserId(long userId) {
		List<UsualContactGroupDTO> groupList = getUsualContactGroupByUserId(userId);
		return new UsualContactGroupsDTO(groupList);
	}
}
