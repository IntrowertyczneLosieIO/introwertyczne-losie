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

    @PutMapping("edit-room/{id}")
    Room updateRoom(@RequestBody Room room, @PathVariable Long id) {
        Room currentRoom = roomService.get(id);
        if (currentRoom != null) {
            currentRoom.setLocalization(room.getLocalization());
            currentRoom.setMaximalCapacity(room.getMaximalCapacity());
            currentRoom.setNumber(room.getNumber());
            currentRoom.setRecommendedCapacity(room.getRecommendedCapacity());
            return roomService.save(currentRoom);
        } else {
            room.setId(id);
            return roomService.save(room);
        }
    }

    @DeleteMapping("delete-room/{id}")
    public ResponseEntity<HttpStatus> deleteRoom(@PathVariable Long id) {
        Room currentRoom = roomService.get(id);
        if (currentRoom != null) {
             roomService.delete(id);
        }
        return ResponseEntity.ok(HttpStatus.OK);

    }

}
