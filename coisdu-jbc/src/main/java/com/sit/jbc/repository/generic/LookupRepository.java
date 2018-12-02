package com.sit.jbc.repository.generic;

import com.sit.jbc.domain.entity.generic.Lookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LookupRepository extends JpaRepository<Lookup, Long> {
    //Lookup findByGenLookupId(Long genLookupid);
    List<Lookup> findByType(String type);
    List<Lookup> findByTypeAndIsActive(String type, Long isActive);
    Lookup findByValue(int value);
    Lookup findByLookupId(Long lookupId);
    List<Lookup> findByIsDeleted(Boolean isDeleted);
    List<Lookup> findByTypeAndIsActiveAndRefId(String type, Long isActive, Long refId);
}