package com.agh.introwertycznelosie;

import com.agh.introwertycznelosie.data.Exam;
import com.agh.introwertycznelosie.data.Faculty;
import com.agh.introwertycznelosie.data.Major;
import com.agh.introwertycznelosie.data.RecruitmentCycle;
import com.agh.introwertycznelosie.services.ExamService;
import com.agh.introwertycznelosie.services.FacultyService;
import com.agh.introwertycznelosie.services.MajorService;
import com.agh.introwertycznelosie.services.RecruitmentCycleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.agh.introwertycznelosie.data.ModeOfStudy.fullTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExamTest {

    private static Exam exam1, exam2, exam1Test;
    private static Major major1, major2;
    private static RecruitmentCycle recruitmentCycle;
    private static Faculty wiet;
  
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
        major1 = new Major(wiet, "Computer Science", "Inf", fullTime, 200, "Adam Nowak", "Janina Kowalska", false, "");
        major2 = new Major(wiet, "Electronics", "Inf", fullTime, 200, "Adam Nowak", "Janina Kowalska", false, "");
        recruitmentCycle = new RecruitmentCycle(null);
        exam1 = new Exam("Computer Science", major1, fullTime, new Date(2021, Calendar.SEPTEMBER, 1), new Date(2021, Calendar.SEPTEMBER, 8), recruitmentCycle);
        exam2 = new Exam("Electronics", major2, fullTime, new Date(2021, Calendar.SEPTEMBER, 8), new Date(2021, Calendar.SEPTEMBER, 16), recruitmentCycle);
    }

    @Test
    void testAddingExamToDatabase(){
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