package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.data.Room;
import com.agh.introwertycznelosie.data.Subexam;
import com.agh.introwertycznelosie.mockups.MajorMockup;
import com.agh.introwertycznelosie.mockups.SubexamMockup;
import com.agh.introwertycznelosie.services.ExamService;
import com.agh.introwertycznelosie.services.RoomService;
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
    RoomService roomService;

    @Autowired
    ExamService examService;

    @PostMapping("/new-subexam")
    public Long postNewSubexam(@RequestBody SubexamMockup subexamMockup) {
        Subexam subexam = subexamMockup.mockToSubexam(examService, roomService);
        subexam = subexamService.save(subexam);
        return subexam.getId();
    }

    @GetMapping(value="/newest-subexams/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SubexamMockup> getSubexamsByExam(@PathVariable(name = "id") Long id) {
        List<Subexam> subexams = subexamService.getByExam_Id(id);
        List<SubexamMockup> list = new ArrayList<>();
        for(Subexam s: subexams) {
            list.add(new SubexamMockup(s));
        }
        return list;
    }

    @DeleteMapping("delete-subexam/{id}")
    public ResponseEntity<HttpStatus> deleteRoom(@PathVariable Long id) {
        Subexam currentSubexam = subexamService.get(id);
        if (currentSubexam != null) {
            subexamService.delete(id);
        }
        return ResponseEntity.ok(HttpStatus.OK);

    }

}
