package com.sit.jbc.service.security.impl;

import com.sit.jbc.domain.entity.security.SubModule;
import com.sit.jbc.repository.security.SubModuleRepository;
import com.sit.jbc.service.security.SubModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Geetanjali Oishe on 10/7/2018.
 */

@Service
@Transactional
public class SubModuleServiceImpl implements SubModuleService{
    @Autowired
    SubModuleRepository subModuleRepository;

    @Override
    public SubModule save(SubModule subModule) {
        return subModuleRepository.save(subModule);
    }

    @Override
    public void delete(SubModule subModule) {
    }

    @Override
    public SubModule findSubModuleById(Long id) {
        return subModuleRepository.findBySubModuleId(id);
    }

    @Override
    public SubModule findSubModuleByName(String name) {
        return subModuleRepository.findBySubModuleName(name);
    }

    @Override
    public List<SubModule> findAll() {
        return subModuleRepository.findAll();
    }
}
