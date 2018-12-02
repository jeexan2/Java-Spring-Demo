package com.sit.jbc.domain.entity.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Geetanjali Oishe on 10/31/2018.
 */
@Getter
@Setter
@Entity
@Table(name ="SEC_ROLE_OPTION")
public class RoleOption {
    @Id
    @SequenceGenerator(name="pk_seq",sequenceName="SEC_ROLE_OPTION_SEQ",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pk_seq")
    @Column(name = "SEC_ROLE_OPTION_ID")
    private Long roleOptionId;

    @NotNull(message = "Role ID can not be null")
    @Column(name = "SEC_ROLE_ID")
    private Long roleId;

    @NotNull(message = "Option ID can not be null")
    @Column(name = "SEC_OPTION_ID")
    private Long optionId;

    @Column(name = "CAN_ADD")
    private String canAdd;

    @Column(name = "CAN_EDIT")
    private String canEdit;

    @Column(name = "CAN_DELETE")
    private String canDelete;
}
