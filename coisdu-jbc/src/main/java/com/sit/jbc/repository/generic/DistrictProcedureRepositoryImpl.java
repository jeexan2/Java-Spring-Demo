package com.sit.jbc.repository.generic;

import com.sit.jbc.domain.dto.generic.DistrictDataTable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 04-Nov-18.
 */
@Repository
public class DistrictProcedureRepositoryImpl implements DistrictProcedureRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<DistrictDataTable> getDistrictDataTable() {
        List<DistrictDataTable> ret = new ArrayList<DistrictDataTable>();
        StoredProcedureQuery query = em
                .createStoredProcedureQuery("GET_DISTRICT_TABLE_LIST")
                .registerStoredProcedureParameter(1, Class.class,
                        ParameterMode.REF_CURSOR);
        query.execute();
        ret = DistrictDataTable.convertToDistrictDataTable(query.getResultList());
        return ret;
    }
}
