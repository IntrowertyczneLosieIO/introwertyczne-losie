package com.agh.introwertycznelosie.repositories;

import com.agh.introwertycznelosie.data.Major;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MajorRepository extends JpaRepository<Major, Long> {
    Major findByFullName(String fullname);
    List<Major> findTop3ByOrderByIdDesc();
}
