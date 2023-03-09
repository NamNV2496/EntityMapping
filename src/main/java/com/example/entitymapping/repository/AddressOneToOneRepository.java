package com.example.entitymapping.repository;

import com.example.entitymapping.domain.Address;
import com.example.entitymapping.domain.AddressOneToOne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressOneToOneRepository extends JpaRepository<AddressOneToOne, Long> {
}
