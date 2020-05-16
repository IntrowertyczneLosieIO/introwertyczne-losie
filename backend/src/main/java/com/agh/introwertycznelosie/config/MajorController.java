package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.data.Faculty;
import com.agh.introwertycznelosie.data.Major;
import com.agh.introwertycznelosie.services.FacultyService;
import com.agh.introwertycznelosie.services.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MajorController {

    @Autowired
    MajorService majorService;

    @Autowired
    FacultyService facultyService;

    @GetMapping(value = "/newest-majors", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Major> getMajors() {
        return majorService.get();
    }

    @PostMapping("/new-major")
    public ResponseEntity<HttpStatus> postNewMajor(@RequestBody Major major) {
        Faculty faculty = facultyService.findByAcronym(major.getFaculty().getAcronym());
        if (faculty == null) {
            faculty = facultyService.save(major.getFaculty());
        }
        major.setFaculty(faculty);
        majorService.save(major);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("edit-major/{id}")
    public Major updateMajor(@RequestBody Major major, @PathVariable Long id) {
        Major majorDB = majorService.get(id);
        if (majorDB != null) {
            majorDB.setFullName(major.getFullName());
            majorDB.setShortName(major.getShortName());
            Faculty faculty = facultyService.findByAcronym(major.getFaculty().getAcronym());
            if (faculty == null) {
                faculty = facultyService.save(major.getFaculty());
            }
            majorDB.setFaculty(faculty);
            majorDB.setContactPerson1(major.getContactPerson1());
            majorDB.setContactPerson2(major.getContactPerson2());
            majorDB.setAnnotations(major.getAnnotations());
            majorDB.setMode(major.getMode());
            majorDB.setNumberOfPlaces(major.getNumberOfPlaces());
            majorDB.setAnnotations(major.getAnnotations());
            majorDB.setMixedField(major.isMixedField());
            return majorService.save(majorDB);
        } else {
            return majorService.save(major);
        }
    }

    @GetMapping(value = "/major/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Major getMajor(@PathVariable(name = "id") Long id) {
        return majorService.get(id);
    }
}
