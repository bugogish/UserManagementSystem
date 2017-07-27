package com.ROI.test.model;

//        Address is {zip, country, city, district, street}

import org.hibernate.usertype.UserType;

import javax.persistence.Entity;

public class Address {
    private Integer zip;

    private String country;

    private String city;

    private String district;

    private String street;

//    @Override
    public boolean equals(Object x, Object y) {
        if (x == y) {
            return true;
        }

        if (x == null || y == null) {
            return false;
        }

        if (x.getClass() != y.getClass()) {
            return false;
        }

        Address xx = (Address) x;
        Address yy = (Address) y;
        if (xx.zip.equals(yy.zip) && xx.country.equals(yy.country) && xx.city.equals(yy.city) &&
                xx.district.equals(yy.district) && xx.street.equals(yy.street)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Address other = (Address) obj;
        if (zip.equals(other.zip) && country.equals(other.country) && city.equals(other.city) &&
                district.equals(other.district) && street.equals(other.street)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Address [zip=" + zip.toString() + ", country=" + country + ", city=" + city +
                ", district=" + district + ", street=" + street + "]";
    }
}
