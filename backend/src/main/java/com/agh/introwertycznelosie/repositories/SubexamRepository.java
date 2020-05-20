package com.agh.introwertycznelosie.repositories;

import com.agh.introwertycznelosie.data.Subexam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SubexamRepository extends JpaRepository<Subexam, Long> {
    List<Subexam> findTop3ByOrderByIdDesc();

    List<Subexam> getByExam_Id(Long id);
}
