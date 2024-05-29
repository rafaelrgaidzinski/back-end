package com.example.transacoes.repositories;

import com.example.transacoes.models.TransacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoModel, UUID> {
}
