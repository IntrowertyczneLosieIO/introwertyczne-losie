package com.agh.introwertycznelosie.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Exam {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Major major;
    private Date startDate;
    private Date endDate;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private RecruitmentCycle recruitmentCycle;

    public Exam(){}

    public Exam(String name, Major major, Date startDate, Date endDate, RecruitmentCycle recruitmentCycle) {
        this.name = name;
        this.major = major;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruitmentCycle = recruitmentCycle;
    }

    public void setRecruitmentCycle(RecruitmentCycle recruitmentCycle) {
        this.recruitmentCycle = recruitmentCycle;
    }

    public RecruitmentCycle getRecruitmentCycle() { return this.recruitmentCycle; }

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
