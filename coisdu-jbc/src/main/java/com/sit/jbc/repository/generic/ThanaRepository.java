package com.sit.jbc.repository.generic;

import com.sit.jbc.domain.entity.generic.Thana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 03-Oct-18.
 */
@Repository
public interface ThanaRepository extends JpaRepository<Thana,Long> {
    List<Thana> findByDistrictCode(Long districtCode);
    Thana findByThanaId(Long thanaId);
    List<Thana> findByIsDeleted(boolean isDeleted);
}
