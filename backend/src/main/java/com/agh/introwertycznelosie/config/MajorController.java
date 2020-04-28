package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.data.Major;
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

    @GetMapping(value="/majors", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Major> getMajors() {
        return majorService.get();
    }

    @PostMapping("/new-major")
    public ResponseEntity<HttpStatus> postNewMajor(@RequestBody Major major) {
        majorService.save(major);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
