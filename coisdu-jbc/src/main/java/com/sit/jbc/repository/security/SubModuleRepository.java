package com.sit.jbc.repository.security;

import com.sit.jbc.domain.entity.security.SubModule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Geetanjali Oishe on 10/7/2018.
 */
public interface SubModuleRepository extends JpaRepository<SubModule,Long> {
    SubModule findBySubModuleId(Long id);
    SubModule findBySubModuleName(String name);
}
