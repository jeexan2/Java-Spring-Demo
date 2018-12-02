package com.sit.jbc.controller.generic;

import com.sit.jbc.domain.entity.generic.District;
import com.sit.jbc.domain.entity.generic.Thana;
import com.sit.jbc.service.generic.DistrictService;
import com.sit.jbc.service.generic.DivisionService;
import com.sit.jbc.service.generic.ThanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 04-Nov-18.
 */
@Controller
@RequestMapping(value = "/generic")
public class ThanaController {
    @Autowired
    DivisionService divisionService;
    @Autowired
    DistrictService districtService;
    @Autowired
    ThanaService thanaService;

    @RequestMapping(value = "/thana",method = RequestMethod.GET)
    public String getThanaPage(Model model){
        model.addAttribute("dataUnavailable","Data Unavailable");
        //model.addAttribute("district",new District());
        model.addAttribute("divisionList",divisionService.findAll());
        model.addAttribute("thanaTableData",thanaService.getThanaListDataTable());
        //model.addAttribute("",)
        return "generic/thana";
    }
    @RequestMapping(value = "/thana",method = RequestMethod.POST)
    public String addOrUpdateThana(@ModelAttribute("thana") Thana thana, Model model){
        thanaService.save(thana);
        return "redirect:/generic/thana";
    }


    @RequestMapping(value = "/getDistrictList",method = RequestMethod.GET)
    public @ResponseBody
    List<District> getDistrictList(@RequestParam("divisionCode") long divisionId){
        List<District> districts = districtService.getDistrictListByDivisionForDropdown(divisionId);
        return districts;
    }

    @RequestMapping(value = "/thana/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Thana getThana(@PathVariable("id") long id) {
        Thana thana = thanaService.getThana(id);
        return thana;
    }

    @RequestMapping(value = "/deleteThana/{id}",method = RequestMethod.POST)
    public String deleteThana(@PathVariable("id") Long id, Model model) {
        thanaService.safeDelete(id);
        return "/generic/division";
    }

    @RequestMapping(value = "/getThanaForDistrict/{id}",method = RequestMethod.GET)
    public @ResponseBody List<Thana> getThanaForDistrict(@PathVariable("id") Long id){
        return thanaService.getThanaListByDistrictForDropdown(id);
    }

}
