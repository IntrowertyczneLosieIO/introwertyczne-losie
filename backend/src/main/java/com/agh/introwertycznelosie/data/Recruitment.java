package com.agh.introwertycznelosie.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

@Entity
public class Recruitment {

    @Id
    @GeneratedValue
    private Long id;
    private String acronym;
    private RecruitmentStatus recruitmentStatus;
    private int year;
    private Semester semester;

    public Recruitment() {
    }

    public Recruitment(String acronym, int year, Semester semester) {
        this.acronym = acronym;
        this.recruitmentStatus = RecruitmentStatus.in_preparation;
        setYear(year);
        this.semester = semester;
    }

    public boolean isEditable() {
        return recruitmentStatus == RecruitmentStatus.in_preparation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public void setRecruitmentStatus(RecruitmentStatus recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if(year < getCurrentYear()) {
            throw new IllegalArgumentException("Set year cannot be earlier than current");
        }
        this.year = year;
    }

    private int getCurrentYear() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        return calendar.get(Calendar.YEAR);
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}
