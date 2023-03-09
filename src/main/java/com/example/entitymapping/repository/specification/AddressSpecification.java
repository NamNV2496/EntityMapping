package com.example.entitymapping.repository.specification;

import com.example.entitymapping.domain.AddressOneToMany;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;

public class AddressSpecification {
    public static Specification<AddressOneToMany> fetchItems() {
        return (root, query, criteriaBuilder) -> {
            root.fetch("people", JoinType.LEFT);
            return criteriaBuilder.notEqual(root.get("id"), 0L);
        };
    }
}
