package com.agh.introwertycznelosie;

import com.agh.introwertycznelosie.data.*;
import com.agh.introwertycznelosie.services.ExamService;
import com.agh.introwertycznelosie.services.RoomService;
import com.agh.introwertycznelosie.services.SubexamService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SubexamTests {
//    private static Subexam s1;
//    private static Subexam s2;
//    private static Exam e1;
//    private static Room r1;
//    @Autowired
//    private SubexamService subexamService;
//    @Autowired
//    private ExamService examService;
//    @Autowired
//    private RoomService roomService;
//
//    @BeforeAll
//    public static void setup(){
//        LinkedList<DateRange> availableDates = new LinkedList<>();
//        availableDates.add(new DateRange());
//        availableDates.add(new DateRange());
//        r1 = new Room(100, 200, "d17", "3.42", availableDates);
//        e1 = new Exam("analiza", Faculty.WIEiT, ModeOfStudy.fullTime, new Date(2020, 10, 10), new Date(2020, 10, 13));
//        s1 = new Subexam(e1, r1, new Date(2020, 10, 10), LocalTime.NOON);
//        s2 = new Subexam(e1, r1, new Date(2020, 10, 12), LocalTime.MIDNIGHT);
//    }
//
//    @Test
//    void testRoomSave(){
//        roomService.save(r1);
//        examService.save(e1);
//        subexamService.save(s1);
//    }
//
//    @Test
//    void testRoomGet(){
//        assertEquals(subexamService.get(s1.getId()).getId(), s1.getId());
//        assertEquals(subexamService.get(s2.getId()).getId(), s2.getId());
//    }
//
//    @Test
//    void testDelete(){
//        subexamService.delete(s1);
//    }

}
