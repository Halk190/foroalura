package com.challenge.foroalura.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso,Long> {
    @Query("SELECT c FROM Curso c WHERE LOWER(c.nombre) = LOWER(:nombre)")
    Optional<Curso> findByNombre(@Param("nombre")String nombre);
    boolean existsByNombre(String nombre);
}
