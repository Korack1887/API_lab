package com.nure.API_lab.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Animator {
    @Id
    @GeneratedValue
    public Long id;
    public String name;

    @Override
    public String toString() {
        return "Animator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animator animator = (Animator) o;
        return Objects.equals(id, animator.id) &&
                Objects.equals(name, animator.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animator() {
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Animator(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
