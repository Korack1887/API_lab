package com.nure.API_lab.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class EventOrder {
    @Id
    @GeneratedValue
    public Long id;
    @Enumerated(EnumType.STRING)
    public Status status;
    @OneToOne
    public Details details;
    @ManyToOne
    public Category category;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public Date date;

    @Override
    public String toString() {
        return "EventOrder{" +
                "id=" + id +
                ", status=" + status +
                ", details=" + details +
                ", category=" + category +
                ", date=" + date +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EventOrder() {
    }
}
