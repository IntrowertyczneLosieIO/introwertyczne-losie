package main.java.com.agh.introwertycznelosie.services;

import main.java.com.agh.introwertycznelosie.data.User;
import main.java.com.agh.introwertycznelosie.repositories.RoleRepository;
import main.java.com.agh.introwertycznelosie.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
}
