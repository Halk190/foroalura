package com.challenge.foroalura.domain.curso.datos;

import com.challenge.foroalura.domain.curso.Curso;

public record CursoResponseDTO(
        Long id,
        String nombre,
        String categoria
) {
    public CursoResponseDTO(Curso curso){ this(curso.getId(), curso.getNombre(),curso.getCategoria().toString()); }
}
