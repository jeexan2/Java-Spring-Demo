package com.sit.jbc.domain.dto.security;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Geetanjali Oishe on 10/22/2018.
 */
@Getter
@Setter
public class TreeElement {

        public TreeElement(String name, Long id, String type, int activeStatus) {
        this.setName(name);
        this.setType(type);
        this.setID(id);
        this.setActiveStatus(activeStatus);
    }
    String Name;
    Long ID;
    String type;
    int activeStatus;

    @Override
    public String toString() {
        return "TreeElement{" +
                "Name='" + Name + '\'' +
                ", ID=" + ID +
                ", type='" + type + '\'' +
                ", activeStatus='" + activeStatus + '\'' +
                '}';
    }
}
