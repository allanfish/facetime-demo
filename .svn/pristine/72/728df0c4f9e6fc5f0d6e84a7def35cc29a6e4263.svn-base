package com.qycloud.web.mail;

import java.io.IOException;

import com.conlect.oatos.dto.autobean.INetworkFileDTO;
import com.conlect.oatos.dto.autobean.mail.IMailAttachDTO;
import com.conlect.oatos.dto.autobean.mail.IMailQueryDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.NetworkFilesDTO;
import com.conlect.oatos.dto.client.NetworkFolderDTO;
import com.conlect.oatos.dto.client.mail.MailAccountDTO;
import com.conlect.oatos.dto.client.mail.MailAttachDTO;
import com.conlect.oatos.dto.client.mail.MailDTO;
import com.conlect.oatos.dto.client.mail.MailFolderListDTO;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.conlect.oatos.utils.StringUtils;
import com.qycloud.web.utils.ConfigUtil;

public class MailUtils {

	/**
	 * 保存邮箱文件夹信息
	 * @param account
	 * @param userToken
	 * @return MailFolderListDTO
	 */
	public static String saveMailFolders(MailAccountDTO account, String userToken, MailFolderListDTO folderListDTO) {
		return XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.saveMailFolders), userToken,
				PojoMapper.toJson(folderListDTO));
	}

	public static String getMailList(String query, String userToken) {
		return XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.getMailList), userToken, query);
	}

	public static String getMailFolders(MailAccountDTO account, String userToken) {
		return XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.getMailFolders), userToken,
				account.getMailAccountId() + "");
	}

	public static String updateMailAccount(MailAccountDTO account, String userToken) {
		return XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.updateMailAccount), userToken,
				PojoMapper.toJson(account));
	}

	public static String addMailAccount(MailAccountDTO account, String userToken) {
		return XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.addMailAccount), userToken,
				PojoMapper.toJson(account));
	}

	/**
	 * 删除数据库中的邮件
	 * @param mailQuery
	 * @param userToken
	 */
	public static void deleteMail(IMailQueryDTO mailQuery, String userToken) {
		XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.deleteMails), userToken,
				PojoMapper.toJson(mailQuery));
	}

	public static MailDTO saveMail(MailDTO mailInfo, String userToken) {
		String result = XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.saveMail), userToken,
				PojoMapper.toJson(mailInfo));
		return PojoMapper.fromJsonAsObject(result, MailDTO.class);
	}

	public static String getMail(IMailQueryDTO query, String userToken) {
		return XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.getMailInfo), userToken,
				PojoMapper.toJson(query));
	}

	/**
	 * 通过mailAccountId到后台获取邮箱账号信息
	 */
	public static MailAccountDTO getMailAccount(String mailAccountId, String userToken) throws IOException {
		String result = XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.getMailAccount), userToken,
				mailAccountId);
		return PojoMapper.fromJsonAsObject(result, MailAccountDTO.class);
	}

	/**
	 * 获取网盘文件信息
	 * 
	 * @param fileId
	 * @param userToken
	 * @return
	 */
	public static INetworkFileDTO getNetworkFile(long fileId, String userToken) {
		String result = XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.getFileById), userToken,
				fileId + "");
		INetworkFileDTO networkFileDTO = PojoMapper.fromJsonAsObject(result, NetworkFileDTO.class);
		return networkFileDTO;
	}

	public static IMailAttachDTO getMailAttach(long attachId, String userToken) {
		String result = XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.getMailAttachById),
				userToken, attachId + "");
		IMailAttachDTO dto = PojoMapper.fromJsonAsObject(result, MailAttachDTO.class);
		return dto;
	}

	public static NetworkFolderDTO getMailFolder(long userId, String userToken) {
		String result = XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.getEmailFolder), userToken,
				userId + "");
		NetworkFolderDTO dto = PojoMapper.fromJsonAsObject(result, NetworkFolderDTO.class);
		return dto;
	}

	/**
	 * 获取网盘文件信息
	 * @param userToken
	 * @param fileId
	 * 
	 * @return
	 */
	public static NetworkFilesDTO getNetworkFilePath(String[] fileNames, String userToken) {
		String result = XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.getAttachFiles), userToken,
				CommonUtil.string2ASCII(StringUtils.join(fileNames, ",")));
		return PojoMapper.fromJsonAsObject(result, NetworkFilesDTO.class);
	}

	public static String setMailRead(long mailId, String userToken) {
		return XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.setMailRead), userToken, mailId + "");
	}

	public static String receiveLatestMailFolder(IMailQueryDTO mailQuery, String userToken) {
		return XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.receiveLatestMailFolder), userToken,
				PojoMapper.toJson(mailQuery));
	}
}
