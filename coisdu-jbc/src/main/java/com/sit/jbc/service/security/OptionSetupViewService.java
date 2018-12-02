package com.sit.jbc.service.security;

import com.sit.jbc.domain.entity.security.OptionSetupView;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Geetanjali Oishe on 10/9/2018.
 */
public interface OptionSetupViewService {
    OptionSetupView save (OptionSetupView optionSetupView);
    void delete(OptionSetupView optionSetupViewtion);
    OptionSetupView findOptionSetupViewById(Long id);
    List<OptionSetupView> findOptionSetupViewBymoduleId(Long id);
    List<OptionSetupView> findAll();
}
