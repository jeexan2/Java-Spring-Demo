package com.sit.jbc.domain.entity.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Geetanjali Oishe on 10/9/2018.
 */
@Getter
@Setter

@Entity
@Table(name = "SEC_OPTION_SETUP_VIEW")
public class OptionSetupView {

    @Id
    @Column(name = "SEC_OPTION_SETUP_VIEW_ID")
    private Long viewId;

    @Column(name = "SEC_MODULE_ID")
    private Long moduleId;

    @Column(name = "SEC_MODULE_NAME")
    private String moduleName;

    @Column(name = "SEC_SUB_MODULE_ID")
    private Long subModuleId;

    @Column(name = "SEC_SUB_MODULE_NAME")
    private String subModuleName;

    @Column(name = "SEC_OPTION_ID")
    private Long optionId;

    @Column(name = "SEC_OPTION_NAME")
    private String optionName;

    @Column(name = "SEC_ACCESS_URL")
    private String accessUrl;

    @Column(name = "SEC_MODULE_DISPLAY_NAME")
    private  String moduleDispName;

    @Column(name = "SEC_SUB_MODULE_DISPLAY_NAME")
    private  String subModuleDispName;

    @Column(name = "SEC_OPTION_DISPLAY_NAME")
    private  String optionDispName;

    @Column(name = "MODULE_ACTIVE")
    private  String moduleActiveSts;

    @Column(name = "SUB_MODULE_ACTIVE")
    private  String subModuleActiveSts;

    @Column(name = "OPTION_ACTIVE")
    private  String optionActiveSts;

}
