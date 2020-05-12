package com.agh.introwertycznelosie.repositories;

import com.agh.introwertycznelosie.data.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    List<Recruitment> findAll();
}
