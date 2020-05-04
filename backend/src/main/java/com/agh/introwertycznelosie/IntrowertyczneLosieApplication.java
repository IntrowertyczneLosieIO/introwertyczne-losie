package com.agh.introwertycznelosie;

import com.agh.introwertycznelosie.data.Faculty;
import com.agh.introwertycznelosie.data.Major;
import com.agh.introwertycznelosie.data.ModeOfStudy;
import com.agh.introwertycznelosie.repositories.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class IntrowertyczneLosieApplication {

    @Autowired
    MajorRepository majorRepository;

    public static void main(String[] args) {
        SpringApplication.run(IntrowertyczneLosieApplication.class, args);
    }

    @PostConstruct
    private void initMajors(){
        Major m1 = new Major();

        m1.setFaculty(Faculty.WIEiT);
        m1.setFullName("TESTInformatyka");
        m1.setShortName("Infa-TEST");
        m1.setMode(ModeOfStudy.fullTime);
        m1.setNumberOfPlaces(200);

        Major m2 = new Major();

        m2.setFaculty(Faculty.WIEiT);
        m2.setFullName("TESTElektrotechnika");
        m2.setShortName("ET-TEST");
        m2.setMode(ModeOfStudy.partTime);
        m2.setNumberOfPlaces(150);

        m1.setContactPerson1("Garek Majęcki");

        m2.setContactPerson1("Jan Kowalski");
        m2.setContactPerson2("Adam Nowak");

        majorRepository.save(m1);
        majorRepository.save(m2);

    }

}