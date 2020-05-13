package com.agh.introwertycznelosie.services;

import com.agh.introwertycznelosie.data.RecruitmentCycle;
import com.agh.introwertycznelosie.repositories.RecruitmentCycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RecruitmentCycleService {

    @Autowired
    RecruitmentCycleRepository recruitmentCycleRepository;
    
    public RecruitmentCycle save(RecruitmentCycle recruitmentCycle) { return  recruitmentCycleRepository.save(recruitmentCycle);}

    public List<RecruitmentCycle> get() {
        List<RecruitmentCycle> recruitmentCycles = recruitmentCycleRepository.findAll();
        return recruitmentCycles;
    }

    public RecruitmentCycle get(Long id) { return recruitmentCycleRepository.getOne(id);}

    public void delete(Long id) { recruitmentCycleRepository.deleteById(id);}

    public void delete(RecruitmentCycle recruitmentCycle) { recruitmentCycleRepository.delete(recruitmentCycle);}
}
