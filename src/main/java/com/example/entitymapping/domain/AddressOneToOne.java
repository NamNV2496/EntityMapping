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
public class AddressOneToOne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "add_id")
    Long id;
    String street;
    String born;

//    because address doesn't have people_id column. So it will map with address by add_id
//    it means address id 1 will map with people id 1.
//    cannot redirect to another id. it means if people doesn't have id 1 so address id 1 will have data in people property is null
//    [
//    {
//        "id": 1,
//        "name": "test1",
//        "address": null
//    },
//    {
//        "id": 2,
//        "name": "test2",
//        "address": {
//            "id": 2,
//            "street": "Cau Giay",
//            "born": "HN"
//        }
//    }
//  ]
    @OneToOne
//    @PrimaryKeyJoinColumn
    private People people;
}
