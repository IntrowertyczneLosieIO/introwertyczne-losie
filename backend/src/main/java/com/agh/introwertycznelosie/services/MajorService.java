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

    public void save(Major major) {
        majorRepository.save(major);
    }

    public List<Major> get() {
        return majorRepository.findTop2ByOrderByIdDesc();
    }

    public Major get(Long id) {
        return majorRepository.getOne(id);
    }

    public void delete(Long id) {
        majorRepository.delete(get(id));
    }

    public Major findByFullName(String fullname) {
        return majorRepository.findByFullName(fullname);
    }
}
