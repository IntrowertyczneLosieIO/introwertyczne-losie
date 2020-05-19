package com.agh.introwertycznelosie;

import com.agh.introwertycznelosie.data.Recruitment;
import com.agh.introwertycznelosie.data.RecruitmentStatus;
import com.agh.introwertycznelosie.data.Semester;
import com.agh.introwertycznelosie.services.RecruitmentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestMethod;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class RecruitmentTest {

    private static Recruitment recruitment1, recruitment2, recruitment3;

    @Autowired
    private RecruitmentService recruitmentService;

    @BeforeAll
    public static void createClasses() {
        recruitment1 = new Recruitment("LATO-2020", 2020, Semester.summer);
        recruitment2 = new Recruitment("ZIMA-2021", 2021, Semester.winter);
        recruitment3 = new Recruitment("LATO-2021", 2021, Semester.summer);
        recruitment2.setRecruitmentStatus(RecruitmentStatus.pending);
        recruitment1.setRecruitmentStatus(RecruitmentStatus.closed);
    }

    @Test
    public void testRecruitmentSave() {
        recruitment1 = recruitmentService.save(recruitment1);
        recruitment2 = recruitmentService.save(recruitment2);
        assertNotNull(recruitment1, "Recruitment1 is null while should not be");
        assertNotNull(recruitment2, "Recruitment2 is null while should not be");
    }

// test works only when database setup does not exist
//    @Test
//    public void getAll() {
//        List<Recruitment> recruitments = new ArrayList<>();
//        recruitment2 = recruitmentService.save(recruitment2);
//        recruitment1 = recruitmentService.save(recruitment1);
//        recruitment3 = recruitmentService.save(recruitment3);
//
//        recruitments.add(recruitment3);
//        recruitments.add(recruitment2);
//        recruitments.add(recruitment1);
//        for (Recruitment recruitment : recruitments) {
//            System.out.println(recruitment);
//        }
//        for (Recruitment recruitment: recruitmentService.get()) {
//            System.out.println(recruitment);
//        }
//        assertIterableEquals(recruitments, recruitmentService.get());
//    }


    @Test
    void testRecruitmentGet() {
        Recruitment recruitmentTest = recruitmentService.save(recruitment2);
        Recruitment recruitmentTest2 = recruitmentService.get(recruitmentTest.getId());
        assertEquals(recruitmentTest.getId(), recruitmentTest2.getId(), "Given and expected id's don't match");
    }

    @Test
    void testAssertEditable() {
        assertFalse(recruitment1.isEditable(),
                "Recruitment should not be editable but it is");
        assertFalse(recruitment2.isEditable(),
                "Recruitment should not be editable but it is");
        assertTrue(recruitment3.isEditable(),
                "Recruitment should be editable but is not");

    }

    @Test
    void testSetYear() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Recruitment("ZIMA-2019", 2019, Semester.winter);
        }, "Given year is valid and should not be.");
    }

    @Test
    @AfterTestMethod("testRecruitmentSave")
    public void testDeleteRecruitmentById() {
        assertThrows(org.springframework.dao.EmptyResultDataAccessException.class, () -> {
            recruitmentService.delete(recruitment2.getId());
            recruitmentService.get(recruitment2.getId());
        }, "Recruitment object should not exist in database but it does");
    }

    @Test
    @AfterTestMethod("testDeleteRecruitmentById")
    public void testDeleteRecruitmentByObject() {
            recruitmentService.delete(recruitment1);
            assertNull(recruitment1.getId());
    }
}
