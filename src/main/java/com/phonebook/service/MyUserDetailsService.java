package com.phonebook.service;

import com.phonebook.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.phonebook.entity.User user = userDao.findByLogin(username);

        return buildUserForAuthentication(user);

    }

    private User buildUserForAuthentication(com.phonebook.entity.User user) {
        return new User(user.getLogin(), user.getPassword(),
                true, true, true, true, buildUserAuthority());
    }

    private List<GrantedAuthority> buildUserAuthority() {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
            setAuths.add(new SimpleGrantedAuthority("user"));

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
