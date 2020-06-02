package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.data.Faculty;
import com.agh.introwertycznelosie.data.Major;
import com.agh.introwertycznelosie.mockups.MajorMockup;
import com.agh.introwertycznelosie.services.FacultyService;
import com.agh.introwertycznelosie.services.MajorService;
import com.agh.introwertycznelosie.services.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class MajorController {

    Logger logger = LogManager.getLogger(MajorController.class);
    Logger facultyLogger = LogManager.getLogger(FacultyController.class);


    @Autowired
    MajorService majorService;

    @Autowired
    FacultyService facultyService;

    @Autowired
    PersonService personService;

    @GetMapping(value = "/newest-majors", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MajorMockup> getMajors() {
        List<MajorMockup> list = new ArrayList<>();
        for (Major major : majorService.get()) {
            list.add(new MajorMockup(major));
        }
        return list;
    }

    @PostMapping("/new-major")
    public ResponseEntity<HttpStatus> postNewMajor(@RequestBody MajorMockup majorMockup) {
        Major major = majorMockup.mockToMajor(personService, facultyService);
        Faculty faculty = facultyService.findByAcronym(major.getFaculty().getAcronym());
        if (faculty == null) {
            faculty = facultyService.save(major.getFaculty());
            facultyLogger.info("New faculty created: " + faculty);
        }
        major.setFaculty(faculty);
        majorService.save(major);
        logger.info("New major created" + major);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("edit-major/{id}")
    public MajorMockup updateMajor(@RequestBody MajorMockup majorMockup, @PathVariable Long id) {
        Major major = majorMockup.mockToMajor(personService, facultyService);
        Major majorDB = majorService.get(id);
        Major oldMajor = majorDB;
        if (majorDB != null) {
            majorDB.setFullName(major.getFullName());
            majorDB.setShortName(major.getShortName());
            Faculty faculty = facultyService.findByAcronym(major.getFaculty().getAcronym());
            if (faculty == null) {
                faculty = facultyService.save(major.getFaculty());
                facultyLogger.info("New faculty created " + faculty);
            }
            majorDB.setFaculty(faculty);
            majorDB.setContactPerson1(major.getContactPerson1());
            majorDB.setContactPerson2(major.getContactPerson2());
            majorDB.setAnnotations(major.getAnnotations());
            majorDB.setMode(major.getMode());
            System.out.println(majorDB.getMode());
            majorDB.setNumberOfPlaces(major.getNumberOfPlaces());
            majorDB.setAnnotations(major.getAnnotations());
            majorDB.setMixedField(major.isMixedField());
            majorDB = majorService.save(majorDB);
            logger.info("Updated major " + oldMajor + " to " + majorDB);
            return new MajorMockup(majorDB);
        } else {
            major = majorService.save(major);
            logger.info("New major created " + major);
            return new MajorMockup(major);
        }
    }

    @DeleteMapping("delete-major/{id}")
    public ResponseEntity<HttpStatus> deleteMajor(@PathVariable Long id) {
        Major currentMajor = majorService.get(id);
        if (currentMajor != null) {
            majorService.delete(id);
            logger.info("Deleted major " + currentMajor);
        }
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping(value = "/major/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MajorMockup getMajor(@PathVariable(name = "id") Long id) {
        return new MajorMockup(majorService.get(id));
    }
}
