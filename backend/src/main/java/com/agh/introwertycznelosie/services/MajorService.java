package com.agh.introwertycznelosie.services;

import com.agh.introwertycznelosie.data.Major;
import com.agh.introwertycznelosie.repositories.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService {

    @Autowired
    MajorRepository majorRepository;

    public Major save(Major major) {
        return majorRepository.save(major);
    }

    public List<Major> get() {
        return majorRepository.findTop3ByOrderByIdDesc();
    }

    public Major get(Long id) {
        return majorRepository.getOne(id);
    }

    public void delete(Long id) {
        majorRepository.deleteById(id);
    }

    public void delete(Major major) { majorRepository.delete(major); }

    public Major findByFullName(String fullname) {
        return majorRepository.findByFullName(fullname);
    }

    public List<Major> getAll() {
        return majorRepository.findAllByOrderByIdDesc();
    }
}
