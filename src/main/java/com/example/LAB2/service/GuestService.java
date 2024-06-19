package com.example.LAB2.service;

import com.example.LAB2.model.Guest;
import com.example.LAB2.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public Guest saveGuest(Guest guest) {
        Guest savedGuest = guestRepository.save(guest);
        // Добавьте логирование
        System.out.println("Guest saved: " + savedGuest.getId());
        return savedGuest;
    }
}

