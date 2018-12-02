package com.sit.jbc.domain.dto.security;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 10-Oct-18.
 */
@Getter
@Setter
public class OfficeTable {
    //int rowId;
    BigDecimal rowId;
    BigDecimal secOfficeId;
    String officeName;
    String shortName;
    String districtName;
    String divisionName;
    String thanaName;
    String address;

    public static List<OfficeTable> convertToOfficeTablePojo(List<Object[]> objectList){
        List<OfficeTable> offices = new ArrayList<>();
            //int rowIndex = 0;
            BigDecimal rowIndex = new BigDecimal(1.0);
            for(Object[] obj : objectList){
                OfficeTable off = new OfficeTable();
                off.setSecOfficeId((BigDecimal) obj[0]);
                off.setOfficeName((String) obj[1]);
                off.setShortName((String)obj[2]);
                off.setAddress((String)obj[3]);
                off.setDistrictName((String)obj[4]);
                off.setDivisionName((String)obj[5]);
                off.setThanaName((String)obj[6]);
                off.setRowId(rowIndex);
                rowIndex = rowIndex.add(new BigDecimal(1.0));
                offices.add(off);
            }
        return offices;
    }
}
