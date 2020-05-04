package com.agh.introwertycznelosie.repositories;

import com.agh.introwertycznelosie.data.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findTop3ByOrderByIdDesc();
    List<Exam> findAllByOrderByIdDesc();
}
