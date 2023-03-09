package com.example.entitymapping.repository;

import com.example.entitymapping.domain.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Long> {
}
