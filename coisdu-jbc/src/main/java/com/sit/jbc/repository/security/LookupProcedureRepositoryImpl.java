package com.sit.jbc.repository.security;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 01-Nov-18.
 */
@Repository
public class LookupProcedureRepositoryImpl implements LookupProcedureRepository
{
    @PersistenceContext
    EntityManager em;

    @Override
    public List<String> getLookupTypeList() {
        List<String> ret = new ArrayList<String>();
        StoredProcedureQuery query = em
                .createStoredProcedureQuery("GET_TYPE_FROM_GEN_LOOKUP")
                .registerStoredProcedureParameter(1, Class.class,
                        ParameterMode.REF_CURSOR);
        query.execute();
        ret = query.getResultList();
        return ret;
    }
}
