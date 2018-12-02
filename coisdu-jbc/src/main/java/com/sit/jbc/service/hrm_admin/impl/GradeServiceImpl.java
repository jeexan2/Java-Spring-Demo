package com.sit.jbc.service.hrm_admin.impl;

import com.sit.jbc.domain.entity.hrm_admin.Grade;
import com.sit.jbc.repository.hrm_admin.GradeRepository;
import com.sit.jbc.service.hrm_admin.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by User on 08-Oct-18.
 */
@Service
public class GradeServiceImpl implements GradeService {
@Autowired
    GradeRepository gradeRepository;
    @Override
    public Grade save(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public void delete(Grade grade) {
        gradeRepository.delete(grade);
    }

    @Override
    public Grade findByGradeId(Long id) {
        return gradeRepository.findByGradeId(id);
    }

    @Override
    public List<Grade> findAll() {
        return gradeRepository.findAll(

        );
    }
    @Override
    public List<Grade> findAllActive(Long isActive) {
        return gradeRepository.findByIsActive(isActive);
    }
}
