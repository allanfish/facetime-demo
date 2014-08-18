package com.qycloud.oatos.server.domain.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conlect.oatos.dto.client.ShareLinkDTO;
import com.conlect.oatos.dto.client.ShareLinkInfoDTO;
import com.conlect.oatos.dto.client.ShareLinkMailDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.FileStatus;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.dao.EnterpriseMapper;
import com.qycloud.oatos.server.dao.ShareFileMapper;
import com.qycloud.oatos.server.dao.ShareFolderMapper;
import com.qycloud.oatos.server.dao.ShareLinkMapper;
import com.qycloud.oatos.server.domain.entity.Enterprise;
import com.qycloud.oatos.server.domain.entity.ShareFile;
import com.qycloud.oatos.server.domain.entity.ShareFolder;
import com.qycloud.oatos.server.domain.entity.ShareLink;
import com.qycloud.oatos.server.logic.mail.MailSender;
import com.qycloud.oatos.server.security.Security;
import com.qycloud.oatos.server.util.LogicException;
import com.qycloud.oatos.server.util.ReadTxtUtil;

@Service("ShareLinkLogic")
public class ShareLinkLogic {

	@Autowired
	private SequenceLogic sequence;

	@Autowired
	private ShareLinkMapper shareLinkMapper;

	@Autowired
	private EnterpriseMapper enterpriseMapper;

	@Autowired
	private ShareFolderMapper shareFolderMapper;
	@Autowired
	private ShareFileMapper shareFileMapper;

	@Autowired
	private MailSender mailSender;

	public String createShareLink(ShareLinkDTO linkDTO) {
		ShareLink link = new ShareLink(linkDTO);
		boolean exist = false;
		if (link.getFileId() != null) {
	        if (shareLinkMapper.getShareLinkByFileId(link.getFileId()) != null) {
	        	exist = true;
            }
        } else if (link.getFolderId() != null) {
	        if (shareLinkMapper.getShareLinkByFolderId(link.getFolderId()) != null) {
	        	exist = true;
            }
		}
		if (exist) {
	        shareLinkMapper.updateShareLink(link);
        } else {
    		link.setLinkId(sequence.getNextId());
    		String code = UUID.randomUUID().toString().replace("-", "");
    		link.setLinkCode(code);
    		link.setCreateTime(new Date());
    		shareLinkMapper.addShareLink(link);
		}
		ShareLinkDTO shareLinkDTO = link.toShareLinkDTO();
		return PojoMapper.toJson(shareLinkDTO);
	}

	public void deleteShareLink(long linkId) {
		shareLinkMapper.deleteShareLinkByLinkId(linkId);
	}

	public void updateShareLink(ShareLinkDTO linkDTO) {
		ShareLink link = new ShareLink(linkDTO);
		shareLinkMapper.updateShareLink(link);
	}

    public ShareLinkInfoDTO getShareLinkByCode(String code) {
		ShareLink link = shareLinkMapper.getShareLinkByCode(code);
		ShareLinkInfoDTO linkDTO = null;
		if (link != null) {
			if (!checkFileExist(link)) {
				throw new LogicException(ErrorType.errorFileNotFound);
	        }

			linkDTO = new ShareLinkInfoDTO();
			if (link.getExpirationTime() != null) {
		        if (new Date().after(link.getExpirationTime())) {
		        	throw new LogicException(ErrorType.errorExpirationTimeOver);
	            }
	        }
			BeanUtils.copyProperties(link, linkDTO);
			linkDTO.setToken(Security.CreateToken(String.valueOf(link.getLinkId())));
			Enterprise enterprise = enterpriseMapper.getEnterpriseById(link.getEnterpriseId());
			linkDTO.setEnterpriseId(enterprise.getEnterpriseId());
			linkDTO.setEnterpriseName(enterprise.getEnterpriseName());
			linkDTO.setEnterpriseLogo(enterprise.getLogo());
        }
	    return linkDTO;
    }

    public List<ShareLinkDTO> getShareLinkByEntId(long entId) {
		List<ShareLink> linkModels = shareLinkMapper.getShareLinkByEntId(entId);
		List<ShareLinkDTO> links = new ArrayList<ShareLinkDTO>();
		for (ShareLink li : linkModels) {
	        links.add(li.toShareLinkDTO());
        }
	    return links;
    }

