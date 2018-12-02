package com.sit.jbc.service.security.impl;

import com.sit.jbc.domain.entity.security.Module;
import com.sit.jbc.repository.security.ModuleRepository;
import com.sit.jbc.service.security.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Geetanjali Oishe on 10/7/2018.
 */
@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    ModuleRepository moduleRepository;

    @Override
    public Module save(Module module) {
        return moduleRepository.save(module);
    }

    @Override
    public void delete(Module module) {
    }

    @Override
    public Module findModuleById(Long id) {
        return moduleRepository.findByModuleId(id);
    }

    @Override
    public Module findModuleByName(String name) {
        return moduleRepository.findByModuleName(name);
    }
    @Override
    public List<Module> findAll() {
        return moduleRepository.findAll();
    }
}
