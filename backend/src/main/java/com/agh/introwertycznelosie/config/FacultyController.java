package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.data.Faculty;
import com.agh.introwertycznelosie.mockups.FacultyMockup;
import com.agh.introwertycznelosie.services.FacultyService;
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
public class FacultyController {

    Logger logger = LogManager.getLogger(FacultyController.class);
    @Autowired
    FacultyService facultyService;

    @GetMapping(value="/newest-faculties", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FacultyMockup> getFaculties() {
        List<FacultyMockup> list = new ArrayList<>();
        for (Faculty faculty : facultyService.get())
        {
            list.add(new FacultyMockup(faculty));
        }
        return list;
    }

    @PostMapping("/new-faculty")
    public ResponseEntity<HttpStatus> postNewFaculty(@RequestBody FacultyMockup facultyMockup) {
        Faculty faculty = facultyMockup.mockToFaculty();
        facultyService.save(faculty);
        logger.info("New faculty created: " + faculty );
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PutMapping("edit-faculty/{id}")
    FacultyMockup updateFaculty(@RequestBody FacultyMockup facultyMockup, @PathVariable Long id) {
        Faculty faculty = facultyMockup.mockToFaculty();
        Faculty currentFaculty = facultyService.get(id);
        Faculty oldFaculty = currentFaculty;
        if (currentFaculty != null) {
            currentFaculty.setName(faculty.getName());
            currentFaculty.setAcronym(faculty.getAcronym());
            currentFaculty = facultyService.save(currentFaculty);
            logger.info("Updated faculty " + oldFaculty + " to " + currentFaculty);
            return new FacultyMockup(currentFaculty);
        } else {
            faculty = facultyService.save(faculty);
            logger.info("New faculty created " + faculty);
            return new FacultyMockup(faculty);
        }
    }

    @DeleteMapping("delete-faculty/{id}")
    public ResponseEntity<HttpStatus> deleteFaculty(@PathVariable Long id) {
        Faculty currentFaculty = facultyService.get(id);
        if (currentFaculty != null) {
            facultyService.delete(id);
            logger.info("Deleted faculty " + currentFaculty);
        }
        return ResponseEntity.ok(HttpStatus.OK);

    }

}
