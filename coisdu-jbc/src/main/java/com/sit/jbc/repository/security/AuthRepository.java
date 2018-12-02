package com.sit.jbc.repository.security;

import com.sit.jbc.domain.entity.security.Role;
import com.sit.jbc.domain.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User, Long> {
    User findByUsernameAndOfficeCategory(String username, Long officeCategory);
    User findByUserId(Long secUserId);
}