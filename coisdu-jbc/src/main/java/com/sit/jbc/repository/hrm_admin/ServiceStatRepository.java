package com.sit.jbc.repository.hrm_admin;

import com.sit.jbc.domain.entity.hrm_admin.ServiceStat;

/**
 * Created by User on 01-Nov-18.
 */
public interface ServiceStatRepository {
    ServiceStat findByGradeId(Long serviceId);
}
