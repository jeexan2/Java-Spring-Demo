package com.sit.jbc.domain.entity.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Geetanjali Oishe on 10/7/2018.
 */
@Getter
@Setter

@Entity
@Table (name = "SEC_OPTION")
public class Option {

    @Id
    @SequenceGenerator(name="pk_seq",sequenceName="sec_option_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pk_seq")

    @Column(name = "SEC_OPTION_ID")
    private Long optionId;

    @NotNull(message = "Option name can not be null")
    @Column(name = "NAME")
    private String optionName;

    @Column(name = "DISPLAY_NAME")
    private String displayName;

    @Column(name = "ACCESS_URL")
    private String accessUrl;

    @NotNull(message = "Active status can not be null")
    @Column(name = "IS_ACTIVE")
    private Integer isActive;

    @NotNull(message = "Creator user can not be null")
    @Column(name = "CREATED_BY")
    private Integer createdBy;

    @NotNull(message = "Creation timestamp can not be null")
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
    private Integer deletedBy;

    @Column(name = "DELETED_ON")
    private Date deletedOn;

    @NotNull(message = "SUB-Module ID can not be null")
    @Column (name = "SEC_SUB_MODULE_ID")
    private Long subModuleID;
}
