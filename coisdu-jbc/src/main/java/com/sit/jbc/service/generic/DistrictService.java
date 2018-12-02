package com.sit.jbc.service.generic;

/**
 * Created by User on 03-Oct-18.
 */
import com.sit.jbc.domain.dto.generic.DistrictDataTable;
import com.sit.jbc.domain.entity.generic.District;

import java.util.List;

/**
 * Created by User on 03-Oct-18.
 */
public interface DistrictService {
    District save(District district);
    void delete(District district);
    List<District> findAll();
    List<District> getDistrictListByDivisionForDropdown(Long divisionId);
    List<DistrictDataTable> getDistrictDataTable();
    District getDistrict(Long districtId);
    void safeDelete(Long id);
    //List<DropDownElement> getDropDownList();
    //District findBySecDistrictId(Long secDistrictId);
}
