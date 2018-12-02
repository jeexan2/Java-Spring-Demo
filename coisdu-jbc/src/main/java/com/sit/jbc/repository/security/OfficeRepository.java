package com.sit.jbc.repository.security;


import com.sit.jbc.domain.entity.security.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import java.util.List;

/**
 * Created by User on 23-Sep-18.
 */
public interface OfficeRepository extends JpaRepository<Office,Long> {
    Office findBySecOfficeId(Long secOfficeId);
    List<Office> findByCategory(Long categoryId);
    List<Office> findByIsActive(Long isActive);
    List<Office> findByIsDeleted(Boolean isDeleted);
    @Procedure(procedureName = "TESTPROCEDURE", outputParameterName = "TEST1")
    public Long callTestProcedure();


}
