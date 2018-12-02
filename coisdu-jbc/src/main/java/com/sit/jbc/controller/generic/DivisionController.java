package com.sit.jbc.controller.generic;

import com.sit.jbc.domain.entity.generic.Division;
import com.sit.jbc.domain.entity.generic.Lookup;
import com.sit.jbc.service.generic.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by User on 01-Nov-18.
 */
@Controller
@RequestMapping(value = "/generic")
public class DivisionController
{
    @Autowired
    DivisionService divisionService;

    @RequestMapping(value = "/division",method = RequestMethod.GET)
    public String getDivisionPage(Model model){
        model.addAttribute("division",new Division());
        model.addAttribute("dataUnavailable","Data Unavalaible");
        model.addAttribute("divisionTableData",divisionService.getDivisionDataTable());
        return "generic/division";
    }

    @RequestMapping(value = "/division",method = RequestMethod.POST)
    public String addOrUpdateDivision(@ModelAttribute("division") Division division, Model model){
        divisionService.save(division);
        return "redirect:/generic/division";
    }

    @RequestMapping(value = "/division/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Division getDivision(@PathVariable("id") long id) {
        Division division = divisionService.findByDivisionId(id);
        return division;
    }

    @RequestMapping(value = "/deleteDivision/{id}",method = RequestMethod.POST)
    public String deleteDivision(@PathVariable("id") Long id, Model model) {
        divisionService.safeDelete(id);
        return "/generic/division";
    }
}
