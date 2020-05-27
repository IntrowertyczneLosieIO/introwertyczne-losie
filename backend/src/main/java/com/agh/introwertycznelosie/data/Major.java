package com.agh.introwertycznelosie.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Major {

    public Major() {

    }


    @ManyToOne
    private Recruitment recruitment;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Faculty faculty;

    @OneToMany(mappedBy = "major")
    private List<Exam> exams = new ArrayList<>();

    private String fullName;
    private String shortName;
    private ModeOfStudy mode;
    private int numberOfPlaces;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_person_1", referencedColumnName = "id")
    private Person contactPerson1;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_person_2", referencedColumnName = "id")
    private Person contactPerson2;
    private boolean mixedField;
    private String annotations;

    public Major(Faculty faculty, String fullName, String shortName, ModeOfStudy mode,
                 int numberOfPlaces, Person contactPerson1, Person contactPerson2,
                 boolean mixedField, String annotations, Recruitment recruitment) {
        this.faculty = faculty;
        this.fullName = fullName;
        this.shortName = shortName;
        this.mode = mode;
        this.numberOfPlaces = numberOfPlaces;
        this.contactPerson1 = contactPerson1;
        this.contactPerson2 = contactPerson2;
        this.mixedField = mixedField;
        this.annotations = annotations;
        this.recruitment = recruitment;
    }

    public Recruitment getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public ModeOfStudy getMode() {
        return mode;
    }

    public void setMode(ModeOfStudy mode) {
        this.mode = mode;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public Person getContactPerson1() {
        return contactPerson1;
    }

    public void setContactPerson1(Person contactPerson1) {
        this.contactPerson1 = contactPerson1;
    }

    public Person getContactPerson2() {
        return contactPerson2;
    }

    public void setContactPerson2(Person contactPerson2) {
        this.contactPerson2 = contactPerson2;
    }

    public boolean isMixedField() {
        return mixedField;
    }

    public void setMixedField(boolean mixedField) {
        this.mixedField = mixedField;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}