package com.conlect.oatos.dto.autobean;

import com.conlect.oatos.dto.autobean.admin.IAdminDTO;
import com.conlect.oatos.dto.autobean.admin.IAdminDataDTO;
import com.conlect.oatos.dto.autobean.admin.IAdminDepartmentDTO;
import com.conlect.oatos.dto.autobean.admin.IAdminFolderDTO;
import com.conlect.oatos.dto.autobean.admin.IAdminsDTO;
import com.conlect.oatos.dto.autobean.mail.IMailAccountDTO;
import com.conlect.oatos.dto.autobean.mail.IMailAccountsDTO;
import com.conlect.oatos.dto.autobean.mail.IMailAttachDTO;
import com.conlect.oatos.dto.autobean.mail.IMailContactDTO;
import com.conlect.oatos.dto.autobean.mail.IMailContactGroupDTO;
import com.conlect.oatos.dto.autobean.mail.IMailContactGroupsDTO;
import com.conlect.oatos.dto.autobean.mail.IMailDTO;
import com.conlect.oatos.dto.autobean.mail.IMailFolderDTO;
import com.conlect.oatos.dto.autobean.mail.IMailFoldersDTO;
import com.conlect.oatos.dto.autobean.mail.IMailListDTO;
import com.conlect.oatos.dto.autobean.mail.IMailQueryDTO;
import com.conlect.oatos.dto.autobean.pay.IBuyServiceDTO;
import com.conlect.oatos.dto.autobean.pay.IBuyServiceListDTO;
import com.conlect.oatos.dto.autobean.pay.ICurrentServiceDTO;
import com.conlect.oatos.dto.autobean.pay.ICurrentServiceListDTO;
import com.conlect.oatos.dto.autobean.pay.IEntPayDTO;
import com.conlect.oatos.dto.autobean.pay.IOrderDTO;
import com.conlect.oatos.dto.autobean.pay.IOrderItemDTO;
import com.conlect.oatos.dto.autobean.pay.IOrderItemListDTO;
import com.conlect.oatos.dto.autobean.pay.IServiceTypeDTO;
import com.conlect.oatos.dto.autobean.pay.IServiceTypeListDTO;
import com.conlect.oatos.dto.autobean.user.IEnterpriseUserListDTO;
import com.conlect.oatos.dto.autobean.user.IPersonDiskAllocDTO;
import com.conlect.oatos.dto.autobean.user.IPersonDiskAllocListDTO;
import com.conlect.oatos.dto.autobean.user.IUserImportDTO;
import com.conlect.oatos.dto.autobean.user.IUserImportListDTO;
import com.conlect.oatos.dto.autobean.user.IUserImportSaveDTO;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;

/**
 * @author jinkerjiang
 */
public class DTOBeanUtil {

	private DTOFactory factory;

	/**
	 * Create bean utility<br>
	 * Get instance of {@link DTOFactory}, in server, use
	 * {@link com.google.web.bindery.autobean.vm.AutoBeanFactorySource#create(Class)}
	 * <br>
	 * in client, use {@link com.google.gwt.core.client.GWT#create(Class)}.
	 */
	public DTOBeanUtil(DTOFactory factory) {
		this.factory = factory;
	}

	/**
	 * IClientToken
	 */
	public IClientToken createClientToken() {
		AutoBean<IClientToken> bean = factory.clientToken();
		return bean.as();
	}

	public IMailQueryDTO createMailQueryDTO() {
		AutoBean<IMailQueryDTO> bean = factory.createIMailQueryDTO();
		return bean.as();
	}

	public IClientToken createClientToken(IClientToken toWrap) {
		AutoBean<IClientToken> bean = factory.clientToken(toWrap);
		return bean.as();
	}

