package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.data.Faculty;
import com.agh.introwertycznelosie.data.Recruitment;
import com.agh.introwertycznelosie.mockups.FacultyMockup;
import com.agh.introwertycznelosie.services.FacultyService;
import com.agh.introwertycznelosie.services.RecruitmentService;
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
    @Autowired
    RecruitmentService recruitmentService;

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
        Faculty faculty = facultyMockup.mockToFaculty(recruitmentService);
        facultyService.save(faculty);
        logger.info("New faculty created: " + faculty );
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PutMapping("edit-faculty/{id}")
    FacultyMockup updateFaculty(@RequestBody FacultyMockup facultyMockup, @PathVariable Long id) {
        Faculty faculty = facultyMockup.mockToFaculty(recruitmentService);
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

    @GetMapping("faculties-from-recruitation/{id}")
    public List<FacultyMockup> facultiesByRecruitation(@PathVariable Long id) {
        Recruitment recruitment = recruitmentService.get(id);
        List<FacultyMockup> faculties = new ArrayList<>();
        for (Faculty faculty : recruitment.getFaculties())
        {
            faculties.add(new FacultyMockup(faculty));
        }
        return faculties;
    }

    private static final String SERVER_LOCATION = "/";

    @GetMapping(path = "/download/faculty/{id}")
    public ResponseEntity<Resource> download( @PathVariable Long id) throws IOException {
        File file = new File(SERVER_LOCATION + File.separator + id + ".txt");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=raport.txt");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

}
