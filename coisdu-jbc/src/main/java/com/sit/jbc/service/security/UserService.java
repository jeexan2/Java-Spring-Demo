package com.sit.jbc.service.security;

import com.sit.jbc.domain.dto.security.Dropdown;
import com.sit.jbc.domain.dto.security.UserTable;
import com.sit.jbc.domain.entity.security.Office;
import com.sit.jbc.domain.entity.security.Role;
import com.sit.jbc.domain.entity.security.User;

import java.util.List;

/**
 * Created by User on 22-Oct-18.
 */
public interface UserService {
    User save(User user);
    void delete(User user);
    List<User> findAll();
    User findByUserId(Long userId);

    List<UserTable> getUserDetails();
    void safeDelete(long userId);
    List<Role> roleListForDropdown();
    Boolean findByUserName(String userName);
    //Dropdown officeListByCategory(Long categoryId);

}
