package com.agh.introwertycznelosie.data;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Faculty {

    @ManyToMany
    private List<Recruitment> recruitments;

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String acronym;

    public Faculty(){}

    public Faculty(String name, String acronym, List<Recruitment> recruitments){
        this.name = name;
        this.acronym = acronym;
        this.recruitments = recruitments;
    }

    public void setRecruitments(List<Recruitment> recruitments) { recruitments = recruitments; }

    public List<Recruitment> getRecruitments() { return recruitments; }

    public void addRecruitment(Recruitment recruitment) {
        recruitments.add(recruitment);}

    public Long getId(){
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

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }
}