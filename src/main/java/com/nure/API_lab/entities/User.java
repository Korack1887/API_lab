package com.nure.API_lab.entities;


import javax.persistence.*;
import java.sql.Date;

@Entity
public class User {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    @Column(length=75, nullable = false)
    public String firstName;
    @Column(length=75, nullable = false)
    public String lastName;
    @Column(length=75, nullable = false)
    public  String midName;
    @Column(length=13, nullable = false)
    public String phoneNumber;
    @Column(length=100, nullable = false)
    public String email;
    @Column(nullable = false, columnDefinition = "Date")
    public Date birthday;
}
