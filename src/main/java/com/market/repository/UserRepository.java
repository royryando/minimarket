package com.market.repository;

import com.market.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Roy on 19/07/2017.
 */
@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);;
}
