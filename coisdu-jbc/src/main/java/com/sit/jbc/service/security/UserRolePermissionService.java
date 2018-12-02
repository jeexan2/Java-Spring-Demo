package com.sit.jbc.service.security;

import com.sit.jbc.domain.dto.security.UserRolePermission;
import com.sit.jbc.domain.entity.security.Module;
import com.sit.jbc.domain.entity.security.Role;
import com.sit.jbc.domain.entity.security.RoleOption;

import java.util.List;

/**
 * Created by Geetanjali Oishe on 10/31/2018.
 */
public interface UserRolePermissionService {
    public String userRolePermission(UserRolePermission userRolePermission);
    public List<Module> getModuleList();
    public List<Role> getRoleList();
    public String getModuleWiseTree(Long moduleId, Long roleId);
    public List<RoleOption> getCurrentPermission();
}
