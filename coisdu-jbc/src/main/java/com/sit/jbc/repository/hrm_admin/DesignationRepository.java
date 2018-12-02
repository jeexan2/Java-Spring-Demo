package com.sit.jbc.repository.hrm_admin;

import com.sit.jbc.domain.entity.hrm_admin.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by User on 01-Nov-18.
 */
public interface DesignationRepository extends JpaRepository<Designation,Long>{
    Designation findByDesignationId(Long designationId);
    List<Designation> findByIsActive(Long isActive);
}
