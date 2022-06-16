package com.nure.API_lab.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Scenario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @Column(length=100, nullable = false, unique = true)
    public String name;
    @Column(length=1000, nullable = false)
    public String description;
    @Column(nullable = false, columnDefinition="Decimal(7,2)")
    public double price;
    @ManyToMany
    public List<Costume> costumes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Costume> getCostumes() {
        return costumes;
    }

    public void setCostumes(List<Costume> costumes) {
        this.costumes = costumes;
    }
}
