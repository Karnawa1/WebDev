package com.sdc.webdev.dao;

import com.sdc.webdev.model.User;

import java.util.List;

public interface UserDAO {
    void registerUser(User user);
    User getUserByUsername(String username);
}
