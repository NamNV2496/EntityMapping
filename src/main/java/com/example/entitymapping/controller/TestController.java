package com.example.entitymapping.controller;

import com.example.entitymapping.domain.*;
import com.example.entitymapping.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final PeopleRepository peopleRepository;
    private final AddressRepository addressRepository;
    private final AddressOneToManyRepository addressOneToManyRepository;
    private final PeopleManyToOneRepository peopleManyToOneRepository;
    private final AddressOneToOneRepository addressOneToOneRepository;

    @GetMapping("/createData")
    public void createData() {
        peopleRepository.save(PeopleOneToOne.builder().name("test1").build());
        addressRepository.save(Address.builder().street("Lang").born("HN").build());
        addressRepository.save(Address.builder().street("Cau Giay").born("HN").build());
    }

    @GetMapping("/oneToOne")
    public List<PeopleOneToOne> oneToOne() {
        return peopleRepository.findAll();
    }

    @GetMapping("/oneToOne2")
    public List<AddressOneToOne> oneToOne2() {
        return addressOneToOneRepository.findAll();
    }

    @GetMapping("/oneToMany")
    public List<AddressOneToMany> oneToMany() {
        return addressOneToManyRepository.findAll();
//        way 2: use specification
//        return addressOneToManyRepository.findAll(AddressSpecification.fetchItems());
    }

    @GetMapping("/manyToOne")
    public List<PeopleManyToOne> manyToOne() {
        return peopleManyToOneRepository.findAll();
    }
}
