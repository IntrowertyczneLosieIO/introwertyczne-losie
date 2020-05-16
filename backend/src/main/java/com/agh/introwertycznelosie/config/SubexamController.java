package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.data.Exam;
import com.agh.introwertycznelosie.data.Subexam;
import com.agh.introwertycznelosie.mockups.ExamMockup;
import com.agh.introwertycznelosie.services.ExamService;
import com.agh.introwertycznelosie.services.MajorService;
import com.agh.introwertycznelosie.services.RecruitmentCycleService;
import com.agh.introwertycznelosie.services.SubexamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class SubexamController {
    @Autowired
    SubexamService subexamService;

    @Autowired
    RecruitmentCycleService recruitmentCycleService;

    @Autowired
    MajorService majorService;

    @GetMapping(value="/newest-exams", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ExamMockup> getSubexams() {
        List<ExamMockup> list = new ArrayList<>();
        for (Subexam subexam : subexamService.get())
        {
            list.add(new SubexamMockup(subexam));
        }
        return list;
    }

    @PostMapping("/new-exam")
    public ResponseEntity<HttpStatus> postNewSubexam(@RequestBody SubexamMockup subexamMockup) {
        Subexam subexam = subexamMockup.mockToSubexam(recruitmentCycleService, majorService);
        subexamService.save(subexam);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
