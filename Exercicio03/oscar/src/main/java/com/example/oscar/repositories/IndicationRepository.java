package com.example.oscar.repositories;

import com.example.oscar.models.IndicationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IndicationRepository extends JpaRepository<IndicationModel, UUID> {
}
