package main.java.com.agh.introwertycznelosie.repositories;

import main.java.com.agh.introwertycznelosie.data.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> { }
