package com.agh.introwertycznelosie.services;

import com.agh.introwertycznelosie.data.Recruitment;
import com.agh.introwertycznelosie.data.SortByYearAndSemesterDesc;
import com.agh.introwertycznelosie.repositories.RecruitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RecruitmentService {

    @Autowired
    RecruitmentRepository recruitmentRepository;

    public Recruitment save(Recruitment recruitment) { return  recruitmentRepository.save(recruitment);}

    public List<Recruitment> get() {
        List<Recruitment> recruitments = recruitmentRepository.findAll();
        Collections.sort(recruitments, new SortByYearAndSemesterDesc());
        return recruitments;
    }

    public Recruitment get(Long id) { return recruitmentRepository.getOne(id);}

    public void delete(Long id) { recruitmentRepository.deleteById(id);}

    public void delete(Recruitment recruitment) { recruitmentRepository.delete(recruitment);}

}
