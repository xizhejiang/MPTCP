package com.spring.elevator.model.POJO.vo;
import com.spring.elevator.model.POJO.Address;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by AlexJIANG on 12/24/15.
 */
public class VUser implements Serializable {

    private String name;
    private int age;
    private VAddress vaddress;
    private String email;
    private String passwd;
//    @DateTimeFormat(pattern = "yyyy/MM/dd")
//    private Date birthday;


    public VAddress getVAddress() {
        return vaddress;
    }

    public void setVAddress(VAddress vaddress) {
        this.vaddress = vaddress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
//
//    public Date getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(Date birthday) {
//        this.birthday = birthday;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
