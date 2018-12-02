package com.sit.jbc.repository.generic;

import com.sit.jbc.domain.dto.generic.ThanaDataTable;

import java.util.List;

/**
 * Created by User on 04-Nov-18.
 */
public interface ThanaProcedureRepository {
    List<ThanaDataTable> getThanaDataTable();
}
