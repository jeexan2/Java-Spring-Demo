package com.sit.jbc.service.security.impl;

import com.sit.jbc.domain.entity.security.Option;
import com.sit.jbc.repository.security.OptionRepository;
import com.sit.jbc.service.security.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Geetanjali Oishe on 10/7/2018.
 */
@Service
@Transactional
public class OptionServiceImpl implements OptionService {
    @Autowired
    OptionRepository optionRepository;

    @Override
    public Option save(Option option) {
        return optionRepository.save(option);
    }

    @Override
    public void delete(Option option) {

    }

    @Override
    public Option findOptionById(Long id) {
        return optionRepository.findByOptionId(id);
    }

    @Override
    public Option findOptionByName(String name) {
        return optionRepository.findByOptionName(name);
    }


    @Override
    public List<Option> findAll() {
        return optionRepository.findAll();
    }
}
