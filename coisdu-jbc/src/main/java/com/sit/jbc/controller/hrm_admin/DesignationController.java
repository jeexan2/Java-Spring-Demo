package com.sit.jbc.controller.hrm_admin;

import com.sit.jbc.domain.dto.security.SessionUser;
import com.sit.jbc.service.generic.LookupService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.sit.jbc.domain.entity.hrm_admin.Designation;
import com.sit.jbc.domain.entity.security.Role;
import com.sit.jbc.service.hrm_admin.DesignationService;
import com.sit.jbc.service.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/hrm_admin")
public class DesignationController {
    @Autowired
    DesignationService designationService;
    @Autowired
    LookupService lookupService;
    @RequestMapping(value="/desigSetup",method= RequestMethod.GET)
    public String getPage(Model model){
        model.addAttribute("empleeList", lookupService.findByType("EMPLOYEE_TYPE"));
        List<Designation> designations = designationService.findAll();
        model.addAttribute("designations", designationService.findAll());
       // Designation designation = designationService.findByDesignationId((long)2);
      //  designation.setIsActive(1);
        return "hrm_admin/designationSetup";
    }

    @RequestMapping(value="/desigSetup", method = RequestMethod.POST)
    public String addorUpdatePage(@ModelAttribute("designation") Designation designation, Model model){

        Date date = new Date();
        if(designation.getDesignationId() == -1) {
            SessionUser user = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            designation.setCreatedBy(Math.toIntExact(user.getUserId()));
            //designation.setCreatedBy(1);
            designation.setCreatedOn(date);
            designation.setIsMigrated(1);
            designation.setIsDeleted(0);
            designation.setIsUpdated(0);
            designation.setUpdatedOn(date);
            designationService.save(designation);
        } else {
            Designation olddesignation = designationService.findByDesignationId(designation.getDesignationId());
            designation.setIsUpdated(1);
            designation.setUpdatedOn(date);
            designation.setUpdatedBy(1);
            designation.setIsMigrated(olddesignation.getIsMigrated());
            designation.setMigratedOn(olddesignation.getMigratedOn());
            designation.setCreatedBy(olddesignation.getCreatedBy());
            designation.setCreatedOn(olddesignation.getCreatedOn());
            designationService.save(designation);
        }
        Designation designation1 = new Designation();
        model.addAttribute("designation",designation1);
        model.addAttribute("designations", designationService.findAll());
        List<Designation> designations = designationService.findAll();

        return "hrm_admin/designationSetup";
    }

    @RequestMapping(value="/getDesignation/{id}",method = RequestMethod.GET)
    public @ResponseBody Designation getDesignation(@PathVariable("id") long id){
        Designation designation = designationService.findByDesignationId(id);
        return designation;
    }
}
