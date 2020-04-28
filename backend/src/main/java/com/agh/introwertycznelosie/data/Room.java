package com.agh.introwertycznelosie.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private long id;

    private int recommendedCapacity;
    private int maximalCapacity;
    private String localization;
    private String number;
    @OneToMany(mappedBy = "room")
    private List<DateRange> availableDates = new ArrayList<>();

    public Room(){}

    public Room(int recommendedCapacity, int maximalCapacity, String localization, String number, List availableDates){
        this.setRecommendedCapacity(recommendedCapacity);
        this.setMaximalCapacity(maximalCapacity);
        this.setLocalization(localization);
        this.setNumber(number);
        this.setAvailableDates(availableDates);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRecommendedCapacity() {
        return recommendedCapacity;
    }

    public void setRecommendedCapacity(int recommendedCapacity) {
        this.recommendedCapacity = recommendedCapacity;
    }

    public int getMaximalCapacity() {
        return maximalCapacity;
    }

    public void setMaximalCapacity(int maximalCapacity) {
        this.maximalCapacity = maximalCapacity;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<DateRange> getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(List<DateRange> availableDates) {
        this.availableDates = availableDates;
    }
}
