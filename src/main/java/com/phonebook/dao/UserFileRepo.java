package com.phonebook.dao;

import com.phonebook.entity.User;

import java.util.List;

public interface UserFileRepo {

    void save(User user);

    List<User> getAllUsers();

}
