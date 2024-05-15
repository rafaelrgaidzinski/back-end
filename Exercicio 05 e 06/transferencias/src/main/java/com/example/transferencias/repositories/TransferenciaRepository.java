package com.example.transferencias.repositories;

import com.example.transferencias.models.TransferenciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransferenciaRepository extends JpaRepository<TransferenciaModel, UUID> {
}
