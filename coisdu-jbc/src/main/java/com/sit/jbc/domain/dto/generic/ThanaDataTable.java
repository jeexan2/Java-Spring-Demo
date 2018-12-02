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
public class ThanaDataTable {
    BigDecimal thanaId;
    BigDecimal rowId;
    String name;
    String shortName;
    String districtName;
    String divisionName;

    public static List<ThanaDataTable> convertToThanaDataTable(List<Object[]> objects){
        List<ThanaDataTable> ret = new ArrayList<ThanaDataTable>();
        BigDecimal rowIndex = new BigDecimal(1.0);

        for(Object[] obj : objects){
            ThanaDataTable thana = new ThanaDataTable();
            thana.setRowId(rowIndex);
            rowIndex = rowIndex.add(new BigDecimal(1.0));
            thana.setThanaId((BigDecimal) obj[0]);
            thana.setName((String)obj[1]);
            thana.setShortName((String)obj[2]);
            thana.setDistrictName((String)obj[3]);
            thana.setDivisionName((String)obj[4]);
            ret.add(thana);
        }
        return ret;
    }
}
