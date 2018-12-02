package com.sit.jbc.domain.dto.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter


public class AccessPermission {
    Long addPermit;
    Long editPermit;
    Long deletePermit;

    public AccessPermission(Long addPermit, Long editPermit, Long deletePermit){
        this.addPermit = addPermit;
        this.editPermit = editPermit;
        this.deletePermit = deletePermit;
    }
}