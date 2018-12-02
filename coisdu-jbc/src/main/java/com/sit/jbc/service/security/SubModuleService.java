package com.sit.jbc.service.security;

import com.sit.jbc.domain.entity.security.SubModule;

import java.util.List;

/**
 * Created by Geetanjali Oishe on 10/7/2018.
 */
public interface SubModuleService {
    SubModule save (SubModule subModule);
    void delete(SubModule subModule);
    SubModule findSubModuleById(Long id);
    SubModule findSubModuleByName(String name);
    List<SubModule> findAll();
}
