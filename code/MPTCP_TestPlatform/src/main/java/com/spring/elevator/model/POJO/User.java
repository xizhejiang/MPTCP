package com.spring.elevator.model.POJO;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by AlexJIANG on 12/24/15.
 */
@Entity
@Table(name = "User")
@NamedQuery(name = "user.finduser", query = "select u from User u where u.name=:name")
public class User implements Serializable {

    private static final long serialVersionUID = -2208939422379530714L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private int age;
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;
    private String email;
    private String passwd;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthday;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (passwd != null ? !passwd.equals(user.passwd) : user.passwd != null) return false;
        return !(birthday != null ? !birthday.equals(user.birthday) : user.birthday != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passwd != null ? passwd.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
