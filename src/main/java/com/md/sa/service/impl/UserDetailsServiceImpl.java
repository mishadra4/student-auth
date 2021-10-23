package com.md.sa.service.impl;

import com.md.sa.dao.UserDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDetailsDao userDetailsDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsDao.getUser(username);
    }

    public void setUserDetailsDao(UserDetailsDao userDetailsDao) {
        this.userDetailsDao = userDetailsDao;
    }
}
