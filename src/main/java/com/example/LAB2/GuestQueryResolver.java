package com.example.LAB2;

import com.example.LAB2.model.Guest;
import com.example.LAB2.repository.GuestRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GuestQueryResolver implements GraphQLQueryResolver {

    private final GuestRepository guestRepository;

    @Autowired
    public GuestQueryResolver(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Guest getGuestById(Long id) {
        Optional<Guest> guestOptional = guestRepository.findById(id);
        return guestOptional.orElse(null); // Handle null case appropriately
    }
}
