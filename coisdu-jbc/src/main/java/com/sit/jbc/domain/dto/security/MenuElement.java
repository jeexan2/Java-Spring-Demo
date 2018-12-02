package com.sit.jbc.domain.dto.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuElement {
    int menuLevel;
    Long menuId;
    String menuName;
    String menuAccessUrl;
}