package com.spring.elevator.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.servlet.View;


/**
 * Created by AlexJIANG on 12/18/15.
 */
public class TestJson {


    private String name;
    private int age;
    public TestJson(String name,int age){
        this.name=name;
        this.age=age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
