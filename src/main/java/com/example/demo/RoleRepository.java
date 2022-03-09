package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<TestObject, Long> {
    TestObject findById(long id);

    List<TestObject> findAllByIdIn(List<Long> list);
    List<TestObject> findAllByNameAndIdIn(String name,List<Long> list);
}