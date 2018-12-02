package com.sit.jbc.service.security.impl;

import com.sit.jbc.domain.dto.security.AccessPermission;
import com.sit.jbc.domain.dto.security.AddOption;
import com.sit.jbc.domain.dto.security.TreeElement;
import com.sit.jbc.domain.entity.security.Module;
import com.sit.jbc.domain.entity.security.Option;
import com.sit.jbc.domain.entity.security.OptionSetupView;
import com.sit.jbc.domain.entity.security.SubModule;
import com.sit.jbc.repository.security.OptionSetupViewRepository;
import com.sit.jbc.service.security.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Geetanjali Oishe on 10/28/2018.
 */
@Service
@Transactional
public class AddOptionServiceImpl implements AddOptionService {
    @Autowired
    ModuleService moduleService;
    @Autowired
    SubModuleService subModuleService;
    @Autowired
    OptionService optionService;
    @Autowired
    OptionSetupViewRepository optionSetupViewRepository;

    @Autowired
    OptionSetupViewService optionSetupViewService;

    @Override
    public String addOption(AddOption addOption) {
        if (addOption.getSelectOption().equals("1")) { //module
            Module module = new Module();
            int isActive = 0;

            if (addOption.getActive() != null)
                isActive = 1;

            if (addOption.getId() != -1) { // edit module
                Module oldModule = moduleService.findModuleById(addOption.getId());

                if (oldModule.getIsActive() == 1 && isActive == 0) { // set all child active status to 0
                    changeSubModuleList(oldModule.getModuleId(), isActive);
                }
                module.setModuleId(oldModule.getModuleId());
                module.setCreatedBy(oldModule.getCreatedBy());
                module.setCreatedOn(oldModule.getCreatedOn());
                module.setIsUpdated(1);
                module.setUpdatedBy(41);
                module.setUpdatedOn(new Date());
            } else {                        // new module add
                module.setCreatedBy(1);
                module.setCreatedOn(new Date());
            }

            module.setIsActive(isActive);
            module.setModuleName(addOption.getName());
            module.setDisplayName(addOption.getDisplayName());
            moduleService.save(module);
        } else if (addOption.getSelectOption().equals("2")) { //sub-module
            SubModule subModule = new SubModule();
            int isActive = 0;

            if (addOption.getActive() != null)
                isActive = 1;

            if (addOption.getId() != -1) { // edit Sub-module
                SubModule oldSubModule = subModuleService.findSubModuleById(addOption.getId());

                if (oldSubModule.getIsActive() == 1 && isActive == 0) {  // set all child active status to 0
                    changeOptionStatus(oldSubModule.getSubModuleId(), isActive);
                } else if (oldSubModule.getIsActive() == 0 && isActive == 1) { // set parent status to 1
                    changeModuleStatus(oldSubModule.getModuleID(), isActive);
                }

                subModule.setSubModuleId(oldSubModule.getSubModuleId());
                subModule.setCreatedBy(oldSubModule.getCreatedBy());
                subModule.setCreatedOn(oldSubModule.getCreatedOn());
                subModule.setIsUpdated(1);
                subModule.setUpdatedBy(41);
                subModule.setUpdatedOn(new Date());
            } else {                        // new sub-module add
                if (isActive == 1) {        // set parent status to 1
                    changeModuleStatus(addOption.getSelectedModule(), isActive);
                }
                subModule.setCreatedBy(1);
                subModule.setCreatedOn(new Date());
            }

            subModule.setIsActive(isActive);
            subModule.setDisplayName(addOption.getDisplayName());
            subModule.setSubModuleName(addOption.getName());
            subModule.setModuleID(addOption.getSelectedModule());
            subModuleService.save(subModule);
        } else if (addOption.getSelectOption().equals("3")) { // option
            Option option = new Option();
            int isActive = 0;

            if (addOption.getActive() != null)
                isActive = 1;

            if (addOption.getId() != -1) {  // edit option
                Option oldOption = optionService.findOptionById(addOption.getId());

                if (oldOption.getIsActive() == 0 && isActive == 1) {
                    SubModule oldSubModule = subModuleService.findSubModuleById(addOption.getSelectedSubModule());
                    changeSubModuleStatus(oldSubModule, isActive);
                }

                option.setOptionId(oldOption.getOptionId());
                option.setCreatedBy(oldOption.getCreatedBy());
                option.setUpdatedOn(new Date());
                option.setCreatedOn(oldOption.getCreatedOn());
                option.setIsUpdated(1);
                option.setUpdatedBy(41);
            } else {                      // add new option
                if (isActive == 1) {
                    SubModule oldSubModule = subModuleService.findSubModuleById(addOption.getSelectedSubModule());
                    changeSubModuleStatus(oldSubModule, isActive);
                }
                option.setCreatedOn(new Date());
                option.setCreatedBy(1);
            }

            option.setDisplayName(addOption.getDisplayName());
            option.setIsActive(isActive);
            option.setAccessUrl(addOption.getAccessUrl());
            option.setOptionName(addOption.getName());
            option.setSubModuleID(addOption.getSelectedSubModule()); //edit needed
            optionService.save(option);
        }
        return null;
    }

