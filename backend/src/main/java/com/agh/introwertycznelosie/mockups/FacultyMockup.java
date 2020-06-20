package com.agh.introwertycznelosie.mockups;

import com.agh.introwertycznelosie.data.Faculty;
import com.agh.introwertycznelosie.data.Recruitment;
import com.agh.introwertycznelosie.services.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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
    private Long recruitmentId;

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public Long getRecruitmentIds() { return recruitmentId; }


    public FacultyMockup(Faculty faculty) {
        id = faculty.getId();
        name = faculty.getName();
        acronym = faculty.getAcronym();
        recruitmentId = faculty.getRecruitment().getId();
    }

    public Faculty mockToFaculty(RecruitmentService recruitmentService){
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setAcronym(acronym);
        faculty.setRecruitment(recruitmentService.get(id));
        return faculty;
    }

}
