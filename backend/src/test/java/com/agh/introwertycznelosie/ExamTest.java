package com.agh.introwertycznelosie;

import com.agh.introwertycznelosie.data.Exam;
import com.agh.introwertycznelosie.data.Faculty;
import com.agh.introwertycznelosie.data.Major;
import com.agh.introwertycznelosie.services.ExamService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

import static com.agh.introwertycznelosie.data.Faculty.WH;
import static com.agh.introwertycznelosie.data.Faculty.WIEiT;
import static com.agh.introwertycznelosie.data.ModeOfStudy.fullTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExamTest {

    private static Exam exam1, exam2, exam1Test;

    @Autowired
    private ExamService examService;

    @BeforeAll
    public static void createClasses() {
        Major major1 = new Major(WIEiT, "Computer Science", "Inf", fullTime, 200, "Adam Nowak", "Janina Kowalska", false, "");
        Major major2 = new Major(WIEiT, "Electronics", "Inf", fullTime, 200, "Adam Nowak", "Janina Kowalska", false, "");
        exam1 = new Exam("Computer Science", major1, fullTime, new Date(2021, Calendar.SEPTEMBER, 1), new Date(2021, Calendar.SEPTEMBER, 8));
        exam2 = new Exam("Electronics", major2, fullTime, new Date(2021, Calendar.SEPTEMBER, 8), new Date(2021, Calendar.SEPTEMBER, 16));

    }

    @Test
    void testAddingExamToDatabase(){
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