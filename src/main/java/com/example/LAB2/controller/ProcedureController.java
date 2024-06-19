package com.example.LAB2.controller;

import com.example.LAB2.model.Procedure;
import com.example.LAB2.repository.ProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procedures")
public class ProcedureController {

    @Autowired
    private ProcedureRepository procedureRepository;

    @GetMapping
    public List<Procedure> getAllProcedures() {
        return procedureRepository.findAll();
    }

    @GetMapping("/{id}")
    public Procedure getProcedureById(@PathVariable Long id) {
        return procedureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid procedure Id:" + id));
    }

    @PostMapping
    public Procedure createProcedure(@RequestBody Procedure procedure) {
        return procedureRepository.save(procedure);
    }

    @PutMapping("/{id}")
    public Procedure updateProcedure(@PathVariable Long id, @RequestBody Procedure procedureDetails) {
        Procedure procedure = procedureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid procedure Id:" + id));
        procedure.setName(procedureDetails.getName());
        return procedureRepository.save(procedure);
    }

    @DeleteMapping("/{id}")
    public void deleteProcedure(@PathVariable Long id) {
        Procedure procedure = procedureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid procedure Id:" + id));
        procedureRepository.delete(procedure);
    }
}
