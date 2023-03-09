package com.example.entitymapping.repository;

import com.example.entitymapping.domain.PeopleManyToOne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleManyToOneRepository extends JpaRepository<PeopleManyToOne, Long> {
}
