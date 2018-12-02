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
@Table(name ="HRM_SERVICE_STAT" )
public class ServiceStat {
    @Id
    @SequenceGenerator(name="pk_seq_hrm",sequenceName="HRM_SERVICE_STAT_SEQ",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pk_seq_hrm")
    @Column(name = "SERVICE_ID")
    private Long serviceId;

    @NotNull(message = "Service name can not be null")
    @Column(name = "SERVICE_NAME")
    private String serviceName;

    @NotNull(message = "Service Short name can not be blank")
    @Column(name = "SERVICE_SHORT_NAME")
    private String serviceShortName;

    @Column(name = "IS_ACTIVE")
    private Integer isActive;

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
