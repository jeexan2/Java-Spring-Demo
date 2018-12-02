package com.sit.jbc.service.hrm_admin;

import com.sit.jbc.domain.entity.hrm_admin.Grade;

import java.util.List;

/**
 * Created by User on 08-Oct-18.
 */
public interface GradeService {
    Grade save(Grade grade);
    void delete(Grade grade);
    Grade findByGradeId(Long id);
    List<Grade> findAll();
    List<Grade> findAllActive(Long isActive);
}
