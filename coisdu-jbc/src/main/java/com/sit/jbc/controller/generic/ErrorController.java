package com.sit.jbc.controller.generic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping(value = "/error")
public class ErrorController {
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String handleForbiddenAccess(Model model,
                               HttpServletRequest httpServletRequest) throws IOException {
        return "error/403";
    }
}