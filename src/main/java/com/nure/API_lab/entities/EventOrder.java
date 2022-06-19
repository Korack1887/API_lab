package com.nure.API_lab.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
public class EventOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @ManyToOne(optional = false)
    public User user;
    @Column(nullable = false, columnDefinition = "Date")
    public Date date;
    @Column(nullable = false)
    public Time time;
    @Column(nullable = false)
    public String address;
    @ManyToOne(optional = false)
    public Status status;

    public EventOrder() {
        date = new Date(System.currentTimeMillis());
        time = new Time(System.currentTimeMillis());
        address = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
