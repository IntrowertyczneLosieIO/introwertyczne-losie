package com.agh.introwertycznelosie.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
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
}
