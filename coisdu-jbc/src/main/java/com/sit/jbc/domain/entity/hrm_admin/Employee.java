package com.sit.jbc.domain.entity.hrm_admin;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name ="HRM_EMPLOYEE")
public class Employee {
    @Id
    @SequenceGenerator(name="pk_seq_employee", sequenceName="HRM_EMPLOYEE_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pk_seq_employee")
    @Column(name = "HRM_EMPLOYEE_ID")
    private Long hrmEmployeeId;

    @Column(name = "EMP_GID")
    private Long empGid;

    @Column(name = "EMP_ID")
    private String empId;

    @Column(name = "EMP_FILE_NO")
    private String empFileNo;

    @Column(name = "SALUT")
    private String salut;

    @Column(name = "EMP_NM_ENG")
    private String empNmEng;

    @Column(name = "EMP_NM_BNG")
    private String empNmBng;

    @Column(name = "BIRTH_DT")
    private Date birthDt;

    @Column(name = "JOIN_DT")
    private Date joinDt;

    @Column(name = "CONFIRM_DT")
    private Date confirmDt;

    @Column(name = "LPR_DT")
    private Date lprDt;

    @Column(name = "RETIRE_DT")
    private Date retireDt;

    @Column(name = "ACTIVITY_CD")
    private String activityCd;

    @Column(name = "SERVICE_CD")
    private String serviceCd;

    @Column(name = "EMPLOYMENT_ST_CD")
    private String employementStCd;

    @Column(name = "PAYSC_ID")
    private Long paysId;

    @Column(name = "PAY_LEVEL")
    private String payLevel;

    @Column(name = "CURR_BASIC")
    private BigDecimal currBasic;

    @Column(name = "LAST_INCRE_DT")
    private Date lastIncrDt;

    @Column(name = "LAST_PROM_DT")
    private Date lastPromDt;

    @Column(name = "PEN_PAY_STAT")
    private String penPayStat;

    @Column(name = "MARITAL_STAT_CD")
    private String maritalStatCd;

    @Column(name = "PREVIOUS_BASIC")
    private BigDecimal previousBasic;

    @Column(name = "PFC_DED_STAT")
    private String pfcDedStat;

    @Column(name = "EMP_GLAC_NO")
    private String empGlacNo;

    @Column(name = "TARGET_BASIS")
    private char targetBasis;

    @Column(name = "TARGET_AMOUNT")
    private BigDecimal targetAmount;

    @Column(name = "EFFECT_DT")
    private Date effectDt;

    @Column(name = "TARGET_TERM")
    private Long targetTerm;

    @Column(name = "SAL_PAY_STAT")
    private String salPayStat;

    @Column(name = "BANK_ACCOUNT_NO")
    private String bankAccountNo;

    @Column(name = "BANK_BR_CD")
    private String bankBrCd;

    @Column(name = "BANK_ACCOUNT_STATUS")
    private char bankAccountStatus;

    @Column(name = "MEM_TYPE")
    private String memType;

    @Column(name = "INCHARGE_ST")
    private char inchargeSt;

    @Column(name = "EFFECT_DT_ON_SALARY")
    private Date effectDtOnSalary;

    @Column(name = "FREEDOM_FIGHTER_ST")
    private char freedomFighterSt;

    @Column(name = "DISABILITY_EMP_ST")
    private char disabilityEmpSt;

    @Column(name = "DISABILITY_CHILD_ST")
    private char disabilityChildSt;

    @Column(name = "DESIGNATION_ID")
    private Long designationId;

    @Column(name = "OFFICE_ID")
    private Long officeId;

    @Column(name = "GRADE_ID")
    private Long gradeId;

    @Column(name = "RELIGION_ID")
    private Long religionId;

    @Column(name = "MUNILOC_ID")
    private Long munilocId;

    @Column(name = "GENDER_ID")
    private Long genderId;

    @Column(name = "EMPLOYMENT_TYPE_ID")
    private Long employmentTypeId;

    @Column(name = "EMPLOYEE_TYPE_ID")
    private Long employeeTypeId;

    @Column(name = "DIVDEPT_ID")
    private Long divdeptId;

    // rest columns

    @Column(name = "IS_ACTIVE")
    private Long isActive;

    @Column(name = "IS_MIGRATED")
    private Long isMigrated;

    @Column(name = "CREATED_BY")
    private Integer createdBy;

    @Column(name = "CREATED_ON")
    private Date createdOn;

    @Column(name = "IS_UPDATED")
    private Integer isUpdated;

    @Column(name = "UPDATED_BY")
    private Integer updatedBy;

    @Column(name = "UPDATED_ON")
    private Date updatedOn;

    @Column(name = "IS_DELETED")
    private Integer isDeleted;

    @Column(name = "DELETED_BY")
    private String deletedBy;

    @Column(name = "DELETED_ON")
    private Date deletedOn;

    @Column(name = "MIGRATED_ON")
    private Date migratedOn;
}