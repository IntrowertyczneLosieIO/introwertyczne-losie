package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.data.Recruitment;
import com.agh.introwertycznelosie.services.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class RecruitmentController {

    @Autowired
    RecruitmentService recruitmentService;

    @GetMapping(value="/newest-recruitments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Recruitment> getRecruitments() {
        return recruitmentService.get();
    }

    @PostMapping("/new-recruitment")
    public ResponseEntity<HttpStatus> postNewMajor(@RequestBody Recruitment recruitment) {
        recruitmentService.save(recruitment);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
