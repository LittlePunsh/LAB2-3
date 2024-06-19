package com.example.LAB2.controller;


import com.example.LAB2.model.Room;
import com.example.LAB2.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room Id:" + id));
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room Id:" + id));
        room.setId(roomDetails.getType());
        return roomRepository.save(room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room Id:" + id));
        roomRepository.delete(room);
    }
}
