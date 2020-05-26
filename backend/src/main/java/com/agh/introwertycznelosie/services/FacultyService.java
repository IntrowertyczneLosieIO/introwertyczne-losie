package com.agh.introwertycznelosie.services;

import com.agh.introwertycznelosie.data.Faculty;
import com.agh.introwertycznelosie.repositories.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    @Autowired
    FacultyRepository facultyRepository;

    public Faculty save(Faculty faculty){
        return facultyRepository.save(faculty);
    }

    public Faculty findByAcronym(String acronym) { return facultyRepository.findByAcronym(acronym); }

    public List<Faculty> get() { return facultyRepository.findTop3ByOrderByIdDesc(); }

    public Faculty get(Long id){
        return facultyRepository.getOne(id);
    }

    public void delete(Long id){ facultyRepository.deleteById(id); }

    public void delete(Faculty faculty){ facultyRepository.delete(faculty); }

}
