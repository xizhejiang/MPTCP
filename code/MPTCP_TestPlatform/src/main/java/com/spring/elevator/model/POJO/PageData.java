package com.spring.elevator.model.POJO;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by AlexJIANG on 7/11/16.
 */
@Entity
@Table(name="PageData")
@NamedQuery(name = "pagedata.findall", query = "select p from PageData p")
public class PageData implements Serializable {

    private static final long serialVersionUID = 8573788736683118578L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer loadTime;


    private String domain;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocal) {
        this.protocol = protocal;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String protocol;
    private String user;
    private Integer totalTimes;
    private Integer counter;

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Integer getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(Integer totalTimes) {
        this.totalTimes = totalTimes;
    }






    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date timestamp;





    public Integer getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Integer loadTime) {
        this.loadTime = loadTime;
    }
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageData pageData = (PageData) o;

        if (loadTime != null ? !loadTime.equals(pageData.loadTime) : pageData.loadTime != null) return false;
        return timestamp != null ? timestamp.equals(pageData.timestamp) : pageData.timestamp == null;

    }

    @Override
    public int hashCode() {
        int result = loadTime != null ? loadTime.hashCode() : 0;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }
}
