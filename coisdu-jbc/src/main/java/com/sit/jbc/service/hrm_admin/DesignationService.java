package com.sit.jbc.service.hrm_admin;
import com.sit.jbc.domain.entity.hrm_admin.Designation;

import java.util.List;
/**
 * Created by User on 01-Nov-18.
 */
public interface DesignationService {
        Designation save(Designation designation);
        void delete(Designation designation);
        Designation findByDesignationId(Long id);
        List<Designation> findAll();
        List<Designation> findAllActive(Long isActive);
}
