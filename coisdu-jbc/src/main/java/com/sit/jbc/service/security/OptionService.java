package com.sit.jbc.service.security;

import com.sit.jbc.domain.entity.security.Option;

import java.util.List;

/**
 * Created by Geetanjali Oishe on 10/7/2018.
 */
public interface OptionService {
    Option save (Option option);
    void delete(Option option);
    Option findOptionById(Long id);
    Option findOptionByName(String name);
    List<Option> findAll();
}
