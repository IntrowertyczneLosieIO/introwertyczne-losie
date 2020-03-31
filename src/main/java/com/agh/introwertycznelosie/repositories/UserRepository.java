package com.agh.introwertycznelosie.repositories;

import com.agh.introwertycznelosie.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
