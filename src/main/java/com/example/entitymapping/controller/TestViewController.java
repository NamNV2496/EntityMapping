package com.example.entitymapping.controller;

import com.example.entitymapping.domain.PeopleOneToOne;
import com.example.entitymapping.domain.views.ViewName;
import com.example.entitymapping.repository.PeopleRepository;
import com.example.entitymapping.repository.view.ViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestViewController {

    private final ViewRepository viewRepository;

    @GetMapping("/view")
    public List<ViewName> oneToOne() {
        return viewRepository.findByBorn("HN");
    }
}
