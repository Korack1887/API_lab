package com.nure.API_lab.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Objects;

@Entity
public class Details {
    @Id
    @GeneratedValue
    Long id;
    public String address;
    @ManyToOne
    public User user;

    public Details() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Details details = (Details) o;
        return Objects.equals(id, details.id) &&
                Objects.equals(address, details.address) &&
                Objects.equals(user, details.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, user);
    }

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", user=" + user +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
