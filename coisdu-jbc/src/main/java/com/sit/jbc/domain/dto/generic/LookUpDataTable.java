package com.sit.jbc.domain.dto.generic;

import com.sit.jbc.domain.entity.generic.Lookup;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 16-Oct-18.
 */
@Getter
@Setter
public class LookUpDataTable {
    Long id;
    BigDecimal rowId;
    String name;
    String displayName;
    String shortName;
    String value;
    String type;

    public static List<LookUpDataTable> convertToLookUpPojo(List<Lookup> objectList){
        List<LookUpDataTable> lookupList = new ArrayList<>();
        BigDecimal rowIndex = new BigDecimal(1.0);
        for(Lookup obj : objectList){
            LookUpDataTable pojo = new LookUpDataTable();
            pojo.setId(obj.getLookupId());
            pojo.setName((String)obj.getName());
            pojo.setDisplayName((String)obj.getDisplayName());
            pojo.setShortName((String)obj.getShortName());
            pojo.setValue((String) obj.getValue());
            pojo.setType(obj.getType());
            pojo.setRowId(rowIndex);
            rowIndex = rowIndex.add(new BigDecimal(1.0));
            lookupList.add(pojo);
        }
        return lookupList;
    }
}
