package com.challenge.foroalura.domain.curso;

public record CursoResponseDTO(
        Long id,
        String nombre,
        String categoria
) {
    public CursoResponseDTO(Curso curso){ this(curso.getId(), curso.getNombre(),curso.getCategoria().toString()); }
}
