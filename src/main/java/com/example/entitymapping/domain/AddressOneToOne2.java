package com.example.entitymapping.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class AddressOneToOne2 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "add_id")
    Long id;
    String street;
    String born;

    @OneToOne(mappedBy = "addressOne")
    private PeopleOneToOne2 people;
}
