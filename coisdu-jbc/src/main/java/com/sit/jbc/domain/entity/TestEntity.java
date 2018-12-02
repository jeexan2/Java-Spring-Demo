package com.sit.jbc.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by MAMUN on 09-Oct-18.
 */
@NamedNativeQueries({
/*
        @NamedNativeQuery(
                name = "getAllDistrict",
                query = "CALL DISTRICT_GETALL(?)",
                hints=	@javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true"),
                resultClass = District.class
        ),*/
        @NamedNativeQuery(
                name = "findTestEntityByName",
                query = "{CALL GET_TEST_ENTITY(?)}"
                //,
                //hints=	@javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true"),
                //resultClass = TestEntity.class
        )

})
@Getter
@Setter

@Entity
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;




}
