package com.agh.introwertycznelosie.services;

import com.agh.introwertycznelosie.data.Exam;
import com.agh.introwertycznelosie.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

    @Autowired
    ExamRepository examRepository;

    public void save(Exam exam){
        examRepository.save(exam);
    }

    public Exam get(Long id){
        return examRepository.getOne(id);
    }

    public void delete(Long id){
        examRepository.delete(get(id));
    }

}
