package com.sit.jbc.domain.dto.hrm_admin;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by User on 20-Nov-18.
 */
@Getter
@Setter
public class EmployeeAddressModel {
    private String presentAddress;
    private Long presentThandaId;
    private Long presentDistrictId;
    private Long presentDivisionId;
    private String presentPhoneNumber;

    private String permanentAddress;
    private Long permanentThandaId;
    private Long permanentDistrictId;
    private Long permanentDivisionId;
    private String permanentPhoneNumber;

    private Long countryId;
    private Long NationalityId;
    private String cellNumber;
    private String fax;
    private String email;
    private String url;
}
