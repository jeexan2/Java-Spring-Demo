package com.sit.jbc.service.generic.impl;

import com.sit.jbc.domain.dto.generic.DivisionDataTable;
import com.sit.jbc.domain.entity.generic.Division;
import com.sit.jbc.repository.generic.DivisionRepository;
import com.sit.jbc.service.generic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 02-Oct-18.
 */
@Service
@Transactional
public class DivisionServiceImpl implements DivisionService {
    @Autowired
    DivisionRepository divisionRepository;
    @Override
    public Division save(Division division) {
        if(division.getDivisionId() == -1){
            division.setIsMigrate(false);
            division.setCreatedBy((long)1);
            division.setCreatedOn(new Date());
            division.setIsDeleted(false);
        }
        else {
            Division tempDivision = divisionRepository.
                    findByDivisionId(division.getDivisionId());
            division.setCreatedOn(tempDivision.getCreatedOn());
            division.setIsMigrate(tempDivision.getIsMigrate());
            division.setIsDeleted(tempDivision.getIsDeleted());
            division.setCreatedBy(tempDivision.getCreatedBy());
            division.setIsUpdated(true);
            division.setUpdatedBy((long)1);

            division.setUpdatedOn(new Date());
        }
        return divisionRepository.save(division);
    }

    @Override
    public void delete(Division division) {
        divisionRepository.delete(division);
    }

    @Override
    public List<Division> findAll() {
        return divisionRepository.findByIsDeleted(false);
    }

    @Override
    public List<DivisionDataTable> getDivisionDataTable() {
        return DivisionDataTable.convertToDivisionDataTable(divisionRepository.
                findByIsDeleted(false));
    }

    @Override
    public Division findByDivisionId(Long divisionId) {
        return divisionRepository.findByDivisionId(divisionId);
    }

    @Override
    public void safeDelete(Long divisionId) {
        Division division = divisionRepository.findByDivisionId(divisionId);
        division.setIsDeleted(true);
        divisionRepository.save(division);
    }

    /**  @Override
    public Division findBySecDivisionId(Long secDivisionId) {
        return divisionRepository.getOne(secDivisionId);
    }**/


}
