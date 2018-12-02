package com.sit.jbc.service.security;

import com.sit.jbc.domain.entity.security.RoleOption;

import java.util.List;

/**
 * Created by Geetanjali Oishe on 10/31/2018.
 */
public interface RoleOptionServie {
    RoleOption save (RoleOption roleOption);
    void delete(RoleOption roleOption);
    RoleOption findRoleOptionById(Long id);
    RoleOption findByOptionId(Long optionId);
//    List<RoleOption> findByOptionId(Long optionId);
    List<RoleOption> findAll();
    RoleOption findByOptionIdAndRoleId(Long optionId, Long roleId);
}
