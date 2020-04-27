package com.agh.introwertycznelosie.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;
import java.time.LocalTime;

@Entity
public class Subexam {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Exam exam;
    @OneToOne
    private Room room;
    private Date date;
    private LocalTime time;

    public Subexam() {}

    public Subexam(long id, Exam exam, Room room, Date date, LocalTime time) {
        this.id = id;
        this.exam = exam;
        this.room = room;
        this.date = date;
        this.time = time;
    }

    public void setId(long id) { this.id = id; }

    public long getId() { return this.id; }

    public void setExam(Exam exam) { this.exam = exam; }

    public Exam getExam() { return this.exam; }

    public void setRoom(Room room) { this.room = room; }

    public Room getRoom() { return this.room; }

    public void setDate(Date date) { this.date = date; }

    public Date getDate() { return this.date; }

    public void setTime(LocalTime time) { this.time = time; }

    public LocalTime getTime() { return time; }
}
