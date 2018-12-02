package com.sit.jbc.repository.hrm_admin;

import com.sit.jbc.domain.entity.hrm_admin.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by User on 08-Oct-18.
 */
public interface GradeRepository extends JpaRepository<Grade,Long> {
Grade findByGradeId(Long gradeId);
    List<Grade> findByIsActive(Long isActive);
}
