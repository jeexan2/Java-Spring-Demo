package com.sit.jbc.controller.security;

import com.sit.jbc.common.BaseResponse;
import com.sit.jbc.domain.dto.security.AccessPermission;
import com.sit.jbc.domain.dto.security.OfficeTable;
import com.sit.jbc.domain.dto.security.SessionUser;
import com.sit.jbc.domain.entity.generic.District;
import com.sit.jbc.domain.entity.generic.Thana;
import com.sit.jbc.domain.entity.security.Office;
import com.sit.jbc.service.generic.LookupService;
import com.sit.jbc.service.generic.ThanaService;
import com.sit.jbc.service.generic.DistrictService;
import com.sit.jbc.service.generic.DivisionService;
import com.sit.jbc.service.security.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Created by User on 23-Sep-18.
 */

@RequestMapping(value = "/security")
@Controller
public class OfficeController {
    @Autowired
    OfficeService officeService;
    @Autowired
    LookupService lookupService;
    @Autowired
    DivisionService divisionService;
    @Autowired
    DistrictService districtService;
    @Autowired
    ThanaService thanaService;

    @Autowired
    HttpSession httpSession;


    BaseResponse baseResponse = new BaseResponse();


    @RequestMapping(value = "/office",method = RequestMethod.GET)
    public String getOfficePage(Model model){
        /*SessionUser user = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccessPermission accessPermission = user.getCurrentPermissions();*/
        AccessPermission accessPermission = (AccessPermission) httpSession.getAttribute("userPermission");
        model.addAttribute("permissionCheck", accessPermission);
        model.addAttribute("office", new Office());
        model.addAttribute("officeDataTable",officeService.getOfficeDetails());
        model.addAttribute("dataUnavailable","Data Unavailable");
        model.addAttribute("categoryList", lookupService.findOfficeByOfficeCategoryForDropdown());
        model.addAttribute("divisionList",divisionService.findAll());
        model.addAttribute("municipalityList", lookupService.findMunicipalityInfo());

        return "security/office";
    }


    @RequestMapping(value = "/office",method = RequestMethod.POST)
    public @ResponseBody OfficeTable addOrUpdateOffice(@ModelAttribute("office") Office office){
        Office retoffice =  officeService.save(office);

        OfficeTable officeTablerow = officeService.getRowDetailsById(retoffice.getSecOfficeId());
        //officeTablerow.setRowId((new BigDecimal(String.valueOf(rowId))));
        return officeTablerow;
    }

    @PostMapping("/officeList")
    @ResponseBody
    public ResponseEntity<?> officeList(){
        return ResponseEntity.ok(officeService.findAll());
    }


    @RequestMapping(value = "/deleteOffice/{id}",method = RequestMethod.POST)
    public @ResponseBody String  deleteOffice(@PathVariable("id") Long id, Model model, HttpServletRequest request){
            officeService.safeDelete(id);
            return "Success";
    }



    @RequestMapping(value="/getOffice/{id}",method = RequestMethod.GET)
    public @ResponseBody Office getOffice(@PathVariable("id") long id){
        return officeService.findBySecOfficeId(id);
    }
    @RequestMapping(value = "/getCategory",method = RequestMethod.GET)
    public @ResponseBody List<Integer> getOfficeCategory(){

        List<Integer> ret = new ArrayList<Integer>();

        return ret;
    }
    @RequestMapping(value = "/getDistrictList",method = RequestMethod.GET)
    public @ResponseBody List<District> getDistrictList(@RequestParam("divisionId") long divisionId){
        List<District> districts = districtService.getDistrictListByDivisionForDropdown(divisionId);
        return districts;
    }
   @RequestMapping(value = "/getThanaList",method = RequestMethod.GET)
    public @ResponseBody List<Thana> getThanaList(@RequestParam("districtId") long districtId){
        List<Thana> thanas = thanaService.getThanaListByDistrictForDropdown(districtId);
        return thanas;
    }



}
