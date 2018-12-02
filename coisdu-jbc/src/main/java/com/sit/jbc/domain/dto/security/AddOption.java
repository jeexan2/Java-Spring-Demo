package com.sit.jbc.domain.dto.security;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Geetanjali Oishe on 10/3/2018.
 */
@Getter
@Setter
public class AddOption {
    public Long id;
    public String selectOption;
    public String name;
    public String displayName;
    public String accessUrl;
    public String active;
    public Long selectedModule;
    public Long selectedSubModule;
}
