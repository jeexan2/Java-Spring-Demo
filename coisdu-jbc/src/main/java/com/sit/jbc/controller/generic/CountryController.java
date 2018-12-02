package com.sit.jbc.controller.generic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.sit.jbc.domain.entity.generic.Country;
import com.sit.jbc.domain.entity.security.Role;
import com.sit.jbc.service.generic.CountryService;
import com.sit.jbc.service.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;
/**
 * Created by User on 29-Oct-18.
 */
@Controller
@RequestMapping(value="/generic")
public class CountryController {
    @Autowired
    CountryService countryService;
    @RequestMapping(value="/countrySetup",method= RequestMethod.GET)
    public String getPage(Model model){
        List<Country> countries = countryService.findAll();
        model.addAttribute("countries", countryService.findAll());
        Country country = countryService.findByCountryId((long)2);
        country.setIsActive(1);
        return "generic/country";
    }

    @RequestMapping(value="/countrySetup", method = RequestMethod.POST)
    public String addorUpdatePage(@ModelAttribute("country") Country country, Model model){

        Date date = new Date();
        if(country.getCountryId() == -1) {
            country.setCreatedBy(1);
            country.setCreatedOn(date);
            country.setIsMigrated(1);
            country.setIsDeleted(0);
            country.setIsUpdated(0);
            country.setUpdatedOn(date);
            countryService.save(country);
        } else {
            Country oldcountry = countryService.findByCountryId(country.getCountryId());
            country.setIsUpdated(1);
            country.setUpdatedOn(date);
            country.setUpdatedBy(1);
            country.setIsMigrated(oldcountry.getIsMigrated());
            country.setMigratedOn(oldcountry.getMigratedOn());
            country.setCreatedBy(oldcountry.getCreatedBy());
            country.setCreatedOn(oldcountry.getCreatedOn());
            countryService.save(country);
        }
        Country country1 = new Country();
        model.addAttribute("country",country1);
        model.addAttribute("countries", countryService.findAll());
        List<Country> countries = countryService.findAll();

        return "generic/country";
    }

    @RequestMapping(value="/getCountry/{id}",method = RequestMethod.GET)
    public @ResponseBody Country getCountry(@PathVariable("id") long id){
        Country country = countryService.findByCountryId(id);
        return country;
    }
}
