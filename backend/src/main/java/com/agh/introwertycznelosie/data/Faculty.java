package com.agh.introwertycznelosie.data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Faculty {

    private class InvalidFacultyException extends Exception {
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

    public Faculty(String acronym) throws InvalidFacultyException {
        this.acronym = acronym;
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
                throw new InvalidFacultyException(acronym + " is not recognized faculty acronym");

        }
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