package com.sit.jbc.controller.generic;

import com.sit.jbc.domain.entity.generic.District;
import com.sit.jbc.domain.entity.generic.Division;
import com.sit.jbc.service.generic.DistrictService;
import com.sit.jbc.service.generic.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 04-Nov-18.
 */
@Controller
@RequestMapping(value = "/generic")
public class DistrictController {
    @Autowired
    DistrictService districtService;
    @Autowired
    DivisionService divisionService;

    @RequestMapping(value = "/district",method = RequestMethod.GET)
    public String getDistrictPage(Model model){
        model.addAttribute("dataUnavailable","Data Unavailable");
        model.addAttribute("district",new District());
        model.addAttribute("divisionList",divisionService.findAll());
        model.addAttribute("districtTableData",districtService.getDistrictDataTable());
        return "generic/district";
    }

    @RequestMapping(value = "/district",method = RequestMethod.POST)
    public String addOrUpdateDistrict(@ModelAttribute("district")District district, Model model){
        districtService.save(district);
        return "redirect:/generic/district";
    }
    @RequestMapping(value = "/deleteDistrict/{id}",method = RequestMethod.POST)
    public String deleteDistrict(@PathVariable("id") Long id, Model model) {
        districtService.safeDelete(id);
        return "generic/district";
    }

    @RequestMapping(value = "/district/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    District getDistrict(@PathVariable("id") Long id) {
        District district = districtService.getDistrict(id) ;
        return district;
    }


    @RequestMapping(value = "/getDistrictForDivision/{id}",method = RequestMethod.GET)
    public @ResponseBody
    List<District> getDistrictByDivision(@PathVariable("id") Long divisionId){
        List<District> districts = districtService.getDistrictListByDivisionForDropdown(divisionId);
        return districts;
    }



}
