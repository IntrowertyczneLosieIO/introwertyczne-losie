package com.agh.introwertycznelosie.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Major {

    @Id
    @GeneratedValue
    private Long id;

    private Faculty faculty;
    private String fullName;
    private String shortName;
    private ModeOfStudy mode;
    private int numberOfPlaces;

    // TODO - klasa zamiast stringa, ale klasy jeszcze nie ma
    private String contactPerson1;
    private String contactPerson2;

    private boolean mixedField;

    public Major(Faculty f, String fn, String sn, ModeOfStudy m, int numberOfPlaces, String cp1, String cp2, boolean mf, String a) {
        this.faculty = f;
        this.fullName = fn;
        this.shortName = sn;
        this.mode = m;
        this.numberOfPlaces = numberOfPlaces;
        this.contactPerson1 = cp1;
        this.contactPerson2 = cp2;
        this.mixedField = mf;
        this.annotations = a;
    }

    private String annotations;

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

    public String getContactPerson1() {
        return contactPerson1;
    }

    public void setContactPerson1(String contactPerson1) {
        this.contactPerson1 = contactPerson1;
    }

    public String getContactPerson2() {
        return contactPerson2;
    }

    public void setContactPerson2(String contactPerson2) {
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

}