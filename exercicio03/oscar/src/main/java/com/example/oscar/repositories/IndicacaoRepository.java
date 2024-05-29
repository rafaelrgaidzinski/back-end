package com.example.oscar.repositories;

import com.example.oscar.models.IndicacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IndicacaoRepository extends JpaRepository<IndicacaoModel, UUID> {
}
