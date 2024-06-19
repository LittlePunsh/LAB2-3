package com.example.LAB2.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        com.example.LAB2.grpc.GuestServiceGrpc.GuestServiceBlockingStub stub = com.example.LAB2.grpc.GuestServiceGrpc.newBlockingStub(channel);

        // Создание запроса GetGuestsRequest
        com.google.protobuf.Empty request = com.google.protobuf.Empty.getDefaultInstance();

        // Вызов метода сервера getGuests с использованием запроса GetGuestsRequest
        com.example.LAB2.grpc.Sanatorium.GetGuestsResponse response = stub.getGuests(request);

        // Обработка ответа
        response.getGuestsList().forEach(guestEntity -> {
            System.out.println("Guest ID: " + guestEntity.getId());
            System.out.println("First Name: " + guestEntity.getFirstName());
            System.out.println("Last Name: " + guestEntity.getLastName());
            System.out.println("---------------------");
        });

        channel.shutdown();
    }
}
