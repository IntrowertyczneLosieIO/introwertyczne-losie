package com.agh.introwertycznelosie.repositories;

import com.agh.introwertycznelosie.data.RecruitmentCycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitmentCycleRepository extends JpaRepository<RecruitmentCycle, Long> {
    List<RecruitmentCycle> findAll();
}
