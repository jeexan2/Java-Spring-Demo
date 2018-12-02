package com.sit.jbc.service.hrm_admin.impl;

import com.sit.jbc.domain.entity.hrm_admin.Designation;
import com.sit.jbc.repository.hrm_admin.DesignationRepository;
import com.sit.jbc.service.hrm_admin.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DesigantionServiceImpl implements DesignationService {
    @Autowired
    DesignationRepository designationRepository;
    @Override
    public Designation save(Designation designation) {
        return designationRepository.save(designation);
    }

    @Override
    public void delete(Designation designation) {
        designationRepository.delete(designation);
    }

    @Override
    public Designation findByDesignationId(Long id) {
        return designationRepository.findByDesignationId(id);
    }

    @Override
    public List<Designation> findAll() {
        return designationRepository.findAll(

        );
    }
    @Override
    public List<Designation> findAllActive(Long isActive) {
        return designationRepository.findByIsActive(isActive);
    }
}