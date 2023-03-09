package com.example.entitymapping.repository;

import com.example.entitymapping.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
