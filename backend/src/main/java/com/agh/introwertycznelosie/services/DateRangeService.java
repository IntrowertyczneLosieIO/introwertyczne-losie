package com.agh.introwertycznelosie.services;

import com.agh.introwertycznelosie.data.DateRange;
import com.agh.introwertycznelosie.repositories.DateRangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DateRangeService {

    @Autowired
    DateRangeRepository dateRangeRepository;

    public void save(DateRange dateRange) { dateRangeRepository.save(dateRange);}

    public DateRange get(Long id) { return dateRangeRepository.getOne(id);}

    public void delete(Long id) { dateRangeRepository.delete(get(id));}

    public List<DateRange> getAllBetween(LocalDateTime from, LocalDateTime to) {
        return dateRangeRepository.findAllByDateFromAfterAndDateToBefore(from, to);
    }
}
