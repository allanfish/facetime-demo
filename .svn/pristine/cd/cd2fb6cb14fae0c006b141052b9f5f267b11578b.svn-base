package com.conlect.oatos.dto.client.mail;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.client.BaseDTO;

/**
 * 获取邮件列表
 * 
 * @author PeterGuo
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MailNextPageDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 用户ID
	public long userId;
	// 页码
	public int pageNo;
	// 每页的行数
	public int rowsPerPage;
	// 文件夹ID
	public long folderId;
	// 邮件帐户ID
	public long mailAccountId;

	public MailNextPageDTO() {
	}

	public MailNextPageDTO(long userId, int pageNo, int rowsPerPage,
			long folderId, long mailAccountId) {
		this.userId = userId;
		this.pageNo = pageNo;
		this.rowsPerPage = rowsPerPage;
		this.folderId = folderId;
		this.mailAccountId = mailAccountId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (folderId ^ (folderId >>> 32));
		result = prime * result + pageNo;
		result = prime * result + rowsPerPage;
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MailNextPageDTO other = (MailNextPageDTO) obj;
		if (folderId != other.folderId)
			return false;
		if (pageNo != other.pageNo)
			return false;
		if (rowsPerPage != other.rowsPerPage)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}
