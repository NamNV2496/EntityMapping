package com.example.entitymapping.repository;

import com.example.entitymapping.domain.AddressOneToOne2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressOneToOneWay2Repository extends JpaRepository<AddressOneToOne2, Long> {
}
