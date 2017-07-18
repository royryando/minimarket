package com.market.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.market.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	User findByUsername(String username);
}
