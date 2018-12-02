package com.sit.jbc.repository.security;

import com.sit.jbc.domain.dto.security.Dropdown;
import com.sit.jbc.domain.dto.security.OfficeTable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by User on 11-Oct-18.
 */
@Repository
public interface OfficeProcedureRepository {
    List<OfficeTable> getOfficeTableData();
//    Dropdown getOfficeByCategoryForDropdown(Long categoryId);

}
