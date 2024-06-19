package com.example.LAB2.model;



import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class ProcedureSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Booking booking;

    @ManyToOne
    private Procedure procedure;

    private Timestamp sessionDate;
    private int duration;
    private String resultNotes;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public Timestamp getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Timestamp sessionDate) {
        this.sessionDate = sessionDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getResultNotes() {
        return resultNotes;
    }

    public void setResultNotes(String resultNotes) {
        this.resultNotes = resultNotes;
    }
}
