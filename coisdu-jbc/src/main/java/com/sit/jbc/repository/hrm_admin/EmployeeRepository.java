package com.sit.jbc.repository.hrm_admin;

import com.sit.jbc.domain.entity.hrm_admin.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByHrmEmployeeId(Long hrmEmployeeId);
}