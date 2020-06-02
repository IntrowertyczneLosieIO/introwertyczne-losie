package com.agh.introwertycznelosie;

import com.agh.introwertycznelosie.data.Faculty;
import com.agh.introwertycznelosie.data.Major;
import com.agh.introwertycznelosie.data.ModeOfStudy;
import com.agh.introwertycznelosie.data.Person;
import com.agh.introwertycznelosie.services.FacultyService;
import com.agh.introwertycznelosie.services.MajorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class MajorTest {

    private static Major m1;
    private static Major m2;
    private static Faculty wiet;
    private static Faculty weaiib;

    @Autowired
    private MajorService majorService;

    @Autowired
    private FacultyService facultyService;

    @BeforeAll
    static void setup() {
        m1 = new Major();
        wiet = new Faculty("Wydział Informatyki, Elektroniki i Telekomunikacji", "WIEiT", new ArrayList<>());
        Person contact1 = new Person("Anna", "Nowak", "667452082", "anowak@agh.edu.pl");
        Person contact2 = new Person("Tomasz", "Kowalski", "525908712", "tkowalski@agh.edu.pl");
        m1.setFaculty(wiet);
        m1.setFullName("Elektronika i Telekomunikacja");
        m1.setShortName("EiT");
        m1.setMode(ModeOfStudy.fullTime);
        m1.setNumberOfPlaces(70);
        m1.setContactPerson1(contact1);
        m1.setContactPerson2(contact2);


        m2 = new Major();
        weaiib = new Faculty("Wydział Elektrotechniki, Automatyki, Informatyki i Inżynierii Biomedycznej", "WEAiIB", new ArrayList<>());
        Person contact3 = new Person("Maria", "Pisak", "983782130", "mpisak@agh.edu.pl");
        m2.setFaculty(weaiib);
        m2.setFullName("Automatyka i Robotyka");
        m2.setShortName("AiR");
        m2.setMode(ModeOfStudy.fullTime);
        m2.setNumberOfPlaces(90);
        m2.setContactPerson1(contact3);
        m2.setContactPerson2(null);
    }

    @Test
    void testMajorSave() {
        System.out.println(m1.getId());
        m1 = majorService.save(m1);
        System.out.println(m1.getId());
    }

    @Test
    void testMajorGet() {
        m1 = majorService.save(m1);
        m2 = majorService.save(m2);
        assertEquals(majorService.get(m1.getId()).getId(), m1.getId());
        assertEquals(majorService.get(m2.getId()).getId(), m2.getId());
    }

    @Test
    @AfterTestMethod("testMajorSave")
    void testDeleteById() {
        m1 = majorService.save(m1);
        Assertions.assertDoesNotThrow(() -> majorService.delete(m1.getId()));
        Assertions.assertThrows(EmptyResultDataAccessException.class, () ->majorService.delete(m1.getId()));
    }

    @Test
    @AfterTestMethod("testMajorSave")
    void testDeleteByObject() {
        majorService.delete(m1);
    }

    @Test
    void findByFullName() {
        String fullName = "Automatyka i Robotyka";
        weaiib = facultyService.save(weaiib);
        m2 = majorService.save(m2);
        assertEquals(majorService.findByFullName(fullName), m2);
    }
}