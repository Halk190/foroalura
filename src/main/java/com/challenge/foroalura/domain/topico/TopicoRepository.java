package com.challenge.foroalura.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico,Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    @Query("""
            select t 
            from Topico t
            where
            t.id = :idTopico
            """)
    boolean findActivoById(Long idTopico);

    Page<Topico> findAllByCursoNombre(String cursoNombre, Pageable pageable);
}
