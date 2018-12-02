package com.sit.jbc.service.hrm_admin.impl;

import com.sit.jbc.domain.dto.hrm_admin.EmployeeModel;
import com.sit.jbc.domain.entity.hrm_admin.Employee;
import com.sit.jbc.repository.hrm_admin.EmployeeRepository;
import com.sit.jbc.service.hrm_admin.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Value("${upload.path}")
    private String path;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee save(EmployeeModel empModel) throws IOException {

        Employee employee = convertToEntity(empModel);

        employee = employeeRepository.save(employee);

        String photoName = fileUpload(empModel.getEmpPhoto());
        String sigName = fileUpload(empModel.getEmpSig());

        return employee;
    }

    @Override
    public Employee findEmployeeById(Long hrmEmployeeId) {
        return employeeRepository.findByHrmEmployeeId(hrmEmployeeId);
    }


    private String fileUpload(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return null;
        }
        else {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            InputStream is = file.getInputStream();

            Files.copy(is, Paths.get(path + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }
    }
    private Employee convertToEntity(EmployeeModel empModel){
        Employee employee = new Employee();

//        employee.setEmpGid(empModel.getEmpGid());
        employee.setEmpId(empModel.getEmpId());

        employee.setEmpFileNo(empModel.getEmpFileNo());
        employee.setSalut(empModel.getSalut());
        employee.setEmpNmEng(empModel.getEmpNmEng());
        employee.setEmpNmBng(empModel.getEmpNmBng());
        employee.setBirthDt(empModel.getBirthDt());
        employee.setJoinDt(empModel.getJoinDt());
        employee.setConfirmDt(empModel.getConfirmDt());
        employee.setLprDt(empModel.getLprDt());
        employee.setRetireDt(empModel.getRetireDt());


//        employee.setPayLevel(empModel.getPayLevel());
//        employee.setCurrBasic(empModel.getCurrBasic());
//        employee.setLastIncrDt(empModel.getLastIncrDt());
//        employee.setLastPromDt(empModel.getLastPromDt());
//        employee.setPenPayStat(empModel.getPenPayStat());
//        employee.setPreviousBasic(empModel.getPreviousBasic());
//
//        employee.setPfcDedStat(empModel.getPfcDedStat());
//        employee.setEmpGlacNo(empModel.getEmpGlacNo());
//        employee.setTargetBasis(empModel.getTargetBasis());
//        employee.setTargetAmount(empModel.getTargetAmount());

//        employee.setEffectDt(empModel.getEffectDt());
//        employee.setTargetTerm(empModel.getTargetTerm());
//        employee.setSalPayStat(empModel.getSalPayStat());
//        employee.setBankAccountNo(empModel.getBankAccountNo());
//
//        employee.setBankAccountStatus(empModel.getBankAccountStatus());
//        employee.setMemType(empModel.getMemType());
//        employee.setEffectDtOnSalary(empModel.getEffectDtOnSalary());

//        employee.setFreedomFighterSt(empModel.getFreedomFighterSt());
//        employee.setDisabilityEmpSt(empModel.getDisabilityEmpSt());
//        employee.setDisabilityChildSt(empModel.getDisabilityChildSt());

        employee.setDesignationId(empModel.getDesignationId());
        employee.setOfficeId(empModel.getOfficeId());
        employee.setGradeId(empModel.getGradeId());
        employee.setReligionId(empModel.getReligionId());
        employee.setMunilocId(empModel.getMunilocId());
        employee.setGenderId(empModel.getGenderId());
        employee.setEmploymentTypeId(empModel.getEmploymentTypeId());
        employee.setEmployeeTypeId(empModel.getEmployeeTypeId());
        employee.setDivdeptId(empModel.getDivdeptId());


//        employee.setActivityCd(empModel.getActivityCd());
//        employee.setServiceCd(empModel.getServiceCd());
//        employee.setEmployementStCd(empModel.getEmployementStCd());
//        employee.setPaysId(empModel.getPaysId());
//        employee.setMaritalStatCd(empModel.getMaritalStatCd());
//        employee.setBankBrCd(empModel.getBankBrCd());

        return employee;
    }

}