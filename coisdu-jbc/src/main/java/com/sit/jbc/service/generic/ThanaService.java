package com.sit.jbc.service.generic;

import com.sit.jbc.domain.dto.generic.ThanaDataTable;
import com.sit.jbc.domain.entity.generic.Thana;

import java.util.List;

/**
 * Created by User on 03-Oct-18.
 */
public interface ThanaService {
    Thana save(Thana thana);
    void delete(Thana thana);
    List<Thana> findAll();
    List<Thana> getThanaListByDistrictForDropdown(long districtId);
    List<ThanaDataTable> getThanaListDataTable();
    void safeDelete(long thanaId);
    Thana getThana(Long thanaId);
}
