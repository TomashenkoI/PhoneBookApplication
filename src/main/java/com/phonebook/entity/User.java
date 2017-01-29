package com.phonebook.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    private String login;

    private String password;

    private String full_name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<Contacts> listOfContacts;

    public User(String login, String password, String full_name, List<Contacts> listOfContacts) {
        this.login = login;
        this.password = password;
        this.full_name = full_name;
        this.listOfContacts = listOfContacts;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", full_name='" + full_name + '\'' +
                ", listOfContacts=" + listOfContacts +
                '}';
    }

    public void setListOfContacts(List<Contacts> listOfContacts) {
        this.listOfContacts = listOfContacts;
    }

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

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public List<Contacts> getListOfContacts() {
        return listOfContacts;
    }
}
