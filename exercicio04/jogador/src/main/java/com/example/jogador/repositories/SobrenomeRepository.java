package com.example.jogador.repositories;

import com.example.jogador.models.SobrenomeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SobrenomeRepository extends JpaRepository<SobrenomeModel, UUID> {
}
