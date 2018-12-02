package com.sit.jbc.service.security.impl;

import com.sit.jbc.domain.dto.security.UserTable;
import com.sit.jbc.domain.entity.security.Role;
import com.sit.jbc.domain.entity.security.User;
import com.sit.jbc.repository.security.*;
import com.sit.jbc.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 22-Oct-18.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserProcedureRepository userProcedureRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    OfficeRepository officeRepository;
    @Autowired
    OfficeProcedureRepository officeProcedureRepository;

    @Override
    public User save(User user) {
        if(user.getUserId() == -1){
            user.setCreatedBy(1);
            user.setIsMigrated(0);
            user.setCreatedOn(new Date());
            user.setOfficeType(1);
            user.setIsDeleted(0);
            //user.setOfficeType((long) user.getOfficeId());
          //  user.setOfficeCategory(1);
           // user.set
            // userRepository.save(user);
        }
        else {
            User tempUser = userRepository.findByUserId(user.getUserId());
            user.setIsUpdated(1);
            user.setUpdatedOn(new Date());
            user.setCreatedBy(tempUser.getCreatedBy());
            user.setCreatedOn(tempUser.getCreatedOn());
            user.setIsMigrated(tempUser.getIsMigrated());
            user.setIsDeleted(tempUser.getIsDeleted());
            user.setOfficeType(tempUser.getOfficeType());
        }
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public List<UserTable> getUserDetails() {
        return userProcedureRepository.getUserTableData();
    }

    @Override
    public void safeDelete(long userId) {
            User user = userRepository.findByUserId(userId);
            user.setIsDeleted(1);
            user.setDeletedOn(new Date());
            userRepository.save(user);
    }
    @Override
    public List<Role> roleListForDropdown() {
        List<Role> roleDropDown = new ArrayList<Role>();
        Role select = new Role();
        select.setRoleId((long)-1);
        select.setRoleName("Select Role");
        roleDropDown.add(select);
        roleDropDown.addAll(roleRepository.findAll());
        return roleDropDown;
    }

    @Override
    public Boolean findByUserName(String userName) {
        return userRepository.findByUsername(userName) != null;
    }

    /*@Override
    public Dropdown officeListByCategory(Long categoryId) {
       Dropdown dropdown = officeProcedureRepository.getOfficeByCategoryForDropdown(categoryId);
      //  Dropdown dropdown = new Dropdown();

        return dropdown;
    }*/


}
