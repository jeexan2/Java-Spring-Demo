package com.sit.jbc.domain.entity.security;

/**
 * Created by MAMUN on 11-Sep-18.
 */

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter

@Entity
@Table(name ="SEC_ROLE" )
public class Role {

    @Id
    @SequenceGenerator(name="pk_seq",sequenceName="sec_role_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pk_seq")
    @Column(name = "SEC_ROLE_ID")
    private Long roleId;

    @NotNull(message = "Role name can not be null")
    @Column(name = "ROLE_NAME")
    private String roleName;

    @NotBlank(message = "Dispaly name can not be blank")
    @Column(name = "DISPLAY_NAME")
    private String displayName;

    @NotNull(message = "Is active value can not be null")
    @Column(name = "IS_ACTIVE")
    private Integer isActive;

    @NotNull(message = "Creator user can not be null")
    @Column(name = "CREATED_BY")
    private Integer createdBy;

    @NotNull(message = "Creation timestamp can not be null")
    @Column(name = "CREATED_ON")
    private String createdOn;

    @Column(name="IS_UPDATED")
    private Integer isUpdated;

    @Column(name = "UPDATED_BY")
    private Integer updatedBy;

    @Column(name = "UPDATED_ON")
    private String updatedOn;

    @Column(name = "IS_DELETED")
    private Integer isDeleted;

    @Column(name = "DELETED_BY")
    private Integer deletedBy;

    @Column(name = "DELETED_ON")
    private String deletedOn;

}

