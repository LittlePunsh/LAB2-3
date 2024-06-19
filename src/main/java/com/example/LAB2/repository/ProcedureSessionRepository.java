package com.example.LAB2.repository;

import com.example.LAB2.model.ProcedureSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedureSessionRepository extends JpaRepository<ProcedureSession, Long> {
}
