package com.sit.jbc.domain.dto.generic;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 04-Nov-18.
 */
@Getter
@Setter
public class DistrictDataTable {
    BigDecimal districtId;
    BigDecimal rowId;
     String name;
     String shortName;
     String divisionName;

    public static List<DistrictDataTable> convertToDistrictDataTable(List<Object[]> objects){
        List<DistrictDataTable> ret = new ArrayList<DistrictDataTable>();
        BigDecimal rowIndex = new BigDecimal(1.0);
        for(Object[] obj : objects){
            DistrictDataTable district = new DistrictDataTable();
            district.setRowId(rowIndex);
            rowIndex = rowIndex.add(new BigDecimal(1.0));
            district.setDistrictId((BigDecimal) obj[0]);
            district.setName((String)obj[1]);
            district.setShortName((String)obj[2]);
            district.setDivisionName((String)obj[3]);
            ret.add(district);
        }
        return ret;
    }

}
