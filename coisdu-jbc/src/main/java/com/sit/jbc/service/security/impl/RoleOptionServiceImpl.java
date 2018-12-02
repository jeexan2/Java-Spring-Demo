package com.sit.jbc.service.security.impl;

import com.sit.jbc.domain.entity.security.RoleOption;
import com.sit.jbc.repository.security.RoleOptionRepository;
import com.sit.jbc.service.security.RoleOptionServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Geetanjali Oishe on 10/31/2018.
 */
@Service
@Transactional
public class RoleOptionServiceImpl implements RoleOptionServie {
    @Autowired
    RoleOptionRepository roleOptionRepository;

    @Override
    public RoleOption save(RoleOption roleOption) {
        return roleOptionRepository.save(roleOption);
    }

    @Override
    public void delete(RoleOption roleOption) {

    }

    @Override
    public RoleOption findRoleOptionById(Long id) {
        return roleOptionRepository.findByRoleOptionId(id);
    }

    @Override
    public RoleOption findByOptionId(Long optionId) {
        return roleOptionRepository.findByOptionId(optionId);
    }

    @Override
    public List<RoleOption> findAll() {
        return roleOptionRepository.findAll();
    }

    @Override
    public RoleOption findByOptionIdAndRoleId(Long optionId, Long roleId) {
        return roleOptionRepository.findByOptionIdAndRoleId(optionId, roleId);
    }
}
