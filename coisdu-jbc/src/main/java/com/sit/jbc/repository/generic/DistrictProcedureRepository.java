package com.sit.jbc.repository.generic;

import com.sit.jbc.domain.dto.generic.DistrictDataTable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 04-Nov-18.
 */
@Repository
public interface DistrictProcedureRepository {
    List<DistrictDataTable> getDistrictDataTable();
}
