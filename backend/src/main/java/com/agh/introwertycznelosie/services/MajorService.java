package com.agh.introwertycznelosie.services;

import com.agh.introwertycznelosie.data.Major;
import com.agh.introwertycznelosie.repositories.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorService {

    @Autowired
    MajorRepository majorRepository;

    public long save(Major major) {
        return majorRepository.save(major).getId();

    }

    public Major get(Long id) {
        return majorRepository.getOne(id);
    }

    public void delete(Long id) {
        majorRepository.deleteById(id);
    }

    public Major findByFullName(String fullname) {
        return majorRepository.findByFullName(fullname);
    }

    public void deleteIfExists(Long id)
    {
        try
        {
            delete(id);
        }
        catch (NullPointerException ex)
        {

        }
    }
}
