package com.agh.introwertycznelosie.mockups;

import com.agh.introwertycznelosie.data.DateRange;
import com.agh.introwertycznelosie.data.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomMockup {
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

    @Autowired
    public RoomMockup(){}

    public Long getId() {
        return id;
    }

    private Long id;
    private int recommendedCapacity;
    private int maximalCapacity;
    private String localization;
    private String number;
    private List<DateRange> availableDates = new ArrayList<>();


    public RoomMockup(Room room) {
        id = room.getId();
        recommendedCapacity = room.getRecommendedCapacity();
        maximalCapacity = room.getMaximalCapacity();
        localization = room.getLocalization();
        number = room.getNumber();
    }

    public Room mockToRoom(){
        Room room = new Room();
        room.setLocalization(localization);
        room.setNumber(number);
        room.setRecommendedCapacity(recommendedCapacity);
        room.setMaximalCapacity(maximalCapacity);
        return room;
    }

}
