package com.agh.introwertycznelosie.config;


import com.agh.introwertycznelosie.data.Exam;
import com.agh.introwertycznelosie.mockups.ExamMockup;
import com.agh.introwertycznelosie.services.ExamService;
import com.agh.introwertycznelosie.services.MajorService;
import com.agh.introwertycznelosie.services.RecruitmentCycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class ExamController {

    @Autowired
    ExamService examService;

    @Autowired
    RecruitmentCycleService recruitmentCycleService;

    @Autowired
    MajorService majorService;

    @GetMapping(value="/newest-exams", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ExamMockup> getExams() {
        List<ExamMockup> list = new ArrayList<>();
        for (Exam exam : examService.get())
        {
            list.add(new ExamMockup(exam));
        }
        return list;
    }

    @PostMapping("/new-exam")
    public ResponseEntity<HttpStatus> postNewMajor(@RequestBody ExamMockup examMockup) {
        Exam exam = examMockup.mockToExam(recruitmentCycleService, majorService);
        examService.save(exam);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
