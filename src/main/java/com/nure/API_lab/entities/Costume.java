package com.nure.API_lab.entities;

import javax.persistence.*;

@Entity
public class Costume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    @Column(length = 50, nullable = false, unique = true)
    public String name;
    @Column(nullable = false)
    public int amount;
    @Column(length = 500, nullable = false)
    public String description;
    @Column(nullable = false, columnDefinition="Decimal(7,2)")
    public double price;
}
