package com.nure.API_lab.entities;

import javax.persistence.*;

@Entity
public class Authorization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @Column(length=50, nullable = false, unique = true)
    public String login;
    @Column(length=50, nullable = false)
    public String password;
    @OneToOne
    public User user;
    @OneToOne
    public Actor actor;
    @ManyToOne(optional = false)
    public  Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
