package com.agh.introwertycznelosie.services;

import com.agh.introwertycznelosie.data.Subexam;
import com.agh.introwertycznelosie.repositories.SubexamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubexamService {

    @Autowired
    SubexamRepository subexamRepository;

    public Subexam save(Subexam subexam){
        return subexamRepository.save(subexam);
    }

    public Subexam get(Long id){
        return subexamRepository.getOne(id);
    }

    public void delete(Subexam subexam){ subexamRepository.delete(subexam); }

    public void delete(Long id){ subexamRepository.deleteById(id); }

}
