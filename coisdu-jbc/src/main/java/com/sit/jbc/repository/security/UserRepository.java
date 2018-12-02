package com.sit.jbc.repository.security;

import com.sit.jbc.domain.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by User on 22-Oct-18.
 */
public interface UserRepository extends JpaRepository<User,Long>{
    User findByUserId(long userId);
    User findByUsername(String userName);
}
