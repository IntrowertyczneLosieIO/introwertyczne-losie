package com.agh.introwertycznelosie.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class DateRange {
    @Id
    @GeneratedValue
    private Long id;

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
            throw new IllegalArgumentException();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateFrom() {
        return this.dateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        if (dateFrom.compareTo(this.dateTo) > 0) {
            this.dateFrom = LocalDateTime.MIN;
            throw new IllegalArgumentException();
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
            throw new IllegalArgumentException();
        } else {
            this.dateTo = dateTo;
        }
    }

    @Override
    public String toString() {
        return "DateRange{" +
                "id=" + id +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", room=" + room +
                '}';
    }
}
