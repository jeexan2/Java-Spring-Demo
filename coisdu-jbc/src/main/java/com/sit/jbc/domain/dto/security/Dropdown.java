package com.sit.jbc.domain.dto.security;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 24-Oct-18.
 */
@Getter
@Setter
public class Dropdown {
    String name;
    Map<Long,String> dropdownValue;
    public Dropdown(){
        dropdownValue = new HashMap<Long,String >();
    }
    public static Dropdown convertToDropdownList(List<Object[]> objectList,
                                                 String dropdownName){
        Dropdown dropdown = new Dropdown();
        dropdown.setName(dropdownName);
        dropdown.dropdownValue.put((long)-1,"Select "+dropdownName);
        for(Object[] obj : objectList){
           // dropdown.dropdownValue.put((long)obj[0],(String)obj[1]);
        }
        return dropdown;
    }
}

