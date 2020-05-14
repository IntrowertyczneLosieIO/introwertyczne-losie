package com.agh.introwertycznelosie.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RecruitmentCycle
{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Recruitment recruitment;

    @OneToMany(mappedBy = "recruitmentCycle")
    private List<Exam> exams;

    public RecruitmentCycle(Recruitment recruitment){
        this.recruitment = recruitment;
        this.exams = new ArrayList<Exam>();
    }

    public RecruitmentCycle(){}

    public Long getId() {
        return id;
    }

    public Recruitment getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
