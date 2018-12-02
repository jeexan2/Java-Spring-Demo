package com.sit.jbc.controller.security;

import com.sit.jbc.domain.dto.security.*;
import com.sit.jbc.domain.entity.security.*;
import com.sit.jbc.repository.security.OptionSetupViewRepository;
import com.sit.jbc.service.security.*;
import com.sit.jbc.service.security.impl.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.tree.Tree;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Geetanjali Oishe on 10/3/2018.
 */

@Controller
@RequestMapping(value = "/security")
public class OptionController {
    @Autowired
    ModuleService moduleService;
    @Autowired
    SubModuleService subModuleService;
    @Autowired
    OptionService optionService;
    @Autowired
    OptionSetupViewRepository optionSetupViewRepository;
    @Autowired
    AddOptionService addOptionService;
    @Autowired
    HttpSession httpSession;

    @Autowired
    OptionSetupViewService optionSetupViewService;

    @RequestMapping(value = "/addoption", method = RequestMethod.GET)
    public String addOption(Model model) {
//        model.addAttribute("test", "test");
        try {
            /*SessionUser user = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            AccessPermission accessPermission = user.getCurrentPermissions();*/
            AccessPermission accessPermission = (AccessPermission) httpSession.getAttribute("userPermission");
            model.addAttribute("permissionCheck", accessPermission);
        }
        catch (Exception e){
        }

        return "security/addoption";
    }

    @RequestMapping(value = "/addoption", method = RequestMethod.POST)
    public String addOption(@Validated @ModelAttribute("add-option") AddOption addOption, BindingResult result, Model model) {
        addOptionService.addOption(addOption);
        return "redirect:security/addoption";
    }

    @RequestMapping(value = "/getModuleList", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Module> getModuleList() {
        return addOptionService.getModuleList();
    }

    @RequestMapping(value = "/getSubModuleList", method = RequestMethod.GET)
    public
    @ResponseBody
    List<SubModule> getSubModuleList(@RequestParam("moduleId") long moduleId) {
        return addOptionService.getSubModuleList(moduleId);
    }

    @RequestMapping(value = "/getOptionSetupTree", method = RequestMethod.GET)
    public
    @ResponseBody
    String getOptionSetupTree() {
        AccessPermission accessPermission = (AccessPermission) httpSession.getAttribute("userPermission");
        return addOptionService.getOptionSetupTree(accessPermission);
    }

    @RequestMapping(value = "/getDeleteData", method = RequestMethod.POST)
    public
    @ResponseBody
    String getDeleteData(@RequestParam("elementId") Long elementId, @RequestParam("elementType") String elementType) {
        return addOptionService.getDeleteData(elementId, elementType);
    }

    @RequestMapping(value = "/getEditData", method = RequestMethod.GET)
    public
    @ResponseBody
    OptionSetupView getEditData(@RequestParam("elementId") Long elementId, @RequestParam("elementType") String elementType) {
        return addOptionService.getEditData(elementId, elementType);
    }

}
