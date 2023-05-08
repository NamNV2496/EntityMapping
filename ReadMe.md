```sql
INSERT INTO db_example.people (id, name,address_id) VALUES
	 (1, 'test1',2),
	 (2, 'test2',2);
INSERT INTO db_example.address (add_id,born,street) VALUES
    (3,'HN','Lang'),
    (2,'HN','Cau Giay');


```

![img_12.png](img_12.png)

# @OneToOne

```java

@Table(name = "people")
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "add_id")
//    @PrimaryKeyJoinColumn
    private Address address;
}


@Table(name = "address")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "add_id")
  Long id;
  String street;
  String born;
}
```
![img_6.png](img_6.png)

## Call OneToOne from people

because in database we have address_id. so we can redirect to any address

Example: people 1 has address 1 or 2 or 3 or 4

    GET http://localhost:8080/oneToOne

Result:

```json
[
    {
        "id": 1,
        "name": "test1",
        "address": {
          "id": 2,
          "street": "Cau Giay",
          "born": "HN"
        }
    },
    {
        "id": 2,
        "name": "test2",
        "address": {
            "id": 2,
            "street": "Cau Giay",
            "born": "HN"
        }
    }
]
```
## Call OneToOne from address
because address doesn't have people_id column. So it will map with address by add_id

It means address id 1 will map with people id 1.

It cannot redirect to another id. it means if people doesn't have id 1 so address id 1 will have data in people property is null

```java
@Table(name = "address")
public class AddressOneToOne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "add_id")
    Long id;
    String street;
    String born;

    @OneToOne
    private People people;
}
@Table(name = "people")
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Column(name = "address_id")
    Long addresses;
}
```

    GET http://localhost:8080/oneToOne2

### Result:

```json
[
  {
    "id": 2,
    "street": "Cau Giay",
    "born": "HN",
    "people": {
      "id": 2, // mapping id 2 of address with id 2 of people, although in people size: people id 2 map with address id 1
      "name": "test2",
      "addresses": 1
    }
  },
  {
    "id": 3,
    "street": "Lang",
    "born": "HN",
    "people": null
  }
]
```

## How to resolve it

create an object in people with @OneToOne relationship with address
```java
@Table(name = "people")
public class PeopleOneToOne2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "add_id")
    private Address addressOne;
}
```

Use @OneToOne mapper to link from address corresponding with object `addressOne` in people

```java
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
```
Request:

    GET http://localhost:8080/oneToOneWay2

### Result:

```json
[
  {
    "id": 1,
    "street": "TAN binh",
    "born": "HCM",
    "people": {
      "id": 2,
      "name": "test2",
      "addressOne": {
        "id": 1,
        "street": "TAN binh",
        "born": "HCM"
      }
    }
  },
  {
    "id": 2,
    "street": "Cau Giay",
    "born": "HN",
    "people": {
      "id": 1,
      "name": "test1",
      "addressOne": {
        "id": 2,
        "street": "Cau Giay",
        "born": "HN"
      }
    }
  },
  {
    "id": 3,
    "street": "Dong Da",
    "born": "HN",
    "people": {
      "id": 3,
      "name": "test3",
      "addressOne": {
        "id": 3,
        "street": "Dong Da",
        "born": "HN"
      }
    }
  }
]
```

# @OneToMany


```sql
INSERT INTO db_example.people (id, name,address_id) VALUES
	 (1, 'test1',2),
	 (2, 'test2',2);
INSERT INTO db_example.address (add_id,born,street) VALUES
    (3,'HN','Lang'),
    (2,'HN','Cau Giay');


```
![img_7.png](img_7.png)

## => Vậy nên ta sẽ đứng từ phía `address` để gọi

![img_4.png](img_4.png)


only need define @OneToMany in 1 side and config mapped with reference_column in n side

    // mapping with column address_id in people table
    @OneToMany(mappedBy = "addresses")
    private List<PeopleOneToMany> people;

```java

@Table(name = "address")
public class AddressOneToMany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "add_id")
    Long id;
    String street;
    String born;
    // mapping with column address_id in people table
    @OneToMany(mappedBy = "addresses")
    private List<PeopleOneToMany> people;
}
@Table(name = "people")
public class PeopleOneToMany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    // this is column in people table
    @Column(name = "address_id")
    Long addresses;

}
```
## Request

    http://localhost:8080/oneToMany

```json
[
    {
        "id": 2,
        "street": "Cau Giay",
        "born": "HN",
        "people": [
            {
                "id": 1,
                "name": "test1",
                "addresses": 2
            },
            {
                "id": 2,
                "name": "test2",
                "addresses": 2
            }
        ]
    },
    {
        "id": 3,
        "street": "Lang",
        "born": "HN",
        "people": []
    }
]
```

# @ManyToOne

```sql
INSERT INTO db_example.people (id, name,address_id) VALUES
	 (1, 'test1',2),
	 (2, 'test2',2);
INSERT INTO db_example.address (add_id,born,street) VALUES
    (3,'HN','Lang'),
    (2,'HN','Cau Giay');


```
![img_8.png](img_8.png)

## => Vậy nên ta sẽ đứng từ phía `people` để gọi

![img_5.png](img_5.png)

only need define in n side

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "add_id")
    Address addresses;

```java
@Table(name = "people")
public class PeopleManyToOne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "add_id")
    Address addresses;
}

@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "add_id")
    Long id;
    String street;
    String born;
}

```

## Request

    http://localhost:8080/manyToOne

```json
[
    {
        "id": 1,
        "name": "test1",
        "addresses": {
            "id": 2,
            "street": "Cau Giay",
            "born": "HN"
        }
    },
    {
        "id": 2,
        "name": "test2",
        "addresses": {
            "id": 2,
            "street": "Cau Giay",
            "born": "HN"
        }
    }
]
```


# With report we can create a Views to collage information from many table

```sql
    CREATE VIEW view_name AS
    SELECT columns
    FROM tables
    [WHERE conditions];
Example:

    CREATE VIEW view_name AS
    SELECT pp.id, pp.name, ad.born, ad.street
    FROM people pp left join address ad on pp.address_id = ad.add_id 
```
  
### Create entity normally

![img_9.png](img_9.png)

### And create a repository and access with method name normally

![img_10.png](img_10.png)

## The result

![img_11.png](img_11.png)

