package com.qycloud.oatos.server.domain.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conlect.oatos.dto.client.CheckPermissionDTO;
import com.conlect.oatos.dto.client.CheckPermissionsDTO;
import com.conlect.oatos.dto.client.RoleDTO;
import com.conlect.oatos.dto.client.RolePermissionDTO;
import com.conlect.oatos.dto.client.RolePermissionsDTO;
import com.conlect.oatos.dto.client.RoleUserDTO;
import com.conlect.oatos.dto.client.RoleUsersDTO;
import com.conlect.oatos.dto.client.RolesDTO;
import com.conlect.oatos.dto.client.UserRoleDTO;
import com.conlect.oatos.dto.client.UserRolesDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.UserType;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.dao.RoleMapper;
import com.qycloud.oatos.server.dao.RolePermissionMapper;
import com.qycloud.oatos.server.dao.UserMapper;
import com.qycloud.oatos.server.domain.entity.Role;
import com.qycloud.oatos.server.domain.entity.RolePermission;
import com.qycloud.oatos.server.domain.entity.User;
import com.qycloud.oatos.server.domain.entity.UserRole;
import com.qycloud.oatos.server.util.FilePermissionUtil;
import com.qycloud.oatos.server.util.LogicException;

/**
 * 角色权限服务
 * @author yang
 *
 */
@Service("RoleLogic")
public class RoleLogic {

	@Autowired
	private SequenceLogic sequence;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Transactional
    public String addRoleList(List<RoleDTO> roleList) {
		List<Role> roles = new ArrayList<Role>();
		for(RoleDTO role : roleList) {
	    	Role r = new Role(role);
	    	Role existRole = roleMapper.getSameNameRole(r);
	    	if (existRole != null) {
	    		// 存在同名角色
				throw new LogicException(ErrorType.errorSameName);
			}
			r.setRoleId(sequence.getNextId());
			roles.add(r);
		}
		if (roles.size() > 0) {
			roleMapper.addRoles(roles);
		}
		return CommConstants.OK_MARK;
	}
    
    /**
     * 添加角色
     * @param role
     * @return
     */
    @Transactional
	public String addRole(RoleDTO role) {
    	Role r = new Role(role);
    	Role existRole = roleMapper.getSameNameRole(r);
    	if (existRole != null) {
    		// 存在同名角色
			throw new LogicException(ErrorType.errorSameName);
		}
		r.setRoleId(sequence.getNextId());
		roleMapper.addRole(r);
		return PojoMapper.toJson(r.toRoleDTO());
	}
	
    public void addUserRole(UserRole userRole) {
		roleMapper.addUserRole(userRole);
	}
    
    /**
     * 修改角色
     * @param role
     * @return
     */
    @Transactional
	public String updateRole(RoleDTO role) {
    	Role r = new Role(role);
    	Role existRole = roleMapper.getSameNameRole(r);
    	if (existRole != null) {
    		// 存在同名角色
			throw new LogicException(ErrorType.errorSameName);
		}
		roleMapper.updateRole(r);
		return CommConstants.OK_MARK;
	}
    
    /**
     * 删除角色
     * @param role
     * @return
     */
	@Transactional
	public String deleteRole(RoleDTO role) {
		rolePermissionMapper.deletePermissionByRoleId(role.getRoleId());
		roleMapper.deleteUserRoleByRoleId(role.getRoleId());
		roleMapper.deleteRole(new Role(role));
		return CommConstants.OK_MARK;
	}
	
	/**
	 * 指定企业ID，获取企业所有角色数据
	 * @param enterpriseId
	 * @return
	 */
	public RolesDTO getRolesByEnterprise(long enterpriseId) {
		List<Role> roleModelList = roleMapper.getRolesByEntId(enterpriseId);
		if (roleModelList == null) {
			return null;
		}
		RolesDTO rolesDto = new RolesDTO();
		List<RoleDTO> roleDtoList = new ArrayList<RoleDTO>();
		rolesDto.setRoleList(roleDtoList);
		for (Role roleModel : roleModelList) {
			roleDtoList.add(roleModel.toRoleDTO());
		}
		return rolesDto;
	}
	
	/**
	 * 指定企业ID， 获取企业中所有角色和文件夹之间的权限关系
	 * @param enterpriseId
	 * @return
	 */
	public RolePermissionsDTO getRolePermissionsByEnterprise(long enterpriseId) {
		List<RolePermission> permissionModelList = rolePermissionMapper.getRolePermisssionsByEntId(enterpriseId);
		if (permissionModelList == null) {
			return null;
		}
		RolePermissionsDTO rolePermissionsDto = new RolePermissionsDTO();
		List<RolePermissionDTO> rolePermissionDtoList = new ArrayList<RolePermissionDTO>();
		rolePermissionsDto.setPermissionList(rolePermissionDtoList);
		for (RolePermission item : permissionModelList) {
			rolePermissionDtoList.add(item.toRolePermissionDTO());
		}
		return rolePermissionsDto;
	}

