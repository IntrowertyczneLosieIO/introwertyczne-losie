package com.agh.introwertycznelosie.mockups;

import com.agh.introwertycznelosie.data.Exam;
import com.agh.introwertycznelosie.data.Room;
import com.agh.introwertycznelosie.data.Subexam;
import com.agh.introwertycznelosie.services.ExamService;
import com.agh.introwertycznelosie.services.RoomService;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class SubexamMockup {

    public SubexamMockup(Subexam subexam) {
        id = subexam.getId();
        examId = subexam.getExam().getId();
        roomId = subexam.getRoom().getId();
        room = subexam.getRoom().getLocalization() + " " + subexam.getRoom().getNumber();
        date = subexam.getDate().toString().substring(0,10);
        time = subexam.getTime().toString();
    }

    public Subexam mockToSubexam(ExamService examService, RoomService roomService) {
        Subexam subexam = new Subexam();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateFromString = format.parse(date);
            subexam.setDate(dateFromString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Exam exam = examService.get(examId);
        subexam.setExam(exam);
        Room room = roomService.get(roomId);
        subexam.setRoom(room);
        subexam.setTime(LocalTime.parse(time));
        return subexam;
    }


    public Long getId() {
        return id;
    }

    public Long getExamId() {
        return examId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getRoom() {return room; }

    private Long id;
    private Long examId;
    private Long roomId;
    private String date;
    private String time;
    private String room;

}
