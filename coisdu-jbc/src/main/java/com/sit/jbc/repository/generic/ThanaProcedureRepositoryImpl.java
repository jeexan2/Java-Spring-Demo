package com.sit.jbc.repository.generic;

import com.sit.jbc.domain.dto.generic.ThanaDataTable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.swing.text.Element;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 04-Nov-18.
 */
@Repository
public class ThanaProcedureRepositoryImpl implements ThanaProcedureRepository {
    @PersistenceContext
    EntityManager em;
    @Override
    public List<ThanaDataTable> getThanaDataTable() {
        List<ThanaDataTable> ret = new ArrayList<ThanaDataTable>();
        StoredProcedureQuery query = em
                .createStoredProcedureQuery("GET_THANA_TABLE_LIST")
                .registerStoredProcedureParameter(1, Class.class,
                        ParameterMode.REF_CURSOR);
        query.execute();

        ret = ThanaDataTable.convertToThanaDataTable(query.getResultList());
        return ret;
    }
}