    public void changeSubModuleList(Long moduleId, int isActive) {
        List<SubModule> subModules = subModuleService.findAll().stream().
                filter(s -> moduleId.equals(s.getModuleID()))
                .collect(Collectors.toList());

        for (SubModule subModule : subModules) {
            SubModule oldSubModule = subModuleService.findSubModuleById(subModule.getSubModuleId());
            if (isActive == 0) {
                changeOptionStatus(oldSubModule.getSubModuleId(), isActive); // change all option status to 0 for each submodule in list
            }
            changeSubModuleStatus(oldSubModule, isActive);
        }
    }

    public void changeSubModuleStatus(SubModule oldSubModule, int isActive) {

        if (isActive == 1) {
            changeModuleStatus(oldSubModule.getModuleID(), isActive);
        }

        SubModule updateSubModule = new SubModule();
        updateSubModule.setSubModuleId(oldSubModule.getSubModuleId());
        updateSubModule.setIsActive(isActive);
        updateSubModule.setIsUpdated(1);
        updateSubModule.setUpdatedBy(41);
        updateSubModule.setUpdatedOn(new Date());
        updateSubModule.setSubModuleName(oldSubModule.getSubModuleName());
        updateSubModule.setDisplayName(oldSubModule.getDisplayName());
        updateSubModule.setCreatedOn(oldSubModule.getCreatedOn());
        updateSubModule.setCreatedBy(oldSubModule.getCreatedBy());
        updateSubModule.setModuleID(oldSubModule.getModuleID());
        subModuleService.save(updateSubModule);
    }

    public void changeOptionStatus(Long subModuleId, int isActive) {
        List<Option> options = optionService.findAll().stream().
                filter(s -> subModuleId.equals(s.getSubModuleID()))
                .collect(Collectors.toList());

        for (Option option : options) {
            Option oldOption = optionService.findOptionById(option.getOptionId());
            option.setCreatedOn(oldOption.getCreatedOn());
            option.setCreatedBy(oldOption.getCreatedBy());
            option.setIsActive(isActive);
            option.setIsUpdated(1);
            option.setUpdatedBy(41);
            option.setUpdatedOn(new Date());
            option.setOptionName(oldOption.getOptionName());
            option.setOptionId(oldOption.getOptionId());
            option.setAccessUrl(oldOption.getAccessUrl());
            option.setDisplayName(oldOption.getDisplayName());
            option.setSubModuleID(oldOption.getSubModuleID());
            optionService.save(option);
        }
    }

    public void changeModuleStatus(Long moduleId, int isActive) {
        Module module = new Module();
        Module oldModule = moduleService.findModuleById(moduleId);
        module.setModuleId(oldModule.getModuleId());
        module.setCreatedBy(oldModule.getCreatedBy());
        module.setCreatedOn(oldModule.getCreatedOn());
        module.setIsUpdated(1);
        module.setUpdatedBy(41);
        module.setUpdatedOn(new Date());
        module.setIsActive(isActive);
        module.setModuleName(oldModule.getModuleName());
        module.setDisplayName(oldModule.getDisplayName());
        moduleService.save(module);
    }

    @Override
    public List<Module> getModuleList() {
        List<Module> modules = moduleService.findAll().stream().
                filter(s -> s.getIsDeleted() == null || s.getIsDeleted() == 0)
                .collect(Collectors.toList());
        return modules;
    }

    @Override
    public List<SubModule> getSubModuleList(long moduleId) {
        List<SubModule> subModules = subModuleService.findAll().stream().
                filter(s -> s.getModuleID().equals(moduleId) && (s.getIsDeleted() == null || s.getIsDeleted() == 0))
                .collect(Collectors.toList());
        return subModules;
    }

    @Override
    public String getOptionSetupTree(AccessPermission accessPermission) {
        List<OptionSetupView> optionSetupViews = optionSetupViewService.findAll();
        ArrayList<Long> visitedModules = new ArrayList<>();
        ArrayList<Long> visitedSubModules = new ArrayList<>();

        Node<TreeElement> root = new Node<>(new TreeElement("root", 0L, " ", 0));

        for (OptionSetupView element : optionSetupViews) {
            Node<TreeElement> nodeModule = null;
            if (visitedModules.contains(element.getModuleId())) { //retrieve node if previously added to tree
                nodeModule = root.getChild(element.getModuleName(), "module");
            } else {                                            //add new child node and add to visited module list
                nodeModule = root.addChild(new Node<>(new TreeElement(element.getModuleName(), element.getModuleId(), "module", element.getModuleActiveSts().equals("1") ? 1 : 0)));
                visitedModules.add(element.getModuleId());
            }

            if ((element.getSubModuleName() != null) ? true : false) { // if sub Module present previously
                Node<TreeElement> nodeSubModule = null;
                if (visitedSubModules.contains(element.getSubModuleId())) { //retrieve node if previously added to tree
                    nodeSubModule = nodeModule.getChild(element.getSubModuleName(), "subModule");
                } else { //add new child node
                    nodeSubModule = nodeModule.addChild(new Node<>(new TreeElement(element.getSubModuleName(), element.getSubModuleId(), "subModule", element.getSubModuleActiveSts().equals("1") ? 1 : 0)));
                    visitedSubModules.add(element.getSubModuleId());
                }

                if ((element.getOptionName() != null) ? true : false) { // if option present
                    nodeSubModule.addChild(new Node<>(new TreeElement(element.getOptionName(), element.getOptionId(), "option", element.getOptionActiveSts().equals("1") ? 1 : 0)));

                }
            }
        }

        String preAdd = "<ul><li><a href='#'>";
        String postAdd = "</a>";
        String lastAdd = "</li></ul>";

        String optionSetupTree = printTree(root, lastAdd, accessPermission);
        System.out.println(optionSetupTree);
        finalTree = "";
        return optionSetupTree;
    }

