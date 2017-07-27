package com.ROI.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//User {id, firsname, lastname, username, password, email, birthday, isActive, createdTimestamp,
//        lastUpdatedTimestamp, address}
//        Address is {zip, country, city, district, street}

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;


}
