package com.sit.jbc.repository.security;

import com.sit.jbc.domain.dto.security.Dropdown;
import com.sit.jbc.domain.dto.security.OfficeTable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 11-Oct-18.
 */
@Repository
public class OfficeProcedureRepositoryImpl implements OfficeProcedureRepository {
    @PersistenceContext
    EntityManager em;
    @Override
    public List<OfficeTable> getOfficeTableData() {
        List<OfficeTable> retOfficeList = new ArrayList<>();
        StoredProcedureQuery query = em
                .createStoredProcedureQuery("GET_OFFICE_TABLE_LIST")
                .registerStoredProcedureParameter(1, Class.class,
                        ParameterMode.REF_CURSOR);
        query.execute();
        retOfficeList = OfficeTable.convertToOfficeTablePojo(query.getResultList());
        return retOfficeList;
    }

   /* @Override
    public Dropdown getOfficeByCategoryForDropdown(Long categoryId) {

        StoredProcedureQuery query = em
                .createStoredProcedureQuery("GET_OFFICE_TABLE_LIST_BY_CATEGORY")
                .registerStoredProcedureParameter("p_CATEGORY",BigDecimal.class,ParameterMode.IN)
                .registerStoredProcedureParameter("cur_OUT",Class.class,ParameterMode.REF_CURSOR)
                .setParameter("p_CATEGORY",new BigDecimal(categoryId));

        query.execute();
        //officeList = query.getResultList();
        Dropdown officeDropdown = Dropdown.convertToDropdownList(query.getResultList(),"Office");
        return officeDropdown;
    }*/
}
