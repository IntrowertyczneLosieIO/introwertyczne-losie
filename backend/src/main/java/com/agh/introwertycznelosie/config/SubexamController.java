package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.data.Subexam;
import com.agh.introwertycznelosie.mockups.MajorMockup;
import com.agh.introwertycznelosie.mockups.SubexamMockup;
import com.agh.introwertycznelosie.services.ExamService;
import com.agh.introwertycznelosie.services.RoomService;
import com.agh.introwertycznelosie.services.SubexamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SubexamController {

    @Autowired
    SubexamService subexamService;

    @Autowired
    RoomService roomService;

    @Autowired
    ExamService examService;

    @PostMapping("/new-subexam")
    public Long postNewSubexam(@RequestBody SubexamMockup subexamMockup) {
        Subexam subexam = subexamMockup.mockToSubexam(examService, roomService);
        subexam = subexamService.save(subexam);
        return subexam.getId();
    }

}
