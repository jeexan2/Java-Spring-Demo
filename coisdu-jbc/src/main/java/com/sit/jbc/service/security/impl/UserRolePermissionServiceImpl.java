package com.sit.jbc.service.security.impl;

import com.sit.jbc.domain.dto.security.TreeElement;
import com.sit.jbc.domain.dto.security.UserRolePermission;
import com.sit.jbc.domain.entity.security.Module;
import com.sit.jbc.domain.entity.security.OptionSetupView;
import com.sit.jbc.domain.entity.security.Role;
import com.sit.jbc.domain.entity.security.RoleOption;
import com.sit.jbc.service.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.ws.ServiceMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Geetanjali Oishe on 11/7/2018.
 */
@Service
@Transactional
public class UserRolePermissionServiceImpl implements UserRolePermissionService {

    @Autowired
    ModuleService moduleService;

    @Autowired
    RoleOptionServie roleOptionServie;

    @Autowired
    OptionSetupViewService optionSetupViewService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRolePermissionService userRolePermissionService;

    @Override
    public String userRolePermission(UserRolePermission userRolePermission) {
        List<UserRolePermission> userRolePermissions = new ArrayList<>();

        String[] optId = userRolePermission.optionId.split(",");
        String[] optSts = userRolePermission.optionStatus.split(",");
        String[] addPermit = userRolePermission.addPermission.split(",");
        String[] editPetmit = userRolePermission.editPermission.split(",");
        String[] delPermit = userRolePermission.deletePermission.split(",");
        String[] addOrEdit = userRolePermission.addOrEdit.split(",");

        for (int i = 0; i < optId.length; i++) {
            UserRolePermission rolePermission = new UserRolePermission();
            rolePermission.setAddPermission(addPermit[i]);
            rolePermission.setDeletePermission(delPermit[i]);
            rolePermission.setEditPermission(editPetmit[i]);
            rolePermission.setOptionId(optId[i]);
            rolePermission.setOptionStatus(optSts[i]);
            rolePermission.setSelectedModule(userRolePermission.getSelectedModule());
            rolePermission.setSelectedRole(userRolePermission.getSelectedRole());
            rolePermission.setAddOrEdit(addOrEdit[i]);
            userRolePermissions.add(rolePermission);
        }

        userRolePermissions = userRolePermissions.stream().
                filter(s -> s.getOptionStatus().equals("1"))
                .collect(Collectors.toList());


        for (UserRolePermission permission : userRolePermissions) {
            RoleOption roleOption = new RoleOption();
            if (permission.getAddOrEdit().equals("Edit")) {
                RoleOption oldRoleOption = roleOptionServie.findByOptionIdAndRoleId(Long.parseLong(permission.getOptionId()), permission.getSelectedRole());
                roleOption.setRoleOptionId(oldRoleOption.getRoleOptionId());
            }
            roleOption.setRoleId(permission.getSelectedRole());
            roleOption.setOptionId(Long.parseLong(permission.getOptionId()));
            roleOption.setCanAdd(permission.getAddPermission());
            roleOption.setCanEdit(permission.getEditPermission());
            roleOption.setCanDelete(permission.getDeletePermission());
            roleOptionServie.save(roleOption);
        }
        return "redirect:security/user_role_permission";
    }

    @Override
    public List<Module> getModuleList() {
        List<Module> modules = moduleService.findAll().stream().
                filter(s -> s.getIsDeleted() == null || s.getIsDeleted() == 0)
                .collect(Collectors.toList());
        return modules;
    }

    @Override
    public List<Role> getRoleList() {
            List<Role> roles = roleService.findAll();
            return roles;
    }

