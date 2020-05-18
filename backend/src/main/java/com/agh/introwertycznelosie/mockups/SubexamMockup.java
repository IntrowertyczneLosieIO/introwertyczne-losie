package com.agh.introwertycznelosie.mockups;

import com.agh.introwertycznelosie.data.Exam;
import com.agh.introwertycznelosie.data.Room;
import com.agh.introwertycznelosie.data.Subexam;
import com.agh.introwertycznelosie.services.ExamService;
import com.agh.introwertycznelosie.services.RoomService;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

public class SubexamMockup {

    public SubexamMockup(Subexam subexam) {
        id = subexam.getId();
        examId = subexam.getExam().getId();
        roomId = subexam.getRoom().getId();
        date = subexam.getDate();
        time = subexam.getTime();
    }

    public Subexam mockToSubexam(ExamService examService, RoomService roomService) {
        Subexam subexam = new Subexam();

        subexam.setDate(date);
        Exam exam = examService.get(examId);
        subexam.setExam(exam);
        Room room = roomService.get(roomId);
        subexam.setRoom(room);
        subexam.setTime(time);
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

    public Date getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    private Long id;
    private Long examId;
    private Long roomId;
    private Date date;
    private LocalTime time;

}
