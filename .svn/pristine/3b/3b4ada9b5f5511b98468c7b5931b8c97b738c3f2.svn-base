package com.qycloud.oatos.server.domain.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conlect.oatos.dto.client.admin.AdminDTO;
import com.conlect.oatos.dto.client.admin.AdminsDTO;
import com.conlect.oatos.dto.status.UserType;
import com.qycloud.oatos.server.dao.AdminDepartmentMapper;
import com.qycloud.oatos.server.dao.AdminFolderMapper;
import com.qycloud.oatos.server.dao.AdminMapper;
import com.qycloud.oatos.server.domain.entity.AdminDepartment;
import com.qycloud.oatos.server.domain.entity.AdminFolder;
import com.qycloud.oatos.server.domain.entity.Admin;

/**
 * 二级管理员服务
 * @author yang
 *
 */
@Service("AdminLogic")
public class AdminLogic {
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private AdminDepartmentMapper adminDepartmentMapper;
	
	@Autowired
	private AdminFolderMapper adminFolderMapper;

	/**
	 * 取企业的二级管理员
	 * @param entId
	 * @return
	 */
	public AdminsDTO getAdminsByEntId(long entId) {
		List<Admin> admins = adminMapper.getAdminsByEntId(entId);
		List<AdminDTO> as = new ArrayList<AdminDTO>();
		for (Admin a : admins) {
			if (a.isCreateDept() || a.isCreateMember()) {
		        List<AdminDepartment> ds = adminDepartmentMapper.getAdminDepartmentsByUserId(a.getUserId());
		        a.setDepartments(ds);
            }
			if (a.isFolderPermission()) {
		        List<AdminFolder> fs = adminFolderMapper.getAdminFoldersByUserId(a.getUserId());
		        a.setFolders(fs);
            }
	        as.add(a.toAdminDTO());
        }
		AdminsDTO a = new AdminsDTO();
		a.setAdmins(as);
		return a;
	}

	/**
	 * 新建二级管理员
	 * @param admins
	 */
	@Transactional
	public void addAdmins(List<AdminDTO> admins) {
		if (admins.size() > 0) {
			List<Admin> as = new ArrayList<Admin>();
			for (AdminDTO a : admins) {
		        as.add(new Admin(a));
		        adminMapper.updateUserType(a.getUserId(), UserType.SecondAdministrator);
	        }
			adminMapper.addAdmins(as);
		}
	}

	/**
	 * 修改二级管理员权限
	 * @param admins
	 */
	@Transactional
	public void updateAdmins(List<AdminDTO> admins) {
		for (AdminDTO a : admins) {
			Admin admin = new Admin(a);
			adminMapper.updateAdmin(admin);
			adminDepartmentMapper.deleteAdminDepartmentsByUserId(a.getUserId());
			if (admin.getDepartments().size() > 0) {
				adminDepartmentMapper.addAdminDepartments(admin.getDepartments());
			}
			adminFolderMapper.deleteAdminFoldersByUserId(a.getUserId());
			if (admin.getFolders().size() > 0) {
				adminFolderMapper.addAdminFolders(admin.getFolders());
			}
        }
	}

	/**
	 * 删除二级管理员
	 * @param a
	 */
	@Transactional
	public void deleteAdmin(AdminDTO a) {
		adminMapper.updateUserType(a.getUserId(), UserType.BusinessUser);
		//删掉管理员之前先要把该用户的状态变成普通用户
		adminMapper.deleteAdmin(new Admin(a));
	}

}