    @Override
    public String getModuleWiseTree(Long moduleId, Long roleId) {
        //        List<OptionSetupView> optionSetupViews = optionSetupViewService.findAll().stream()
//                .filter(s -> s.getModuleId() == moduleId).collect(Collectors.toList());
        List<OptionSetupView> optionSetupViews = optionSetupViewService.findOptionSetupViewBymoduleId(moduleId);

        ArrayList<Long> visitedModules = new ArrayList<>();
        ArrayList<Long> visitedSubModules = new ArrayList<>();

        Node<TreeElement> root = new Node<>(new TreeElement("root", 0L, " ", 0));

        for (OptionSetupView element : optionSetupViews) {
            Node<TreeElement> nodeModule = null;
            if (visitedModules.contains(element.getModuleId())) { //retrieve node if previously added to tree
                nodeModule = root.getChild(element.getModuleName(), "module");
            } else { //add new child node and add to visited module list
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

        String optionSetupTree = printTree(root, roleId);
        finalTree = "";
        return optionSetupTree;
    }


    String finalTree = "";

    private <T> String printTree(Node<TreeElement> node, Long roleId) {
        if (!node.getData().getName().equals("root")) {

            String pA = "";
            String postA = "<br></a>";
            if (node.getData().getType().equals("module")) {
                pA = "<ul><li data-type='module' data-id='" + node.getData().getID() + "'><a><input type='checkbox' id='moduleStatus' name='moduleStatus' value='moduleCheck'>";
            }

            if (node.getData().getType().equals("subModule")) {
                pA = "<ul><li data-type='subModule' data-id='" + node.getData().getID() + "'><a><input type='checkbox' name='subModuleStatus' value='subModuleCheck'>";
            }

            if (node.getData().getType().equals("option")) {
                RoleOption roleOption = roleOptionServie.findByOptionIdAndRoleId(node.getData().getID(), roleId);

//                if (roleOption != null) {
//                    if (roleOption.getRoleId() != roleId) {
//                        roleOption = null;
//                    }
//                }
                //
//                roleOption = roleOption.stream().
//                        filter(s -> s.getRoleId().equals(roleId))
//                        .collect(Collectors.toList());

                if (roleOption != null) {  // exists in database
                    pA = "<ul><li data-type='option' data-id='" + node.getData().getID() + "' data-subModId='" + node.getParent().getData().getID() + "'>"
                            + "<input type='hidden' name='optionId' value='" + node.getData().getID() + "'>"
                            + "<input type='hidden' name='addOrEdit' value='Edit'>"
                            + "<a>"
                            + "<input type='hidden' name='optionStatus' value='0'>"
                            + "<input type='checkbox' name='optionStatus' value='1' checked>";

                    postA = "<input type='hidden' name='addPermission' value='0'>";
                    if (("1").equals(roleOption.getCanAdd())) {
                        postA = postA + "<input type='checkbox' data-id='" + node.getData().getID() + "'name='addPermission' value='1' checked> Can Add ";
                    } else {
                        postA = postA + "<input type='checkbox' data-id='" + node.getData().getID() + "'name='addPermission' value='1'> Can Add ";
                    }
                    postA = postA + "<input type='hidden' name='editPermission' value='0'>";

                    if (("1").equals(roleOption.getCanEdit())) {
                        postA = postA + "<input type='checkbox' data-id='" + node.getData().getID() + "' name='editPermission' value='1' checked> Can Edit";
                    } else {
                        postA = postA + "<input type='checkbox' data-id='" + node.getData().getID() + "' name='editPermission' value='1'> Can Edit";
                    }
                    postA = postA + "<input type='hidden' name='deletePermission' value='0'>";

                    if (("1").equals(roleOption.getCanDelete())) {
                        postA = postA + "<input type='checkbox' data-id='" + node.getData().getID() + "' name='deletePermission' value='1' checked>Can Delete";
                    } else {
                        postA = postA + "<input type='checkbox' data-id='" + node.getData().getID() + "' name='deletePermission' value='1'> Can Delete";
                    }
                }

                else {
                    pA = "<ul><li data-type='option' data-id='" + node.getData().getID() + "' data-subModId='" + node.getParent().getData().getID()
                            + "'>"
                            + "<input type='hidden' name='optionId' value='" + node.getData().getID() + "'>"
                            + "<input type='hidden' name='addOrEdit' value='Add'>"
                            + "<a>"
                            + "<input type='hidden' name='optionStatus' value='0'>"
                            + "<input type='checkbox' name='optionStatus' value='1'>";

                    postA = "<input type='hidden' name='addPermission' value='0'>"
                            + "<input type='checkbox' data-id='" + node.getData().getID() + "'name='addPermission' value='1'> Can Add "
                            + "<input type='hidden' name='editPermission' value='0'>"
                            + "<input type='checkbox' data-id='" + node.getData().getID() + "' name='editPermission' value='1'> Can Edit "
                            + "<input type='hidden' name='deletePermission' value='0'>"
                            + "<input type='checkbox' data-id='" + node.getData().getID() + "' name='deletePermission' value='1'> Can Delete ";
                }
            }

            finalTree = finalTree + pA + node.getData().getName() + postA;
        }

        node.getChildren().forEach(each -> printTree(each, roleId));

        if (!node.getData().getName().equals("root")) {
            finalTree = finalTree + "</li></ul>";
        }
        return finalTree;
    }


    @Override
    public List<RoleOption> getCurrentPermission() {
        List<RoleOption> roleOptions = roleOptionServie.findAll();
        return roleOptions;
    }
}
