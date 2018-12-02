package com.sit.jbc.controller.security;

import com.sit.jbc.domain.dto.security.AccessPermission;
import com.sit.jbc.domain.dto.security.ChangePassword;
import com.sit.jbc.domain.dto.security.LoginUser;
import com.sit.jbc.domain.dto.security.SessionUser;
import com.sit.jbc.domain.entity.security.OptionSetupView;
import com.sit.jbc.domain.entity.security.User;
import com.sit.jbc.exception.CustomException;
import com.sit.jbc.service.generic.impl.LookupService;
import com.sit.jbc.service.security.AuthService;
import com.sit.jbc.service.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@Controller
@RequestMapping(value = "/security")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    JwtService jwtService;
    @Autowired
    LookupService lookupService;
    @Autowired
    HttpSession httpSession;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String testAuthUser(@RequestParam(value = "error", required = false) String error,
                               Map<String, Object> model,
                               HttpServletRequest httpServletRequest) throws IOException {
        System.out.println("here1");
        User user = authService.getUserByUsernameAndOfficeCategory("admin", 1L);
        System.out.println("User details: " + user.getUsername() + " " + user.getPassword() + " " + user.getActivateOn());
        if (error != null) {
            System.out.println("Invalid username or password!");
        }
        return "security/login";
    }

    @RequestMapping(value = "/login-user", method = RequestMethod.GET)
    public String loginUser(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            @RequestParam(value = "expired", required = false) String expired,
                            Model model) {
        if(error != null) {
            model.addAttribute("error", "Bad Credentials!");
        }
        if(logout != null) {
            model.addAttribute("logout", "You have been logged out!");
        }
        if(expired != null) {
            model.addAttribute("expired", "Session has been expired!");
        }

        model.addAttribute("officeCategoryList", lookupService.findByType("OFFICE_CATEGORY"));

        return "security/login_user";
    }

   /* @RequestMapping(value = "/login-user", method = RequestMethod.GET)
    public String loginUser(Model model) {
        String status = "";
        status = httpSession.getAttribute("StatusBanner") == null ? "" : httpSession.getAttribute("StatusBanner").toString();
        model.addAttribute("StatusBannerText", status);
     //   httpSession.removeAttribute("logInStatus");
        //destroy
        model.addAttribute("officeCategoryList", lookupService.findByType("OFFICE_CATEGORY"));

        return "security/login_user";
    }*/

    @RequestMapping(value = "/login-user", method = RequestMethod.POST)
    public String loginUser(@Validated @ModelAttribute("login-user")LoginUser loginUser,
                            BindingResult result, Model model) {
        String token = jwtService.getEncryptedToken(loginUser.getUsername() + "::" + loginUser.getOfficeCategory());
        model.addAttribute("user_token", token);

        Long officeCategory = 0L;
        try{
            officeCategory = Long.parseLong(loginUser.getOfficeCategory());
        }
        catch (Exception e){
           // httpSession.setAttribute("StatusBanner", "Bad Credentials!");
            return "redirect:/security/login-user?error=true";
           // return "redirect:/security/login-user";
        }

        User user =  authService.getUserByUsernameAndOfficeCategory(loginUser.getUsername(), officeCategory);
        if(user != null) {
            if (!user.getOfficeCategory().toString().equals(loginUser.getOfficeCategory())) {
                //httpSession.setAttribute("StatusBanner", "Bad Credentials!");
                return "redirect:/security/login-user?error=true";
               // return "redirect:/security/login-user";
            }
            model.addAttribute("user_name", user.getUsername());
        }
        if(user == null){
           // httpSession.setAttribute("StatusBanner", "Bad Credentials!");
           // return "redirect:/security/login-user";
            return "redirect:/security/login-user?error=true";
        }
        return "security/auth_user";
    }


    @GetMapping(value = "/test")
    public String showTest(Model model) throws CustomException {
        //model.addAttribute("tree", authService.getMenuElementsHtml(23L));
        //throw new CustomException("4000", "Err", "Something happens...");
        int i = 1/0;
        return "security/test";
    }

    @GetMapping(value = "/dashboard")
    public String showDashboard(Model model) {
        SessionUser user = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("permissions", user.getCurrentPermissions());
        return "security/dashboard";
    }

    @GetMapping(value = "/change_password")
    public String passwordChange(Model model) {

        return "security/change_password";
    }

    @PostMapping(value = "/change_password")
    public String changePassword(@Validated @ModelAttribute("password-change")ChangePassword changePassword,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes) {
        try {
            SessionUser user = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //authService.updatePassword(changePassword, user.getUserId());
            redirectAttributes.addFlashAttribute("status", "success");
            return "redirect:/security/change_password";
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("status", "error");
            return "redirect:/security/change_password";
        }

    }

   @RequestMapping(value = "/getUserPermission", method = RequestMethod.GET)
    public @ResponseBody
    AccessPermission getPermission() {

        try {
            AccessPermission accessPermission = (AccessPermission) httpSession.getAttribute("userPermission");
            return accessPermission;
        }
        catch (Exception e){
            return new AccessPermission((long)0, (long)0, (long)0);
        }
    }

}