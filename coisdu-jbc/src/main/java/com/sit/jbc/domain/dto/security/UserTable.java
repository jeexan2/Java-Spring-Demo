package com.sit.jbc.domain.dto.security;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 23-Oct-18.
 */
@Getter
@Setter
public class UserTable {
    BigDecimal rowId;
    BigDecimal userId;
    String userName;
    String roleName;
    String officeName;
    String officeCategory;
    String officeAddress;
    String officeDivision;
    String officeDistrict;
    String officeThana;
   // Boolean isActive;

    public static List<UserTable> convertToUserTablePojo(List<Object[]> objectList){
        List<UserTable> users = new ArrayList<UserTable>();
        BigDecimal rowIndex = new BigDecimal(1.0);
        for(Object[] obj: objectList){
            UserTable user = new UserTable();
            user.setUserId((BigDecimal) obj[0]);
            user.setUserName((String)obj[1]);
            user.setRoleName((String)obj[2]);
            user.setOfficeName((String)obj[3]);
            user.setOfficeCategory((String)obj[4]);
            user.setOfficeAddress((String)obj[5]);
            user.setOfficeDivision((String)obj[6]);
            user.setOfficeDistrict((String)obj[7]);
            user.setOfficeThana((String)obj[8]);
           // user.setIsActive((BigDecimal)obj[9]);
            user.setRowId(rowIndex);
            rowIndex = rowIndex.add(new BigDecimal(1.0));
            users.add(user);

        }
        return users;
    }
}
