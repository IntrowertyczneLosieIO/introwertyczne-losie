package com.agh.introwertycznelosie;

import com.agh.introwertycznelosie.data.*;
import com.agh.introwertycznelosie.services.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;

import static com.agh.introwertycznelosie.data.ModeOfStudy.fullTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SubexamTests {

    private static Subexam s1;
    private static Subexam s2;
    private static Exam e1;
    private static Exam e2;
    private static Room r1;
    private static Major major1;
    private static Person person1, person2;
    private static RecruitmentCycle recruitmentCycle;
    private static Faculty wiet;

    @Autowired
    private SubexamService subexamService;
    @Autowired
    private ExamService examService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private RecruitmentCycleService recruitmentCycleService;

    @Autowired
    private FacultyService facultyService;

    @BeforeAll
    public static void setup(){
        LinkedList<DateRange> availableDates = new LinkedList<>();
        availableDates.add(new DateRange());
        availableDates.add(new DateRange());
        wiet = new Faculty("Wydział Informatyki, Elektroniki i Telekomunikacji", "WIEiT");
        person1 = new Person("Adam", "Kowalik", "666555444", "a.kowalik@agh.edu.pl");
        person2 = new Person("Janina", "Bosacka", "999888777", "j.bosacka@agh.edu.pl");

        major1 = new Major(wiet, "Computer Science", "Inf", fullTime, 200, person1, person2, false, "");
        r1 = new Room(100, 200, "d17", "3.42", availableDates);
        recruitmentCycle = new RecruitmentCycle(null);
        e1 = new Exam("analiza", major1, ModeOfStudy.fullTime, new Date(2020, 10, 10), new Date(2020, 10, 13), recruitmentCycle);
        e2 = new Exam("analiza", major1, ModeOfStudy.fullTime, new Date(2020, 10, 10), new Date(2020, 10, 13), recruitmentCycle);
        s1 = new Subexam(e1, r1, new Date(2020, 10, 10), LocalTime.NOON);
        s2 = new Subexam(e2, r1, new Date(2020, 10, 12), LocalTime.MIDNIGHT);
    }

    @Test
    void testSubexamSave(){
        recruitmentCycle = recruitmentCycleService.save(recruitmentCycle);
        wiet = facultyService.save(wiet);
        major1 = majorService.save(major1);
        System.out.println("==============================" + major1.getId());
        roomService.save(r1);
        System.out.println(s1.getId());
        s1 = subexamService.save(s1);
        System.out.println(s1.getId());
    }

    @Test
    void testSubexamGet(){
        s2 = subexamService.save(s2);
        assertEquals(subexamService.get(s1.getId()).getId(), s1.getId());
        assertEquals(subexamService.get(s2.getId()).getId(), s2.getId());
    }

    @Test
    void testDelete(){
        assertThrows(org.springframework.dao.EmptyResultDataAccessException.class, () -> {
            roomService.delete(s2.getId());
            roomService.get(s2.getId());
        });
    }


}
