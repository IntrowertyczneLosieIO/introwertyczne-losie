package com.agh.introwertycznelosie.mockups;

import com.agh.introwertycznelosie.data.*;
import com.agh.introwertycznelosie.services.FacultyService;
import com.agh.introwertycznelosie.services.PersonService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

    public String getContactPerson1Mail() {
        return contactPerson1Mail;
    }

    public void setContactPerson1Mail(String contactPerson1Mail) {
        this.contactPerson1Mail = contactPerson1Mail;
    }

    public String getContactPerson2Mail() {
        return contactPerson2Mail;
    }

    public void setContactPerson2Mail(String contactPerson2Mail) {
        this.contactPerson2Mail = contactPerson2Mail;
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

    private String faculty;
    private List<Exam> exams = new ArrayList<>();
    private String fullName;
    private String shortName;
    private ModeOfStudy mode;
    private int numberOfPlaces;
    @JsonProperty("contactPerson1")
    private String contactPerson1Mail;
    @JsonProperty("contactPerson2")
    private String contactPerson2Mail;
    private boolean mixedField;
    private String annotations;

    public MajorMockup(Major major)
    {
        faculty = major.getFaculty().getAcronym();
        shortName = major.getShortName();
        fullName = major.getFullName();
        mode = major.getMode();
        numberOfPlaces = major.getNumberOfPlaces();
        contactPerson1Mail = major.getContactPerson1().getMail();
        contactPerson2Mail = major.getContactPerson2().getMail();
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
        Person contactPerson1 = personService.findByMail(contactPerson1Mail);
        Person contactPerson2 = personService.findByMail(contactPerson2Mail);
        //TODO handle creating new Person
        if(contactPerson1==null) {
            contactPerson1 = new Person(null, null, null, contactPerson1Mail);
            contactPerson1 = personService.save(contactPerson1);
        }
        major.setContactPerson1(contactPerson1);
        if(contactPerson2==null) {
            contactPerson2 = new Person(null, null, null, contactPerson2Mail);
            contactPerson2 = personService.save(contactPerson2);
        }
        major.setContactPerson2(contactPerson2);
        major.setNumberOfPlaces(numberOfPlaces);
        major.setMode(mode);
        return major;
    }

}
