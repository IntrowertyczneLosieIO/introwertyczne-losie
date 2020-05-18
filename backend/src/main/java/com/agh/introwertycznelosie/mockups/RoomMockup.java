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

    public int getRecommendedCapacity() {
        return recommendedCapacity;
    }

    public int getMaximalCapacity() {
        return maximalCapacity;
    }

    public String getLocalization() {
        return localization;
    }

    public String getNumber() {
        return number;
    }

    public List<DateRange> getAvailableDates() {
        return availableDates;
    }

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
