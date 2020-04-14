package com.agh.introwertycznelosie.repositories;

import com.agh.introwertycznelosie.data.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
