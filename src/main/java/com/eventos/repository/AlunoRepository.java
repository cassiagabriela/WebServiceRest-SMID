package com.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventos.models.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, String>{

    Aluno findByMatricula(long matricula);
}
