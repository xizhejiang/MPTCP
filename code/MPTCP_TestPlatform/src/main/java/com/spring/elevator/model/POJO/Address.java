package com.spring.elevator.model.POJO;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by AlexJIANG on 12/24/15.
 */
@Entity
@Table(name="Address")
public class Address implements Serializable {
    private static final long serialVersionUID = 4888697613044851675L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer address_id;
    private String Street;
    private String city;

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

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
