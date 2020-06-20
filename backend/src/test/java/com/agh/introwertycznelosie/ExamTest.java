package com.agh.introwertycznelosie;

import com.agh.introwertycznelosie.data.*;
import com.agh.introwertycznelosie.services.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.agh.introwertycznelosie.data.ModeOfStudy.fullTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ExamTest {

    private static Exam exam1, exam2, exam1Test;
    private static Person person1, person2, person3;
    private static Major major1, major2;
    private static RecruitmentCycle recruitmentCycle;
    private static Faculty wiet;
    private static Recruitment recruitment;

    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private ExamService examService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private RecruitmentCycleService recruitmentCycleService;

    @BeforeAll
    public static void createClasses() {
        wiet = new Faculty("Wydział Informatyki, Elektroniki i Telekomunikacji", "WIEiT", new ArrayList<>());
        person1 = new Person("Adam", "Kowalik", "666555444", "a.kowalik@agh.edu.pl");
        person2 = new Person("Janina", "Bosacka", "999888777", "j.bosacka@agh.edu.pl");
        person3 = new Person("Janina", "Bosacka", "999888777", "j.bosacka@agh.edu.pl");
        recruitment = new Recruitment("LATO-2020", 2020, Semester.summer);
        wiet.addRecruitment(recruitment);
        major1 = new Major(wiet, "Computer Science", "Inf", fullTime, 200, person1, null, false, "", recruitment);
        major2 = new Major(wiet, "Electronics", "Inf", fullTime, 200, person2, person3, false, "", recruitment);
        recruitmentCycle = new RecruitmentCycle(null, 1);
        recruitment.addRecruitmentCycle(recruitmentCycle);
        exam1 = new Exam("Computer Science", major1, new Date(2021, Calendar.SEPTEMBER, 1), new Date(2021, Calendar.SEPTEMBER, 8), recruitmentCycle);
        exam2 = new Exam("Electronics", major2, new Date(2021, Calendar.SEPTEMBER, 8), new Date(2021, Calendar.SEPTEMBER, 16), recruitmentCycle);
    }

    @Test
    void testAddingExamToDatabase(){
        recruitment = recruitmentService.save(recruitment);
        recruitmentCycle = recruitmentCycleService.save(recruitmentCycle);
        wiet = facultyService.save(wiet);
        major1 = majorService.save(major1);
        major2 = majorService.save(major2);
        exam1Test = examService.save(exam1);
        assertNotNull(exam1Test);
    }

    @Test
    void testGettingExamFromDatabase() {
        Exam exam2Test = examService.save(exam2);
        Exam exam2GetTest = examService.get(exam2Test.getId());
        assertEquals(exam2Test.getId(), exam2GetTest.getId());
    }

}