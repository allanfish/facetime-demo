package com.qycloud.oatos.server.domain.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conlect.oatos.dto.client.ConferenceDTO;
import com.conlect.oatos.dto.client.ConferenceDocDTO;
import com.conlect.oatos.dto.client.ConferenceDocsDTO;
import com.conlect.oatos.dto.client.ConferenceMemberDTO;
import com.conlect.oatos.dto.client.ConferenceMembersDTO;
import com.conlect.oatos.dto.client.ConferencesDTO;
import com.conlect.oatos.dto.client.CreateConferenceDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ConferenceMemberStatus;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.dao.ConferenceMapper;
import com.qycloud.oatos.server.dao.ConferenceDocMapper;
import com.qycloud.oatos.server.domain.entity.ConferenceDoc;
import com.qycloud.oatos.server.domain.entity.ConferenceMember;
import com.qycloud.oatos.server.domain.entity.Conference;
import com.qycloud.oatos.server.util.LogicException;

@Service("ConferenceLogic")
public class ConferenceLogic {

	@Autowired
	private SequenceLogic sequence;

	@Autowired
	private ConferenceMapper conferenceMapper;

	@Autowired
	private ConferenceDocMapper conferenceDocMapper;

	@Transactional
	public long createConference(CreateConferenceDTO conferenceDTO) {
		Conference conference = new Conference(conferenceDTO);
		long conferenceId = sequence.getNextId();
		conference.setId(conferenceId);
		conferenceMapper.addConference(conference);

		if (conferenceDTO.getMembers() != null && conferenceDTO.getMembers().size() > 0) {
			List<ConferenceMember> members = new ArrayList<ConferenceMember>();
			for (ConferenceMemberDTO memberDTO : conferenceDTO.getMembers()) {
				ConferenceMember m = new ConferenceMember(memberDTO);
				m.setConferenceId(conferenceId);
				members.add(m);
			}
			conferenceMapper.addConferenceMembers(members);
		}
		if (conferenceDTO.getDocList() != null && conferenceDTO.getDocList().size() > 0) {
		    List<ConferenceDoc> docList = new ArrayList<ConferenceDoc>();
		    for (ConferenceDocDTO d : conferenceDTO.getDocList()) {
		    	ConferenceDoc docModel = new ConferenceDoc(d);
		    	docModel.setFileId(sequence.getNextId());
		    	docModel.setConferenceId(conferenceId);
		        docList.add(docModel);
	        }
		    conferenceDocMapper.addConferenceDocs(docList);
        }
		return conferenceId;
	}

	@Transactional
	public void updateConferenceStatus(ConferenceDTO conference) {
		conferenceMapper.updateConference(new Conference(conference));
	}

	@Transactional
	public void deleteConference(List<ConferenceDTO> conferenceDTOs) {
		for (ConferenceDTO c : conferenceDTOs) {
			conferenceMapper.deleteConferenceById(c.getId());
        }
	}

	public ConferenceDTO getConferenceById(long conferenceId) {
		ConferenceDTO conferenceDTO = null;
		Conference conferenceModel = conferenceMapper.getConferenceById(conferenceId);
		if (conferenceModel != null) {
			conferenceDTO = conferenceModel.toConferenceDTO();
			List<ConferenceMember> ms = conferenceMapper.getConferenceMembers(conferenceId);
			for (ConferenceMember m : ms) {
				conferenceDTO.getMembers().add(m.toConferenceMemberDTO());
	        }
        }
		return conferenceDTO;
	}

	public ConferencesDTO getConferenceByUserId(long userId) {
		List<Conference> cons = conferenceMapper.getConferenceByUserId(userId);
		ConferencesDTO conferencesDTO = new ConferencesDTO();
		for (Conference c : cons) {
			ConferenceDTO conferenceDTO = c.toConferenceDTO();
			List<ConferenceMember> ms = conferenceMapper.getConferenceMembers(c.getId());
			for (ConferenceMember m : ms) {
				conferenceDTO.getMembers().add(m.toConferenceMemberDTO());
            }
			conferencesDTO.getConferenceList().add(conferenceDTO);
		}
		return conferencesDTO;
	}

	@Transactional
	public void updateConferenceMember(ConferenceMemberDTO member) {
		conferenceMapper.updateConferenceMember(new ConferenceMember(member));
	}

	@Transactional
    public void addConferenceMembers(ConferenceMembersDTO membersDTO) {
		if (membersDTO.getMembers() != null && membersDTO.getMembers().size() > 0) {
			long c = conferenceMapper.getConferenceMemberCount(membersDTO.getMembers().get(0).getConferenceId());
			if (c + membersDTO.getMembers().size() <= CommConstants.MAX_CONFERENCE_MEMBER) {
				List<ConferenceMember> members = new ArrayList<ConferenceMember>();
				for (ConferenceMemberDTO memberDTO : membersDTO.getMembers()) {
					ConferenceMember m = new ConferenceMember(memberDTO);
					members.add(m);
				}
				conferenceMapper.addConferenceMembers(members);
            } else {
				throw new LogicException(ErrorType.errorConferenceMemberOver);
			}
		}
    }

	@Transactional
    public String addConferenceDoc(ConferenceDocDTO conferenceDocDTO) {
		conferenceDocDTO.setFileId(sequence.getNextId());
	    ConferenceDoc docModel = new ConferenceDoc(conferenceDocDTO);
	    conferenceDocMapper.addConferenceDoc(docModel);
	    return PojoMapper.toJson(conferenceDocDTO);
    }

    public List<ConferenceDocDTO> getConferenceDocsByConId(long conferenceId) {
		List<ConferenceDoc> docModels = conferenceDocMapper.getConferenceDocByConId(conferenceId);

		List<ConferenceDocDTO> docDTOs = new ArrayList<ConferenceDocDTO>();
		for (ConferenceDoc d : docModels) {
	        docDTOs.add(d.toConferenceDocDTO());
        }
	    return docDTOs;
    }

