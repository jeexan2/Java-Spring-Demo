package com.sit.jbc.repository.generic;

/**
 * Created by User on 03-Oct-18.
 */
import com.sit.jbc.domain.entity.generic.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DivisionRepository extends JpaRepository<Division,Long> {
  //  @Query(value="SELECT d from SEC_DIVISION d where d.SEC_DIVISION_ID = id",nativeQuery = true)
    //public Division findBySecOfficeId(@Param("id") Long id);
    List<Division> findByIsDeleted(boolean isDeleted);
    Division findByDivisionId(Long divisionId);
}