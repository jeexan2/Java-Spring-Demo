package com.sit.jbc.service.generic.impl;

import com.sit.jbc.domain.entity.generic.Lookup;
import com.sit.jbc.repository.generic.LookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LookupService {
    @Autowired
    LookupRepository lookupRepository;

    public List<Lookup> findByType(String type){
        return lookupRepository.findByType(type);
    }
    public List<Lookup> findOfficeByOfficeCategory(){
        return lookupRepository.findByType("OFFICE_CATEGORY");
    }
    public List<Lookup> findMunicipalityInfo(){
        return lookupRepository.findByType("MUNI LOC");
    }
    public List<Lookup> findEmploymentType(){
        return lookupRepository.findByTypeAndIsActive("EMPLOYMENT_TYPE", 1L);
    }
    public List<Lookup> findEmployeeType(){
        return lookupRepository.findByTypeAndIsActive("EMPLOYEE_TYPE", 1L);
    }
    public List<Lookup> findGender(){
        return lookupRepository.findByTypeAndIsActive("GENDER", 1L);
    }
    public List<Lookup> findMaritalStatus(){
        return lookupRepository.findByTypeAndIsActive("MARITAL_STATUS", 1L);
    }
    public List<Lookup> findReligion(){
        return lookupRepository.findByTypeAndIsActive("RELIGION", 1L);
    }
    public List<Lookup> findDivDept(){
        return lookupRepository.findByTypeAndIsActive("DIVDEPT", 1L);
    }
    public List<Lookup> findServiceStatus(){
        return lookupRepository.findByTypeAndIsActive("SERVICE_STATUS", 1L);
    }
    public List<Lookup> findPfType(){
        return lookupRepository.findByTypeAndIsActive("CPF_PF_TYPE", 1L);
    }
    public List<Lookup> findBanks(){
        return lookupRepository.findByTypeAndIsActive("BANK", 1L);
    }
    public List<Lookup> findBranch(Long bankId){
        return lookupRepository.findByTypeAndIsActiveAndRefId("BANK_BRANCH", 1L, bankId);
    }
    public List<Lookup> findEmployeeStatus(){
        return lookupRepository.findByTypeAndIsActive("EMPLOYEE_STATUS", 1L);
    }
    public List<Lookup> findActivityStatus(){
        return lookupRepository.findByTypeAndIsActive("ACTIVITY_STATUS", 1L);
    }
}