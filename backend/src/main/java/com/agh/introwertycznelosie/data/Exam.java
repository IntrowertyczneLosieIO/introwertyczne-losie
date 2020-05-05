package com.agh.introwertycznelosie.data;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Exam {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Major major;
    private ModeOfStudy modeOfStudy;
    private Date startDate;
    private Date endDate;
    //TODO Add Recrutation Cycle field after it has been implemented

    public Exam(){}

    public Exam(String name, Major major, ModeOfStudy modeOfStudy, Date startDate, Date endDate) {
        this.name = name;
        this.major = major;
        this.modeOfStudy = modeOfStudy;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public ModeOfStudy getModeOfStudy() {
        return modeOfStudy;
    }

    public void setModeOfStudy(ModeOfStudy modeOfStudy) {
        this.modeOfStudy = modeOfStudy;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
