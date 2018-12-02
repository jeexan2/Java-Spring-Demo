package com.sit.jbc.service.security;

import com.sit.jbc.domain.dto.security.AccessPermission;
import com.sit.jbc.domain.dto.security.AddOption;
import com.sit.jbc.domain.entity.security.Module;
import com.sit.jbc.domain.entity.security.OptionSetupView;
import com.sit.jbc.domain.entity.security.SubModule;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Geetanjali Oishe on 10/28/2018.
 */
public interface AddOptionService {
    String addOption( AddOption addOption);
    List<Module> getModuleList();
    List<SubModule> getSubModuleList(long moduleId);
    String getOptionSetupTree(AccessPermission accessPermission);
    String getDeleteData(Long elementId, String elementType);
    OptionSetupView getEditData(Long elementId, String elementType);
}
