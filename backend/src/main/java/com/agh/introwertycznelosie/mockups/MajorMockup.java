package com.agh.introwertycznelosie.mockups;

import com.agh.introwertycznelosie.data.*;
import com.agh.introwertycznelosie.services.FacultyService;
import com.agh.introwertycznelosie.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MajorMockup {

    @Autowired
    public MajorMockup(){}

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
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

    public Long getId() {
        return id;
    }

    private Long id;
    private String faculty;
    private List<Exam> exams = new ArrayList<>();
    private String fullName;
    private String shortName;
    private ModeOfStudy mode;
    private int numberOfPlaces;
//    @JsonProperty("contactPerson1")
    private Person contactPerson1;
//    @JsonProperty("contactPerson2")
    private Person contactPerson2;
    private boolean mixedField;
    private String annotations;

    public MajorMockup(Major major)
    {
        id = major.getId();
        faculty = major.getFaculty().getAcronym();
        shortName = major.getShortName();
        fullName = major.getFullName();
        mode = major.getMode();
        numberOfPlaces = major.getNumberOfPlaces();
        contactPerson1 = major.getContactPerson1();
        contactPerson2 = major.getContactPerson2();
        mixedField = major.isMixedField();
        annotations = major.getAnnotations();
    }
    public Major mockToMajor(PersonService personService, FacultyService facultyService)
    {
        Major major = new Major();
        Faculty faculty = facultyService.findByAcronym(this.faculty);
        if(faculty==null) {
            try {
                faculty = facultyService.save(new Faculty(this.faculty));
            } catch (Faculty.InvalidFacultyException e) {
                e.printStackTrace();
            }
        }
        major.setFaculty(faculty);
        major.setMixedField(mixedField);
        major.setAnnotations(annotations);
        major.setShortName(shortName);
        major.setFullName(fullName);
        Person contactPersonDB1 = personService.findByMail(contactPerson1.getMail());
        Person contactPersonDB2 = personService.findByMail(contactPerson2.getMail());
        //TODO handle creating new Person
        if(contactPersonDB1==null) {
            contactPersonDB1 = new Person(contactPerson1.getFirstName(), contactPerson1.getLastName(), contactPerson1.getPhoneNo(), contactPerson1.getMail());
            contactPersonDB1 = personService.save(contactPersonDB1);
        }
        major.setContactPerson1(contactPersonDB1);
        if(contactPersonDB2==null) {
            contactPersonDB2 = new Person(contactPerson2.getFirstName(), contactPerson2.getLastName(), contactPerson2.getPhoneNo(), contactPerson2.getMail());
            contactPersonDB2 = personService.save(contactPersonDB2);
        }
        major.setContactPerson2(contactPersonDB2);
        major.setNumberOfPlaces(numberOfPlaces);
        major.setMode(mode);
        return major;
    }

}