package com.phonebook.dao;

import com.phonebook.entity.User;

import java.util.List;

public class UserWrapper {

    private List<User> allUsers;

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }
}
