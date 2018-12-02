package com.sit.jbc.repository.security;

import com.sit.jbc.domain.dto.security.UserTable;

import java.util.List;

/**
 * Created by User on 23-Oct-18.
 */
public interface UserProcedureRepository {
    List<UserTable> getUserTableData();
}
