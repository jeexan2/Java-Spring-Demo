package com.sit.jbc.domain.entity.generic;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
/**
 * Created by User on 29-Oct-18.
 */
@Getter
@Setter

@Entity
@Table(name ="HRM_COUNTRY" )
public class Country {
    @Id
    @SequenceGenerator(name="pk_seq_hrm",sequenceName="HRM_COUNTRY_SEQ",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pk_seq_hrm")
    @Column(name = "COUNTRY_ID")
    private Long countryId;

    @NotNull(message = "Country name can not be null")
    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @NotNull(message = "Country Short name can not be blank")
    @Column(name = "COUNTRY_SHORT_NAME")
    private String countryShortName;

    @NotNull(message = "Nationality can not be blank")
    @Column(name = "NATIONALITY")
    private String nationality;

    @NotNull(message = "Phone Code can not be empty")
    @Column(name = "NATIONAL_PHONE_CODE")
    private String phoneCode;

    //@NotNull(message = "Is active value can not be null")
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
