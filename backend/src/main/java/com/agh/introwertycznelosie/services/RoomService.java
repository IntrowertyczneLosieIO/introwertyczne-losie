package com.agh.introwertycznelosie.services;

import com.agh.introwertycznelosie.data.Room;
import com.agh.introwertycznelosie.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public Room save(Room room) { return roomRepository.save(room);}

    public List<Room> get() {
        return roomRepository.findTop3ByOrderByIdDesc();
    }

    public Room get(Long id) { return roomRepository.getOne(id);}

    public void delete(Long id) { roomRepository.deleteById(id);}

    public void delete(Room room) { roomRepository.delete(room);}

}