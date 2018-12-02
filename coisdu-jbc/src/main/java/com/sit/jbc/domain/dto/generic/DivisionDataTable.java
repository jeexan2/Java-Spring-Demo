package com.sit.jbc.domain.dto.generic;

import com.sit.jbc.domain.entity.generic.Division;
import com.sun.org.apache.xpath.internal.operations.Div;
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
public class DivisionDataTable {
    Long divisionId;
    BigDecimal rowId;
    String name;
    String shortName;
    public static List<DivisionDataTable> convertToDivisionDataTable(List<Division> objects){
        List<DivisionDataTable> ret = new ArrayList<DivisionDataTable>();
        BigDecimal rowIndex = new BigDecimal(1.0);
        for (Division obj: objects) {
            DivisionDataTable division = new DivisionDataTable();
            division.setRowId(rowIndex);
            rowIndex = rowIndex.add(new BigDecimal(1));
            division.setDivisionId(obj.getDivisionId());
            division.setName(obj.getDivisionName());
            division.setShortName(obj.getShortName());
            ret.add(division);
        }
        return ret;
    }

}
