package com.sit.jbc.domain.dto.hrm_admin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class EmployeeModel {
    private Long empGid;

    private String empId;

    private String empFileNo;

    private String salut;

    private String empNmEng;

    private String empNmBng;

    private Date birthDt;

    private Date joinDt;

    private Date confirmDt;

    private Date lprDt;

    private Date retireDt;

    private String activityCd;

    private String serviceCd;

    private String employementStCd;

    private Long paysId;

    private String payLevel;

    private BigDecimal currBasic;

    private Date lastIncrDt;

    private Date lastPromDt;

    private String penPayStat;

    private String maritalStatCd;

    private BigDecimal previousBasic;

    private String pfcDedStat;

    private String empGlacNo;

    private char targetBasis;

    private BigDecimal targetAmount;

    private Date effectDt;

    private Long targetTerm;

    private String salPayStat;

    private String bankAccountNo;

    private String bankBrCd;

    private char bankAccountStatus;

    private String memType;

    private char inchargeSt;

    private Date effectDtOnSalary;

    private char freedomFighterSt;

    private char disabilityEmpSt;

    private char disabilityChildSt;

    private Long designationId;

    private Long officeId;

    private Long gradeId;

    private Long religionId;

    private Long munilocId;

    private Long genderId;

    private Long employmentTypeId;

    private Long employeeTypeId;

    private Long divdeptId;

    private MultipartFile empPhoto;

    private MultipartFile empSig;
}