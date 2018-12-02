package com.sit.jbc.repository;

import com.sit.jbc.domain.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by MAMUN on 09-Oct-18.
 */
public interface TestEntityRepository extends JpaRepository<TestEntity ,Long> {

    //List<TestEntity> findTestEntityByName();
}
