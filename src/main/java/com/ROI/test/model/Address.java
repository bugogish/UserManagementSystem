package com.ROI.test.model;

//        Address is {zip, country, city, district, street}

public class Address {
    private Integer zip;

    private String country;

    private String city;

    private String district;

    private String street;

    @Override
    public String toString() {
        return "Address [zip=" + zip.toString() + ", country=" + country + ", city=" + city +
                ", district=" + district + ", street=" + street + "]";
    }
}
