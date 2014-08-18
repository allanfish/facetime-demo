package com.qycloud.oatos.filecache.domain.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.conlect.oatos.dto.autobean.INetworkFileDTO;
import com.conlect.oatos.dto.client.ConferenceDTO;
import com.conlect.oatos.dto.client.ConferenceDocDTO;
import com.conlect.oatos.dto.client.ConferenceDocsDTO;
import com.conlect.oatos.dto.client.ConferencesDTO;
import com.conlect.oatos.dto.client.CreateConferenceDTO;
import com.conlect.oatos.dto.client.PrepareFileAsConferenceDocDTO;
import com.conlect.oatos.dto.client.PrepareShareFileAsConferenceDocDTO;
import com.conlect.oatos.dto.client.ShareFileDTO;
import com.conlect.oatos.dto.client.ViewFileResultDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.oatos.filecache.util.ConferenceDocConverter;
import com.qycloud.oatos.filecache.util.ConfigUtil;
import com.qycloud.oatos.filecache.util.DocConverter;
import com.qycloud.oatos.filecache.util.FileProxy;
import com.qycloud.oatos.filecache.util.Logs;

/**
 * 视频会议文档逻辑服务
 * @author yang
 *
 */
@Service
public class ConferenceDocLogic {

	private final static Logger logger = Logs.getLogger();

	/**
	 * 远程服务请求代理
	 */
	private static XhrProxy proxy = XhrProxy.getInstance();

	/**
	 * 浏览会议文档，将文档转成swf
	 * @param postData
	 * @param token
	 * @return
	 */
	public String viewConferenceDoc(String postData, String token) {
		ConferenceDocDTO docDTO = PojoMapper.fromJsonAsObject(postData, ConferenceDocDTO.class);
		ViewFileResultDTO resultDTO = convertDocToSwf(docDTO, token);
		return PojoMapper.toJson(resultDTO);
	}

