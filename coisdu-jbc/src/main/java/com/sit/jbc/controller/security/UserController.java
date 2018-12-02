package com.sit.jbc.controller.security;

import com.sit.jbc.domain.entity.security.Office;
import com.sit.jbc.domain.entity.security.User;
import com.sit.jbc.service.generic.LookupService;
import com.sit.jbc.service.security.OfficeService;
import com.sit.jbc.service.security.RoleService;
import com.sit.jbc.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by User on 22-Oct-18.
 */
@Controller
@RequestMapping(value="/security")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    LookupService lookupService;
    @Autowired
    RoleService roleService;
    @Autowired
    OfficeService officeService;
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getUserPage(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("dataUnavailable","Data Unavailable");
        model.addAttribute("userDataTable",userService.getUserDetails());
        model.addAttribute("categoryList", lookupService.findOfficeByOfficeCategoryForDropdown());
        model.addAttribute("roleList",userService.roleListForDropdown());
        return "security/user";
    }
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String addOrUpdateUser(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/security/user";
    }
    @RequestMapping(value = "/getOfficeList",method = RequestMethod.GET)
    public @ResponseBody
    List<Office> getDropDownOfOfficeList(@RequestParam("officeCategory") long officeCategoryId){
        return officeService.findByCategory(officeCategoryId);
    }

    @RequestMapping(value="/getUser/{userId}",method = RequestMethod.GET)
    public @ResponseBody User getUser(@PathVariable("userId") Long id){
        return userService.findByUserId((long)id);
    }
    @RequestMapping(value="/userNamechecking/{username}",method = RequestMethod.GET)
    public @ResponseBody Boolean getUser(@PathVariable("username") String name){
      //  return userService.findByUserId((long)id);
        Boolean flag = userService.findByUserName(name);
        return flag;
    }

    @RequestMapping(value = "/deleteUser/{id}",method = RequestMethod.POST)
    public String deleteOffice(@PathVariable("id") Long id, Model model, HttpServletRequest request){
        userService.safeDelete(id);
        return "security/user";

    }


}

