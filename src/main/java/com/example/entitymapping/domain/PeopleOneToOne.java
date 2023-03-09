package com.example.entitymapping.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "people")
public class PeopleOneToOne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

//    because in database we have address_id. so we can redirect to any address
//    Example: people 1 has address 1 or 2 or 3 or 4
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "add_id")
//    @PrimaryKeyJoinColumn
    private Address address;

}
