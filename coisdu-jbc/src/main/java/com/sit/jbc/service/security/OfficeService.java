package com.sit.jbc.service.security;

import com.sit.jbc.domain.dto.security.OfficeTable;
import com.sit.jbc.domain.dto.security.OfficeTablePojo;
import com.sit.jbc.domain.entity.security.Office;
import com.sit.jbc.domain.entity.security.Role;


import java.util.List;

/**
 * Created by User on 23-Sep-18.
 */
public interface OfficeService {
    Office save(Office office);
    void delete(Office office);
    List<Office> findAll();
    Office findBySecOfficeId(Long secOfficeId);
    Long callTestProcedure();
    List<OfficeTable> getOfficeDetails();
    void safeDelete(long officeId);
    List<Office> findByCategory(long categoryId);
    OfficeTable getRowDetailsById(long officeId);
    List<Office> findAllActive(Long isActive);

}
