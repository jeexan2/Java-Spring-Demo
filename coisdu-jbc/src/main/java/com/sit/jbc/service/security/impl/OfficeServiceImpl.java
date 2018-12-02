package com.sit.jbc.service.security.impl;

import com.sit.jbc.domain.dto.security.OfficeTable;
import com.sit.jbc.domain.entity.security.Office;
import com.sit.jbc.repository.generic.DistrictRepository;
import com.sit.jbc.repository.generic.DivisionRepository;
import com.sit.jbc.repository.generic.ThanaRepository;
import com.sit.jbc.repository.security.OfficeRepository;
import com.sit.jbc.repository.security.OfficeProcedureRepository;
import com.sit.jbc.service.security.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User on 23-Sep-18.
 */
@Service
public class OfficeServiceImpl implements OfficeService {
    @Autowired
    OfficeRepository officeRepository;
    @Autowired
    OfficeProcedureRepository officeProcedureRepository;
    @Autowired
    DivisionRepository divisionRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    ThanaRepository thanaRepository;


    //@PersistenceContext
    //EntityManager em;

    @Override
    public Office save(Office office) {
        if(office.getSecOfficeId() == -1){
            office.setCreatedBy((long) 1);
            office.setCategory((long) 1);
            office.setIsDeleted(false);
        }
        else {
            Office oldOffice = officeRepository.findBySecOfficeId(office.getSecOfficeId());
            oldOffice.setIsUpdated(true);
            office.setIsUpdated(true);
            office.setUpdatedOn(new Date());
            office.setCreatedBy(oldOffice.getCreatedBy());
            office.setCreatedOn(oldOffice.getCreatedOn());
            office.setIsDeleted(false);
            office.setSecOfficeId(oldOffice.getSecOfficeId());
        }
        return officeRepository.save(office);
    }

    @Override
    public void delete(Office office) {
        officeRepository.delete(office);
    }

    @Override
    public List<Office> findAll() {
         List<Office> offices = officeRepository.findByIsDeleted(false);

        // List<OfficeTableData> retOffices = new ArrayList<OfficeTableData>();

         return offices;
    }

    @Override
    public Office findBySecOfficeId(Long secOfficeId) {
        return officeRepository.findBySecOfficeId(secOfficeId);
    }

    @Override
    public Long callTestProcedure() {
        return officeRepository.callTestProcedure();
    }

    @Override
    public List<OfficeTable> getOfficeDetails() {
        return officeProcedureRepository.getOfficeTableData();
    }

    @Override
    public void safeDelete(long officeId) {
        Office  office = officeRepository.findBySecOfficeId(officeId);
        office.setIsDeleted(true);
        office.setDeletedOn(new Date());
        officeRepository.save(office);
    }

    @Override
    public List<Office> findByCategory(long categoryId) {
        List<Office> ret = new ArrayList<Office>();
        Office office = new Office();
        office.setSecOfficeId((long)-1);
        office.setName("Select Office");
        ret.add(office);
        ret.addAll(officeRepository.findByCategory(categoryId));
        return ret;
    }

    @Override
    public OfficeTable getRowDetailsById(long officeId) {
        Office office = officeRepository.findBySecOfficeId(officeId);
        OfficeTable officeRow = new OfficeTable();
        officeRow.setAddress(office.getAddress());
        officeRow.setShortName(office.getShortName());
        officeRow.setOfficeName(office.getName());
        officeRow.setSecOfficeId(new BigDecimal(office.getSecOfficeId()));
        officeRow.setDivisionName(
                divisionRepository.findByDivisionId(office.getDivisionId()).
                        getDivisionName());
        officeRow.setDistrictName(
                districtRepository.findByDistrictId(office.getDistrictId()).getDistrictName());
        officeRow.setThanaName(
                thanaRepository.findByThanaId(office.getThanaId()).getThanaName());

        return officeRow;
    }

    @Override
    public List<Office> findAllActive(Long isActive){
        return officeRepository.findByIsActive(isActive);
    }


}
