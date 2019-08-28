package com.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventos.models.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, String>{

    Professor findByCodigo(long codigo);
}
