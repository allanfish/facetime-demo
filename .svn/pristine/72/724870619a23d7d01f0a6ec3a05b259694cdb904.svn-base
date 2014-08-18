package com.conlect.oatos.dto.client;

/**
 * 聊天发送文件dto
 * 
 * @author yang
 * 
 */
public class SendFileDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 发送者
	 */
	private long sendId;
	/**
	 * 接收者
	 */
	private long receiverId;
	/**
	 * 文件类型
	 */
	private String type;
	/**
	 * 文件信息
	 */
	private NetworkFileDTO fileDTO;

	public SendFileDTO() {
	}

	public SendFileDTO(long sendId, long receiverId, String type) {
		this.sendId = sendId;
		this.receiverId = receiverId;
		this.type = type;
	}

	public long getSendId() {
		return sendId;
	}

	public void setSendId(long sendId) {
		this.sendId = sendId;
	}

	public long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public NetworkFileDTO getFileDTO() {
		return fileDTO;
	}

	public void setFileDTO(NetworkFileDTO fileDTO) {
		this.fileDTO = fileDTO;
	}

}
