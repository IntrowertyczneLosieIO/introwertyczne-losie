package com.agh.introwertycznelosie.data;

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

    private static Exam exam1, exam2;

    @Autowired
    private ExamService examService;

    @BeforeAll
    public static void createClasses() {
        exam1 = new Exam("Computer Science", WIEiT, fullTime, new Date(2021, Calendar.SEPTEMBER, 1), new Date(2021, Calendar.SEPTEMBER, 8));
        exam2 = new Exam("Cultural Studies", WH, fullTime, new Date(2021, Calendar.SEPTEMBER, 8), new Date(2021, Calendar.SEPTEMBER, 16));

    }

    @Test
    void testAddingExamToDatabase(){
        Exam exam1Test = examService.save(exam1);
        assertNotNull(exam1Test);
    }

    @Test
    void testGettingExamFromDatabase() {
        Exam exam2Test = examService.save(exam2);
        Exam exam2GetTest = examService.get(exam2Test.getId());
        assertEquals(exam2Test, exam2GetTest);
    }

}