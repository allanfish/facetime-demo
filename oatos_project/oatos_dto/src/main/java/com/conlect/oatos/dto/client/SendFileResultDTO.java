package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.INetworkFileDTO;
import com.conlect.oatos.dto.autobean.ISendFileResultDTO;

/**
 * 发送文件结果dto
 * 
 * @author yang
 * 
 */
public class SendFileResultDTO implements ISendFileResultDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 发送状态
	 */
	private String satus;

	/**
	 * 发送者的文件信息
	 */
	private INetworkFileDTO fileDTO;

	/**
	 * 接收者的文件信息
	 */
	private INetworkFileDTO receiveFileDTO;

	@Override
	public String getSatus() {
		return satus;
	}

	@Override
	public void setSatus(String satus) {
		this.satus = satus;
	}

	@Override
	public INetworkFileDTO getFileDTO() {
		return fileDTO;
	}

	@Override
	public void setFileDTO(INetworkFileDTO fileDTO) {
		this.fileDTO = fileDTO;
	}

	@Override
	public INetworkFileDTO getReceiveFileDTO() {
		return receiveFileDTO;
	}

	@Override
	public void setReceiveFileDTO(INetworkFileDTO receiveFileDTO) {
		this.receiveFileDTO = receiveFileDTO;
	}
}
