package com.sit.jbc.controller.security;

import com.sit.jbc.common.BaseResponse;
import com.sit.jbc.common.MessagType;
import com.sit.jbc.domain.entity.security.Role;
import com.sit.jbc.service.security.RoleService;
import com.sit.jbc.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by MAMUN on 11-Sep-18.
 */
@RequestMapping(value = "/role")
@Controller
public class RoleController {
    @Autowired
    RoleService roleService;

    BaseResponse baseResponse = new BaseResponse();

    @GetMapping(value = "/page")
    public String getRolePage(Model model){

        model.addAttribute("role", new Role());
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "security/role";
    }

/*    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> saveRole(@RequestBody @Validated Role role) throws URISyntaxException {
        BaseResponse response = new BaseResponse();
        if (role.getRoleId() != null) {
            //return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new project cannot already have an ID")).body(null);
            response.setStatus(MessagType.FAIL);
            response.setMessage("Entity already exist with this Id");
            response.setDetails("");
            response.setTimestamp(DateTimeUtil.getCurrentTimeStampInGmt());
            return ResponseEntity.ok(response);
        }
        Role result = roleService.save(role);
        if(result.getRoleId() == null){
            response.setStatus(MessagType.FAIL);
            response.setMessage("Failed to save");
            response.setDetails("");
            response.setTimestamp(DateTimeUtil.getCurrentTimeStampInGmt());
        }else{
            response.setStatus(MessagType.SUCCESS);
            response.setMessage("Saved successfully and ID is :" +result.getRoleId());
            response.setDetails("");
            response.setTimestamp(DateTimeUtil.getCurrentTimeStampInGmt());
        }
        return  ResponseEntity.ok(response);
    }*/

    @PostMapping(value = "/saveOrUpdate")
    public String saveProject(@ModelAttribute("role")final Role role, final BindingResult bindingResult, final ModelMap model, HttpServletRequest request) {
        String type = request.getParameter("update")!=null?request.getParameter("update"):"";
        String delete = request.getParameter("delete") != null?request.getParameter("delete"):"" ;
        if(type.equalsIgnoreCase("update") || delete.equalsIgnoreCase("delete") ){
            Role result = roleService.findRoleById(role.getRoleId());
            role.setRoleId(result.getRoleId());
        }

        if (bindingResult.hasErrors()) {
            System.out.println("=====================error======================" + bindingResult.getFieldErrors());
            return "redirect:/role/page";
        }

        if(delete.equalsIgnoreCase("delete") ) {
            roleService.delete(role);
        }else {
            role.setIsActive(1);
            role.setCreatedBy(1);
            role.setCreatedOn(DateTimeUtil.getCurrentTimeStamp());
            roleService.save(role);
        }
        return "redirect:/role/page";
    }

    @RequestMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@RequestBody @Validated Role role) throws URISyntaxException {
        BaseResponse response = new BaseResponse();

        if(role.getRoleId() == null){
            response.setStatus(MessagType.FAIL);
            response.setMessage("Failed to update");
            response.setDetails("Id can not be null during update");
            response.setTimestamp(DateTimeUtil.getCurrentTimeStampInGmt());
        }else{
            Role result = roleService.save(role);

            response.setStatus(MessagType.SUCCESS);
            response.setMessage("Update successfully and ID is :" +result.getRoleId());
            response.setDetails("");
            response.setTimestamp(DateTimeUtil.getCurrentTimeStampInGmt());
        }

        return  ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(@RequestBody  Role role) throws URISyntaxException {
        BaseResponse response = new BaseResponse();

        roleService.delete(role);
        response.setStatus(MessagType.SUCCESS);
        response.setMessage("Deleted successfully");
        response.setDetails("");
        response.setTimestamp(DateTimeUtil.getCurrentTimeStampInGmt());

        return  ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/find-all", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Role>> findAll() throws URISyntaxException {
        BaseResponse response = new BaseResponse();
        List<Role> roles = roleService.findAll();
        return  ResponseEntity.ok(roles);
    }
    @RequestMapping(value = "/viewRole")
    public ModelAndView roleModify(@ModelAttribute("roleId")Role role, Model model){
        Role result = roleService.findRoleById(role.getRoleId());
        model.addAttribute("role" , result);
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return new ModelAndView("security/role");
    }
}
