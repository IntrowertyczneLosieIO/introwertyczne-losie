package com.agh.introwertycznelosie.mockups;

import com.agh.introwertycznelosie.data.Recruitment;
import com.agh.introwertycznelosie.data.RecruitmentStatus;
import com.agh.introwertycznelosie.data.Semester;

public class RecruitmentMockup {
    public Long getId() {
        return id;
    }

    public String getAcronym() {
        return acronym;
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public int getYear() {
        return year;
    }

    public Semester getSemester() {
        return semester;
    }

    private Long id;
    private String acronym;
    private RecruitmentStatus recruitmentStatus;
    private int year;
    private Semester semester;

    public RecruitmentMockup() {}

    public RecruitmentMockup(Recruitment recruitment) {
        id = recruitment.getId();
        acronym = recruitment.getAcronym();
        recruitmentStatus = recruitment.getRecruitmentStatus();
        year = recruitment.getYear();
        semester = recruitment.getSemester();
    }

    public Recruitment mockupToRecruitment(){
        Recruitment recruitment = new Recruitment();
        recruitment.setRecruitmentStatus(recruitmentStatus);
        recruitment.setYear(year);
        recruitment.setAcronym(acronym);
        recruitment.setSemester(semester);
        return recruitment;
    }



}
