package com.sit.jbc.service.security.impl;

import com.sit.jbc.domain.entity.security.Role;
import com.sit.jbc.repository.security.RoleRepository;
import com.sit.jbc.service.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MAMUN on 11-Sep-18.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public Role findRoleById(Long id) {
        return roleRepository.findByRoleId(id);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
