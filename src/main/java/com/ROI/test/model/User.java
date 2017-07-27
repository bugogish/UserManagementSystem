package com.ROI.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.sql.Date;

//User {id, firsname, lastname, username, password, email, birthday, isActive, createdTimestamp,
//        lastUpdatedTimestamp, address}

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    @JsonIgnore
    private String password;

    @Email
    private String email; //EMAIL??? hybernate

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedTimestamp;

    //Can we do it as a separate class?
    private Address address;

    User() {
    }
}

