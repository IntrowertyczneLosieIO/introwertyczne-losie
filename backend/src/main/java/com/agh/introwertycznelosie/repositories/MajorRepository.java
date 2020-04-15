package com.agh.introwertycznelosie.repositories;

import com.agh.introwertycznelosie.data.Major;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorRepository extends JpaRepository<Major, Long> {
    Major findByFullName(String fullname);
}