	/**
	 * 从个人网盘中选择添加会议文档
	 * @param postData
	 * @param token
	 * @return
	 */
	public String addConferenceDocFromPersonalDisk(String postData, String token) {
		PrepareFileAsConferenceDocDTO filesDTO = PojoMapper.fromJsonAsObject(postData,
				PrepareFileAsConferenceDocDTO.class);
		// 选择的文件
		List<ConferenceDocDTO> docList = new ArrayList<ConferenceDocDTO>();
		for (INetworkFileDTO f : filesDTO.getNetworkFileDTOList()) {
			ConferenceDocDTO docDTO = new ConferenceDocDTO(f);
			docDTO.setEnterpriseId(filesDTO.getEnterpriseId());
			docDTO.setConferenceId(filesDTO.getConferenceId());
			docList.add(docDTO);
		}
		// 添加文件到数据库
		ConferenceDocsDTO docsDTO = new ConferenceDocsDTO();
		docsDTO.setDocList(docList);
		String postJson = PojoMapper.toJson(docsDTO);
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.addConferenceDocs), token, postJson);

		// 将文件转成swf
		docsDTO = PojoMapper.fromJsonAsObject(result, ConferenceDocsDTO.class);
		ConferenceDocConverter converter = new ConferenceDocConverter(docsDTO.getDocList(), token);
		converter.start();
		return CommConstants.OK_MARK;
	}

	/**
	 * 从企业网盘中浏览添加会议文档
	 * @param postData
	 * @param token
	 * @return
	 */
	public String addConferenceDocFromEnterpriseDisk(String postData, String token) {
		PrepareShareFileAsConferenceDocDTO filesDTO = PojoMapper.fromJsonAsObject(postData,
				PrepareShareFileAsConferenceDocDTO.class);
		// 选择的文件
		List<ConferenceDocDTO> docList = new ArrayList<ConferenceDocDTO>();
		for (ShareFileDTO f : filesDTO.getShareFileList()) {
			ConferenceDocDTO docDTO = new ConferenceDocDTO(f);
			docDTO.setEnterpriseId(filesDTO.getEnterpriseId());
			docDTO.setConferenceId(filesDTO.getConferenceId());
			docList.add(docDTO);
		}
		// 添加文件到数据库
		ConferenceDocsDTO docsDTO = new ConferenceDocsDTO();
		docsDTO.setDocList(docList);
		String postJson = PojoMapper.toJson(docsDTO);
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.addConferenceDocs), token, postJson);

		// 将文件转成swf
		docsDTO = PojoMapper.fromJsonAsObject(result, ConferenceDocsDTO.class);
		ConferenceDocConverter converter = new ConferenceDocConverter(docsDTO.getDocList(), token);
		converter.start();
		return CommConstants.OK_MARK;
	}

	/**
	 * 删除会议文档
	 * @param postData
	 * @param token
	 * @return
	 */
	public String deleteConferenceDocs(String postData, String token) {
		ConferenceDocsDTO docDTOs = PojoMapper.fromJsonAsObject(postData, ConferenceDocsDTO.class);
		// 删除数据库中记录
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.deleteConferenceDocs), token, postData);
		// 删除文件
		if (CommConstants.OK_MARK.equals(result)) {
			StringBuilder sb = new StringBuilder();
			for (ConferenceDocDTO d : docDTOs.getDocList()) {
				if (!CommConstants.FILE_TYPE_SHAREDISK.equals(d.getType())
						&& !CommConstants.FILE_TYPE_ONLINEDISK.equals(d.getType())) {
					String f = DiskUtil.getConferenceDocPath(d.getEnterpriseId(), d.getConferenceId(), d.getGuid());
					String swf = DiskUtil.getConferenceSwfDir(d.getEnterpriseId(), d.getConferenceId(), d.getGuid());
					String pdf = DiskUtil.getConferencePdfPath(d.getEnterpriseId(), d.getConferenceId(), d.getGuid());
					String ppt = DiskUtil.getConferenceDocPath(d.getEnterpriseId(), d.getConferenceId(), d.getGuid());
					FileUtil.delete(DiskUtil.ONLINEDISK_PATH + f, 0);
					FileUtil.delete(DiskUtil.ONLINEDISK_PATH + swf, 0);
					FileUtil.delete(DiskUtil.ONLINEDISK_PATH + pdf, 0);
					FileUtil.delete(DiskUtil.ONLINEDISK_PATH + ppt, 0);

					sb.append(f);
					sb.append(",");
					sb.append(swf);
					sb.append(",");
					sb.append(pdf);
					sb.append(",");
					sb.append(ppt);
					sb.append(",");
				}
			}
			FileProxy.delete(sb.toString(), token);
		}
		return result;
	}

	/**
	 * 删除会议
	 * @param postData
	 * @param token
	 * @return
	 */
	public String deleteConference(String postData, String token) {
		ConferencesDTO conferencesDTO = PojoMapper.fromJsonAsObject(postData, ConferencesDTO.class);
		// 删除数据库中记录
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.deleteConference), token, postData);
		if (CommConstants.OK_MARK.equals(result)) {
			// 删除会议文档
			StringBuilder sb = new StringBuilder();
			for (ConferenceDTO c : conferencesDTO.getConferenceList()) {
				String doc = DiskUtil.getConferenceDocDir(c.getEnterpriseId(), c.getId());
				FileUtil.delete(DiskUtil.ONLINEDISK_PATH + doc, 0);
				sb.append(doc);
				sb.append(",");
			}
			FileProxy.delete(sb.toString(), token);
		}
		return result;
	}

	/**
	 * 创建会议
	 * @param postData
	 * @param token
	 * @return
	 */
	public String createConference(String postData, String token) {
		CreateConferenceDTO conferenceDTO = PojoMapper.fromJsonAsObject(postData, CreateConferenceDTO.class);
		// 创建会议
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.createConference), token, postData);
		try {
			long conferenceId = Long.parseLong(result);
			if (conferenceDTO.getDocList() != null) {
				// 创建会议文件夹，用户存放会议文件
				File dir = new File(DiskUtil.ONLINEDISK_PATH, DiskUtil.getConferenceDocDir(conferenceDTO.getEnterpriseId(), conferenceId));
				if (dir.mkdir()) {
					dir.mkdirs();
				}
				for (ConferenceDocDTO d : conferenceDTO.getDocList()) {
					// 保存文件至会议文件夹
					d.setConferenceId(conferenceId);
					if (!CommConstants.FILE_TYPE_SHAREDISK.equals(d.getType())
							&& !CommConstants.FILE_TYPE_ONLINEDISK.equals(d.getType())) {
						String s = DiskUtil.getFilePath(conferenceDTO.getCreaterId(), d.getGuid(), CommConstants.FILE_TYPE_TEMP);
						String t = DiskUtil.getConferenceDocPath(conferenceDTO.getEnterpriseId(), conferenceId, d.getGuid());
						// 保存文件至本地
						File target = new File(DiskUtil.ONLINEDISK_PATH, t);
						new File(DiskUtil.ONLINEDISK_PATH, s).renameTo(target);
						// 保存文件至文件服务器
						FileUploadUtil.upload(target, t, token);
					}
				}

			}
		} catch (Exception ex) {
			logger.error(postData, ex);
		}
		try {
			if (conferenceDTO.getDocList().size() > 0 && result != null && !result.startsWith(CommConstants.ERROR_MARK)) {
				// 将文件转成swf
				String json = proxy.post(ConfigUtil.getServiceUrl(RESTurl.getConferenceDocsByConId), token, result);
				ConferenceDocsDTO docsDTO = PojoMapper.fromJsonAsObject(json, ConferenceDocsDTO.class);
				ConferenceDocConverter converter = new ConferenceDocConverter(docsDTO.getDocList(), token);
				converter.start();
			}
		} catch (Exception ex) {
			logger.error(result, ex);
		}
		return result;
	}

	/**
	 * 转换会议文档为swf
	 * @param docDTO
	 * @param token
	 * @return
	 */
	public ViewFileResultDTO convertDocToSwf(ConferenceDocDTO docDTO, String token) {
		return DocConverter.convertDocToSwf(docDTO, token);
	}
}
