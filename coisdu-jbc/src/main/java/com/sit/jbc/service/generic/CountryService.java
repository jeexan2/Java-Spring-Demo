package com.sit.jbc.service.generic;

import java.util.List;

import com.sit.jbc.domain.entity.generic.Country;

/**
 * Created by User on 29-Oct-18.
 */
public interface CountryService {
    Country save(Country country);
    void delete(Country country);
    Country findByCountryId(Long id);
    List<Country> findAll();
}
