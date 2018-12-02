package com.sit.jbc.domain.dto.security;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Geetanjali Oishe on 11/1/2018.
 */
@Getter
@Setter
public class UserRolePermission {
    public Long selectedRole;
    public Long selectedModule;
    public String addOrEdit;
    public String optionId;
    public String optionStatus;
    public String addPermission;
    public String editPermission;
    public String deletePermission;
}