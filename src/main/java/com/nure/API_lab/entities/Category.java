package com.nure.API_lab.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Category {
    @Id
    @GeneratedValue
    public Long id;
    public String name;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    public List<Animator> animators;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    public List<Costume> costumes;
    public float price;
    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", animators=" + animators +
                ", costumes=" + costumes +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Float.compare(category.price, price) == 0 &&
                Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(animators, category.animators) &&
                Objects.equals(costumes, category.costumes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, animators, costumes, price);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAnimators(List<Animator> animators) {
        this.animators = animators;
    }

    public void setCostumes(List<Costume> Costumes) {
        this.costumes = Costumes;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category(Long id, String name, List<Animator> animators, List<Costume> Costumes) {
        this.id = id;
        this.name = name;
        this.animators = animators;
        this.costumes = Costumes;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public List<Animator> getAnimators() {
        return animators;
    }


    public List<Costume> getCostumes() {
        return costumes;
    }

}
