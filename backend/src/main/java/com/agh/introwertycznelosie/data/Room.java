package com.agh.introwertycznelosie.data;

import org.springframework.data.domain.Range;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private int id;

    private int recommendedCapacity;
    private int maximalCapacity;
    private String localization;
    private int number;
    @OneToMany(mappedBy = "room")
    private List<DateRange> availableDates = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<DateRange> getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(List<DateRange> availableDates) {
        this.availableDates = availableDates;
    }

    @Entity
    private static class DateRange {
        @Id
        @GeneratedValue
        private int id;

        private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm";
        private LocalDateTime dateFrom;
        private LocalDateTime dateTo;
        @ManyToOne
        private Room room;

        public DateRange() {
            dateFrom = LocalDateTime.MIN;
            dateTo = LocalDateTime.MAX;
        }

        public DateRange(String dateFrom, String dateTo) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            LocalDateTime probableDateFrom = LocalDateTime.parse(dateFrom, formatter);
            LocalDateTime probableDateTo = LocalDateTime.parse(dateTo, formatter);
            if (probableDateFrom.compareTo(probableDateTo) > 0) {
                this.dateFrom = LocalDateTime.MIN;
                this.dateTo = LocalDateTime.MAX;
            } else {
                this.dateFrom = LocalDateTime.parse(dateFrom, formatter);
                this.dateTo = LocalDateTime.parse(dateTo, formatter);
            }
        }

        public Room getRoom() {
            return room;
        }

        public void setRoom(Room room) {
            this.room = room;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public LocalDateTime getDateFrom() {
            return this.dateFrom;
        }

        public void setDateFrom(LocalDateTime dateFrom) {
            if (dateFrom.compareTo(this.dateTo) > 0) {
                this.dateFrom = LocalDateTime.MIN;
            } else {
                this.dateFrom = dateFrom;
            }
        }

        public LocalDateTime getDateTo() {
            return this.dateTo;
        }

        public void setDateTo(LocalDateTime dateTo) {
            if (this.dateFrom.compareTo(dateTo) > 0) {
                this.dateTo = LocalDateTime.MAX;
            } else {
                this.dateTo = dateTo;
            }
        }
    }
}
