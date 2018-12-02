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
@Table(name ="HRM_GRADE" )

public class Grade {
    @Id
    @SequenceGenerator(name="pk_seq_hrm",sequenceName="HRM_GRADE_SEQUENCE",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pk_seq_hrm")
    @Column(name = "GRADE_ID")
    private Long gradeId;

    @NotNull(message = "Grade name can not be null")
    @Column(name = "GRADE_NAME")
    private String gradeName;

    @NotBlank(message = "Short name can not be blank")
    @Column(name = "SHORT_NAME")
    private String shortName;

    //@NotNull(message = "Is active value can not be null")
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


}
