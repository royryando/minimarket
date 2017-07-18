package com.market.dao;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.market.model.Role;
import com.market.model.User;
import com.market.repository.RoleRepository;
import com.market.repository.UserRepository;
import com.market.service.UserService;

@Service("userService")
public class UserDao implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByUsername(String username){
		return userRepository.findByUsername(username);
	}
	@Override
	public void saveUser(User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
		}
	
}
