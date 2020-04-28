package com.agh.introwertycznelosie;

import com.agh.introwertycznelosie.data.Faculty;
import com.agh.introwertycznelosie.data.Major;
import com.agh.introwertycznelosie.data.ModeOfStudy;
import com.agh.introwertycznelosie.services.MajorService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

@SpringBootTest
public class MajorTests {
    @Test
    void contextLoads() {
    }

    @Test
    void saveAndGetMajorTest()
    {
        System.out.println(m1.getId());
        m1 = majorService.save(m1);
        Major testMajor = majorService.findByFullName(m1.getFullName());
        System.out.println(testMajor.getId());
        Assertions.assertEquals(m1, testMajor);
        Assertions.assertNotEquals(m2, testMajor);
    }

    @BeforeTestMethod("editMajorTest")
    void setupEdit()
    {
        majorService.save(m1);
    }

    @Test
    void editMajorTest()
    {
        Major testMajor = majorService.findByFullName(m1.getFullName());
        testMajor.setNumberOfPlaces(42);
        majorService.delete(m1.getId());
        majorService.save(testMajor);
        testMajor = majorService.findByFullName(m1.getFullName());
        Assertions.assertEquals(42, testMajor.getNumberOfPlaces());
    }
    @AfterTestMethod("editMajorTest")
    void cleanupEdit()
    {
        majorService.delete(majorService.findByFullName(m1.getFullName()).getId());
    }

    @BeforeTestMethod("deleteTest")
    void setupDelete()
    {
        majorService.save(m1);
        majorService.save(m2);
    }

    @Test
    void deleteTest()
    {
        majorService.delete(m1.getId());
    }

    @Autowired
    private MajorService majorService;

    private static Major m1;
    private static Major m2;

    @BeforeAll
    static void setup()
    {
        m1 = new Major(Faculty.WIEiT, "InformatykaTest", "Infa-TEST", ModeOfStudy.fullTime, 200);
        m2 = new Major(Faculty.WEAiIB, "ElektrotechnikaTest", "ET-TEST", ModeOfStudy.partTime, 150);
    }


}
