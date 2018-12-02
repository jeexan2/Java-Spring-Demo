package com.sit.jbc.service.generic.impl;

/**
 * Created by User on 03-Oct-18.
 */

import com.sit.jbc.domain.dto.generic.DistrictDataTable;
import com.sit.jbc.domain.dto.security.SessionUser;
import com.sit.jbc.domain.entity.generic.District;
import com.sit.jbc.repository.generic.DistrictProcedureRepository;
import com.sit.jbc.repository.generic.DistrictRepository;
import com.sit.jbc.service.generic.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User on 03-Oct-18.
 */
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    DistrictProcedureRepository districtProcedureRepository;

    @Override
    public District save(District district) {
        SessionUser user = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(district.getDistrictId() == -1){
            district.setCreatedBy(user.getUserId());
            district.setCreatedOn(new Date());
            district.setIsDeleted(false);
            district.setIsMigrate(false);
        }
        else {
            District tempDistrict = districtRepository.
                    findByDistrictId(district.getDistrictId());
            district.setCreatedOn(tempDistrict.getCreatedOn());
            district.setIsDeleted(tempDistrict.getIsDeleted());
            district.setCreatedBy(tempDistrict.getCreatedBy());
            district.setIsMigrate(tempDistrict.getIsMigrate());
            district.setIsUpdated(true);
            district.setUpdatedOn(new Date());
            district.setUpdatedBy(user.getUserId());
        }
        return districtRepository.save(district);
    }

    @Override
    public void delete(District district) {
        districtRepository.delete(district);
    }

    @Override
    public List<District> findAll() {
        return districtRepository.findByIsDeleted(false);
    }

    @Override
    public List<District> getDistrictListByDivisionForDropdown(Long divisionId) {
        List<District> districts = new ArrayList<District>();
        District district = new District();
        district.setDistrictId((long)-1);
        district.setDistrictName("Select District");
        districts.add(district);
        districts.addAll(districtRepository.findByDivisionCode(divisionId)
                .stream().filter(u-> u.getIsDeleted() == false || u.getIsDeleted() == null).collect(Collectors.toList()));
        return districts;
    }

    @Override
    public List<DistrictDataTable> getDistrictDataTable() {
        return districtProcedureRepository.getDistrictDataTable();
    }

    @Override
    public District getDistrict(Long districtId) {
        return districtRepository.findByDistrictId(districtId);
    }

    @Override
    public void safeDelete(Long id) {
        SessionUser user = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        District district = districtRepository.findByDistrictId(id);
        district.setIsDeleted(true);
        district.setDeletedOn(new Date());
        district.setDeletedBy(user.getUserId());
        districtRepository.save(district);
    }

}
