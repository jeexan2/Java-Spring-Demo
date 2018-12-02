package com.sit.jbc.domain.entity.security;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name ="SEC_USER" )
public class User {
    @Id
    @SequenceGenerator(name="pk_seq_user",sequenceName="sec_user_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pk_seq_user")
    @Column(name = "SEC_USER_ID")
    private Long userId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "LAST_PASSWORD")
    private String lastPassword;

    @Column(name = "IS_ACTIVE")
    private Long isActive;

    @Column(name = "INACTIVE_ON")
    private Date inactiveOn;

    @Column(name = "INACTIVE_REASON")
    private String inactiveReason;

    @Column(name = "ACTIVATE_ON")
    private Date activateOn;

    @Column(name = "ACTIVATE_TO")
    private Date activateTo;

    @Column(name = "NO_OF_TRY")
    private Integer noOfTry;

    @Column(name = "IS_LOCKED")
    private Integer isLocked;

    @Column(name = "LOCKED_ON")
    private Date lockedOn;

    @Column(name = "LOCKED_REASON")
    private String lockedReason;

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

    @Column(name = "SEC_OFFICE_TYPE")
    private Integer officeType;

    @Column(name = "SEC_OFFICE_CATEGORY")
    private Long officeCategory;

    @Column(name = "HRM_EMP_ID")
    private Long empId;

    @Column(name = "SEC_OFFICE_ID")
    private Long officeId;

    @Column(name = "SEC_ROLE_ID")
    private Long roleId;

    @Column(name = "IS_MIGRATED")
    private Integer isMigrated;

}