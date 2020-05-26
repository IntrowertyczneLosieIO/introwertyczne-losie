package com.agh.introwertycznelosie.mockups;

import com.agh.introwertycznelosie.data.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FacultyMockup {

    @Autowired
    public FacultyMockup(){}

    public Long getId() {
        return id;
    }

    private Long id;
    private String name;
    private String acronym;

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }


    public FacultyMockup(Faculty faculty) {
        id = faculty.getId();
        name = faculty.getName();
        acronym = faculty.getAcronym();
    }

    public Faculty mockToFaculty(){
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setAcronym(acronym);
        return faculty;
    }

}
