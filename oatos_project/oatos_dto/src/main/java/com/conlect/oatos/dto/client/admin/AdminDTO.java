package com.conlect.oatos.dto.client.admin;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.client.BaseDTO;

/**
 * 二级管理员
 * 
 * @author yang
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AdminDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 企业ID
	 */
	private long enterpriseId;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 编辑企业信息
	 */
	private boolean editEntInfo;
	/**
	 * 创建部门
	 */
	private boolean createDept;
	/**
	 * 添加用户
	 */
	private boolean createMember;
	/**
	 * 创建企业网盘文件夹
	 */
	private boolean createFolder;
	/**
	 * 设置文件夹权限
	 */
	private boolean folderPermission;
	/**
	 * 设置用户角色
	 */
	private boolean setRole;
	/**
	 * 添加二级管理员
	 */
	private boolean addAdmin;
	/**
	 * 设置阻止联系人
	 */
	private boolean setBlock;
	/**
	 * 查看操作记录
	 */
	private boolean record;
	/**
	 * 客服嵌入
	 */
	private boolean csPlugin;

	/**
	 * 可以添加用户的部门
	 */
	private List<AdminDepartmentDTO> departments;

	/**
	 * 可以授权的文件夹
	 */
	private List<AdminFolderDTO> folders;

	public AdminDTO() {
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isEditEntInfo() {
		return editEntInfo;
	}

	public void setEditEntInfo(boolean editEntInfo) {
		this.editEntInfo = editEntInfo;
	}

	public boolean isCreateDept() {
		return createDept;
	}

	public void setCreateDept(boolean createDept) {
		this.createDept = createDept;
	}

	public boolean isCreateMember() {
		return createMember;
	}

	public void setCreateMember(boolean createMember) {
		this.createMember = createMember;
	}

	public boolean isCreateFolder() {
		return createFolder;
	}

	public void setCreateFolder(boolean createFolder) {
		this.createFolder = createFolder;
	}

	public boolean isFolderPermission() {
		return folderPermission;
	}

	public void setFolderPermission(boolean folderPermission) {
		this.folderPermission = folderPermission;
	}

	public boolean isSetRole() {
		return setRole;
	}

	public void setSetRole(boolean setRole) {
		this.setRole = setRole;
	}

	public boolean isAddAdmin() {
		return addAdmin;
	}

	public void setAddAdmin(boolean addAdmin) {
		this.addAdmin = addAdmin;
	}

	public boolean isSetBlock() {
		return setBlock;
	}

	public void setSetBlock(boolean setBlock) {
		this.setBlock = setBlock;
	}

	public boolean isRecord() {
		return record;
	}

	public void setRecord(boolean record) {
		this.record = record;
	}

	public boolean isCsPlugin() {
		return csPlugin;
	}

	public void setCsPlugin(boolean csPlugin) {
		this.csPlugin = csPlugin;
	}

	public List<AdminDepartmentDTO> getDepartments() {
		return departments;
	}

	public void setDepartments(List<AdminDepartmentDTO> departments) {
		this.departments = departments;
	}

	public List<AdminFolderDTO> getFolders() {
		return folders;
	}

	public void setFolders(List<AdminFolderDTO> folders) {
		this.folders = folders;
	}

}
