package com.sit.jbc.repository.security;

import com.sit.jbc.domain.entity.security.Module;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Geetanjali Oishe on 10/7/2018.
 */
public interface ModuleRepository extends JpaRepository<Module,Long> {
//    Module findByModuleName()
    Module findByModuleId(Long id);
    Module findByModuleName(String name);
}

