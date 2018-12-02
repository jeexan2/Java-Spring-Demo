package com.sit.jbc.repository.generic;
import com.sit.jbc.domain.entity.generic.Country;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Created by User on 29-Oct-18.
 */
public interface CountryRepository extends JpaRepository <Country,Long> {
    Country findByCountryId(Long countryId);
    Country findByCountryName(String CountryName);
}

