package com.sit.jbc.controller.application;

import com.sit.jbc.domain.entity.TestEntity;
import com.sit.jbc.repository.TestEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by MAMUN on 11-Sep-18.
 */
@Controller
public class RootController {

   // @Autowired
    //TestEntityRepository testEntityRepository;

    @GetMapping(value ="/")
    public String getRootPage(){

        return "common/index";    }

    @GetMapping(value ="/test-entity")
    public String getTestEntity(){
       // List<TestEntity> result = testEntityRepository.findTestEntityByName();
       // System.out.println(result);
        return "common/index";    }
}
