package com.spring.elevator.model.POJO.vo;

import javax.persistence.*;

/**
 * Created by AlexJIANG on 12/24/15.
 */

public class VAddress {

    private String Street;
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }
}
