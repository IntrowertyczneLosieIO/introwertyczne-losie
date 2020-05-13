package com.agh.introwertycznelosie.repositories;

import com.agh.introwertycznelosie.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
