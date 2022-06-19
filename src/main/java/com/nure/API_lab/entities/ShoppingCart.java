package com.nure.API_lab.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ShoppingCart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @ManyToOne(optional = false)
    public EventOrder eventOrder;
    @ManyToOne(optional = false)
    public Scenario scenario;
    @ManyToOne
    public Actor actor;
    @ManyToOne
    public Costume costume;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventOrder getEventOrder() {
        return eventOrder;
    }

    public void setEventOrder(EventOrder eventOrder) {
        this.eventOrder = eventOrder;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }


    public Costume getCostume() {
        return costume;
    }

    public void setCostume(Costume costume) {
        this.costume = costume;
    }
}
