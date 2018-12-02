package com.sit.jbc.repository.security;

import com.sit.jbc.domain.entity.security.OptionSetupView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Geetanjali Oishe on 10/9/2018.
 */
public interface OptionSetupViewRepository extends JpaRepository<OptionSetupView,Long> {
    List<OptionSetupView> findOptionSetupViewBymoduleId(Long moduleId);
}
