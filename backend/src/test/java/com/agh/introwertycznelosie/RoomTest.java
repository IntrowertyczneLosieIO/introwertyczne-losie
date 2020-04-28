package com.agh.introwertycznelosie;

import com.agh.introwertycznelosie.data.DateRange;
import com.agh.introwertycznelosie.data.Room;
import com.agh.introwertycznelosie.repositories.RoomRepository;
import com.agh.introwertycznelosie.services.RoomService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestMethod;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RoomTest {
    private static Room r1;
    private static Room r2;
    @Autowired
    private RoomService roomService;

    @BeforeAll
    public static void setup(){
        LinkedList<DateRange> availableDates = new LinkedList<>();
        availableDates.add(new DateRange());
        availableDates.add(new DateRange());
        r1 = new Room(100, 200, "d17", "3.42", availableDates);
        availableDates.add(new DateRange());
        r2 = new Room(200, 250, "d10", "1.11", availableDates);
    }

    @Test
    void testRoomSave(){
        roomService.save(r1);
        roomService.save(r2);
    }
    @Test
    void testRoomGet(){
        assertEquals(roomService.get(r1.getId()).getId(), r1.getId());
        assertEquals(roomService.get(r2.getId()).getId(), r2.getId());
    }

    @Test
    void testFindRoomByNumber(){
        //assertEquals(roomService.findByNumber("3.42").getId(), r1.getId());
        //assertEquals(roomService.findByNumber("1.11").getId(), r2.getId());
    }

    @Test
    @AfterTestMethod("testFindRoomByNumber")
    void testDeleteById(){
        roomService.delete(r2.getId());
    }

    @Test
    @AfterTestMethod("testFindRoomByNumber")
    void testDeleteByObject(){
        roomService.delete(r1);
    }
}
