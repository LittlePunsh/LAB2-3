package com.example.LAB2.controller;

import com.example.LAB2.model.ProcedureSession;
import com.example.LAB2.repository.ProcedureSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procedure-sessions")
public class ProcedureSessionController {

    @Autowired
    private ProcedureSessionRepository procedureSessionRepository;

    @GetMapping
    public List<ProcedureSession> getAllProcedureSessions() {
        return procedureSessionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ProcedureSession getProcedureSessionById(@PathVariable Long id) {
        return procedureSessionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid procedure session Id:" + id));
    }

    @PostMapping
    public ProcedureSession createProcedureSession(@RequestBody ProcedureSession procedureSession) {
        return procedureSessionRepository.save(procedureSession);
    }

    @PutMapping("/{id}")
    public ProcedureSession updateProcedureSession(@PathVariable Long id, @RequestBody ProcedureSession procedureSessionDetails) {
        ProcedureSession procedureSession = procedureSessionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid procedure session Id:" + id));
        procedureSession.setBooking(procedureSessionDetails.getBooking());
        procedureSession.setProcedure(procedureSessionDetails.getProcedure());
        procedureSession.setSessionDate(procedureSessionDetails.getSessionDate());
        procedureSession.setDuration(procedureSessionDetails.getDuration());
        procedureSession.setResultNotes(procedureSessionDetails.getResultNotes());
        return procedureSessionRepository.save(procedureSession);
    }

    @DeleteMapping("/{id}")
    public void deleteProcedureSession(@PathVariable Long id) {
        ProcedureSession procedureSession = procedureSessionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid procedure session Id:" + id));
        procedureSessionRepository.delete(procedureSession);
    }
}
