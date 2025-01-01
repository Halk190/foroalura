package com.challenge.foroalura.domain.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public CursoResponseDTO registrarCurso(CursoDTO cursoDTO) {

        Curso curso = new Curso(
                cursoDTO.nombre(),
                cursoDTO.categoria()
        );

        cursoRepository.save(curso);

        // Crear el DTO de respuesta
        return new CursoResponseDTO(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria().toString()
        );
    }
}
