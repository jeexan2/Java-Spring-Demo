package com.sit.jbc.repository.security;

import com.sit.jbc.domain.entity.security.RoleOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Geetanjali Oishe on 10/31/2018.
 */
public interface RoleOptionRepository extends JpaRepository<RoleOption, Long> {
    RoleOption findByRoleOptionId(Long id);
    RoleOption findByOptionId(Long optionId);
    RoleOption findByOptionIdAndRoleId(Long optionId, Long roleId);
}
