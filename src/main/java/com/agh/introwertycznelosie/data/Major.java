package com.agh.introwertycznelosie.data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Major {

  @Id
  @GeneratedValue
  private int id;

  private Faculties faculty;
  private String fullName;
  private String shortName; // czy to jest w ogóle potrzebne? Że np informatyka to będzie inf?
  private ModeOfStudy mode;
  private int numberOfPlaces;
  private String contactPerson1; // tu ma byc klasa zamiast stringa
  private String contactPerson2;
  private boolean mixedField;
  private String annotations;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Faculties getFaculty() {
    return faculty;
  }

  public void setFaculty(Faculties faculty) {
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