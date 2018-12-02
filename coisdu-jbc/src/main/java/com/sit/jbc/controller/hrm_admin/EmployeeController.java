package com.sit.jbc.controller.hrm_admin;

import com.sit.jbc.domain.dto.hrm_admin.EmployeeAddressModel;
import com.sit.jbc.domain.dto.hrm_admin.EmployeeModel;
import com.sit.jbc.domain.dto.security.AccessPermission;
import com.sit.jbc.domain.dto.security.LoginUser;
import com.sit.jbc.domain.entity.generic.Lookup;
import com.sit.jbc.domain.entity.hrm_admin.Designation;
import com.sit.jbc.domain.entity.hrm_admin.Employee;
import com.sit.jbc.domain.entity.security.User;
import com.sit.jbc.service.generic.CountryService;
import com.sit.jbc.service.generic.DistrictService;
import com.sit.jbc.service.generic.DivisionService;
import com.sit.jbc.service.generic.ThanaService;
import com.sit.jbc.service.generic.impl.LookupService;
import com.sit.jbc.service.hrm_admin.DesignationService;
import com.sit.jbc.service.hrm_admin.EmployeeService;
import com.sit.jbc.service.hrm_admin.GradeService;
import com.sit.jbc.service.security.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping(value = "/hrm_admin")
public class EmployeeController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    LookupService lookupService;
    @Autowired
    DesignationService designationService;
    @Autowired
    GradeService gradeService;
    @Autowired
    OfficeService officeService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DivisionService divisionService;
    @Autowired
    DistrictService districtService;
    @Autowired
    ThanaService thanaService;
    @Autowired
    CountryService countryService;
    private Object empSetupId;

    @RequestMapping(value = "/employee_general_setup", method = RequestMethod.POST)
    public String setupGeneralEmployee(@Validated @ModelAttribute("employeemodel")EmployeeModel employeeModel,
                                       BindingResult result, Model model,
                                       RedirectAttributes redirectAttributes) throws IOException {
        Employee employee = new Employee();
        try{
            employee = employeeService.save(employeeModel);
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("status", "success");
            throw e;
    }
        httpSession.setAttribute("empSetupId", employee.getHrmEmployeeId());
        redirectAttributes.addFlashAttribute("status", "success");
        return "redirect:/hrm_admin/employee_setup";
    }

    @RequestMapping(value = "/employee_setup", method = RequestMethod.GET)
    public String setupEmployee(Model model) {
        Object empSetupId = httpSession.getAttribute("empSetupId");
        Employee employee = new Employee();

        if(empSetupId != null) {
            System.out.println("empSetupId: " + empSetupId );
            employee = employeeService.findEmployeeById((Long)empSetupId);
        }
        //employee = employeeService.findEmployeeById(39500L);

        AccessPermission accessPermission = (AccessPermission) httpSession.getAttribute("userPermission");
        model.addAttribute("permissionCheck", accessPermission);
        model.addAttribute("employmentTypeList", lookupService.findEmploymentType());
        model.addAttribute("employeeType", lookupService.findEmployeeType());
        model.addAttribute("employeeGenderList", lookupService.findGender());
        model.addAttribute("employeeReligionList", lookupService.findReligion());
        model.addAttribute("employeeMarList", lookupService.findMaritalStatus());
        model.addAttribute("employeeDesigList", designationService.findAllActive(1L));
        model.addAttribute("employeeGradeList", gradeService.findAllActive(1L));
        model.addAttribute("employeeOfficeList", officeService.findAllActive(1L));
        model.addAttribute("employeeDivDeptList", lookupService.findDivDept());

        model.addAttribute("employeeActStatusList", null);
        model.addAttribute("employeeStatusList", null);
        model.addAttribute("employeeServiceStatusList", lookupService.findServiceStatus());
        model.addAttribute("employeeSalaryPaymentServiceStatusList", null);

        model.addAttribute("pfDeductionStatusList", null);
        model.addAttribute("pfTypeList", lookupService.findPfType());
        model.addAttribute("pensionPaymentStatusList", null);
        model.addAttribute("bankList", lookupService.findBanks());
        model.addAttribute("employeeStatusList", lookupService.findEmployeeStatus());
        model.addAttribute("employeeActStatusList", lookupService.findActivityStatus());

        model.addAttribute("employee", employee);
        model.addAttribute("divisionList",divisionService.findAll());
        model.addAttribute("districtList",districtService.findAll());
        model.addAttribute("thanaList",thanaService.findAll());
        model.addAttribute("countryList",countryService.findAll());
        return "hrm_admin/employee_setup";
    }

    @RequestMapping(value = "/get_branch_details", method = RequestMethod.POST)
    @ResponseBody
    public List<Lookup> getBranchDetails(Long bankId) {
        return lookupService.findBranch(bankId);
    }

    @RequestMapping(value = "/employee_address_setup", method = RequestMethod.POST)
    public String setupAddressEmployee(@Validated @ModelAttribute("employeeAddressModel")EmployeeAddressModel employeeAddressModel,
                                       BindingResult result, Model model,
                                       RedirectAttributes redirectAttributes) throws IOException{

        EmployeeAddressModel addressModel = new EmployeeAddressModel();

        return "redirect:/hrm_admin/employee_setup";
    }
}