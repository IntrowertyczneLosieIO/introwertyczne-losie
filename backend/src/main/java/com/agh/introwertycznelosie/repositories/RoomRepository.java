package com.agh.introwertycznelosie.repositories;

import com.agh.introwertycznelosie.data.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByNumber(String number);
}

