package com.agh.introwertycznelosie;

import com.agh.introwertycznelosie.data.Faculty;
import com.agh.introwertycznelosie.data.Major;
import com.agh.introwertycznelosie.data.ModeOfStudy;
import com.agh.introwertycznelosie.services.FacultyService;
import com.agh.introwertycznelosie.services.MajorService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MajorTests {

    @Test
    void saveAndGetMajorTest()
    {
        Major testMajor = majorService.get(m1.getId());
        Assertions.assertEquals(m1, testMajor);
        Assertions.assertNotEquals(m2, testMajor);
    }

    @Test
    void editMajorTest()
    {
        Major testMajor = majorService.get(m1.getId());
        testMajor.setNumberOfPlaces(42);
        majorService.save(testMajor);
        Assertions.assertEquals(42, majorService.get(m1.getId()).getNumberOfPlaces());
    }

    @Test
    void deleteByIdTest()
    {
        Assertions.assertDoesNotThrow(() -> majorService.delete(m1.getId()));
        Assertions.assertThrows(EmptyResultDataAccessException.class, () ->majorService.delete(m1.getId()));
    }

    @Test
    void getByFullNameTest()
    {
        Assertions.assertEquals(m1.getFullName(), majorService.findByFullName(m1.getFullName()).getFullName());
    }

    @Test
    void deleteTest()
    {
        Assertions.assertDoesNotThrow(() -> majorService.delete(m1));
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> majorService.delete(m1.getId()));
    }

    @Autowired
    private MajorService majorService;

    @Autowired
    private FacultyService facultyService;

    private static Major m1;
    private static Major m2;
    private static Faculty wiet;
    private static Faculty weaiib;

    @BeforeAll
    static void setupAll()
    {
        m1 = new Major();
        wiet = new Faculty("Wydział Informatyki, Elektroniki i Telekomunikacji", "WIEiT");
        m1.setFaculty(wiet);
        m1.setFullName("InformatykaTest");
        m1.setShortName("Infa-TEST");
        m1.setMode(ModeOfStudy.fullTime);
        m1.setNumberOfPlaces(200);

        m2 = new Major();
        weaiib = new Faculty("Wydział Elektrotechniki, Automatyki, Informatyki i Inżynierii Biomedycznej", "WEAiIB");
        m2.setFaculty(weaiib);
        m2.setFullName("ElektrotechnikaTest");
        m2.setShortName("ET-TEST");
        m2.setMode(ModeOfStudy.partTime);
        m2.setNumberOfPlaces(150);

    }
    @BeforeEach
    void setupEach()
    {
        weaiib = facultyService.save(weaiib);
        wiet = facultyService.save(wiet);
        m1 = majorService.save(m1);
        m2 = majorService.save(m2);
    }
    @AfterEach
    void cleanupEach()
    {
        try
        {
            majorService.delete(m1.getId());
            majorService.delete(m2.getId());
        }
        catch (EmptyResultDataAccessException ex)
        {

        }
    }


}
