package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.data.Room;
import com.agh.introwertycznelosie.mockups.RoomMockup;
import com.agh.introwertycznelosie.services.RoomService;
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
public class RoomController {

    Logger logger = LogManager.getLogger(RoomController.class);
    @Autowired
    RoomService roomService;

    @GetMapping(value="/newest-rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    List<RoomMockup> getRooms() {
        List<RoomMockup> list = new ArrayList<>();
        for (Room room : roomService.get())
        {
            list.add(new RoomMockup(room));
        }
        return list;
    }

    @GetMapping(value="/all-rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    List<RoomMockup> getAllRooms() {
        List<RoomMockup> list = new ArrayList<>();
        for (Room room : roomService.getAll())
        {
            list.add(new RoomMockup(room));
        }
        return list;
    }

    @PostMapping("/new-room")
    public ResponseEntity<HttpStatus> postNewRoom(@RequestBody RoomMockup roomMockup) {
        Room room = roomMockup.mockToRoom();
        roomService.save(room);
        logger.info("New room created: " + room );
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PutMapping("edit-room/{id}")
    RoomMockup updateRoom(@RequestBody RoomMockup roomMockup, @PathVariable Long id) {
        Room room = roomMockup.mockToRoom();
        Room currentRoom = roomService.get(id);
        Room oldRoom = currentRoom;
        if (currentRoom != null) {
            currentRoom.setLocalization(room.getLocalization());
            currentRoom.setMaximalCapacity(room.getMaximalCapacity());
            currentRoom.setNumber(room.getNumber());
            currentRoom.setRecommendedCapacity(room.getRecommendedCapacity());
            currentRoom = roomService.save(currentRoom);
            logger.info("Updated room " + oldRoom + " to " + currentRoom);
            return new RoomMockup(currentRoom);
        } else {
            room = roomService.save(room);
            logger.info("New room created " + room);
            return new RoomMockup(room);
        }
    }

    @DeleteMapping("delete-room/{id}")
    public ResponseEntity<HttpStatus> deleteRoom(@PathVariable Long id) {
        Room currentRoom = roomService.get(id);
        if (currentRoom != null) {
             roomService.delete(id);
             logger.info("Deleted room " + currentRoom);
        }
        return ResponseEntity.ok(HttpStatus.OK);

    }

}
