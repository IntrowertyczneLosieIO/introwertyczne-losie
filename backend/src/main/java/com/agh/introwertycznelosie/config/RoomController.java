package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.data.Room;
import com.agh.introwertycznelosie.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping(value="/newest-rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Room> getRooms() {
        return roomService.get();
    }

    @PostMapping("/new-room")
    public ResponseEntity<HttpStatus> postNewRoom(@RequestBody Room room) {
        roomService.save(room);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
