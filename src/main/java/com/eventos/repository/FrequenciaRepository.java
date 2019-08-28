package com.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventos.models.Frequencia;

public interface FrequenciaRepository extends JpaRepository<Frequencia, String>{

    Frequencia findByCodigo(long codigo);
}
