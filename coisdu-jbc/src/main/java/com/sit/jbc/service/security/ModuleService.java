package com.sit.jbc.service.security;

import com.sit.jbc.domain.entity.security.Module;
import java.util.List;

/**
 * Created by Geetanjali Oishe on 10/7/2018.
 */
public interface ModuleService {
    Module save (Module module);
    void delete(Module module);
    Module findModuleById(Long id);
    Module findModuleByName(String name);
    List<Module> findAll();
}