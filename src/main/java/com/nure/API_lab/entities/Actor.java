package com.nure.API_lab.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @Column(length=75, nullable = false)
    public  String firstName;
    @Column(length=75, nullable = false)
    public  String lastName;
    @Column(length=75, nullable = false)
    public String midName;
    @ManyToMany
    public List<Category> categoryList;
    @Column(nullable = false, columnDefinition = "Date")
    public Date dateHired;
    @Column(columnDefinition = "Date")
    public Date dateFired;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Date getDateHired() {
        return dateHired;
    }

    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }

    public Date getDateFired() {
        return dateFired;
    }

    public void setDateFired(Date dateFired) {
        this.dateFired = dateFired;
    }
}
