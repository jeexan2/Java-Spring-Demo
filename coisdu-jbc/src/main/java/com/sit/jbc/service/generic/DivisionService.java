package com.sit.jbc.service.generic;

import com.sit.jbc.domain.dto.generic.DivisionDataTable;
import com.sit.jbc.domain.entity.generic.Division;

import java.util.List;

/**
 * Created by User on 02-Oct-18.
 */
public interface DivisionService {
    Division save(Division division);
    void delete(Division division);
    List<Division> findAll();
    List<DivisionDataTable> getDivisionDataTable();
    Division findByDivisionId(Long divisionId);

    void safeDelete(Long divisionId);
    // Division findBySecDivisionId(Long secDivisionId);
    // Division findDivisionById(Long secDivisionId);
    // String getOfficeName(Long secDivisionId);
}
