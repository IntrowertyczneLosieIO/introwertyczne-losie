package com.agh.introwertycznelosie.repositories;

import com.agh.introwertycznelosie.data.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room, Long> {

}

