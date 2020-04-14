package com.agh.introwertycznelosie.repositories;

import com.agh.introwertycznelosie.data.DateRange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DateRangeRepository extends JpaRepository<DateRange, Long> {
    List<DateRange> findAllByDateFromAfterAndDateToBefore(LocalDateTime from, LocalDateTime to);
}