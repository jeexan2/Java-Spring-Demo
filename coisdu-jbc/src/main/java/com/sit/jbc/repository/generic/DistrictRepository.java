package com.sit.jbc.repository.generic;

/**
 * Created by User on 03-Oct-18.
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.sit.jbc.domain.entity.generic.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by User on 03-Oct-18.
 */
public interface DistrictRepository extends JpaRepository<District,Long> {
    List<District> findByDivisionCode(long divisionId);
    District findByDistrictId(long districtId);
    List<District> findByIsDeleted(boolean isDeleted);
}
