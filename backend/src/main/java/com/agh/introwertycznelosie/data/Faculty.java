package com.agh.introwertycznelosie.data;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(using = FacultySerializer.class)
@JsonDeserialize(using = FacultyDeserializer.class)
public class Faculty {

    public class InvalidFacultyException extends Exception {
        public InvalidFacultyException(String errorMessage) {
            super(errorMessage);
        }
    }


    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String acronym;

    public Faculty(){}

    public Faculty(String name, String acronym){
        this.name = name;
        this.acronym = acronym;
    }
    //TODO change this method after adding dynamic Faculties on frontend
    public Faculty(String acronym) throws InvalidFacultyException {
        switch (acronym) {
            case "WGiG":
                this.name = "Wydział Górnictwa i Geoinżynierii";
                break;
            case "WIMIP":
                this.name = "Wydział Inżynierii Metali\n" +
                        "i Informatyki Przemysłowej";
                break;
            case "WEAiIB":
                this.name = "Wydział Elektrotechniki, Automatyki,\n" +
                        "Informatyki i Inżynierii Biomedycznej";
                break;
            case "WIEiT":
                this.name = "Wydział Informatyki, Elektroniki\n" +
                        "i Telekomunikacji";
            case "WIMiR":
                this.name = "Wydział Inżynierii Mechanicznej\n" +
                        "i Robotyki";
                break;
            case "WGGiOS":
                this.name = "Wydział Geologii, Geofizyki\n" +
                        "i Ochrony Środowiska";
                break;
            case "WGGiIS":
                this.name = "Wydział Geodezji Górniczej\n" +
                        "i Inżynierii Środowiska";
                break;
            case "WIMiC":
                this.name = "Wydział Inżynierii Materiałowej\n" +
                        "i Ceramiki";
                break;
            case "WO":
                this.name = "Wydział Odlewnictwa";
                break;
            case "WMN":
                this.name = "Wydział Metali Nieżelaznych";
                break;
            case "WWNiG":
                this.name = "Wydział Wiertnictwa, Nafty i Gazu";
                break;
            case "WZ":
                this.name = "Wydział Zarządzania";
                break;
            case "WEiP":
                this.name = "Wydział Energetyki i Paliw";
                break;
            case "WFiIS":
                this.name = "Wydział Fizyki i Informatyki Stosowanej";
                break;
            case "WMS":
                this.name = "Wydział Matematyki Stosowanej";
                break;
            case "WH":
                this.name = "Wydział Humanistyczny";
                break;
            default:
                this.name = "Incorrect Faculty Acronym";
                throw new InvalidFacultyException(acronym + " is not recognized faculty acronym");
        }
        this.acronym = acronym;
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
                '}';
    }
}