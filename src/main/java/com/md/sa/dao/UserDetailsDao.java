package com.md.sa.dao;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsDao {
    UserDetails getUser(String username);
}
