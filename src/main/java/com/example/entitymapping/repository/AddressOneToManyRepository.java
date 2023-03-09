package com.example.entitymapping.repository;

import com.example.entitymapping.domain.Address;
import com.example.entitymapping.domain.AddressOneToMany;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AddressOneToManyRepository extends JpaRepository<AddressOneToMany, Long>, JpaSpecificationExecutor<AddressOneToMany> {
}
