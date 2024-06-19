//http://localhost:8080/h2-console - подключение к H2
//jdbc:h2:file:C:/h2/Sanatoriumforlabs
//LittlePunsh - Login
//123 - Password
//http://localhost:8080/api/bookings
//http://localhost:8080/api/guests
//http://localhost:8080/api/procedures
//http://localhost:8080/api/procedure-sessions
//http://localhost:8080/api/rooms
//http://localhost:8080/graphiql/graphiql.html - не работает, пытаюсь сделать.


package com.example.LAB2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SanatoriumDatabaseH2Application {
	public static void main(String[] args) {
		SpringApplication.run(SanatoriumDatabaseH2Application.class, args);
	}
}
