package com.agh.introwertycznelosie.services;

import com.agh.introwertycznelosie.data.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MajorTest {

    private static Major m1;
    private static String fullName1 = "Elektronika i Telekomunikacja";
    private static String shortName1 = "EiT";
    private static ModeOfStudy mode1 = ModeOfStudy.fullTime;
    private static int places1 = 70;
    private static String contact1 = "Anna Nowak, anowak@agh.edu.pl, 667452082";
    private static String contact2 = "Tomasz Kowalski, tkowalski@agh.edu.pl, 525908712";

    private static Major m2;
    private static String fullName2 = "Automatyka i Robotyka";
    private static String shortName2 = "AiR";
    private static ModeOfStudy mode2 = ModeOfStudy.fullTime;
    private static int places2 = 90;
    private static String contact3 = "Maria Pisak, mpisak@agh.edu.pl, 983782130";
    private static String contact4 = "Karol Okno, kokno@agh.edu.pl, 782339019";


    @Autowired
    private MajorService majorService;

    @BeforeAll
    public static void setup(){
        m1 = new Major(Faculty.WIEiT, fullName1, shortName1, mode1, places1, contact1, contact2, false, null);
        m2 = new Major(Faculty.WEAiIB, fullName2, shortName2, mode2, places2, contact3, contact4, false, null);
    }


    @Test
    void testMajorSave() {
        System.out.println(m1.getId());
        m1 = majorService.save(m1);
        System.out.println(m1.getId());
    }

    @Test
    void testMajorGet() {
        m2 = majorService.save(m2);
        assertEquals(majorService.get(m1.getId()).getId(), m1.getId());
        assertEquals(majorService.get(m2.getId()).getId(), m2.getId());
    }

    @Test
    @AfterTestMethod("testMajorSave")
    void testDeleteById() {
        assertThrows(org.springframework.dao.EmptyResultDataAccessException.class, () -> {
            majorService.delete(m1.getId());
            majorService.get(m1.getId());
        });
    }

    @Test
    @AfterTestMethod("testRoomSave")
    void testDeleteByObject() {
        majorService.delete(m1);
    }

    @Test
    void findByFullName() {
        assertEquals(majorService.findByFullName(fullName1), m1);
    }
}