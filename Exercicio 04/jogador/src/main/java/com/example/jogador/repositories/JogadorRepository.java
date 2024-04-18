package com.example.jogador.repositories;

import com.example.jogador.models.JogadorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JogadorRepository extends JpaRepository<JogadorModel, UUID> {
}
