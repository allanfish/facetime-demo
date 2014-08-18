package com.qycloud.web;

import java.util.ArrayList;
import java.util.List;

import com.conlect.oatos.dto.autobean.IMessageDTO;
import com.conlect.oatos.dto.client.CustomerDTO;
import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.client.MessagesDTO;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.clouddisk.EnterpriseDiskUpdateService;
import com.qycloud.web.clouddisk.FileService;
import com.qycloud.web.utils.ConfigUtil;

/**
 * 服务工具类，调用REST服务<br>
 * <br>
 * Service utility, invoke from REST server
 * 
 * @author jinkerjiang
 */
public class ServiceUtil {
	/**
	 * 远程服务请求代理
	 */
	private static XhrProxy proxy = XhrProxy.getInstance();
	
	public static String logout(String token, String userId) {
		return proxy.post(ConfigUtil.getServiceUrl(RESTurl.logout), token, userId);
	}
	
	/**
	 * Invoke service
	 * 
	 * @param token
	 * @param methodName
	 * @param postJson
	 * @return
	 */
	public static String invoke(String token, String methodName, String postJson) {
		String result = "";
		
		if (RESTurl.sendMessages.equals(methodName)) {
			MessagesDTO messagesDTO = PojoMapper.fromJsonAsObject(postJson, MessagesDTO.class);
			List<IMessageDTO> wrapper = new ArrayList<IMessageDTO>();
			for (IMessageDTO messageDTO : messagesDTO.getMessageDTOList()) {
				wrapper.add(messageDTO);
			}
			result = MQMessageService.getInstance().sendMessages(token, wrapper, true);
		} else if (RESTurl.sendMessage.equals(methodName)) {
			MessageDTO messageDTO = PojoMapper.fromJsonAsObject(postJson, MessageDTO.class);
			result = MQMessageService.getInstance().sendMessage(token, messageDTO);
		} else {
			// execute application service
			if (RESTurl.newShareFolder.equals(methodName)
					|| RESTurl.updateShareFolder.equals(methodName)
					|| RESTurl.deleteShareFolder.equals(methodName)
					|| RESTurl.updateShareFile.equals(methodName)
					|| RESTurl.updateShareFolderAndFiles.equals(methodName)
					|| RESTurl.moveShareFolderAndFiles.equals(methodName)
					|| RESTurl.deleteShareFolderAndFiles.equals(methodName)
					|| RESTurl.restoreShareFolderAndFiles.equals(methodName)
					|| RESTurl.restoreShareFolderVersion.equals(methodName)
					|| RESTurl.restoreShareFileVersion.equals(methodName)) {
				result = EnterpriseDiskUpdateService.service(methodName, postJson, token);
			} else if (RESTurl.viewFileAsImage.equals(methodName)
					|| RESTurl.viewFileAsHtml.equals(methodName)
					|| RESTurl.viewFileAsPdf.equals(methodName)
					|| RESTurl.viewFileAsSwf.equals(methodName)
					|| RESTurl.editFileAsHtml.equals(methodName)
					|| RESTurl.saveFileToDisk.equals(methodName)
					|| RESTurl.saveFile.equals(methodName)
					|| RESTurl.acceptFile.equals(methodName)
					|| RESTurl.resizeUserPicture.equals(methodName)
					|| RESTurl.cropUserPicture.equals(methodName)
					|| RESTurl.getUserIcons.equals(methodName)
					|| RESTurl.getEnterpriseLogos.equals(methodName)
					|| RESTurl.getDiskImageThumbs.equals(methodName)
					|| RESTurl.deleteUserIcon.equals(methodName)
					|| RESTurl.downZipFile.equals(methodName)
					|| RESTurl.sharePersonalFile.equals(methodName)
					|| RESTurl.checkDiskFile.equals(methodName)
					|| RESTurl.viewConferenceDoc.equals(methodName)
					|| RESTurl.addConferenceDocFromPersonalDisk.equals(methodName)
					|| RESTurl.addConferenceDocFromEnterpriseDisk.equals(methodName)
					|| RESTurl.deleteConferenceDocs.equals(methodName)
					|| RESTurl.deleteConference.equals(methodName)
					|| RESTurl.createConference.equals(methodName)
					|| RESTurl.deleteEnterpriseUsers.equals(methodName)) {
				result = FileService.service(token, methodName, postJson);
			} else {
				if (RESTurl.createCustomerId.equals(methodName)) {
					CustomerDTO customerDTO = new CustomerDTO();
					customerDTO.setIp(postJson);
					postJson = PojoMapper.toJson(customerDTO);
				}
				result = proxy.post(ConfigUtil.getServiceUrl(methodName), token, postJson);
			}
		}
		return result;
	}
	
}
