package com.example.entitymapping.repository;

import com.example.entitymapping.domain.PeopleOneToOne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<PeopleOneToOne, Long> {
}