	public String serializeToJson(IClientToken clientToken) {
		AutoBean<IClientToken> bean = AutoBeanUtils.getAutoBean(clientToken);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IClientToken deserializeFromJsonToClientToken(String json) {
		AutoBean<IClientToken> bean = AutoBeanCodex.decode(factory,
				IClientToken.class, json);
		return bean.as();
	}

	/**
	 * IBuddyDTO
	 */
	public IBuddyDTO createBuddyDTO() {
		AutoBean<IBuddyDTO> bean = factory.buddyDTO();
		return bean.as();
	}

	public IBuddyDTO createBuddyDTO(IBuddyDTO toWrap) {
		AutoBean<IBuddyDTO> bean = factory.buddyDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IBuddyDTO clientToken) {
		AutoBean<IBuddyDTO> bean = AutoBeanUtils.getAutoBean(clientToken);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IBuddyDTO deserializeFromJsonToBuddyDTO(String json) {
		AutoBean<IBuddyDTO> bean = AutoBeanCodex.decode(factory,
				IBuddyDTO.class, json);
		return bean.as();
	}

	/**
	 * IUserInfoDTO
	 */
	public IUserInfoDTO createUserInfoDTO() {
		AutoBean<IUserInfoDTO> bean = factory.userInfoDTO();
		return bean.as();
	}

	public IUserInfoDTO createUserInfoDTO(IUserInfoDTO toWrap) {
		AutoBean<IUserInfoDTO> bean = factory.userInfoDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IUserInfoDTO userInfoDTO) {
		AutoBean<IUserInfoDTO> bean = AutoBeanUtils.getAutoBean(userInfoDTO);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IUserInfoDTO fromJsonToUserInfo(String json) {
		AutoBean<IUserInfoDTO> bean = AutoBeanCodex.decode(factory,
				IUserInfoDTO.class, json);
		return bean.as();
	}

	/**
	 * copy
	 * 
	 * @param user
	 * @return
	 */
	public IUserInfoDTO clone(IUserInfoDTO user) {
		IUserInfoDTO u = createUserInfoDTO();
		u.setUserId(user.getUserId());
		u.setUserName(user.getUserName());
		u.setRegisterMailAccount(user.getRegisterMailAccount());
		u.setMailing7MailAccount(user.getMailing7MailAccount());
		u.setGender(user.getGender());
		u.setBirthday(user.getBirthday());
		u.setAge(user.getAge());
		u.setRegisterDate(user.getRegisterDate());
		u.setIcon(user.getIcon());
		u.setSignature(user.getSignature());
		u.setNation(user.getNation());
		u.setProvince(user.getProvince());
		u.setCity(user.getCity());
		u.setDistrict(user.getDistrict());
		u.setOccupation(user.getOccupation());
		u.setCollege(user.getCollege());
		u.setDegree(user.getDegree());
		u.setAnimal(user.getAnimal());
		u.setStar(user.getStar());
		u.setRealName(user.getRealName());
		u.setEngishName(user.getEngishName());
		u.setHomePage(user.getHomePage());
		u.setBlood(user.getBlood());
		u.setMajor(user.getMajor());
		u.setPhone(user.getPhone());
		u.setMobile(user.getMobile());
		u.setEmailEnabled(user.isEmailEnabled());
		u.setPhoneEnabled(user.isPhoneEnabled());
		u.setLocked(user.isLocked());
		u.setEnterpriseId(user.getEnterpriseId());
		u.setDepartmentId(user.getDepartmentId());
		u.setEmployeeNumber(user.getEmployeeNumber());
		u.setDownload(user.isDownload());
		u.setAccessExternal(user.isAccessExternal());
		u.setCustomerService(user.isCustomerService());
		u.setStatus(user.getStatus());
		u.setJobTitle(user.getJobTitle());
		u.setRoles(user.getRoles());
		u.setUserType(user.getUserType());
		u.setDiskSize(user.getDiskSize());
		u.setOnlineStatus(user.getOnlineStatus());
		u.setPassword(user.getPassword());
		u.setNonce(user.getNonce());
		u.setHashKey(user.getHashKey());
		return u;
	}

	public IMessageDTO createMessageDTO() {
		AutoBean<IMessageDTO> bean = factory.messageDTO();
		return bean.as();
	}

	public IMessagesDTO createMessagesDTO() {
		AutoBean<IMessagesDTO> bean = factory.messagesDTO();
		return bean.as();
	}

	public IMessageDTO createMessageDTO(IMessageDTO toWrap) {
		AutoBean<IMessageDTO> bean = factory.messageDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IMessageDTO messageDTO) {
		AutoBean<IMessageDTO> bean = AutoBeanUtils.getAutoBean(messageDTO);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IMessageDTO deserializeFromJsonToMessageDTO(String json) {
		AutoBean<IMessageDTO> bean = AutoBeanCodex.decode(factory,
				IMessageDTO.class, json);
		return bean.as();
	}

	/**
	 * INetworkFileDTO
	 */
	public INetworkFileDTO createNetworkFileDTO() {
		AutoBean<INetworkFileDTO> bean = factory.networkFileDTO();
		return bean.as();
	}

	public INetworkFileDTO createNetworkFileDTO(INetworkFileDTO iNetworkFileDTO) {
		AutoBean<INetworkFileDTO> bean = factory
				.networkFileDTO(iNetworkFileDTO);
		return bean.as();
	}

	public String serializeToJson(INetworkFileDTO iNetworkFileDTO) {
		AutoBean<INetworkFileDTO> bean = AutoBeanUtils
				.getAutoBean(iNetworkFileDTO);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public INetworkFileDTO fromJsonToNetworkFileDTO(String json) {
		AutoBean<INetworkFileDTO> bean = AutoBeanCodex.decode(factory,
				INetworkFileDTO.class, json);
		return bean.as();
	}

	public INetworkFileDTO clone(INetworkFileDTO fileDTO) {
		INetworkFileDTO file = createNetworkFileDTO();
		file.setFileId(fileDTO.getFileId().longValue());
		file.setUserId(fileDTO.getUserId());
		if (fileDTO.getFolderId() != null) {
			file.setFolderId(fileDTO.getFolderId().longValue());
		}
		file.setGuid(fileDTO.getGuid());
		file.setSize(fileDTO.getSize());
		file.setType(fileDTO.getType());
		file.setName(fileDTO.getName());
		file.setUpdateTime(fileDTO.getUpdateTime());
		file.setCreateTime(fileDTO.getCreateTime());
		file.setDeleted(fileDTO.getDeleted());
		file.setThumb(fileDTO.getThumb());
		file.setRemark(fileDTO.getRemark());
		file.setVersion(fileDTO.getVersion());
		return file;
	}

	/**
	 * INetworkFolderDTO
	 */

	public INetworkFolderDTO createNetworkFolderDTO() {
		AutoBean<INetworkFolderDTO> bean = factory.networkFolderDTO();
		return bean.as();
	}

	public INetworkFolderDTO createNetworkFolderDTO(INetworkFolderDTO toWrap) {
		AutoBean<INetworkFolderDTO> bean = factory.networkFolderDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(INetworkFolderDTO networkFolderDTO) {
		AutoBean<INetworkFolderDTO> bean = AutoBeanUtils
				.getAutoBean(networkFolderDTO);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public INetworkFolderDTO deserializeFromJsonToNetworkFolderDTO(String json) {
		AutoBean<INetworkFolderDTO> bean = AutoBeanCodex.decode(factory,
				INetworkFolderDTO.class, json);
		return bean.as();
	}

	public INetworkFolderDTO clone(INetworkFolderDTO folderDTO) {
		INetworkFolderDTO folder = createNetworkFolderDTO();
		folder.setFolderId(folderDTO.getFolderId());
		folder.setParentFolderId(folderDTO.getParentFolderId());
		folder.setUserId(folderDTO.getUserId());
		folder.setFolderName(folderDTO.getFolderName());
		folder.setDeleted(folderDTO.getDeleted());
		folder.setThumb(folderDTO.getThumb());
		folder.setRemark(folderDTO.getRemark());
		folder.setVersion(folderDTO.getVersion());
		return folder;
	}

	/**
	 * IViewFileDTO
	 */

	public IViewFileDTO createViewFileDTO() {
		AutoBean<IViewFileDTO> bean = factory.viewFileDTO();
		return bean.as();
	}

	public IViewFileDTO createViewFileDTO(IViewFileDTO toWrap) {
		AutoBean<IViewFileDTO> bean = factory.viewFileDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IViewFileDTO viewFileDTO) {
		AutoBean<IViewFileDTO> bean = AutoBeanUtils.getAutoBean(viewFileDTO);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IViewFileDTO deserializeFromJsonToViewFileDTO(String json) {
		AutoBean<IViewFileDTO> bean = AutoBeanCodex.decode(factory,
				IViewFileDTO.class, json);
		return bean.as();
	}

	/**
	 * IViewFileResultDTO
	 */

	public IViewFileResultDTO createViewFileResultDTO() {
		AutoBean<IViewFileResultDTO> bean = factory.viewFileResultDTO();
		return bean.as();
	}

	public IViewFileResultDTO createViewFileResultDTO(IViewFileResultDTO toWrap) {
		AutoBean<IViewFileResultDTO> bean = factory.viewFileResultDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IViewFileResultDTO viewFileResultDTO) {
		AutoBean<IViewFileResultDTO> bean = AutoBeanUtils
				.getAutoBean(viewFileResultDTO);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IViewFileResultDTO deserializeFromJsonToViewFileResultDTO(String json) {
		AutoBean<IViewFileResultDTO> bean = AutoBeanCodex.decode(factory,
				IViewFileResultDTO.class, json);
		return bean.as();
	}

	/**
	 * ISaveFileDTO
	 */

	public ISaveFileDTO saveFileDTO() {
		AutoBean<ISaveFileDTO> bean = factory.saveFileDTO();
		return bean.as();
	}

	public ISaveFileDTO saveFileDTO(ISaveFileDTO toWrap) {
		AutoBean<ISaveFileDTO> bean = factory.saveFileDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(ISaveFileDTO saveFileDTO) {
		AutoBean<ISaveFileDTO> bean = AutoBeanUtils.getAutoBean(saveFileDTO);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public ISaveFileDTO deserializeFromJsonToSaveFileToDiskDTO(String json) {
		AutoBean<ISaveFileDTO> bean = AutoBeanCodex.decode(factory,
				ISaveFileDTO.class, json);
		return bean.as();
	}

	/**
	 * IImageDTO
	 */

	public IImageDTO createImageDTO() {
		AutoBean<IImageDTO> bean = factory.imageDTO();
		return bean.as();
	}

	public IImageDTO createImageDTO(IImageDTO toWrap) {
		AutoBean<IImageDTO> bean = factory.imageDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IImageDTO imageDTO) {
		AutoBean<IImageDTO> bean = AutoBeanUtils.getAutoBean(imageDTO);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IImageDTO deserializeFromJsonToImageDTO(String json) {
		AutoBean<IImageDTO> bean = AutoBeanCodex.decode(factory,
				IImageDTO.class, json);
		return bean.as();
	}

	/**
	 * IUserStatusDTO
	 */

	public IUserStatusDTO createUserStatusDTO() {
		AutoBean<IUserStatusDTO> bean = factory.userStatusDTO();
		return bean.as();
	}

	public IUserStatusDTO createUserStatusDTO(IUserStatusDTO toWrap) {
		AutoBean<IUserStatusDTO> bean = factory.userStatusDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IUserStatusDTO dto) {
		AutoBean<IUserStatusDTO> bean = AutoBeanUtils.getAutoBean(dto);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IUserStatusDTO deserializeFromJsonToUserStatusDTO(String json) {
		AutoBean<IUserStatusDTO> bean = AutoBeanCodex.decode(factory,
				IUserStatusDTO.class, json);
		return bean.as();
	}

	/**
	 * IUserStatausesDTO
	 */

	public IUserStatausesDTO createUserStatausesDTO() {
		AutoBean<IUserStatausesDTO> bean = factory.userStatusesDTO();
		return bean.as();
	}

	public IUserStatausesDTO createUserStatausesDTO(IUserStatausesDTO toWrap) {
		AutoBean<IUserStatausesDTO> bean = factory.userStatusesDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IUserStatausesDTO dto) {
		AutoBean<IUserStatausesDTO> bean = AutoBeanUtils.getAutoBean(dto);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IUserStatausesDTO deserializeFromJsonToUserStatusesDTO(String json) {
		AutoBean<IUserStatausesDTO> bean = AutoBeanCodex.decode(factory,
				IUserStatausesDTO.class, json);
		return bean.as();
	}

	/**
	 * IPasswordChangeDTO
	 */

	public IPasswordChangeDTO createPasswordChangeDTO() {
		AutoBean<IPasswordChangeDTO> bean = factory.passwordChangeDTO();
		return bean.as();
	}

	public IPasswordChangeDTO createPasswordChangeDTO(IPasswordChangeDTO toWrap) {
		AutoBean<IPasswordChangeDTO> bean = factory.passwordChangeDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IPasswordChangeDTO passwordChangeDTO) {
		AutoBean<IPasswordChangeDTO> bean = AutoBeanUtils
				.getAutoBean(passwordChangeDTO);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IPasswordChangeDTO deserializeFromJsonToPasswordChangeDTO(String json) {
		AutoBean<IPasswordChangeDTO> bean = AutoBeanCodex.decode(factory,
				IPasswordChangeDTO.class, json);
		return bean.as();
	}

	/**
	 * INetworkFoldersDTO
	 */

	public INetworkFoldersDTO createNetworkFoldersDTO() {
		AutoBean<INetworkFoldersDTO> bean = factory.networkFoldersDTO();
		return bean.as();
	}

	public INetworkFoldersDTO createNetworkFoldersDTO(INetworkFoldersDTO toWrap) {
		AutoBean<INetworkFoldersDTO> bean = factory.networkFoldersDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(INetworkFoldersDTO networkFoldersDTO) {
		AutoBean<INetworkFoldersDTO> bean = AutoBeanUtils
				.getAutoBean(networkFoldersDTO);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public INetworkFoldersDTO deserializeFromJsonToNetworkFoldersDTO(String json) {
		AutoBean<INetworkFoldersDTO> bean = AutoBeanCodex.decode(factory,
				INetworkFoldersDTO.class, json);
		return bean.as();
	}

	/**
	 * INetworkFilesDTO
	 */

	public INetworkFilesDTO createNetworkFilesDTO() {
		AutoBean<INetworkFilesDTO> bean = factory.networkFilesDTO();
		return bean.as();
	}

	public INetworkFilesDTO createNetworkFilesDTO(INetworkFilesDTO toWrap) {
		AutoBean<INetworkFilesDTO> bean = factory.networkFilesDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(INetworkFilesDTO networkFilesDTO) {
		AutoBean<INetworkFilesDTO> bean = AutoBeanUtils
				.getAutoBean(networkFilesDTO);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public INetworkFilesDTO deserializeFromJsonToNetworkFilesDTO(String json) {
		AutoBean<INetworkFilesDTO> bean = AutoBeanCodex.decode(factory,
				INetworkFilesDTO.class, json);
		return bean.as();
	}

	/**
	 * IUserInfosDTO
	 */

	public IUserInfosDTO createUserInfosDTO() {
		AutoBean<IUserInfosDTO> bean = factory.userInfosDTO();
		return bean.as();
	}

	public IUserInfosDTO createUserInfosDTO(IUserInfosDTO toWrap) {
		AutoBean<IUserInfosDTO> bean = factory.userInfosDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IUserInfosDTO userInfosDTO) {
		AutoBean<IUserInfosDTO> bean = AutoBeanUtils.getAutoBean(userInfosDTO);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IUserInfosDTO deserializeFromJsonToUserInfosDTO(String json) {
		AutoBean<IUserInfosDTO> bean = AutoBeanCodex.decode(factory,
				IUserInfosDTO.class, json);
		return bean.as();
	}

	/**
	 * IUserIconsDTO
	 * 
	 * @param json
	 * @return
	 */
	public IUserIconsDTO deserializeFromJsonToUserIconsDTO(String json) {
		AutoBean<IUserIconsDTO> bean = AutoBeanCodex.decode(factory,
				IUserIconsDTO.class, json);
		return bean.as();
	}

	/**
	 * IMessagesDTO
	 * 
	 * @param json
	 * @return
	 */
	public IMessagesDTO deserializeFromJsonToMessagesDTO(String json) {
		AutoBean<IMessagesDTO> bean = AutoBeanCodex.decode(factory,
				IMessagesDTO.class, json);
		return bean.as();
	}

	public String serializeToJson(IMessagesDTO messagesDTO) {
		AutoBean<IMessagesDTO> bean = AutoBeanUtils.getAutoBean(messagesDTO);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IMessagesDTO createMessagesDTO(IMessagesDTO toWrap) {
		AutoBean<IMessagesDTO> bean = factory.messagesDTO(toWrap);
		return bean.as();
	}

	/**
	 * IFileThumbsDTO
	 * 
	 * @param json
	 * @return
	 */
	public IFileThumbsDTO deserializeFromJsonToFileThumbsDTO(String json) {
		AutoBean<IFileThumbsDTO> bean = AutoBeanCodex.decode(factory,
				IFileThumbsDTO.class, json);
		return bean.as();
	}

	/**
	 * IChatGroupMemberDTO
	 * 
	 * @param json
	 * @return
	 */
	public IChatGroupMemberDTO createChatGroupMemberDTO() {
		AutoBean<IChatGroupMemberDTO> bean = factory.chatGroupMemberDTO();
		return bean.as();
	}

	public IChatGroupMemberDTO createChatGroupMemberDTO(
			IChatGroupMemberDTO toWrap) {
		AutoBean<IChatGroupMemberDTO> bean = factory.chatGroupMemberDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IChatGroupMemberDTO chatGroupMember) {
		AutoBean<IChatGroupMemberDTO> bean = AutoBeanUtils
				.getAutoBean(chatGroupMember);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IChatGroupMemberDTO deserializeFromJsonToChatGroupMemberDTO(
			String json) {
		AutoBean<IChatGroupMemberDTO> bean = AutoBeanCodex.decode(factory,
				IChatGroupMemberDTO.class, json);
		return bean.as();
	}

	/**
	 * IChatGroupMembersDTO
	 * 
	 * @param json
	 * @return
	 */
	public IChatGroupMembersDTO createChatGroupMembersDTO() {
		AutoBean<IChatGroupMembersDTO> bean = factory.chatGroupMembersDTO();
		return bean.as();
	}

	public IChatGroupMembersDTO createChatGroupMembersDTO(
			IChatGroupMembersDTO toWrap) {
		AutoBean<IChatGroupMembersDTO> bean = factory
				.chatGroupMembersDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IChatGroupMembersDTO chatGroupMembers) {
		AutoBean<IChatGroupMembersDTO> bean = AutoBeanUtils
				.getAutoBean(chatGroupMembers);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IChatGroupMembersDTO deserializeFromJsonToChatGroupMembersDTO(
			String json) {
		AutoBean<IChatGroupMembersDTO> bean = AutoBeanCodex.decode(factory,
				IChatGroupMembersDTO.class, json);
		return bean.as();
	}

	/**
	 * IUserIconDTO
	 * 
	 * @param json
	 * @return
	 */
	public IUserIconDTO userIconDTO(IUserIconDTO userIconDTO) {
		AutoBean<IUserIconDTO> bean = factory.userIconDTO(userIconDTO);
		return bean.as();
	}

	public IUserIconDTO deserializeFromJsonToUserIconDTO(String json) {
		AutoBean<IUserIconDTO> bean = AutoBeanCodex.decode(factory,
				IUserIconDTO.class, json);
		return bean.as();
	}

	public String serializeToJson(IUserIconDTO userIconDTO) {
		AutoBean<IUserIconDTO> bean = AutoBeanUtils.getAutoBean(userIconDTO);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	/**
	 * ISendFileResultDTO
	 * 
	 * @param json
	 * @return
	 */
	public ISendFileResultDTO createSendFileResultDTO() {
		AutoBean<ISendFileResultDTO> bean = factory.sendFileResultDTO();
		return bean.as();
	}

	public ISendFileResultDTO createSendFileResultDTO(ISendFileResultDTO toWrap) {
		AutoBean<ISendFileResultDTO> bean = factory.sendFileResultDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(ISendFileResultDTO data) {
		AutoBean<ISendFileResultDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public ISendFileResultDTO deserializeFromJsonToSendFileResultDTO(String json) {
		AutoBean<ISendFileResultDTO> bean = AutoBeanCodex.decode(factory,
				ISendFileResultDTO.class, json);
		return bean.as();
	}

	/**
	 * ILoginDTO
	 * 
	 * @param json
	 * @return
	 */
	public ILoginDTO createLoginDTO() {
		AutoBean<ILoginDTO> bean = factory.loginDTO();
		return bean.as();
	}

	public ILoginDTO createLoginDTO(ILoginDTO toWrap) {
		AutoBean<ILoginDTO> bean = factory.loginDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(ILoginDTO data) {
		AutoBean<ILoginDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public ILoginDTO deserializeFromJsonToLoginDTO(String json) {
		AutoBean<ILoginDTO> bean = AutoBeanCodex.decode(factory,
				ILoginDTO.class, json);
		return bean.as();
	}

	/**
	 * IMailDTO
	 * 
	 * @param json
	 * @return
	 */
	public IMailDTO createMailDTO() {
		AutoBean<IMailDTO> bean = factory.mailDTO();
		return bean.as();
	}

	public IMailDTO createMailDTO(IMailDTO toWrap) {
		AutoBean<IMailDTO> bean = factory.mailDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IMailDTO data) {
		AutoBean<IMailDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IMailDTO deserializeFromJsonToMailDTO(String json) {
		AutoBean<IMailDTO> bean = AutoBeanCodex.decode(factory, IMailDTO.class,
				json);
		return bean.as();
	}

	/**
	 * IRegisterDTO
	 * 
	 * @param json
	 * @return
	 */
	public IRegisterDTO createRegisterDTO() {
		AutoBean<IRegisterDTO> bean = factory.registerDTO();
		return bean.as();
	}

	public IRegisterDTO createRegisterDTO(IRegisterDTO toWrap) {
		AutoBean<IRegisterDTO> bean = factory.registerDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IRegisterDTO data) {
		AutoBean<IRegisterDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IRegisterDTO deserializeFromJsonToRegisterDTO(String json) {
		AutoBean<IRegisterDTO> bean = AutoBeanCodex.decode(factory,
				IRegisterDTO.class, json);
		return bean.as();
	}

	/**
	 * IP2pKeyDTO
	 * 
	 * @param json
	 * @return
	 */
	public IP2pKeyDTO createP2pKeyDTO() {
		AutoBean<IP2pKeyDTO> bean = factory.p2pKeyDTO();
		return bean.as();
	}

	public String serializeToJson(IP2pKeyDTO data) {
		AutoBean<IP2pKeyDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	/**
	 * IFolderAndFileDTO
	 * 
	 * @param json
	 * @return
	 */
	public IFolderAndFileDTO createFolderAndFileDTO() {
		AutoBean<IFolderAndFileDTO> bean = factory.folderAndFileDTO();
		return bean.as();
	}

	public String serializeToJson(IFolderAndFileDTO data) {
		AutoBean<IFolderAndFileDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IFolderAndFileDTO deserializeFromJsonToFolderAndFileDTO(String json) {
		AutoBean<IFolderAndFileDTO> bean = AutoBeanCodex.decode(factory,
				IFolderAndFileDTO.class, json);
		return bean.as();
	}

	/**
	 * IAcceptFileDTO
	 * 
	 * @param json
	 * @return
	 */
	public IAcceptFileDTO createAcceptFileDTO() {
		AutoBean<IAcceptFileDTO> bean = factory.acceptFileDTO();
		return bean.as();
	}

	public String serializeToJson(IAcceptFileDTO data) {
		AutoBean<IAcceptFileDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	/**
	 * IChatGroupDTO
	 * 
	 * @param json
	 * @return
	 */
	public IChatGroupDTO createChatGroupDTO() {
		AutoBean<IChatGroupDTO> bean = factory.chatGroupDTO();
		return bean.as();
	}

	public IChatGroupDTO createChatGroupDTO(IChatGroupDTO toWrap) {
		AutoBean<IChatGroupDTO> bean = factory.chatGroupDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IChatGroupDTO data) {
		AutoBean<IChatGroupDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IChatGroupDTO deserializeFromJsonToChatGroupDTO(String json) {
		AutoBean<IChatGroupDTO> bean = AutoBeanCodex.decode(factory,
				IChatGroupDTO.class, json);
		return bean.as();
	}

	/**
	 * IGetHistoryDTO
	 * 
	 * @param json
	 * @return
	 */
	public IGetHistoryDTO getHistoryDTO() {
		AutoBean<IGetHistoryDTO> bean = factory.getHistoryDTO();
		return bean.as();
	}

	public String serializeToJson(IGetHistoryDTO data) {
		AutoBean<IGetHistoryDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	/**
	 * IEnterpriseDTO
	 * 
	 * @param json
	 * @return
	 */
	public IEnterpriseDTO enterpriseDTO() {
		AutoBean<IEnterpriseDTO> bean = factory.enterpriseDTO();
		return bean.as();
	}

	public IEnterpriseDTO enterpriseDTO(IEnterpriseDTO toWrap) {
		AutoBean<IEnterpriseDTO> bean = factory.enterpriseDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IEnterpriseDTO data) {
		AutoBean<IEnterpriseDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IEnterpriseDTO deserializeFromJsonToEnterpriseDTO(String json) {
		AutoBean<IEnterpriseDTO> bean = AutoBeanCodex.decode(factory,
				IEnterpriseDTO.class, json);
		return bean.as();
	}

	/**
	 * clone
	 * 
	 * @param enterprise
	 * @return
	 */
	public IEnterpriseDTO clone(IEnterpriseDTO enterprise) {
		IEnterpriseDTO ent = enterpriseDTO();
		ent.setEnterpriseId(enterprise.getEnterpriseId());
		ent.setEnterpriseName(enterprise.getEnterpriseName());
		ent.setPhone(enterprise.getPhone());
		ent.setFax(enterprise.getFax());
		ent.setAddress(enterprise.getAddress());
		ent.setPostcode(enterprise.getPostcode());
		ent.setWebsite(enterprise.getWebsite());
		ent.setAdminPassword(enterprise.getAdminPassword());
		ent.setMaxEmployees(enterprise.getMaxEmployees());
		ent.setLogo(enterprise.getLogo());
		ent.setSkin(enterprise.getSkin());
		ent.setEmployeePassword(enterprise.getEmployeePassword());
		ent.setProductKey(enterprise.getProductKey());
		ent.setDiskSize(enterprise.getDiskSize());
		ent.setCloudDiskIp(enterprise.getCloudDiskIp());
		ent.setRegisterTime(enterprise.getRegisterTime());
		ent.setExpirationTime(enterprise.getExpirationTime());
		ent.setMobile(enterprise.getMobile());
		ent.setMail(enterprise.getMail());
		ent.setContact(enterprise.getContact());
		ent.setShareDiskVersion(enterprise.getShareDiskVersion());
		ent.setPersonalDiskSize(enterprise.getPersonalDiskSize());
		ent.setPersonDiskUsed(enterprise.getPersonDiskUsed());
		ent.setMaxShareLinkDownload(enterprise.getMaxShareLinkDownload());
		ent.setShareLinkDownCount(enterprise.getShareLinkDownCount());
		ent.setStatus(enterprise.getStatus());
		ent.setLocale(enterprise.getLocale());
		ent.setNet(enterprise.isNet());
		ent.setEntModule(enterprise.getEntModule());
		return ent;
	}

	/**
	 * IEnterpriseLoginDTO
	 * 
	 * @param json
	 * @return
	 */
	public IEnterpriseLoginDTO enterpriseLoginDTO() {
		AutoBean<IEnterpriseLoginDTO> bean = factory.enterpriseLoginDTO();
		return bean.as();
	}

	public IEnterpriseLoginDTO createEnterpriseLoginDTO(
			IEnterpriseLoginDTO toWrap) {
		AutoBean<IEnterpriseLoginDTO> bean = factory.enterpriseLoginDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IEnterpriseLoginDTO data) {
		AutoBean<IEnterpriseLoginDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	/**
	 * IDepartmentsDTO
	 * 
	 * @param json
	 * @return
	 */
	public IDepartmentsDTO departmentsDTO() {
		AutoBean<IDepartmentsDTO> bean = factory.departmentsDTO();
		return bean.as();
	}

	public IDepartmentsDTO departmentsDTO(IDepartmentsDTO toWrap) {
		AutoBean<IDepartmentsDTO> bean = factory.departmentsDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IDepartmentsDTO data) {
		AutoBean<IDepartmentsDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IDepartmentsDTO deserializeFromJsonToDepartmentsDTO(String json) {
		AutoBean<IDepartmentsDTO> bean = AutoBeanCodex.decode(factory,
				IDepartmentsDTO.class, json);
		return bean.as();
	}

	/**
	 * IDepartmentDTO
	 * 
	 * @param json
	 * @return
	 */
	public IDepartmentDTO departmentDTO() {
		AutoBean<IDepartmentDTO> bean = factory.departmentDTO();
		return bean.as();
	}

	public IDepartmentDTO departmentDTO(IDepartmentDTO toWrap) {
		AutoBean<IDepartmentDTO> bean = factory.departmentDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IDepartmentDTO data) {
		AutoBean<IDepartmentDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IDepartmentDTO deserializeFromJsonToDepartmentDTO(String json) {
		AutoBean<IDepartmentDTO> bean = AutoBeanCodex.decode(factory,
				IDepartmentDTO.class, json);
		return bean.as();
	}

	/**
	 * IEnterpriseUserDTO
	 * 
	 * @param json
	 * @return
	 */
	public IEnterpriseUserDTO enterpriseUserDTO() {
		AutoBean<IEnterpriseUserDTO> bean = factory.enterpriseUserDTO();
		return bean.as();
	}

	public IEnterpriseUserDTO enterpriseUserDTO(IEnterpriseUserDTO toWrap) {
		AutoBean<IEnterpriseUserDTO> bean = factory.enterpriseUserDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IEnterpriseUserDTO data) {
		AutoBean<IEnterpriseUserDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	/**
	 * ICustomerDTO
	 * 
	 * @param json
	 * @return
	 */
	public ICustomerDTO customerDTO() {
		AutoBean<ICustomerDTO> bean = factory.customerDTO();
		return bean.as();
	}

	public ICustomerDTO customerDTO(ICustomerDTO toWrap) {
		AutoBean<ICustomerDTO> bean = factory.customerDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(ICustomerDTO data) {
		AutoBean<ICustomerDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public ICustomerDTO deserializeFromJsonToCustomerDTO(String json) {
		AutoBean<ICustomerDTO> bean = AutoBeanCodex.decode(factory,
				ICustomerDTO.class, json);
		return bean.as();
	}

	/**
	 * ICustomerClientToken
	 * 
	 * @param json
	 * @return
	 */
	public ICustomerClientToken customerClientToken() {
		AutoBean<ICustomerClientToken> bean = factory.customerClientToken();
		return bean.as();
	}

	public ICustomerClientToken customerClientToken(ICustomerClientToken toWrap) {
		AutoBean<ICustomerClientToken> bean = factory
				.customerClientToken(toWrap);
		return bean.as();
	}

	public String serializeToJson(ICustomerClientToken data) {
		AutoBean<ICustomerClientToken> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public ICustomerClientToken deserializeFromJsonToCustomerClientToken(
			String json) {
		AutoBean<ICustomerClientToken> bean = AutoBeanCodex.decode(factory,
				ICustomerClientToken.class, json);
		return bean.as();
	}

	/**
	 * IShareFolderDTO
	 * 
	 * @param json
	 * @return
	 */
	public IShareFolderDTO shareFolderDTO() {
		AutoBean<IShareFolderDTO> bean = factory.shareFolderDTO();
		return bean.as();
	}

	public IShareFolderDTO shareFolderDTO(IShareFolderDTO toWrap) {
		AutoBean<IShareFolderDTO> bean = factory.shareFolderDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IShareFolderDTO data) {
		AutoBean<IShareFolderDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IShareFolderDTO deserializeFromJsonToShareFolderDTO(String json) {
		AutoBean<IShareFolderDTO> bean = AutoBeanCodex.decode(factory,
				IShareFolderDTO.class, json);
		return bean.as();
	}

	public IShareFolderDTO clone(IShareFolderDTO folderDTO) {
		IShareFolderDTO folder = shareFolderDTO();
		folder.setFolderId(folderDTO.getFolderId());
		folder.setParentId(folderDTO.getParentId());
		folder.setEnterpriseId(folderDTO.getEnterpriseId());
		folder.setName(folderDTO.getName());
		folder.setDeleted(folderDTO.getDeleted());
		folder.setMaxSize(folderDTO.getMaxSize());
		folder.setShareLinkId(folderDTO.getShareLinkId());
		folder.setUserId(folderDTO.getUserId());
		folder.setThumb(folderDTO.getThumb());
		folder.setRemark(folderDTO.getRemark());
		folder.setVersion(folderDTO.getVersion());
		return folder;
	}

	/**
	 * IShareFileDTO
	 * 
	 * @param json
	 * @return
	 */
	public IShareFileDTO shareFileDTO() {
		AutoBean<IShareFileDTO> bean = factory.shareFileDTO();
		return bean.as();
	}

	public IShareFileDTO shareFileDTO(IShareFileDTO toWrap) {
		AutoBean<IShareFileDTO> bean = factory.shareFileDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IShareFileDTO data) {
		AutoBean<IShareFileDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IShareFileDTO fromJsonToShareFile(String json) {
		AutoBean<IShareFileDTO> bean = AutoBeanCodex.decode(factory,
				IShareFileDTO.class, json);
		return bean.as();
	}

	public IShareFileDTO clone(IShareFileDTO fileDTO) {
		IShareFileDTO file = shareFileDTO();
		file.setFileId(fileDTO.getFileId().longValue());
		file.setEnterpriseId(fileDTO.getEnterpriseId());
		file.setFolderId(fileDTO.getFolderId().longValue());
		file.setGuid(fileDTO.getGuid());
		file.setSize(fileDTO.getSize());
		file.setType(fileDTO.getType());
		file.setCreateTime(fileDTO.getCreateTime());
		file.setUpdateTime(fileDTO.getUpdateTime());
		file.setName(fileDTO.getName());
		file.setDeleted(fileDTO.getDeleted());
		file.setShareLinkId(fileDTO.getShareLinkId());
		file.setCloudDiskIp(fileDTO.getCloudDiskIp());
		file.setLockByUserId(fileDTO.getLockByUserId());
		file.setLockByUser(file.getLockByUser());
		file.setUserId(fileDTO.getUserId());
		file.setThumb(fileDTO.getThumb());
		file.setRemark(fileDTO.getRemark());
		file.setVersion(fileDTO.getVersion());
		return file;
	}

	/**
	 * IShareFilesDTO
	 * 
	 * @param json
	 * @return
	 */
	public IShareFilesDTO shareFilesDTO() {
		AutoBean<IShareFilesDTO> bean = factory.shareFilesDTO();
		return bean.as();
	}

	public IShareFilesDTO shareFilesDTO(IShareFilesDTO toWrap) {
		AutoBean<IShareFilesDTO> bean = factory.shareFilesDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IShareFilesDTO data) {
		AutoBean<IShareFilesDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IShareFilesDTO fromJsonToShareFilesDTO(String json) {
		AutoBean<IShareFilesDTO> bean = AutoBeanCodex.decode(factory,
				IShareFilesDTO.class, json);
		return bean.as();
	}

	/**
	 * IShareFoldersDTO
	 * 
	 * @param json
	 * @return
	 */
	public IShareFoldersDTO shareFoldersDTO() {
		AutoBean<IShareFoldersDTO> bean = factory.shareFoldersDTO();
		return bean.as();
	}

	public IShareFoldersDTO shareFoldersDTO(IShareFoldersDTO toWrap) {
		AutoBean<IShareFoldersDTO> bean = factory.shareFoldersDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IShareFoldersDTO data) {
		AutoBean<IShareFoldersDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IShareFoldersDTO fromJsonToShareFoldersDTO(String json) {
		AutoBean<IShareFoldersDTO> bean = AutoBeanCodex.decode(factory,
				IShareFoldersDTO.class, json);
		return bean.as();
	}

	/**
	 * IShareFolderAndFileDTO
	 * 
	 * @param json
	 * @return
	 */
	public IShareFolderAndFileDTO shareFolderAndFileDTO() {
		AutoBean<IShareFolderAndFileDTO> bean = factory.shareFolderAndFileDTO();
		return bean.as();
	}

	public IShareFolderAndFileDTO shareFolderAndFileDTO(
			IShareFolderAndFileDTO toWrap) {
		AutoBean<IShareFolderAndFileDTO> bean = factory
				.shareFolderAndFileDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IShareFolderAndFileDTO data) {
		AutoBean<IShareFolderAndFileDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IShareFolderAndFileDTO fromJsonToShareFolderAndFile(String json) {
		AutoBean<IShareFolderAndFileDTO> bean = AutoBeanCodex.decode(factory,
				IShareFolderAndFileDTO.class, json);
		return bean.as();
	}

	/**
	 * IDepartmentAndUserDTO
	 * 
	 * @param json
	 * @return
	 */
	public IDepartmentAndUserDTO deserializeFromJsonToDepartmentAndUserDTO(
			String json) {
		AutoBean<IDepartmentAndUserDTO> bean = AutoBeanCodex.decode(factory,
				IDepartmentAndUserDTO.class, json);
		return bean.as();
	}

	/**
	 * IRoleDTO
	 * 
	 * @param json
	 * @return
	 */
	public IRoleDTO roleDTO() {
		AutoBean<IRoleDTO> bean = factory.roleDTO();
		return bean.as();
	}

	public IRoleDTO roleDTO(IRoleDTO toWrap) {
		AutoBean<IRoleDTO> bean = factory.roleDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IRoleDTO data) {
		AutoBean<IRoleDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IRoleDTO deserializeFromJsonToRoleDTO(String json) {
		AutoBean<IRoleDTO> bean = AutoBeanCodex.decode(factory, IRoleDTO.class,
				json);
		return bean.as();
	}

	/**
	 * IRolesDTO
	 * 
	 * @param json
	 * @return
	 */
	public IRolesDTO rolesDTO() {
		AutoBean<IRolesDTO> bean = factory.rolesDTO();
		return bean.as();
	}

	public String serializeToJson(IRolesDTO data) {
		AutoBean<IRolesDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IRolesDTO deserializeFromJsonToRolesDTO(String json) {
		AutoBean<IRolesDTO> bean = AutoBeanCodex.decode(factory,
				IRolesDTO.class, json);
		return bean.as();
	}

	/**
	 * IRolePermissionDTO
	 * 
	 * @param json
	 * @return
	 */
	public IRolePermissionDTO rolePermissionDTO() {
		AutoBean<IRolePermissionDTO> bean = factory.rolePermissionDTO();
		return bean.as();
	}

	public IRolePermissionDTO rolePermissionDTO(IRolePermissionDTO toWrap) {
		AutoBean<IRolePermissionDTO> bean = factory.rolePermissionDTO(toWrap);
		return bean.as();
	}

	public IRolePermissionDTO clone(IRolePermissionDTO p) {
		IRolePermissionDTO c = rolePermissionDTO();
		c.setRoleId(p.getRoleId());
		c.setFolderId(p.getFolderId());
		c.setRead(p.isRead());
		c.setWrite(p.isWrite());
		c.setDownload(p.isDownload());
		c.setUpload(p.isUpload());
		c.setDelete(p.isDelete());
		c.setList(p.isList());
		c.setShare(p.isShare());
		c.setLocal(p.isLocal());
		return c;
	}

	/**
	 * IRolePermissionsDTO
	 * 
	 * @param json
	 * @return
	 */
	public IRolePermissionsDTO rolePermissionsDTO() {
		AutoBean<IRolePermissionsDTO> bean = factory.rolePermissionsDTO();
		return bean.as();
	}

	public String serializeToJson(IRolePermissionsDTO data) {
		AutoBean<IRolePermissionsDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IRolePermissionsDTO deserializeFromJsonToRolePermissionsDTO(
			String json) {
		AutoBean<IRolePermissionsDTO> bean = AutoBeanCodex.decode(factory,
				IRolePermissionsDTO.class, json);
		return bean.as();
	}

	/**
	 * IUserRoleDTO
	 * 
	 * @param json
	 * @return
	 */
	public IUserRoleDTO userRoleDTO() {
		AutoBean<IUserRoleDTO> bean = factory.userRoleDTO();
		return bean.as();
	}

	public IUserRoleDTO userRoleDTO(IUserRoleDTO toWrap) {
		AutoBean<IUserRoleDTO> bean = factory.userRoleDTO(toWrap);
		return bean.as();
	}

	/**
	 * IUserRolesDTO
	 * 
	 * @param json
	 * @return
	 */
	public IUserRolesDTO userRolesDTO() {
		AutoBean<IUserRolesDTO> bean = factory.userRolesDTO();
		return bean.as();
	}

	public String serializeToJson(IUserRolesDTO data) {
		AutoBean<IUserRolesDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IUserRolesDTO deserializeFromJsonToUserRolesDTO(String json) {
		AutoBean<IUserRolesDTO> bean = AutoBeanCodex.decode(factory,
				IUserRolesDTO.class, json);
		return bean.as();
	}

	/**
	 * ICheckPermissionDTO
	 * 
	 * @param json
	 * @return
	 */
	public ICheckPermissionDTO checkPermissionDTO() {
		AutoBean<ICheckPermissionDTO> bean = factory.checkPermissionDTO();
		return bean.as();
	}

	public String serializeToJson(ICheckPermissionDTO data) {
		AutoBean<ICheckPermissionDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	/**
	 * IFileDTO
	 * 
	 * @param json
	 * @return
	 */
	public IFileDTO fileDTO() {
		AutoBean<IFileDTO> bean = factory.fileDTO();
		return bean.as();
	}

	public IFileDTO fileDTO(IFileDTO toWrap) {
		AutoBean<IFileDTO> bean = factory.fileDTO(toWrap);
		return bean.as();
	}

	public IFileDTO deserializeFromJsonToFileDTO(String json) {
		AutoBean<IFileDTO> bean = AutoBeanCodex.decode(factory, IFileDTO.class,
				json);
		return bean.as();
	}

	/**
	 * ISharePersonalFileDTO
	 * 
	 * @param json
	 * @return
	 */
	public ISharePersonalFileDTO sharePersonalFileDTO() {
		AutoBean<ISharePersonalFileDTO> bean = factory.sharePersonalFileDTO();
		return bean.as();
	}

	public String serializeToJson(ISharePersonalFileDTO data) {
		AutoBean<ISharePersonalFileDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	/**
	 * IReLoginDTO
	 * 
	 * @param json
	 * @return
	 */
	public IReLoginDTO reLoginDTO() {
		AutoBean<IReLoginDTO> bean = factory.reLoginDTO();
		return bean.as();
	}

	public String serializeToJson(IReLoginDTO data) {
		AutoBean<IReLoginDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	/**
	 * IShareFileNewMessageDTO
	 * 
	 * @param json
	 * @return
	 */
	public IShareFileNewMessageDTO shareFileNewMessageDTO() {
		AutoBean<IShareFileNewMessageDTO> bean = factory
				.shareFileNewMessageDTO();
		return bean.as();
	}

	public String serializeToJson(IShareFileNewMessageDTO data) {
		AutoBean<IShareFileNewMessageDTO> bean = AutoBeanUtils
				.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IShareFileNewMessageDTO deserializeFromJsonToShareFileNewMessageDTO(
			String json) {
		AutoBean<IShareFileNewMessageDTO> bean = AutoBeanCodex.decode(factory,
				IShareFileNewMessageDTO.class, json);
		return bean.as();
	}

	/**
	 * IBlockDTO
	 * 
	 * @param json
	 * @return
	 */
	public IBlockDTO blockDTO() {
		AutoBean<IBlockDTO> bean = factory.blockDTO();
		return bean.as();
	}

	public String serializeToJson(IBlockDTO data) {
		AutoBean<IBlockDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IBlockDTO deserializeFromJsonToBlockDTO(String json) {
		AutoBean<IBlockDTO> bean = AutoBeanCodex.decode(factory,
				IBlockDTO.class, json);
		return bean.as();
	}

	/**
	 * IBlocksDTO
	 * 
	 * @param json
	 * @return
	 */
	public IBlocksDTO blocksDTO() {
		AutoBean<IBlocksDTO> bean = factory.blocksDTO();
		return bean.as();
	}

	public String serializeToJson(IBlocksDTO data) {
		AutoBean<IBlocksDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IBlocksDTO deserializeFromJsonToBlocksDTO(String json) {
		AutoBean<IBlocksDTO> bean = AutoBeanCodex.decode(factory,
				IBlocksDTO.class, json);
		return bean.as();
	}

	/**
	 * IUsualContactDTO
	 * 
	 * @param json
	 * @return
	 */
	public IUsualContactDTO usualContactDTO() {
		AutoBean<IUsualContactDTO> bean = factory.usualContactDTO();
		return bean.as();
	}

	public String serializeToJson(IUsualContactDTO data) {
		AutoBean<IUsualContactDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IUsualContactDTO deserializeFromJsonToUsualContactDTO(String json) {
		AutoBean<IUsualContactDTO> bean = AutoBeanCodex.decode(factory,
				IUsualContactDTO.class, json);
		return bean.as();
	}

	/**
	 * IShareFileNewDTO
	 * 
	 * @param json
	 * @return
	 */
	public IShareFileNewDTO shareFileNewDTO() {
		AutoBean<IShareFileNewDTO> bean = factory.shareFileNewDTO();
		return bean.as();
	}

	/**
	 * IShareFolderNewDTO
	 * 
	 * @param json
	 * @return
	 */
	public IShareFolderNewDTO shareFolderNewDTO() {
		AutoBean<IShareFolderNewDTO> bean = factory.shareFolderNewDTO();
		return bean.as();
	}

	/**
	 * IDownFileDTO
	 * 
	 * @param json
	 * @return
	 */
	public IDownFileDTO downFileDTO() {
		AutoBean<IDownFileDTO> bean = factory.downFileDTO();
		return bean.as();
	}

	public IDownFileDTO downFileDTO(IDownFileDTO toWrap) {
		AutoBean<IDownFileDTO> bean = factory.downFileDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IDownFileDTO data) {
		AutoBean<IDownFileDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	/**
	 * IComplexFolderDTO
	 * 
	 * @param json
	 * @return
	 */
	public IComplexFolderDTO complexFolderDTO() {
		AutoBean<IComplexFolderDTO> bean = factory.complexFolderDTO();
		return bean.as();
	}

	public IComplexFolderDTO downFileDTO(IComplexFolderDTO toWrap) {
		AutoBean<IComplexFolderDTO> bean = factory.complexFolderDTO(toWrap);
		return bean.as();
	}

	/**
	 * IFolderDTO
	 * 
	 * @param json
	 * @return
	 */
	public IFolderDTO folderDTO() {
		AutoBean<IFolderDTO> bean = factory.folderDTO();
		return bean.as();
	}

	public IFolderDTO folderDTO(IFolderDTO toWrap) {
		AutoBean<IFolderDTO> bean = factory.folderDTO(toWrap);
		return bean.as();
	}

	/**
	 * IConferenceDTO
	 * 
	 * @param json
	 * @return
	 */
	public IConferenceDTO conferenceDTO() {
		AutoBean<IConferenceDTO> bean = factory.conferenceDTO();
		return bean.as();
	}

	public IConferenceDTO conferenceDTO(IConferenceDTO toWrap) {
		AutoBean<IConferenceDTO> bean = factory.conferenceDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IConferenceDTO data) {
		AutoBean<IConferenceDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IConferenceDTO deserializeFromJsonToConferenceDTO(String json) {
		AutoBean<IConferenceDTO> bean = AutoBeanCodex.decode(factory,
				IConferenceDTO.class, json);
		return bean.as();
	}

	public IConferenceDTO clone(IConferenceDTO conferenceDTO) {
		IConferenceDTO con = conferenceDTO();
		con.setId(conferenceDTO.getId());
		con.setEnterpriseId(conferenceDTO.getEnterpriseId());
		con.setTheme(conferenceDTO.getTheme());
		con.setContent(conferenceDTO.getContent());
		con.setStartTime(conferenceDTO.getStartTime());
		con.setLength(conferenceDTO.getLength());
		con.setStatus(conferenceDTO.getStatus());
		con.setCreaterId(conferenceDTO.getCreaterId());
		con.setCreaterName(conferenceDTO.getCreaterName());
		con.setCreateTime(conferenceDTO.getCreateTime());
		con.setEnterpriseId(conferenceDTO.getEnterpriseId());
		con.setRecord(con.isRecord());
		con.setType(conferenceDTO.getType());
		con.setMembers(conferenceDTO.getMembers());
		return con;
	}

	/**
	 * IConferenceMemberDTO
	 * 
	 * @param json
	 * @return
	 */
	public IConferenceMemberDTO conferenceMemberDTO() {
		AutoBean<IConferenceMemberDTO> bean = factory.conferenceMemberDTO();
		return bean.as();
	}

	public IConferenceMemberDTO conferenceMemberDTO(IConferenceMemberDTO toWrap) {
		AutoBean<IConferenceMemberDTO> bean = factory
				.conferenceMemberDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IConferenceMemberDTO data) {
		AutoBean<IConferenceMemberDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IConferenceMemberDTO deserializeFromJsonToConferenceMemberDTO(
			String json) {
		AutoBean<IConferenceMemberDTO> bean = AutoBeanCodex.decode(factory,
				IConferenceMemberDTO.class, json);
		return bean.as();
	}

	/**
	 * IConferencesDTO
	 * 
	 * @param json
	 * @return
	 */
	public IConferencesDTO conferencesDTO() {
		AutoBean<IConferencesDTO> bean = factory.conferencesDTO();
		return bean.as();
	}

	public IConferencesDTO conferencesDTO(IConferencesDTO toWrap) {
		AutoBean<IConferencesDTO> bean = factory.conferencesDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IConferencesDTO data) {
		AutoBean<IConferencesDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IConferencesDTO deserializeFromJsonToConferencesDTO(String json) {
		AutoBean<IConferencesDTO> bean = AutoBeanCodex.decode(factory,
				IConferencesDTO.class, json);
		return bean.as();
	}

	/**
	 * IConferenceMembersDTO
	 * 
	 * @param json
	 * @return
	 */
	public IConferenceMembersDTO conferenceMembersDTO() {
		AutoBean<IConferenceMembersDTO> bean = factory.conferenceMembersDTO();
		return bean.as();
	}

	public IConferenceMembersDTO conferenceMembersDTO(
			IConferenceMembersDTO toWrap) {
		AutoBean<IConferenceMembersDTO> bean = factory
				.conferenceMembersDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IConferenceMembersDTO data) {
		AutoBean<IConferenceMembersDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IConferenceMembersDTO deserializeFromJsonToConferenceMembersDTO(
			String json) {
		AutoBean<IConferenceMembersDTO> bean = AutoBeanCodex.decode(factory,
				IConferenceMembersDTO.class, json);
		return bean.as();
	}

	/**
	 * ICheckPermissionsDTO
	 * 
	 * @param json
	 * @return
	 */
	public ICheckPermissionsDTO checkPermissionsDTO() {
		AutoBean<ICheckPermissionsDTO> bean = factory.checkPermissionsDTO();
		return bean.as();
	}

	public String serializeToJson(ICheckPermissionsDTO data) {
		AutoBean<ICheckPermissionsDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	/**
	 * IMailAccountDTO
	 * 
	 * @return
	 */
	public IMailAccountDTO createMailAccountDTO() {
		AutoBean<IMailAccountDTO> bean = factory.mailAccountDTO();
		return bean.as();
	}

	public String toJson(IMailAccountDTO data) {
		return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(data))
				.getPayload();
	}

	public String toJson(IPersonDiskAllocDTO data) {
		AutoBean<IPersonDiskAllocDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public String toJson(IPersonDiskAllocListDTO data) {
		AutoBean<IPersonDiskAllocListDTO> bean = AutoBeanUtils
				.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public String toJson(IMailQueryDTO data) {
		AutoBean<IMailQueryDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IMailAccountDTO deserializeFromJsonToMailAccountDTO(String json) {
		AutoBean<IMailAccountDTO> bean = AutoBeanCodex.decode(factory,
				IMailAccountDTO.class, json);
		return bean.as();
	}

	public IPersonDiskAllocListDTO personDiskAllocListDTO() {
		AutoBean<IPersonDiskAllocListDTO> bean = factory
				.personDiskAllocListDTO();
		return bean.as();
	}

	public IPersonDiskAllocDTO personDiskAllocDTO() {
		AutoBean<IPersonDiskAllocDTO> bean = factory.personDiskAllocDTO();
		return bean.as();
	}

	public IPersonDiskAllocDTO personDiskAllocDTO(IPersonDiskAllocDTO toWrap) {
		AutoBean<IPersonDiskAllocDTO> bean = factory.personDiskAllocDTO(toWrap);
		return bean.as();
	}

	/**
	 * IMailAccountsDTO
	 * 
	 * @return
	 */
	public IMailAccountsDTO mailAccountsDTO() {
		AutoBean<IMailAccountsDTO> bean = factory.mailAccountsDTO();
		return bean.as();
	}

	public IMailListDTO createIMailListDTO() {
		AutoBean<IMailListDTO> bean = factory.createIMailListDTO();
		return bean.as();
	}

	public String toJson(IMailListDTO data) {
		AutoBean<IMailListDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public String toJson(IMailDTO data) {
		AutoBean<IMailDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public String serializeToJson(IMailAccountsDTO data) {
		AutoBean<IMailAccountsDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IMailAccountsDTO deserializeFromJsonToMailAccountsDTO(String json) {
		AutoBean<IMailAccountsDTO> bean = AutoBeanCodex.decode(factory,
				IMailAccountsDTO.class, json);
		return bean.as();
	}

	/**
	 * IMailFolderDTO
	 * 
	 * @return
	 */
	public IMailFolderDTO mailFolderDTO() {
		AutoBean<IMailFolderDTO> bean = factory.mailFolderDTO();
		return bean.as();
	}

	public String serializeToJson(IMailFolderDTO data) {
		AutoBean<IMailFolderDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IMailFolderDTO deserializeFromJsonToMailFolderDTO(String json) {
		AutoBean<IMailFolderDTO> bean = AutoBeanCodex.decode(factory,
				IMailFolderDTO.class, json);
		return bean.as();
	}

	/**
	 * IMailFoldersDTO
	 * 
	 * @return
	 */
	public IMailFoldersDTO mailFoldersDTO() {
		AutoBean<IMailFoldersDTO> bean = factory.mailFoldersDTO();
		return bean.as();
	}

	public String serializeToJson(IMailFoldersDTO data) {
		AutoBean<IMailFoldersDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IMailFoldersDTO deserializeFromJsonToMailFoldersDTO(String json) {
		AutoBean<IMailFoldersDTO> bean = AutoBeanCodex.decode(factory,
				IMailFoldersDTO.class, json);
		return bean.as();
	}

	public IMailListDTO fromJsonToIMailListDTO(String json) {
		AutoBean<IMailListDTO> bean = AutoBeanCodex.decode(factory,
				IMailListDTO.class, json);
		return bean.as();
	}

	public IMailDTO fromJsonToMailDTO(String json) {
		AutoBean<IMailDTO> bean = AutoBeanCodex.decode(factory, IMailDTO.class,
				json);
		return bean.as();
	}

	public IMailFolderDTO fromJsonToMailFolderDTO(String json) {
		AutoBean<IMailFolderDTO> bean = AutoBeanCodex.decode(factory,
				IMailFolderDTO.class, json);
		return bean.as();
	}

	/**
	 * IMailContactGroupDTO
	 * 
	 * @return
	 */
	public IMailContactGroupDTO mailContactGroupDTO() {
		AutoBean<IMailContactGroupDTO> bean = factory.mailContactGroupDTO();
		return bean.as();
	}

	public String serializeToJson(IMailContactGroupDTO data) {
		AutoBean<IMailContactGroupDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IMailContactGroupDTO deserializeFromJsonToMailContactGroupDTO(
			String json) {
		AutoBean<IMailContactGroupDTO> bean = AutoBeanCodex.decode(factory,
				IMailContactGroupDTO.class, json);
		return bean.as();
	}

	/**
	 * IMailContactDTO
	 * 
	 * @return
	 */
	public IMailContactDTO mailContactDTO() {
		AutoBean<IMailContactDTO> bean = factory.mailContactDTO();
		return bean.as();
	}

	public String serializeToJson(IMailContactDTO data) {
		AutoBean<IMailContactDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IMailContactDTO deserializeFromJsonToMailContactDTO(String json) {
		AutoBean<IMailContactDTO> bean = AutoBeanCodex.decode(factory,
				IMailContactDTO.class, json);
		return bean.as();
	}

	/**
	 * IConferenceDocDTO
	 * 
	 * @param json
	 * @return
	 */
	public IConferenceDocDTO conferenceDocDTO() {
		AutoBean<IConferenceDocDTO> bean = factory.conferenceDocDTO();
		return bean.as();
	}

	public IConferenceDocDTO conferenceDocDTO(IConferenceDocDTO toWrap) {
		AutoBean<IConferenceDocDTO> bean = factory.conferenceDocDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IConferenceDocDTO data) {
		AutoBean<IConferenceDocDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IConferenceDocDTO deserializeFromJsonToConferenceDocDTO(String json) {
		AutoBean<IConferenceDocDTO> bean = AutoBeanCodex.decode(factory,
				IConferenceDocDTO.class, json);
		return bean.as();
	}

	/**
	 * IConferenceDocsDTO
	 * 
	 * @param json
	 * @return
	 */
	public IConferenceDocsDTO conferenceDocsDTO() {
		AutoBean<IConferenceDocsDTO> bean = factory.conferenceDocsDTO();
		return bean.as();
	}

	public IConferenceDocsDTO conferenceDocsDTO(IConferenceDocsDTO toWrap) {
		AutoBean<IConferenceDocsDTO> bean = factory.conferenceDocsDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IConferenceDocsDTO data) {
		AutoBean<IConferenceDocsDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IConferenceDocsDTO deserializeFromJsonToConferenceDocsDTO(String json) {
		AutoBean<IConferenceDocsDTO> bean = AutoBeanCodex.decode(factory,
				IConferenceDocsDTO.class, json);
		return bean.as();
	}

	/**
	 * IMailContactGroupsDTO
	 * 
	 * @param json
	 * @return
	 */
	public IMailContactGroupsDTO mailContactGroupsDTO() {
		AutoBean<IMailContactGroupsDTO> bean = factory.mailContactGroupsDTO();
		return bean.as();
	}

	public IMailContactGroupsDTO mailContactGroupsDTO(
			IMailContactGroupsDTO toWrap) {
		AutoBean<IMailContactGroupsDTO> bean = factory
				.mailContactGroupsDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IMailContactGroupsDTO data) {
		AutoBean<IMailContactGroupsDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IMailContactGroupsDTO fromJsonToMailContactGroupsDTO(String json) {
		AutoBean<IMailContactGroupsDTO> bean = AutoBeanCodex.decode(factory,
				IMailContactGroupsDTO.class, json);
		return bean.as();
	}

	/**
	 * IPrepareFileAsConferenceDocDTO
	 * 
	 * @return
	 */
	public IPrepareFileAsConferenceDocDTO prepareFileAsConferenceDocDTO() {
		AutoBean<IPrepareFileAsConferenceDocDTO> bean = factory
				.prepareFileAsConferenceDocDTO();
		return bean.as();
	}

	public String serializeToJson(IPrepareFileAsConferenceDocDTO data) {
		AutoBean<IPrepareFileAsConferenceDocDTO> bean = AutoBeanUtils
				.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	/**
	 * IPrepareShareFileAsConferenceDocDTO
	 * 
	 * @return
	 */
	public IPrepareShareFileAsConferenceDocDTO prepareShareFileAsConferenceDocDTO() {
		AutoBean<IPrepareShareFileAsConferenceDocDTO> bean = factory
				.prepareShareFileAsConferenceDocDTO();
		return bean.as();
	}

	public String serializeToJson(IPrepareShareFileAsConferenceDocDTO data) {
		AutoBean<IPrepareShareFileAsConferenceDocDTO> bean = AutoBeanUtils
				.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	/**
	 * IShareLinkDTO
	 * 
	 * @param json
	 * @return
	 */
	public IShareLinkDTO shareLinkDTO() {
		AutoBean<IShareLinkDTO> bean = factory.shareLinkDTO();
		return bean.as();
	}

	public IShareLinkDTO shareLinkDTO(IShareLinkDTO toWrap) {
		AutoBean<IShareLinkDTO> bean = factory.shareLinkDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IShareLinkDTO data) {
		AutoBean<IShareLinkDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IShareLinkDTO deserializeFromJsonToShareLinkDTO(String json) {
		AutoBean<IShareLinkDTO> bean = AutoBeanCodex.decode(factory,
				IShareLinkDTO.class, json);
		return bean.as();
	}

	/**
	 * IShareLinkFilesDTO
	 * 
	 * @param json
	 * @return
	 */
	public IShareLinkFilesDTO deserializeFromJsonToShareLinkFilesDTO(String json) {
		AutoBean<IShareLinkFilesDTO> bean = AutoBeanCodex.decode(factory,
				IShareLinkFilesDTO.class, json);
		return bean.as();
	}

	/**
	 * IShareLinkInfoDTO
	 * 
	 * @param json
	 * @return
	 */
	public IShareLinkInfoDTO deserializeFromJsonToShareLinkInfoDTO(String json) {
		AutoBean<IShareLinkInfoDTO> bean = AutoBeanCodex.decode(factory,
				IShareLinkInfoDTO.class, json);
		return bean.as();
	}

	/**
	 * IShareLinkMailDTO
	 * 
	 * @param json
	 * @return
	 */
	public IShareLinkMailDTO shareLinkMailDTO() {
		AutoBean<IShareLinkMailDTO> bean = factory.shareLinkMailDTO();
		return bean.as();
	}

	public IShareLinkMailDTO shareLinkMailDTO(IShareLinkMailDTO toWrap) {
		AutoBean<IShareLinkMailDTO> bean = factory.shareLinkMailDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IShareLinkMailDTO data) {
		AutoBean<IShareLinkMailDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IMailAttachDTO createMailAttachDTO() {
		AutoBean<IMailAttachDTO> bean = factory.createMailAttachDTO();
		return bean.as();
	}

	/**
	 * ILimitDTO
	 * 
	 * @param json
	 * @return
	 */
	public ILimitDTO deserializeFromJsonToLimitDTO(String json) {
		AutoBean<ILimitDTO> bean = AutoBeanCodex.decode(factory,
				ILimitDTO.class, json);
		return bean.as();
	}

	/**
	 * IFolderAndFileUpdateDTO
	 * 
	 * @param json
	 * @return
	 */
	public IFolderAndFileUpdateDTO folderAndFileUpdateDTO() {
		AutoBean<IFolderAndFileUpdateDTO> bean = factory
				.folderAndFileUpdateDTO();
		return bean.as();
	}

	public IFolderAndFileUpdateDTO folderAndFileUpdateDTO(
			IFolderAndFileUpdateDTO toWrap) {
		AutoBean<IFolderAndFileUpdateDTO> bean = factory
				.folderAndFileUpdateDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IFolderAndFileUpdateDTO data) {
		AutoBean<IFolderAndFileUpdateDTO> bean = AutoBeanUtils
				.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IFolderAndFileUpdateDTO deserializeFromJsonToFolderAndFileUpdateDTO(
			String json) {
		AutoBean<IFolderAndFileUpdateDTO> bean = AutoBeanCodex.decode(factory,
				IFolderAndFileUpdateDTO.class, json);
		return bean.as();
	}

	/**
	 * IPrivateFileUpdateDTO
	 * 
	 * @param json
	 * @return
	 */
	public IPrivateFileUpdateDTO privateFileUpdateDTO() {
		AutoBean<IPrivateFileUpdateDTO> bean = factory.privateFileUpdateDTO();
		return bean.as();
	}

	public IPrivateFileUpdateDTO privateFileUpdateDTO(
			IPrivateFileUpdateDTO toWrap) {
		AutoBean<IPrivateFileUpdateDTO> bean = factory
				.privateFileUpdateDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IPrivateFileUpdateDTO data) {
		AutoBean<IPrivateFileUpdateDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IPrivateFileUpdateDTO deserializeFromJsonToPrivateFileUpdateDTO(
			String json) {
		AutoBean<IPrivateFileUpdateDTO> bean = AutoBeanCodex.decode(factory,
				IPrivateFileUpdateDTO.class, json);
		return bean.as();
	}

	/**
	 * IPrivateFolderUpdateDTO
	 * 
	 * @param json
	 * @return
	 */
	public IPrivateFolderUpdateDTO privateFolderUpdateDTO() {
		AutoBean<IPrivateFolderUpdateDTO> bean = factory
				.privateFolderUpdateDTO();
		return bean.as();
	}

	public IPrivateFolderUpdateDTO privateFileUpdateDTO(
			IPrivateFolderUpdateDTO toWrap) {
		AutoBean<IPrivateFolderUpdateDTO> bean = factory
				.privateFolderUpdateDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IPrivateFolderUpdateDTO data) {
		AutoBean<IPrivateFolderUpdateDTO> bean = AutoBeanUtils
				.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IPrivateFolderUpdateDTO deserializeFromJsonToPrivateFolderUpdateDTO(
			String json) {
		AutoBean<IPrivateFolderUpdateDTO> bean = AutoBeanCodex.decode(factory,
				IPrivateFolderUpdateDTO.class, json);
		return bean.as();
	}

	/**
	 * IShareFolderAndFileUpdateDTO
	 * 
	 * @param json
	 * @return
	 */
	public IShareFolderAndFileUpdateDTO createShareFolderAndFileUpdate() {
		AutoBean<IShareFolderAndFileUpdateDTO> bean = factory
				.shareFolderAndFileUpdateDTO();
		return bean.as();
	}

	public IShareFolderAndFileUpdateDTO shareFolderAndFileUpdateDTO(
			IShareFolderAndFileDTO toWrap) {
		IShareFolderAndFileUpdateDTO updateDTO = createShareFolderAndFileUpdate();
		updateDTO.setForderList(toWrap.getForderList());
		updateDTO.setFileList(toWrap.getFileList());
		updateDTO.setCloudDiskIp(toWrap.getCloudDiskIp());
		updateDTO.setUserId(toWrap.getUserId());
		updateDTO.setVersion(toWrap.getVersion());
		return updateDTO;
	}

	public IShareFolderAndFileUpdateDTO shareFolderAndFileUpdateDTO(
			IShareFolderAndFileUpdateDTO toWrap) {
		AutoBean<IShareFolderAndFileUpdateDTO> bean = factory
				.shareFolderAndFileUpdateDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IShareFolderAndFileUpdateDTO data) {
		AutoBean<IShareFolderAndFileUpdateDTO> bean = AutoBeanUtils
				.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IShareFolderAndFileUpdateDTO deserializeFromJsonToShareFolderAndFileUpdateDTO(
			String json) {
		AutoBean<IShareFolderAndFileUpdateDTO> bean = AutoBeanCodex.decode(
				factory, IShareFolderAndFileUpdateDTO.class, json);
		return bean.as();
	}

	/**
	 * IShareFileUpdateDTO
	 * 
	 * @param json
	 * @return
	 */
	public IShareFileUpdateDTO shareFileUpdateDTO() {
		AutoBean<IShareFileUpdateDTO> bean = factory.shareFileUpdateDTO();
		return bean.as();
	}

	public IShareFileUpdateDTO shareFileUpdateDTO(IShareFileUpdateDTO toWrap) {
		AutoBean<IShareFileUpdateDTO> bean = factory.shareFileUpdateDTO(toWrap);
		return bean.as();
	}

	public IShareFileUpdateDTO shareFileUpdateDTO(IShareFileDTO fileDTO) {
		IShareFileUpdateDTO file = shareFileUpdateDTO();
		file.setFileId(fileDTO.getFileId().longValue());
		file.setEnterpriseId(fileDTO.getEnterpriseId());
		file.setFolderId(fileDTO.getFolderId().longValue());
		file.setGuid(fileDTO.getGuid());
		file.setSize(fileDTO.getSize());
		file.setType(fileDTO.getType());
		file.setCreateTime(fileDTO.getCreateTime());
		file.setUpdateTime(fileDTO.getUpdateTime());
		file.setName(fileDTO.getName());
		file.setDeleted(fileDTO.getDeleted());
		file.setShareLinkId(fileDTO.getShareLinkId());
		file.setCloudDiskIp(fileDTO.getCloudDiskIp());
		file.setLockByUserId(fileDTO.getLockByUserId());
		file.setLockByUser(file.getLockByUser());
		file.setUserId(fileDTO.getUserId());
		file.setThumb(fileDTO.getThumb());
		file.setRemark(fileDTO.getRemark());
		file.setVersion(fileDTO.getVersion());
		return file;
	}

	public String serializeToJson(IShareFileUpdateDTO data) {
		AutoBean<IShareFileUpdateDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IShareFileUpdateDTO deserializeFromJsonToShareFileUpdateDTO(
			String json) {
		AutoBean<IShareFileUpdateDTO> bean = AutoBeanCodex.decode(factory,
				IShareFileUpdateDTO.class, json);
		return bean.as();
	}

	/**
	 * IShareFolderUpdateDTO
	 * 
	 * @param json
	 * @return
	 */
	public IShareFolderUpdateDTO shareFolderUpdateDTO() {
		AutoBean<IShareFolderUpdateDTO> bean = factory.shareFolderUpdateDTO();
		return bean.as();
	}

	public IShareFolderUpdateDTO shareFolderUpdateDTO(IShareFolderDTO toWrap) {
		IShareFolderUpdateDTO updateDTO = shareFolderUpdateDTO();
		updateDTO.setFolderId(toWrap.getFolderId());
		updateDTO.setParentId(toWrap.getParentId());
		updateDTO.setEnterpriseId(toWrap.getEnterpriseId());
		updateDTO.setName(toWrap.getName());
		updateDTO.setDeleted(toWrap.getDeleted());
		updateDTO.setMaxSize(toWrap.getMaxSize());
		updateDTO.setShareLinkId(toWrap.getShareLinkId());
		updateDTO.setUserId(toWrap.getUserId());
		updateDTO.setThumb(toWrap.getThumb());
		updateDTO.setRemark(toWrap.getRemark());
		updateDTO.setVersion(toWrap.getVersion());
		return updateDTO;
	}

	public IShareFolderUpdateDTO shareFolderUpdateDTO(
			IShareFolderUpdateDTO toWrap) {
		AutoBean<IShareFolderUpdateDTO> bean = factory
				.shareFolderUpdateDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IShareFolderUpdateDTO data) {
		AutoBean<IShareFolderUpdateDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IShareFolderUpdateDTO deserializeFromJsonToShareFolderUpdateDTO(
			String json) {
		AutoBean<IShareFolderUpdateDTO> bean = AutoBeanCodex.decode(factory,
				IShareFolderUpdateDTO.class, json);
		return bean.as();
	}

	/**
	 * IShareHistoryDTO
	 * 
	 * @param json
	 * @return
	 */
	public IShareHistoryDTO shareHistoryDTO() {
		AutoBean<IShareHistoryDTO> bean = factory.shareHistoryDTO();
		return bean.as();
	}

	public IShareHistoryDTO shareHistoryDTO(IShareHistoryDTO toWrap) {
		AutoBean<IShareHistoryDTO> bean = factory.shareHistoryDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IShareHistoryDTO data) {
		AutoBean<IShareHistoryDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IShareHistoryDTO deserializeFromJsonToShareHistoryDTO(String json) {
		AutoBean<IShareHistoryDTO> bean = AutoBeanCodex.decode(factory,
				IShareHistoryDTO.class, json);
		return bean.as();
	}

	public IUserImportListDTO fromJsonToUserImportListDTO(String json) {
		AutoBean<IUserImportListDTO> bean = AutoBeanCodex.decode(factory,
				IUserImportListDTO.class, json);
		return bean.as();
	}

	public IUserImportDTO fromJsonToUserImportDTO(String json) {
		AutoBean<IUserImportDTO> bean = AutoBeanCodex.decode(factory,
				IUserImportDTO.class, json);
		return bean.as();
	}

	public IEnterpriseUserListDTO fromJsonToEnterpriseUserListDTO(String json) {
		AutoBean<IEnterpriseUserListDTO> bean = AutoBeanCodex.decode(factory,
				IEnterpriseUserListDTO.class, json);
		return bean.as();
	}

	public String toJson(IEnterpriseUserListDTO userListDTO) {
		AutoBean<IEnterpriseUserListDTO> bean = AutoBeanUtils
				.getAutoBean(userListDTO);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IEnterpriseUserListDTO createEnterpriseUserListDTO() {
		AutoBean<IEnterpriseUserListDTO> bean = factory
				.createEnterpriseUserListDTO();
		return bean.as();
	}

	public IUserImportSaveDTO createUserImportSaveDTO() {
		AutoBean<IUserImportSaveDTO> bean = factory.createUserImportSaveDTO();
		return bean.as();
	}

	public String toJson(IUserImportSaveDTO userSaveDTO) {
		AutoBean<IUserImportSaveDTO> bean = AutoBeanUtils
				.getAutoBean(userSaveDTO);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	/**
	 * IRoleUsersDTO
	 * 
	 * @param json
	 * @return
	 */
	public IRoleUsersDTO roleUsersDTO() {
		AutoBean<IRoleUsersDTO> bean = factory.roleUsersDTO();
		return bean.as();
	}

	public IRoleUsersDTO roleUsersDTO(IRoleUsersDTO toWrap) {
		AutoBean<IRoleUsersDTO> bean = factory.roleUsersDTO(toWrap);
		return bean.as();
	}

	public String serializeToJson(IRoleUsersDTO data) {
		AutoBean<IRoleUsersDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IRoleUsersDTO deserializeFromJsonToRoleUsersDTO(String json) {
		AutoBean<IRoleUsersDTO> bean = AutoBeanCodex.decode(factory,
				IRoleUsersDTO.class, json);
		return bean.as();
	}

	/**
	 * IRoleUserDTO
	 * 
	 * @param json
	 * @return
	 */
	public IRoleUserDTO roleUserDTO() {
		AutoBean<IRoleUserDTO> bean = factory.roleUserDTO();
		return bean.as();
	}

	/**
	 * ICreateConferenceDTO
	 * 
	 * @return
	 */
	public ICreateConferenceDTO createConferenceDTO() {
		AutoBean<ICreateConferenceDTO> bean = factory.createConferenceDTO();
		return bean.as();
	}

	public IShareFileRecordDTO fromJsonToShareFileRecordDTO(String json) {
		AutoBean<IShareFileRecordDTO> bean = AutoBeanCodex.decode(factory,
				IShareFileRecordDTO.class, json);
		return bean.as();
	}

	/**
	 * IAdminDataDTO
	 * 
	 * @return
	 */
	public IAdminDataDTO fromJsonToShareAdminDataDTO(String json) {
		AutoBean<IAdminDataDTO> bean = AutoBeanCodex.decode(factory,
				IAdminDataDTO.class, json);
		return bean.as();
	}

	/**
	 * IAdminDepartmentDTO
	 * 
	 * @param json
	 * @return
	 */
	public IAdminDepartmentDTO adminDepartmentDTO() {
		AutoBean<IAdminDepartmentDTO> bean = factory.adminDepartmentDTO();
		return bean.as();
	}

	/**
	 * IAdminDTO
	 * 
	 * @param json
	 * @return
	 */
	public IAdminDTO adminDTO() {
		AutoBean<IAdminDTO> bean = factory.adminDTO();
		return bean.as();
	}

	public String toJson(IAdminDTO data) {
		AutoBean<IAdminDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IAdminDTO fromJsonToAdminDTO(String json) {
		AutoBean<IAdminDTO> bean = AutoBeanCodex.decode(factory,
				IAdminDTO.class, json);
		return bean.as();
	}

	/**
	 * IAdminFolderDTO
	 * 
	 * @param json
	 * @return
	 */
	public IAdminFolderDTO adminFolderDTO() {
		AutoBean<IAdminFolderDTO> bean = factory.adminFolderDTO();
		return bean.as();
	}

	/**
	 * IAdminsDTO
	 * 
	 * @param json
	 * @return
	 */
	public IAdminsDTO adminsDTO() {
		AutoBean<IAdminsDTO> bean = factory.adminsDTO();
		return bean.as();
	}

	public String toJson(IAdminsDTO data) {
		AutoBean<IAdminsDTO> bean = AutoBeanUtils.getAutoBean(data);
		return AutoBeanCodex.encode(bean).getPayload();
	}

	public IAdminsDTO fromJsonToAdminsDTO(String json) {
		AutoBean<IAdminsDTO> bean = AutoBeanCodex.decode(factory,
				IAdminsDTO.class, json);
		return bean.as();
	}

	public ICurrentServiceDTO createCurrentService() {
		return factory.createCurrentService().as();
	}

	public String toJson(ICurrentServiceDTO dto) {
		return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(dto))
				.getPayload();
	}

	public ICurrentServiceListDTO createCurrentServiceList() {
		return factory.createCurrentServiceList().as();
	}

	public String toJson(ICurrentServiceListDTO dto) {
		return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(dto))
				.getPayload();
	}

	public IBuyServiceDTO createBuyService() {
		return factory.createBuyService().as();
	}

	public ICurrentServiceListDTO createCurrentServiceList(String json) {
		return AutoBeanCodex
				.decode(factory, ICurrentServiceListDTO.class, json).as();
	}

	public String toJson(IBuyServiceDTO dto) {
		return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(dto))
				.getPayload();
	}

	public IBuyServiceListDTO createBuyServiceList() {
		return factory.createBuyServiceList().as();
	}

	public IBuyServiceListDTO createBuyServiceList(String json) {
		return AutoBeanCodex.decode(factory, IBuyServiceListDTO.class, json)
				.as();
	}

	public String toJson(IBuyServiceListDTO dto) {
		return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(dto))
				.getPayload();
	}

	public IOrderDTO createOrder() {
		return factory.createOrderDTO().as();
	}

	public IOrderItemDTO createOrderItem() {
		return factory.createOrderItem().as();
	}

	public IOrderItemListDTO createOrderItemList() {
		return factory.createOrderItemList().as();
	}

	public IOrderItemListDTO createOrderItemList(String json) {
		return AutoBeanCodex.decode(factory, IOrderItemListDTO.class, json)
				.as();
	}

	public String toJson(IOrderDTO dto) {
		return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(dto))
				.getPayload();
	}

	public String toJson(IOrderItemDTO dto) {
		return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(dto))
				.getPayload();
	}

	public IEntPayDTO createUserPay() {
		return factory.createUserPayDTO().as();
	}

	public IServiceTypeDTO createServiceType() {
		return factory.createServiceType().as();
	}

	public IServiceTypeListDTO createServiceTypeList() {
		return factory.createServiceTypeList().as();
	}

	public IServiceTypeListDTO createServiceTypeList(String json) {
		return AutoBeanCodex.decode(factory, IServiceTypeListDTO.class, json)
				.as();
	}

	public IEntPayDTO createEntPay(String json) {
		return AutoBeanCodex.decode(factory, IEntPayDTO.class, json).as();
	}

	public IOrderDTO createOrder(String json) {
		return AutoBeanCodex.decode(factory, IOrderDTO.class, json).as();
	}

	public IFolderAndFileParamDTO createFolderAndFileParam() {
		return factory.createFolderAndFileParam().as();
	}

	public String toJson(IFolderAndFileParamDTO param) {
		return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(param))
				.getPayload();
	}

	/**
	 * IFileUploadResultDTO
	 * 
	 * @param json
	 * @return
	 */
	public IFileUploadResultDTO fromJsonToFileUploadResultDTO(String json) {
		AutoBean<IFileUploadResultDTO> bean = AutoBeanCodex.decode(factory,
				IFileUploadResultDTO.class, json);
		return bean.as();
	}

	public IFolderAndFileDTO fromJsonToFolderAndFile(String json) {
		AutoBean<IFolderAndFileDTO> bean = AutoBeanCodex.decode(factory,
				IFolderAndFileDTO.class, json);
		return bean.as();
	}

}
