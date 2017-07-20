package com.market.service;

import com.market.model.Role;
import com.market.model.User;

import java.util.List;

/**
 * Created by Roy on 16/07/2017.
 */
public interface UserService {
    public User findUserByUsername(String username);
    public void saveUser(User user);

    public List<User> findAllKasir();

}