    String finalTree = "";

    private String printTree(Node<TreeElement> node, String lastAdd, AccessPermission accessPermission) {
        if (!node.getData().getName().equals("root")) {
            String pA = "";
            String postA = "</a>";
            if (node.getData().getType().equals("module")) {
                if (node.getData().getActiveStatus() == 0)
                    pA = "<ul><li data-type='module'><a class='not-active'>";
                else
                    pA = "<ul><li data-type='module'><a>";
            }

            if (node.getData().getType().equals("subModule")) {
                if (node.getData().getActiveStatus() == 0)
                    pA = "<ul><li data-type='subModule'><a class='not-active'>";
                else
                    pA = "<ul><li data-type='subModule'><a>";
            }

            if (node.getData().getType().equals("option")) {
                if (node.getData().getActiveStatus() == 0)
                    pA = "<ul><li data-type='option'><a class='not-active'>";
                else
                    pA = "<ul><li data-type='option'><a>";
            }

            String editBtn = "<div class='pull-right btn-group btn-group-xs'> <button class='btn btn-success' value='Edit' data-id='" + node.getData().getID() + "' data-type='" + node.getData().getType() + "' href='#'> <i class='fa fa-pencil'></i></button>";
            String deleteBtn = "<button class='btn btn-danger' value='Delete' data-id='" + node.getData().getID() + "' data-type='" + node.getData().getType() + "' href='#'> <i class='fa fa-trash'></i></button> </div>";

            if (accessPermission.getEditPermit() != 1) editBtn = "";
            if (accessPermission.getDeletePermit() != 1) deleteBtn = "";
            finalTree = finalTree + pA + node.getData().getName() + editBtn + deleteBtn + postA;
        }

        node.getChildren().forEach(each -> printTree(each, lastAdd, accessPermission));

        if (!node.getData().getName().equals("root")) {
            finalTree = finalTree + lastAdd;
        }
        return finalTree;
    }

    @Override
    public String getDeleteData(Long elementId, String elementType) {
        List<OptionSetupView> optionSetupViews = optionSetupViewService.findAll();

        if (elementType.equals("module")) {
            Module module = moduleService.findModuleById(elementId);
            for (OptionSetupView element : optionSetupViews) {
                if (elementId.equals(element.getModuleId()) && element.getSubModuleId() != null) {
                    return "Module has submodules. Cannot be deleted.";
                }
            }
            module.setIsActive(0);
            module.setIsDeleted(1);
            module.setDeletedOn(new Date());
            return "Module " + module.getModuleName() + " is Deleted Successfully";
        } else if (elementType.equals("subModule")) {
            SubModule subModule = subModuleService.findSubModuleById(elementId);
            for (OptionSetupView element : optionSetupViews) {
                if (elementId.equals(element.getSubModuleId()) && element.getOptionId() != null) {
                    return "Submodule has options. Cannot be deleted.";
                }
            }
            subModule.setIsActive(0);
            subModule.setIsDeleted(1);
            subModule.setDeletedOn(new Date());
            return "Sub-Module " + subModule.getSubModuleName() + " is Deleted Successfully";
        } else if (elementType.equals("option")) {
            Option option = optionService.findOptionById(elementId);
            if (option != null) {
                option.setIsActive(0);
                option.setIsDeleted(1);
                option.setDeletedOn(new Date());
                return "Option " + option.getOptionName() + " is Deleted Successfully";
            }
        }
        return null;
    }

    @Override
    public OptionSetupView getEditData(Long elementId, String elementType) {
        List<OptionSetupView> optionSetupViews = optionSetupViewService.findAll();

        if (elementType.equals("module")) {
            for (OptionSetupView element : optionSetupViews) {
                if (elementId.equals(element.getModuleId()))
                    return element;
            }
//            return moduleService.findModuleById(elementId);
        } else if (elementType.equals("subModule")) {
            for (OptionSetupView element : optionSetupViews) {
                if (elementId.equals(element.getSubModuleId()))
                    return element;
            }
//            return subModuleService.findSubModuleById(elementId);
        } else if (elementType.equals("option")) {
            for (OptionSetupView element : optionSetupViews) {
                if (elementId.equals(element.getOptionId())) {
                    return element;
                }
            }
//            return optionService.findOptionById(elementId);
        }
        return null;
    }
}
