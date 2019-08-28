package com.eventos.repository;

        import org.springframework.data.jpa.repository.JpaRepository;

        import com.eventos.models.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, String>{

    Disciplina findByCodigo(long codigo);
}
