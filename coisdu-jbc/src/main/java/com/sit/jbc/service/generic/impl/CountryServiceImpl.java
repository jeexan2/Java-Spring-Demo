package com.sit.jbc.service.generic.impl;
import com.sit.jbc.domain.entity.generic.Country;
import com.sit.jbc.repository.generic.CountryRepository;
import com.sit.jbc.service.generic.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Created by User on 29-Oct-18.
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;
    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public void delete(Country country) {
        countryRepository.delete(country);
    }

    @Override
    public Country findByCountryId(Long id) {
        return countryRepository.findByCountryId(id);
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll(

        );
    }
}
