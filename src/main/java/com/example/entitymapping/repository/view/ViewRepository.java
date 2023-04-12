package com.example.entitymapping.repository.view;

import com.example.entitymapping.domain.views.ViewName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewRepository extends JpaRepository<ViewName, Long> {
    List<ViewName> findByBorn(String born);
}
