package com.sit.jbc.controller.generic;

import com.sit.jbc.domain.entity.generic.Lookup;
import com.sit.jbc.repository.generic.LookupRepository;
import com.sit.jbc.service.generic.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by User on 16-Oct-18.
 */
@Controller
@RequestMapping(value="/generic")
public class LookupController {
    @Autowired
    LookupService lookupService;

    @RequestMapping(value = "/lookup", method = RequestMethod.GET)
    public String getLookupPage(Model model) {

        model.addAttribute("lookUpTableData", lookupService.getLookupDetails());
        model.addAttribute("dataUnavailable", "Data Unavailable");
        model.addAttribute("typeList",lookupService.getTypeList());
        return "generic/lookup";
    }

    @RequestMapping(value = "/lookup", method = RequestMethod.POST)
    public String addOrUpdateLookUp(@ModelAttribute("lookup") Lookup lookup, Model model) {

        lookupService.save(lookup);
        return "redirect:/generic/lookup";
    }

    @RequestMapping(value = "/lookup/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Lookup getLookup(@PathVariable("id") long id) {
        Lookup lookup = lookupService.findByGenLookupId(id);
        return lookup;
    }

    @RequestMapping(value = "/deleteLookup/{id}",method = RequestMethod.POST)
    public String deleteLookup(@PathVariable("id") Long id, Model model) {
        lookupService.safeDelete(id);
        return "generic/lookup";
    }
}
