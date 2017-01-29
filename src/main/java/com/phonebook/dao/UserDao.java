package com.phonebook.dao;

import com.phonebook.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Integer> {
    User findByLogin(String login);
}