	@Transactional
    public void deleteConferenceDocs(List<ConferenceDocDTO> conferenceDocDTOs) {
		List<ConferenceDoc> docList = new ArrayList<ConferenceDoc>();
		for (ConferenceDocDTO d : conferenceDocDTOs) {
	        docList.add(new ConferenceDoc(d));
        }
		if (docList.size() > 0) {
			conferenceDocMapper.deleteConferenceDocs(docList);
		}
    }

    public String checkConferenceDocUpload(ConferenceDocDTO conferenceDocDTO) {
		String result = CommConstants.OK_MARK;
	    ConferenceDoc docModel = conferenceDocMapper.getConferenceDocByConIdAndName(new ConferenceDoc(conferenceDocDTO));
		if (docModel == null) {
			// TODO
		} else {
			// 在用户同一文件夹下存在同名文件
			result = ErrorType.errorSameFile.name();
		}
	    return result;
    }

    public String addConferenceDoc(List<ConferenceDocDTO> conferenceDocDTOs) {
	    List<ConferenceDoc> docList = new ArrayList<ConferenceDoc>();
	    for (ConferenceDocDTO d : conferenceDocDTOs) {
	    	d.setFileId(sequence.getNextId());
	    	ConferenceDoc docModel = new ConferenceDoc(d);
	        docList.add(docModel);
        }
	    if (docList.size() > 0) {
	    	conferenceDocMapper.addConferenceDocs(docList);
		}
	    ConferenceDocsDTO docsDTO = new ConferenceDocsDTO();
	    docsDTO.setDocList(conferenceDocDTOs);
	    return PojoMapper.toJson(docsDTO);
    }

	@Transactional
    public void updateConference(CreateConferenceDTO conference) {
		List<ConferenceMember> members = conferenceMapper.getConferenceMembers(conference.getId());
		List<ConferenceMember> newMembers = new ArrayList<ConferenceMember>();
		List<ConferenceMember> deleteMembers = new ArrayList<ConferenceMember>();
		if (conference.getMembers() != null) {
	        for (ConferenceMemberDTO m : conference.getMembers()) {
	        	boolean e = false;
	            for (ConferenceMember cm : members) {
	                if (cm.getUserId() == m.getUserId()
	                		&& !ConferenceMemberStatus.Refused.equals(cm.getStatus())) {
	                    e = true;
	                    break;
                    }
                }
	            if (!e) {
	            	ConferenceMember member = new ConferenceMember(m);
	            	member.setConferenceId(conference.getId());
	            	newMembers.add(member);
                }
            }
        }
        for (ConferenceMember m : members) {
        	boolean e = false;
        	if (conference.getMembers() != null) {
        		for (ConferenceMemberDTO cm : conference.getMembers()) {
        			if (cm.getUserId() == m.getUserId()) {
	                    e = true;
	                    break;
                    }
        		}
            }
        	if (!e) {
	            deleteMembers.add(m);
            }
        }
        if (newMembers.size() > 0 && deleteMembers.size() > 0) {
        	long c = conferenceMapper.getConferenceMemberCount(conference.getId());
        	c = c + newMembers.size() - deleteMembers.size();
            for (ConferenceMember m : deleteMembers) {
	            if (ConferenceMemberStatus.Refused.equals(m.getStatus())) {
	                c = c + 1;
                }
            }
            if (c > CommConstants.MAX_CONFERENCE_MEMBER) {
            	throw new LogicException(ErrorType.errorConferenceMemberOver);
            }
        }
	    if (deleteMembers.size() > 0) {
	    	conferenceMapper.deleteConferenceMembers(conference.getId(), deleteMembers);
        }
        if (newMembers.size() > 0) {
	        conferenceMapper.addConferenceMembers(newMembers);
        }
        
        List<ConferenceDoc> docs = conferenceDocMapper.getConferenceDocByConId(conference.getId());
        List<ConferenceDoc> deleteDocs = new ArrayList<ConferenceDoc>();
        if (conference.getDocList() != null) {
        	List<ConferenceDoc> newDocs = new ArrayList<ConferenceDoc>();
	        for (ConferenceDocDTO d : conference.getDocList()) {
	            if (d.getFileId() <= 0) {
	    	    	ConferenceDoc docModel = new ConferenceDoc(d);
	    	    	docModel.setFileId(sequence.getNextId());
	    	    	newDocs.add(docModel);
                }
            }
	        if (newDocs.size() > 0) {
	            conferenceDocMapper.addConferenceDocs(newDocs);
            }
	        for (ConferenceDoc d : docs) {
	        	boolean e = false;
	        	for (ConferenceDocDTO doc : conference.getDocList()) {
	        		if (d.getFileId() == doc.getFileId()) {
	                    e = true;
	                    break;
                    }
	        	}
	        	if (!e) {
	                deleteDocs.add(d);
                }
	        }
        } else {
			deleteDocs = docs;
		}
        if (deleteDocs.size() > 0) {
	        conferenceDocMapper.deleteConferenceDocs(deleteDocs);
        }

		conferenceMapper.updateConference(new Conference(conference));
    }

    public void updateDocPageCount(ConferenceDocDTO docDTO) {
	    conferenceDocMapper.updateDocPageCount(new ConferenceDoc(docDTO));
    }

    /**
     * 取会议文档信息
     * @param fileId
     * @return
     */
    public ConferenceDocDTO getConferenceDocById(long fileId) {
    	ConferenceDoc doc = conferenceDocMapper.getConferenceDocById(fileId);
    	return doc.toConferenceDocDTO();
    }

}
