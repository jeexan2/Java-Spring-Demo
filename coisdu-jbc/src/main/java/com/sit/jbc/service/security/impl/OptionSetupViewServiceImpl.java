package com.sit.jbc.service.security.impl;

import com.sit.jbc.domain.entity.security.OptionSetupView;
import com.sit.jbc.repository.security.OptionSetupViewRepository;
import com.sit.jbc.service.security.OptionSetupViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Geetanjali Oishe on 10/9/2018.
 */
@Service
public class OptionSetupViewServiceImpl implements OptionSetupViewService {

    @Autowired
    OptionSetupViewRepository optionSetupViewRepository;

    @Override
    public OptionSetupView save(OptionSetupView optionSetupView) {
        return null;
    }

    @Override
    public void delete(OptionSetupView optionSetupViewtion) {

    }

    @Override
    public OptionSetupView findOptionSetupViewById(Long id) {
        return null;
    }

    @Override
    public List<OptionSetupView> findOptionSetupViewBymoduleId(Long moduleId) {
        return optionSetupViewRepository.findOptionSetupViewBymoduleId(moduleId);
    }

    @Override
    public List<OptionSetupView> findAll() {
        return optionSetupViewRepository.findAll();
    }

}
