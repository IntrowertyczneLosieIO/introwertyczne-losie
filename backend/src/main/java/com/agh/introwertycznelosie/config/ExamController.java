package com.agh.introwertycznelosie.config;


import com.agh.introwertycznelosie.data.Exam;
import com.agh.introwertycznelosie.data.Major;
import com.agh.introwertycznelosie.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ExamController {
    @Autowired
    ExamService examService;

    @GetMapping(value="/newest-exams", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Exam> getExams() {
        return examService.get();
    }

    @PostMapping("/new-exam")
    public ResponseEntity<HttpStatus> postNewMajor(@RequestBody Exam exam) {
        examService.save(exam);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
