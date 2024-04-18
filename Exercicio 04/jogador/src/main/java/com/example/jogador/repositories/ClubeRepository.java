package com.example.jogador.repositories;

import com.example.jogador.models.ClubeModel;
import com.example.jogador.models.JogadorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClubeRepository extends JpaRepository<ClubeModel, UUID> {
}