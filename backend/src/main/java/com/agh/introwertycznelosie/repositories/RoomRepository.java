package com.agh.introwertycznelosie.repositories;

import com.agh.introwertycznelosie.data.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findTop3ByOrderByIdDesc();
    List<Room> findAllByOrderByIdDesc();
}