	/**
	 * 指定企业ID， 获取企业所有用户和角色数据
	 * @param enterpriseId
	 * @return
	 */
	public UserRolesDTO getUserRolesByEnterprise(long enterpriseId) {
		List<UserRole> userRoleModels = roleMapper.getUserRoleByEntId(enterpriseId);
		List<Role> roles = roleMapper.getRolesByEntId(enterpriseId);
		if (userRoleModels == null) {
			return null;
		}
		Set<Long> uids = new HashSet<Long>();
		for (UserRole ur : userRoleModels) {
	        uids.add(ur.getUserId());
        }
		UserRolesDTO dtos = new UserRolesDTO();
		List<UserRoleDTO> pers = new ArrayList<UserRoleDTO>();
		dtos.setUserRoleList(pers);

		for (long uid : uids) {
			UserRoleDTO urDto = new UserRoleDTO();
			urDto.setUserId(uid);
			for (UserRole ur : userRoleModels) {
				for (Role r : roles) {
		            if (ur.getRoleId() == r.getRoleId()) {
		                urDto.getRoles().add(r.toRoleDTO());
	                }
	            }
	        }
			pers.add(urDto);
		}

		return dtos;
	}

	/**
	 * 更新角色的文件夹权限信息
	 * @param permissionsDto
	 * @return
	 */
	@Transactional
	public String updatePermissions(RolePermissionsDTO permissionsDto) {
		List<RolePermission> rolePermissions = new ArrayList<RolePermission>();
		for (RolePermissionDTO item : permissionsDto.getPermissionList()) {
			rolePermissions.add(new RolePermission(item));
		}
		if (rolePermissions.size() > 0) {
			rolePermissionMapper.updateRolePermisssions(rolePermissions);
		}
		return CommConstants.OK_MARK;
	}

	/**
	 * 保存用户的角色
	 * @param userRolesDto
	 * @return
	 */
	@Transactional
	public String saveUserRoles(UserRolesDTO userRolesDto) {
		List<UserRole> userRoles = new ArrayList<UserRole>();
		for (UserRoleDTO item : userRolesDto.getUserRoleList()) {
			// 删除用户的所有角色数据
			roleMapper.deleteUserRoleByUserId(item.getUserId());
			
			for (RoleDTO r : item.getRoles()) {
				userRoles.add(new UserRole(item.getUserId(), r.getRoleId()));
            }
		}
		// 添加用户的角色
		if (userRoles.size() > 0) {
			roleMapper.addUserRoles(userRoles);
		}

		return CommConstants.OK_MARK;
	}
	
	/**
	 * 检查文件操作权限
	 * @param checkPermissionDTO
	 * @return
	 */
    public String checkPermission(CheckPermissionDTO checkPermissionDTO) {
		User user = userMapper.getUser(checkPermissionDTO.getUserId());
		String check = CommConstants.OK_MARK;
		if (UserType.Administrator != user.getUserType().longValue()) {
    		List<RolePermission> permissions = rolePermissionMapper.getRolePermisssionsByUserIdAndFolderId(checkPermissionDTO.getUserId(), checkPermissionDTO.getFolderId());
    		boolean c = FilePermissionUtil.checkPermission(checkPermissionDTO.getFolderId(), checkPermissionDTO.getOperate(), permissions);
    		if (!c) {
    			check = ErrorType.errorNoPermission.name();
            }
        }
		return check;
    }
    
    /**
     * 批量检查文件操作权限
     * @param permissionsDTO
     * @return
     */
    public String checkPermissions(CheckPermissionsDTO permissionsDTO) {
	    String check = CommConstants.OK_MARK;
		User user = userMapper.getUser(permissionsDTO.getUserId());
		if (UserType.Administrator != user.getUserType().longValue()) {
    		List<RolePermission> permissions = rolePermissionMapper.getRolePermisssionsByUserId(permissionsDTO.getUserId());
    		for (CheckPermissionDTO c : permissionsDTO.getCheckPermissionDTOs()) {
	            boolean ch = FilePermissionUtil.checkPermission(c.getFolderId(), c.getOperate(), permissions);
	            if (!ch) {
	            	check = ErrorType.errorNoPermission.name();
	            	break;
                }
            }
        }
	    return check;
    }
    
    /**
     * 获取企业下的角色及用户
     * @param entId
     * @return
     */
    public RoleUsersDTO getRoleUsersByEntId(long entId) {
		List<Role> roles = roleMapper.getRolesByEntId(entId);
		List<UserRole> userRoleModels = roleMapper.getUserRoleByEntId(entId);
		List<RoleUserDTO> roleUserDTOs = new ArrayList<RoleUserDTO>();
		for (Role r : roles) {
			RoleUserDTO roleUser = new RoleUserDTO();
			roleUserDTOs.add(roleUser);
			roleUser.setEnterpriseId(entId);
			roleUser.setRoleId(r.getRoleId());
			roleUser.setName(r.getName());
			roleUser.setDescription(r.getDescription());
			List<Long> uids = new ArrayList<Long>();
			roleUser.setUserIdList(uids);
			for (UserRole u : userRoleModels) {
		        if (u.getRoleId() == r.getRoleId()) {
	                uids.add(u.getUserId());
                }
	        }
        }
		RoleUsersDTO roleUsersDTO = new RoleUsersDTO();
		roleUsersDTO.setRoleUserDTOs(roleUserDTOs);
	    return roleUsersDTO;
    }
    
    /**
     * 修改用户角色
     * @param roleUsersDTO
     * @return
     */
	@Transactional
    public String updateRoleUsers(RoleUsersDTO roleUsersDTO) {
		List<UserRole> urs = new ArrayList<UserRole>();
		for (RoleUserDTO r : roleUsersDTO.getRoleUserDTOs()) {
	        roleMapper.deleteUserRoleByRoleId(r.getRoleId());
	        for (Long uid : r.getUserIdList()) {
	        	UserRole ur = new UserRole(uid, r.getRoleId());
	        	urs.add(ur);
            }
        }
		if (urs.size() > 0) {
			roleMapper.addUserRoles(urs);
		}
	    return CommConstants.OK_MARK;
    }

}
