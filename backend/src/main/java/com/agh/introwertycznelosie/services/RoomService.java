package com.agh.introwertycznelosie.services;

import com.agh.introwertycznelosie.data.Room;
import com.agh.introwertycznelosie.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public void save(Room room) { roomRepository.save(room);}

    public Room get(Long id) {return roomRepository.getOne(id);}

    public Room findByNumber(String number) { return roomRepository.findByNumber(number);}

    public void delete(Long id) {roomRepository.delete(get(id));}

    public void delete(Room room) { roomRepository.delete(room);}

}
