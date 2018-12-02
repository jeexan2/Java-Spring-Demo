package com.sit.jbc.service.hrm_admin;

import com.sit.jbc.domain.dto.hrm_admin.EmployeeModel;
import com.sit.jbc.domain.entity.hrm_admin.Employee;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface EmployeeService {
    Employee save(EmployeeModel empModel) throws IOException;
    Employee findEmployeeById(Long hrmEmployeeId);
}