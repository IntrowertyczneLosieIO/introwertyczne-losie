package com.agh.introwertycznelosie.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Recruitment {

    @Id
    @GeneratedValue
    private Long id;
    private String acronym;
    private RecruitmentStatus recruitmentStatus;
    private int year;
    private Semester semester;

    @OneToMany(mappedBy = "recruitment")
    private List<RecruitmentCycle> recruitmentCycles;

    @OneToMany(mappedBy = "recruitment")
    private List<Major> majors;

    /*
    @OneToMany(mappedBy = "recruitment")
    private List<Room> rooms;
    */
    @ManyToMany(mappedBy = "recruitments")
    private List<Faculty> faculties;

    public Recruitment() {
    }

    public Recruitment(String acronym, int year, Semester semester) {
        this.acronym = acronym;
        this.recruitmentStatus = RecruitmentStatus.in_preparation;
        setYear(year);
        this.semester = semester;
        recruitmentCycles = new ArrayList<>();
        faculties = new ArrayList<>();
    }

    public void addFaculty(Faculty faculty) {
        this.faculties.add(faculty);
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

    public List<RecruitmentCycle> getRecruitmentCycles(){
        return this.recruitmentCycles;
    }

    private int getCurrentYear() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        return calendar.get(Calendar.YEAR);
    }

    public List<Major> getMajors() {
        return majors;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public void setRecruitmentCycles(List<RecruitmentCycle> recruitmentCycles) {
        this.recruitmentCycles = recruitmentCycles;
    }

    public void addRecruitmentCycle(RecruitmentCycle recruitmentCycle) { recruitmentCycles.add(recruitmentCycle); }

    public void setMajors(List<Major> majors) {
        this.majors = majors;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    @Override
    public String toString() {
        return "Recruitment{" +
                "acronym='" + acronym + '\'' +
                ", year=" + year +
                ", semester=" + semester +
                '}';
    }
}
