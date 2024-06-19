package com.example.LAB2.controller;

import com.example.LAB2.model.Guest;
import com.example.LAB2.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    @Autowired
    private GuestRepository guestRepository;

    @GetMapping
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    @GetMapping("/{id}")
    public Guest getGuestById(@PathVariable Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid guest Id:" + id));
    }

    @PostMapping
    public Guest createGuest(@RequestBody Guest guest) {
        return guestRepository.save(guest);
    }

    @PutMapping("/{id}")
    public Guest updateGuest(@PathVariable Long id, @RequestBody Guest guestDetails) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid guest Id:" + id));
        guest.setId(guestDetails.getName());
        return guestRepository.save(guest);
    }

    @DeleteMapping("/{id}")
    public void deleteGuest(@PathVariable Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid guest Id:" + id));
        guestRepository.delete(guest);
    }
}