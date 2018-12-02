package com.sit.jbc.repository.security;

import com.sit.jbc.domain.entity.security.Option;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Geetanjali Oishe on 10/7/2018.
 */
public interface OptionRepository extends JpaRepository<Option, Long> {
    Option findByOptionId(Long id);
    Option findByOptionName(String name);
}
