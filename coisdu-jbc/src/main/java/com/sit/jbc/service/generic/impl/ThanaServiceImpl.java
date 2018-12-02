package com.sit.jbc.service.generic.impl;

import com.sit.jbc.domain.dto.generic.ThanaDataTable;
import com.sit.jbc.domain.dto.security.SessionUser;
import com.sit.jbc.domain.entity.generic.Thana;
import com.sit.jbc.repository.generic.ThanaProcedureRepository;
import com.sit.jbc.repository.generic.ThanaRepository;
import com.sit.jbc.service.generic.ThanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User on 03-Oct-18.
 */
@Service
@Transactional
public class ThanaServiceImpl implements ThanaService {
    @Autowired
    ThanaRepository thanaRepository;
    @Autowired
    ThanaProcedureRepository thanaProcedureRepository;
    @Override
    public Thana save(Thana thana) {
        SessionUser user = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(thana.getThanaId() == -1) {
            thana.setCreatedBy(user.getUserId());
            //thana.setCreatedBy((long)1);
            thana.setCreatedOn(new Date());
            thana.setIsMigrate(false);
            thana.setIsDeleted(false);

        }
        else {
            Thana tempThana = thanaRepository.
                    findByThanaId(thana.getThanaId());
            thana.setCreatedBy(tempThana.getCreatedBy());
            thana.setCreatedOn(tempThana.getCreatedOn());
            thana.setIsDeleted(tempThana.getIsDeleted());
            thana.setIsMigrate(tempThana.getIsMigrate());
            thana.setUpdatedBy(user.getUserId());
            //thana.setUpdatedBy((long)1);
            thana.setUpdatedOn(new Date());
            thana.setIsUpdated(true);
        }
        return thanaRepository.save(thana);
    }

    @Override
    public void delete(Thana thana) {
        thanaRepository.delete(thana);
    }

    @Override
    public List<Thana> findAll() {
        return thanaRepository.findByIsDeleted(false);
    }

    @Override
    public List<Thana> getThanaListByDistrictForDropdown(long districtId) {
        List<Thana> thanaList = new ArrayList<Thana>();
        Thana thana= new Thana();
        thana.setThanaId((long) -1);
        thana.setThanaName("Select Thana");
        thanaList.add(thana);
        thanaList.addAll(thanaRepository.findByDistrictCode(districtId).
                    stream().
                    filter(u -> u.getIsDeleted() == false || u.getIsDeleted() == null).
                    collect(Collectors.toList()));
        return thanaList;
    }

    @Override
    public List<ThanaDataTable> getThanaListDataTable() {
        return thanaProcedureRepository.getThanaDataTable();
    }

    @Override
    public void safeDelete(long thanaId) {
        SessionUser user = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Thana thana = thanaRepository.findByThanaId(thanaId);
        thana.setIsDeleted(true);
        thana.setDeletedOn(new Date());
        thana.setDeletedBy((long)user.getUserId());
        thanaRepository.save(thana);
    }

    @Override
    public Thana getThana(Long thanaId) {
        return thanaRepository.findByThanaId(thanaId);
    }
}
