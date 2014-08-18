package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.conlect.oatos.dto.client.admin.AdminDTO;
import com.conlect.oatos.dto.client.admin.AdminDepartmentDTO;
import com.conlect.oatos.dto.client.admin.AdminFolderDTO;

/**
 * 二级管理员权限
 * @author yang
 *
 */
public class Admin implements Serializable {

	/**
     * 
     */
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
	 * 用户帐号
	 */
	private String userName;
	/**
	 * 用户真实姓名
	 */
	private String realName;

	/**
	 * 编辑企业信息
	 */
	private boolean editEntInfo;
	/**
	 * 创建部门
	 */
	private boolean createDept;
	/**
	 * 添加成员
	 */
	private boolean createMember;
	/**
	 * 创建企业网盘文件夹
	 */
	private boolean createFolder;
	/**
	 * 企业网盘授权
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
	 * 有权限操作的部门
	 */
	private List<AdminDepartment> departments = new ArrayList<AdminDepartment>();
	/**
	 * 有权限操作的企业网盘文件夹
	 */
	private List<AdminFolder> folders = new ArrayList<AdminFolder>();

	public Admin() {
	}

	public Admin(AdminDTO a) {
		userId = a.getUserId();
		enterpriseId = a.getEnterpriseId();
		editEntInfo = a.isEditEntInfo();
		createDept = a.isCreateDept();
		createMember = a.isCreateMember();
		createFolder = a.isCreateFolder();
		folderPermission = a.isFolderPermission();
		setRole = a.isSetRole();
		addAdmin = a.isAddAdmin();
		setBlock = a.isSetBlock();
		record = a.isRecord();
		csPlugin = a.isCsPlugin();
		if (a.getDepartments() != null) {
			for (AdminDepartmentDTO d : a.getDepartments()) {
				departments.add(new AdminDepartment(d));
			}
		}
		if (a.getFolders() != null) {
			for (AdminFolderDTO f : a.getFolders()) {
				folders.add(new AdminFolder(f));
			}
		}
	}

	public AdminDTO toAdminDTO() {
		AdminDTO a = new AdminDTO();
		a.setUserId(userId);
		a.setEnterpriseId(enterpriseId);
		if (realName != null && !"".equals(realName)) {
			a.setUserName(realName);
		} else {
			a.setUserName(userName);
		}
		a.setEditEntInfo(editEntInfo);
		a.setCreateDept(createDept);
		a.setCreateMember(createMember);
		a.setCreateFolder(createFolder);
		a.setFolderPermission(folderPermission);
		a.setSetRole(setRole);
		a.setAddAdmin(addAdmin);
		a.setSetBlock(setBlock);
		a.setRecord(record);
		a.setCsPlugin(csPlugin);
		List<AdminDepartmentDTO> ds = new ArrayList<AdminDepartmentDTO>();
		if (departments != null) {
			for (AdminDepartment d : departments) {
				ds.add(d.toAdminDepartmentDTO());
			}
		}
		List<AdminFolderDTO> fs = new ArrayList<AdminFolderDTO>();
		if (folders != null) {
			for (AdminFolder f : folders) {
				fs.add(f.toAdminFolderDTO());
			}
		}
		a.setDepartments(ds);
		a.setFolders(fs);
		return a;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public List<AdminDepartment> getDepartments() {
		return departments;
	}

	public void setDepartments(List<AdminDepartment> departments) {
		this.departments = departments;
	}

	public List<AdminFolder> getFolders() {
		return folders;
	}

	public void setFolders(List<AdminFolder> folders) {
		this.folders = folders;
	}

}