    public ShareLinkDTO getExistShareLink(ShareLinkDTO link) {
		ShareLink linkModel = null;
		if (link.getFolderId() != null) {
			linkModel = shareLinkMapper.getShareLinkByFolderId(link.getFolderId());
        } else if (link.getFileId() != null) {
        	linkModel = shareLinkMapper.getShareLinkByFileId(link.getFileId());
		}
		ShareLinkDTO linkDTO = null;
		if (linkModel != null) {
			linkDTO = linkModel.toShareLinkDTO();
        } else {
			linkDTO = new ShareLinkDTO();
			linkDTO.setEnterpriseId(link.getEnterpriseId());
			linkDTO.setFolderId(link.getFolderId());
			linkDTO.setFileId(link.getFileId());
		}
	    return linkDTO;
    }

    public ShareLinkDTO getShareLinkByLinkId(long linkId) {
		ShareLink link = shareLinkMapper.getShareLinkByLinkId(linkId);
		ShareLinkDTO linkDTO = null;
		if (link != null) {
			linkDTO = link.toShareLinkDTO();
        }
		return linkDTO;
    }

    public String checkDownload(long linkId) {
		String result = CommConstants.OK_MARK;
		ShareLink link = shareLinkMapper.getShareLinkByLinkId(linkId);

		if (!checkFileExist(link)) {
			result = ErrorType.errorFileNotFound.name();
        }

		if (link.getExpirationTime() != null) {
	        if (new Date().after(link.getExpirationTime())) {
	        	result = ErrorType.errorExpirationTimeOver.name();
            }
        }
		if (CommConstants.OK_MARK.equals(result)) {
	        if (link.getMaxDownload() != null) {
	            if (link.getMaxDownload() <= link.getDownloadCount()) {
	            	result = ErrorType.errorDownCountOver.name();
                }
            }
        }
		if (CommConstants.OK_MARK.equals(result)) {
			Enterprise ent = enterpriseMapper.getEnterpriseById(link.getEnterpriseId());
            if (ent.getMaxShareLinkDownload() <= ent.getShareLinkDownCount()) {
            	result = ErrorType.errorFlowOver.name();
            }
        }
	    return result;
    }

	@Transactional
    public void updateDownloadCount(String param) {
		String str[] = param.split(":");
		long linkId = Long.parseLong(str[0]);
		long size = Long.parseLong(str[1]);
		ShareLink link = shareLinkMapper.getShareLinkByLinkId(linkId);
		enterpriseMapper.updateShareLinkDownCount(link.getEnterpriseId(), size);
		shareLinkMapper.updateDownloadCount(linkId);
    }

    public void sendShareLinkMail(ShareLinkMailDTO mailDTO) {

		String mailMsg = ReadTxtUtil.getShareLinkMailText(mailDTO.getLocale(), mailDTO.isNet());
		
		mailMsg = mailMsg.replace("#{enterpriseName}", mailDTO.getEnterpriseName());
		mailMsg = mailMsg.replace("#{userName}", mailDTO.getSender());
		mailMsg = mailMsg.replace("#{linkUrl}", mailDTO.getLinkUrl());
		mailMsg = mailMsg.replace("#{message}", mailDTO.getContent());
		
		mailDTO.setContent(mailMsg);
		if (mailDTO.isNet()) {
			mailDTO.setSubject("OATOS Document Share");
        } else {
        	mailDTO.setSubject("OATOS文档共享");
		}

	    mailSender.sendMail(mailDTO);
    }

	private boolean checkFileExist(ShareLink link) {
		boolean exist = false;
		if (link.getFolderId() != null) {
			// get top folder
	        ShareFolder folder = shareFolderMapper.getShareFolderById(link.getFolderId());
	        if (folder != null && FileStatus.active == folder.getDeleted()) {
	        	exist = true;
            }
        } else if (link.getFileId() != null) {
			ShareFile fileModel = shareFileMapper.getShareFileById(link.getFileId());
			if (fileModel != null && FileStatus.active == fileModel.getDeleted()) {
				exist = true;
            }
		}
		return exist;
	}

}
