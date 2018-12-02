package com.sit.jbc.domain.dto.security;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RolewiseOptionProcPojo {
    Long roleId;
    Long secModuleId;
    String secModuleName;
    Long secSubModuleId;
    String secSubModuleName;
    Long secOptionId;
    String secOptionName;
    String secOptionAccessUrl;
    Long canAdd;
    Long canEdit;
    Long canDelete;

    public static List<RolewiseOptionProcPojo> convertToPojo(List<Object[]> o){
        List<RolewiseOptionProcPojo> list = new ArrayList<RolewiseOptionProcPojo>();
        for (Object[] obj : o) {
            RolewiseOptionProcPojo p = new RolewiseOptionProcPojo();
            p.setRoleId(((BigDecimal)obj[0]).longValue());

            p.setSecModuleId(((BigDecimal)obj[1]).longValue());
            p.setSecModuleName((String)obj[2]);

            p.setSecSubModuleId(((BigDecimal)obj[3]).longValue());
            p.setSecSubModuleName((String)obj[4]);

            p.setSecOptionId(((BigDecimal)obj[5]).longValue());
            p.setSecOptionName((String)obj[6]);
            p.setSecOptionAccessUrl((String)obj[7]);

            BigDecimal canAdd = (BigDecimal)obj[8];
            BigDecimal canEdit = (BigDecimal)obj[9];
            BigDecimal canDelete = (BigDecimal)obj[10];

            p.setCanAdd(canAdd == null  ? 0 : canAdd.longValue());
            p.setCanEdit(canEdit == null ? 0 : canEdit.longValue());
            p.setCanDelete(canDelete == null ? 0 : canDelete.longValue());

            list.add(p);
        }
        return list;
    }

}