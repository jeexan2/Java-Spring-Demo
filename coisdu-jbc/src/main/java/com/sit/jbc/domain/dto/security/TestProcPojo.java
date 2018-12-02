package com.sit.jbc.domain.dto.security;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TestProcPojo {
    BigDecimal id;
    String description;
    String name;

    public static List<TestProcPojo> convertToPojo(List<Object[]> o){
        List<TestProcPojo> list = new ArrayList<TestProcPojo>();
        for (Object[] obj : o) {
            TestProcPojo p = new TestProcPojo();
            p.setId((BigDecimal)obj[0]);
            p.setDescription((String)obj[1]);
            p.setName((String)obj[2]);

            list.add(p);
        }
        return list;
    }
}