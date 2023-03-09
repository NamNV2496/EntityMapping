package com.example.entitymapping.controller;

import com.example.entitymapping.domain.Address;
import com.example.entitymapping.domain.AddressOneToMany;
import com.example.entitymapping.domain.People;
import com.example.entitymapping.domain.PeopleManyToOne;
import com.example.entitymapping.repository.AddressOneToManyRepository;
import com.example.entitymapping.repository.AddressRepository;
import com.example.entitymapping.repository.PeopleManyToOneRepository;
import com.example.entitymapping.repository.PeopleRepository;
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

    @GetMapping("/createData")
    public void createData() {
        peopleRepository.save(People.builder().name("test1").build());
        addressRepository.save(Address.builder().street("Lang").born("HN").build());
        addressRepository.save(Address.builder().street("Cau Giay").born("HN").build());
    }

    @GetMapping("/oneToOne")
    public People oneToOne() {
        return peopleRepository.findById(1L).get();
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
