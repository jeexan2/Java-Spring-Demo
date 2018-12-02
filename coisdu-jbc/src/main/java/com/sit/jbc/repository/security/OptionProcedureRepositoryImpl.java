package com.sit.jbc.repository.security;

import com.sit.jbc.domain.dto.security.RolewiseOptionProcPojo;
import com.sit.jbc.domain.dto.security.TestProcPojo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Repository
public class OptionProcedureRepositoryImpl implements OptionProcedureRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<RolewiseOptionProcPojo> getRolewiseOptionList(Long roleId) {
        StoredProcedureQuery query = em
                .createStoredProcedureQuery("GET_ROLEWISE_OPTION")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Class.class, ParameterMode.REF_CURSOR)
                .setParameter(1, roleId);

        query.execute();
        List<RolewiseOptionProcPojo> rolewiseOptionList = RolewiseOptionProcPojo.convertToPojo(query.getResultList());
        return rolewiseOptionList;


    }
}