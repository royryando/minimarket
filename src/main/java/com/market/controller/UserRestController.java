package com.market.controller;

import com.market.model.User;
import com.market.repository.UserRepository;
import com.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Roy on 19/07/2017.
 */
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private UserRepository userRepository;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = {"/list", "/list/"}, method = RequestMethod.GET)
    public Iterable<User> listUser(){
        return userRepository.findAll();
    }

    @RequestMapping(value = {"/createupdate", "/createupdate/"}, method = RequestMethod.POST)
    public Iterable<User> createupdateUser(@RequestBody User user){
        userService.saveUser(user);
        return userRepository.findAll();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Iterable<User> delete(@PathVariable Integer id){
        userRepository.delete(id);
        return userRepository.findAll();
    }

    @RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
    public User searchUser(@PathVariable Integer id){
        return userRepository.findOne(id);
    }

    @RequestMapping(value = {"/password", "/password/"}, method = RequestMethod.GET)
    @ResponseBody
    public void gantiPassword(Integer id, String password){
        userService.gantiPassword(id, password);
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public void update(Integer id, String name, String username){
        userService.ubahData(id, username, name);
    }
}