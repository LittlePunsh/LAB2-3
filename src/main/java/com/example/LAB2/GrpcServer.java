 package com.example.LAB2;

import com.example.LAB2.grpc.GuestServiceGrpc;
import com.example.LAB2.model.Guest;
import com.example.LAB2.grpc.Sanatorium.GetGuestsResponse;
import com.example.LAB2.grpc.Sanatorium.GuestEntity;
import com.example.LAB2.repository.GuestRepository;
import com.google.protobuf.Empty;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SpringBootApplication(scanBasePackages = "com.example.LAB2")
public class GrpcServer {

    @Autowired
    private GuestRepository guestRepository;

    public static void main(String[] args) {
        SpringApplication.run(GrpcServer.class, args);
    }

    @Bean
    public CommandLineRunner grpcServerRunner(GuestRepository guestRepository) {
        return args -> {
            Server server = ServerBuilder.forPort(9090)
                    .addService(new GuestServiceImpl(guestRepository))
                    .build();

            server.start();
            System.out.println("Server started, listening on " + server.getPort());

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutting down gRPC server");
                server.shutdown();
            }));

            server.awaitTermination();
        };
    }

    @GrpcService
    static class GuestServiceImpl extends GuestServiceGrpc.GuestServiceImplBase {

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
}
