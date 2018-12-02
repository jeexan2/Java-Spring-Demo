package com.sit.jbc.domain.entity.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by User on 23-Sep-18.
 */

@Getter
@Setter

@Entity
@Table(name ="SEC_OFFICE" )
public class Office {
    @Id
    @SequenceGenerator(name="pk_seq_office",sequenceName="sec_office_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pk_seq_office")
    @Column(name = "SEC_OFFICE_ID")
    private Long secOfficeId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SHORT_NAME")
    private String shortName;
    @Column(name = "TYPE")
    private Long type;
    @NotNull(message = "Office Category can't be null!")
    @Column(name = "CATEGORY")
    private Long category;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EMAIL")
    private String email;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "CONTACT_NO")
    private String contactNumber;
    @Column(name = "DIVISION_ID")
    private Long divisionId;
    @Column(name = "DISTRICT_ID")
    private Long districtId;
    @Column(name = "THANA_ID")
    private Long thanaId;
    @Column(name = "MUCPLTY_ID")
    private Long municipalityId;
    @Column(name = "POST_CODE")
    private String postCode;
    // changed type boolean to long
    @NotNull(message = "Active status can't be null!")
    @Column(name = "IS_ACTIVE")
    private Long isActive;

    @Column(name = "IS_MIGRATE")
    private Boolean isMigrated;
    @Column(name = "MIGRATED_ON")
    private Date migratedOn;
    @NotNull(message = "This field can't be remain null!")
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
    @Column(name = "IS_DELETED")
    private Boolean isDeleted;
    @Column(name = "DELETED_BY")
    private Long deletedBy;
    @Column(name = "DELETED_ON")
    private Date deletedOn;
    @Column(name = "OLD_TBL_PK_ID")
    private String oldTblPkID;


}
