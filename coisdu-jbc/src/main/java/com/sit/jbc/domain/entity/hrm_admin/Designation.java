package com.sit.jbc.domain.entity.hrm_admin;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter

@Entity
@Table(name ="HRM_DESIGNATION" )
public class Designation {
    @Id
    @SequenceGenerator(name="pk_seq_hrm",sequenceName="HRM_DESIGNATION_SEQ",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pk_seq_hrm")
    @Column(name = "DESIGNATION_ID")
    private Long designationId;

    @NotNull(message = "Designation name can not be null")
    @Column(name = "DESIGNATION_NAME")
    private String designationName;

    @NotNull(message = "Designation Short name can not be blank")
    @Column(name = "DESIGNATION_SHORT_NAME")
    private String designationShortName;

    @Column(name = "EMPLOYMENT_TYPE_ID")
    private Integer employmentTypeId;

    @Column(name = "EMPLOYEE_TYPE_ID")
    private Integer employeeTypeId;

    @Column(name = "IS_ACTIVE")
    private Long isActive;

    @Column(name = "IS_MIGRATED")
    private Integer isMigrated;

    @Column(name = "MIGRATED_ON")
    private Date migratedOn;

    @Column(name = "CREATED_BY")
    private Integer createdBy;

    @Column(name = "CREATED_ON")
    private Date createdOn;

    @Column(name="IS_UPDATED")
    private Integer isUpdated;

    @Column(name = "UPDATED_BY")
    private Integer updatedBy;

    @Column(name = "UPDATED_ON")
    private Date updatedOn;

    @Column(name = "IS_DELETED")
    private Integer isDeleted;

    @Column(name = "DELETED_BY")
    private Integer deletedBy;

    @Column(name = "DELETED_ON")
    private Date deletedOn;

    @Column(name = "DESIGNATION_HIERARCHY_OLD")
    private Integer designationHierarchyOld;

    @Column(name = "OLD_TBL_PK_ID")
    private String oldTblPkId;
}
