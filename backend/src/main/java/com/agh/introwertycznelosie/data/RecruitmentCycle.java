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

    private int iteration;

    public RecruitmentCycle(Recruitment recruitment, int iteration){
        this.recruitment = recruitment;
        this.exams = new ArrayList<Exam>();
        this.iteration = iteration;
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

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    @Override
    public String toString() {
        return "RecruitmentCycle{" +
                "id=" + id +
                ", recruitment=" + recruitment +
                ", iteration=" + iteration +
                '}';
    }
}
