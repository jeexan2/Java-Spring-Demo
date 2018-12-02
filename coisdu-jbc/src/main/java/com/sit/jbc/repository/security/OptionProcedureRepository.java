package com.sit.jbc.repository.security;

import com.sit.jbc.domain.dto.security.RolewiseOptionProcPojo;

import java.math.BigDecimal;
import java.util.List;

public interface OptionProcedureRepository {
    List<RolewiseOptionProcPojo> getRolewiseOptionList(Long roleId);
}