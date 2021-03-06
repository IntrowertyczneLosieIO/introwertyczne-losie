package com.agh.introwertycznelosie.data;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Faculty {

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Recruitment recruitment;

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String acronym;

    public Faculty(){}

    public Faculty(String name, String acronym, Recruitment recruitment){
        this.name = name;
        this.acronym = acronym;
        this.recruitment = recruitment;
    }

    public void setRecruitment(Recruitment recruitment) { this.recruitment = recruitment; }

    public Recruitment getRecruitment() { return recruitment; }

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

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", acronym='" + acronym + '\'' +
                ", recruitment=" + recruitment + '\'' +
                '}';
    }
}