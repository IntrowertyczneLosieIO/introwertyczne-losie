package com.agh.introwertycznelosie.services;

import com.agh.introwertycznelosie.data.Exam;
import com.agh.introwertycznelosie.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    @Autowired
    ExamRepository examRepository;

    public Exam save(Exam exam){
        return examRepository.save(exam);
    }

    public Exam get(Long id){
        return examRepository.getOne(id);
    }

    public List<Exam> get() { return examRepository.findTop3ByOrderByIdDesc(); }

    public void delete(Long id){ examRepository.deleteById(id); }

    public void delete(Exam exam){ examRepository.delete(exam); }

}
