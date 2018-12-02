package com.sit.jbc.repository.security;

import com.sit.jbc.domain.dto.security.UserTable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 23-Oct-18.
 */
@Repository
public class UserProcedureRepositoryImpl implements UserProcedureRepository {
    @PersistenceContext
    EntityManager em;
    @Override
    public List<UserTable> getUserTableData() {
        List<UserTable> retUserTable = new ArrayList<UserTable>();
        StoredProcedureQuery query = em
                .createStoredProcedureQuery("GET_USER_TABLE_LIST")
                .registerStoredProcedureParameter(1, Class.class,
                        ParameterMode.REF_CURSOR);
        query.execute();
        retUserTable = UserTable.convertToUserTablePojo(query.getResultList());
        return retUserTable;
    }
}
