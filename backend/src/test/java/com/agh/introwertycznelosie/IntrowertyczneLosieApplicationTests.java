package com.agh.introwertycznelosie;

import com.agh.introwertycznelosie.data.Major;
import com.agh.introwertycznelosie.services.MajorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntrowertyczneLosieApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MajorService majorService;

    @Test
    void modifyMajorTest() {
        String nameBeforeChange = "TestMajor";
        String nameAfterChange = "ChangedTestMajor";
        Major test = new Major();
        test.setFullName(nameBeforeChange);
        majorService.save(test);
        test = majorService.findByFullName(nameBeforeChange);
        majorService.delete(test.getId());
        test.setFullName(nameAfterChange);
        majorService.save(test);
        test = majorService.findByFullName(nameAfterChange);
        Assertions.assertEquals(test.getFullName(), nameAfterChange);
        majorService.delete(test.getId());
    }
}
