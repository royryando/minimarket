package com.market.dao;

import com.market.model.Role;
import com.market.model.User;
import com.market.repository.UserRepository;
import com.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by Roy on 19/07/2017.
 */
@Service
public class UserDao implements UserService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManagerFactory emf;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public User findUserByUsername(String username) {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from User where username='" + username + "'", User.class).getSingleResult();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Enkripsi password

        userRepository.save(user);

        //User user1 = findUserByUsername(user.getUsername());

        //EntityManager em = emf.createEntityManager();
        //em.createNativeQuery("insert into user_role(`user_id`, `role_id`) values(" + user1.getId() + ",2)").executeUpdate();
    }

    @Override
    public List<User> findAllKasir() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from User where User.Roles.role='ROLE_KASIR'", User.class).getResultList();
    }
}
