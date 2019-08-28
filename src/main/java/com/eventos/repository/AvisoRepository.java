package com.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventos.models.Aviso;

public interface AvisoRepository extends JpaRepository<Aviso, String>{

    Aviso findByCodigo(long codigo);
}
