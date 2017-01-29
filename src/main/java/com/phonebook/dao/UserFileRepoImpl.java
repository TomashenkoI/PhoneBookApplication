package com.phonebook.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonebook.entity.User;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Configuration
public class UserFileRepoImpl implements UserFileRepo{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void save(User user){


        UserWrapper userWrapper = new UserWrapper();

        List<User> users = userWrapper.getAllUsers();

        users.add(user);

        try {
            mapper.writeValue(new File("D:\\users.json"), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {

        UserWrapper userWrapper = new UserWrapper();

        try {
            userWrapper = mapper.readValue(new File("D:\\users.json"), UserWrapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userWrapper.getAllUsers();
    }
}
