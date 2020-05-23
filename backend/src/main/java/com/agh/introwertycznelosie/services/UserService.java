package com.agh.introwertycznelosie.services;

import com.agh.introwertycznelosie.data.Role;
import com.agh.introwertycznelosie.data.User;
import com.agh.introwertycznelosie.repositories.RoleRepository;
import com.agh.introwertycznelosie.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    private void postConstruct() {
        Role role = new Role();
        role.setName("basic");
        User admin = new User("admin", bCryptPasswordEncoder.encode("admin"), "admin");
        userRepository.save(admin);
    }

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void changePassword(User user, String password){
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }

    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
}
