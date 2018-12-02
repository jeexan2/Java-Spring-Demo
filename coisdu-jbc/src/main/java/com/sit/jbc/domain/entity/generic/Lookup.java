package com.sit.jbc.domain.entity.generic;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name ="GEN_LOOKUP" )
public class Lookup {
    @Id
    @SequenceGenerator(name="pk_seq",sequenceName="sequence_id",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pk_seq")
    @Column(name = "GEN_LOOKUP_ID")
    private Long lookupId;

    @Column(name = "REF_ID")
    private Long refId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DISPLAY_NAME")
    private String displayName;

    @Column(name = "SHORT_NAME")
    private String shortName;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IS_MIGRATED")
    private Boolean isMigrated;
    //changed to Long from bool
    @Column(name = "IS_ACTIVE")
    private Long isActive;

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
    private Boolean isDeleted;

    @Column(name = "DELETED_BY")
    private String deletedBy;

    @Column(name = "DELETED_ON")
    private Date deletedOn;

    @Column(name = "MIGRATED_ON")
    private Date migratedOn;

    @Column(name = "OLD_TBL_PK_ID")
    private String oldTblPkID;



}