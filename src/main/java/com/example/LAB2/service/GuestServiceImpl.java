package com.example.LAB2.service;

import com.example.LAB2.grpc.Sanatorium.GetGuestsResponse;
import com.example.LAB2.grpc.Sanatorium.GuestEntity;
import com.example.LAB2.grpc.GuestServiceGrpc;
import com.example.LAB2.model.Guest;
import com.example.LAB2.repository.GuestRepository;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@GrpcService
public class GuestServiceImpl extends GuestServiceGrpc.GuestServiceImplBase {

    private final GuestRepository guestRepository;

    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public void getGuests(Empty request, StreamObserver<GetGuestsResponse> responseObserver) {
        Iterable<Guest> guests = guestRepository.findAll();

        GetGuestsResponse response = GetGuestsResponse.newBuilder()
                .addAllGuests(StreamSupport.stream(guests.spliterator(), false)
                        .map(guest -> GuestEntity.newBuilder()
                                .setId(Math.toIntExact(guest.getId()))
                                .setFirstName(guest.getFirstName())
                                .setLastName(guest.getLastName())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
