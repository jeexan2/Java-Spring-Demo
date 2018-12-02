package com.sit.jbc.service.security;

import com.sit.jbc.domain.entity.security.Role;

import java.util.List;

/**
 * Created by MAMUN on 11-Sep-18.
 */
public interface RoleService {
    Role save(Role role);
    void delete(Role role);
    Role findRoleById(Long id);
    List<Role> findAll();

}
