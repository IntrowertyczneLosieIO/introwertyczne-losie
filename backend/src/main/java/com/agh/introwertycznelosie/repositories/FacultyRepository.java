package com.agh.introwertycznelosie.repositories;

import com.agh.introwertycznelosie.data.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findByAcronym(String acronym);
    Faculty findByName(String name);
}
