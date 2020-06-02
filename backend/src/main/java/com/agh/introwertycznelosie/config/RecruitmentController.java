package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.data.Recruitment;
import com.agh.introwertycznelosie.mockups.RecruitmentMockup;
import com.agh.introwertycznelosie.services.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class RecruitmentController {

    Logger logger = LogManager.getLogger(RecruitmentController.class);


    @Autowired
    RecruitmentService recruitmentService;

    @GetMapping(value="/newest-recruitments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecruitmentMockup> getRecruitments() {
        List<RecruitmentMockup> recruitmentMockups = new ArrayList<>();
        for (Recruitment recruitment : recruitmentService.get())
        {
            recruitmentMockups.add(new RecruitmentMockup(recruitment));
        }
        return recruitmentMockups;
    }

    @PostMapping("/new-recruitment")
    public Long postNewRecruitment(@RequestBody RecruitmentMockup recruitmentMockup) {
        Recruitment recruitment = recruitmentMockup.mockupToRecruitment();
        recruitment = recruitmentService.save(recruitment);
        logger.info("New recruitment added " + recruitment);
        return recruitment.getId();
    }

    @GetMapping(value = "/recruitment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RecruitmentMockup getRecruitment(@PathVariable Long id) {
        return new RecruitmentMockup(recruitmentService.get(id));
    }
}
