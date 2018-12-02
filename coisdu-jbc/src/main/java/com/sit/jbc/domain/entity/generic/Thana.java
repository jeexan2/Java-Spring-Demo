package com.sit.jbc.domain.entity.generic;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by User on 03-Oct-18.
 */
@Getter
@Setter
@Entity
@Table(name = "GEN_THANA")
public class Thana {
    @Id
    @Column(name = "GEN_THANA_ID")
    @SequenceGenerator(name="pk_gen_thana",sequenceName="GEN_THANA_SEQUENCE",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pk_gen_thana")
    private Long thanaId;

    @Column(name = "DIVISION_CODE")
    private Long divisionCode;

    @Column(name = "DISTRICT_CODE")
    private Long districtCode;

    @Column(name = "THANA_NAME")
    private String thanaName;

    @Column(name = "SHORT_NAME")
    private String shortName;

    @Column(name = "NATIONAL_CODE")
    private String nationalCode;

    @Column(name = "IS_MIGRATE")
    private Boolean isMigrate;

    @Column(name = "MIGRATED_ON")
    private Date migratedOn;

    @Column(name = "IS_DELETED")
    private Boolean isDeleted;

    @Column(name = "DELETED_BY")
    private Long deletedBy;

    @Column(name = "DELETED_ON")
    private Date deletedOn;

    @Column(name = "CREATED_BY")
    private Long createdBy;

    @Column(name = "CREATED_ON")
    private Date createdOn;

    @Column(name = "IS_UPDATED")
    private Boolean isUpdated;

    @Column(name = "UPDATED_BY")
    private Long updatedBy;

    @Column(name = "UPDATED_ON")
    private Date updatedOn;

    @Column(name = "OLD_TBL_PK_ID")
    private String oldTblPkID;


}
