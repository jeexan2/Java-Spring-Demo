package com.sit.jbc.domain.dto.generic;

import com.sit.jbc.domain.entity.generic.Lookup;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Created by User on 16-Oct-18.
 */
@Getter
@Setter
public class LookUpPojo {
    Long id;
    BigDecimal rowId;
    String name;
    String displayName;
    String shortName;
    String value;
    String type;

    public static List<LookUpPojo> convertToLookUpPojo(List<Lookup> objectList){
        List<LookUpPojo> lookupList = new ArrayList<>();
        BigDecimal rowIndex = new BigDecimal(1.0);
        for(Lookup obj : objectList){
            LookUpPojo pojo = new LookUpPojo();
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
