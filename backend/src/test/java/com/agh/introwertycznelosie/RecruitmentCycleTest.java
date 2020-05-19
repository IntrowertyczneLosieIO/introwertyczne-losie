package com.agh.introwertycznelosie;


import com.agh.introwertycznelosie.data.Exam;
import com.agh.introwertycznelosie.data.Recruitment;
import com.agh.introwertycznelosie.data.RecruitmentCycle;
import com.agh.introwertycznelosie.data.Semester;
import com.agh.introwertycznelosie.services.RecruitmentCycleService;
import com.agh.introwertycznelosie.services.RecruitmentService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.agh.introwertycznelosie.data.ModeOfStudy.fullTime;

@SpringBootTest
@Transactional
public class RecruitmentCycleTest {

    public static Recruitment recruitment;
    public static RecruitmentCycle recruitmentCycle1;
    public static RecruitmentCycle recruitmentCycle2;

    @Autowired
    RecruitmentCycleService recruitmentCycleService;
    @Autowired
    RecruitmentService recruitmentService;

    @Test
    public void getRCTest(){
        Assertions.assertEquals(recruitmentCycle1, recruitmentCycleService.get(recruitmentCycle1.getId()));
        Assertions.assertEquals(recruitmentCycle2, recruitmentCycleService.get(recruitmentCycle2.getId()));
        Assertions.assertNotEquals(recruitmentCycle1, recruitmentCycleService.get(recruitmentCycle2.getId()));
    }
    @Test
    public void deleteRCTest(){
        Assertions.assertDoesNotThrow(() -> recruitmentCycleService.delete(recruitmentCycle1.getId()));
        Assertions.assertThrows(EmptyResultDataAccessException.class, () ->recruitmentCycleService.delete(recruitmentCycle1.getId()));
    }
    @Test
    public void editRCTest(){
        RecruitmentCycle recruitmentCycle = recruitmentCycleService.get(recruitmentCycle1.getId());
        Exam exam = new Exam("Computer Science", null, new Date(2021, Calendar.SEPTEMBER, 1), new Date(2021, Calendar.SEPTEMBER, 8), recruitmentCycle);
        List<Exam> exams = new ArrayList<>();
        exams.add(exam);
        recruitmentCycle.setExams(exams);
        recruitmentCycleService.save(recruitmentCycle);
        Assertions.assertEquals(exams, recruitmentCycleService.get(recruitmentCycle1.getId()).getExams());
    }


    @BeforeAll
    public static void setupObjects() {
        recruitment = new Recruitment("LATO-2020", 2020, Semester.summer);
        recruitmentCycle1 = new RecruitmentCycle(recruitment, 1);
        recruitmentCycle2 = new RecruitmentCycle(recruitment, 2);
    }

    @BeforeEach
    public void setupDB() {
        recruitment = recruitmentService.save(recruitment);
        recruitmentCycle1 = recruitmentCycleService.save(recruitmentCycle1);
        recruitmentCycle2 = recruitmentCycleService.save(recruitmentCycle2);
    }
    @AfterEach
    public void cleanDB(){
        try {
            recruitmentCycleService.delete(recruitmentCycle1);
            recruitmentCycleService.delete(recruitmentCycle2);
            recruitmentService.delete(recruitment);
        }
        catch (EmptyResultDataAccessException ex) {
        }
    }
}
