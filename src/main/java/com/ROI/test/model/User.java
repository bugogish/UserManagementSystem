package com.ROI.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Date;

//User {id, firsname, lastname, username, password, email, birthday, isActive, createdTimestamp,
//        lastUpdatedTimestamp, address}

//Just remember that if
//        you're working with Date objects you should do a defensive copy of your Date object
//        (so you should return something like new Date(oldDate.getTime()); instead of plain  return oldDate).
//        This will prevent users from using getter of your Date and modifying its state.

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

    @Embedded
    private Address address;

    public User(String name, String password) {
        this.firstName = name;
        this.password = password;
    }

    User() {}

    @PrePersist
    void createdAt() {
        this.createdTimestamp = this.lastUpdatedTimestamp = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.lastUpdatedTimestamp = new Date();
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreatedTimestamp() {
        return new Date(createdTimestamp.getTime());
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Date getLastUpdatedTimestamp() {
        return new Date(lastUpdatedTimestamp.getTime());
    }

    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }
}

